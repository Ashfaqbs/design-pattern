package design_patterns_java.behavioral.observer;

public interface Subject {
	void registerObserver(Observer observer);

	void removeObserver(Observer observer);

	void notifyObservers();
}
