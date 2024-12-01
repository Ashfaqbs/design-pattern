package design_patterns_java.structural.bridge;

public class RedColor implements Color {
    @Override
    public void applyColor() {
        System.out.println("Applying Red Color");
    }
}