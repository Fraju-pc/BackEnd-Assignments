package Week05CodingAssignment;

public class App {
	
    public static void main(String[] args) {
    	
        // Instantiate instances of the logger classes
        Logger asteriskLogger = new AsteriskLogger();
        Logger spacedLogger = new SpacedLogger();

        // Test log and error methods for asterisk
        asteriskLogger.log("Hello");
        System.out.println("\n");
        asteriskLogger.error("Hello");
        System.out.println("\n");
        
     // Test log and error methods for spaced
        spacedLogger.log("World");
        System.out.println("\n");
        spacedLogger.error("World");
    }
}
