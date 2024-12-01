package design_patterns_java.structural.bridge;

public class Square extends Shape {
	public Square(Color color) {
		super(color);
	}

	@Override
	public void draw() {
		System.out.print("Drawing Square with ");
		color.applyColor();
	}
}