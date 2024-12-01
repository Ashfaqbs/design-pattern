package design_patterns_java.creational.prototype;

//Step 1: Prototype Class
class Prototype implements Cloneable {
	private String name;
	private int value;

	public Prototype(String name, int value) {
		this.name = name;
		this.value = value;
	}

	// Getter and Setter
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	// Step 2: Implement the clone() method
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public String toString() {
		return "Prototype [name=" + name + ", value=" + value + "]";
	}
}
