/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clickfirst;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;


/**
 *
 * @author Nogna
 */
public class ClickFirst {
    static ServerSocket serverSocket;
    static GameLogic gamelogic;
    static boolean GAME_ON;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            serverSocket = new ServerSocket(8080);
            gamelogic = new GameLogic();
            GAME_ON = false;
            while (true) {
                Socket newClient = waitForConnection(serverSocket);
                setUpConnectionWithClient(newClient);
                if (gamelogic.AMOUNT_OF_PLAYERS == 2) {
                    GAME_ON = true;
                    gamelogic.start();
                    
                } 
            }
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }

    }

    private static void setUpConnectionWithClient(Socket newclient) throws IOException {
        try {
            ServerThreadToEachClient clientThread = new ServerThreadToEachClient(newclient);
            System.out.println("Accepted Client: " + clientThread.getId());
            gamelogic.add(clientThread);
            clientThread.start();
        } catch (SocketException e) {
            System.out.println("Couldn't connect to Client");
        }

    }
        private static Socket waitForConnection(ServerSocket serverSocket) throws IOException {
        System.out.println("Waiting for client on port: " + serverSocket.getLocalPort()
                + " and IP: " + InetAddress.getLocalHost());
        return serverSocket.accept();
    }
}
