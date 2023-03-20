package oldschoolproject.utils.loaders;

import oldschoolproject.utils.loaders.command.CommandLoader;
import oldschoolproject.utils.loaders.listener.ListenerLoader;

public class RegistrationLoader {
	
	public RegistrationLoader() {
		ListenerLoader.loadListenersAndRegister();
		CommandLoader.loadCommandsAndRegister();
	}

}
