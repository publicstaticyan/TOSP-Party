package oldschoolproject.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import oldschoolproject.Modules.Loaders.Command.BaseCommand;

public class Chat extends BaseCommand {

	public Chat() {
		super("chatclear");
	}

	@Override
	public void onCommand(CommandSender sender, String[] args) {
		for (int i = 0; i < 100; i++) {
			Bukkit.broadcastMessage("");
		}
		sender.sendMessage("§aO chat foi limpo");
	}

}
