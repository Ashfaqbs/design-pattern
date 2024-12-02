### **Template Method Pattern**

The **Template Method Pattern** is a **behavioral design pattern** that defines the skeleton of an algorithm in a base class, allowing subclasses to override specific steps of the algorithm without changing its overall structure.

---

### **Why Use the Template Method Pattern?**
- **Reuse common logic:** It ensures that the common structure of an algorithm is reused in different scenarios.
- **Flexibility for variation:** Subclasses can define or customize parts of the algorithm while maintaining its general framework.
- **Encapsulation of invariant behavior:** It encapsulates the unchanging parts of the algorithm in a single place (base class).

---

### **Scenario**
Imagine you are creating a framework for different types of beverages like **Tea** and **Coffee**. Both beverages follow a similar preparation process:
1. Boil water
2. Brew the beverage
3. Pour it into a cup
4. Add condiments

The Template Method Pattern can be used to define the common process while allowing subclasses to implement specific steps (e.g., brewing or adding condiments).

---

### **Key Components**
1. **Abstract Class:**
   - Defines the template method (final) that contains the skeleton of the algorithm.
   - Provides default implementations for some steps (optional).
   - Includes abstract or hook methods for steps that subclasses must or can override.

2. **Concrete Classes:**
   - Override the abstract or hook methods to provide specific behavior for some steps of the algorithm.

---

### **Implementation Example**

#### **Step 1: Define the Abstract Class**
```java
abstract class Beverage {
    // Template method (final to prevent overriding)
    public final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }

    // Steps that are the same for all beverages
    private void boilWater() {
        System.out.println("Boiling water");
    }

    private void pourInCup() {
        System.out.println("Pouring into cup");
    }

    // Abstract methods for subclasses to define
    protected abstract void brew();
    protected abstract void addCondiments();
}
```

#### **Step 2: Create Concrete Classes**
```java
class Tea extends Beverage {
    @Override
    protected void brew() {
        System.out.println("Steeping the tea");
    }

    @Override
    protected void addCondiments() {
        System.out.println("Adding lemon");
    }
}

class Coffee extends Beverage {
    @Override
    protected void brew() {
        System.out.println("Dripping coffee through filter");
    }

    @Override
    protected void addCondiments() {
        System.out.println("Adding sugar and milk");
    }
}
```

#### **Step 3: Test the Template Method Pattern**
```java
public class TemplateMethodDemo {
    public static void main(String[] args) {
        Beverage tea = new Tea();
        Beverage coffee = new Coffee();

        System.out.println("Preparing tea:");
        tea.prepareRecipe();

        System.out.println("\nPreparing coffee:");
        coffee.prepareRecipe();
    }
}
```

---

### **Output**
```
Preparing tea:
Boiling water
Steeping the tea
Pouring into cup
Adding lemon

Preparing coffee:
Boiling water
Dripping coffee through filter
Pouring into cup
Adding sugar and milk
```

---

### **Key Points**
1. **Template Method:** The `prepareRecipe` method provides the invariant structure for the algorithm.
2. **Customizable Steps:** `brew` and `addCondiments` are abstract methods that subclasses must implement, allowing customization.
3. **Encapsulation:** The steps like `boilWater` and `pourInCup` are encapsulated in the base class and cannot be overridden.

---

### **Hook Methods**
- A **hook** is a method in the abstract class with a default implementation. Subclasses can override it if needed.
- Example:
  ```java
  protected boolean customerWantsCondiments() {
      return true;  // Default behavior
  }
  ```

You could use this to make the addition of condiments optional:
```java
public final void prepareRecipe() {
    boilWater();
    brew();
    pourInCup();
    if (customerWantsCondiments()) {
        addCondiments();
    }
}
```

---

### **Advantages**
- **Code reuse:** Common logic is centralized in the abstract class.
- **Consistency:** Ensures the algorithm's overall structure remains the same.
- **Extensibility:** Subclasses can modify or extend specific steps of the algorithm.

### **Disadvantages**
- **Tight coupling:** Subclasses depend on the abstract class.
- **Complexity:** For simple use cases, it might introduce unnecessary complexity.

---

### **Real-World Use Cases**
1. **Frameworks:** Many frameworks use the Template Method pattern to define the skeleton of algorithms.
   - Example: Spring Framework uses this pattern in various lifecycle methods (`doGet`, `doPost`, etc.) for servlets.
2. **Game Development:** Define the main game loop and let subclasses implement specific game rules or mechanics.
3. **UI Components:** Frameworks like Swing in Java use this pattern to let developers define specific rendering behavior while maintaining the overall rendering logic.

---

### **Template Method in Spring Framework**
- **Spring Security:** The `AbstractAuthenticationProcessingFilter` class uses a template method pattern. It defines a `doFilter` method and delegates authentication logic to its subclasses.
- **Transaction Management:** Spring’s transaction management uses template methods for handling the transaction lifecycle.

---
