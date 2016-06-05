/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Nogna
 */
public class GameLogicTest {
    
    public GameLogicTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of add method, of class GameLogic.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        ServerThreadToEachClient clientThread = null;
        GameLogic instance = new GameLogic();
        instance.add(clientThread);
        
    }

    /**
     * Test of startGame method, of class GameLogic.
     */
    @Test
    public void testStartGame() {
        System.out.println("startGame");
        GameLogic instance = new GameLogic();
        boolean expresult1 = instance.GAME_ON;
        boolean expresult2 = instance.LOSER;
        instance.startGame();
        
        assertFalse(expresult1);
        assertFalse(expresult2);
        assertTrue(instance.GAME_ON);
        assertFalse(instance.LOSER);
        
        
    }

    /**
     * Test of clientResponded method, of class GameLogic.
     */
    @Test
    public void testClientResponded() {
        System.out.println("clientResponded");
        GameLogic instance = new GameLogic();
        String expResultLoser = "Loser";
        String expResultWinner = "Winner";
        String resultWinner = instance.clientResponded();
        String resultLoser = instance.clientResponded();
        assertEquals(expResultWinner, resultWinner);
        assertEquals(expResultLoser, resultLoser);
        
    }
    
}
