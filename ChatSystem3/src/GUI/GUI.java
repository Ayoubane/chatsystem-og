/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import chatsystem.ChatSystem;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.text.BadLocationException;

/**
 *
 * @author Ayoub, Omar
 */
public class GUI extends Thread {

    ChatSystem controller;
    private ChatGUI chatGui;
    private ConnectWindow connect;
    private String msg;
    private String username;

    /**
     * Creates a new GUI for the controller
     * @param controller 
     */
    public GUI(ChatSystem controller) {
        this.controller = controller;
    }

    /**
     * A Setter for the connect field
     * @param connect 
     */
    public void setConnect(ConnectWindow connect) {
        this.connect = connect;
    }
    
    /**
     * A Getter for the username field
     * @return 
     */
    public String getUser() {
        return this.username;
    }

    /**
     * A Getter for the msg field
     * @return 
     */
    public String getMsg() {
        return msg;
    }

    /**
     * Shows the message recieved in the ChatGUI
     * @param username
     * @param msg
     * @throws BadLocationException 
     */
    public void setMsg(String username, String msg) throws BadLocationException {
        //this.msg = msg;
        if (chatGui != null) {
            chatGui.appendjTextPane1(username + ": ",1);
            chatGui.appendjTextPane1(msg,0);
            chatGui.appendjTextPane1("\n",0);
        }
      // playSound();
    }

    /**
     * Sets the username to the given username an sends a Hello to other users
     * @param userName 
     */
    public void performConnect(String userName) {
        this.username = userName;
        controller.sendHello(userName);
    }

    /**
     * Disposes the actual chatGUI and sends goodbye to other users
     * @param userName 
     */
    public void performDisconnect(String userName) {
        controller.sndGoodbye(userName);
        chatGui.dispose();
        connect.setVisible(true);
    }

    /**
     * Sends a message to the remote User
     * @param msg 
     */
    public void performSend(String msg) {
        controller.sendMessage(msg);
    }

    /**
     * Sends a file proposal to the remote user
     * @param Name
     * @param size 
     */
    void performSendProposal(String Name, long size) {
        controller.sendProposal(Name, size);
    }

    /**
     * Sends an accept message to the remote user who sent a file request
     * @param fileName
     * @param from
     * @throws IOException 
     */
    public void acceptFileTransfer(String fileName,String from) throws IOException {
        controller.acceptFileTransfer(fileName,from);
    }

    @Override
    public void run() {
        new ConnectWindow(GUI.this).setVisible(true);
    }

    /**
     * A Getter for the chatGui field
     * @return 
     */
    public ChatGUI getChatGui() {
        return chatGui;
    }

    /**
     * A Setter for the chatGui Field
     * @param chatGui 
     */
    public void setChatGui(ChatGUI chatGui) {
        this.chatGui = chatGui;
    }

    /**
     * Adds a user to the list in the chatGui
     * @param username 
     */
    public void addUser(String username) {
        chatGui.addUser(username);
    }

    /**
     * Removes a user of the list in the chatGui
     * @param username 
     */
    public void rmvUser(String username) {
        chatGui.removeUser(username);
    }

    /**
     * Sets the remote IPAddress to which the messages/files will be sent
     * @param username
     * @throws UnknownHostException 
     */
    public void setRemoteIpAdress(String username) throws UnknownHostException {
        controller.setRemoteIpAdress(username);
    }

    /**
     * Shows the file proposal sent from a remote user
     * @param fileName
     * @param from 
     */
    public void showChoice(String fileName, String from) {
        chatGui.showProposal(fileName, from);
    }

    /**
     * Plays the sound when a message and/or a file proposal is received
     */
    public void playSound() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("fb.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("fb.wav"));
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

}
