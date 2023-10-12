package WarGame;

public class Card {

	//Variables
	private String name;
	private char suit;
	private int value;
	
	//Constructor
	Card(String name, char suit, int value){
		this.name = name;
		this.suit = suit;
		this.value = value;
	}

	//Getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getSuit() {
		return suit;
	}

	public void setSuit(char suit) {
		this.suit = suit;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	//Describe Method
	public void describe() {
		System.out.println(this.name + " of " + this.suit + " - " + this.value);
	}
}
