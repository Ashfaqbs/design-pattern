package design_patterns_java.behavioral.chainofresponsibility;

public class ConsoleLogger extends Logger {
	public void logMessage(String message, int level) {
		if (level == 1) { // Level 1: Debug
			System.out.println("Console Logger: " + message);
		} else if (nextLogger != null) {
			nextLogger.logMessage(message, level);
		}
	}
}




