package com.example.android.smashupscorekeeper;

public class PlayerClass {
    public int score;
    public int points;
    public int playerN;
    public String playerName;
    public int playerID;

    public PlayerClass(String name, int id) {
        score=0;
        points=0;
        playerName = name;
        playerID = id;
    }

    public void reset(){
        score=0;
        points=0;
    }


}
