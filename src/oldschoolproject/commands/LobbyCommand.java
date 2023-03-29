package oldschoolproject.commands;

import org.bukkit.block.Sign;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import oldschoolproject.managers.LobbyManager;
import oldschoolproject.managers.SignManager;
import oldschoolproject.managers.game.Minigame;
import oldschoolproject.utils.loaders.command.BaseCommand;

public class LobbyCommand extends BaseCommand {

	public LobbyCommand() {
		super("lobby");
	}

	@Override
	public void onCommand(CommandSender sender, String[] args) {
		Player p = (Player)sender;
		
		if (args.length == 0) {
			p.sendMessage("§cErro: /lobby [create : remove : list : types : setsign]");
			return;
		}
		
		if (args[0].equalsIgnoreCase("create")) {
			if (args.length < 2) {
				p.sendMessage("§cErro: /lobby create [gametype] [id]");
				return;
			}
			
			Minigame minigame = Minigame.hasTag(args[1]);
			
			if (minigame != null) {
				
				// TODO: This is wrong, createLobby should be a boolean
				if (LobbyManager.lobbyExists(args[2]) != null) { 
					p.sendMessage("§cEsse lobby já existe");
					return;
				}
				
				LobbyManager.createLobby(minigame, args[2]);
				p.sendMessage("§aLobby criado!");
				return;
			}
			
			p.sendMessage("§cEsse minigame não existe");
			return;
		}
		
		if (args[0].equalsIgnoreCase("remove")) {
			LobbyManager.join(p, args[1]);
			return;
		}
		
		if (args[0].equalsIgnoreCase("list")) {
			return;
		}
		
		if (args[0].equalsIgnoreCase("types")) {
			p.sendMessage("§eAqui estão todos os tipos de lobby que podem ser criados: ");
			
			for (Minigame minigame : Minigame.values()) {
				p.sendMessage("§6- " + minigame.getTag());
			}
			
			return;
		}
		
		p.sendMessage("§cErro: /lobby [create : remove : list : types]");
		return;
	}

}
