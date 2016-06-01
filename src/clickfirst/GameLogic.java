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
    int RESPONSES;
    boolean GAME_ON;

    public GameLogic() {
        this.PLAYER_LIST = new ArrayList<ServerThreadToEachClient>();
        GAME_ON = false;
    }
    


    void add(ServerThreadToEachClient clientThread) {
        try{
        PLAYER_LIST.add(clientThread);
        AMOUNT_OF_PLAYERS++;
        }
        catch(Exception e){
            System.out.println(e + " Woops!");
        }
    }

    void start() throws IOException {
        RESPONSES = 2;
        for (int i = 0; i < PLAYER_LIST.size(); i++) {
            String gameOn = "Press the buttom!";
            PLAYER_LIST.get(i).STREAM_OUT_TO_CLIENT.writeObject(gameOn);
            PLAYER_LIST.get(i).STREAM_OUT_TO_CLIENT.flush();
        }
    }

    String clientResponded() {
        if (RESPONSES == 0) {
            return "Loser";
        } else {
            RESPONSES--;
            return "Winner";
        }
    }

}
