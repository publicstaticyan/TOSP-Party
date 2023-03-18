package oldschoolproject.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import oldschoolproject.Listeners.PlayerChat;

import oldschoolproject.Modules.Loaders.Command.BaseCommand;

public class Chat extends BaseCommand {
	
	public Chat() {
		super("chat");
	}

	@Override
	public void onCommand(CommandSender sender, String[] args) {
		
		if (args.length == 0) {
			sender.sendMessage("§cErro: /chat [clear | stop | resume]");
			return;
		}
		
		if (args[0].equalsIgnoreCase("clear")) {
			for (int i = 0; i < 100; i++) {
				Bukkit.broadcastMessage("");
			}
			sender.sendMessage("§aO chat global foi limpo");
			return;
		}
		
		if (args[0].equalsIgnoreCase("stop")) {
			if (PlayerChat.chatPaused) {
				sender.sendMessage("§cO chat já está pausado");
				return;
			}
			
			PlayerChat.chatPaused = true;
			sender.sendMessage("§aO chat foi pausado");
			return;
		}
		
		if (args[0].equalsIgnoreCase("resume")) {
			if (!PlayerChat.chatPaused) {
				sender.sendMessage("§cO chat não está pausado");
				return;
			}
			
			PlayerChat.chatPaused = false;
			sender.sendMessage("§aO chat foi despausado");
			return;
		}
		

	}

}
