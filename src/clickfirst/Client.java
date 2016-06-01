/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clickfirst;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.Thread.sleep;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Nogna
 */
public class Client {

    static Socket SOCKET;
    static String SERVER = "localhost";
    static public ObjectOutputStream STREAM_OUT_TO_SERVER;
    static public ObjectInputStream STREAM_IN_FROM_SERVER;
    static final int DEFAULT_SOCKET_PORT = 8080;
    static private Object SERVER_RESPONSE = null;

    /*
    Closes the correct socket depending on the state
     */
    private static void closeConnection() throws IOException {
        if (SOCKET != null) {
            SOCKET.close();
        }
        if (STREAM_OUT_TO_SERVER != null) {
            STREAM_OUT_TO_SERVER.close();
        }
        if (STREAM_IN_FROM_SERVER != null) {
            STREAM_IN_FROM_SERVER.close();
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        try {
            SOCKET = createSocketToServer();
            System.out.println("Socket ---- Success");
            createStreamsToServer();
            System.out.println("Streams ---- Success");
            SERVER_RESPONSE = STREAM_IN_FROM_SERVER.readObject();
            System.out.println("Server response "+ SERVER_RESPONSE);
            
            Scanner Sc = new Scanner(System.in);
            String sendThisToServer = Sc.nextLine();
            STREAM_OUT_TO_SERVER.writeObject(sendThisToServer);
            STREAM_OUT_TO_SERVER.flush();
            
            SERVER_RESPONSE = STREAM_IN_FROM_SERVER.readObject();

        } catch (IOException e) {
            System.out.println("Couldnt connect to " + SERVER + " fel:"+ e);

        }

        System.out.println("Det här skickade server till mig: " + SERVER_RESPONSE);
    }

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
}
