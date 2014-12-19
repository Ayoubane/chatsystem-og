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
     * The main method for the Project
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        ChatSystem controller = new ChatSystem();
        gui = new GUI(controller);
        ni = new NI(controller,Integer.valueOf(args[0]),Integer.valueOf(args[1]));
        gui.start();
    }

    /**
     * Sends a hello using the NI
     * @param userName 
     */
    public void sendHello(String userName) {
        ni.sendHello(userName);
    }
    
    /**
     * Sends a goodbye using the NI
     * @param userName 
     */
    public void sendGoodbye(String userName){
        ni.sendGoodbye(userName);
        this.CONNECTED=false;
    }

    /**
     * Sends a message using the NI
     * @param msg 
     */
    public void sendMessage(String msg) {
        ni.sendMessage(msg);
    }
    
    /**
     * Shows a message in the ChatGui
     * @param msg
     * @throws BadLocationException 
     */
     public void showMessage(TextMessage msg) throws BadLocationException {
            gui.dispalyMsg(msg.getFrom(),msg.getMessage());
    }
    
     //-------------------   File Transfer   ---------------------------------//
     
    /**
     * Sends a fileProposal using the NI
     * @param Name
     * @param size 
     */
    public void sendProposal(String Name, long size) {
        ni.sendFileProposal(Name, size);
    }

    /**
     * Sends the acceptFileProposal using the NI
     * @param fileName
     * @param from
     * @throws IOException 
     */
    public void acceptFileTransfer(String fileName,String from) throws IOException {
        ni.acceptFileTransfer(fileName,from);
    }
   
    
    //------------    Hello, HelloOK, GoodBye, and FileProposal    -----------//
    
    /**
     * Shows the hello in the ChatGui
     * @param hello
     * @throws BadLocationException 
     */
    public void showHello(Hello hello) throws BadLocationException{
        if(this.Language=="English"){
            gui.dispalyMsg(hello.getUsername(),"Hello, I'm "+hello.getUsername());
        }else if(this.Language=="Francais"){
            gui.dispalyMsg(hello.getUsername(),"Bonjour, je suis "+hello.getUsername());
        }
        
        gui.addUser(hello.getUsername());
    }
    
    /**
     * Shows the helloOk in the ChatGui
     * @param helloOk 
     * @throws BadLocationException 
     */
    public void showHelloOK(HelloOK helloOk) throws BadLocationException{
        if(this.Language=="English"){
            gui.dispalyMsg(helloOk.getUsername(),"Hello, I'm "+helloOk.getUsername());
        }else if(this.Language=="Francais"){
            gui.dispalyMsg(helloOk.getUsername(),"Bonjour, je suis "+helloOk.getUsername());
        }
        gui.addUser(helloOk.getUsername());
    }
    
    /**
     * Shows the goodbye in the ChatGui
     * @param goodbye 
     * @throws BadLocationException 
     */
    public void showGoodbye(Goodbye goodbye) throws BadLocationException{
        if(this.Language=="English"){
            gui.dispalyMsg(goodbye.getUsername(),"Goodbye, I'm "+goodbye.getUsername());
        }else if(this.Language=="Francais"){
            gui.dispalyMsg(goodbye.getUsername(),"Aurevoir, je suis "+goodbye.getUsername());
        }
        gui.rmvUser(goodbye.getUsername());
    }
    
    /**
     * Shows the fileproposal in the ChatGui
     * @param fileProposal 
     * @throws BadLocationException 
     */
    public void showFileProposal(FileProposal fileProposal) throws BadLocationException {
        if(this.Language=="English"){
            gui.dispalyMsg(fileProposal.getFrom(),"Hey, Wanna get the file "+fileProposal.getFileName()+ " from "+fileProposal.getFrom()+" ?");
        }else if(this.Language=="Francais"){
            gui.dispalyMsg(fileProposal.getFrom(),"Hey, veut-tu telecharger le fichier "+fileProposal.getFileName()+ " envoyé par "+fileProposal.getFrom()+" ?");
        }
        gui.showChoice(fileProposal.getFileName(),fileProposal.getFrom());
    }
    
    //----------------------    Getters and Setters    -----------------------//
    
    /**
     * Creates the username@IpAddress used for transfers
     * @return 
     */
    public String getUsername(){
        return this.gui.getUser()+"@"+ni.getLocalIpAdressString();
    }
    
    /**
     * Sets the remote Address to which messages and/or files will be sent
     * @param username
     * @throws UnknownHostException 
     */
    public void setRemoteIpAdress(String username) throws UnknownHostException{
        ni.setRemoteIpAdressString(username);
    }

    public void notAcceptFileTransfer(String fileName, String from) {
        ni.notAcceptFileTransfer(fileName, from);
    }

    

}
