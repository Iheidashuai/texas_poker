package com.dashuai.poker.bo.player;

public class PlayerCheckAction implements PlayerAction{
    private PlayerEngine playerEngine;

    PlayerCheckAction(PlayerEngine playerEngine){
        this.playerEngine = playerEngine;
    }

    @Override
    public void action(int n) {

    }
}
