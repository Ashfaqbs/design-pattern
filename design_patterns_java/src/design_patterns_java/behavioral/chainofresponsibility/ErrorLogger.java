package design_patterns_java.behavioral.chainofresponsibility;

public class ErrorLogger extends Logger {
	public void logMessage(String message, int level) {
		if (level == 3) { // Level 3: Error
			System.out.println("Error Logger: " + message);
		} else if (nextLogger != null) {
			nextLogger.logMessage(message, level);
		}
	}
}