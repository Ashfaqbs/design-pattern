### **Structural Design Patterns in Java**

Structural Design Patterns focus on the **composition of classes and objects** to form larger structures, ensuring flexibility and efficiency. These patterns simplify the relationships between objects and classes, making systems easier to manage and extend.

---

### **Types of Structural Design Patterns**
1. **Adapter**
2. **Bridge**
3. **Composite**
4. **Decorator**
5. **Facade**
6. **Flyweight**
7. **Proxy**

---

### **1. Adapter Pattern**
- **Purpose:** Converts the interface of a class into another interface that the client expects.
- **Use Case:** When integrating incompatible systems.

---

### **2. Bridge Pattern**
- **Purpose:** Separates an abstraction from its implementation so that both can evolve independently.
- **Use Case:** When designing a system with multiple dimensions of variability.

---

### **3. Composite Pattern**
- **Purpose:** Composes objects into tree-like structures to represent part-whole hierarchies.
- **Use Case:** When objects need to be treated uniformly regardless of being individual or composite.

---

### **4. Decorator Pattern**
- **Purpose:** Adds responsibilities to an object dynamically without modifying its code.
- **Use Case:** When extending functionality without altering existing code.

---

### **5. Facade Pattern**
- **Purpose:** Provides a simplified interface to a complex system or subsystem.
- **Use Case:** When you want to hide the complexity of a subsystem from the client.

---

### **6. Flyweight Pattern**
- **Purpose:** Shares objects to minimize memory usage when dealing with large numbers of similar objects.
- **Use Case:** When managing a large number of fine-grained objects efficiently.

---

### **7. Proxy Pattern**
- **Purpose:** Provides a placeholder or surrogate to control access to another object.
- **Use Case:** When you need controlled access, lazy initialization, or logging for an object.

---

### **Overview Table**

| **Pattern**  | **Purpose**                                     | **Real-world Example**                   |
|--------------|-------------------------------------------------|------------------------------------------|
| Adapter      | Converts one interface into another            | Phone charger adapting different plugs   |
| Bridge       | Separates abstraction from implementation      | Graphic rendering: 2D or 3D APIs         |
| Composite    | Tree-like structures for part-whole hierarchy  | Filesystem: Folders and files            |
| Decorator    | Dynamically adds behavior                      | Adding scrollbars to a text view         |
| Facade       | Simplifies complex subsystems                  | Hotel booking via a travel portal        |
| Flyweight    | Shares common objects to reduce memory usage   | Text rendering with shared characters    |
| Proxy        | Controls access to objects                     | ATM machine accessing bank account       |

---

### **How to Approach Each Pattern**
1. **Adapter:** Focus on integrating systems with incompatible interfaces.
2. **Bridge:** Design for systems with orthogonal dimensions of variability.
3. **Composite:** Build recursive structures that require uniform treatment.
4. **Decorator:** Dynamically add functionality without subclassing.
5. **Facade:** Simplify the client’s interaction with a complex subsystem.
6. **Flyweight:** Optimize memory by reusing objects.
7. **Proxy:** Add controlled access, logging, or lazy initialization for objects.

---



In **Spring Boot**, **Structural Design Patterns** are widely utilized to simplify and organize the architecture of the application. Here’s how each of the structural patterns is typically applied in Spring Boot applications:

---

### **1. Adapter Pattern in Spring Boot**
**Where Used:**
- The **Adapter Pattern** is used to convert interfaces between incompatible systems or subsystems.
- It’s commonly used when integrating third-party libraries or APIs.

**Example in Spring Boot:**
Imagine integrating a third-party payment gateway that has a different interface from what your system expects.

```java
// Target Interface
public interface PaymentService {
    void processPayment(double amount);
}

// Adaptee (Third-party class)
public class ThirdPartyPaymentGateway {
    public void makePayment(String paymentDetails) {
        // Third-party payment logic
        System.out.println("Payment of amount processed: " + paymentDetails);
    }
}

// Adapter
public class PaymentAdapter implements PaymentService {
    private ThirdPartyPaymentGateway gateway;

    public PaymentAdapter(ThirdPartyPaymentGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public void processPayment(double amount) {
        // Adapting the interface
        String paymentDetails = "Amount: " + amount;
        gateway.makePayment(paymentDetails);
    }
}

// Usage in Spring Boot Service
@Service
public class PaymentProcessingService {
    private final PaymentService paymentService;

    public PaymentProcessingService() {
        this.paymentService = new PaymentAdapter(new ThirdPartyPaymentGateway());
    }

    public void makePayment(double amount) {
        paymentService.processPayment(amount);
    }
}
```

**Real-World Use:**
- Integrating external services or third-party libraries into your Spring Boot system, where their API doesn't match your internal service's expected interface.

---

### **2. Bridge Pattern in Spring Boot**
**Where Used:**
- The **Bridge Pattern** helps in separating abstraction from implementation, which is useful when you have multiple variants of an abstraction and different implementation logic for each.
- It is often used when dealing with complex business logic or multiple platforms.

**Example in Spring Boot:**
Imagine you want to render content for both web and mobile apps, with the rendering strategy changing based on the platform.

```java
// Abstraction
public abstract class ContentRenderer {
    protected RenderingEngine engine;

    public ContentRenderer(RenderingEngine engine) {
        this.engine = engine;
    }

    public abstract void renderContent(String content);
}

// Implementor
public interface RenderingEngine {
    void render(String content);
}

// Concrete Implementor
public class WebRenderingEngine implements RenderingEngine {
    @Override
    public void render(String content) {
        System.out.println("Rendering for Web: " + content);
    }
}

public class MobileRenderingEngine implements RenderingEngine {
    @Override
    public void render(String content) {
        System.out.println("Rendering for Mobile: " + content);
    }
}

// Refined Abstraction
public class HtmlContentRenderer extends ContentRenderer {
    public HtmlContentRenderer(RenderingEngine engine) {
        super(engine);
    }

    @Override
    public void renderContent(String content) {
        engine.render("<html>" + content + "</html>");
    }
}

// Usage
@Service
public class ContentService {
    private ContentRenderer renderer;

    public ContentService() {
        this.renderer = new HtmlContentRenderer(new WebRenderingEngine());
    }

    public void renderContent(String content) {
        renderer.renderContent(content);
    }
}
```

**Real-World Use:**
- If you need to support multiple platforms (like mobile and web) and want to switch the implementation of rendering, this pattern is highly beneficial.

---

### **3. Composite Pattern in Spring Boot**
**Where Used:**
- The **Composite Pattern** is useful in building hierarchical structures (like file systems or UI components) that need to be treated uniformly regardless of whether they are a single object or a composite collection.

**Example in Spring Boot:**
Imagine you want to model a file system with files and directories. Both should implement the same interface so you can treat them uniformly.

```java
// Component
public interface FileSystemComponent {
    void display();
}

// Leaf
public class File implements FileSystemComponent {
    private String name;

    public File(String name) {
        this.name = name;
    }

    @Override
    public void display() {
        System.out.println("File: " + name);
    }
}

// Composite
public class Directory implements FileSystemComponent {
    private String name;
    private List<FileSystemComponent> components = new ArrayList<>();

    public Directory(String name) {
        this.name = name;
    }

    public void addComponent(FileSystemComponent component) {
        components.add(component);
    }

    @Override
    public void display() {
        System.out.println("Directory: " + name);
        for (FileSystemComponent component : components) {
            component.display();
        }
    }
}

// Usage in Spring Boot
@Service
public class FileSystemService {
    public void printFileSystem() {
        FileSystemComponent directory = new Directory("Root");
        directory.addComponent(new File("file1.txt"));
        directory.addComponent(new File("file2.txt"));

        FileSystemComponent subDir = new Directory("SubDir");
        subDir.addComponent(new File("subfile1.txt"));
        
        directory.addComponent(subDir);

        directory.display();
    }
}
```

**Real-World Use:**
- A **menu system** in a web application where both individual menu items and groups of menu items are treated uniformly.

---

### **4. Decorator Pattern in Spring Boot**
**Where Used:**
- The **Decorator Pattern** is used when you want to dynamically add additional responsibilities to an object at runtime. This is useful in scenarios where subclassing is not feasible or too rigid.

**Example in Spring Boot:**
Imagine you have a logging service and want to add more logging behavior dynamically.

```java
// Component
public interface LoggingService {
    void log(String message);
}

// Concrete Component
public class BasicLoggingService implements LoggingService {
    @Override
    public void log(String message) {
        System.out.println("Logging: " + message);
    }
}

// Decorator
public class AdvancedLoggingServiceDecorator implements LoggingService {
    private LoggingService wrappedService;

    public AdvancedLoggingServiceDecorator(LoggingService service) {
        this.wrappedService = service;
    }

    @Override
    public void log(String message) {
        wrappedService.log(message);  // Base log
        System.out.println("Advanced logging with timestamp: " + System.currentTimeMillis());
    }
}

// Usage in Spring Boot
@Service
public class LoggingServiceHandler {
    private final LoggingService loggingService;

    public LoggingServiceHandler() {
        loggingService = new AdvancedLoggingServiceDecorator(new BasicLoggingService());
    }

    public void logMessage(String message) {
        loggingService.log(message);
    }
}
```

**Real-World Use:**
- **Spring Security** dynamically adds behavior (authentication, logging) without modifying core business logic.

---

### **5. Facade Pattern in Spring Boot**
**Where Used:**
- **Facade Pattern** is used when you want to provide a simplified interface to a complex subsystem. In Spring Boot, this can be used to simplify the interaction with complex services or configurations.

**Example in Spring Boot:**
Imagine a complex subsystem where you need to configure and interact with different services.

```java
// Subsystems
@Service
public class EmailService {
    public void sendEmail(String to) {
        System.out.println("Sending email to: " + to);
    }
}

@Service
public class SMSService {
    public void sendSMS(String to) {
        System.out.println("Sending SMS to: " + to);
    }
}

// Facade
public class CommunicationFacade {
    private EmailService emailService;
    private SMSService smsService;

    public CommunicationFacade(EmailService emailService, SMSService smsService) {
        this.emailService = emailService;
        this.smsService = smsService;
    }

    public void sendCommunication(String email, String phone) {
        emailService.sendEmail(email);
        smsService.sendSMS(phone);
    }
}

// Usage in Spring Boot
@Service
public class CommunicationHandler {
    private final CommunicationFacade communicationFacade;

    public CommunicationHandler() {
        communicationFacade = new CommunicationFacade(new EmailService(), new SMSService());
    }

    public void notifyUser(String email, String phone) {
        communicationFacade.sendCommunication(email, phone);
    }
}
```

**Real-World Use:**
- Used to simplify interactions with multiple systems, like the integration of payment gateways, logging systems, or messaging services.

---

### **6. Flyweight Pattern in Spring Boot**
**Where Used:**
- **Flyweight Pattern** can be used to minimize memory usage when dealing with large numbers of objects. It’s often used when creating reusable objects for repetitive tasks.

**Example in Spring Boot:**
Imagine you’re storing lots of characters in a text-processing system where many characters repeat.

```java
// Flyweight
public class Character {
    private final char value;

    public Character(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }
}

// Flyweight Factory
public class CharacterFactory {
    private final Map<Character, Character> characterPool = new HashMap<>();

    public Character getCharacter(char value) {
        if (!characterPool.containsKey(value)) {
            characterPool.put(value, new Character(value));
        }
        return characterPool.get(value);
    }
}

// Usage in Spring Boot
@Service
public class TextProcessor {
    private final CharacterFactory characterFactory;

    public TextProcessor() {
        characterFactory = new CharacterFactory();
    }

    public void printText(String text) {
        for (char c : text.toCharArray()) {
            System.out.println(characterFactory.getCharacter(c).getValue());
        }
    }
}
```

**Real-World Use:**
- Managing large-scale applications

 with repetitive data, such as in **text rendering**, **game development**, or **networked applications**.

---

### **7. Proxy Pattern in Spring Boot**
**Where Used:**
- The **Proxy Pattern** is used for controlling access to objects. In Spring Boot, it is often used in lazy loading, security, or when managing expensive resources.

**Example in Spring Boot:**
Imagine you have a service that performs heavy computation, and you want to delay its instantiation until needed.

```java
// Subject
public interface DataService {
    void fetchData();
}

// RealSubject
public class DataServiceImpl implements DataService {
    @Override
    public void fetchData() {
        System.out.println("Fetching data...");
    }
}

// Proxy
public class DataServiceProxy implements DataService {
    private DataServiceImpl realService;

    @Override
    public void fetchData() {
        if (realService == null) {
            realService = new DataServiceImpl();
        }
        realService.fetchData();
    }
}

// Usage in Spring Boot
@Service
public class DataServiceHandler {
    private final DataService dataService;

    public DataServiceHandler() {
        dataService = new DataServiceProxy();
    }

    public void getData() {
        dataService.fetchData();
    }
}
```

**Real-World Use:**
- **Spring AOP** uses proxy patterns for adding behaviors like logging, transaction management, etc.

---

In **Spring Boot**, these patterns help **manage complexity**, **improve flexibility**, and make your system **extensible**. If you're working with a microservices architecture, integration, or complex configurations, these patterns are very useful.

---
