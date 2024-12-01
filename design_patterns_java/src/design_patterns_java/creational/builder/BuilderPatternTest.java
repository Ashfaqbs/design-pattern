package design_patterns_java.creational.builder;

public class BuilderPatternTest {
	public static void main(String[] args) {
		// Step 1: Use the Builder to create an object
		Product product = new Product.ProductBuilder().setName("Laptop").setQuantity(10).build();

		// Step 2: Print the created object
		System.out.println(product);
	}
}
