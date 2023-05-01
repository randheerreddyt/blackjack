package org.example;

import java.io.Serializable;

public class Card implements Serializable {
  private String name;

  private int value;
  private String symbol;

  Card(int value, String symbol) {
    this.value = value;
    this.symbol = symbol;
    this.name = getCardName(value);
  }

  public int getCardValue(String name) {
    return Constants.CARD_NAMES.indexOf(name) + 1;
  }

  public String getCardName(int cardValue) {
    return Constants.CARD_NAMES.get(cardValue - 1);
  }

  public String getName() {
    return name;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

}
