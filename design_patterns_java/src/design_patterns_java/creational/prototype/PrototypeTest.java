package design_patterns_java.creational.prototype;

public class PrototypeTest {
	public static void main(String[] args) {
		try {
			// Step 1: Create Original Object
			Prototype original = new Prototype("Original", 42);

			// Step 2: Clone the Object
			Prototype clone = (Prototype) original.clone();

			// Step 3: Modify the Cloned Object
			clone.setName("Clone");
			clone.setValue(99);

			// Step 4: Verify
			System.out.println("Original Object: " + original);
			System.out.println("Cloned Object: " + clone);

		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
}
