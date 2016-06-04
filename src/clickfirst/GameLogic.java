/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clickfirst;


import java.util.ArrayList;

/**
 *
 * @author Nogna
 */
public class GameLogic{

    int AMOUNT_OF_PLAYERS;
    ArrayList<ServerThreadToEachClient> PLAYER_LIST;
    boolean LOSER;
    boolean GAME_ON;

    public GameLogic() {
        this.PLAYER_LIST = new ArrayList<ServerThreadToEachClient>();
        GAME_ON = false;
        LOSER = false;
    }

    void add(ServerThreadToEachClient clientThread) {
        try {
            PLAYER_LIST.add(clientThread);
            System.out.println("Added "+ clientThread.getId()+" to the game");
            AMOUNT_OF_PLAYERS++;
        } catch (Exception e) {
            System.out.println(e + " Woops!");
        }
    }

    
    public void startGame(){
        LOSER = false;
        GAME_ON = true;
    }

    String clientResponded() {
        if (!LOSER) {
            LOSER = true;
            return "Winner";
        } else {
            return "Loser";
        }
    }

}
