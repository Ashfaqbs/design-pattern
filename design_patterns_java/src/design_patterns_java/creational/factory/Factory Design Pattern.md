### **Factory Design Pattern**

The **Factory** pattern is a creational design pattern that provides an interface or method to create objects, allowing subclasses or specific implementations to determine the type of object that needs to be created.

---

### **Why Use Factory?**
1. **Encapsulation:** Hides the object creation logic from the client.
2. **Flexibility:** Allows the addition of new types without modifying the client code.
3. **Avoids Tight Coupling:** The client depends only on an interface or abstract class, not on the specific classes.

---

### **Types of Factory Patterns**
1. **Simple Factory:** A single method handles object creation.
2. **Factory Method:** Uses a method in a subclass to determine which object to create (more flexible).
3. **Abstract Factory:** Creates families of related objects without specifying their concrete classes.

We'll focus on **Simple Factory** for ease of understanding.

---

### **Simple Factory Example**

#### **Steps for Implementation**
1. Create a common interface or abstract class for the products.
2. Implement concrete classes for each type of product.
3. Create a factory class with a static method to generate objects based on input.
4. The client uses the factory method to get the required object.

---

#### **Code Example**

##### **Step 1: Product Interface**
```java
interface Shape {
    void draw();
}
```

##### **Step 2: Concrete Implementations**
```java
class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a Circle");
    }
}

class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a Rectangle");
    }
}
```

##### **Step 3: Factory Class**
```java
class ShapeFactory {
    // Static method to create objects
    public static Shape getShape(String shapeType) {
        if (shapeType == null) {
            return null;
        }
        switch (shapeType.toLowerCase()) {
            case "circle":
                return new Circle();
            case "rectangle":
                return new Rectangle();
            default:
                throw new IllegalArgumentException("Unknown shape type: " + shapeType);
        }
    }
}
```

##### **Step 4: Client Code**
```java
public class FactoryPatternTest {
    public static void main(String[] args) {
        // Step 1: Get a Circle object
        Shape circle = ShapeFactory.getShape("circle");
        circle.draw();

        // Step 2: Get a Rectangle object
        Shape rectangle = ShapeFactory.getShape("rectangle");
        rectangle.draw();

        // Step 3: Attempt to get an unsupported shape
        try {
            Shape unknown = ShapeFactory.getShape("triangle");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
```

---

### **Output**
```
Drawing a Circle
Drawing a Rectangle
Unknown shape type: triangle
```

---

### **Advantages of Factory**
1. **Centralized Object Creation:** The factory encapsulates the creation logic.
2. **Easy Maintenance:** New types can be added by extending the factory method.
3. **Decoupled Code:** The client code doesn’t depend on concrete implementations.

---

### **Disadvantages of Factory**
1. **Complexity:** Adding too many product types can make the factory class large and difficult to maintain.
2. **Limited to One Responsibility:** The factory can only handle object creation; it cannot manage object lifecycle.

---

### **Enhancements**
1. **Factory Method:** Make `ShapeFactory` abstract and let subclasses define specific object creation logic.
2. **Abstract Factory:** Create factories that produce families of related objects (e.g., GUI widgets for different operating systems).
