package design_patterns_java.behavioral.visitor;

public class AreaVisitor implements Visitor {
	@Override
	public void visit(Circle circle) {
		double area = Math.PI * 5 * 5; // Example for radius = 5
		System.out.println("Area of Circle: " + area);
	}

	@Override
	public void visit(Rectangle rectangle) {
		double area = 4 * 6; // Example for length = 4, width = 6
		System.out.println("Area of Rectangle: " + area);
	}

	@Override
	public void visit(Triangle triangle) {
		double area = 0.5 * 4 * 3; // Example for base = 4, height = 3
		System.out.println("Area of Triangle: " + area);
	}
}