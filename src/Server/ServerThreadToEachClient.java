/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Nogna
 */
class ServerThreadToEachClient extends Thread {

    final ObjectOutputStream STREAM_OUT_TO_CLIENT;
    final ObjectInputStream STREAM_IN_FROM_CLIENT;
    final Socket CLIENTSOCKET;

    public ServerThreadToEachClient(Socket newclient) throws IOException {
        super("ClientThread");
        CLIENTSOCKET = newclient;
        STREAM_IN_FROM_CLIENT = new ObjectInputStream(CLIENTSOCKET.getInputStream());
        STREAM_OUT_TO_CLIENT = new ObjectOutputStream(CLIENTSOCKET.getOutputStream());
    }

    @Override
    public void run() {
        try {

            if (Server.gamelogic.AMOUNT_OF_PLAYERS_CURRENTLY == Server.gamelogic.NUMBER_OF_PLAYERS_WHEN_GAME_START) {
                synchronized (Server.lock) {
                    System.out.println(this.getId() + " is trying to notify the other thread");
                    Server.lock.notifyAll();
                }
            } else {
                try {
                    synchronized (Server.lock) {
                        System.out.println(this.getId() + " is now waiting for the game to start...");
                        Server.lock.wait();
                    }

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            String gameOn = "Press the buttom!";
            STREAM_OUT_TO_CLIENT.writeObject(gameOn);

            ///Client Response/////////
            Object clientResponse = STREAM_IN_FROM_CLIENT.readObject();


            String gameResponse = Server.gamelogic.clientResponded();
            System.out.println("Sending " + gameResponse + " to " + this.getId());
            STREAM_OUT_TO_CLIENT.writeObject(gameResponse);

        } catch (IOException e) {
            System.out.println("Connection lost with Client: " + CLIENTSOCKET + " " + e);
        } catch (ClassNotFoundException ex) {
            System.out.println("Client send something new...?? " + ex);
        }

    }

}
