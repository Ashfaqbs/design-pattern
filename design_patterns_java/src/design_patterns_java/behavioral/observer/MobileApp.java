package design_patterns_java.behavioral.observer;

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