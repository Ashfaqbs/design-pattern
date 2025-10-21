package design_patterns_java.behavioral.chainofresponsibility;


public class InfoLogger extends BaseLogger {
    @Override
    protected boolean canHandle(int level) {
        return level == 2; // Handles info-level logs
    }

    @Override
    protected void write(String message) {
        System.out.println("[INFO] " + message);
    }
}
