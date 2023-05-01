package org.example;

import java.util.ArrayList;
import java.util.List;

public class Player {
  private String playerName;
  private int totalCount = 0;
  private boolean isBlackJack;
  private boolean isBusted;
  private boolean isDealer;

  public List<Card> getPlayeerCards() {
    return playeerCards;
  }

  private List<Card> playeerCards;
  int aceCount;

  public Player(String name, boolean isDealer) {
    this.playerName = name;
    this.isDealer = isDealer;
    playeerCards = new ArrayList<>();
  }

  public String getPlayerName() {
    return playerName;
  }

  public boolean getBlackJack() {
    return isBlackJack;
  }

  public boolean getDealer() {
    return isDealer;
  }

  public boolean getBusted() {
    return isBusted;
  }

  public int getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(int totalCount) {
    this.totalCount = totalCount;
  }

  private boolean checkForAce(Card card) {
    if (Constants.CARD_NAMES.get(0).equals(card.getName())) {
      aceCount++;
      return true;
    }
    return false;
  }

  public void addCard(Card card) {
    int cardVal = Math.min(card.getValue(), 10);
    card.setValue(cardVal);

    playeerCards.add(card);
    reCalculateTotal(card);
  }

  public void reCalculateTotal(Card card) {
    boolean isAce = checkForAce(card);
    if (isAce) {
      totalCount += 11;
    } else {
      totalCount += card.getValue();
    }

    if (playeerCards.size() == 2 && totalCount == 21) {
      isBlackJack = true;
      return;
    }

    while (aceCount > 0 && totalCount > 21) {
      totalCount -= 10;
      aceCount--;
    }

    if (totalCount > 21)
      isBusted = true;
  }

  public int hitCard(Deck deck) {
    Card card = deck.drawACard();
    this.addCard(card);

    if (totalCount > 21) {
      return -1;
    }

    return totalCount;
  }

  public String getResults(int dealerCount) {
    StringBuilder message = new StringBuilder();
    if (isBlackJack) {
      message.append(playerName + " has a blackjack :-), cards: ");
      playeerCards.stream().forEach(card -> message.append(card.getName() + ", "));
      return message.toString();
    }

    if (isBusted) {
      message.append("Player " + playerName + " busted with cards: ");
      playeerCards.stream().forEach(card -> message.append(card.getName() + ", "));
      return message.toString();
    }

    if (dealerCount == -1 && !isBusted) {
      message.append("dealer busted and player " + playerName + " wins with cards: ");
      playeerCards.stream().forEach(card -> message.append(card.getName() + ", "));
      message.append(" and total count: " + totalCount);
      return message.toString();
    }

    if (!isBusted) {
      int result = Integer.compare(totalCount, dealerCount);

      if (result > 0) {
        message.append(
            "Player " + playerName + " wins as the total count " + totalCount + " is greater than dealer count " + dealerCount + " with cards: ");
        playeerCards.stream().forEach(card -> message.append(card.getName() + ", "));

      } else if (result < 0) {
        message.append(
            "Player " + playerName + " looses as the total count " + totalCount + " is less than dealer count " + dealerCount + " with cards: ");
        playeerCards.stream().forEach(card -> message.append(card.getName() + ", "));

      } else if (result == 0) {
        message.append(
            "Player " + playerName + " pushes as the total count " + totalCount + " is equals to the dealer count " + dealerCount + " with cards: ");
        playeerCards.stream().forEach(card -> message.append(card.getName() + ", "));
      }
    }
    return message.toString();
  }
}


