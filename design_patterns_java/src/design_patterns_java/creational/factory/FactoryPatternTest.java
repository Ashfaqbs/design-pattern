package design_patterns_java.creational.factory;

public class FactoryPatternTest {
	public static void main(String[] args) {
		// Step 1: Get a Circle object
		Shape circle = ShapeFactory.getShape("circle");
		circle.draw();

		// Step 2: Get a Rectangle object
		Shape rectangle = ShapeFactory.getShape("rectangle");
		rectangle.draw();

		// Step 3: Attempt to get an unsupported shape
		try {
			Shape unknown = ShapeFactory.getShape("triangle");
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
}
