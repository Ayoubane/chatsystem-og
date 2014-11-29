/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatsystem;

import signals.TextMessage;
import signals.HelloOk;
import signals.Hello;
import signals.Goodbye;
import GUI.GUI;
import NI.NI;
import java.io.IOException;
import java.util.Scanner;
import chatsystem.*;

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

    public void showMessage(TextMessage msg) {
            gui.setMsg(msg.getFrom(),msg.getMessage());
    }
    
    public void showHello(Hello hello){
        gui.setMsg(hello.getUsername(),"Hello, I'm "+hello.getUsername());
        gui.addUser(hello.getUsername());
    }
    
    public void showHelloOk(HelloOk helloOk){
        gui.setMsg(helloOk.getUsername(),"Hello, I'm "+helloOk.getUsername());
        gui.addUser(helloOk.getUsername());
    }
    
    public void showGoodbye(Goodbye goodbye){
        gui.setMsg(goodbye.getUsername(),"Goodbye, I'm "+goodbye.getUsername());
        gui.rmvUser(goodbye.getUsername());
    }
    
    
    public String getUsername(){
        return this.gui.getUser();
    }
    

}
