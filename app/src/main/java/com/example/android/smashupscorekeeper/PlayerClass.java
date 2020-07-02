package com.example.android.smashupscorekeeper;

public class PlayerClass {
    public int score;
    public int points;
    public int playerN;
    public String playerName;

    public PlayerClass(int num) {
        score=0;
        points=0;
        playerN=num;
        playerName = "player "+playerN;
    }

    public void reset(){
        score=0;
        points=0;
    }


}
