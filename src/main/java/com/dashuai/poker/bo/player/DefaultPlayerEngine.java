package com.dashuai.poker.bo.player;

import com.dashuai.poker.bo.poker.PokerCard;

import java.util.List;

public class DefaultPlayerEngine implements PlayerEngine {
    private Player player;

    public DefaultPlayerEngine(Player player) {
        this.player = player;
    }


    @Override
    public void bet(int bet) {
        if (bet == 0) {
            return;
        }

        player.bet(bet);
    }

    @Override
    public void fold() {
        player.setFold(true);
    }

    @Override
    public void allIn() {
        bet(player.getChips());
    }

    @Override
    public void getPokerCards(List<PokerCard> pokerCards) {
        player.getCards().addAll(pokerCards);
    }
}
