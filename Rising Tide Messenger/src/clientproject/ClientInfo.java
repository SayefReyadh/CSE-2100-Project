/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientproject;

import designfiles.Login;
import designfiles.OnlineChatUsersListWindow;
import java.net.Socket;

/**
 *
 * @author SayefReyadh
 */
public class ClientInfo {

    public static ClientInfo clientInfoObject;
    private final String userName;
    private final String password;
    private final Socket clientSocket;
    private final ClientWriter clientWriterInstance; // = new ClientWriter(clientSocket);
    private final ClientReciever clientReceiverInstance; // = new ClientReciever(clientSocket);
    public static Login loginInstance;
    public static OnlineChatUsersListWindow chatUsersListWindow;
    private static byte [] userImageBuffer;
    
    public ClientInfo(String userName, String password, Socket clientSocket, ClientWriter clientWriterInstance, ClientReciever clientReceiverInstance) {
        this.userName = userName;
        this.password = password;
        this.clientSocket = clientSocket;
        this.clientWriterInstance = clientWriterInstance;
        this.clientReceiverInstance = clientReceiverInstance;
    }
    
    
    public static void setClientInfoObject(ClientInfo clientInfoObject) {
        ClientInfo.clientInfoObject = clientInfoObject;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public ClientWriter getClientWriterInstance() {
        return clientWriterInstance;
    }

    public ClientReciever getClientReceiverInstance() {
        return clientReceiverInstance;
    }

    public static byte[] getUserImageBuffer() {
        return userImageBuffer;
    }

    public static void setUserImageBuffer(byte[] userImageBuffer) {
        ClientInfo.userImageBuffer = userImageBuffer;
    }
    
    
    
}
