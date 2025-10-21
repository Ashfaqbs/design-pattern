package design_patterns_java.behavioral.chainofresponsibility;


public class DebugLogger extends BaseLogger {
    @Override
    protected boolean canHandle(int level) {
        return level == 1; // Handles debug-level logs
    }

    @Override
    protected void write(String message) {
        System.out.println("[DEBUG] " + message);
    }
}
