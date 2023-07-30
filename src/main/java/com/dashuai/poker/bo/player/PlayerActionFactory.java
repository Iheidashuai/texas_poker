package com.dashuai.poker.bo.player;

public class PlayerActionFactory {
    public static PlayerAction createPlayerAction(PlayerActionEnum playerActionEnum, Player player) {
        DefaultPlayerEngine defaultPlayerEngine = new DefaultPlayerEngine(player);
        switch (playerActionEnum) {
            case GIVE_UP:
                return new PlayerFoldAction(defaultPlayerEngine);
            case BET:
                return new PlayerBetAction(defaultPlayerEngine);
            case ALL_IN:
                return new PlayerAllInAction(defaultPlayerEngine);
            case CHECK:
                return new PlayerCheckAction(defaultPlayerEngine);
            default:
                throw new RuntimeException("unknown player action");
        }
    }
}
