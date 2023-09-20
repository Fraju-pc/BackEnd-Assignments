package Week04;

import java.util.ArrayList;
import java.util.List;

public class Week04VideoFollowAlong {

	public static void main(String[] args) {
		String firstName = "Tom";
		String lastName = "Brady";
		
		System.out.println(firstName.concat(lastName));
		
		StringBuilder fullName = new StringBuilder("Patrick");
		fullName.append(" Corcoran");
		System.out.println(fullName.toString());
		
		System.out.println(fullName.charAt(5));
		
		String middleInitial = "B";
		String space = " ";

		System.out.println(firstName);
		System.out.println(lastName);

		StringBuilder sb = new StringBuilder();

		
		sb.append(firstName);
		sb.append(space);
		sb.append(lastName);
		System.out.println(sb.toString());

		
		sb.insert(firstName.length()+1, middleInitial + space);
		System.out.println(sb.toString());
		
		List<String> sports = new ArrayList<String>();
		sports.add("Baseball");
		sports.add("Hockey");
		sports.add("Football");
		//sports.remove(1);
		
		for (int i = 0; i< sports.size(); i++) {
			System.out.println(sports.get(i));
		}
		for(String sport : sports) {
			System.out.println(sport);
		}
		
		List<Integer> numbers = new ArrayList<Integer>();
		
		System.out.println(numbers.isEmpty());
		
	}

}
