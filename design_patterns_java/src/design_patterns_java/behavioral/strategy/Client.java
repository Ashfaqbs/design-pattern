package design_patterns_java.behavioral.strategy;

public class Client {
	public static void main(String[] args) {
		// Payment context with CreditCard payment strategy
		PaymentContext context = new PaymentContext(new CreditCardPayment());
		context.makePayment(500); // Paid 500 using Credit Card.

		// Change payment strategy to PayPal dynamically
		context.setPaymentStrategy(new PayPalPayment());
		context.makePayment(1500); // Paid 1500 using PayPal.
	}
}
