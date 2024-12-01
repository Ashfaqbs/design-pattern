package design_patterns_java.behavioral.visitor;

public class PrintVisitor implements Visitor {
	@Override
	public void visit(Circle circle) {
		System.out.println("This is a Circle.");
	}

	@Override
	public void visit(Rectangle rectangle) {
		System.out.println("This is a Rectangle.");
	}

	@Override
	public void visit(Triangle triangle) {
		System.out.println("This is a Triangle.");
	}
}