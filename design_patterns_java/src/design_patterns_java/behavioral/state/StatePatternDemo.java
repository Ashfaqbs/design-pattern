package design_patterns_java.behavioral.state;

public class StatePatternDemo {
	public static void main(String[] args) {
		VendingMachine machine = new VendingMachine();

		machine.pressButton(); // No coin inserted yet
		machine.insertCoin(); // Coin inserted
		machine.pressButton(); // Button pressed
		machine.insertCoin(); // Wait for dispensing to complete
		machine.dispense(); // Dispense item
	}
}
