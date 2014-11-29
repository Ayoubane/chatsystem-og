/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import chatsystem.ChatSystem;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author Ayoub
 */
public class GUI extends Thread {

    ChatSystem controller;
    private ChatGUI chatGui;
    private String msg;
    private String username;

    public GUI(ChatSystem controller) {
        this.controller = controller;
    }
    
    public String getUser(){
        return this.username;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String username, String msg) {
        //this.msg = msg;
        if (chatGui != null) {
            chatGui.getjTextArea1().append(username+": ");
            chatGui.getjTextArea1().append(msg);
            chatGui.getjTextArea1().append("\n");
        }
        playSound();
    }

    public void performConnect(String userName) {
        this.username=userName;
        controller.sendHello(userName);
    }
    
    public void performDisconnect(String userName){
        controller.sndGoodbye(userName);
        
    }

    public void performSend(String msg) {
        controller.sendMessage(msg);
    }

    public void run() {
        new ConnectWindow(GUI.this).setVisible(true);
    }

    public ChatGUI getChatGui() {
        return chatGui;
    }

    public void setChatGui(ChatGUI chatGui) {
        this.chatGui = chatGui;
    }
    
    public void addUser(String username){
        chatGui.addUser(username);
    }
    
    public void rmvUser(String username){
        chatGui.removeUser(username);
    }
    
    //---------------------------------------//
     public void playSound() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("fb.wav"));
            Clip clip = AudioSystem.getClip( );
            clip.open(audioInputStream);
            clip.start( );
        }
        catch(Exception e)  {
            e.printStackTrace( );
        }
    }
    //--------------------------------------//
    
}
