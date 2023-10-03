package Week05CodingAssignment;

//Class for the Asterisk Logger
class AsteriskLogger implements Logger {
	
	//Log output method
    @Override
    public void log(String message) {
        System.out.println("***" + message + "***");
    }

    //error output method
    @Override
    public void error(String message) {
        System.out.println("******************");
        System.out.println("***Error: " + message + "***");
        System.out.println("******************");
    }
}
