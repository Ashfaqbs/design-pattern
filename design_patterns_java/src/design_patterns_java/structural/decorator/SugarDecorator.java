package design_patterns_java.structural.decorator;

public class SugarDecorator extends CoffeeDecorator {
	public SugarDecorator(Coffee coffee) {
		super(coffee);
	}

	@Override
	public String getDescription() {
		return coffee.getDescription() + ", Sugar";
	}

	@Override
	public double getCost() {
		return coffee.getCost() + 5.0; // Sugar adds Rs. 5
	}
}