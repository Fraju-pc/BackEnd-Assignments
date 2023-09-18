package Week03;

public class Week03VideoFollowAlong {

	public static void main(String[] args) {
		// Arrays
		String[] students = new String[3];
		
		students[0] = "Tom Riddle";
		students[1] = "Luke Skywalker";
		students[2] = "Master Chief";
		
		System.out.println(students[0]);
		System.out.println(students[1]);
		System.out.println(students[2]);
		
		for(int i = 0; i < students.length; i++) {
			System.out.println(students[i]);
		}
		for(String student : students) {
			System.out.println(student);
		}
		
		String firstName = "Mary";
		String lastName = "Brown";
		int[] assignments = {100, 79, 80, 90, 100};
		
		System.out.println("Student: " + firstName + " " + lastName);
		System.out.println("Grades:");
		for(int i = 0; i < assignments.length; i++) {
			System.out.println((i+1) + ": " + assignments[i]);
		}
		
		int[] multiplesOf3 = new int[10];
		
		for (int i = 1; i <= multiplesOf3.length; i++) {
			multiplesOf3[i - 1] = i * 3;
			System.out.println("number: " + multiplesOf3[i - 1]);
		}
		
		String fullName = createFullName(firstName, lastName);
		System.out.println(fullName);
		
		String fullName2 = createFullName("Gary", "Busey");
		System.out.println(fullName2);
		
		double[] assignments2 = {100, 79, 80, 90, 100};
		
		System.out.println(sumArray(assignments));
		System.out.println(averageArray(assignments2));
		System.out.println(multiplyString("Hello", 3));
		
	}
	//Full name Method
	public static String createFullName(String x, String y) {
		return  x + " " + y; 
	}
	//Add up Array ints
	public static int sumArray (int[] numbers) {
		int sum = 0;
		
		for(int number : numbers) {
			sum += number;
			
		}
		return sum;
	}
	//Average of an Array of doubles
		public static double averageArray (double[] numbers) {
			double sum = 0;
			double avg = 0;
			for(double number : numbers) {
				sum += number;
				
			}
			avg = sum/numbers.length +1;
			return avg;
		}
	//String Method that repeats the string num amount of times
		public static String multiplyString(String str, int num) {
			String result = "";
			for (int i = 0; i < num; i++) {
				result += str;
			}
			return result;
		}

}
