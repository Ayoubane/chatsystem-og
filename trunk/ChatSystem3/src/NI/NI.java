/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NI;

import chatsystem.ChatSystem;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import signals.FileProposal;
import signals.FileProposalOK;
import signals.FileTransferOK;
import signals.Goodbye;
import signals.Hello;
import signals.HelloOK;
import signals.TextMessage;

/**
 *
 * @author root
 */
public class NI implements NiInterface {

    UDPSender udpSender;
    UDPServer udpServer;
    TCPSend tcpSender;
    TCPServ tcpServer;
    ChatSystem controller;
    InetAddress localIpAdress;
    InetAddress broadcast;
    InetAddress remoteIpAdress;

    String localIpAdressString;
    String broadcastString;
    private String remoteIpAdressString;

    public NI(ChatSystem controller, int portr, int ports) {
        udpSender = new UDPSender(this, ports);
        udpServer = new UDPServer(this, portr);
        tcpServer = new TCPServ();
        tcpSender = new TCPSend();
        udpServer.start();
        udpSender.start();
        this.controller = controller;
        try {
            localIpAdress = InetAddress.getByName("localhost");
            getIpOfInterface("wlan0");
           // broadcastString="255.255.255.255";
            broadcast=InetAddress.getByName(broadcastString);
            System.out.println(localIpAdressString + "/" + broadcastString);
        } catch (UnknownHostException ex) {
            Logger.getLogger(NI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void getMessage(Object obj) throws UnknownHostException {
        if (obj instanceof TextMessage) {
            controller.showMessage((TextMessage) obj);
            TextMessage txt = (TextMessage) obj;
            System.out.println("\nTxt Rcvd" + txt.getMessage() + "\n");
        } else if (obj instanceof Hello) {
            Hello hello;
            hello = (Hello) obj;
            if (!controller.getUsername().equals(hello.getUsername())) {
                HelloOK helloOk = new HelloOK(controller.getUsername());
                udpSender.sendHelloOk(helloOk, getIpAdressFromUsername(hello.getUsername()), false);
                System.out.println("HEllo OK" + helloOk.getUsername());
                controller.showHello((Hello) obj);
            }
        } else if (obj instanceof HelloOK) {
            controller.showHelloOK((HelloOK) obj);
        } else if (obj instanceof Goodbye) {
            controller.showGoodbye((Goodbye) obj);
        } else if (obj instanceof FileProposal) {
            controller.showFileProposal((FileProposal) obj);  
        }else if (obj instanceof FileTransferOK) {
           tcpSender.start();
        }else {
            System.out.println("ERROR 404: Packet type not found!");

        }
    }

    @Override
    public void sendHello(String userName) {
        Hello hello = new Hello(userName + "@" + localIpAdressString);
        System.out.println("hello : " + userName + "@" + localIpAdressString);
        udpSender.sendHello(hello, broadcast, true);
    }

    public void sendGoodbye(String userName) {
        Goodbye goodbye = new Goodbye(userName + "@" + localIpAdressString);
        udpSender.sendGoodbye(goodbye, broadcast, true);
    }

    public void sendMessage(String msg) {
        ArrayList<String> to = new ArrayList<>();
        to.add(remoteIpAdressString);
        TextMessage message = new TextMessage(msg, controller.getUsername(), to);
        udpSender.sendMsg(message, remoteIpAdress, false);
    }

    /*
     Files
     */
    public void sendFileProposal(String Name, long size) {
        ArrayList<String> to = new ArrayList<>();
        to.add(remoteIpAdressString);
        System.out.println("fole name=" + Name);
        String[] fileName = Name.split("/");

        FileProposal fileprop = new FileProposal(fileName[fileName.length - 1], size, controller.getUsername(), to);
        udpSender.sendFilePropose(fileprop, remoteIpAdress, false);
        tcpSender.setReceiver(remoteIpAdressString);
        tcpSender.RUN = true;
        tcpSender.setFileName(Name);

    }

    public void acceptFileTransfer(String fileName, String from) {
       
        System.out.println("waiting file from : "+from);
        tcpServer.setfileName(fileName); //A Server will be opened to send the file
        tcpServer.start();
        
        FileTransferOK fileProposalOK = new FileTransferOK();
        udpSender.sendFileProposeOK(fileProposalOK, localIpAdress, true);
    }

    @Override
    public void closeConnection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void sendMsg(String msg, String from, String[] to) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void sendFile(String path, String from, String[] to) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public InetAddress getIpAdressFromUsername(String username) throws UnknownHostException {
        String[] splited = username.split("@");
        InetAddress adress = InetAddress.getByName(splited[1]);
        return adress;
    }

    public String getNameFromUsername(String username) throws UnknownHostException {
        String[] splited = username.split("@");
        return splited[0];
    }

    public String getLocalIpAdressString() {
        return localIpAdressString;
    }

    public InetAddress getRemoteIpAdress() {
        return remoteIpAdress;
    }

    public void setRemoteIpAdress(InetAddress remoteIpAdress) {
        this.remoteIpAdress = remoteIpAdress;
    }

    public String getRemoteIpAdressString() {
        return remoteIpAdressString;
    }

    public void setRemoteIpAdressString(String remoteIpAdressString) throws UnknownHostException {
        this.remoteIpAdressString = getIpAdressFromUsername(remoteIpAdressString).getHostAddress();
        setRemoteIpAdress(getIpAdressFromUsername(remoteIpAdressString));
    }

    private void getIpOfInterface(String inter) throws UnknownHostException {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();

                //System.out.println("    " + intf.getName() + " " + intf.getDisplayName());
                if (intf.getName().equals(inter)) {
                    for (InterfaceAddress intAddress : intf.getInterfaceAddresses()) {
                        {
                            if (intAddress.getAddress().getAddress().length == 4) {
                                localIpAdress = intAddress.getAddress();
                                localIpAdressString = localIpAdress.getHostAddress();
                                broadcast = intAddress.getBroadcast();
                                broadcastString = broadcast.getHostAddress();
                            }
                        }
                    }
                }
            }
        } catch (SocketException e) {
            System.out.println(" (error retrieving network interface list)");
        }

    }

}
