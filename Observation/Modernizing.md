# Modern Java Design: Replacing Classic Patterns with Language Features

## Overview

In 2025, Java is no longer the same language it was in the early 2000s. With features like lambdas, records, sealed interfaces, and the Flow API, many traditional design patterns from the "Gang of Four" era are now redundant. This document revisits common patterns and shows how to replace them using modern Java.

---

## 1. Singleton Pattern

### Traditional Approach

```java
public class MySingleton {
    private static final MySingleton instance = new MySingleton();
    private MySingleton() {}
    public static MySingleton getInstance() {
        return instance;
    }
}
```

### Modern Java

```java
public enum MySingleton {
    INSTANCE;
    public void doSomething() {
        System.out.println("Doing work...");
    }
}
```

### Alternative

Use a dependency injection framework (like Spring) to handle singleton scope.

---

## 2. Builder Pattern

### Traditional Approach

```java
User user = new User.Builder()
    .setName("Alice")
    .setAge(30)
    .build();
```

### Modern Java with Records

```java
public record User(String name, int age) {}

User user = new User("Alice", 30);
```

### Alternative: Lombok

```java
@Builder
public class User {
    private String name;
    private int age;
}
```

---

## 3. Strategy Pattern

### Traditional Approach

```java
interface CompressionStrategy {
    void compress(File input);
}
```

### Modern Java

```java
CompressionStrategy gzip = input -> {
    // Compression logic here
};
```

Functional interfaces and lambdas eliminate the need for multiple concrete strategy classes.

---

## 4. Observer Pattern

### Traditional Approach

```java
Observable obs = new ConcreteObservable();
obs.addObserver(new ConcreteObserver());
```

### Modern Java (Flow API)

```java
SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
publisher.subscribe(data -> System.out.println("Got: " + data));
publisher.submit("Hello");
```

Reactive programming frameworks (Reactor, RxJava) are recommended for robust event-stream management.

---

## 5. Factory Pattern

### Traditional Approach

```java
public class ShapeFactory {
    public Shape create(String type) {
        switch(type) {
            case "CIRCLE": return new Circle();
            case "SQUARE": return new Square();
            default: throw new IllegalArgumentException();
        }
    }
}
```

### Modern Java (Sealed Interfaces + Switch Expressions)

```java
sealed interface Shape permits Circle, Square {}

public class Circle implements Shape {}
public class Square implements Shape {}

Shape shape = switch (type) {
    case "CIRCLE" -> new Circle();
    case "SQUARE" -> new Square();
    default -> throw new IllegalArgumentException();
};
```

Combine with DI for even cleaner object instantiation.

---

## Language Features That Replace Patterns

| Feature                   | Replaces                             |
| ------------------------- | ------------------------------------ |
| `record`                  | Builder, DTOs                        |
| Sealed Interfaces         | Strategy, Visitor                    |
| Lambdas/Method References | Strategy, Command, Observer          |
| Flow API                  | Observer                             |
| Spring DI/AOP             | Singleton, Factory, Proxy, Decorator |

---

## Conclusion

Rather than adhering rigidly to traditional design patterns, modern Java encourages developers to utilize built-in features for cleaner, more expressive code. Patterns still matter—but they are no longer mandatory scaffolding. Use them only when they add value.
