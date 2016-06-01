/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clickfirst;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Nogna
 */
class ServerThreadToEachClient extends Thread {

    ObjectOutputStream STREAM_OUT_TO_CLIENT;
    ObjectInputStream STREAM_IN_FROM_CLIENT;
    final Socket CLIENTSOCKET;

    public ServerThreadToEachClient(Socket socket) throws IOException {
        super("ClientThread");
        CLIENTSOCKET = socket;
        STREAM_IN_FROM_CLIENT = new ObjectInputStream(CLIENTSOCKET.getInputStream());
        STREAM_OUT_TO_CLIENT = new ObjectOutputStream(CLIENTSOCKET.getOutputStream());
    }

    public void run() {
        try {
            System.out.println("Waiting for client, " + CLIENTSOCKET + " to send something...");
            
            while(!ClickFirst.gamelogic.GAME_ON){
                
            }
            
            String goTime = "Press the buttom!";
            STREAM_OUT_TO_CLIENT.writeObject(goTime);
            
            ///Client Response/////////
            Object clientResponse = STREAM_IN_FROM_CLIENT.readObject();
            System.out.println("Clienten, " + CLIENTSOCKET + " skickade " + clientResponse);
            /////////////////////

            String gameResponse = ClickFirst.gamelogic.clientResponded();
            STREAM_OUT_TO_CLIENT.writeObject(gameResponse);
            
        } catch (IOException e) {
            System.out.println("Connection lost with Client: " + CLIENTSOCKET +" "+ e);
        } catch (ClassNotFoundException ex) {
            System.out.println("Client send something new...?? " + ex);
        }

    }

}
