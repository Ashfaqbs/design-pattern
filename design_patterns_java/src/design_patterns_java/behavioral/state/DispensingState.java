package design_patterns_java.behavioral.state;

public class DispensingState implements State {
	private VendingMachine machine;

	public DispensingState(VendingMachine machine) {
		this.machine = machine;
	}

	@Override
	public void insertCoin() {
		System.out.println("Wait! Currently dispensing an item.");
	}

	@Override
	public void pressButton() {
		System.out.println("Already dispensing an item.");
	}

	@Override
	public void dispense() {
		System.out.println("Item dispensed.");
		machine.setState(machine.getNoCoinInsertedState());
	}
}