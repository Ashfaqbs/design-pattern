The **Decorator Design Pattern** is a structural pattern used to **dynamically add new behavior or responsibilities** to an object without modifying its structure. It’s an alternative to creating multiple subclasses to extend functionality, which keeps your code flexible and adheres to the **Open/Closed Principle**.

---

### **Why Use Decorator?**

1. **Problem it Solves:**  
   Imagine you have an object, like a `Coffee`, and you want to dynamically add features such as "with milk" or "with sugar." Creating a subclass for each possible combination (`MilkCoffee`, `SugarMilkCoffee`, etc.) would lead to **class explosion** and poor maintainability.

2. **Goal:**  
   The Decorator pattern allows you to **attach additional responsibilities** to an object at runtime without altering its base class or other classes using it.

---

### **Key Concepts**
1. **Component:**  
   The base interface or abstract class that defines the core functionality.
   
2. **Concrete Component:**  
   The main class whose behavior you want to enhance.

3. **Decorator:**  
   An abstract class or interface that wraps the component and provides additional behavior.

4. **Concrete Decorator:**  
   Implements the additional features, wrapping the component it decorates.

---

### **Example: Coffee Customization**

#### **Step 1: Component Interface**
```java
public interface Coffee {
    String getDescription();
    double getCost();
}
```

#### **Step 2: Concrete Component**
```java
public class SimpleCoffee implements Coffee {
    @Override
    public String getDescription() {
        return "Simple Coffee";
    }

    @Override
    public double getCost() {
        return 50.0; // Base price
    }
}
```

#### **Step 3: Abstract Decorator**
```java
public abstract class CoffeeDecorator implements Coffee {
    protected Coffee coffee;

    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public String getDescription() {
        return coffee.getDescription();
    }

    @Override
    public double getCost() {
        return coffee.getCost();
    }
}
```

#### **Step 4: Concrete Decorators**
```java
public class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Milk";
    }

    @Override
    public double getCost() {
        return coffee.getCost() + 10.0; // Milk adds Rs. 10
    }
}

public class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Sugar";
    }

    @Override
    public double getCost() {
        return coffee.getCost() + 5.0; // Sugar adds Rs. 5
    }
}
```

#### **Step 5: Client Code**
```java
public class Client {
    public static void main(String[] args) {
        // Base Coffee
        Coffee coffee = new SimpleCoffee();
        System.out.println(coffee.getDescription() + " -> Rs. " + coffee.getCost());

        // Add Milk
        coffee = new MilkDecorator(coffee);
        System.out.println(coffee.getDescription() + " -> Rs. " + coffee.getCost());

        // Add Sugar
        coffee = new SugarDecorator(coffee);
        System.out.println(coffee.getDescription() + " -> Rs. " + coffee.getCost());
    }
}
```

---

#### **Output:**
```
Simple Coffee -> Rs. 50.0
Simple Coffee, Milk -> Rs. 60.0
Simple Coffee, Milk, Sugar -> Rs. 65.0
```

---

### **How It Works:**
1. Each decorator wraps the original object and adds functionality to it.
2. You can chain multiple decorators to dynamically build the desired behavior.

---

### **Advantages:**
- **Flexible:** New functionalities can be added without altering existing code.
- **Extensible:** You can add as many decorators as needed without modifying the base component.
- **Single Responsibility Principle:** Each decorator focuses on a specific feature or responsibility.

---

### **Use Cases in Real Life:**
- **IO Streams in Java:** `BufferedReader`, `BufferedWriter`, `InputStreamReader` are decorators over `Reader` and `InputStream`.
- **Logging Frameworks:** Adding timestamps or log levels dynamically.
- **UI Frameworks:** Adding visual effects to UI components.

---

### **When to Use Decorator?**
- When you need to add functionality dynamically without altering the base class.
- When subclassing leads to a combinatorial explosion of classes.

---
