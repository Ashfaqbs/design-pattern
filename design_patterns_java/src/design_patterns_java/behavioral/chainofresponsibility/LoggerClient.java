package design_patterns_java.behavioral.chainofresponsibility;

public class LoggerClient {
    public static void main(String[] args) {
        // Create each handler
        BaseLogger debugLogger = new DebugLogger();
        BaseLogger infoLogger = new InfoLogger();
        BaseLogger criticalLogger = new CriticalLogger();

        // Register the next set of loggers / Link them in a chain
        debugLogger.setNext(infoLogger);
        infoLogger.setNext(criticalLogger);

        // Test messages
        debugLogger.log("System initialized successfully.", 2);
        debugLogger.log("Variable x value is 42.", 1);
        debugLogger.log("Database connection failed!", 3);

        /*
         * Add debug as starting point, register them in a chain and call the debug logger log and pass the level based in the 
         * level the next set of loggers will be triggered
         *
         */
    }
}
