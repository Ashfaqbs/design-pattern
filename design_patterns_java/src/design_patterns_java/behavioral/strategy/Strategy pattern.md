### **Strategy Design Pattern**

The **Strategy Design Pattern** allows you to define a family of algorithms, encapsulate each one, and make them interchangeable. This pattern enables the selection of an algorithm at runtime without altering the client that uses it.

### **Scenario**
Imagine you are building an application that processes orders in different ways (e.g., credit card payments, PayPal payments, etc.). The `Strategy` pattern helps in selecting the appropriate payment method dynamically at runtime without changing the core logic of the application.

---

### **Key Components**
1. **Strategy:** This defines a common interface for all supported algorithms.
2. **ConcreteStrategy:** Implements the `Strategy` interface and defines specific algorithms.
3. **Context:** Maintains a reference to the `Strategy` object and allows it to execute the algorithm.

---

### **Why Use the Strategy Pattern?**
- It promotes the **Open/Closed Principle**: the code is open for extension but closed for modification.
- Allows behavior to change dynamically at runtime.
- Helps avoid **conditional logic** in your code (e.g., using `if` or `switch` to choose an algorithm).

---

### **Implementation Example**

#### **Step 1: Define the Strategy Interface**
```java
public interface PaymentStrategy {
    void pay(int amount);
}
```

#### **Step 2: Implement Concrete Strategies**
```java
public class CreditCardPayment implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using Credit Card.");
    }
}

public class PayPalPayment implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using PayPal.");
    }
}
```

#### **Step 3: Create the Context Class**
```java
public class PaymentContext {
    private PaymentStrategy strategy;

    public PaymentContext(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void makePayment(int amount) {
        strategy.pay(amount);  // Executes the payment strategy
    }
}
```

#### **Step 4: Test the Strategy Pattern**
```java
public class Client {
    public static void main(String[] args) {
        // Payment context with CreditCard payment strategy
        PaymentContext context = new PaymentContext(new CreditCardPayment());
        context.makePayment(500);  // Paid 500 using Credit Card.

        // Change payment strategy to PayPal dynamically
        context.setPaymentStrategy(new PayPalPayment());
        context.makePayment(1500);  // Paid 1500 using PayPal.
    }
}
```

---

### **Output:**
```
Paid 500 using Credit Card.
Paid 1500 using PayPal.
```

---

### **Key Points**
1. **Decoupling Algorithms from Clients:** The client can focus on high-level operations, while different algorithms can be selected and applied dynamically.
2. **Flexibility:** Adding new algorithms doesn't require modifying existing code, just creating a new `ConcreteStrategy`.
3. **No Conditionals:** Avoids complex conditionals (like `if` or `switch`) to choose an algorithm.

---

### **Advantages**
- **Extensibility:** New strategies can be introduced without changing existing code.
- **Maintainability:** Keeps the codebase clean and focused, adhering to the **Single Responsibility Principle**.
- **Testability:** Each concrete strategy can be unit tested independently.

### **Disadvantages**
- **Increased number of classes:** More classes can lead to complexity, especially when there are many algorithms.
- **Context coupling:** The context still needs to know the strategy interface to invoke the correct algorithm.

---

### **Real-World Use Cases**
1. **Payment Processing:** Different payment methods like CreditCard, PayPal, etc.
2. **Sorting Algorithms:** Selecting a sorting algorithm (e.g., QuickSort, MergeSort) based on the context (data size, type, etc.).
3. **Compression Algorithms:** Choosing a compression algorithm (e.g., Zip, Gzip) dynamically for file compression.

---

### **Example in Spring Boot:**
A typical use case in Spring Boot is handling multiple **authentication strategies**. For example:
- Using `DaoAuthenticationProvider` for basic username/password authentication.
- Using `JwtAuthenticationProvider` for JWT-based token authentication.

You could switch between different authentication strategies depending on the user's preferences or the environment (e.g., development vs. production).

---
