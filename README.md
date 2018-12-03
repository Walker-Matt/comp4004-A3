
A simplistic Poker web application to get experience using selenium as a testing method.

Some code taken from the web (spring documentation), see:

https://github.com/spring-projects/spring-boot/tree/master/spring-boot-samples/spring-boot-sample-websocket-tomcat

Acknowledgments 
Some of this code was taken from the posted blackjack game: https://github.com/Sacredify/4004-SeleniumBlackjack.git

  

IMPORTING AND RUNNING WITH ECLIPSE
----------------------

For the eclipse users, you can:

  1. get Selenium poker from the zip 
  2. `File --> Import --> Maven --> Import existing maven projects --> SeleniumPoker --> pom.xml selected.
  3. From there, you can do whatever. Select the main class (PokerApplication) and run (as application).



Running the tests
-----------------

Tests can be found in /src/test/java/selenium, right click the package and run as junit test, test are as follows
  1. Connections Based test -  tests that a user can connect to the game, open the lobby, start the game, disconnect
  2. Game test- tests that the user can use all of the game options
  3. Ranking test - checking proper ranking of the possible hands: (1 human player):
     * rig their hands a first time with 4 different hands and check ranking
     * rig their hands a 2nd time with 4 other hands and check ranking
     * rig their hands a 3rd time with the 2 other hands not yet tested and two don't cares and check ranking

How To Play the Game
---------------------

  1. Connect to the game
     * if you are the first person to connect you will be assigned as admin
     * As admin you can set the number of players and open the lobby 
  2. Once enough people have connected you can start the game
  3. All UI Players will play first
  4. When it is your turn the game option buttons will be enabled and you can decide to stay or hit. 
     * If you hit you need to click on cards to improve them.
     * When you have clicked all cards you want to improve, click done.
     * Note:  Once you click a card you cannot unselect it.
  5. Once each player has had one turn the system will display the winner and you can disconnect or start the game again.
  
   ## AI Players
   AI Players play using a Strategy design pattern where: 
   1. Strategy 1: 
      * if this AI player has a straight or better, hold (ie do not exchange any card)
      * else this AI player attempts to get a full house by exchanging everything that is not a pair or 3 of a kind
   2. Strategy 2:  
      * IF you are the first player to play, use Option 1 Strategy 1
      * ELSE IF any player before you has 3 visible cards of the same kind: keep your pair(s) if any, and exchange other cards
        * ELSE use  Option 1 Strategy 1
        
   *NOTE: to see what strategy is used you can check the console Log. 


Rigging the Game
-----------------
  1. Connect to the game 
  2. once enough people are connected press rigged_game
  3. the admin will  receive 5 prompts to set all the hands
     * to set a hand type (rank-? suit, rank-? suit, rank-? suit, rank-? suit)
     * each card must be separated by comma
        * the ? represents the rank value where:
           * a = ace
           * k = king
           * q = queen
           * j = jack
           * everything else is its number
        * suits can be:
          * hearts
          * diams
          * spades
          * clubs
   
  4. Once all the players have a card 
     * AI play first, if the AI chooses to hit the admin will be prompted to set which cards to improve
     * Players have a choice to stay or hit: if they hit, press done and you the player will be prompted to set which cards to improve
     * Improving the cards
        * to improve the cards type (index:rank-? suit, )
        * index is the position of the card you want to replace (All cards are sorted by rank)
        * rank and suit are the same as setting the hand;
        * each card must be separated by a comma
  5. once all players have played the results will be displayed
  
  
  
  Winning the Game
  ------------------
  
  Poker hand Ranks are as follows:
   1. Royal Flush
   2. Straight Flush
   3. Four of a kind
   4. Full House
   5. Flush
   6. Straight
   7. Three of a kind
   8. Two Pair
   9. One Pair
   10. High Card
   ** if two players have the same poker hand the player with the highest card in their whole hand wins
   

