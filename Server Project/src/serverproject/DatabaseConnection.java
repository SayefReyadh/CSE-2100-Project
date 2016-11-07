/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverproject;

import java.io.ByteArrayInputStream;
import java.sql.*;
import javax.swing.JOptionPane;
import jdk.nashorn.internal.objects.NativeString;

/**
 *
 * @author Shaafi
 */
public class DatabaseConnection {

    private static Connection con = null;

    public static Connection dbConnect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            //con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_chat?user=rising_tide&password=messenger");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_chat?autoReconnect=true&useSSL=false", "root", "");

//            JOptionPane.showMessageDialog(null, "connected with database");
//            System.out.println("connected");
            return con;
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "not connected " + e.getMessage());

            return con = null;
        }
    }

    public static ResultSet find_user_by_username(String username) {
        try {

            username = NativeString.trim(username);

            PreparedStatement ps = con.prepareStatement("SELECT * FROM chat_users WHERE username=? LIMIT 1"); //need to check if the user is already logged in

            ps.setString(1, username);

//            Statement s = con.createStatement();
//
//            ResultSet rs = s.executeQuery("SELECT * FROM users WHERE username = '" + username + "'");
            ResultSet rs = ps.executeQuery();

            return rs;

        } catch (SQLException ex) {
            System.out.println("find user Error: " + ex);
            return null;
        }
    }

    public static void changeUserLoginState(String state, String userName) throws SQLException {
        int loginState;
        if (state.equalsIgnoreCase("logOut")) {
            loginState = 0;
        } else {
            loginState = 1;
        }
        PreparedStatement ps = con.prepareStatement("UPDATE chat_users SET is_logged_in = ? WHERE userName = ?");
        ps.setInt(1, loginState);
        ps.setString(2, userName);

        ps.executeUpdate();

    }

    public static int checkUser(String user, String pass) {

        //changed return value to make it more meaningful by shaafi (28-7-16)
        /*
        return 0 if username and password matches
        return 1 if username found bt password doesn't match
        return 2 if username already exists
        return 3 if user already logged in
        return -1 if username not found
        return -2 if query failed
         */
        try {
            ResultSet re = find_user_by_username(user);
            if (re.next()) {

                String foundPass = re.getString("password");
                int loginState = re.getInt("is_logged_in");
                if (pass.equals(foundPass) && loginState == 0) {
                    changeUserLoginState("login", user); //changing the user's login state when username and password matches
                    return 0;
                } else if (pass.equals(foundPass) && loginState == 1) {
                    return 3;
                } else {
                    return 1;
                }
            } else {
                return -1;
            }
        } catch (SQLException sQLException) {
            return -2;
        }
    }

    public static boolean insertUser(String name, String userName, String password) {
        try {
            name = NativeString.trim(name);
            userName = NativeString.trim(userName);
            password = NativeString.trim(password);

            //shaafi 27-7-16 start
            //checking if the username already exists or not
            ResultSet re = find_user_by_username(userName);
            if (re.next()) { //if username already exists,,show a msg and return false
                if (userName.equalsIgnoreCase(re.getString("username"))) {
                    return false;
                }
            }

            String statement = "INSERT INTO chat_users (name,userName, password) VALUES (?, ?, ?)";

            PreparedStatement ps = con.prepareCall(statement);
            ps.setString(1, name);
            ps.setString(2, userName);
            ps.setString(3, password);
            //ps.setString(4, gender);

            int temp = ps.executeUpdate();

            return temp != 0;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Insert error: " + e.getMessage());
            return false;
        }
    }
    
    public static boolean insertImageByUsername(String username, byte[] imageByteArray)
    {
        try {
            
            System.out.println("byte array is: " + imageByteArray.length);
            
            String statement = "UPDATE chat_users SET image = ? where userName = ?";
            
            PreparedStatement ps = con.prepareCall(statement);
            ps.setBinaryStream(1, new ByteArrayInputStream(imageByteArray), imageByteArray.length);
            ps.setString(2, username);
            
            int result = ps.executeUpdate();
            
            
            System.out.println("byte array is: done: " + result);
            
            return result != 0;
            
        } catch (Exception e) {
            
            System.err.println("Image insert Exception: " + e.getMessage());
            return false;
            
        }
    }

    public static byte[] searchImageBytesByUsername(String username) {
        try {
            ResultSet rs = find_user_by_username(username);
            if (rs.next()) {
                byte[] img = rs.getBytes("image");
                return img;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            System.err.println("Image Exception " + ex);
            return null;
        }
    }

    public static boolean checkIfChatHistoryExists(String sender, String receiver) {
        try {

            String statement = "SELECT * FROM chat_history WHERE username = ? AND user_chatting_with = ? LIMIT 1";

            PreparedStatement ps = con.prepareCall(statement);

            ps.setString(1, sender);
            ps.setString(2, receiver);

            ResultSet resultSet = ps.executeQuery();

            return resultSet.next();

        } catch (SQLException e) {

            //JOptionPane.showMessageDialog(null, "Error msg: " + e);
            return false;
        }
    }

    public static String insertChatHistory(String sender, String receiver, String msg) {

        try {
            if (checkIfChatHistoryExists(sender, receiver)) {

                String statement = "UPDATE chat_history SET chat_msg = ? "
                        + "WHERE username = ? and user_chatting_with = ? ";

                PreparedStatement ps = con.prepareCall(statement);

                ps.setString(1, msg);
                ps.setString(2, sender);
                ps.setString(3, receiver);

                int result = ps.executeUpdate();
                
                return (result != 0) ? "updated" : "not updated";
            }

            String statement = "INSERT INTO chat_history (username, user_chatting_with, chat_msg) "
                    + "VALUES ( ?, ?, ? )";
            PreparedStatement ps = con.prepareCall(statement);

            ps.setString(1, sender);
            ps.setString(2, receiver);
            ps.setString(3, msg);

            int result = ps.executeUpdate();

            return (result != 0) ? "inserted" : "not inserted";

        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, ex.getMessage());
            return "error";
        }
    }

    public static String getChatHistoryForAUser(String username, String userChattingWith) {
        try {
            String statement = "SELECT * FROM chat_history WHERE username = ? AND user_chatting_with = ? LIMIT 1";

            PreparedStatement ps = con.prepareCall(statement);
            ps.setString(1, username);
            ps.setString(2, userChattingWith);

            //JOptionPane.showMessageDialog(null, username + "+" + userChattingWith);

            ResultSet resultSet = ps.executeQuery();
            //JOptionPane.showMessageDialog(null, "done");
            
            String msgString = null;
            
            if (resultSet.next()) {
                msgString = resultSet.getString("chat_msg");
                
            }
            
            return msgString;
            
        } catch (SQLException e) {
            //JOptionPane.showMessageDialog(null, "Error msg: " + e);
            return null;
        }
    }
}
