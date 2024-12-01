package design_patterns_java.structural.bridge;

public class BlueColor implements Color {
	@Override
	public void applyColor() {
		System.out.println("Applying Blue Color");
	}
}