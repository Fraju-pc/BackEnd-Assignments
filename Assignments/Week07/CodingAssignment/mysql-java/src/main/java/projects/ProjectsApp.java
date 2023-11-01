package projects;

//imports
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import projects.entity.Project;
import projects.exception.DbException;
import projects.service.ProjectService;

//class to store menu options in a collection
public class ProjectsApp {
	// @formatter:off
	private List<String> operations = List.of(
			"1: Add a project"
			);
	// @formatter:on

	//Scanner and service variables
	private Scanner scanner = new Scanner(System.in);
	private ProjectService projectService = new ProjectService();
	
	//main function
	public static void main(String[] args) {
	
		new ProjectsApp().processUserSelection();
		
	}

	//Method to determine what is done with menu selections
	private void processUserSelection() {
		boolean done = false;
		
		while(!done) {
			try {
				int selection = getUserSelection();
				
				switch (selection) {
				case -1:
					done = exitMenu();
					break;
								
					case 1:
					createProject();
					break;
												
				default:
					System.out.println("\n" + selection + " is not valid. Try again.");
					break;
					}
			} catch(Exception e){
				System.out.println("\nError: " + e + " Try Again.");
			}
		}
	}

	//Method to gather user input to create SQL query
	private void createProject() {
		String projectName = getStringInput("Enter the project name");
		BigDecimal estimatedHours = getDecimalInput("Enter the estimated hours");
		BigDecimal actualHours = getDecimalInput("Enter the actual hours");
		Integer difficulty = getIntInput("Enter the project difficulty (1-5)");
		String notes = getStringInput("Enter the project notes");
		
		Project project = new Project();
		
		project.setProjectName(projectName);
		project.setEstimatedHours(estimatedHours);
		project.setActualHours(actualHours);
		project.setDifficulty(difficulty);
		project.setNotes(notes);
		
		Project dbProject = projectService.addProject(project);
		System.out.println("You have successfully created project: " + dbProject);
		
	}

	//takes in user input and converts to Big Decimal
	private BigDecimal getDecimalInput(String prompt) {
		String input = getStringInput(prompt);
		
		if(Objects.isNull(input)) {
			return null;
		}
		try {
			return new BigDecimal(input).setScale(2);
		}
		catch(NumberFormatException e){
			throw new DbException(input + " is not a valid dcimal number.");
		}
	}

	//method to end program
	private boolean exitMenu() {
		System.out.println("\n Exiting Menu");
		return true;
	}

	//prompt to get user input
	private int getUserSelection() {
		printOperations();
		
		Integer input = getIntInput("Enter a menu selection");
		
		return Objects.isNull(input) ? -1 : input;
	}

	//takes in user input and converts to integer
	private Integer getIntInput(String prompt) {
		String input = getStringInput(prompt);
		
		if(Objects.isNull(input)) {
			return null;
		}
		try {
			return Integer.valueOf(input);
		}
		catch(NumberFormatException e){
			throw new DbException(input + " is not a valid number.");
		}
	}

	//takes in user input as a string
	private String getStringInput(String prompt) {
		System.out.println(prompt + ": ");
		String input = scanner.nextLine();
		
		return input.isBlank() ? null : input.trim();
		
	}

	//Prints out the menu options
	private void printOperations() {
		System.out.println("\nThese are the available selections. Press the Enter key to quit:");
		operations.forEach(line -> System.out.println(" " + line));
	}

}
