/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NI;

import signals.TextMessage;
import signals.HelloOK;
import signals.Hello;
import signals.Goodbye;
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
import signals.FileProposal;

/**
 *
 * @author root
 */
public class NI implements NiInterface {

    UDPSender udpSender;
    UDPServer udpServer;
    ChatSystem controller;
    InetAddress localIpAdress;
    InetAddress broadcast;
    InetAddress remoteIpAdress;
    ArrayList<InetAddress> rmteAddresses=new ArrayList();
    
    String localIpAdressString;
    String broadcastString;
    private String remoteIpAdressString;
    private ArrayList<String> rmteAddressesString=new ArrayList();

    public NI(ChatSystem controller, int portr, int ports) {
        udpSender = new UDPSender(this, ports);
        udpServer = new UDPServer(this, portr);
        udpServer.start();
        udpSender.start();
        this.controller = controller;
        try {
            localIpAdress = InetAddress.getByName("localhost");
            getIpOfInterfac("eth8");
            System.out.println(localIpAdressString+ "/"+ broadcastString);
        } catch (UnknownHostException ex) {
            Logger.getLogger(NI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void getMessage(Object obj) throws UnknownHostException {
        if (obj instanceof TextMessage) {
            controller.showMessage((TextMessage) obj);
            System.out.println(obj.toString());
        } else if (obj instanceof Hello) {
            Hello hello;
            hello = (Hello) obj;
            HelloOK helloOk = new HelloOK(controller.getUsername()+"@"+localIpAdressString);
            udpSender.sendHelloOk(helloOk, getIpAdressFromUsername(hello.getUsername()), false); 
            controller.showHello((Hello) obj);
        } else if (obj instanceof HelloOK) {
            controller.showHelloOK((HelloOK) obj);
        } else if (obj instanceof Goodbye) {
            controller.showGoodbye((Goodbye) obj);
        } else if(obj instanceof FileProposal){
            controller.showProposal((FileProposal) obj);
        }else {
            System.out.println("ERROR 404: Packet type not found!");

        }
    }

    @Override
    public void sendHello(String userName) {
        Hello hello = new Hello(userName+"@"+localIpAdressString);
        System.out.println("hello : "+ userName+"@"+localIpAdressString);
        udpSender.sendHello(hello, broadcast, true);
    }

    public void sendGoodbye(String userName) {
        Goodbye goodbye = new Goodbye(userName+"@"+localIpAdressString);
        udpSender.sendGoodbye(goodbye, broadcast, true);
    }
    
    public void sendProposal(String File, long size) {
        if(rmteAddresses.isEmpty()){
            //UNICAST
            ArrayList<String> to=new ArrayList<>();
            to.add(controller.getUsername());
            FileProposal proposal= new FileProposal(File, size, controller.getUsername(), to);
            udpSender.sendProposal(proposal, remoteIpAdress, false);
        }
        else{
            //MULTICAST
            ArrayList<String> to=new ArrayList<>();
            to.add(controller.getUsername());
            FileProposal proposal= new FileProposal(File, size, controller.getUsername(), to);
            for(int i=0; i<rmteAddresses.size(); i++){
                udpSender.sendProposal(proposal, rmteAddresses.get(i),false);
            }
        }
    }

    public void sendMessage(String msg) {
        if(rmteAddresses.isEmpty()){
            //UNICAST
            ArrayList<String> to=new ArrayList<>();
            to.add(controller.getUsername());
            TextMessage message=new TextMessage(msg,controller.getUsername(),to);
            udpSender.sendMsg(message, remoteIpAdress,false);
        }
        else{
            //MULTICAST
            ArrayList<String> to=new ArrayList<>();
            to.add(controller.getUsername());
            TextMessage message=new TextMessage(msg,controller.getUsername(),to);
            for(int i=0; i<rmteAddresses.size(); i++){
                udpSender.sendMsg(message, rmteAddresses.get(i),false);
            }
        }
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
    
    public void getIpAdresssFromUsernames(ArrayList<String> usernames) throws UnknownHostException {
        for(int i=0; i<usernames.size(); i++){
            String[] splited = usernames.get(i).split("@");
            InetAddress adress = InetAddress.getByName(splited[1]);
            this.rmteAddresses.add(adress);
        }
    }
    
    public String getNameFromUsername(String username) throws UnknownHostException {
        String[] splited = username.split("@");
        return splited[0];
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
        this.remoteIpAdressString = remoteIpAdressString;
        setRemoteIpAdress(getIpAdressFromUsername(remoteIpAdressString));
    }

    public ArrayList<InetAddress> getRmteAddresses() {
        return rmteAddresses;
    }

    public void setRmteAddresses(ArrayList<InetAddress> rmteAddresses) {
        this.rmteAddresses = rmteAddresses;
    }

    public ArrayList<String> getRmteAddressesString() {
        return rmteAddressesString;
    }

    public void setRmteAddressesString(ArrayList<String> rmteAddressesString) throws UnknownHostException {
        this.rmteAddressesString = rmteAddressesString;
        getIpAdresssFromUsernames(rmteAddressesString);
    }
    

    private void getIpOfInterfac(String inter) throws UnknownHostException {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();

                //System.out.println("    " + intf.getName() + " " + intf.getDisplayName());
                if (intf.getName().equals(inter)) {
                   /* for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                        //System.out.println("        " + enumIpAddr.nextElement().toString());
                        InetAddress adressInterface = enumIpAddr.nextElement();
                        if (adressInterface.getAddress().length == 4) {
                            localIpAdress = adressInterface;
                        }
                    }*/
                    for (InterfaceAddress intAddress : intf.getInterfaceAddresses()) {
                        {
                            if (intAddress.getAddress().getAddress().length == 4) {
                                localIpAdress=intAddress.getAddress();
                                localIpAdressString=localIpAdress.getHostAddress();
                                broadcast=intAddress.getBroadcast();
                                broadcastString=broadcast.getHostAddress();
                               // System.out.println(intAddress.getBroadcast());
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