### **State Design Pattern**

The **State Design Pattern** is a **behavioral pattern** used to manage state-dependent behavior. It allows an object to change its behavior when its internal state changes, as if the object were an instance of another class.

---

### **Why Use the State Pattern?**

- **State-specific behavior:** When an object’s behavior depends on its state, and it must change behavior dynamically.
- **Avoiding conditional logic:** Reduces complex `if-else` or `switch` statements for handling state transitions.
- **Encapsulation:** Each state is represented as a separate class, encapsulating state-specific behavior.

---

### **Scenario**
Consider a **vending machine**:
- It has states like `NoCoinInserted`, `CoinInserted`, and `Dispensing`.
- The behavior (like inserting a coin, pressing a button, or dispensing) depends on its current state.

Using the State Pattern, you can represent each state as a class, with the vending machine delegating requests to the current state object.

---

### **Key Components**

1. **Context:**
   - The object whose behavior changes with its state.
   - Maintains a reference to the current state and delegates requests to it.

2. **State (Interface):**
   - Defines a common interface for all states.
   - Contains the methods that define state-specific behavior.

3. **Concrete States:**
   - Implement the `State` interface and define behavior specific to the state.

---

### **Implementation Example**

#### **Step 1: Define the State Interface**
```java
public interface State {
    void insertCoin();
    void pressButton();
    void dispense();
}
```

#### **Step 2: Create Concrete States**
```java
public class NoCoinInsertedState implements State {
    private VendingMachine machine;

    public NoCoinInsertedState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertCoin() {
        System.out.println("Coin inserted.");
        machine.setState(machine.getCoinInsertedState());
    }

    @Override
    public void pressButton() {
        System.out.println("Please insert a coin first.");
    }

    @Override
    public void dispense() {
        System.out.println("Insert a coin before dispensing.");
    }
}

public class CoinInsertedState implements State {
    private VendingMachine machine;

    public CoinInsertedState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertCoin() {
        System.out.println("Coin already inserted.");
    }

    @Override
    public void pressButton() {
        System.out.println("Button pressed. Dispensing item...");
        machine.setState(machine.getDispensingState());
    }

    @Override
    public void dispense() {
        System.out.println("Press the button to dispense.");
    }
}

public class DispensingState implements State {
    private VendingMachine machine;

    public DispensingState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertCoin() {
        System.out.println("Wait! Currently dispensing an item.");
    }

    @Override
    public void pressButton() {
        System.out.println("Already dispensing an item.");
    }

    @Override
    public void dispense() {
        System.out.println("Item dispensed.");
        machine.setState(machine.getNoCoinInsertedState());
    }
}
```

#### **Step 3: Create the Context Class**
```java
public class VendingMachine {
    private State noCoinInsertedState;
    private State coinInsertedState;
    private State dispensingState;

    private State currentState;

    public VendingMachine() {
        noCoinInsertedState = new NoCoinInsertedState(this);
        coinInsertedState = new CoinInsertedState(this);
        dispensingState = new DispensingState(this);

        currentState = noCoinInsertedState;  // Initial state
    }

    public void insertCoin() {
        currentState.insertCoin();
    }

    public void pressButton() {
        currentState.pressButton();
    }

    public void dispense() {
        currentState.dispense();
    }

    public void setState(State state) {
        currentState = state;
    }

    public State getNoCoinInsertedState() {
        return noCoinInsertedState;
    }

    public State getCoinInsertedState() {
        return coinInsertedState;
    }

    public State getDispensingState() {
        return dispensingState;
    }
}
```

#### **Step 4: Test the State Pattern**
```java
public class StatePatternDemo {
    public static void main(String[] args) {
        VendingMachine machine = new VendingMachine();

        machine.pressButton(); // No coin inserted yet
        machine.insertCoin();  // Coin inserted
        machine.pressButton(); // Button pressed
        machine.insertCoin();  // Wait for dispensing to complete
        machine.dispense();    // Dispense item
    }
}
```

---

### **Output**
```
Please insert a coin first.
Coin inserted.
Button pressed. Dispensing item...
Wait! Currently dispensing an item.
Item dispensed.
```

---

### **Key Points**
1. **Dynamic Behavior:** The `VendingMachine` object's behavior changes dynamically depending on its current state.
2. **Delegation:** The context class (`VendingMachine`) delegates state-specific behavior to the current state object.
3. **Encapsulation:** Each state class encapsulates the behavior specific to that state.

---

### **Advantages**
1. **Encapsulation of States:** Each state is encapsulated in its own class, making the system easier to maintain and extend.
2. **No Complex Conditionals:** Eliminates the need for complex `if-else` or `switch` statements to handle state transitions.
3. **Flexibility:** Adding new states or modifying existing ones is straightforward without affecting other parts of the system.

### **Disadvantages**
1. **Increased Number of Classes:** Each state requires a separate class, which can increase the complexity of the system.
2. **Context Awareness:** The state classes need a reference to the context to switch states.

---

### **Real-World Use Cases**
1. **Workflow Management Systems:** Managing different states in a workflow, such as draft, review, and published.
2. **UI Components:** A button can have different behaviors based on its state (enabled, disabled, hovered, clicked).
3. **Game Development:** Characters can have different behaviors depending on their state (walking, running, attacking).

---

### **State Pattern in Spring Framework**
The State Pattern is often used implicitly in Spring for:
- **Spring State Machine:** Helps manage application states and state transitions.
- **Security:** Spring Security can handle state transitions in an authentication flow, such as pre-authentication, post-authentication, etc.

---
