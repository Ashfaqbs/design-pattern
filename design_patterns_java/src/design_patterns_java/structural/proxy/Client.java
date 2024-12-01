package design_patterns_java.structural.proxy;

public class Client {
	public static void main(String[] args) {
		Image image1 = new ProxyImage("Photo1.jpg");
		Image image2 = new ProxyImage("Photo2.jpg");

		// Image is not loaded yet
		System.out.println("Displaying first image...");
		image1.display(); // Loads and displays the image

		System.out.println("Displaying second image...");
		image2.display(); // Loads and displays the image
	}
}
