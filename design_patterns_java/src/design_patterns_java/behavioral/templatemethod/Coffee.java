package design_patterns_java.behavioral.templatemethod;

class Coffee extends Beverage {
	@Override
	protected void brew() {
		System.out.println("Dripping coffee through filter");
	}

	@Override
	protected void addCondiments() {
		System.out.println("Adding sugar and milk");
	}
}