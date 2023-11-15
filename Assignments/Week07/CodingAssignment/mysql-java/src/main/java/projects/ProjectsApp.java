package projects;

//imports
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import projects.entity.Project;
import projects.entity.Category;
import projects.entity.Material;
import projects.entity.Step;
import projects.exception.DbException;
import projects.service.ProjectService;

//class to store menu options in a collection
public class ProjectsApp {
	// @formatter:off
	private List<String> operations = List.of(
			"1: Add a project",
			"2: List projects",
			"3: Select a project",
			"4: Update project details",
			"5: Add material to current project",
			"6: Modify Material in current project",
			"7: Add Step to current project",
			"8: Modify Step in current project",
			"9: Add Category to current project",
			"10: Delete project"
			);
	// @formatter:on

	// Scanner, current project and service variables
	private Scanner scanner = new Scanner(System.in);
	private ProjectService projectService = new ProjectService();
	private Project curProject;

	// main function
	public static void main(String[] args) {

		new ProjectsApp().processUserSelection();

	}

	// Method to determine what is done with menu selections
	private void processUserSelection() {
		boolean done = false;

		while (!done) {
			try {
				int selection = getUserSelection();

				// Switch statement to process user selection
				switch (selection) {
				case -1:
					done = exitMenu();
					break;

				case 1:
					createProject();
					break;

				case 2:
					listProjects();
					break;

				case 3:
					selectProject();
					break;

				case 4:
					updateProjectDetails();
					break;

				case 5:
					addMaterialtoCurrentProject();
					break;

				case 6:
					modifyMatrialInCurrentProject();
					break;

				case 7:
					addStepToCurrentProject();
					break;

				case 8:
					modifyStepInCurrentProject();
					break;

				case 9:
					addCategoryToCurrentProject();
					break;

				case 10:
					deleteProject();
					break;

				default:
					System.out.println("\n" + selection + " is not valid. Try again.");
					break;
				}
			} catch (Exception e) {
				System.out.println("\nError: " + e + " Try Again.");
			}
		}
	}

	// Method to update materials in the current project
	private void modifyMatrialInCurrentProject() {
		if (Objects.isNull(curProject)) {
			System.out.println("\nPlease select a project first");
			return;
		}

		List<Material> material = projectService.fetchMaterial(curProject.getProjectId());

		System.out.println("\nMaterial(s) for current project");
		material.forEach(materials -> System.out.println("  " + materials));

		Integer materialId = getIntInput("Enter ID of Material to modify: ");

		if (Objects.nonNull(materialId)) {
			String materialName = getStringInput("Enter new material name: ");
			Integer numRequired = getIntInput("Enter the new number required: ");
			BigDecimal cost = getDecimalInput("Enter the new cost: ");

			if (Objects.nonNull(materialName)) {
				Material materials = new Material();

				materials.setMaterialId(materialId);
				materials.setMaterialName(materialName);
				materials.setNumRequired(numRequired);
				materials.setCost(cost);

				projectService.modifyMaterial(materials);

				curProject = projectService.fetchProjectById(curProject.getProjectId());
			}
		}

	}

	// Method to update the step in the current project
	private void modifyStepInCurrentProject() {
		if (Objects.isNull(curProject)) {
			System.out.println("\nPlease select a project first");
			return;
		}

		List<Step> steps = projectService.fetchSteps(curProject.getProjectId());

		System.out.println("\nSteps for current project");
		steps.forEach(step -> System.out.println("  " + step));

		Integer stepId = getIntInput("Enter ID of step to modify: ");

		if (Objects.nonNull(stepId)) {
			String stepText = getStringInput("Enter new step text: ");
			if (Objects.nonNull(stepText)) {
				Step step = new Step();

				step.setStepId(stepId);
				step.setStepText(stepText);

				projectService.modifyStep(step);

				curProject = projectService.fetchProjectById(curProject.getProjectId());
			}
		}

	}

	// Method to add a category to the current project
	private void addCategoryToCurrentProject() {
		if (Objects.isNull(curProject)) {
			System.out.println("\nPlease select a project first");
			return;
		}

		List<Category> categories = projectService.fetchCategories();
		categories.forEach(category -> System.out.println("  " + category.getCategoryName()));

		String category = getStringInput("Enter the category to add: ");

		if (Objects.nonNull(category)) {

			projectService.addCategoryToProject(curProject.getProjectId(), category);

			curProject = projectService.fetchProjectById(curProject.getProjectId());
		}

	}

	// Method to add a step to the current project
	private void addStepToCurrentProject() {
		if (Objects.isNull(curProject)) {
			System.out.println("\nPlease select a project first");
			return;
		}

		String stepText = getStringInput("Enter the step text: ");

		if (Objects.nonNull(stepText)) {
			Step step = new Step();

			step.setProjectId(curProject.getProjectId());
			step.setStepText(stepText);

			projectService.addStep(step);

			curProject = projectService.fetchProjectById(curProject.getProjectId());
		}

	}

	// Method to add a material to the current project
	private void addMaterialtoCurrentProject() {

		if (Objects.isNull(curProject)) {
			System.out.println("\nPlease select a project first");
			return;
		}

		String name = getStringInput("Enter the material name: ");
		Integer numRequired = getIntInput("Enter the number required: ");
		BigDecimal cost = getDecimalInput("Enter the cost: ");

		Material material = new Material();
		material.setProjectId(curProject.getProjectId());
		material.setMaterialName(name);
		material.setNumRequired(numRequired);
		material.setCost(cost);

		projectService.addMaterial(material);

		curProject = projectService.fetchProjectById(curProject.getProjectId());

	}

	// Method to delete a project
	private void deleteProject() {
		listProjects();
		Integer projectId = getIntInput("Enter the ID of the project to delete: ");

		projectService.deleteProject(projectId);

		System.out.println("Project " + projectId + " was successfully delted.");

		if (Objects.nonNull(curProject) && curProject.getProjectId().equals(projectId)) {
			curProject = null;
		}

	}

	// Method to update the base project details
	private void updateProjectDetails() {
		if (Objects.isNull(curProject)) {
			System.out.println("\nPlease select a project first");
			return;
		}

		String projectName = getStringInput("Enter the project name [" + curProject.getProjectName() + "]");
		BigDecimal estimatedHours = getDecimalInput(
				"Enter the estimated hours [" + curProject.getEstimatedHours() + "]");
		BigDecimal actualHours = getDecimalInput("Enter the actual hours [" + curProject.getActualHours() + "]");
		Integer difficulty = getIntInput("Enter the project diffculty (1-5) [" + curProject.getDifficulty() + "]");
		String notes = getStringInput("Enter the project notes [" + curProject.getNotes() + "]");

		Project project = new Project();

		project.setProjectId(curProject.getProjectId());
		project.setProjectName(Objects.isNull(projectName) ? curProject.getProjectName() : projectName);
		project.setEstimatedHours(Objects.isNull(estimatedHours) ? curProject.getEstimatedHours() : estimatedHours);
		project.setActualHours(Objects.isNull(actualHours) ? curProject.getActualHours() : actualHours);
		project.setDifficulty(Objects.isNull(difficulty) ? curProject.getDifficulty() : difficulty);
		project.setNotes(Objects.isNull(notes) ? curProject.getNotes() : notes);

		projectService.modifyProjectDetails(project);

		curProject = projectService.fetchProjectById(curProject.getProjectId());

	}

	// Method to select a project to work with
	private void selectProject() {
		listProjects();
		Integer projectId = getIntInput("Enter a project ID to select a project");

		// clears out an already selected project
		curProject = null;

		// selects user input as project
		curProject = projectService.fetchProjectById(projectId);

	}

	// Method to list out all projects currently in the database
	private void listProjects() {
		List<Project> projects = projectService.fetchAllProjects();

		System.out.println("\nProjects: ");

		projects.forEach(project -> System.out.println(" " + project.getProjectId() + ": " + project.getProjectName()));

	}

	// Method to gather user input to create SQL query
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

	// takes in user input and converts to Big Decimal
	private BigDecimal getDecimalInput(String prompt) {
		String input = getStringInput(prompt);

		if (Objects.isNull(input)) {
			return null;
		}
		try {
			return new BigDecimal(input).setScale(2);
		} catch (NumberFormatException e) {
			throw new DbException(input + " is not a valid dcimal number.");
		}
	}

	// method to end program
	private boolean exitMenu() {
		System.out.println("\n Exiting Menu");
		return true;
	}

	// prompt to get user input
	private int getUserSelection() {
		printOperations();

		Integer input = getIntInput("\nEnter a menu selection");

		return Objects.isNull(input) ? -1 : input;
	}

	// takes in user input and converts to integer
	private Integer getIntInput(String prompt) {
		String input = getStringInput(prompt);

		if (Objects.isNull(input)) {
			return null;
		}
		try {
			return Integer.valueOf(input);
		} catch (NumberFormatException e) {
			throw new DbException(input + " is not a valid number.");
		}
	}

	// takes in user input as a string
	private String getStringInput(String prompt) {
		System.out.println(prompt + ": ");
		String input = scanner.nextLine();

		return input.isBlank() ? null : input.trim();

	}

	// Prints out the menu options
	private void printOperations() {
		System.out.println("\nThese are the available selections. Press the Enter key to quit:");
		operations.forEach(line -> System.out.println(" " + line));

		if (Objects.isNull(curProject)) {
			System.out.println("\nYou are not working with a Project.");
		} else {
			System.out.println("\nYou are working with Project: " + curProject);
		}
	}

}
