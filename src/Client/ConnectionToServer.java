/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Nogna
 */
public class ConnectionToServer {

    static Socket SOCKET;
    static String SERVER = "localhost";
    static public ObjectOutputStream STREAM_OUT_TO_SERVER;
    static public ObjectInputStream STREAM_IN_FROM_SERVER;
    static final int DEFAULT_SOCKET_PORT = 9010;
    static public Object SERVER_RESPONSE = null;

    private static Socket createSocketToServer() throws IOException {

        System.out.println("Trying connect to " + SERVER + " on port " + DEFAULT_SOCKET_PORT);
        Socket ClientSocketToServer = new Socket(SERVER, DEFAULT_SOCKET_PORT);
        System.out.println("Just connected to " + ClientSocketToServer.getRemoteSocketAddress());
        return ClientSocketToServer;
    }

    private static void createStreamsToServer() throws IOException {
        System.out.println("Trying to create OutputStream...");
        STREAM_OUT_TO_SERVER = new ObjectOutputStream(SOCKET.getOutputStream());
        STREAM_OUT_TO_SERVER.flush();
        System.out.println("OutputStream ---- Success");
        System.out.println("Trying to create InputStream...");
        STREAM_IN_FROM_SERVER = new ObjectInputStream(SOCKET.getInputStream());
        System.out.println("InputStream ---- Success");

    }

    public static void closeConnection() {
        try {
            if (SOCKET != null) {
                SOCKET.close();
            }
            if (STREAM_OUT_TO_SERVER != null) {
                STREAM_OUT_TO_SERVER.close();
            }
            if (STREAM_IN_FROM_SERVER != null) {
                STREAM_IN_FROM_SERVER.close();
            }

        } catch (IOException e) {
            System.out.println("Coulnt close the streams...- " + e.getMessage());
        }
    }

    static void setUpToServer() {
        try {
            SOCKET = createSocketToServer();
            System.out.println("Socket ---- Success");
            createStreamsToServer();
            System.out.println("Streams ---- Success");

        } catch (IOException e) {
            System.out.println("Couldnt connect to " + SERVER + " fel:" + e);

        }
    }

    static void getServerResponse() {
        try {

            SERVER_RESPONSE = STREAM_IN_FROM_SERVER.readObject();

            System.out.println("Server response " + SERVER_RESPONSE);

        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ConnectionToServer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    static void sendClientResponse() {
        try {
            /*Scanner Sc = new Scanner(System.in);
        String sendThisToServer = Sc.nextLine();
             */
            String sendThisToServer = "I PRESSED THE BUTTON!";
            STREAM_OUT_TO_SERVER.writeObject(sendThisToServer);
            STREAM_OUT_TO_SERVER.flush();
        } catch (IOException e) {
            System.out.println("Error sending - " + e.getMessage());
        }
    }

}
