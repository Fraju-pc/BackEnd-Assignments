package Week03;

public class Week3VideosObjects {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(power(3,4));
		
	}
	
	public static int power(int base, int exponent) {
		int result = 1;
		for (int index=1; index <= exponent; index++) {
			result = result*base;
		}
		return result;
	}

}
