package design_patterns_java.behavioral.state;

public class CoinInsertedState implements State {
	private VendingMachine machine;

	public CoinInsertedState(VendingMachine machine) {
		this.machine = machine;
	}

	@Override
	public void insertCoin() {
		System.out.println("Coin already inserted.");
	}

	@Override
	public void pressButton() {
		System.out.println("Button pressed. Dispensing item...");
		machine.setState(machine.getDispensingState());
	}

	@Override
	public void dispense() {
		System.out.println("Press the button to dispense.");
	}
}