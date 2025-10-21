package design_patterns_java.behavioral.chainofresponsibility;


public class CriticalLogger extends BaseLogger {
    @Override
    protected boolean canHandle(int level) {
        return level == 3; // Handles critical/error logs
    }

    @Override
    protected void write(String message) {
        System.out.println("[CRITICAL] " + message);
    }
}
