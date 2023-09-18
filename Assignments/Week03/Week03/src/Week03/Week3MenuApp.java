package Week03;

import java.util.Scanner;


public class Week3MenuApp {

	static Scanner s = new Scanner(System.in);
	static int addingIndex = 0;
	
	public static void main(String[] args) {
		
		String[] team = new String[5];
		
		int decision = 0;
		
		while(decision != -1) {
			showMenu();
			
			decision = s.nextInt();
			
			if (decision < -1 || decision > 5) {
				System.out.println("Please pick a valid option.");
			} else if(decision == 1) {
				showTeam(team);
			} else if (decision == 2) {
				showTeamMember(team);
			} else if (decision == 3) {
				addTeamMember(team);
			} else if (decision == 4) {
				deleteTeamMember(team);
			} else {
				deleteAllTeamMembers(team);
			}
		}
		
	}
	
	

	
	private static void deleteAllTeamMembers(String[] team) {
		for(int i = 0; i < team.length; i++) {
			team[i] = null;
		}
		
	}


	private static void deleteTeamMember(String[] team) {
		System.out.println("Which team member would you like to delte?");
		int memberToDelete = s.nextInt();
		if(isValid(memberToDelete)) {
			team[memberToDelete] = null;
		}
		
	}


	private static void addTeamMember(String[] team) {
		System.out.print("Enter new member name: ");
		String newMember = s.next();
		if(isValid(addingIndex)) {
			team[addingIndex++] = newMember;
		} 
		
		
		
	}


	private static void showTeamMember(String[] team) {
		System.out.println("Which team member would you like to see? ");
		int teamNumber = s.nextInt();
		if(isValid(teamNumber)) {
			System.out.println(teamNumber + ": " + team[teamNumber]);
		} 
		
	}
	
	public static boolean isValid(int input) {
		if(input >=0 && input <5) {
			return true;
		} else {
			System.out.println("Please enter a valid entry.");
			return false;
		}
	}


	private static void showTeam(String[] team) {
		
		System.out.println("Team: ");
		for (int i = 0; i< team.length; i++ ) {
			System.out.println(i + ": " + team[i]);
		}
	}


	public static void showMenu() {
		System.out.println("Please pick and option or enter -1 to exit.");
		System.out.println("1) Show Team");
		System.out.println("2) Show Team Member");
		System.out.println("3) Add Team Member");
		System.out.println("4) Delete Team Member");
		System.out.println("5) Delete All Team Members");
	}
}
