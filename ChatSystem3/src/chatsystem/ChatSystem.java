/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatsystem;

import signals.TextMessage;
import signals.HelloOK;
import signals.Hello;
import signals.Goodbye;
import GUI.GUI;
import NI.NI;
import java.io.IOException;
import java.util.Scanner;
import chatsystem.*;
import java.net.UnknownHostException;
import java.util.ArrayList;
import signals.FileProposal;

/**
 *
 * @author root
 */
public class ChatSystem {

    static GUI gui;
    static NI ni;
    public boolean CONNECTED = false;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        ChatSystem controller = new ChatSystem();
        gui = new GUI(controller);
        ni = new NI(controller,Integer.valueOf(args[0]),Integer.valueOf(args[1]));
        gui.start();
    }

    public void sendHello(String userName) {
        ni.sendHello(userName);
    }
    
    public void sndGoodbye(String userName){
        ni.sendGoodbye(userName);
    }

    public void sendMessage(String msg) {
        ni.sendMessage(msg);
    }
    
    public void sendProposal(String File, long size) {
        ni.sendProposal(File, size);
    }

    public void showMessage(TextMessage msg) {
            gui.setMsg(msg.getFrom(),msg.getMessage());
    }
    
    public void showHello(Hello hello){
        gui.setMsg(hello.getUsername(),"Hello, I'm "+hello.getUsername());
        gui.addUser(hello.getUsername());
    }
    
    public void showHelloOK(HelloOK helloOk){
        gui.setMsg(helloOk.getUsername(),"Hello, I'm "+helloOk.getUsername());
        gui.addUser(helloOk.getUsername());
    }
    
    public void showGoodbye(Goodbye goodbye){
        gui.setMsg(goodbye.getUsername(),"Goodbye, I'm "+goodbye.getUsername());
        gui.rmvUser(goodbye.getUsername());
    }
    
    public void showProposal(FileProposal fileproposal) {
        gui.setMsg(fileproposal.getFrom(), "Received a File proposal from "+fileproposal.getFrom());
    }
    
    
    public String getUsername(){
        return this.gui.getUser();
    }
    
    public void setRemoteIpAdress(String username) throws UnknownHostException{
        ni.setRemoteIpAdressString(username);
    }
    
    public void setRmteIpAddresses(ArrayList<String> addresses) throws UnknownHostException{
        ni.setRmteAddressesString(addresses);
    }

    
}