package com.dashuai.poker.bo.texas;

import com.dashuai.poker.bo.player.Player;
import com.dashuai.poker.bo.player.PlayerActionEnum;
import com.dashuai.poker.bo.poker.PokerCard;
import org.junit.Test;

public class DefaultTexasPokerTest {

    @Test
    public void testTexasPoker() {
        TexasPoker texasPoker = new DefaultTexasPoker();
        texasPoker.start();
        System.out.println("开始游戏");

        while (!texasPoker.isEnd()) {
            texasPoker.playerAction(PlayerActionEnum.BET, 100);
        }
    }

}
