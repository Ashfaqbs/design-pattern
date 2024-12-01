package design_patterns_java.structural.decorator;

public class SimpleCoffee implements Coffee {
	@Override
	public String getDescription() {
		return "Simple Coffee";
	}

	@Override
	public double getCost() {
		return 50.0; // Base price
	}
}
