package design_patterns_java.structural.bridge;

public class Client {
	public static void main(String[] args) {
		// Create objects for different combinations
		Shape redCircle = new Circle(new RedColor());
		Shape blueSquare = new Square(new BlueColor());

		// Draw shapes
		redCircle.draw(); // Output: Drawing Circle with Applying Red Color
		blueSquare.draw(); // Output: Drawing Square with Applying Blue Color
	}
}
