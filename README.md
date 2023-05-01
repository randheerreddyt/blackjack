# Blackjack java program

## Game rules:
1. A simple textual command-line interface for input/output(nothing graphical needed).
2. Hence the 3 other players can be the same person sitting at the command line, playing three
   hands.
3. No hand-splitting (when one has a pair) or any other complex rules.
   The simple case of getting cards, trying to get as close to 21 but not being over.
4. Players are dealt cards, and can receive another (hit) or stop receiving cards (stand). If they go
   over 21, they are busted.
5. Cards have numeric value, with face cards are value 10. An ace is either 1 or 11 to be used to
   best advantage (highest total without busting 21).
6. Dealer then receives cards and hits or stands based on some strategy.
7. Scoring:
   a. If Dealer has more than 21, dealer is busted, and players not busted win.
   b. If Dealer has 21 or less, then players closer to 21 beat dealer, and dealer beats players
   with same score or less (tie goes to dealer).
8. Flexible Players and Dealer’s (computer’s) strategy.
