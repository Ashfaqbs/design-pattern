package design_patterns_java.behavioral.nullObject;

class RealCustomer implements Customer {
private String email;


public RealCustomer(String email) {
this.email = email;
}


@Override
public void sendPromotionalEmail() {
System.out.println("Sending email to: " + email);
}
}