/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designfiles;

import audio.AudioTone;
import clientproject.ClientInfo;
import clientproject.ClientProject;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;
import rising.tide.messenger.RisingTideMessenger;

/**
 *
 * @author Rumu
 */
public class SignUp extends javax.swing.JFrame {

    /**
     * Creates new form SignUp
     */
    public SignUp() {
        initComponents();
        RisingTideMessenger.defaultIconCreator(this, "SignUp");
        pass.setEchoChar((char) 0);
        confirmpass.setEchoChar((char) 0);
        
        fullname.setVisible(false);
        username.setVisible(false);
        password.setVisible(false);
        confirmpassword.setVisible(false);
    }

    public void SignUpProcess() {

        String name = fullnameText.getText().toLowerCase();
        String userName = usernameText.getText().toLowerCase();
        String password = new String(pass.getPassword());
        String confirmPassword = new String(confirmpass.getPassword());
        if (name.equals("FULL NAME") || userName.equals("USERNAME") || password.equals("PASSWORD") || confirmPassword.equals("CONFIRM PASSWORD")) {
            JOptionPane.showMessageDialog(null, "Please fill up all the informations");
            fullnameText.setText("FULL NAME");
            usernameText.setText("USERNAME");
            pass.setText("PASSWORD");
            confirmpass.setText("CONFIRM PASSWORD");
            pass.setEchoChar((char) 0);
            confirmpass.setEchoChar((char) 0);

        } else if (!password.equals(confirmPassword)) {
            pass.setText("PASSWORD");
            confirmpass.setText("CONFIRM PASSWORD");
            pass.setEchoChar((char) 0);
            confirmpass.setEchoChar((char) 0);
            JOptionPane.showMessageDialog(null, "please enter same password");
        } else {
            System.out.println("name: " + name);
            System.out.println("username: " + userName);
            System.out.println("pss: " + password);
            boolean check = ClientProject.signUpClient(name, userName, password);
            if (check) {

                NewUserPicUploadForm form = new NewUserPicUploadForm();
                form.setUsernameAndPasswordForLogin(userName, password);
                form.setVisible(true);
                this.dispose();

                /*
                OnlineChatUsersListWindow onlineChatUsersListWindow = new OnlineChatUsersListWindow();
                onlineChatUsersListWindow.setVisible(true);
                onlineChatUsersListWindow.setUserNameLabel(ClientInfo.clientInfoObject.getUserName());
                ClientInfo.loginInstance.dispose();
                AudioTone sn = new AudioTone();
                sn.loginSound();
                this.dispose();
                 */
            }
        }

        //System.out.println("hi");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        signupButton = new javax.swing.JLabel();
        confirmpass = new javax.swing.JPasswordField();
        pass = new javax.swing.JPasswordField();
        usernameText = new javax.swing.JTextField();
        fullnameText = new javax.swing.JTextField();
        minimize = new javax.swing.JLabel();
        closing = new javax.swing.JLabel();
        loginButton = new javax.swing.JLabel();
        fullname = new javax.swing.JLabel();
        username = new javax.swing.JLabel();
        password = new javax.swing.JLabel();
        confirmpassword = new javax.swing.JLabel();
        aboutus = new javax.swing.JLabel();
        frame = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(730, 615));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        signupButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/sign_logo.png"))); // NOI18N
        signupButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                signupButtonMouseClicked(evt);
            }
        });
        getContentPane().add(signupButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 470, -1, -1));

        confirmpass.setBackground(new java.awt.Color(204, 204, 204));
        confirmpass.setFont(new java.awt.Font("Ravie", 0, 24)); // NOI18N
        confirmpass.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        confirmpass.setText("CONFIRM PASSWORD");
        confirmpass.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                confirmpassFocusGained(evt);
            }
        });
        confirmpass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                confirmpassMouseClicked(evt);
            }
        });
        confirmpass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmpassActionPerformed(evt);
            }
        });
        confirmpass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                confirmpassKeyPressed(evt);
            }
        });
        getContentPane().add(confirmpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 410, 340, -1));

        pass.setBackground(new java.awt.Color(204, 204, 204));
        pass.setFont(new java.awt.Font("Ravie", 0, 24)); // NOI18N
        pass.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pass.setText("PASSWORD");
        pass.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                passFocusGained(evt);
            }
        });
        pass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                passMouseClicked(evt);
            }
        });
        pass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passActionPerformed(evt);
            }
        });
        pass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passKeyPressed(evt);
            }
        });
        getContentPane().add(pass, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 320, 340, -1));

        usernameText.setBackground(new java.awt.Color(204, 204, 204));
        usernameText.setFont(new java.awt.Font("Ravie", 0, 24)); // NOI18N
        usernameText.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        usernameText.setText("USERNAME");
        usernameText.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                usernameTextFocusGained(evt);
            }
        });
        usernameText.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                usernameTextMouseClicked(evt);
            }
        });
        usernameText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameTextActionPerformed(evt);
            }
        });
        usernameText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                usernameTextKeyPressed(evt);
            }
        });
        getContentPane().add(usernameText, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 230, 340, -1));

        fullnameText.setBackground(new java.awt.Color(204, 204, 204));
        fullnameText.setFont(new java.awt.Font("Ravie", 0, 24)); // NOI18N
        fullnameText.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        fullnameText.setText("FULL NAME");
        fullnameText.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                fullnameTextFocusGained(evt);
            }
        });
        fullnameText.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fullnameTextMouseClicked(evt);
            }
        });
        fullnameText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fullnameTextActionPerformed(evt);
            }
        });
        fullnameText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                fullnameTextKeyPressed(evt);
            }
        });
        getContentPane().add(fullnameText, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 140, 340, -1));

        minimize.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        minimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                minimizeMouseClicked(evt);
            }
        });
        getContentPane().add(minimize, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 10, 40, 50));

        closing.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        closing.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closingMouseClicked(evt);
            }
        });
        getContentPane().add(closing, new org.netbeans.lib.awtextra.AbsoluteConstraints(684, 10, 40, 50));

        loginButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        loginButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/login_logo.png"))); // NOI18N
        loginButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loginButtonMouseClicked(evt);
            }
        });
        getContentPane().add(loginButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 540, -1, -1));

        fullname.setFont(new java.awt.Font("Copperplate Gothic Bold", 1, 18)); // NOI18N
        fullname.setForeground(new java.awt.Color(204, 204, 204));
        fullname.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fullname.setText("FULL NAME");
        getContentPane().add(fullname, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 110, 340, -1));

        username.setFont(new java.awt.Font("Copperplate Gothic Bold", 1, 18)); // NOI18N
        username.setForeground(new java.awt.Color(204, 204, 204));
        username.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        username.setText("USERNAME");
        getContentPane().add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 200, 340, -1));

        password.setFont(new java.awt.Font("Copperplate Gothic Bold", 1, 18)); // NOI18N
        password.setForeground(new java.awt.Color(204, 204, 204));
        password.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        password.setText("PASSWORD");
        getContentPane().add(password, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 290, 340, -1));

        confirmpassword.setFont(new java.awt.Font("Copperplate Gothic Bold", 1, 18)); // NOI18N
        confirmpassword.setForeground(new java.awt.Color(204, 204, 204));
        confirmpassword.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        confirmpassword.setText("CONFIRM PASSWORD");
        getContentPane().add(confirmpassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 380, 340, -1));

        aboutus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        aboutus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logoABoutUs.png"))); // NOI18N
        aboutus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        aboutus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aboutusMouseClicked(evt);
            }
        });
        getContentPane().add(aboutus, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 540, -1, -1));

        frame.setFont(new java.awt.Font("Copperplate Gothic Bold", 1, 18)); // NOI18N
        frame.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/chat_bg.png"))); // NOI18N
        getContentPane().add(frame, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, 0, -1, -1));
        frame.getAccessibleContext().setAccessibleDescription("");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void fullnameTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fullnameTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fullnameTextActionPerformed

    private void usernameTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameTextActionPerformed

    private void confirmpassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmpassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_confirmpassActionPerformed

    private void passActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passActionPerformed

    private void confirmpassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_confirmpassKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            SignUpProcess();
        } else if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            JOptionPane.showMessageDialog(null, "There is Space in the password please type again!");
            pass.setText("PASSWORD");
            pass.setEchoChar((char) 0);
            confirmpass.setText("CONFIRM PASSWORD");
            confirmpass.setEchoChar((char) 0);
        }
    }//GEN-LAST:event_confirmpassKeyPressed

    private void signupButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_signupButtonMouseClicked
        // TODO add your handling code here:
        SignUpProcess();
    }//GEN-LAST:event_signupButtonMouseClicked

    private void usernameTextKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernameTextKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            JOptionPane.showMessageDialog(null, "There Should not be any space in username please type again!");
            usernameText.setText("USERNAME");
        }
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            if (new String(pass.getPassword()).equalsIgnoreCase("PASSWORD")) {
                pass.setEchoChar('*');
                pass.setText("");

            }

            if (fullnameText.getText().equalsIgnoreCase("")) {
                fullnameText.setText("FULL NAME");
            }
            if (usernameText.getText().equalsIgnoreCase("")) {
                usernameText.setText("USERNAME");
            }
            if (new String(confirmpass.getPassword()).equalsIgnoreCase("")) {
                confirmpass.setText("CONFIRM PASSWORD");
                confirmpass.setEchoChar((char) 0);
            }
        }
    }//GEN-LAST:event_usernameTextKeyPressed

    private void passKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            JOptionPane.showMessageDialog(null, "There is Space in the password please type again!");
            pass.setText("PASSWORD");
            pass.setEchoChar((char) 0);
            confirmpass.setText("CONFIRM PASSWORD");
            confirmpass.setEchoChar((char) 0);
        }
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            if (new String(confirmpass.getPassword()).equalsIgnoreCase("CONFIRM PASSWORD")) {
                confirmpass.setEchoChar('*');
                confirmpass.setText("");

            }

            if (fullnameText.getText().equalsIgnoreCase("")) {
                fullnameText.setText("FULL NAME");
            }
            if (usernameText.getText().equalsIgnoreCase("")) {
                usernameText.setText("USERNAME");
            }
            if (new String(pass.getPassword()).equalsIgnoreCase("")) {
                pass.setText("PASSWORD");
                pass.setEchoChar((char) 0);
            }

        }
    }//GEN-LAST:event_passKeyPressed

    private void fullnameTextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fullnameTextMouseClicked
        // TODO add your handling code here:
        if (fullnameText.getText().equalsIgnoreCase("FULL NAME")) {
            fullnameText.setText("");
            fullname.setVisible(true);
        }
        if (usernameText.getText().equalsIgnoreCase("")) {
            usernameText.setText("USERNAME");
            username.setVisible(false);
        }
        if (new String(pass.getPassword()).equalsIgnoreCase("") || new String(confirmpass.getPassword()).equalsIgnoreCase("")) {
            pass.setText("PASSWORD");
            pass.setEchoChar((char) 0);
            confirmpass.setText("CONFIRM PASSWORD");
            confirmpass.setEchoChar((char) 0);
            //fullname.setVisible(false);
            //username.setVisible(false);
            password.setVisible(false);
            confirmpassword.setVisible(false);
        }
    }//GEN-LAST:event_fullnameTextMouseClicked

    private void usernameTextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usernameTextMouseClicked
        // TODO add your handling code here:
        if (fullnameText.getText().equalsIgnoreCase("")) {
            fullnameText.setText("FULL NAME");
            fullname.setVisible(false);
        }
        if (usernameText.getText().equalsIgnoreCase("USERNAME")) {
            usernameText.setText("");
            username.setVisible(true);
        }
        if (new String(pass.getPassword()).equalsIgnoreCase("") || new String(confirmpass.getPassword()).equalsIgnoreCase("")) {
            pass.setText("PASSWORD");
            pass.setEchoChar((char) 0);
            confirmpass.setText("CONFIRM PASSWORD");
            confirmpass.setEchoChar((char) 0);
            
            password.setVisible(false);
            confirmpassword.setVisible(false);
        }
    }//GEN-LAST:event_usernameTextMouseClicked

    private void passMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_passMouseClicked
        // TODO add your handling code here:
        if (new String(pass.getPassword()).equalsIgnoreCase("PASSWORD")) {
            pass.setEchoChar('*');
            pass.setText("");
            password.setVisible(true);
        }

        if (fullnameText.getText().equalsIgnoreCase("")) {
            fullnameText.setText("FULL NAME");
            fullname.setVisible(false);
        }
        if (usernameText.getText().equalsIgnoreCase("")) {
            usernameText.setText("USERNAME");
            username.setVisible(false);
        }
        if (new String(confirmpass.getPassword()).equalsIgnoreCase("")) {
            confirmpass.setText("CONFIRM PASSWORD");
            confirmpass.setEchoChar((char) 0);
            confirmpassword.setVisible(false);
        }
    }//GEN-LAST:event_passMouseClicked

    private void confirmpassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmpassMouseClicked
        // TODO add your handling code here:
        if (new String(confirmpass.getPassword()).equalsIgnoreCase("CONFIRM PASSWORD")) {
            confirmpass.setEchoChar('*');
            confirmpass.setText("");
            confirmpassword.setVisible(true);
        }

        if (fullnameText.getText().equalsIgnoreCase("")) {
            fullnameText.setText("FULL NAME");
            fullname.setVisible(false);
        }
        if (usernameText.getText().equalsIgnoreCase("")) {
            usernameText.setText("USERNAME");
            username.setVisible(false);
        }
        if (new String(pass.getPassword()).equalsIgnoreCase("")) {
            pass.setText("PASSWORD");
            pass.setEchoChar((char) 0);
            password.setVisible(false);
        }
    }//GEN-LAST:event_confirmpassMouseClicked

    private void fullnameTextKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fullnameTextKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            if (fullnameText.getText().equalsIgnoreCase("")) {
                fullnameText.setText("FULL NAME");
            }
            if (usernameText.getText().equalsIgnoreCase("USERNAME")) {
                usernameText.setText("");
            }
            if (new String(pass.getPassword()).equalsIgnoreCase("") || new String(confirmpass.getPassword()).equalsIgnoreCase("")) {
                pass.setText("PASSWORD");
                pass.setEchoChar((char) 0);
                confirmpass.setText("CONFIRM PASSWORD");
                confirmpass.setEchoChar((char) 0);
            }
        }
    }//GEN-LAST:event_fullnameTextKeyPressed

    private void minimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizeMouseClicked
        this.setState(Frame.ICONIFIED);        // TODO add your handling code here:
    }//GEN-LAST:event_minimizeMouseClicked

    private void closingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closingMouseClicked
        close();        // TODO add your handling code here:
    }//GEN-LAST:event_closingMouseClicked

    private void loginButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginButtonMouseClicked
        // TODO add your handling code here:
        this.dispose();
        new Login().setVisible(true);
        
    }//GEN-LAST:event_loginButtonMouseClicked

    private void fullnameTextFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fullnameTextFocusGained
        // TODO add your handling code here:
        if (fullnameText.getText().equalsIgnoreCase("FULL NAME")) {
            fullnameText.setText("");
            fullname.setVisible(true);
        }
        if (usernameText.getText().equalsIgnoreCase("")) {
            usernameText.setText("USERNAME");
            username.setVisible(false);
        }
        if (new String(pass.getPassword()).equalsIgnoreCase("") || new String(confirmpass.getPassword()).equalsIgnoreCase("")) {
            pass.setText("PASSWORD");
            pass.setEchoChar((char) 0);
            confirmpass.setText("CONFIRM PASSWORD");
            confirmpass.setEchoChar((char) 0);
            //fullname.setVisible(false);
            //username.setVisible(false);
            password.setVisible(false);
            confirmpassword.setVisible(false);
        }
    }//GEN-LAST:event_fullnameTextFocusGained

    private void usernameTextFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usernameTextFocusGained
        // TODO add your handling code here:
        if (fullnameText.getText().equalsIgnoreCase("")) {
            fullnameText.setText("FULL NAME");
            fullname.setVisible(false);
        }
        if (usernameText.getText().equalsIgnoreCase("USERNAME")) {
            usernameText.setText("");
            username.setVisible(true);
        }
        if (new String(pass.getPassword()).equalsIgnoreCase("") || new String(confirmpass.getPassword()).equalsIgnoreCase("")) {
            pass.setText("PASSWORD");
            pass.setEchoChar((char) 0);
            confirmpass.setText("CONFIRM PASSWORD");
            confirmpass.setEchoChar((char) 0);
            
            password.setVisible(false);
            confirmpassword.setVisible(false);
        }
    }//GEN-LAST:event_usernameTextFocusGained

    private void passFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passFocusGained
        if (new String(pass.getPassword()).equalsIgnoreCase("PASSWORD")) {
            pass.setEchoChar('*');
            pass.setText("");
            password.setVisible(true);
        }

        if (fullnameText.getText().equalsIgnoreCase("")) {
            fullnameText.setText("FULL NAME");
            fullname.setVisible(false);
        }
        if (usernameText.getText().equalsIgnoreCase("")) {
            usernameText.setText("USERNAME");
            username.setVisible(false);
        }
        if (new String(confirmpass.getPassword()).equalsIgnoreCase("")) {
            confirmpass.setText("CONFIRM PASSWORD");
            confirmpass.setEchoChar((char) 0);
            confirmpassword.setVisible(false);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_passFocusGained

    private void confirmpassFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_confirmpassFocusGained
        // TODO add your handling code here:
        if (new String(confirmpass.getPassword()).equalsIgnoreCase("CONFIRM PASSWORD")) {
            confirmpass.setEchoChar('*');
            confirmpass.setText("");
            confirmpassword.setVisible(true);
        }

        if (fullnameText.getText().equalsIgnoreCase("")) {
            fullnameText.setText("FULL NAME");
            fullname.setVisible(false);
        }
        if (usernameText.getText().equalsIgnoreCase("")) {
            usernameText.setText("USERNAME");
            username.setVisible(false);
        }
        if (new String(pass.getPassword()).equalsIgnoreCase("")) {
            pass.setText("PASSWORD");
            pass.setEchoChar((char) 0);
            password.setVisible(false);
        }
    }//GEN-LAST:event_confirmpassFocusGained

    private void aboutusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aboutusMouseClicked
        // TODO add your handling code here:
        new AboutUs().setVisible(true);
    }//GEN-LAST:event_aboutusMouseClicked

    public void close() {

        WindowEvent winClosingEvent = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SignUp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel aboutus;
    private javax.swing.JLabel closing;
    private javax.swing.JPasswordField confirmpass;
    private javax.swing.JLabel confirmpassword;
    private javax.swing.JLabel frame;
    private javax.swing.JLabel fullname;
    private javax.swing.JTextField fullnameText;
    private javax.swing.JLabel loginButton;
    private javax.swing.JLabel minimize;
    private javax.swing.JPasswordField pass;
    private javax.swing.JLabel password;
    private javax.swing.JLabel signupButton;
    private javax.swing.JLabel username;
    private javax.swing.JTextField usernameText;
    // End of variables declaration//GEN-END:variables
}
