package design_patterns_java.behavioral.strategy;

public class PayPalPayment implements PaymentStrategy {
	@Override
	public void pay(int amount) {
		System.out.println("Paid " + amount + " using PayPal.");
	}
}
