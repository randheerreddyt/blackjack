package org.example;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.shuffle;

public class Deck {

  private final List<Card> deck;
  private int cardCounter;

  public Deck() {
    deck = new ArrayList<>(Constants.INITIAL_CAPACITY);
    initializeGame();
  }

  public void initializeGame() {
    cardCounter = Constants.INITIAL_CAPACITY;
    for (int i = 1; i <= 13; i++) {
      deck.add(new Card(i, "diamond"));
      deck.add(new Card(i, "clubs"));
      deck.add(new Card(i, "spades"));
      deck.add(new Card(i, "heart"));
    }
  }

  public Card drawACard() {
    if (cardCounter == 0) {
      System.out.println("No cards left in the deck");
    }

    Card card = deck.get(0);
    deck.remove(card);
    cardCounter--;
    return card;
  }

  public void shuffleCards() {
    shuffle(deck);
  }
}
