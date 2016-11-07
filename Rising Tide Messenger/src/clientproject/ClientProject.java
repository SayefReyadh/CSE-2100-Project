package clientproject;

import java.io.*;
import java.net.*;
import javax.swing.JOptionPane;

public class ClientProject {

    public static Socket clientSocketForSignUpUser;
    private static byte[] imgBytes;

    public static boolean signUpClient(String name, String userName, String password) {
        String request = "signup" + " " + name + " " + userName + " " + password;
        return connectAndSendRequestToSocket(request, userName, password);
    }

    public static boolean loginClient(String userName, String password) {
        String request = "login" + " " + userName + " " + password;
        System.out.println("request: " + request);
        return connectAndSendRequestToSocket(request, userName, password);
    }

    public static boolean signUpImageUpload(String username, String password, byte[] imageBytes) {
        imgBytes = imageBytes;
        return connectAndSendRequestToSocket("signUpImage", username, password);
    }

    public static boolean connectAndSendRequestToSocket(String requestMessage, String userName, String password) {
        //BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader confirmationFromServer;
        DataOutputStream writeToServer;
        String userInfo;
        String[] serverMsg;

        try {
            Socket clientSocket = new Socket("localhost", 6789);
            //Socket clientSocket = new Socket("192.168.43.252", 6789);
            writeToServer = new DataOutputStream(clientSocket.getOutputStream());
            confirmationFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            

            userInfo = requestMessage;
            System.out.println("uerinfor:" + userInfo);
            if (requestMessage.equalsIgnoreCase("signUpImage")) {
                userInfo = requestMessage + " " + userName;
                writeToServer.writeBytes(userInfo + "\n");

                System.out.println("signUpImage message sent");
                ObjectOutputStream writeImageToClient = new ObjectOutputStream(clientSocket.getOutputStream());

                System.out.println("signUpImage obj created");
                writeImageToClient.writeObject(imgBytes);

                System.out.println("signUpImage obj sent");

            } else {
                writeToServer.writeBytes(userInfo + "\n");
            }

            serverMsg = (confirmationFromServer.readLine()).split(":");
            if (serverMsg[0].equalsIgnoreCase("OK")) {

                System.out.println("socket successful: " + serverMsg[1]);

                if (serverMsg[1].equalsIgnoreCase("*signupsuccessful*")) {
                    JOptionPane.showMessageDialog(null, "Signup successful!\nPlease upload your photo to continue!");
                    clientSocketForSignUpUser = clientSocket;
                    clientSocket.close();
                    return true;
                }
                else if (serverMsg[1].equalsIgnoreCase("*imageUploaded*")) {
                    System.out.println("uploaded image successfully");
                    clientSocket.close();
                    return true;
                } else {

                    /// GETS THE PROFILE PHOTO OF THE USER :P
                    try {
                        System.out.println(serverMsg[1]);
                        ObjectInputStream readFromServer = new ObjectInputStream(clientSocket.getInputStream());
                        //readFromServer.close();
                        System.out.println("2");
                        byte[] buffer = (byte[]) readFromServer.readObject();
                        System.out.println("THIS IS IMAGE LENGTH" + buffer.length);
                        System.out.println("3");
                        ClientInfo.setUserImageBuffer(buffer);
                        System.out.println("4");
                    } catch (Exception e) {
                    }

                    ClientWriter clientInstance = new ClientWriter(clientSocket);
                    ClientReciever clientReceiverInstance = new ClientReciever(clientSocket);
                    Thread t = new Thread(clientInstance);
                    Thread t1 = new Thread(clientReceiverInstance);
                    t.start();
                    t1.start();

                    ClientInfo info = new ClientInfo(userName, password, clientSocket, clientInstance, clientReceiverInstance);
                    ClientInfo.setClientInfoObject(info);

                    //ClientInfo.loginInstance.dispose();
                    ClientInfo.clientInfoObject.getClientWriterInstance().writeToClient("SERVER", "*notifyAll*");
                    return true;
                }
            } else if (serverMsg[1].equalsIgnoreCase("*imageUploadFailed*")) {
                JOptionPane.showMessageDialog(null, "Image upload failed,please try again");
                clientSocket.close();
                return false;
            } else if (serverMsg[1].equalsIgnoreCase("*PasswordWrong*")) {
                JOptionPane.showMessageDialog(null, "Wrong Password");
                clientSocket.close();
                return false;
            } else if (serverMsg[1].equalsIgnoreCase("*usernameExists*")) {

                JOptionPane.showMessageDialog(null, "username already taken,please give a new one!");
                clientSocket.close();
                return false;
            } else if (serverMsg[1].equalsIgnoreCase("*alreadyLoggedIn*")) {

                JOptionPane.showMessageDialog(null, "This user is already logged in!\nPlease logout to login again!");
                clientSocket.close();
                return false;
            } else {
                JOptionPane.showMessageDialog(null, "Wrong Username");
                clientSocket.close();
                return false;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "SERVER IS DOWN");
            System.err.println("ErrorFoundInClientProject:" + ex);
            return false;
        }
    }
}
