package design_patterns_java.behavioral.state;

public class NoCoinInsertedState implements State {
	private VendingMachine machine;

	public NoCoinInsertedState(VendingMachine machine) {
		this.machine = machine;
	}

	@Override
	public void insertCoin() {
		System.out.println("Coin inserted.");
		machine.setState(machine.getCoinInsertedState());
	}

	@Override
	public void pressButton() {
		System.out.println("Please insert a coin first.");
	}

	@Override
	public void dispense() {
		System.out.println("Insert a coin before dispensing.");
	}
}