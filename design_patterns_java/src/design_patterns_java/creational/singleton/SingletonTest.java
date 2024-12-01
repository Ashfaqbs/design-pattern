package design_patterns_java.creational.singleton;

public class SingletonTest {
	public static void main(String[] args) throws Exception {
		// Verify Single Instance
		Singleton instance1 = Singleton.getInstance();
		Singleton instance2 = Singleton.getInstance();

		System.out.println("Instance 1 HashCode: " + instance1.hashCode());
		System.out.println("Instance 2 HashCode: " + instance2.hashCode());
		System.out.println("Both instances are the same: " + (instance1 == instance2));
	}
}
