/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author Ayoub
 */
public class AGroup extends javax.swing.JPanel {

    ChatGUI cgui;
    DefaultListModel modelOriginal = new DefaultListModel();
    DefaultListModel modelSelected = new DefaultListModel();
    private final ListSelectionListener listSelectionListener;
    boolean isChanging=false;

    /**
     * Creates new form AGroup
     */
    public AGroup(ChatGUI cgui) {
        this.cgui=cgui;
        initComponents();
        
        listSelectionListener = new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                if(!isChanging && !listSelectionEvent.getValueIsAdjusting() && !modelSelected.contains(jList1.getSelectedValue().toString())){
                    modelSelected.addElement(jList1.getSelectedValue().toString());
                    isChanging = true;
                    jList1.clearSelection();
                    isChanging = false;
                }else if(!isChanging && !listSelectionEvent.getValueIsAdjusting() && modelSelected.contains(jList1.getSelectedValue().toString())){
                    System.out.println("Removing : "+jList1.getSelectedValue().toString());
                    modelSelected.removeElement(jList1.getSelectedValue().toString());
                    isChanging = true;
                    jList1.clearSelection();
                    isChanging = false;
                }
            }
        };
       jList1.addListSelectionListener(listSelectionListener);
       for (int i=0; i<cgui.model.getSize(); i++) {
            modelOriginal.addElement(cgui.model.elementAt(i));
        }
       
       //Changing Language
       if(cgui.gui.controller.Language=="Francais"){
           jButton1.setText("Envoyer");
           jButton2.setText("Choisir fichier");
           jLabel1.setText("Utilisateurs dans ce groupe :");
       }
       
    }

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

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();

        jList1.setModel(modelOriginal);
        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jList1);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 153));
        jLabel1.setText("Users in this group :");

        jList2.setModel(modelSelected);
        jList2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(jList2);

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

        jTextArea3.setRows(5);
        jTextArea3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextArea3KeyPressed(evt);
            }
        });
        jScrollPane5.setViewportView(jTextArea3);

        jScrollPane4.setViewportView(jTextPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)))
                    .addComponent(jScrollPane4))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane5)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(0, 11, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(this); //Where frame is the parent component

        File file = null;
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();
            for(int i=0; i<modelSelected.getSize(); i++){
            try {
                cgui.gui.setRemoteIpAdress((String)modelSelected.getElementAt(i));
            } catch (UnknownHostException ex) {
                Logger.getLogger(AGroup.class.getName()).log(Level.SEVERE, null, ex);
            }
            cgui.gui.performSendProposal(file.getName(), file.getTotalSpace());
            if(this.cgui.gui.controller.Language=="English"){
                JOptionPane.showMessageDialog(this, "Your file has been sent to all selected users successfully!");
            }else if(this.cgui.gui.controller.Language=="Francais"){
                JOptionPane.showMessageDialog(this, "Ton fichier a été bien envoyé aux utilisateurs séléctionnés!");
            }
            
        }
            
        } else {
            //User did not choose a valid file
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            if(this.cgui.gui.controller.Language=="English"){
                appendjTextPane1("Me : ",2); // Or cgui.gui.getUser() ;)
            }else if(this.cgui.gui.controller.Language=="Francais"){
                appendjTextPane1("Moi : ",2); // Or cgui.gui.getUser() ;)
            }
            
            appendjTextPane1(jTextArea3.getText(),0);
            appendjTextPane1("\n",0);
        } catch (BadLocationException ex) {
            Logger.getLogger(AGroup.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(int i=0; i<modelSelected.getSize(); i++){
            try {
                cgui.gui.setRemoteIpAdress((String)modelSelected.getElementAt(i));
            } catch (UnknownHostException ex) {
                Logger.getLogger(AGroup.class.getName()).log(Level.SEVERE, null, ex);
            }
            cgui.gui.performSend(jTextArea3.getText());
        }
        jTextArea3.setText(null);
        if(this.cgui.gui.controller.Language=="English"){
            JOptionPane.showMessageDialog(this, "Your message has been sent to all selected users successfully!");
        }else if(this.cgui.gui.controller.Language=="Francais"){
            JOptionPane.showMessageDialog(this, "Ton message a été bien envoyé aux utilisateurs séléctionnés!");
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextArea3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextArea3KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                if(this.cgui.gui.controller.Language=="English"){
                    appendjTextPane1("Me : ",2);// Or gui.getUser() ;)
                } else if(this.cgui.gui.controller.Language=="Francais"){
                    appendjTextPane1("Moi : ",2);// Or gui.getUser() ;)
                }
                appendjTextPane1(jTextArea3.getText(),0);
                appendjTextPane1("\n",0);
            }catch (BadLocationException ex) {
                    Logger.getLogger(AGroup.class.getName()).log(Level.SEVERE, null, ex);
            }
            cgui.gui.performSend(jTextArea3.getText());
            jTextArea3.setText(null);
        }
    }//GEN-LAST:event_jTextArea3KeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList jList1;
    private javax.swing.JList jList2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextPane jTextPane1;
    // End of variables declaration//GEN-END:variables
}
