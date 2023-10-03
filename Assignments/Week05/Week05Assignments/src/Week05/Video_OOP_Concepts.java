package Week05;

public class Video_OOP_Concepts {

	public static void main(String[] args) {
		
		Student student1 = new Student();
		student1.setFirstName("Tom");
		student1.setLastName("Brady");
		student1.setGradeLevel(12);
		student1.setPhoneNumber("774-312-1212");
		
		student1.introduce();
		
		Student student2 = new Student("Patrick", "Corcoran", "773-312-0713", 11);
		student2.introduce();
				
	}

}
