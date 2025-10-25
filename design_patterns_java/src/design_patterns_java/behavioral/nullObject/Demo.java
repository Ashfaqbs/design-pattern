package design_patterns_java.behavioral.nullObject;

public class Demo {
public static void main(String[] args) {
Customer customer1 = CustomerFactory.getCustomer("john@example.com");
Customer customer2 = CustomerFactory.getCustomer(null);


customer1.sendPromotionalEmail(); // Sends email
customer2.sendPromotionalEmail(); // Does nothing, no errors
}
}