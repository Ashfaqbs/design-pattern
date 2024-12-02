package design_patterns_java.behavioral.templatemethod;

abstract class Beverage {
	// Template method (final to prevent overriding)
	public final void prepareRecipe() {
		boilWater();
		brew();
		pourInCup();
		addCondiments();
	}

	// Steps that are the same for all beverages
	private void boilWater() {
		System.out.println("Boiling water");
	}

	private void pourInCup() {
		System.out.println("Pouring into cup");
	}

	// Abstract methods for subclasses to define
	protected abstract void brew();

	protected abstract void addCondiments();
}
