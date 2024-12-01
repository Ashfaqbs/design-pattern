package design_patterns_java.creational.singleton;

import java.io.Serializable;

public class Singleton implements Serializable, Cloneable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Step 1: Private static instance
	private static volatile Singleton instance;

	// Step 2: Private constructor to prevent instantiation
	private Singleton() {
		if (instance != null) {
			throw new IllegalStateException("Instance already created!");
		}
	}

	// Step 3: Public static method to get the instance
	public static Singleton getInstance() {
		if (instance == null) { // Check 1
			synchronized (Singleton.class) {
				if (instance == null) { // Check 2
					instance = new Singleton();
				}
			}
		}
		return instance;
	}

	// Step 4: Ensure single instance during deserialization
	protected Object readResolve() {
		return getInstance();
	}

	// Step 5: Prevent cloning
	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException("Cloning of Singleton is not allowed!");
	}
}
