package design_patterns_java.creational.abstractfactory;

public class AbstractFactoryTest {
	private static GUIFactory getFactory(String osType) {
		if (osType.equalsIgnoreCase("windows")) {
			return new WindowsFactory();
		} else if (osType.equalsIgnoreCase("mac")) {
			return new MacFactory();
		}
		throw new IllegalArgumentException("Unknown OS type: " + osType);
	}

	public static void main(String[] args) {
		// Step 1: Determine the factory based on the OS type
		GUIFactory factory = getFactory("windows");

		// Step 2: Create and use products
		Button button = factory.createButton();
		button.click();

		Checkbox checkbox = factory.createCheckbox();
		checkbox.toggle();
	}
}
