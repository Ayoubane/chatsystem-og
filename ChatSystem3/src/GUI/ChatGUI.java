/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 * The Chat GUI Class
 * @author Ayoub, Omar
 */



public class ChatGUI extends javax.swing.JFrame {

  
    GUI gui;
    DefaultListModel model = new DefaultListModel();

    /**
     * Creates a new ChatGUI form
     * @param gui 
     */
    public ChatGUI(final GUI gui) {
        this.gui = gui;
        initComponents();
        //JTextArea automatically updates to scroll down ;)
        DefaultCaret caret = (DefaultCaret)jTextPane1.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        

        listSelectionListener = new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                try {
                    gui.setRemoteIpAdress(jList1.getSelectedValue().toString());
                    String username = jList1.getSelectedValue().toString();
                    String[] ipadress = username.split("@");
                    jLabel2.setText(ipadress[1]);
                } catch (UnknownHostException ex) {
                    Logger.getLogger(ChatGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        jList1.addListSelectionListener(listSelectionListener);
       
       
    }

    /**
     * Changes language to French
     */
    public void changeLanguageFR(){
        jButton1.setText("Envoyer");
        jButton2.setText("Choisir fichier");
        jButton3.setText("Créations de groupes");
        jButton4.setText("Se déconnecter");
    }
    
    /**
     * Edits the private component JLabel1
     * @param s The text to put in the JLabel1
     */
    public void editJlabel1(String s) {
        jLabel1.setText(s);
    }

    /**
     * Shows the file proposal sent from an another user
     * @param fileName The file name
     * @param from Sender Username
     */
    public void showProposal(String fileName, String from) {
        

        int n=0; //MAYBE PROBLEM!!!
        if(this.gui.controller.Language=="English"){
            n= JOptionPane.showConfirmDialog(this,"Would you like to get this file : "+fileName+" From"+from+" ?","File Proposal",JOptionPane.YES_NO_OPTION);
        }else if(this.gui.controller.Language=="Francais"){
            n= JOptionPane.showConfirmDialog(this,"Voudrez vous telecharger le fichier : "+fileName+" envoyé par "+from+" ?","Proposition De Telechargement",JOptionPane.YES_NO_OPTION);
        }
        

        //System.out.println("n = "+n);
        if (n == 0) {
            try {
                gui.acceptFileTransfer(fileName, from);
            } catch (IOException ex) {
                Logger.getLogger(ChatGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
           gui.notAcceptFileTransfer(fileName, from);
        }

    }

    /**
     * Puts colored text in the JTextPane1
     * @param str String to put in JTextPane
     * @param color Color chosen for the text
     * @throws BadLocationException 
     */
    public void appendjTextPane1(String str, int color) throws BadLocationException
    {
        if(color==1){
           //SENDER USERNAME
           StyledDocument document = (StyledDocument) jTextPane1.getDocument();
           Style style = jTextPane1.addStyle("I'm a Style", null);
           StyleConstants.setForeground(style, Color.ORANGE);
           style.addAttribute(StyleConstants.CharacterConstants.Bold, Boolean.TRUE);
           document.insertString(document.getLength(), str, style);    
        }
        else if(color==0){
           //MESSAGES
           StyledDocument document = (StyledDocument) jTextPane1.getDocument();
           Style style = jTextPane1.addStyle("I'm a Style", null);
           StyleConstants.setForeground(style, Color.black);
           document.insertString(document.getLength(), str, style); 
        }
        else if(color==2){
            //RECEIVER USERNAME
           StyledDocument document = (StyledDocument) jTextPane1.getDocument();
           Style style = jTextPane1.addStyle("I'm a Style", null);
           StyleConstants.setForeground(style, Color.black);
           style.addAttribute(StyleConstants.CharacterConstants.Bold, Boolean.TRUE);
           document.insertString(document.getLength(), str, style); 
        }
     }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("OA ChatSystem v2.0");
        setBackground(new java.awt.Color(102, 102, 102));
        setForeground(new java.awt.Color(102, 102, 102));

        jPanel1.setForeground(new java.awt.Color(102, 102, 102));
        jPanel1.setToolTipText("");
        jPanel1.setName(""); // NOI18N

        jList1.setModel(model);
        jScrollPane2.setViewportView(jList1);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 153, 153));
        jButton1.setText("Send");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 153, 153));
        jButton2.setText("Select File");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 153, 153));
        jButton3.setText("Create Group");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTextArea2.setColumns(20);
        jTextArea2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextArea2KeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(jTextArea2);

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton4.setForeground(new java.awt.Color(0, 153, 153));
        jButton4.setText("Disconnect");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 153));
        jLabel1.setText("Username");

        jLabel2.setForeground(new java.awt.Color(254, 18, 18));
        jLabel2.setText("Remote IP");
        jLabel2.setBorder(new javax.swing.border.MatteBorder(null));

        jScrollPane4.setViewportView(jTextPane1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(115, 115, 115)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jScrollPane4))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                    .addComponent(jScrollPane4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(7, 7, 7)
                        .addComponent(jButton3))))
        );

        jLabel2.getAccessibleContext().setAccessibleName("Destination");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(661, 458));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    /**
     * Method called when Send Button is pressed
     * @param evt 
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            if(this.gui.controller.Language=="English"){
                appendjTextPane1("Me : ",2);// Or gui.getUser() ;)
            }else if(this.gui.controller.Language=="Francais"){
                appendjTextPane1("Moi : ",2);// Or gui.getUser() ;)
            }
            appendjTextPane1(jTextArea2.getText(),0);
            appendjTextPane1("\n",0);
        } catch (BadLocationException ex) {
            Logger.getLogger(ChatGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        gui.performSend(jTextArea2.getText());
        jTextArea2.setText(null);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * Method called when Select File method is pressed
     * @param evt 
     */
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        final JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(this); //Where frame is the parent component

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = null;
            file = fc.getSelectedFile();
            gui.performSendProposal(file.getAbsolutePath(), file.getTotalSpace());

        } else {
            //User did not choose a valid file
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * Method called when Create a group is pressed
     * @param evt 
     */
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        GroupeChoice choice = new GroupeChoice(this, rootPaneCheckingEnabled);
        choice.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * Method called when RETURN key is pressed
     * @param evt 
     */
    private void jTextArea2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextArea2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                if(this.gui.controller.Language=="English"){
                    appendjTextPane1("Me : ",2);// Or gui.getUser() ;)
                }else if(this.gui.controller.Language=="Francais"){
                    appendjTextPane1("Moi : ",2);// Or gui.getUser() ;)
                }
                appendjTextPane1(jTextArea2.getText(),0);
                appendjTextPane1("\n",0);
            } catch (BadLocationException ex) {
                Logger.getLogger(ChatGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            gui.performSend(jTextArea2.getText());
            jTextArea2.setText(null);
        }
    }//GEN-LAST:event_jTextArea2KeyPressed

    /**
     * Method called when Disconnect button is pressed
     * @param evt 
     */
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            if(this.gui.controller.Language=="English"){
                appendjTextPane1("Me : ",2);// Or gui.getUser() ;)
            }else if(this.gui.controller.Language=="Francais"){
                appendjTextPane1("Moi : ",2);// Or gui.getUser() ;)
            }
            appendjTextPane1("GoodBye, I'm "+gui.getUser(),0);
            appendjTextPane1("\n",0);
        } catch (BadLocationException ex) {
            Logger.getLogger(ChatGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        gui.performDisconnect(gui.getUser());
        if(this.gui.controller.Language=="English"){
                JOptionPane.showMessageDialog(this, "You have been successfully disconnected.");
        }else if(this.gui.controller.Language=="Francais"){
            JOptionPane.showMessageDialog(this, "vous avez bien été déconnecté.");
        }
        
    }//GEN-LAST:event_jButton4ActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextPane jTextPane1;
    // End of variables declaration//GEN-END:variables
  ListSelectionListener listSelectionListener;

    public JTextPane getjTextPane1() {
        return jTextPane1;
    }

    public void setjTextPane1(JTextPane jTextPane1) {
        this.jTextPane1 = jTextPane1;
    }
  

    public JTextArea getjTextArea2() {
        return jTextArea2;
    }

    public void setjTextArea2(JTextArea jTextArea2) {
        this.jTextArea2 = jTextArea2;
    }

    public void addUser(String username) {
        if (!model.contains(username)) {
            model.addElement(username);
        }
    }

    public void removeUser(String username) {
        if (model.contains(username)) {
            model.removeElement(username);
        }
    }
 
}
