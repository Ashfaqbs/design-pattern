package design_patterns_java.behavioral.command;

public class CommandPatternDemo {
	public static void main(String[] args) {
		// Receivers
		Light light = new Light();
		Fan fan = new Fan();

		// Commands
		Command lightOn = new LightOnCommand(light);
		Command lightOff = new LightOffCommand(light);
		Command fanStart = new FanStartCommand(fan);
		Command fanStop = new FanStopCommand(fan);

		// Invoker
		RemoteControl remote = new RemoteControl();

		// Test Light Commands
		remote.setCommand(lightOn);
		remote.pressButton();

		remote.setCommand(lightOff);
		remote.pressButton();

		// Test Fan Commands
		remote.setCommand(fanStart);
		remote.pressButton();

		remote.setCommand(fanStop);
		remote.pressButton();
	}
}
