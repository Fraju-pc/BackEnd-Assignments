package WarGame;

import java.util.ArrayList;
import java.util.List;

public class Player {

	// Variables
	private List<Card> hand = new ArrayList<Card>();
	private int score;
	private String name;

	// Constructor
	public Player(String name) {
		this.name = name;
		this.score = 0;
	}

	// Getters and Setters
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// Describe Method, prints the name score and hand of the player
	public void describe() {
		System.out.println("Player: " + name);
		System.out.println("Score: " + score);
		System.out.println("Hand: ");
		for (Card card : hand) {
			card.describe();
		}
	}

	// Flip method to return the top card
	public Card flip() {
		return hand.remove(0);

	}

	// Draw method to remove card from deck and add to player hand
	public void draw(Deck deck) {
		Card drawnCard = deck.draw();
		hand.add(drawnCard);

	}

	// Method to increment the players score
	public void incrementScore() {
		score++;
	}
}
