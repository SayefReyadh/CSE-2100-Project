package serverproject;

import java.awt.HeadlessException;
import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.util.*;
import javax.swing.JOptionPane;

public class ServerProject {

    public static HashMap<String, ClientHandler> clientHM = new HashMap<>();
    public static HashMap<String, byte[]> clientImage = new HashMap<>();
    public static boolean dummyBooleanValueForLoginOrSignUpCheckToDrawTestProfilePhoto;
    public static ServerLog log;

    public static void main(String[] args) throws Exception {

        Connection con = DatabaseConnection.dbConnect();
        if (con == null) {
            JOptionPane.showMessageDialog(null, "Sorry cannot connect to database");
            System.exit(0);
        }
        int userFound;
        String name, userName, password;
        String givenPassword;

        //socket variables
        BufferedReader clientInput;
        DataOutputStream writeToClient;
        ObjectOutputStream writeImageToClient;

        String[] clientMsg;

        try {
            int PORT = 6789;

            ServerSocket server = new ServerSocket(PORT);
            //System.out.println("Server activated...");
            ServerLog log2 = new ServerLog();
            log2.setVisible(true);
            log2.repaint();
            log = log2;
            
            while (true) {

                Socket socketForClient = server.accept();
                
                clientInput = new BufferedReader(new InputStreamReader(socketForClient.getInputStream()));
                writeToClient = new DataOutputStream(socketForClient.getOutputStream());

                clientMsg = (clientInput.readLine()).split(" "); //getting username password from client  separated by space
                System.out.println("clientmsg[0]: " + clientMsg[0]);
                //checking if the request is for login
                if (clientMsg[0].equalsIgnoreCase("signUpImage")) {
                    ObjectInputStream readFromServer = new ObjectInputStream(socketForClient.getInputStream());

                    byte[] buffer = (byte[]) readFromServer.readObject();

                    userName = clientMsg[1];

                    if (DatabaseConnection.insertImageByUsername(userName, buffer)) {
                        userFound = 5;
                    } else {
                        userFound = -3;
                    }
                } else if (clientMsg[0].equalsIgnoreCase("login")) {

                    userName = clientMsg[1];
                    givenPassword = clientMsg[2];
                    userFound = DatabaseConnection.checkUser(userName, givenPassword);

                } //the request is for signup so do singup process
                else {
                    name = clientMsg[1];
                    userName = clientMsg[2];
                    password = clientMsg[3];
                    //inserting the user into the database,, in a word singing him up

                    if (DatabaseConnection.insertUser(name, userName, password)) {

                        //when user is inserted process with login and do as before
                        //userFound = DatabaseConnection.checkUser(userName, password);
                        userFound = 4;
                    } else {
                        userFound = 2;
                    }
                }

                System.out.println("userFound: " + userFound);
                //return 0 if username and password matches
                //return 1 if username found bt password doesn't match
                //return 2 if username already exists
                //return 3 if user already logged in
                //return 4 if user information inserted into database
                //return -1 if username not found
                //return -2 if query failed
                //return -3 if image upload failed

                /*
                    changed to switch formate from if else for simplysity and 
                    changed return value to make it more meaningful by shaafi (28-7-16)
                 */
                switch (userFound) {
                    case 0:
                        writeToClient.writeBytes("OK:*LoginSuccessful*" + "\n");

                        ClientHandler clientInstance = new ClientHandler(socketForClient);
                        clientHM.put(userName, clientInstance); // online list
                        Thread t = new Thread(clientInstance);
                        t.start();

                        log.appendLogText(userName, "HAS LOGGED IN");

                        writeImageToClient = new ObjectOutputStream(socketForClient.getOutputStream());
                        byte[] img = DatabaseConnection.searchImageBytesByUsername(userName);
                        if (img != null) {
                            writeImageToClient.writeObject(img);
                        } else {
                            img = DatabaseConnection.searchImageBytesByUsername("DEFAULT");
                            writeImageToClient.writeObject(img);
                        }
                        clientImage.put(userName, img);

                        break;
                    case 1:
                        writeToClient.writeBytes("WRONG:*PasswordWrong*" + "\n");
                        socketForClient.close();
                        break;
                    case 2:
                        writeToClient.writeBytes("WRONG:*usernameExists*" + "\n");
                        socketForClient.close();
                        break;
                    case 3:
                        writeToClient.writeBytes("WRONG:*alreadyLoggedIn*" + "\n");
                        socketForClient.close();
                        break;
                    case 4:
                        writeToClient.writeBytes("OK:*signupsuccessful*" + "\n");
                        socketForClient.close();
                        break;
                    case 5:
                        writeToClient.writeBytes("OK:*imageUploaded*" + "\n");
                        socketForClient.close();
                        break;
                    case -1:
                        writeToClient.writeBytes("WRONG:*UsernameWrong*" + "\n");
                        socketForClient.close();
                        break;
                    case -2:
                        JOptionPane.showMessageDialog(null, "Query failed");
                        break;
                    case -3:
                        writeToClient.writeBytes("WRONG:*imageUploadFailed*" + "\n");
                        socketForClient.close();
                        break;
                }
            }
        } catch (IOException | HeadlessException e) {
            //System.err.println("ErrorFoundHereInServerProject:" + e);
        }
    }

    public void CloseConnection(String userKey) throws IOException {
        ClientHandler clientIns = clientHM.get(userKey);
        ClientHandler remove = clientHM.remove(userKey);
        clientIns.inputFromClient.close();
        clientIns.outToClient.close();
        clientIns.socketInstance.close();

        log.appendLogText(userKey, "HAS LOGGED OUT");
    }

}
