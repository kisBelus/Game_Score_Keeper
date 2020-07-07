package com.example.android.smashupscorekeeper;

public class PlayerClass {
    public int score;
    public int points;
    public int victories;
    public int losses;
    public int streak;
    public int total;
    public String playerName;
    public int playerID;
    public boolean win = false;

    public PlayerClass(String name, int id) {
        score=0;
        points=0;
        playerName = name;
        playerID = id;
    }
    public PlayerClass(){}

    public void reset(){
        score=0;
        points=0;
    }

    public int getPlayerID(){
        return playerID;
    }

    public String getPlayerName() {
        return playerName;
    }
}
