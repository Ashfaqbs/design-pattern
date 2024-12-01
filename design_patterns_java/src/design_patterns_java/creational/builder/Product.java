package design_patterns_java.creational.builder;

class Product {
	// Step 1: Two private variables
	private String name;
	private int quantity;

	// Step 2: Private constructor to enforce usage of Builder
	private Product(ProductBuilder builder) {
		this.name = builder.name;
		this.quantity = builder.quantity;
	}

	// Step 3: Static nested Builder class
	public static class ProductBuilder {
		private String name; // Variable 1
		private int quantity; // Variable 2

		// Setter-like methods
		public ProductBuilder setName(String name) {
			this.name = name;
			return this;
		}

		public ProductBuilder setQuantity(int quantity) {
			this.quantity = quantity;
			return this;
		}

		// Build method to create the Product object
		public Product build() {
			return new Product(this);
		}
	}

	@Override
	public String toString() {
		return "Product [name=" + name + ", quantity=" + quantity + "]";
	}
}
