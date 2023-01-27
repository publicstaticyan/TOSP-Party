package oldschoolproject.Commands;

import org.bukkit.Bukkit;

import oldschoolproject.Main;
import oldschoolproject.Utils.CommandFramework.Command;
import oldschoolproject.Utils.CommandFramework.CommandArgs;

public class Chat {

	public Chat() {
		Main.commandFramework.registerCommands(this);
	}
	
	@Command(name = "chat", permission = "admin")
	public void commandExecute(CommandArgs args) {
		for (int i = 0; i < 100; i++) {
			Bukkit.broadcastMessage("");
		}
		args.getPlayer().sendMessage("§aChat limpo com sucesso!");
	}
}
