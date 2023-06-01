package oldschoolproject.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import oldschoolproject.exceptions.LobbyOperationException;
import oldschoolproject.managers.LobbyManager;
import oldschoolproject.utils.loaders.command.BaseCommand;

public class CGame extends BaseCommand {

	public CGame() {
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
			if (args.length < 2) {
				p.sendMessage("§cErro: /game join [id]");
				return;
			}
			
			try {
				LobbyManager.join(p, args[1].toLowerCase());
			}  catch (LobbyOperationException e) {
				p.sendMessage("§cNão foi possivel entrar no lobby: " + e.getMessage());
				return;
			}
			
			p.sendMessage("§aVocê entrou no lobby");
			return;
		}
		
		if (args[0].equalsIgnoreCase("quit")) {
			try {
				LobbyManager.quit(p);
			} catch (LobbyOperationException e) {
				p.sendMessage("§cNão foi possivel sair do lobby: " + e.getMessage());
				return;
			}
			
			p.sendMessage("§aVocê saiu do lobby");
			return;
		}
	}
}
