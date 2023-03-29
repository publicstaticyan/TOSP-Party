package oldschoolproject.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import oldschoolproject.managers.LobbyManager;
import oldschoolproject.utils.loaders.command.BaseCommand;

public class GameCommand extends BaseCommand {

	public GameCommand() {
		super("game");
	}

	@Override
	public void onCommand(CommandSender sender, String[] args) {
		Player p = (Player)sender;
		
		if (args.length == 0) {
			p.sendMessage("§cErro: /game [join : quit] [id]");
			return;
		}
		
		if (args[0].equalsIgnoreCase("join")) {
			if (LobbyManager.join(p, args[1].toLowerCase())) {
				p.sendMessage("§aVocê entrou no lobby: " + args[1].toLowerCase());
				return;
			}
			
			// TODO: Should also do an "already playing" message
			p.sendMessage("§cLobby invalido");
			return;
		}
		
		if (args[0].equalsIgnoreCase("quit")) {
			if (LobbyManager.quit(p)) {
				p.sendMessage("§aVocê saiu do lobby");
				return;
			}
			
			p.sendMessage("§cVocê não está em nenhum lobby");
			return;
		}
		
		p.sendMessage("§cErro: /game [join : quit] [id]");
		return;
	}
}
