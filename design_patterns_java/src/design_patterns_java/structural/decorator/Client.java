package design_patterns_java.structural.decorator;

public class Client {
	public static void main(String[] args) {
		// Base Coffee
		Coffee coffee = new SimpleCoffee();
		System.out.println(coffee.getDescription() + " -> Rs. " + coffee.getCost());

		// Add Milk
		coffee = new MilkDecorator(coffee);
		System.out.println(coffee.getDescription() + " -> Rs. " + coffee.getCost());

		// Add Sugar
		coffee = new SugarDecorator(coffee);
		System.out.println(coffee.getDescription() + " -> Rs. " + coffee.getCost());
	}
}
