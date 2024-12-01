### **Behavioral Design Patterns**  

**Behavioral patterns** focus on how objects interact and communicate with each other. They define common patterns of communication between objects to make the system more flexible and easier to maintain.

---

### **Key Characteristics of Behavioral Patterns**
1. **Object Interaction:** Focus on the interaction between objects.
2. **Encapsulation of Behavior:** Encapsulate behavior into objects or classes, making it easier to modify or extend.
3. **Dynamic Behavior:** Patterns can introduce flexibility by dynamically changing the way objects interact.

---

### **Types of Behavioral Design Patterns**

1. **Chain of Responsibility**  
   - **Purpose:** Pass a request along a chain of handlers until one handles it.
   - **Use Case:** Logging frameworks where different levels (info, debug, error) are processed by different loggers.

2. **Command**  
   - **Purpose:** Encapsulate a request as an object, allowing you to parameterize methods, delay execution, or queue requests.
   - **Use Case:** Undo/Redo functionality in text editors.

3. **Interpreter**  
   - **Purpose:** Define a grammar for a language and provide an interpreter to process sentences in the language.
   - **Use Case:** Rule-based engines or regular expression interpreters.

4. **Iterator**  
   - **Purpose:** Provide a way to sequentially access elements of a collection without exposing its underlying representation.
   - **Use Case:** Iterating over a `List`, `Set`, or `Map` in Java.

5. **Mediator**  
   - **Purpose:** Reduce direct communication between objects by introducing a mediator object to handle interactions.
   - **Use Case:** Chat applications where a server (mediator) handles communication between users.

6. **Memento**  
   - **Purpose:** Capture and store the state of an object to restore it later without violating encapsulation.
   - **Use Case:** Save game functionality in video games.

7. **Observer**  
   - **Purpose:** Notify multiple objects when the state of another object changes.
   - **Use Case:** Event-driven systems like GUIs or publish/subscribe mechanisms.

8. **State**  
   - **Purpose:** Allow an object to change its behavior when its internal state changes.
   - **Use Case:** A document editor where a file can be in different states like Draft, Review, or Published.

9. **Strategy**  
   - **Purpose:** Define a family of algorithms, encapsulate each one, and make them interchangeable.
   - **Use Case:** Sorting algorithms like QuickSort, MergeSort, etc., chosen at runtime.

10. **Template Method**  
    - **Purpose:** Define the skeleton of an algorithm in a superclass, allowing subclasses to implement specific steps.
    - **Use Case:** Abstract classes in Java like `HttpServlet` where `doGet` and `doPost` methods are implemented by subclasses.

11. **Visitor**  
    - **Purpose:** Allow adding new operations to objects without modifying their structure.
    - **Use Case:** Processing nodes of a tree-like structure (e.g., XML, AST).

---

### **When to Use Behavioral Patterns**
1. When you want to change how objects interact without changing their underlying implementations.
2. When a system requires flexible communication between objects.
3. When you need to define or modify the flow of control in a dynamic way.

---
