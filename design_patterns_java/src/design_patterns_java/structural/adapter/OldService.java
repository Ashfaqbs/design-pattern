package design_patterns_java.structural.adapter;

//Adaptee: The existing service that needs adaptation
public class OldService {
	public void oldRequest() {
		System.out.println("Old Service is providing data.");
	}
}
