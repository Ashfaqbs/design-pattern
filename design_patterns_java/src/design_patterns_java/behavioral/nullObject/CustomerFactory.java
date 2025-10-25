package design_patterns_java.behavioral.nullObject;

class CustomerFactory {
public static Customer getCustomer(String email) {
if (email == null || email.isEmpty()) {
return new NullCustomer();
}
return new RealCustomer(email);
}
}