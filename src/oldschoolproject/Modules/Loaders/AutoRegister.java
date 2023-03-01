package oldschoolproject.Modules.Loaders;

import oldschoolproject.Modules.Loaders.Command.CommandLoader;
import oldschoolproject.Modules.Loaders.Listener.ListenerLoader;

public class AutoRegister {
	
	public AutoRegister() {
		ListenerLoader.loadListenersAndRegister();
		CommandLoader.loadCommandsAndRegister();
	}

}
