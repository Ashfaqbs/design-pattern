package design_patterns_java.structural.facade;

public class HomeTheaterFacade {
	private DVDPlayer dvdPlayer;
	private Projector projector;
	private SoundSystem soundSystem;

	public HomeTheaterFacade(DVDPlayer dvdPlayer, Projector projector, SoundSystem soundSystem) {
		this.dvdPlayer = dvdPlayer;
		this.projector = projector;
		this.soundSystem = soundSystem;
	}

	public void watchMovie(String movie) {
		System.out.println("Setting up the home theater to watch a movie...");
		projector.on();
		projector.setInput("DVD");
		soundSystem.on();
		soundSystem.setVolume(50);
		dvdPlayer.on();
		dvdPlayer.play(movie);
	}

	public void endMovie() {
		System.out.println("Shutting down the home theater...");
		dvdPlayer.off();
		projector.off();
		soundSystem.off();
	}
}
