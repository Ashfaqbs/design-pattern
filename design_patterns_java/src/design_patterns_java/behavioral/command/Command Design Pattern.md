### **Command Design Pattern**

The **Command Design Pattern** is a **behavioral design pattern** that turns a request into a stand-alone object containing all the information about the request. This pattern allows you to parameterize objects with operations, delay their execution, or queue and log them.

---

### **Why Use the Command Pattern?**
1. **Encapsulation of Requests:** Encapsulates a request as an object, separating the sender from the receiver.
2. **Undo/Redo Operations:** Allows support for undo and redo operations by maintaining a history of executed commands.
3. **Decoupling:** Decouples the object that invokes the operation from the one that knows how to execute it.
4. **Command Queues:** Useful for task scheduling and request logging.

---

### **Scenario**
Imagine a **remote control** for a home automation system:
- You can turn lights on or off, start or stop a fan, etc.
- Each button press is a command.
- The remote control (Invoker) doesn’t know the details of how the light or fan operates; it just triggers a command.

---

### **Key Components**
1. **Command Interface:**
   - Declares the `execute()` method for executing commands.
   
2. **Concrete Commands:**
   - Implement the `Command` interface to define actions for specific operations.

3. **Receiver:**
   - The object that performs the actual operation when the command is executed.

4. **Invoker:**
   - Triggers the execution of commands without knowing their implementation.

5. **Client:**
   - Configures the objects and links the invoker with appropriate commands.

---

### **Implementation Example**

#### **Step 1: Define the Command Interface**
```java
public interface Command {
    void execute();
}
```

#### **Step 2: Create the Receiver Classes**
```java
class Light {
    public void turnOn() {
        System.out.println("Light is ON");
    }

    public void turnOff() {
        System.out.println("Light is OFF");
    }
}

class Fan {
    public void start() {
        System.out.println("Fan is STARTED");
    }

    public void stop() {
        System.out.println("Fan is STOPPED");
    }
}
```

#### **Step 3: Create Concrete Command Classes**
```java
class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }
}

class LightOffCommand implements Command {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff();
    }
}

class FanStartCommand implements Command {
    private Fan fan;

    public FanStartCommand(Fan fan) {
        this.fan = fan;
    }

    @Override
    public void execute() {
        fan.start();
    }
}

class FanStopCommand implements Command {
    private Fan fan;

    public FanStopCommand(Fan fan) {
        this.fan = fan;
    }

    @Override
    public void execute() {
        fan.stop();
    }
}
```

#### **Step 4: Create the Invoker**
```java
class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        if (command != null) {
            command.execute();
        } else {
            System.out.println("No command set");
        }
    }
}
```

#### **Step 5: Test the Command Pattern**
```java
public class CommandPatternDemo {
    public static void main(String[] args) {
        // Receivers
        Light light = new Light();
        Fan fan = new Fan();

        // Commands
        Command lightOn = new LightOnCommand(light);
        Command lightOff = new LightOffCommand(light);
        Command fanStart = new FanStartCommand(fan);
        Command fanStop = new FanStopCommand(fan);

        // Invoker
        RemoteControl remote = new RemoteControl();

        // Test Light Commands
        remote.setCommand(lightOn);
        remote.pressButton();

        remote.setCommand(lightOff);
        remote.pressButton();

        // Test Fan Commands
        remote.setCommand(fanStart);
        remote.pressButton();

        remote.setCommand(fanStop);
        remote.pressButton();
    }
}
```

---

### **Output**
```
Light is ON
Light is OFF
Fan is STARTED
Fan is STOPPED
```

---

### **Key Points**
1. **Command Object:** Encapsulates the request (e.g., `LightOnCommand` encapsulates the `turnOn()` method).
2. **Invoker:** The remote control knows only how to trigger commands, not their implementation.
3. **Receiver:** Light and Fan execute the actual operations.

---

### **Advantages**
1. **Decoupling:** Invoker and Receiver are decoupled.
2. **Flexibility:** Commands can be parameterized and swapped dynamically.
3. **Undo/Redo Support:** Command history can be maintained for undo/redo operations.
4. **Scalability:** Adding new commands is straightforward.

### **Disadvantages**
1. **Overhead:** Increases the number of classes, especially for simple scenarios.
2. **Complexity:** May introduce unnecessary complexity for trivial use cases.

---

### **Real-World Use Cases**
1. **GUI Applications:** Buttons or menu items can trigger commands.
   - Example: Undo/Redo operations in text editors.
2. **Task Scheduling:** Scheduling or queuing commands for later execution.
3. **Transaction Management:** Commands can represent individual transactions.

---

### **Command Pattern in Spring Framework**
1. **Spring MVC:** The `@RequestMapping` or `@GetMapping` annotations can be viewed as a way of mapping commands to specific actions.
2. **Asynchronous Commands:** Spring's `@Async` can be used to trigger command execution in a separate thread.
3. **Job Scheduling:** Spring Batch uses command-like objects to define and execute jobs.

---
