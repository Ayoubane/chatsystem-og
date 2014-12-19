/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NI;

import chatsystem.ChatSystem;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.BadLocationException;
import signals.*;

/**
 * The Chat NI
 *
 * @author Ayoub, Omar
 */
public class NI implements NiInterface {

    UDPSender udpSender;
    UDPServer udpServer;
    TCPSender tcpSender;
    TCPServer tcpServer;
    ChatSystem controller;
    InetAddress localIpAdress;
    InetAddress broadcast;
    InetAddress remoteIpAdress;

    String localIpAdressString;
    String broadcastString;
    private String remoteIpAdressString;

    Thread udpSendThread;
    Thread udpRcvThread;

    boolean serverRunning = true;

    /**
     * Creates a new Chat NI
     *
     * @param controller
     * @param portr
     * @param ports
     */
    public NI(ChatSystem controller, int portr, int ports) {
        udpSender = new UDPSender(this, ports);
        udpServer = new UDPServer(this, portr);
        tcpServer = new TCPServer();
        tcpSender = new TCPSender();
        udpSendThread = new Thread(udpSender);
        udpRcvThread = new Thread(udpServer);

        //--------TCPServer & TCPSender Init
        this.controller = controller;
        try {
            //Get IP Address and set Broadcast Address
            localIpAdress = InetAddress.getLocalHost();
            localIpAdressString = localIpAdress.getHostAddress();
            broadcastString = "255.255.255.255"; //We can use 255.255.255.255 as a broadcast as a general case
            broadcast = InetAddress.getByName(broadcastString);
            System.out.println(localIpAdressString + "/" + broadcastString);

        } catch (UnknownHostException ex) {
            Logger.getLogger(NI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Receives a message from the UDP Server and does the right action
     *
     * @param obj
     * @throws UnknownHostException
     * @throws BadLocationException
     */
    public void getMessage(Object obj) throws UnknownHostException, BadLocationException {
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
        } else if (obj instanceof FileTransferAccepted) {
            Thread tcpsnd = new Thread(tcpSender);
            tcpsnd.start();
            FileTransferAccepted accepted = (FileTransferAccepted) obj;
            ArrayList<String> to = new ArrayList<String>();
            to.add(accepted.getRemoteUsername());
            TextMessage txt = new TextMessage("File Transfered : " + accepted.getFileName(), controller.getUsername(), to);
            controller.showMessage(txt);

        } else {
            System.out.println("ERROR 404: Packet type not found!");

        }
    }

    /**
     * Sends a hello over the UDP Sender
     *
     * @param userName
     */
    @Override
    public void sendHello(String userName) {
        if (!udpSendThread.isAlive() && !udpRcvThread.isAlive()) {
            udpRcvThread.start();
            udpSendThread.start();
        }

        Hello hello = new Hello(userName + "@" + localIpAdressString);
        System.out.println("hello : " + userName + "@" + localIpAdressString);
        udpSender.sendHello(hello, broadcast, true);
    }

    /**
     * Sends a Goodbye over the UDP Sender
     *
     * @param userName
     */
    @Override
    public void sendGoodbye(String userName) {
        Goodbye goodbye = new Goodbye(userName + "@" + localIpAdressString);
        udpSender.sendGoodbye(goodbye, broadcast, true);
        this.serverRunning = false;
    }

    /**
     * Sends a message over the UDP Sender
     *
     * @param msg
     */
    @Override
    public void sendMessage(String msg) {
        ArrayList<String> to = new ArrayList<>();
        to.add(remoteIpAdressString);
        TextMessage message = new TextMessage(msg, controller.getUsername(), to);
        udpSender.sendMsg(message, remoteIpAdress, false);
    }

    /*
     Files
     */
    /**
     * Sends a FileProposal over the UDP Sender
     *
     * @param Name
     * @param size
     */
    @Override
    public void sendFileProposal(String Name, long size) {
        ArrayList<String> to = new ArrayList<>();
        to.add(remoteIpAdressString);
        System.out.println("file name=" + Name);
        String[] fileName = Name.split("/");

        FileProposal fileprop = new FileProposal(fileName[fileName.length - 1], size, controller.getUsername(), to);
        udpSender.sendFilePropose(fileprop, remoteIpAdress, false);
        tcpSender.setReceiver(remoteIpAdressString);
        tcpSender.setFileName(Name);

    }

    /**
     * Sends the Accept File Transfer and starts the TCP Server to receive it
     *
     * @param fileName
     * @param from
     */
    @Override
    public void acceptFileTransfer(String fileName, String from) {

        System.out.println("waiting file" + fileName + " ,  from : " + from);

        tcpServer.setfileName(fileName);
        //tcpServer.RUN = true;
        if (tcpServer.isAlive()) {
            //tcpServer.run();
        } else {
            tcpServer.start();
        }
        FileTransferAccepted fileProposalAccepted = new FileTransferAccepted(fileName, controller.getUsername());
        udpSender.sendFileProposeOK(fileProposalAccepted, getIpAdressFromUsername(from), true);
    }

    /**
     * Splits the username to get the IP Address from it
     *
     * @param username
     * @return
     */
    public InetAddress getIpAdressFromUsername(String username) {
        String[] splited = username.split("@");
        InetAddress adress;
        try {
            adress = InetAddress.getByName(splited[1]);
            return adress;
        } catch (UnknownHostException ex) {
            Logger.getLogger(NI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Splits the username to get only the username from it
     *
     * @param username
     * @return
     */
    public String getNameFromUsername(String username) {
        String[] splited = username.split("@");
        return splited[0];
    }

    /**
     * A Getter for the localIpAdressString field
     *
     * @return
     */
    public String getLocalIpAdressString() {
        return localIpAdressString;
    }

    /**
     * A Getter for the remoteIpAdress field
     *
     * @return
     */
    public InetAddress getRemoteIpAdress() {
        return remoteIpAdress;
    }

    /**
     * A Setter for the remoteIpAdress field
     *
     * @param remoteIpAdress
     */
    public void setRemoteIpAdress(InetAddress remoteIpAdress) {
        this.remoteIpAdress = remoteIpAdress;
    }

    /**
     * A Getter for the remoteIpAdressString field
     *
     * @return
     */
    public String getRemoteIpAdressString() {
        return remoteIpAdressString;
    }

    /**
     * A Setter for the remoteIpAdressString field that set remoteIpAdress too
     *
     * @param remoteIpAdressString
     *
     */
    public void setRemoteIpAdressString(String remoteIpAdressString) {
        this.remoteIpAdressString = getIpAdressFromUsername(remoteIpAdressString).getHostAddress();
        setRemoteIpAdress(getIpAdressFromUsername(remoteIpAdressString));
    }

    /**
     * Gets the IP Address from the interface connected to Internet
     *
     * @param inter
     * @throws UnknownHostException
     */
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

    public void notAcceptFileTransfer(String fileName, String from) {
        FileTransferNotAccepted fileProposalNOtAccepted = new FileTransferNotAccepted(fileName, controller.getUsername());
        udpSender.sendFileProposeNOTOK(fileProposalNOtAccepted, getIpAdressFromUsername(from), true);
    }

}
