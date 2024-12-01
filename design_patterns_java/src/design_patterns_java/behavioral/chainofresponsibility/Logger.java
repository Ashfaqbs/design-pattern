package design_patterns_java.behavioral.chainofresponsibility;

public abstract class Logger {
	protected Logger nextLogger;

	public void setNextLogger(Logger nextLogger) {
		this.nextLogger = nextLogger;
	}

	public abstract void logMessage(String message, int level);
}
