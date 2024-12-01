package design_patterns_java.behavioral.chainofresponsibility;

public class Client {
	public static void main(String[] args) {
		Logger consoleLogger = new ConsoleLogger();
		Logger fileLogger = new FileLogger();
		Logger errorLogger = new ErrorLogger();

		// Set up the chain
		consoleLogger.setNextLogger(fileLogger);
		fileLogger.setNextLogger(errorLogger);

		// Send different types of requests
		consoleLogger.logMessage("This is a Debug message", 1);
		consoleLogger.logMessage("This is an Info message", 2);
		consoleLogger.logMessage("This is an Error message", 3);
	}
}
