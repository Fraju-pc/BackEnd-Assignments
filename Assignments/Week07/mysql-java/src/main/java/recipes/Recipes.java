package recipes;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import recipes.entity.Recipe;

//import java.sql.Connection;


import recipes.exception.DbException;
import recipes.service.RecipeService;

public class Recipes {

	private Scanner scanner = new Scanner(System.in);
	private RecipeService recipeService = new RecipeService();

	// @formatter:off
	private List<String> operations = List.of(
			"1. Create and populate all tables",
			"2. Add a recipe"
			);
	//@formatter:on

	public static void main(String[] args) {

		new Recipes().displayMenu();
	}

	private void displayMenu() {
		boolean done = false;
		
		try {
		while(!done) {
			int operation = getOperation();
			
			switch (operation) {
			case -1:
				done = exitMenu();
				break;
				
			case 1:
				createTables();
				break;
				
			case 2:
				addRecipe();
				break;
				
			default:
				System.out.println("\n" + operation + " is not valid. Try again.");
				break;
				}
			} 
		}
		catch (Exception e) {
			System.out.println("\n Error: " +e.toString() + "try again.");
		}
		
		
	}

	private void addRecipe() {
		String name = getStringInput("Enter the recipe name");
		String notes = getStringInput("Enter the recipe notes");
		Integer numServings = getInput("Enter the number of servings");
		Integer prepMinutes = getInput("Enter the prep time in minutes");
		Integer cookMinutes = getInput("Enter the cook time in minutes");
		
		LocalTime prepTime = minutesToLocalTime(prepMinutes);
		LocalTime cookTime = minutesToLocalTime(cookMinutes);
		
		Recipe recipe = new Recipe();
		
		recipe.setRecipeName(name);
		recipe.setNotes(notes);
		recipe.setNumServings(numServings);
		recipe.setPrepTime(prepTime);
		recipe.setCookTime(cookTime);
		
		Recipe dbRecipe = recipeService.addRecipe(recipe);
		System.out.println("You added this recipe:\n" + dbRecipe);
	}

	private LocalTime minutesToLocalTime(Integer numMinutes) {
		int min = Objects.isNull(numMinutes) ? 0 : numMinutes;
		int hours = min/60;
		int minutes = min %60;
		
		return LocalTime.of(hours, minutes);
	}

	private void createTables() {
		recipeService.createAndPopulateTables();
		System.out.println("\n Tables created and populated!");
	}

	private boolean exitMenu() {
		System.out.println("\n Exiting Menu");
		return true;
	}

	private int getOperation() {
		printOperation();
		Integer op = getInput("\n Enter an operation number (press enter to quit) ");
		return Objects.isNull(op) ? -1 : op;
	}

	private Integer getInput(String prompt) {
		String input = getStringInput(prompt);

		if (Objects.isNull(input)) {
			return null;
		}
		try {
			return Integer.parseInt(input);
		} catch (NumberFormatException e) {
			throw new DbException(input + " is not a valid number.");
		}
	}

	private Double getDoubleInput(String prompt) {
		String input = getStringInput(prompt);

		if (Objects.isNull(input)) {
			return null;
		}
		try {
			return Double.parseDouble(input);
		} catch (NumberFormatException e) {
			throw new DbException(input + " is not a valid number.");
		}
	}

	private String getStringInput(String prompt) {
		System.out.print(prompt + ": ");
		String line = scanner.nextLine();

		return line.isBlank() ? null : line.trim();
	}

	private void printOperation() {
		System.out.println();
		System.out.println("Here's what you can do: ");

		operations.forEach(op -> System.out.println(op));

	}

}