package design_patterns_java.behavioral.visitor;

public class Client {
	public static void main(String[] args) {
		Shape circle = new Circle();
		Shape rectangle = new Rectangle();
		Shape triangle = new Triangle();

		Visitor areaVisitor = new AreaVisitor();
		Visitor printVisitor = new PrintVisitor();

		// Perform operations using the Visitor pattern
		circle.accept(areaVisitor); // Calculates and prints the area of Circle
		rectangle.accept(areaVisitor); // Calculates and prints the area of Rectangle
		triangle.accept(areaVisitor); // Calculates and prints the area of Triangle

		circle.accept(printVisitor); // Prints "This is a Circle."
		rectangle.accept(printVisitor); // Prints "This is a Rectangle."
		triangle.accept(printVisitor); // Prints "This is a Triangle."
	}
}