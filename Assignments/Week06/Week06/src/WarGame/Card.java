package WarGame;

public class Card {

	//Variables
	private String name;
	private String suit;
	private int value;
	
	//Constructor
	Card(String name, String suit, int value){
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

	public String getSuit() {
		return suit;
	}

	public void setSuit(String suit) {
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
