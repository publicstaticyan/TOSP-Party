package oldschoolproject.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import oldschoolproject.managers.LobbyManager;
import oldschoolproject.utils.loaders.command.BaseCommand;

public class Game extends BaseCommand {

	public Game() {
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
			
			if (!LobbyManager.lobbyExists(args[1])) {
				p.sendMessage("§cEsse lobby não existe");
				return;
			}
			
			if (LobbyManager.isPlaying(p)) {
				p.sendMessage("§cVocê não pode entrar em um lobby estando em outro");
				return;
			}
			
			if (LobbyManager.lobbyIsFull(args[1])) {
				p.sendMessage("§cO lobby está cheio");
				return;
			}
			
			LobbyManager.join(p, args[1].toLowerCase());
			p.sendMessage("§aVocê entrou no lobby: " + args[1].toLowerCase());
			return;
		}
		
		if (args[0].equalsIgnoreCase("quit")) {
			
			if (!LobbyManager.isPlaying(p)) {
				p.sendMessage("§cVocê não está em nenhum lobby");
				return;
			}
			
			LobbyManager.quit(p);
			p.sendMessage("§aVocê saiu do lobby");
			return;
		}
		
		p.sendMessage("§cErro: /game [join : quit] [id]");
		return;
	}
}
