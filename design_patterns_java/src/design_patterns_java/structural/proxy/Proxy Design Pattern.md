The **Proxy Design Pattern** is a structural design pattern that provides a **surrogate or placeholder** for another object to control access to it. The proxy object acts as an intermediary between the client and the real object, adding functionality such as access control, lazy initialization, logging, or caching.

---

### **Why Proxy?**

1. **Problem it Solves:**  
   Direct access to an object may not always be possible or desirable. For instance:
   - The object might be located remotely.
   - It could be resource-intensive to create or load the object.
   - You may want to control access to the object for security reasons.

2. **Goal:**  
   To provide a substitute or placeholder that controls access to the real object, while maintaining the same interface.

---

### **Types of Proxies**

1. **Remote Proxy:**  
   Represents an object in a different address space, such as a server in a distributed system.
   
2. **Virtual Proxy:**  
   Controls access to a resource that is expensive to create, initializing it lazily when needed.
   
3. **Protection Proxy:**  
   Controls access to an object based on permissions or roles.
   
4. **Caching Proxy:**  
   Provides temporary storage for results of operations to enhance performance.

---

### **Example: Virtual Proxy**

Let's consider a scenario where loading a large image from the disk is resource-intensive, and we want to delay its loading until absolutely necessary.

#### **Step 1: Subject Interface**
```java
public interface Image {
    void display();
}
```

#### **Step 2: Real Object**
```java
public class RealImage implements Image {
    private String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk();
    }

    private void loadFromDisk() {
        System.out.println("Loading image: " + fileName);
    }

    @Override
    public void display() {
        System.out.println("Displaying image: " + fileName);
    }
}
```

#### **Step 3: Proxy Object**
```java
public class ProxyImage implements Image {
    private String fileName;
    private RealImage realImage;

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(fileName); // Load the image lazily
        }
        realImage.display();
    }
}
```

#### **Step 4: Client Code**
```java
public class Client {
    public static void main(String[] args) {
        Image image1 = new ProxyImage("Photo1.jpg");
        Image image2 = new ProxyImage("Photo2.jpg");

        // Image is not loaded yet
        System.out.println("Displaying first image...");
        image1.display(); // Loads and displays the image

        System.out.println("Displaying second image...");
        image2.display(); // Loads and displays the image
    }
}
```

---

#### **Output:**
```
Displaying first image...
Loading image: Photo1.jpg
Displaying image: Photo1.jpg
Displaying second image...
Loading image: Photo2.jpg
Displaying image: Photo2.jpg
```

---

### **How It Works:**
1. **ProxyImage** acts as a stand-in for the **RealImage** object.  
2. The image is not loaded from disk until the `display()` method is called, saving resources if the image is never displayed.

---

### **Advantages:**
1. **Lazy Initialization:** Resources are only used when necessary.
2. **Access Control:** You can restrict access based on conditions, such as user roles or permissions.
3. **Separation of Concerns:** Additional responsibilities like caching or logging can be handled in the proxy without modifying the real object.
4. **Remote Interaction:** Allows communication with objects located on different machines.

---

### **Use Cases in Real Life:**
1. **Spring AOP (Aspect-Oriented Programming):**  
   Proxies are used to wrap beans and provide additional behavior, such as logging or transaction management.

2. **Java RMI (Remote Method Invocation):**  
   Remote Proxies represent objects located on remote servers.

3. **Hibernate:**  
   Proxies are used for lazy loading of entities in JPA/Hibernate.

4. **Security Frameworks:**  
   Proxies can restrict access to methods or data based on roles.

---

### **When to Use Proxy?**
- When you need to control access to an object.
- When creating the object is expensive, and you want to use it lazily.
- When you want to enhance functionality, like adding logging, without modifying the real object.

---

### **Proxy vs Decorator vs Adapter**
- **Proxy:** Controls access and may add behavior. It represents the same interface as the real object.  
- **Decorator:** Adds new functionality to an object dynamically, often wrapping multiple times.  
- **Adapter:** Converts one interface into another to make incompatible classes work together.

---
