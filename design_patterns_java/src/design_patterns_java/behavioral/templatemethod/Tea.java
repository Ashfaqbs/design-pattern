package design_patterns_java.behavioral.templatemethod;

class Tea extends Beverage {
	@Override
	protected void brew() {
		System.out.println("Steeping the tea");
	}

	@Override
	protected void addCondiments() {
		System.out.println("Adding lemon");
	}
}
