

## **Decorator Pattern – Explained Simply**


### **What Is It?**

The **Decorator Pattern** is a structural design pattern that:

* Lets you **add behavior** to objects **dynamically** at runtime
* Keeps original classes **unchanged**
* Achieves behavior **extension via composition**, not inheritance

---

### **Why Is It Needed?**

#### Problem: **Class Explosion**

Let’s take a **Pizza example**. Say we have:

* `Margherita`
* `Farmhouse`
* `Cheese Burst`
* `Paneer Tikka`

And we want to support **combinations**:

* With Cheese
* With Olives
* With Mushroom
* With Cheese + Olives
* With Cheese + Mushroom
* ...

This leads to **class explosion** if we try inheritance:

```java
class MargheritaWithCheese {}
class FarmhouseWithCheeseAndMushroom {}
class PaneerTikkaWithOlivesAndMushroomAndCheese {}
```

Too many classes for every combination — **not scalable**.

---

### **Decorator Pattern – Solution**

We break this down as:

1. **Base Interface**: Pizza
2. **Concrete Base**: MargheritaPizza
3. **Decorator Abstract Class**: ToppingDecorator
4. **Concrete Decorators**: CheeseTopping, OliveTopping, MushroomTopping

Each decorator **wraps** the base pizza and adds its own behavior (e.g., cost, description).

---

###  Code Example – Pizza with Toppings

```java
// Step 1: Base Pizza Interface
public interface Pizza {
    String getDescription();
    double getCost();
}
```

```java
// Step 2: Concrete Base Pizza
public class MargheritaPizza implements Pizza {
    public String getDescription() {
        return "Margherita Pizza";
    }

    public double getCost() {
        return 100;
    }
}
```

```java
// Step 3: Abstract Decorator
public abstract class ToppingDecorator implements Pizza {
    protected Pizza basePizza;

    public ToppingDecorator(Pizza basePizza) {
        this.basePizza = basePizza;
    }

    public String getDescription() {
        return basePizza.getDescription();
    }

    public double getCost() {
        return basePizza.getCost();
    }
}
```

```java
// Step 4: Concrete Decorators
public class CheeseTopping extends ToppingDecorator {
    public CheeseTopping(Pizza basePizza) {
        super(basePizza);
    }

    public String getDescription() {
        return basePizza.getDescription() + ", Cheese";
    }

    public double getCost() {
        return basePizza.getCost() + 30;
    }
}

public class OliveTopping extends ToppingDecorator {
    public OliveTopping(Pizza basePizza) {
        super(basePizza);
    }

    public String getDescription() {
        return basePizza.getDescription() + ", Olives";
    }

    public double getCost() {
        return basePizza.getCost() + 20;
    }
}
```

```java
// Step 5: Usage
public class Main {
    public static void main(String[] args) {
        Pizza pizza = new MargheritaPizza();  // base
        pizza = new CheeseTopping(pizza);     // wrap with cheese
        pizza = new OliveTopping(pizza);      // wrap with olives

        System.out.println("Description: " + pizza.getDescription());
        System.out.println("Total Cost: ₹" + pizza.getCost());
    }
}
```

**Output:**

```
Description: Margherita Pizza, Cheese, Olives
Total Cost: ₹150.0
```

---

###  Benefits of Decorator Pattern

| Benefit                      | Description                                       |
| ---------------------------- | ------------------------------------------------- |
| Open/Closed Principle        | Add new toppings without modifying existing code  |
| No Class Explosion           | No need to create separate classes for each combo |
| Dynamic Behavior             | Toppings can be added at runtime                  |
| Composition Over Inheritance | Reuse behavior by wrapping, not subclassing       |

---

### Real-World Use Cases

* Java I/O Streams (`BufferedReader`, `DataInputStream`, etc.)
* GUI frameworks (`BorderDecorator`, `ShadowDecorator`)
* Logging frameworks (add logging before/after calls)
* Spring AOP / Proxy-based wrappers (decorators in disguise)

---

### Summary

* **Problem**: Too many combinations, leads to subclass explosion.
* **Decorator Pattern**: Add features (like toppings) **dynamically** by wrapping objects.
* **Key Idea**: Each decorator "wraps" another object that implements the same interface.

---