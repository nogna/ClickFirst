/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;


///SET UP WINDOW
import javax.swing.JFrame;

///FÖR GRAPIFHCs
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;




/// SET UP SOCKETS
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


///TO BE REMOVED
import java.util.Scanner;

/**
 *
 * @author Nogna
 */
public class Client extends JFrame implements ActionListener{

    static Socket SOCKET;
    static String SERVER = "localhost";
    static public ObjectOutputStream STREAM_OUT_TO_SERVER;
    static public ObjectInputStream STREAM_IN_FROM_SERVER;
    static final int DEFAULT_SOCKET_PORT = 8080;
    static private Object SERVER_RESPONSE = null;

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        setUpWindow();
        setUpToServer();

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

    private static void setUpToServer() throws ClassNotFoundException {
        try {
            SOCKET = createSocketToServer();
            System.out.println("Socket ---- Success");
            createStreamsToServer();
            System.out.println("Streams ---- Success");
            SERVER_RESPONSE = STREAM_IN_FROM_SERVER.readObject();
            System.out.println("Server response " + SERVER_RESPONSE);

            Scanner Sc = new Scanner(System.in);
            String sendThisToServer = Sc.nextLine();
            STREAM_OUT_TO_SERVER.writeObject(sendThisToServer);
            STREAM_OUT_TO_SERVER.flush();

            System.out.println("MEssage sent! lets see if u win");

            SERVER_RESPONSE = STREAM_IN_FROM_SERVER.readObject();
            System.out.println(SERVER_RESPONSE + "!!!");

        } catch (IOException e) {
            System.out.println("Couldnt connect to " + SERVER + " fel:" + e);

        }
    }

    private static void setUpWindow() {
        JFrame window = new JFrame();
        int width = 640;
        int height = 480;
        window.setSize(width, height);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.setTitle("Click First!");
        window.setLocationRelativeTo(null);
        //window.pack(); Kanske vill ha den här
        
        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
    
    }

}
