package com.dashuai.poker.bo.texas;

import com.dashuai.poker.bo.player.Player;
import com.dashuai.poker.bo.player.PlayerAction;
import com.dashuai.poker.bo.player.PlayerActionEnum;
import com.dashuai.poker.bo.poker.PokerCard;
import com.dashuai.poker.bo.round.RoundSnapshot;
import com.dashuai.poker.bo.round.SystemRoundEngine;
import com.dashuai.poker.bo.round.SystemRoundFactory;

import java.util.List;

import static com.dashuai.poker.bo.player.PlayerActionFactory.createPlayerAction;

public class DefaultTexasPoker implements TexasPoker {
    RoundSnapshot roundSnapshot;

    SystemRoundFactory systemRoundFactory;

    public DefaultTexasPoker() {
        roundSnapshot = new RoundSnapshot();
        systemRoundFactory = new SystemRoundFactory(roundSnapshot);
    }

    @Override
    public void start() {
        systemRoundFactory.start();
    }

    @Override
    public void nextRound() {
        if (roundSnapshot.isOver()) {
            return;
        }

        systemRoundFactory.nextRound();
        if (!roundSnapshot.isOver()) {
            String cards = "";
            for (PokerCard publicCard : roundSnapshot.getPublicCards()) {
                cards += String.format("%s%s\t", publicCard.getSuit(), publicCard.getRank());
            }
            System.out.println("开始新的一轮, 目前公共牌是: " + cards);
        }
    }

    @Override
    public void nextPlayer() {
        /**
         * 如果每个人下注都是一样的且当前玩家是庄家，则进入下一轮
         */
        if (roundSnapshot.getNextPlayer().isDealer() && roundSnapshot.isSameBet()) {
            nextRound();
        }

        roundSnapshot.nextPlayer();
    }

    @Override
    public void playerAction(PlayerActionEnum actionEnum, int n) {
        createPlayerAction(actionEnum, roundSnapshot.getCurrentPlayer()).action(n);
        nextPlayer();
    }

    @Override
    public boolean isEnd() {
        return roundSnapshot.isOver();
    }

    @Override
    public List<Player> getWinners() {
        return roundSnapshot.getWinners();
    }
}
