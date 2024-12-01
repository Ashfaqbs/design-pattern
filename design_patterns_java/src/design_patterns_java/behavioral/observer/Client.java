package design_patterns_java.behavioral.observer;

public class Client {
	public static void main(String[] args) {
		StockMarket stockMarket = new StockMarket();

		Observer mobileApp = new MobileApp("StockApp");
		Observer website = new Website("FinanceWebsite");

		stockMarket.registerObserver(mobileApp);
		stockMarket.registerObserver(website);

		stockMarket.setStockPrice("AAPL", 145.67);
		stockMarket.setStockPrice("GOOGL", 2732.45);

		stockMarket.removeObserver(mobileApp);

		stockMarket.setStockPrice("MSFT", 299.89);
	}
}
