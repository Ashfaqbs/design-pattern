package design_patterns_java.structural.adapter;

//Adapter: Converts OldService to TargetService interface
public class AdapterService implements TargetService {
	private OldService oldService;

	public AdapterService(OldService oldService) {
		this.oldService = oldService;
	}

	@Override
	public void request() {
		// Adapting the old request to match the new interface
		oldService.oldRequest();
	}
}
