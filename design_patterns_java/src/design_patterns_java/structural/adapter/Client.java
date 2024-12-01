package design_patterns_java.structural.adapter;

public class Client {
	public static void main(String[] args) {
		// Creating an instance of the old service
		OldService oldService = new OldService();

		// Creating the adapter that adapts the old service to the target service
		// interface
		TargetService targetService = new AdapterService(oldService);

		// The client now interacts with the target service, unaware of the old service
		// behind the scenes
		targetService.request();
	}
}
