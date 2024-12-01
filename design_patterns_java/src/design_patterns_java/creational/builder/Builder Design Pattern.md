### **Builder Design Pattern**

The **Builder** pattern is used to construct a complex object step by step. It separates the construction of an object from its representation, allowing you to create different types of objects using the same construction process.

---

### **Why Use Builder?**
1. **Readable Object Creation:** Simplifies the process of creating objects with multiple optional or mandatory parameters.
2. **Flexibility:** Allows you to create different variations of an object using the same code.
3. **Avoids Constructor Overloading:** Replaces constructors with many parameters by chaining methods for clarity.

---

### **Implementation with Two Variables**

#### **Steps for Builder Pattern:**
1. Create a `Builder` static nested class inside the main class.
2. Add variables to the `Builder` class for the attributes of the main class.
3. Provide setter-like methods in the `Builder` class to set attributes.
4. Add a `build()` method in the `Builder` to return the constructed object.
5. Make the main class constructor private to force object creation through the `Builder`.

---

#### **Code Example**
```java
class Product {
    // Step 1: Two private variables
    private String name;
    private int quantity;

    // Step 2: Private constructor to enforce usage of Builder
    private Product(ProductBuilder builder) {
        this.name = builder.name;
        this.quantity = builder.quantity;
    }

    // Step 3: Static nested Builder class
    public static class ProductBuilder {
        private String name;    // Variable 1
        private int quantity;   // Variable 2

        // Setter-like methods
        public ProductBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ProductBuilder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        // Build method to create the Product object
        public Product build() {
            return new Product(this);
        }
    }

    @Override
    public String toString() {
        return "Product [name=" + name + ", quantity=" + quantity + "]";
    }
}
```

---

### **How to Use the Builder Pattern**
```java
public class BuilderPatternTest {
    public static void main(String[] args) {
        // Step 1: Use the Builder to create an object
        Product product = new Product.ProductBuilder()
                              .setName("Laptop")
                              .setQuantity(10)
                              .build();

        // Step 2: Print the created object
        System.out.println(product);
    }
}
```

---

### **Output**
```
Product [name=Laptop, quantity=10]
```

---

### **Advantages of Builder**
1. **Readability:** Easy to read and understand object creation logic.
2. **Immutability:** Ensures the constructed object is immutable.
3. **Chaining:** Fluent API style makes the code cleaner.

---

### **Disadvantages of Builder**
1. **Boilerplate Code:** Requires writing a lot of boilerplate for the builder class.
2. **Complex for Small Objects:** Overkill for objects with only a few fields.

---

### **Enhancements**
1. **Validation:** Add validation checks in the `build()` method.
2. **Optional Defaults:** Provide default values for variables in the `Builder`.
3. **Reusability:** Extend the Builder for subclasses to create related types of objects.

