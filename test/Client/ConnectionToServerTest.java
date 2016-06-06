/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nogna
 */
public class ConnectionToServerTest {
    
    public ConnectionToServerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of closeConnection method, of class ConnectionToServer.
     */
    @Test
    public void testCloseConnection() {
        System.out.println("closeConnection");
        ConnectionToServer.closeConnection();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUpConnectionToServer method, of class ConnectionToServer.
     */
    @Test
    public void testSetUpToServer() {
        System.out.println("setUpToServer");
        ConnectionToServer.setUpConnectionToServer();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getServerResponse method, of class ConnectionToServer.
     */
    @Test
    public void testWaitForResponse() {
        System.out.println("waitForResponse");
        ConnectionToServer.getServerResponse();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
