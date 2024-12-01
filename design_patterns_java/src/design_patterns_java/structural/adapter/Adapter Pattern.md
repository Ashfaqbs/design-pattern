The **Adapter Pattern** allows you to convert one interface into another expected by the client, making incompatible interfaces compatible. It acts as a bridge between two interfaces. In simple terms, it allows classes to work together that couldn’t otherwise because of incompatible interfaces.

Here’s a basic example to demonstrate the **Adapter Pattern** in Java:

### **Problem:**
Imagine you are integrating a system where you have an existing service (e.g., `OldService`) that provides a method with an incompatible signature, and you want to make it work with a client expecting a different interface (e.g., `TargetService`).

---

### **Step 1: Define the Target Interface**

```java
// Target Interface: The interface that the client expects
public interface TargetService {
    void request();
}
```

---

### **Step 2: Define the Adaptee (Old Service)**

```java
// Adaptee: The existing service that needs adaptation
public class OldService {
    public void oldRequest() {
        System.out.println("Old Service is providing data.");
    }
}
```

---

### **Step 3: Define the Adapter**

The **Adapter** implements the **TargetService** interface and uses the **OldService** to delegate the request.

```java
// Adapter: Converts OldService to TargetService interface
public class AdapterService implements TargetService {
    private OldService oldService;

    public AdapterService(OldService oldService) {
        this.oldService = oldService;
    }

    @Override
    public void request() {
        // Adapting the old request to match the new interface
        oldService.oldRequest();
    }
}
```

---

### **Step 4: Using the Adapter**

Now, the client code can use `TargetService` as expected, and the **Adapter** makes sure that it delegates the call to the **OldService**.

```java
public class Client {
    public static void main(String[] args) {
        // Creating an instance of the old service
        OldService oldService = new OldService();
        
        // Creating the adapter that adapts the old service to the target service interface
        TargetService targetService = new AdapterService(oldService);
        
        // The client now interacts with the target service, unaware of the old service behind the scenes
        targetService.request();
    }
}
```

---

### **Output:**
```
Old Service is providing data.
```

---

### **Explanation:**
- **TargetService** is the interface the client expects to interact with.
- **OldService** is the existing class that provides a method (`oldRequest()`) that doesn't match the expected interface (`request()`).
- The **AdapterService** implements the `TargetService` interface and adapts the method call to `oldRequest()` from the `OldService`.

### **Why use this pattern?**
- If you need to **integrate old code** (that works fine but has a different interface) with **new systems** that use a different interface, the Adapter pattern allows you to avoid modifying the old code while still allowing it to fit into your new system.
