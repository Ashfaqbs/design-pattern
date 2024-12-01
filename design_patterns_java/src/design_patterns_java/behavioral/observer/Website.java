package design_patterns_java.behavioral.observer;

public class Website implements Observer {
	private String siteName;

	public Website(String siteName) {
		this.siteName = siteName;
	}

	@Override
	public void update(String stockName, double price) {
		System.out.println(siteName + " received an update: Stock " + stockName + " is now $" + price);
	}
}