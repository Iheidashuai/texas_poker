package com.dashuai.poker.bo.round;

import com.dashuai.poker.bo.player.Player;
import com.dashuai.poker.bo.poker.DefaultPokerEngine;
import com.dashuai.poker.bo.poker.HandType;
import com.dashuai.poker.bo.poker.PokerCard;
import com.dashuai.poker.bo.poker.PokerEngine;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.util.Pair;

import java.util.*;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoundSnapshot {
    /**
     * 牌引擎
     */
    private PokerEngine pokerEngine;
    /**
     * 是否已经发牌
     */
    private boolean isDealOut;
    /**
     * 玩家，按照座位顺序
     */
    private List<Player> players;
    /**
     * 存活玩家
     */
    private List<Player> alivePlayers;
    /**
     * 庄家
     */
    private Player dealer;
    /**
     * 公共牌
     */
    private List<PokerCard> publicCards;
    /**
     * 当前回合
     */
    private RoundEnum round;
    /**
     * 当前桌面上的下注
     */
    private int bet;
    /**
     * 当前下注的玩家
     */
    private Player currentPlayer;
    /**
     * 还未发的牌
     *
     * @return 未发的牌
     */
    private List<PokerCard> unDealtCards;
    /**
     * 目前最大的下注
     */
    private int maxBet;
    /**
     * 是否已经结束
     */
    private boolean isOver;
    /**
     * 获胜玩家
     */
    private List<Player> winners;
    /**
     * 上一次下注
     */
    private int lastBet;

    public void init(List<Player> players) {
        this.round = RoundEnum.PRE_FLOP;
        this.alivePlayers = players;
        this.players = players;
        this.unDealtCards = PokerCard.init();
        this.dealer = Player.chooseDealer(players);
        this.currentPlayer = this.dealer;
        this.currentPlayer.setDealer(true);
        this.publicCards = new ArrayList<>();
        this.isOver = false;
        this.winners = new ArrayList<>();
        this.pokerEngine = new DefaultPokerEngine(this.unDealtCards);
    }


    /**
     * 翻牌
     */
    public void flop(int n) {
        getPublicCards().addAll(PokerCard.getCards(getUnDealtCards(), n));
    }

    public void showDown() {
        Map<String, List<PokerCard>> playersHands = alivePlayers.stream().collect(Collectors.toMap(Player::getId, Player::getCards));
        Pair<Map<String, HandType>, List<String>> result = pokerEngine.compareNHands(playersHands, publicCards);
        this.winners = alivePlayers.stream().filter(e -> result.getSecond().contains(e.getId())).collect(Collectors.toList());
        this.winners.forEach(e -> e.setHandType(result.getFirst().get(e.getId())));
        this.isOver = true;

        Player winPlayer = winners.get(0);
        String cards = "";
        for (PokerCard publicCard : winPlayer.getCards()) {
            cards += String.format("%s%s\t", publicCard.getSuit(), publicCard.getRank());
        }
        System.out.println("获胜者是: " + winPlayer.getName() + "\t他的底牌是" + cards + "\t他的牌型是" + winPlayer.getHandType());

        Player failPlayer = this.players.stream().filter(e -> !Objects.equals(e.getId(), winPlayer.getId())).collect(Collectors.toList()).get(0);
        String failPlayerCards = "";
        for (PokerCard publicCard : failPlayer.getCards()) {
            failPlayerCards += String.format("%s%s\t", publicCard.getSuit(), publicCard.getRank());
        }
        System.out.println("失败者是: " + failPlayer.getName() + "\t他的底牌是" + failPlayerCards + "\t他的牌型是" + result.getFirst().get(failPlayer.getId()));
    }

    public void nextPlayer() {
        int index = players.indexOf(currentPlayer);
        if (index == players.size() - 1) {
            index = 0;
        } else {
            index++;
        }
        currentPlayer = players.get(index);
    }

    public Player getNextPlayer() {
        int index = players.indexOf(currentPlayer);
        if (index == players.size() - 1) {
            index = 0;
        } else {
            index++;
        }
        return players.get(index);
    }

    /**
     * 判断当前 round 每个人的下注是否相同
     */
    public boolean isSameBet() {
        return alivePlayers.stream().filter(e -> !e.isFold()).map(Player::getBet).distinct().count() == 1;
    }

    public void nextRound() {
        round = round.next();
    }

    public void fold() {
        currentPlayer.setFold(true);
        alivePlayers.remove(currentPlayer);
    }
}
