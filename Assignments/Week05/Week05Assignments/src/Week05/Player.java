package Week05;

public class Player {

	//Attributes
	private String name;
	private String position;
	private String specialty;
	
	//Constructor
	public Player(String name, String position, String specialty) {
		this.name = name;
		this.position = position;
		this.specialty = specialty;
		
	}

	//Public Methods
	public void describe() {
		System.out.println("Player: " + name + " \t Position: " + position + " \t Specialty: " + specialty);
	}
	
	//Getters and Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	
	
}
