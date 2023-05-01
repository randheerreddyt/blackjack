package org.example;

import java.util.Arrays;
import java.util.List;

public class Constants {

  public static final int INITIAL_CAPACITY = 52;

  public static final int PLAYER_COUNT = 3;

  protected static final List<String> CARD_NAMES =
      Arrays.asList("ACE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE", "TEN",
          "JACK", "QUEEN", "KING");

  private Constants() {
  }
}
