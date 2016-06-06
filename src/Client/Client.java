/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * UNUSED CLASS!!! WILL BE REMOVED
 * @author Nogna
 */
public class Client {

    public static void main(String[] args) {

        setUp();
        
        ConnectionToServer.getServerResponse();
        ConnectionToServer.sendClientResponse();
        ConnectionToServer.getServerResponse();
        
        ConnectionToServer.closeConnection();
        System.exit(0);

    }

    private static void setUp() {

        BackgroundPanel bg = new BackgroundPanel();
        bg.setUpBg();
        ConnectionToServer.setUpConnectionToServer();

    }

}
