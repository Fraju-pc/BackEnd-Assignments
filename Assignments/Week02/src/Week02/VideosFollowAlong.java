package Week02;

import java.util.Scanner;

public class VideosFollowAlong {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter Your Name: ");
		String name = sc.nextLine();
		System.out.println("Hello! " + name);
		
		int selection = 0;
		double total = 0;
		
		while (selection !=4) {
		System.out.println("Select an Option: ");
		System.out.println("1) Candybar: 1.99");
		System.out.println("2) Cheese: 6.00");
		System.out.println("3) Soccer Ball: 15.00");
		System.out.println("4) Checkout");
		
		selection = sc.nextInt();
		
		
		
		switch(selection) {
		case 1:
			total += 1.99;
			break;
		case 2:
			total += 6.00;
			break;
		case 3:
			total += 15.00;
			break;
		case 4:
			break;
		default:
			System.out.println("Please pick a valid option.");
			
		}
		}
	System.out.println("Your current total is: " + total);
	
	}

}
