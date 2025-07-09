## Abstract Factory Design Pattern

---

### Definition

The **Abstract Factory Pattern** is a creational design pattern that provides an interface to create **families of related or dependent objects** without specifying their concrete classes.

---

### Problem

A basic **Factory Pattern** is used to instantiate one product type, such as a `Car`. However, when dealing with multiple **families** of products, such as:

* Ordinary Cars (`OrdinaryHatchback`, `OrdinarySedan`)
* Luxury Cars (`LuxuryHatchback`, `LuxurySedan`)

A simple factory becomes insufficient. A **factory of factories** is required to ensure consistency across product families.

This leads to the need for an **Abstract Factory Pattern**.

---

## Use Case: Car Production Line

Two main product families:

* **Luxury Car Family**
* **Ordinary Car Family**

Each family includes:

* A **Hatchback**
* A **Sedan**

---

### Step-by-Step Breakdown

---

#### 1. Product Interfaces

```java
public interface Hatchback {
    void assemble();
}

public interface Sedan {
    void assemble();
}
```

---

#### 2. Concrete Product Implementations

```java
// Ordinary Cars
public class OrdinaryHatchback implements Hatchback {
    public void assemble() {
        System.out.println("Assembling Ordinary Hatchback");
    }
}

public class OrdinarySedan implements Sedan {
    public void assemble() {
        System.out.println("Assembling Ordinary Sedan");
    }
}

// Luxury Cars
public class LuxuryHatchback implements Hatchback {
    public void assemble() {
        System.out.println("Assembling Luxury Hatchback");
    }
}

public class LuxurySedan implements Sedan {
    public void assemble() {
        System.out.println("Assembling Luxury Sedan");
    }
}
```

---

#### 3. Abstract Factory Interface

```java
public interface CarFactory {
    Hatchback createHatchback();
    Sedan createSedan();
}
```

---

#### 4. Concrete Factories

```java
// Factory for Ordinary Cars
public class OrdinaryCarFactory implements CarFactory {
    public Hatchback createHatchback() {
        return new OrdinaryHatchback();
    }

    public Sedan createSedan() {
        return new OrdinarySedan();
    }
}

// Factory for Luxury Cars
public class LuxuryCarFactory implements CarFactory {
    public Hatchback createHatchback() {
        return new LuxuryHatchback();
    }

    public Sedan createSedan() {
        return new LuxurySedan();
    }
}
```

---

#### 5. Factory Producer (Factory of Factories)

```java
public class FactoryProducer {

    public static CarFactory getFactory(String type) {
        return switch (type.toLowerCase()) {
            case "ordinary" -> new OrdinaryCarFactory();
            case "luxury"   -> new LuxuryCarFactory();
            default -> throw new IllegalArgumentException("Unknown car type: " + type);
        };
    }
}
```

---

#### 6. Client Code

```java
public class Main {
    public static void main(String[] args) {

        CarFactory factory = FactoryProducer.getFactory("luxury");

        Hatchback hatchback = factory.createHatchback();
        Sedan sedan = factory.createSedan();

        hatchback.assemble();
        sedan.assemble();

        // Output:
        // Assembling Luxury Hatchback
        // Assembling Luxury Sedan
    }
}
```

---

## Summary

| Component                                 | Role                                                              |
| ----------------------------------------- | ----------------------------------------------------------------- |
| Product Interfaces (`Hatchback`, `Sedan`) | Abstract product contracts                                        |
| Concrete Products                         | Actual product implementations from each family                   |
| `CarFactory` Interface                    | Abstract factory interface                                        |
| `LuxuryCarFactory`, `OrdinaryCarFactory`  | Concrete factories producing family objects                       |
| `FactoryProducer`                         | Factory of factories, responsible for returning the right factory |
| Client                                    | Uses abstract factory to produce related objects                  |

---

## Benefits

* Ensures **consistency across families** of related objects
* Encourages **separation of concerns**
* Supports **Open/Closed Principle**
* Enables easy configuration switching (e.g., luxury vs. ordinary) without changing client logic

---

This design pattern is useful in applications that need to create multiple variants of a group of objects where the concrete classes should not be hardcoded.
