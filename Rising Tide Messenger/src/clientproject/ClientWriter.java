
package clientproject;

import java.io.*;
import java.net.*;

public class ClientWriter implements Runnable {

    private final Socket socketInstance;

    public ClientWriter(Socket s) {
        socketInstance = s;
    }

    public void writeToClient(String username, String msg) {
        try {
            //BufferedReader inputSystem = new BufferedReader(new InputStreamReader(System.in));
            if (msg.compareTo("") != 0) {
                DataOutputStream writeToServer = new DataOutputStream(socketInstance.getOutputStream());
                String input = username + ":" + msg;
                //System.out.println(input);
                writeToServer.writeBytes(input + '\n');
                writeToServer.flush();
            }

        } catch (Exception e) {
            System.err.println("ErrorInClientWriter:" + e);
        }
    }

    @Override
    public void run() {
        try {
            BufferedReader inputSystem = new BufferedReader(new InputStreamReader(System.in));
            DataOutputStream writeToServer = new DataOutputStream(socketInstance.getOutputStream());
            while (true) {
                String input = inputSystem.readLine();
                //System.out.println("Here " + input);
                writeToServer.writeBytes(input + '\n');
                writeToServer.flush();
            }
        } catch (Exception e) {
            System.err.println("ErrorInClientWriter:" + e);
        }
    }

}
