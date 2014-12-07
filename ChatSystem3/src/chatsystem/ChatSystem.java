/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatsystem;

import signals.*;
import GUI.GUI;
import NI.NI;
import java.io.IOException;
import java.util.Scanner;
import chatsystem.*;
import java.net.UnknownHostException;
import javax.swing.text.BadLocationException;
import signals.FileProposal;

/**
 *
 * @author root
 */
public class ChatSystem {

    static GUI gui;
    static NI ni;
    public boolean CONNECTED = false;
    public String Language= "English";

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
        this.CONNECTED=false;
    }

    public void sendMessage(String msg) {
        ni.sendMessage(msg);
    }
    
     public void showMessage(TextMessage msg) throws BadLocationException {
            gui.setMsg(msg.getFrom(),msg.getMessage());
    }
    
    /*
    *---File Transfer
    */
    public void sendProposal(String Name, long size) {
        ni.sendFileProposal(Name, size);
    }

    public void acceptFileTransfer(String fileName,String from) throws IOException {
        ni.acceptFileTransfer(fileName,from);
    }
   
    
    /*
    Hello, HelloOK, GoodBye, and FileProposal
    */
    public void showHello(Hello hello) throws BadLocationException{
        if(this.Language=="English"){
            gui.setMsg(hello.getUsername(),"Hello, I'm "+hello.getUsername());
        }else if(this.Language=="Francais"){
            gui.setMsg(hello.getUsername(),"Bonjour, je suis "+hello.getUsername());
        }
        
        gui.addUser(hello.getUsername());
    }
    
    public void showHelloOK(HelloOK helloOk) throws BadLocationException{
        if(this.Language=="English"){
            gui.setMsg(helloOk.getUsername(),"Hello, I'm "+helloOk.getUsername());
        }else if(this.Language=="Francais"){
            gui.setMsg(helloOk.getUsername(),"Bonjour, je suis "+helloOk.getUsername());
        }
        gui.addUser(helloOk.getUsername());
    }
    
    public void showGoodbye(Goodbye goodbye) throws BadLocationException{
        if(this.Language=="English"){
            gui.setMsg(goodbye.getUsername(),"Goodbye, I'm "+goodbye.getUsername());
        }else if(this.Language=="Francais"){
            gui.setMsg(goodbye.getUsername(),"Aurevoir, je suis "+goodbye.getUsername());
        }
        gui.rmvUser(goodbye.getUsername());
    }
    
    
    public void showFileProposal(FileProposal fileProposal) throws BadLocationException {
        if(this.Language=="English"){
            gui.setMsg(fileProposal.getFrom(),"Hey, Wanna get the file "+fileProposal.getFileName()+ " from "+fileProposal.getFrom()+" ?");
        }else if(this.Language=="Francais"){
            gui.setMsg(fileProposal.getFrom(),"Hey, veut-tu telecharger le fichier "+fileProposal.getFileName()+ " envoyé par "+fileProposal.getFrom()+" ?");
        }
        gui.showChoice(fileProposal.getFileName(),fileProposal.getFrom());
    }
    
    /*
    Getters and Setters
    */
    
    public String getUsername(){
        return this.gui.getUser()+"@"+ni.getLocalIpAdressString();
    }
    
    public void setRemoteIpAdress(String username) throws UnknownHostException{
        ni.setRemoteIpAdressString(username);
    }

    

}
