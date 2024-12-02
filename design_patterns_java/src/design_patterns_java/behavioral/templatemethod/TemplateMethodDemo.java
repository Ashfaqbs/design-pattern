package design_patterns_java.behavioral.templatemethod;

public class TemplateMethodDemo {
	public static void main(String[] args) {
		Beverage tea = new Tea();
		Beverage coffee = new Coffee();

		System.out.println("Preparing tea:");
		tea.prepareRecipe();

		System.out.println("\nPreparing coffee:");
		coffee.prepareRecipe();
	}
}
