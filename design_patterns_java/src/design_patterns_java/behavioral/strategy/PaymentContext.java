package design_patterns_java.behavioral.strategy;

public class PaymentContext {
	private PaymentStrategy strategy;

	public PaymentContext(PaymentStrategy strategy) {
		this.strategy = strategy;
	}

	public void setPaymentStrategy(PaymentStrategy strategy) {
		this.strategy = strategy;
	}

	public void makePayment(int amount) {
		strategy.pay(amount); // Executes the payment strategy
	}
}
