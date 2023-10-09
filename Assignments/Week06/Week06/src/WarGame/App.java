package WarGame;

public class App {

	public static void main(String[] args) {
		
		//Setup and shuffle the Deck
        Deck deck = new Deck();
        deck.shuffle();
        //Setup the 2 players
        Player player1 = new Player("Greg");
        Player player2 = new Player("Larry");

        //Deal Cards
        for (int i = 0; i < 52; i++) {
            if (i % 2 == 0) {
                player1.draw(deck);
            } else {
                player2.draw(deck);
            }
        }

        //Loop 26 times to play the hands
        for (int i = 0; i < 26; i++) {
            Card card1 = player1.flip();
            Card card2 = player2.flip();
            
            //Header for each turn
            System.out.println("\nTurn " + (i + 1));
            System.out.println("---------");
            
            //Cards played from the players hands
            System.out.print(player1.getName() + " flips: " );
            card1.describe();
            System.out.print(player2.getName() + " flips: " );
            card2.describe();

            //Card values are compared and the higher value awards 
            //a point to that player or none if tied
            if (card1.getValue() > card2.getValue()) {
                player1.incrementScore();
                System.out.println(player1.getName() + " receives a point");
            } else if (card1.getValue() < card2.getValue()) {
                player2.incrementScore();
                System.out.println(player2.getName() + " receives a point");
            } else {
                System.out.println("It's a tie, no points awarded");
            }
        }

        //Compare and output final scores
        int player1Score = player1.getScore();
        int player2Score = player2.getScore();
        System.out.println("\nFinal Score:");
        System.out.println(player1.getName() + ": " + player1Score);
        System.out.println(player2.getName() + ": " + player2Score);

        //Determine and output the winner
        if (player1Score > player2Score) {
            System.out.println(player1.getName() + " wins!");
        } else if (player1Score < player2Score) {
            System.out.println(player2.getName() + " wins!");
        } else {
            System.out.println("It's a draw!");
        }
	}

}
