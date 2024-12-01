package design_patterns_java.structural.bridge;

public abstract class Shape {
    protected Color color; // Bridge to the implementor

    public Shape(Color color) {
        this.color = color;
    }

    abstract void draw();
}
