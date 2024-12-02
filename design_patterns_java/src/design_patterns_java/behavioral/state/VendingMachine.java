package design_patterns_java.behavioral.state;

public class VendingMachine {
	private State noCoinInsertedState;
	private State coinInsertedState;
	private State dispensingState;

	private State currentState;

	public VendingMachine() {
		noCoinInsertedState = new NoCoinInsertedState(this);
		coinInsertedState = new CoinInsertedState(this);
		dispensingState = new DispensingState(this);

		currentState = noCoinInsertedState; // Initial state
	}

	public void insertCoin() {
		currentState.insertCoin();
	}

	public void pressButton() {
		currentState.pressButton();
	}

	public void dispense() {
		currentState.dispense();
	}

	public void setState(State state) {
		currentState = state;
	}

	public State getNoCoinInsertedState() {
		return noCoinInsertedState;
	}

	public State getCoinInsertedState() {
		return coinInsertedState;
	}

	public State getDispensingState() {
		return dispensingState;
	}
}
