package clientproject;

import audio.AudioTone;
import designfiles.ChatWindow;
import designfiles.OnlineChatUsersListWindow;
import java.io.ObjectInputStream;
import java.net.*;
import java.util.HashMap;
import java.util.Scanner;
import javax.swing.text.StyledDocument;

public class ClientReciever implements Runnable {

    public static HashMap<String, ChatWindow> chattingWith = new HashMap<>();
    private final Socket socketInstance;
    private String[] msg;
    ChatWindow chatBox;// = new ChatWindow();
    AudioTone sn = new AudioTone();

    public ClientReciever(Socket s) {
        socketInstance = s;
    }

    public void imageFile() {
        try {
            
            ObjectInputStream readFromServer = new ObjectInputStream(socketInstance.getInputStream());
            byte[] buffer = (byte[]) readFromServer.readObject();
            ClientInfo.setUserImageBuffer(buffer);
            ClientInfo.chatUsersListWindow.setUserImage(ClientInfo.getUserImageBuffer());
        } catch (Exception e) {
            System.out.println("In ImageFile Function " + e);
        }
        
    }

    @Override
    public void run() {
        try {
            Scanner inputServer = new Scanner(socketInstance.getInputStream());
            //ObjectInputStream readFromServer = new ObjectInputStream(socketInstance.getInputStream());
                          
            while (true) {
                if (inputServer.hasNext()) {
                    //msg = (inputServer.nextLine()).split(":");
                    msg = (inputServer.nextLine()).split(":", 2);
                    if(msg[0].equalsIgnoreCase("SERVERHISTORY"))
                    {
                        String[] userNameTemp = msg[1].split("-");
                        //sn.chatSound();
                        System.out.println("ChatHistoryRecieved Created == " + userNameTemp[1]);
                        if (!userNameTemp[2].equalsIgnoreCase("null")) {
                            String msg = userNameTemp[2].replaceAll("\\~", "\\\n");
                            //System.out.println("mss: " + msg);
                            chatBox = chattingWith.get(userNameTemp[1]);
                            chatBox.apendMessageBox(msg);
                            //chatBox.appendStringJTextPane("", msg);
                            String[] str = msg.split("\n");
                            for(String i : str)
                            {
                                String[] temp = i.split(" ::: ");
                                String[] test = new String[2];
                                test[0] = "";
                                test[1] = "";
                                int a = 0;
                                
                                for(String j : temp)
                                {
                                    test[a++] = j;
                                }
                                chatBox.appendStringJTextPane(test[0]+ " ::: ", test[1]);
                                
                            }                     
                            chatBox.setVisible(true);
                            chatBox.repaint();
                        }
                    }
                    else if (msg[0].equalsIgnoreCase("SERVER")) {
                        if (msg[1].equalsIgnoreCase("*Image*")) {
                            // server message SERVER:*Image*   
//                          System.out.println("1");
//                          ObjectInputStream readFromServer = new ObjectInputStream(socketInstance.getInputStream());
//                          //readFromServer.close();
//                          System.out.println("2");
//                          byte [] buffer = (byte[]) readFromServer.readObject();
//                          System.out.println("3");
//                          ClientInfo.setUserImageBuffer(buffer);        
//                          System.out.println("4");
                          
                          //ClientInfo.chatUsersListWindow.setUserImage(ClientInfo.getUserImageBuffer());    
                        } //added by shaafi 17-7-16 as this will invoke the function with the updated online list
                        else if (msg[1] == null) {
                            //OnlineListWindow.updateL("no user!");
                        } else {
                            System.out.println("msg: " + msg[0] + " " + msg[1]);
                            Thread.sleep(500);
                            String userOfThisReceiver = ClientInfo.clientInfoObject.getUserName();
                            OnlineChatUsersListWindow.updateOnlineUserList(msg[1], userOfThisReceiver);
                            System.out.println("msg: " + msg[0] + " " + msg[1]);
                        }

                    } else {

                        System.out.println("msg recieved from " + msg[0] + ">>" + msg[1]);
                        if (msg[1].equalsIgnoreCase("*chat*")) {
                            ChatWindow temp = chattingWith.get(msg[0]);
                            if (temp == null)
                            {
                                System.out.println("ChatBox Created" + msg[0]);
                                ChatWindow ob = new ChatWindow(msg[0]);
                                chatBox = ob;
                                chatBox.setVisible(true);
                                chatBox.setUserNameLabel(msg[0]);
                                //chatBox.apendMessageBox(msg[0]+" ::: "+msg[1]);
                                chatBox.repaint();
                                chattingWith.put(msg[0], chatBox);
                                ClientInfo.clientInfoObject.getClientWriterInstance().writeToClient(msg[0], "*chat*");
                                String historyCall = ClientInfo.clientInfoObject.getUserName() + "-" + msg[0];
                                ClientInfo.clientInfoObject.getClientWriterInstance().writeToClient("SERVERHISTORY", historyCall);
                            }
                            else
                            {
                                temp.setVisible(true);
                                temp.repaint();
                            }
                        } else {
                            sn.chatSound();
                            System.out.println("Message Created" + msg[0]);
                            chatBox = chattingWith.get(msg[0]);
                            chatBox.apendMessageBox(msg[0] + " ::: " + msg[1]);
                            chatBox.appendStringJTextPane(msg[0] + " ::: " , msg[1]);
                                                        
                            chatBox.setVisible(true);
                            chatBox.repaint();
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("ErrorInClientReceiver:" + e + "\n");
        }
    }
}
