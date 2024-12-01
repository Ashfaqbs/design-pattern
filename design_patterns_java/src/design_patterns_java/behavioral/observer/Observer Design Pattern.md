### **Observer Design Pattern in Java**

The **Observer Design Pattern** defines a one-to-many relationship where multiple observers are notified whenever the subject (observable) changes its state.

---

### **Scenario**
Suppose we have a stock price tracking system. When the stock price changes, all registered observers (e.g., mobile apps, websites, email alerts) should be notified of the change.

---

### **Key Components**
1. **Subject (Observable):** Maintains a list of observers and notifies them when its state changes.
2. **Observer:** Defines an interface to update observers when notified.
3. **Concrete Subject:** Implements the subject and handles observer registration and state changes.
4. **Concrete Observer:** Implements the observer and defines how to react to state changes.

---

### **Implementation**

#### **Step 1: Define the Observer Interface**
```java
public interface Observer {
    void update(String stockName, double price);
}
```

#### **Step 2: Define the Subject Interface**
```java
import java.util.ArrayList;
import java.util.List;

public interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}
```

#### **Step 3: Implement the Concrete Subject**
```java
public class StockMarket implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private String stockName;
    private double price;

    public void setStockPrice(String stockName, double price) {
        this.stockName = stockName;
        this.price = price;
        notifyObservers(); // Notify all observers whenever price changes
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(stockName, price);
        }
    }
}
```

#### **Step 4: Implement Concrete Observers**
```java
public class MobileApp implements Observer {
    private String appName;

    public MobileApp(String appName) {
        this.appName = appName;
    }

    @Override
    public void update(String stockName, double price) {
        System.out.println(appName + " received an update: Stock " + stockName + " is now $" + price);
    }
}

public class Website implements Observer {
    private String siteName;

    public Website(String siteName) {
        this.siteName = siteName;
    }

    @Override
    public void update(String stockName, double price) {
        System.out.println(siteName + " received an update: Stock " + stockName + " is now $" + price);
    }
}
```

#### **Step 5: Test the Observer Pattern**
```java
public class Client {
    public static void main(String[] args) {
        StockMarket stockMarket = new StockMarket();

        Observer mobileApp = new MobileApp("StockApp");
        Observer website = new Website("FinanceWebsite");

        stockMarket.registerObserver(mobileApp);
        stockMarket.registerObserver(website);

        stockMarket.setStockPrice("AAPL", 145.67);
        stockMarket.setStockPrice("GOOGL", 2732.45);

        stockMarket.removeObserver(mobileApp);

        stockMarket.setStockPrice("MSFT", 299.89);
    }
}
```

---

#### **Output**
```
StockApp received an update: Stock AAPL is now $145.67
FinanceWebsite received an update: Stock AAPL is now $145.67
StockApp received an update: Stock GOOGL is now $2732.45
FinanceWebsite received an update: Stock GOOGL is now $2732.45
FinanceWebsite received an update: Stock MSFT is now $299.89
```

---

### **Key Points**
1. **Dynamic Behavior:** Observers can be added or removed dynamically at runtime.
2. **Encapsulation:** The subject knows only about the observer interface, ensuring loose coupling.
3. **Reusability:** Observers can be reused across different subjects.

---

### **Advantages**
- Ensures loose coupling between subject and observers.
- Allows multiple observers to react to state changes independently.

---

### **Disadvantages**
- Overhead of notifying many observers.
- Risk of observers causing performance issues if they perform heavy operations.

---

### **Real-World Use Cases**
1. **Event Listeners in GUIs.**  
   For example, `ActionListener` in Swing/AWT components.
   
2. **Publish/Subscribe Mechanisms.**  
   Messaging frameworks like RabbitMQ or Kafka.

3. **Spring Boot Event Listeners.**  
   Using `@EventListener` to handle custom application events.

---
