package com.dashuai.poker.bo.player;

import com.dashuai.poker.bo.poker.HandType;
import com.dashuai.poker.bo.poker.PokerCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    /**
     * id
     */
    private String id;
    /**
     * 名称
     */
    private String name;

    /**
     * 筹码
     */
    private Integer chips;

    /**
     * 下注
     */
    private Integer bet;

    /**
     * 上一次下注
     */
    private Integer lastBet;


    /**
     * 手牌
     */
    private List<PokerCard> cards;

    /**
     * 是否弃牌
     */
    private boolean fold;

    /**
     * 是否 all in
     */
    private boolean allIn;

    /**
     * 是否是庄家
     */
    private boolean isDealer;

    /**
     * 手牌类型
     */
    private HandType handType;

    /**
     * 随机选择庄家
     * @param players
     * @return 庄家
     */
    public static Player chooseDealer(List<Player> players) {
        Player player = players.get((int) (Math.random() * players.size()));
        player.setDealer(true);
        return player;
    }

    public void bet(int bet) {
        if (bet == 0) {
            return;
        }

        this.lastBet = bet;
        this.bet += bet;
        this.chips -= bet;

        if (this.chips == 0) {
            this.allIn = true;
        }
    }

    /**
     * 初始化一些测试的玩家
     * @return
     */
    public static List<Player> initPlayers() {
        List<Player> players = new ArrayList<>();
        Player player1 = new Player("1", "player1", 1000, 0, 0, new ArrayList<>(), false, false, false, null);
        Player player2 = new Player("2", "player2", 1000, 0, 0, new ArrayList<>(), false, false, false, null);

        players.add(player1);
        players.add(player2);
        return players;
    }

    public void getCard(List<PokerCard> card) {
        this.cards.addAll(card);
    }
}
