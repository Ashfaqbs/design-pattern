## 1. Layman’s View (Why the pattern exists)

* Imagine a **shape‑drawing app** that must create **circles**, **rectangles**, **triangles**, etc.
* Without a factory, the calling code would use `new Circle()`, `new Rectangle()`, … everywhere.
* When a new shape appears (for example, *Hexagon*), every place that hard‑codes `new` statements must change.
* This tight coupling violates the **Open/Closed Principle** and clutters business logic with *construction* details.

A **Factory** centralises object creation behind one method.
Callers simply *ask* the factory for “a circle” or “a rectangle,” and stay blissfully unaware of class names.

---

## 2. Technical Definition

> *Factory Pattern*: Provide an interface for creating objects in a superclass, while allowing subclasses or methods to decide which class to instantiate. The client deals with an **abstraction**, not concrete classes.

---

## 3. Code Walk‑Through (Java 21)

```java
// ========= 1. Product Abstraction =========
public interface Shape {
    void draw();          // Common behaviour every shape provides
}
```

```java
// ========= 2. Concrete Products =========
public final class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a circle");
    }
}

public final class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a rectangle");
    }
}

public final class Triangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a triangle");
    }
}
```

```java
// ========= 3. Factory = Centralised creator =========
public final class ShapeFactory {

    // Prevent instantiation; factory works via static method
    private ShapeFactory() {}

    /**
     * Factory method: returns a Shape based on supplied key.
     * @param type simple key, could be an enum for better type safety
     * @return concrete Shape instance
     */
    public static Shape createShape(String type) {

        // Case‑insensitive match; switch can be replaced with Map<key,Supplier<Shape>>
        return switch (type.toLowerCase()) {
            case "circle"    -> new Circle();
            case "rectangle" -> new Rectangle();
            case "triangle"  -> new Triangle();
            default -> throw new IllegalArgumentException(
                    "Unknown shape type: " + type);
        };
    }
}
```

```java
// ========= 4. Client Code (Usage) =========
public class DrawingApplication {

    public static void main(String[] args) {

        // Business layer remains decoupled from concrete classes
        Shape circle    = ShapeFactory.createShape("circle");
        Shape rectangle = ShapeFactory.createShape("rectangle");

        circle.draw();      // Drawing a circle
        rectangle.draw();   // Drawing a rectangle
    }
}
```

### Key Points in the Code

| Element                      | Purpose                                                                                            |
| ---------------------------- | -------------------------------------------------------------------------------------------------- |
| `Shape` interface            | Defines the behaviour expected by callers.                                                         |
| Concrete shapes              | Implement `Shape`; individual drawing logic is encapsulated.                                       |
| `ShapeFactory.createShape()` | Decides which concrete class to instantiate. Business code no longer depends on `new SomeShape()`. |

---

## 4. Benefits Recap

| Benefit                    | Explanation                                                                           |
| -------------------------- | ------------------------------------------------------------------------------------- |
| **Loose Coupling**         | Callers depend only on the `Shape` abstraction.                                       |
| **Open/Closed Principle**  | New shapes are added by extending the factory, without touching existing client code. |
| **Centralised Validation** | Incorrect keys (`"hexagon"`) are handled in one place.                                |
| **Testability**            | Factories can be mocked or injected, enabling isolation in unit tests.                |

---

## 5. Variants & Enhancements (Quick Notes)

1. **Enum‑driven factory**

   ```java
   enum ShapeType { CIRCLE, RECTANGLE, TRIANGLE }
   ShapeFactory.createShape(ShapeType.CIRCLE);
   ```
2. **Registry of `Supplier<Shape>`**
   Eliminates `switch`; registration happens at startup.
3. **Abstract Factory**
   Produces families of related objects (e.g., `ColourfulShapeFactory`, `MonochromeShapeFactory`).

---

## 6. Real‑World Uses

| Library / Framework                  | Factory Role                                                     |
| ------------------------------------ | ---------------------------------------------------------------- |
| `java.util.Calendar#getInstance()`   | Hides concrete calendar implementation for locale/time‑zone.     |
| JDBC `DriverManager#getConnection()` | Returns vendor‑specific `Connection` objects.                    |
| Spring `BeanFactory`                 | Dynamically instantiates and wires beans based on configuration. |

---

### Summary

* **Factory Pattern** removes direct construction logic from business code.
* Adds flexibility, improves maintainability, and aligns with SOLID principles.
* Implementation in Java is straightforward: define an interface, concrete classes, and a factory method that maps keys to concrete instances.