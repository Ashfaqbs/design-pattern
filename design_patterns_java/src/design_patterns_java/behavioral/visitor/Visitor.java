package design_patterns_java.behavioral.visitor;

public interface Visitor {
	void visit(Circle circle);

	void visit(Rectangle rectangle);

	void visit(Triangle triangle);
}
