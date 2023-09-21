package Week04;

public class Week04CodingAssignment {

	public static void main(String[] args) {
		
//1. Create an array of int called ages that contains the following values: 3, 9, 23, 64, 2, 8, 28, 93.
//
		System.out.println("1.--------------------------------------------");
		int[] ages = {3, 9, 23, 64, 2, 8, 28, 93};
		
		
//a. Programmatically subtract the value of the first element in the array from the value in the last element of the array (i.e. do not use ages[7] in your code). Print the result to the console.  
//
		System.out.println("1a.-------------------------------------------");
		int subtract = ages[ages.length-1] - ages[0];
		System.out.println(subtract);
		
//b. Create a new array with 9 elements (one more than the ages array)  Repeat the step above to ensure it is dynamic (works for arrays of different lengths).
//	
		System.out.println("1b.-------------------------------------------");
		int[] ages2 = {3, 8, 9, 23, 64, 2, 8, 28, 93};
		
		int subtract2 = ages2[ages2.length-1] - ages2[0];
		System.out.println(subtract2);
		
//c. Use a loop to iterate through the array and calculate the average age. Print the result to the console.
//
		System.out.println("1c.-------------------------------------------");
		int sumc = 0;
		for(int age : ages) {
			sumc += age;
		}
		System.out.println(sumc/ages.length);
//2. Create an array of String called names that contains the following values: “Sam”, “Tommy”, “Tim”, “Sally”, “Buck”, “Bob”.
//
		System.out.println("2.--------------------------------------------");
		String[] names = {"Sam", "Tommy", "Tim", "Sally", "Buck", "Bob"};
		
//a. Use a loop to iterate through the array and calculate the average number of letters per name. Print the result to the console.
//
		System.out.println("2a.-------------------------------------------");
		int length = 0;
		for (int i = 0; i < names.length; i++) {
			length += names[i].length();
		}
		System.out.println(length/names.length);
//b. Use a loop to iterate through the array again and concatenate all the names together, separated by spaces, and print the result to the console.
//
		System.out.println("2b.-------------------------------------------");
		String tots = "";
		for (int k = 0; k < names.length; k++) {
			tots += names[k];
			
		}
		System.out.println(tots);
//3. How do you access the last element of any array?
		System.out.println("3.--------------------------------------------");
		System.out.println("array.length-1;");
		
//4. How do you access the first element of any array?
		System.out.println("4.--------------------------------------------");
		System.out.println("array[0];");
		
//5. Create a new array of int called nameLengths. Write a loop to iterate over the previously created names array and add the length of each name to the nameLengths array.
//
		System.out.println("5.--------------------------------------------");
		int[] nameLengths = new int[6];
		
		for (int i = 0; i < names.length; i++) {
			nameLengths[i] = (names[i].length());
		}
		
		for (int i = 0; i < names.length; i++) {
			System.out.println(nameLengths[i]);
		}
		
		
//6. Write a loop to iterate over the nameLengths array and calculate the sum of all the elements in the array. Print the result to the console.
//
		System.out.println("6.--------------------------------------------");
		int sum = 0;
		
		for (int i = 0; i < nameLengths.length; i++) {
			sum += nameLengths[i];
		}
		System.out.println(sum);
		
//7. Write a method that takes a String, word, and an int, n, as arguments and returns the word concatenated to itself n number of times. (i.e. if I pass in “Hello” and 3, I expect the method to return “HelloHelloHello”).
//
		System.out.println("7.--------------------------------------------");
		System.out.println(conCatWord("Hello", 3));
		
//8. Write a method that takes two Strings, firstName and lastName, and returns a full name (the full name should be the first and the last name as a String separated by a space).
//
		System.out.println("8.--------------------------------------------");
		System.out.println(fullName("Patrick", "Corcoran"));
		
//9. Write a method that takes an array of int and returns true if the sum of all the ints in the array is greater than 100.
//
		System.out.println("9.--------------------------------------------");
		System.out.println(greaterThan100(ages));
		
//10. Write a method that takes an array of double and returns the average of all the elements in the array.
//
		System.out.println("10.--------------------------------------------");
		double[] aged = {3, 9, 23, 64, 2, 8, 28, 93};
		System.out.println(getAverage(aged));
		
//11. Write a method that takes two arrays of double and returns true if the average of the elements in the first array is greater than the average of the elements in the second array.
//
		System.out.println("11.--------------------------------------------");
		double[] aged2 = {3, 8, 9, 23, 64, 2, 8, 28, 93};
		double total1 = getAverage(aged);
		double total2 = getAverage(aged2);		
		System.out.println(isFirstGreater(total1, total2));
		
//12. Write a method called willBuyDrink that takes a boolean isHotOutside, and a double moneyInPocket, and returns true if it is hot outside and if moneyInPocket is greater than 10.50.
//
		System.out.println("12.--------------------------------------------");
		boolean isHotOutside = true;
		double moneyInPocket = 12.00;
		System.out.println(willBuyDrink(isHotOutside, moneyInPocket));
		
//13. Create a method of your own that solves a problem. In comments, write what the method does and why you created it.
		System.out.println("13.--------------------------------------------");
		boolean hasLicense = true;
		int driverAge = 17;
		System.out.println(canIDrive(hasLicense, driverAge));
	}

	private static boolean canIDrive(boolean hasLicense, int driverAge) {
		if (hasLicense == true && driverAge >=16) {
			return true;
		} else {
			return false;
		}
	}

	private static boolean willBuyDrink(boolean isHotOutside, double moneyInPocket) {
		if(isHotOutside == true && moneyInPocket>10.50) {
			return true;
		} else {
			return false;
		}
	}

	private static boolean isFirstGreater(double total1, double total2) {
		
		if (total1 > total2) {
		return true;
		} else {
			return false;
		}
	}

	private static double getAverage(double[] aged) {
		double sum = 0;
		double average = 0;
		
		for (int i = 0; i<aged.length; i++) {
			sum+= aged[i];
		}
		average = sum / aged.length;
		return average;
	}

	private static boolean greaterThan100(int[] ages) {
		int sum = 0;
		for (int i = 0; i < ages.length; i++) {
			sum += ages[i];
		}
		if (sum > 100) {
			return true;
		} else {
			return false;
		}
		
	}

	private static String fullName(String firstName, String lastName) {
		
		String name = firstName + " " + lastName;
		return name;
	}

	private static String conCatWord(String string, int j) {
		
		String result = "";
		
		for (int i=0; i < j; i++) {
			result += string;
		}
		return result;
	}

}
