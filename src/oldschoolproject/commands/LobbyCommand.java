package oldschoolproject.commands;

import java.util.Arrays;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import oldschoolproject.managers.LobbyManager;
import oldschoolproject.managers.game.Minigame;
import oldschoolproject.utils.loaders.command.BaseCommand;

public class LobbyCommand extends BaseCommand {

	public LobbyCommand() {
		super("lobby");
	}
	
	// TODO: Reload signs command

	@Override
	public void onCommand(CommandSender sender, String[] args) {
		Player p = (Player)sender;
		
		if (args.length == 0) {
			p.sendMessage("§cErro: /lobby [create : remove : list : types : reloadsign]");
			return;
		}
		
		if (args[0].equalsIgnoreCase("create")) {
			if (args.length < 2) {
				p.sendMessage("§cErro: /lobby create [gametype] [id]");
				return;
			}
			
			Minigame minigame = Minigame.getMinigame(args[1]);
			
			if (minigame == null) {
				p.sendMessage("§cEsse minigame não existe");
				return;
			}
				
			// I thought about hiding the messages in the back-end but it doesn't need to
			// Since everything related to creating lobbys
			
			if (LobbyManager.lobbyExists(args[2])) { 
				p.sendMessage("§cEsse lobby já existe");
				return;
			}
			
			LobbyManager.createLobby(minigame, args[2]);
			p.sendMessage("§aLobby criado!");
			return;
		}
		
		if (args[0].equalsIgnoreCase("remove")) {
			
			return;
		}
		
		if (args[0].equalsIgnoreCase("list")) {
			return;
		}
		
		if (args[0].equalsIgnoreCase("types")) {
			p.sendMessage("§eAqui estão todos os tipos de lobby que podem ser criados: ");
			
			Arrays.asList(Minigame.values()).forEach(minigame -> p.sendMessage("§6- " + minigame.getTag()));
			return;
		}
		
		p.sendMessage("§cErro: /lobby [create : remove : list : types]");
		return;
	}

}
