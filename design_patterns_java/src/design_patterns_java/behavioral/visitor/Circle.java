package design_patterns_java.behavioral.visitor;

public class Circle implements Shape {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}