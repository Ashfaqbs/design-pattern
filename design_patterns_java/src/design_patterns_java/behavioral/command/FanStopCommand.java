package design_patterns_java.behavioral.command;

class FanStopCommand implements Command {
	private Fan fan;

	public FanStopCommand(Fan fan) {
		this.fan = fan;
	}

	@Override
	public void execute() {
		fan.stop();
	}
}