package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
  static Deck deck;

  public static void main(String[] args) {

    int round = 0;
    // Initialize the deck
    System.out.println("Welcome to blackjack, let's play");
    deck = new Deck();
    deck.initializeGame();
    deck.shuffleCards();

    Scanner scanner = new Scanner(System.in);

    // Input player names
    List<Player> playersList = new ArrayList<>();
    for (int i = 1; i <= Constants.PLAYER_COUNT; i++) {
      System.out.println("Please, enter player " + i + " name");
      String playerName = scanner.nextLine();
      playersList.add(new Player(playerName, false));
    }

    // Also add dealer as a player
    playersList.add(new Player("Dealer", true));

    // draw cards for each player in a sequence in clockwise direction
    while (round < 2) {
      round++;
      for (int i = 0; i < Constants.PLAYER_COUNT + 1; i++) {
        StringBuilder message = new StringBuilder();

        Player player = playersList.get(i);
        Card card = deck.drawACard();
        player.addCard(card);
        if (player.getBlackJack() && !player.getDealer()) {
          message.append(
              "\n Player " + player.getPlayerName() + " wins!, has a black jack with cards: ");
          player.getPlayeerCards().stream().forEach(c -> message.append(c.getName() + ", "));
          System.out.println(message);
        }
        if (player.getDealer() && player.getBlackJack()) {
          System.out.println(
              "Dealer has a black jack all the players who dont have blackjack looses");
          return;
        }
      }
    }

    // ask each player to hit or stay
    for (int i = 0; i < Constants.PLAYER_COUNT; i++) {
      StringBuilder message = new StringBuilder();
      message.append("***********************************");

      Player player = playersList.get(i);
      if (!player.getBlackJack()) {
        message.append(
            "\n player " + player.getPlayerName() + " total is now " + player.getTotalCount() + " with cards: ");
        player.getPlayeerCards().stream().forEach(card -> message.append(card.getName() + ", "));
        message.append(
            " and dealer has an open card of value " + playersList.get(Constants.PLAYER_COUNT)
                .getPlayeerCards().get(1).getValue() + " , do you want to hit(1.) or stand(2.)?");
        System.out.println(message);
        askPlayerForHitOrStay(scanner, player);
      }
    }

    System.out.println("***********************************");

    // draw cards for dealer until the dealer count is >=17
    hitForDealerUntilSeventeen(playersList.get(3));

    System.out.println("***********************************");

    // print result for each player at the end of one play
    for (int i = 0; i < Constants.PLAYER_COUNT; i++) {
      Player player = playersList.get(i);
      String result = player.getResults(playersList.get(3).getTotalCount());
      System.out.println(result);
    }
  }


  public static void hitForDealerUntilSeventeen(Player player) {
    StringBuilder message = new StringBuilder();
    int total = player.getTotalCount();
    message.append("\n Dealers total is now " + total);


    if (player.getDealer() && total < 17) {
      message.append("\n Dealer is drawing a card since the count is <17 with cards: ");
      player.getPlayeerCards().stream().forEach(card -> message.append(card.getName() + ", "));

      total = player.hitCard(deck);
      if (total == -1) {
        message.append("\n Dealer has busted");
        player.setTotalCount(-1);
        System.out.println(message);
        return;
      }
    }

    message.append("\n Dealers total is now " + total + " with cards: ");
    player.getPlayeerCards().stream().forEach(card -> message.append(card.getName() + ", "));
    System.out.println(message);

    if (total < 17) {
      hitForDealerUntilSeventeen(player);
    }
  }

  public static void askPlayerForHitOrStay(Scanner scanner, Player player) {
    StringBuilder message = new StringBuilder();

    String input = scanner.nextLine();
    if (input.equals("1")) {
      int total = player.hitCard(deck);
      if (total == -1) {
        message.append("\n player " + player.getPlayerName() + " have busted with cards: ");
        player.getPlayeerCards().stream().forEach(card -> message.append(card.getName() + ", "));
        System.out.println(message.toString());
        return;
      }
      message.append(
          "\n player " + player.getPlayerName() + " total is now " + total + " with cards:");
      player.getPlayeerCards().stream().forEach(card -> message.append(card.getName() + ", "));
      message.append(", do you want to hit(1.) or stand(2.)?");
      System.out.println(message);
      askPlayerForHitOrStay(scanner, player);
    } else if (input.equals("2")) {
      message.append(
          "\n player " + player.getPlayerName() + " is staying on " + player.getTotalCount() + " with cards: ");
      player.getPlayeerCards().stream().forEach(card -> message.append(card.getName() + ", "));
      message.append("good luck!");
      System.out.println(message);
    }
  }
}

