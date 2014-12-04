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
import javax.swing.JOptionPane;

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

    public String getUser() {
        return this.username;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String username, String msg) {
        //this.msg = msg;
        if (chatGui != null) {
            chatGui.getjTextArea1().append(username + ": ");
            chatGui.getjTextArea1().append(msg);
            chatGui.getjTextArea1().append("\n");
        }
       // playSound();
    }

    public void performConnect(String userName) {
        this.username = userName;
        controller.sendHello(userName);
    }

    public void performDisconnect(String userName) {
        controller.sndGoodbye(userName);

    }

    public void performSend(String msg) {
        controller.sendMessage(msg);
    }

    void performSendProposal(String Name, long size) {
        controller.sendProposal(Name, size);
    }

    public void acceptFileTransfer(String fileName,String from) throws IOException {
        controller.acceptFileTransfer(fileName,from);
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

    public void addUser(String username) {
        chatGui.addUser(username);
    }

    public void rmvUser(String username) {
        chatGui.removeUser(username);
    }

    public void setRemoteIpAdress(String username) throws UnknownHostException {
        controller.setRemoteIpAdress(username);
    }

    public void showChoice(String fileName, String from) {
        chatGui.showProposal(fileName, from);
    }

    //---------------------------------------//
    public void playSound() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("fb.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("fb1.wav"));
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
    //--------------------------------------//

}
