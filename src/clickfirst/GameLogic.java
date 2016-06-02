/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clickfirst;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nogna
 */
public class GameLogic {

    int AMOUNT_OF_PLAYERS;
    ArrayList<ServerThreadToEachClient> PLAYER_LIST;
    boolean LOSER;
    boolean GAME_ON = false;

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

    void start() throws IOException {
        LOSER = false;
        /*for (int i = 0; i < PLAYER_LIST.size(); i++) {
            String gameOn = "Press the buttom!";
            System.out.println("Sending: "+ gameOn + " to "+ PLAYER_LIST.get(i).getId());
            PLAYER_LIST.get(i).STREAM_OUT_TO_CLIENT.writeObject(gameOn);
            PLAYER_LIST.get(i).STREAM_OUT_TO_CLIENT.flush();
        }*/
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
