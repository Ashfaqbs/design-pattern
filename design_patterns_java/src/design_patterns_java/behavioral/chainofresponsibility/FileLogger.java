package design_patterns_java.behavioral.chainofresponsibility;

public class FileLogger extends Logger {
	public void logMessage(String message, int level) {
		if (level == 2) { // Level 2: Info
			System.out.println("File Logger: " + message);
		} else if (nextLogger != null) {
			nextLogger.logMessage(message, level);
		}
	}
}
