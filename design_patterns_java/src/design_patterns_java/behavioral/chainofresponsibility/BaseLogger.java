package design_patterns_java.behavioral.chainofresponsibility;


public abstract class BaseLogger {
    protected BaseLogger nextLogger;

    public void setNext(BaseLogger nextLogger) {
        this.nextLogger = nextLogger;
    }

    public void log(String message, int level) {
        if (canHandle(level)) {
            write(message);
        } else if (nextLogger != null) {
            nextLogger.log(message, level);
        }
    }

    protected abstract boolean canHandle(int level);
    protected abstract void write(String message);
}
