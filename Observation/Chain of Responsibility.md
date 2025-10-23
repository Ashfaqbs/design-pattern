# **Chain of Responsibility Design Pattern **

The **Chain of Responsibility** pattern lets a request travel along a sequence (chain) of handlers.
Each handler either **processes** the request or **forwards** it to the next handler.
This keeps the **sender** (client) **decoupled** (loosely connected) from the **receivers** (handlers).

---

## **Scenario**

A logging system needs to handle messages at different severities:

* **DEBUG** → handled by `DebugLogger`
* **INFO** → handled by `InfoLogger`
* **CRITICAL** (errors) → handled by `CriticalLogger`

Each logger only handles *its* level; otherwise it passes the message along.

---

## **Key Components**

1. **BaseLogger (Abstract Handler):**
   Holds a reference to the next handler and exposes a `log(message, level)` method that each subclass uses to decide: handle or forward.
2. **Concrete Handlers:**
   `DebugLogger`, `InfoLogger`, `CriticalLogger` — each one declares which level it can process.
3. **Client (`LoggerClient`):**
   Builds the chain and sends log requests to the **first** handler.

---

## **Why use it?**

* **Flexible responsibility assignment:** Add or reorder handlers without changing the client.
* **Loose coupling:** Client doesn’t know who will handle a given request.
* **Open for extension:** New loggers can be introduced with no changes to existing ones.

---

## **Implementation (Matches Your Current Code)**

### **Step 1: Base Handler**

```java
package designpatterns.behavioral.chainofresponsibility;

public abstract class BaseLogger {
    protected BaseLogger nextLogger;

    public void setNext(BaseLogger nextLogger) {
        this.nextLogger = nextLogger;
    }

    public void log(String message, int level) {
        if (canHandle(level)) {
            write(message);
        } else if (nextLogger != null) {
            nextLogger.log(message, level);
        }
    }

    protected abstract boolean canHandle(int level);
    protected abstract void write(String message);
}
```

### **Step 2: Concrete Handlers**

```java
package designpatterns.behavioral.chainofresponsibility;

public class DebugLogger extends BaseLogger {
    @Override
    protected boolean canHandle(int level) { return level == 1; }
    @Override
    protected void write(String message) { System.out.println("[DEBUG] " + message); }
}
```

```java
package designpatterns.behavioral.chainofresponsibility;

public class InfoLogger extends BaseLogger {
    @Override
    protected boolean canHandle(int level) { return level == 2; }
    @Override
    protected void write(String message) { System.out.println("[INFO] " + message); }
}
```

```java
package designpatterns.behavioral.chainofresponsibility;

public class CriticalLogger extends BaseLogger {
    @Override
    protected boolean canHandle(int level) { return level == 3; }
    @Override
    protected void write(String message) { System.out.println("[CRITICAL] " + message); }
}
```

### **Step 3: Client to Test the Chain**

```java
package designpatterns.behavioral.chainofresponsibility;

public class LoggerClient {
    public static void main(String[] args) {
        BaseLogger debug = new DebugLogger();
        BaseLogger info = new InfoLogger();
        BaseLogger critical = new CriticalLogger();

        // Build the chain (order matters)
        debug.setNext(info);
        info.setNext(critical);

        // Try different levels
        debug.log("System initialized successfully.", 2); // INFO
        debug.log("Variable x value is 42.", 1);          // DEBUG
        debug.log("Database connection failed!", 3);      // CRITICAL
    }
}
```

---

## **Output**

```
[INFO] System initialized successfully.
[DEBUG] Variable x value is 42.
[CRITICAL] Database connection failed!
```

---

## **Key Points**

1. **Request passing:** Each logger either handles or forwards.
2. **Decoupling:** The client calls only the first handler; the chain decides who processes.
3. **Order matters:** Build the chain in the order you want checks to occur.

---

## **Advantages**

* **Extensible (pluggable):** Add `WarningLogger`, `AuditLogger`, etc., without touching other classes.
* **Separation of concerns:** Each handler focuses on its own level.
* **Runtime configurability:** Chains can be rewired (reordered) on the fly.

## **Tradeoffs**

* **Traceability:** Long chains can make it harder to see who handled a message.
* **Latency:** A request may traverse several handlers before being processed.

---

## **Real-World Use Cases**

* **GUI event bubbling (propagation).**
* **HTTP middleware pipelines (auth, logging, rate limiting).**
* **Logging frameworks (per-level appenders/handlers).**

---

## **Spring Boot Analogy (Filters)**

Similar idea in a web filter chain — each filter can handle or forward:

```java
public class AuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
                                    throws ServletException, IOException {
        // auth logic...
        chain.doFilter(request, response); // forward to next
    }
}
```

---

## **Optional Enhancements (if you want incremental clarity)**

* **Use an enum for levels** (readable names instead of magic numbers):

  ```java
  public enum LogLevel { DEBUG(1), INFO(2), CRITICAL(3); public final int code; LogLevel(int c){code=c;} }
  // Then call: debug.log("...", LogLevel.INFO.code);
  ```
* **Allow multiple handlers to act** (broadcast): remove the `else` and always forward after `write(message)` to let downstream handlers also run.
* **Config-driven chain** (JSON/YAML): build chain from configuration to toggle handlers per environment.