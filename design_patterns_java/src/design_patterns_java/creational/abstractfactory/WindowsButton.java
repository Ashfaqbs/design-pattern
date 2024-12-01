package design_patterns_java.creational.abstractfactory;

class WindowsButton implements Button {
	@Override
	public void click() {
		System.out.println("Windows Button clicked!");
	}
}
