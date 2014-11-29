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
    InetAddress mask;

    public NI(ChatSystem controller, int portr, int ports) {
        udpSender = new UDPSender(this, ports);
        udpServer = new UDPServer(this, portr);
        udpServer.start();
        udpSender.start();
        this.controller = controller;
        try {
            localIpAdress = InetAddress.getByName("localhost");
            getIpOfInterfac("wlan0");
            System.out.println(localIpAdress.toString() + broadcast);
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
            HelloOK helloOk = new HelloOK(controller.getUsername()+""+localIpAdress);
            udpSender.sendHelloOk(helloOk, getIpAdressFromUsername(hello.getUsername()), false); 
            controller.showHello((Hello) obj);
        } else if (obj instanceof HelloOK) {
            controller.showHelloOK((HelloOK) obj);
        } else if (obj instanceof Goodbye) {
            controller.showGoodbye((Goodbye) obj);
        } else {
            System.out.println("ERROR 404: Packet type not found!");

        }
    }

    @Override
    public void sendHello(String userName) {
        Hello hello = new Hello(userName+""+localIpAdress.toString());
        System.out.println("hello : "+ userName+""+localIpAdress.toString());
        udpSender.sendHello(hello, broadcast, true);
    }

    public void sendGoodbye(String userName) {
        Goodbye goodbye = new Goodbye(userName+""+localIpAdress);
        udpSender.sendGoodbye(goodbye, broadcast, true);
    }

    public void sendMessage(String msg) {
        ArrayList<String> to=new ArrayList<>();
        to.add(controller.getUsername());
        TextMessage message=new TextMessage(msg,controller.getUsername(),to);
        udpSender.sendMsg(message, broadcast,true);
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
        String[] splited = username.split("/");
        InetAddress adress = InetAddress.getByName(splited[1]);
        return adress;
    }
    public String getNameFromUsername(String username) throws UnknownHostException {
        String[] splited = username.split("/");
        return splited[0];
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
                                broadcast=intAddress.getBroadcast();
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
