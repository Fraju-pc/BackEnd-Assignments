package Week05;

import java.util.ArrayList;
import java.util.List;

public class Team {
	//Attributes
	private List<Player> players = new ArrayList<Player>();
	private String name;
	
	//Constructor
	public Team(String name) {
		this.setName(name);
	}
	
	//Public Methods
	public void describe() {
		System.out.println("Team Name: " + name);
		for(Player player : players) {
			player.describe();		}
	}
	
	public void addPlayer(Player player) {
		players.add(player);
	}
	
	public void removePlayer(String playerName) {
		for(Player player : players) {
			if(player.getName().equals(playerName)) {
				players.remove(player);
			}
		}
	}

	//Getters and Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}