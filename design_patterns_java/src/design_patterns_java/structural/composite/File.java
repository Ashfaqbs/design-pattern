package design_patterns_java.structural.composite;

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
