package design_patterns_java.structural.decorator;

public class MilkDecorator extends CoffeeDecorator {
	public MilkDecorator(Coffee coffee) {
		super(coffee);
	}

	@Override
	public String getDescription() {
		return coffee.getDescription() + ", Milk";
	}

	@Override
	public double getCost() {
		return coffee.getCost() + 10.0; // Milk adds Rs. 10
	}
}


