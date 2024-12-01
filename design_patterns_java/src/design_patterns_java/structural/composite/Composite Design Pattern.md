The **Composite Design Pattern** is a structural design pattern used to treat individual objects and compositions of objects **uniformly**. It’s especially useful when working with **tree-like hierarchical structures**, such as file systems, organizational charts, or UI components.

---

### **Why Composite Pattern?**

1. **Problem it Solves:**  
   Suppose you have an application managing a hierarchy of objects where some objects are **simple** (e.g., a single file) and others are **composite** (e.g., a folder containing files and subfolders). You need a way to interact with both types **consistently** without knowing whether you're dealing with a simple or composite object.

2. **Goal:**  
   The Composite Pattern lets you **treat individual objects (leaf)** and **compositions of objects (composite)** the same way by using a **common interface**.

---

### **Key Concepts**
1. **Component:**  
   The base interface or abstract class for all objects in the composition (both simple and composite).

2. **Leaf:**  
   Represents individual objects that do not have child components (e.g., a single file).

3. **Composite:**  
   Represents objects that can hold child components (e.g., a folder).

4. **Client:**  
   Interacts with objects through the **Component interface**, without worrying if it’s a leaf or a composite.

---

### **Scenario Example: File System**

#### **Step 1: Component Interface**
```java
public interface FileSystemComponent {
    void showDetails();
}
```

#### **Step 2: Leaf Class**
```java
public class File implements FileSystemComponent {
    private String name;

    public File(String name) {
        this.name = name;
    }

    @Override
    public void showDetails() {
        System.out.println("File: " + name);
    }
}
```

#### **Step 3: Composite Class**
```java
import java.util.ArrayList;
import java.util.List;

public class Folder implements FileSystemComponent {
    private String name;
    private List<FileSystemComponent> components = new ArrayList<>();

    public Folder(String name) {
        this.name = name;
    }

    public void addComponent(FileSystemComponent component) {
        components.add(component);
    }

    public void removeComponent(FileSystemComponent component) {
        components.remove(component);
    }

    @Override
    public void showDetails() {
        System.out.println("Folder: " + name);
        for (FileSystemComponent component : components) {
            component.showDetails();
        }
    }
}
```

#### **Step 4: Client Code**
```java
public class Client {
    public static void main(String[] args) {
        // Create files
        File file1 = new File("Document1.txt");
        File file2 = new File("Picture1.png");
        File file3 = new File("Video1.mp4");

        // Create a folder and add files to it
        Folder folder1 = new Folder("My Documents");
        folder1.addComponent(file1);
        folder1.addComponent(file2);

        // Create another folder
        Folder folder2 = new Folder("My Media");
        folder2.addComponent(file3);

        // Add folder2 to folder1
        folder1.addComponent(folder2);

        // Show details of folder1
        folder1.showDetails();
    }
}
```

---

#### **Output:**
```
Folder: My Documents
File: Document1.txt
File: Picture1.png
Folder: My Media
File: Video1.mp4
```

---

### **How It Works:**
1. **Uniformity:**  
   The `showDetails()` method is called on both `File` (leaf) and `Folder` (composite) using the same interface (`FileSystemComponent`).

2. **Hierarchy Handling:**  
   Composites (`Folder`) can contain other components (`File` or `Folder`), allowing you to create nested structures like trees.

3. **Extensibility:**  
   Adding new types of components is easy—just implement the `FileSystemComponent` interface.

---

### **Real-World Use Cases:**
- **File Systems:** Files and folders.
- **UI Frameworks:** Components like buttons, panels, and windows.
- **Organizational Structures:** Employees and departments.

---

### **When to Use Composite?**
- When you need to represent part-whole hierarchies (e.g., trees).
- When you want clients to treat individual objects and groups of objects the same way.

---
