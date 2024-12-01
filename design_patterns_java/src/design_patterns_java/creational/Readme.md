Creational design patterns deal with the creation of objects, providing mechanisms to make object creation more flexible, reusable, and suited to the specific problem. The goal is to abstract the object creation process to ensure the system is decoupled from the actual instantiation logic.

### Key Features of Creational Patterns:
1. **Encapsulation of object creation logic:** Creation is centralized to improve flexibility.
2. **Control over object instantiation:** Reduces dependencies and enforces constraints on object construction.
3. **Flexibility for change:** Easy to change the implementation without impacting the client code.

---

### Types of Creational Patterns:  

1. **Singleton**  
   - Ensures that a class has only one instance and provides a global access point to it.  
   - Example: A single database connection pool shared across the application.  

2. **Factory Method**  
   - Defines an interface or abstract class for creating an object but lets subclasses decide which class to instantiate.  
   - Example: A logger system that creates loggers based on file type (e.g., JSON, XML).  

3. **Abstract Factory**  
   - Provides an interface to create families of related or dependent objects without specifying their concrete classes.  
   - Example: GUI toolkit with components like buttons and text fields for different platforms (Windows, Mac).  

4. **Builder**  
   - Separates the construction of a complex object from its representation so the same construction process can create different representations.  
   - Example: Creating a house object with optional features like a garden, pool, or garage.  

5. **Prototype**  
   - Creates new objects by copying existing objects (prototypes). Useful when object creation is costly.  
   - Example: Cloning a large configuration object instead of recreating it.  

---

### Quick Analogy for Understanding  
- **Singleton:** Only one electricity meter in your house.  
- **Factory Method:** A vending machine dispensing items based on user selection.  
- **Abstract Factory:** A furniture shop offering matching tables and chairs for specific room styles.  
- **Builder:** An architect designing houses based on a modular blueprint with different options.  
- **Prototype:** A photocopy machine duplicating an existing document.



In **Spring Boot**, the **Creational Design Patterns** are widely utilized to manage object creation, dependency injection, and configuration. Here's a breakdown of how each Creational Pattern is used in Spring Boot:

---

### **1. Singleton Pattern in Spring Boot**
**Where Used:**
- The **Spring IoC Container** implements the Singleton pattern by default for beans.
- Any `@Component`, `@Service`, `@Repository`, or `@Bean` is a singleton by default unless specified otherwise.

**Example in Spring Boot:**
```java
@Service
public class MyService {
    // This bean is a singleton by default
}
```

**Real-world Use:**
- Ensures only one instance of a bean exists in the Spring context to manage shared state or perform stateless operations.

**How Spring Manages Singleton:**
- Spring uses an internal cache to store instances of singleton beans.
- It ensures thread safety by synchronizing bean initialization.

---

### **2. Factory Method Pattern in Spring Boot**
**Where Used:**
- Spring uses factory methods to create beans when we use `@Bean` with a custom creation method.
- Example: Using `FactoryBean` for advanced configurations.

**Example in Spring Boot:**
```java
@Configuration
public class AppConfig {

    @Bean
    public MyService myService() {
        return new MyService(); // Factory method for creating a bean
    }
}
```

**Real-world Use:**
- When bean creation logic is complex or involves conditional creation.
- Example: Defining a `DataSource` bean with environment-specific properties.

---

### **3. Abstract Factory Pattern in Spring Boot**
**Where Used:**
- The `BeanFactory` and `ApplicationContext` act as abstract factories.
- They provide a way to get instances of different types of beans dynamically.

**Example in Spring Boot:**
```java
public class MyApp {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        MyService myService = context.getBean(MyService.class);
        myService.doSomething();
    }
}
```

**Real-world Use:**
- The `ApplicationContext` can create different types of objects based on configuration without exposing their concrete classes.
- Example: Creating different `Environment` implementations for test, dev, and production.

---

### **4. Builder Pattern in Spring Boot**
**Where Used:**
- Used to configure complex objects, such as HTTP requests in **RestTemplate** or **WebClient**.
- Example: Creating `UriComponents` or configuring `RestTemplate`.

**Example in Spring Boot:**
```java
UriComponents uriComponents = UriComponentsBuilder.newInstance()
    .scheme("https")
    .host("api.example.com")
    .path("/data")
    .queryParam("id", "123")
    .build();
```

**Real-world Use:**
- Allows building complex configurations step-by-step without polluting the object with many constructors.
- Used in `Spring Security` for configuring rules via `HttpSecurity`.

---

### **5. Prototype Pattern in Spring Boot**
**Where Used:**
- Spring supports the **Prototype Scope** for beans, where a new instance is created every time the bean is requested.

**Example in Spring Boot:**
```java
@Component
@Scope("prototype")
public class PrototypeBean {
    // New instance for every request
}
```

**Real-world Use:**
- Useful for non-thread-safe beans, where you need a separate instance per request or operation.
- Example: Creating unique request-scoped objects in web applications.

---

### **6. Prototype with Factory in Spring Boot**
- Sometimes `Prototype` scope is used in combination with `Factory` to control object creation manually.

**Example with Manual Creation:**
```java
@Component
@Scope("prototype")
public class Task {
    public void execute() {
        System.out.println("Executing Task");
    }
}

@Service
public class TaskFactory {
    @Autowired
    private ApplicationContext context;

    public Task createTask() {
        return context.getBean(Task.class);
    }
}
```

---

### **Spring Boot Framework Implementations**
- **Singleton:** Bean scopes (`@Scope("singleton")` by default).
- **Factory Method:** `@Bean` or `FactoryBean`.
- **Abstract Factory:** `ApplicationContext` and its various implementations (`AnnotationConfigApplicationContext`, `XmlWebApplicationContext`).
- **Builder:** Used extensively in configuration classes, web requests, and security.
- **Prototype:** Bean scopes (`@Scope("prototype")`).

---
