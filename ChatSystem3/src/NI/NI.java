/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NI;

import signals.TextMessage;
import signals.HelloOk;
import signals.Hello;
import signals.Goodbye;
import chatsystem.ChatSystem;
import chatsystem.*;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class NI implements NiInterface {

    UDPSender udpSender;
    UDPServer udpServer;
    ChatSystem controller;
    InetAddress localIpAdress ;
    InetAddress broadcast;

    public NI(ChatSystem controller, int portr, int ports) {
        udpSender = new UDPSender(this, ports);
        udpServer = new UDPServer(this, portr);
        udpServer.start();
        udpSender.start();
        this.controller = controller;
        try {
            localIpAdress = InetAddress.getByName("localhost");
            getIpOfInterfac("wlan0");
            broadcast=localIpAdress;
            byte[]address =broadcast.getAddress();
            address[3]=(byte) 0xff;
            address[2]=(byte) 0x00;
            broadcast=InetAddress.getByAddress(address);
            System.out.println(localIpAdress.toString());
        } catch (UnknownHostException ex) {
            Logger.getLogger(NI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void getMessage(Object obj) throws UnknownHostException {
        if(obj instanceof TextMessage){
           controller.showMessage((TextMessage)obj);
           System.out.println(obj.toString());
        }
        else if(obj instanceof Hello) {
           Hello hello;
           hello = (Hello)obj;
           HelloOk helloOk= new HelloOk(controller.getUsername(), localIpAdress);
         //  udpSender.sendHelloOk(helloOk, hello.getAdr(), false); //Normalement hello.getAdr()
           controller.showHello((Hello)obj);
        }
        else if(obj instanceof HelloOk){
           controller.showHelloOk((HelloOk)obj);
        }
        else if(obj instanceof Goodbye){
           controller.showGoodbye((Goodbye)obj);
        }
        else{
            System.out.println("ERROR 404: Packet type not found!");
            
        }
    }

    @Override
    public void sendHello(String userName) {
        Hello hello= new Hello(userName);//, this.localIpAdress);
        System.out.println(broadcast);
        udpSender.sendHello(hello,broadcast,true); //Normalement Broadcast Address !
    }
    
    public void sendGoodbye(String userName){
        Goodbye goodbye= new Goodbye(userName, localIpAdress);
        udpSender.sendGoodbye(goodbye, localIpAdress, false);
    }

    public void sendMessage(String msg) {
      //  TextMessage message=new TextMessage(controller.getUsername(), broadcast, msg);
      //  udpSender.sendMsg(message, broadcast,true);
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

    private void getIpOfInterfac(String inter) throws UnknownHostException{
            try {
                for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                    NetworkInterface intf = en.nextElement();
                    //System.out.println("    " + intf.getName() + " " + intf.getDisplayName());
                    if (intf.getName().equals(inter)) {
                        for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                           //System.out.println("        " + enumIpAddr.nextElement().toString());
                            
                                localIpAdress=enumIpAddr.nextElement();
            
                        }
                    }
                }
            } catch (SocketException e) {
                System.out.println(" (error retrieving network interface list)");
            }
            
    }
}

