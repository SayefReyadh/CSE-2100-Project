package designfiles;

import clientproject.ClientInfo;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import rising.tide.messenger.RisingTideMessenger;

public class ChatWindow extends javax.swing.JFrame {

    int xMouse, yMouse;

    private StringBuffer msg = new StringBuffer();
    public String userName;

    public ChatWindow(String userName) {
        initComponents();
        ChatWindow.this.userName = userName;
        RisingTideMessenger.defaultIconCreator(ChatWindow.this, userName);
        messageBoxPane.setCaretPosition(messageBoxPane.getDocument().getLength());
    }

    public ChatWindow() {
        initComponents();
        //RisingTideMessenger.defaultIconCreator(this, userName);
    }

    public void apendMessageBox(String message) {
        msg.append(message);
        msg.append("\n");
        messageBox.setText(msg.toString());
    }

    public void setUserNameLabel(String userName) {
        userNameLabel.setText(userName);
    }

    private void sendMessage() {
        if (messageTextBox.getText().compareTo("") != 0) {
            ClientInfo.clientInfoObject.getClientWriterInstance().writeToClient(userNameLabel.getText(), messageTextBox.getText());
            msg.append("You ::: " + messageTextBox.getText());
            msg.append("\n");
            appendStringJTextPane("You ::: ", messageTextBox.getText());
            messageBoxPane.setCaretPosition(messageBoxPane.getDocument().getLength());
            messageBox.setText(msg.toString());
            messageTextBox.setText(null);
        }
    }

    public void appendStringJTextPane(String user, String msg) {
        try {
            if (msg.startsWith(":")) {
                displayEmoFromMsg(msg, user, 1);
            } else if (msg.startsWith("jLabel")) {
                StyledDocument document = (StyledDocument) messageBoxPane.getDocument();
                document.insertString(document.getLength(), user, null);
                System.out.println("INSERTED USERNAME");

                Style style = document.addStyle("icon", null);
                StyleConstants.setIcon(style, new ImageIcon("src/emoji/" + msg + ".png"));

                document.insertString(document.getLength(), "ignored text", style);

                System.out.println("INSERTED EMOJI");
                document = (StyledDocument) messageBoxPane.getDocument();
                document.insertString(document.getLength(), "\n", null);
            } else {
                StyledDocument document = (StyledDocument) messageBoxPane.getDocument();
                String[] splitMsgs = findEmo(msg);
                if (splitMsgs[0].equalsIgnoreCase("found")) {
                    document.insertString(document.getLength(), user + splitMsgs[1], null);
                    displayEmoFromMsg(splitMsgs[2], user, 2); //2 for in line emo
                } else {

                    document.insertString(document.getLength(), user + splitMsgs[1] + "\n", null);
                }
            }
            
            
            messageBoxPane.setCaretPosition(messageBoxPane.getDocument().getLength());

        } catch (Exception ex) {
            System.out.println("ex: " + ex.getMessage());
        }
    }

    //get emo label name from msg
    public String getEmoName(String msg) {
        switch (msg.toLowerCase()) {
            case ":)":
                return "jLabel7";
            case ":p":
                return "jLabel3";
            case ":o":
                return "jLabel4";

        }
        return msg;
    }

    public void displayEmoFromMsg(String msg, String user, int mode) throws BadLocationException {
        if (mode == 1) {
            StyledDocument document = (StyledDocument) messageBoxPane.getDocument();
            document.insertString(document.getLength(), user, null);
            System.out.println("INSERTED USERNAME");

            Style style = document.addStyle("icon", null);
            StyleConstants.setIcon(style, new ImageIcon("src/emoji/" + getEmoName(msg) + ".png"));

            document.insertString(document.getLength(), "ignored text", style);

            System.out.println("INSERTED EMOJI");
            document = (StyledDocument) messageBoxPane.getDocument();
            document.insertString(document.getLength(), "\n", null);

        } else if (mode == 2) {
            StyledDocument document = (StyledDocument) messageBoxPane.getDocument();
            Style style = document.addStyle("icon", null);
            StyleConstants.setIcon(style, new ImageIcon("src/emoji/" + getEmoName(msg) + ".png"));

            document.insertString(document.getLength(), "ignored text", style);

            System.out.println("INSERTED EMOJI");
            document = (StyledDocument) messageBoxPane.getDocument();
            document.insertString(document.getLength(), "\n", null);
        }
    }

    //find the emo from msg
    public String[] findEmo(String msg) {
        String[] msgs = new String[10];
        int index = msg.lastIndexOf(":");
        if (index >= 0) {
            System.out.println("index is: " + index);
            String s = msg.substring(0, index);
            s += " ";
            System.out.println("msg is: " + s);

            String gotEmo = msg.substring(index);
            
            String searchEmo = getEmoName(gotEmo);

            if (searchEmo.equalsIgnoreCase(gotEmo)) {
                msgs[0] = "not found";
                msgs[1] = msg;
                System.out.println("msges are: " + msgs[0] + " " + msgs[1]);
            } else {
                msgs[0] = "found";
                msgs[1] = s;
                msgs[2] = msg.substring(index);

                System.out.println("msges are: " + msgs[0] + " " + msgs[1] + " " + msgs[2]);
            }

        } else {
            msgs[0] = "not found";
            msgs[1] = msg;
            System.out.println("msges are: " + msgs[0] + " " + msgs[1]);
        }

        return msgs;
    }

    public JTextPane getMessageBoxPane() {
        return messageBoxPane;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        messageTextBox = new javax.swing.JTextField();
        userNameLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        messageBoxPane = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        messageBox = new javax.swing.JTextArea();
        send = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        iconLabel = new javax.swing.JLabel();
        crossLabel = new javax.swing.JLabel();
        toolbarLabel = new javax.swing.JLabel();
        mainBgLabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(153, 153, 153));
        setForeground(java.awt.Color.darkGray);
        setLocation(new java.awt.Point(500, 200));
        setMaximumSize(new java.awt.Dimension(400, 500));
        setMinimumSize(new java.awt.Dimension(400, 500));
        setUndecorated(true);
        setResizable(false);
        setSize(new java.awt.Dimension(400, 500));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        messageTextBox.setBackground(new java.awt.Color(153, 153, 153));
        messageTextBox.setFont(new java.awt.Font("Ebrima", 0, 14)); // NOI18N
        messageTextBox.setForeground(new java.awt.Color(0, 0, 51));
        messageTextBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                messageTextBoxActionPerformed(evt);
            }
        });
        messageTextBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                messageTextBoxKeyPressed(evt);
            }
        });
        getContentPane().add(messageTextBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 250, 70));

        userNameLabel.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        userNameLabel.setForeground(new java.awt.Color(0, 204, 204));
        userNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        userNameLabel.setText("PuduMoniAI");
        getContentPane().add(userNameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 140, 30));

        jScrollPane1.setBackground(new java.awt.Color(153, 153, 153));
        jScrollPane1.setForeground(new java.awt.Color(60, 63, 65));
        jScrollPane1.setAutoscrolls(true);

        messageBoxPane.setEditable(false);
        messageBoxPane.setBackground(new java.awt.Color(153, 153, 153));
        messageBoxPane.setBorder(null);
        messageBoxPane.setFont(new java.awt.Font("Tekton Pro", 0, 18)); // NOI18N
        messageBoxPane.setForeground(new java.awt.Color(0, 0, 51));
        messageBoxPane.setCaretPosition(messageBoxPane.getDocument().getLength());
        jScrollPane1.setViewportView(messageBoxPane);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 370, 230));

        jScrollPane2.setAutoscrolls(true);

        messageBox.setEditable(false);
        messageBox.setColumns(20);
        messageBox.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        messageBox.setLineWrap(true);
        messageBox.setRows(5);
        jScrollPane2.setViewportView(messageBox);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(273, 260, 0, 0));

        send.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/send_logo.png"))); // NOI18N
        send.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sendMouseClicked(evt);
            }
        });
        getContentPane().add(send, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 420, 90, 50));

        jLabel2.setBackground(new java.awt.Color(102, 102, 255));
        jLabel2.setForeground(new java.awt.Color(102, 102, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emoji/jLabel2.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emoji/jLabel3.png"))); // NOI18N
        jLabel3.setText("jLabel3");
        jLabel3.setPreferredSize(new java.awt.Dimension(27, 27));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 370, -1, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emoji/jLabel5.png"))); // NOI18N
        jLabel5.setText("jLabel5");
        jLabel5.setPreferredSize(new java.awt.Dimension(27, 27));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 370, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emoji/jLabel4.png"))); // NOI18N
        jLabel4.setText("jLabel4");
        jLabel4.setPreferredSize(new java.awt.Dimension(27, 27));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 370, -1, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emoji/jLabel6.png"))); // NOI18N
        jLabel6.setText("jLabel6");
        jLabel6.setPreferredSize(new java.awt.Dimension(27, 27));
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 370, -1, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emoji/jLabel7.png"))); // NOI18N
        jLabel7.setText("jLabel7");
        jLabel7.setPreferredSize(new java.awt.Dimension(27, 27));
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 370, -1, -1));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emoji/jLabel8.png"))); // NOI18N
        jLabel8.setText("jLabel8");
        jLabel8.setPreferredSize(new java.awt.Dimension(27, 27));
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 370, -1, -1));

        iconLabel.setBackground(new java.awt.Color(0, 0, 0));
        iconLabel.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        iconLabel.setToolTipText("Minimize");
        iconLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        iconLabel.setFocusable(false);
        iconLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconLabelMouseClicked(evt);
            }
        });
        getContentPane().add(iconLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 12, 25, 28));

        crossLabel.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        crossLabel.setToolTipText("Hide");
        crossLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        crossLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                crossLabelMouseClicked(evt);
            }
        });
        getContentPane().add(crossLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(378, 12, 23, 28));

        toolbarLabel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                toolbarLabelMouseDragged(evt);
            }
        });
        toolbarLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                toolbarLabelMousePressed(evt);
            }
        });
        getContentPane().add(toolbarLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 403, 80));

        mainBgLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/chat_window.png"))); // NOI18N
        getContentPane().add(mainBgLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void messageTextBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_messageTextBoxKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            sendMessage();
        }
    }//GEN-LAST:event_messageTextBoxKeyPressed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        messageTextBox.setText("jLabel3");
        sendMessage();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        messageTextBox.setText("jLabel2");
        sendMessage();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        messageTextBox.setText("jLabel4");
        sendMessage();
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        messageTextBox.setText("jLabel5");
        sendMessage();
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        // TODO add your handling code here:
        messageTextBox.setText("jLabel6");
        sendMessage();
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here:
        messageTextBox.setText("jLabel7");
        sendMessage();
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        // TODO add your handling code here:
        messageTextBox.setText("jLabel8");
        sendMessage();
    }//GEN-LAST:event_jLabel8MouseClicked

    private void messageTextBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_messageTextBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_messageTextBoxActionPerformed

    private void iconLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconLabelMouseClicked
        this.setState(Frame.ICONIFIED);        // TODO add your handling code here:
    }//GEN-LAST:event_iconLabelMouseClicked

    private void crossLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_crossLabelMouseClicked
        close();
    }//GEN-LAST:event_crossLabelMouseClicked

    private void toolbarLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_toolbarLabelMousePressed
        // TODO add your handling code here:
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_toolbarLabelMousePressed

    private void toolbarLabelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_toolbarLabelMouseDragged
        // TODO add your handling code here:
        this.setLocation(evt.getXOnScreen() - xMouse, evt.getYOnScreen() - yMouse);
    }//GEN-LAST:event_toolbarLabelMouseDragged

    private void sendMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sendMouseClicked
        // TODO add your handling code here:
        sendMessage();
    }//GEN-LAST:event_sendMouseClicked

    public void close() {

        WindowEvent winClosingEvent = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChatWindow().setVisible(true);
            }
        });
    }

    public JTextArea getMessageBox() {
        return messageBox;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel crossLabel;
    private javax.swing.JLabel iconLabel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel mainBgLabel;
    private javax.swing.JTextArea messageBox;
    private javax.swing.JTextPane messageBoxPane;
    private javax.swing.JTextField messageTextBox;
    private javax.swing.JLabel send;
    private javax.swing.JLabel toolbarLabel;
    private javax.swing.JLabel userNameLabel;
    // End of variables declaration//GEN-END:variables
}
