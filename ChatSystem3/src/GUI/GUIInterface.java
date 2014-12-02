/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author root
 */
public interface GUIInterface {
    
    public void performConnect();
    public void performDisconnect();
    public void disconnectOK();
    public void updateUserList();
    public void performSend();
    public void performSendProposal();      //WARNING NOT IN THE SRS
    public void performSendFile();          //WARNING NOT IN THE SRS
    public void displayMsg();
    
}
