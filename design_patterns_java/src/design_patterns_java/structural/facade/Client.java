package design_patterns_java.structural.facade;

public class Client {
	public static void main(String[] args) {
		DVDPlayer dvdPlayer = new DVDPlayer();
		Projector projector = new Projector();
		SoundSystem soundSystem = new SoundSystem();

		HomeTheaterFacade homeTheater = new HomeTheaterFacade(dvdPlayer, projector, soundSystem);

		// Watch a movie
		homeTheater.watchMovie("Inception");

		// End the movie
		homeTheater.endMovie();
	}
}
