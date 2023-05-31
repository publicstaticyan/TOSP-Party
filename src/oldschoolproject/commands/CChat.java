package oldschoolproject.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import oldschoolproject.listeners.LChat;
import oldschoolproject.utils.loaders.command.BaseCommand;

public class CChat extends BaseCommand {
	
	public CChat() {
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
			if (LChat.chatPaused) {
				sender.sendMessage("§cO chat já está pausado");
				return;
			}
			
			LChat.chatPaused = true;
			sender.sendMessage("§aO chat foi pausado");
			return;
		}
		
		if (args[0].equalsIgnoreCase("resume")) {
			if (!LChat.chatPaused) {
				sender.sendMessage("§cO chat não está pausado");
				return;
			}
			
			LChat.chatPaused = false;
			sender.sendMessage("§aO chat foi despausado");
			return;
		}
		

	}

}
