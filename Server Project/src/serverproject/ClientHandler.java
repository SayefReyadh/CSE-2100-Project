package serverproject;

import java.io.*;
import java.net.*;
import java.util.*;
import static serverproject.ServerProject.clientImage;

public class ClientHandler implements Runnable {

    public final static int FILE_SIZE = 6022386;
    public final Socket socketInstance;
    public BufferedReader inputFromClient;
    public DataOutputStream outToClient;
    private String[] clientMsg;
    private String user2;
    private String sender2;
    private String onlineUserList = "";
    private ClientHandler clientRequested;
    ObjectOutputStream writeImageToClient;

    public ClientHandler(Socket s) {
        socketInstance = s;
    }

    @Override
    public void run() {
        try {

            inputFromClient = new BufferedReader(new InputStreamReader(socketInstance.getInputStream()));
            outToClient = new DataOutputStream(socketInstance.getOutputStream());

            while (true) {

                clientMsg = (inputFromClient.readLine()).split(":", 2);

                for (Map.Entry<String, ClientHandler> instance : ServerProject.clientHM.entrySet()) {
                    clientRequested = instance.getValue();
                    if (clientRequested.socketInstance == socketInstance) {
                        sender2 = instance.getKey();
                        break;
                    }
                }
                if (clientMsg[0].equalsIgnoreCase("SERVERHISTORY")) {
                    String[] userNameTemp = clientMsg[1].split("-", 2);
                    String chatHistoryMessage = DatabaseConnection.getChatHistoryForAUser(userNameTemp[0], userNameTemp[1]);
                    String serverMessage = userNameTemp[0] + "-" + userNameTemp[1] + "-" + chatHistoryMessage;
                    clientRequested.outToClient.writeBytes("SERVERHISTORY:" + serverMessage + "\n");
                } else if (clientMsg[0].equalsIgnoreCase("SERVERHISTORYINSERT")) {
                    String[] userNameTemp = clientMsg[1].split("-", 3);
                    System.out.println(
                            DatabaseConnection.insertChatHistory(userNameTemp[0], userNameTemp[1], userNameTemp[2])
                            );

                }
                
                if (clientMsg[0].equalsIgnoreCase("SERVER")) {
                    if (clientMsg[1].equalsIgnoreCase("*notifyAll*")) {
                        notifyAllUserOfTheUpdate();
                    }

                    if (clientMsg[1].equalsIgnoreCase("*LogOut*")) {
                        clientRequested.outToClient.writeBytes("SERVER:" + "You're logging out mate!" + "\n");
                        DatabaseConnection.changeUserLoginState("logOut", sender2);
                        ServerProject serverObj = new ServerProject();
                        serverObj.CloseConnection(sender2);
                        notifyAllUserOfTheUpdate();

                    } else if (clientMsg[1].equalsIgnoreCase("*ProfilePhoto*")) {
                        clientRequested.outToClient.writeBytes("SERVER:" + "*image*" + "\n");
                        clientRequested.outToClient.flush();
                        writeImageToClient = new ObjectOutputStream(socketInstance.getOutputStream());
                        writeImageToClient.flush();
                        byte[] img = DatabaseConnection.searchImageBytesByUsername(sender2);
                        writeImageToClient.writeObject(img);

                        clientImage.put(sender2, img);

                    }

                } else if (clientMsg[0].equalsIgnoreCase("All")) //Broadcast msg
                {
                    for (Map.Entry<String, ClientHandler> instance : ServerProject.clientHM.entrySet()) {
                        ClientHandler clIns = instance.getValue();
                        if (clIns.socketInstance != socketInstance) {
                            clIns.outToClient.writeBytes(sender2 + ":" + clientMsg[1] + "\n");
                        }
                    }
                } else {
                    for (Map.Entry<String, ClientHandler> instance : ServerProject.clientHM.entrySet()) {
                        user2 = instance.getKey();
                        if (0 == user2.compareTo((clientMsg[0]))) {
                            ClientHandler clIns = instance.getValue();
                            if (clIns.socketInstance != socketInstance) {
                                clIns.outToClient.writeBytes(sender2 + ":" + clientMsg[1] + "\n");
                            }
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            //System.err.println("ErrorFoundInClientHandler:" + e);
        }
    }

    private void notifyAllUserOfTheUpdate() throws IOException {
        onlineUserList = "";
        for (Map.Entry<String, ClientHandler> instance : ServerProject.clientHM.entrySet()) {
            if (instance != null) {
                user2 = instance.getKey();
                onlineUserList += user2 + "-";
                //System.out.println("online user list: " + onlineUserList);
            }

        }
        for (Map.Entry<String, ClientHandler> instance : ServerProject.clientHM.entrySet()) {
            ClientHandler clIns = instance.getValue();
            //System.out.println("clienthandler1");
            if (onlineUserList.isEmpty()) {
                clIns.outToClient.writeBytes("SERVER:" + "No online user!" + "\n");
            } else {
                clIns.outToClient.writeBytes("SERVER:" + onlineUserList + "\n");
            }
        }
    }
}
