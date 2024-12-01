package design_patterns_java.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

public class StockMarket implements Subject {
	private List<Observer> observers = new ArrayList<>();
	private String stockName;
	private double price;

	public void setStockPrice(String stockName, double price) {
		this.stockName = stockName;
		this.price = price;
		notifyObservers(); // Notify all observers whenever price changes
	}

	@Override
	public void registerObserver(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		observers.remove(observer);
	}

	@Override
	public void notifyObservers() {
		for (Observer observer : observers) {
			observer.update(stockName, price);
		}
	}
}
