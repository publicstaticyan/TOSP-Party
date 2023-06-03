package oldschoolproject.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import oldschoolproject.exceptions.LobbyOperationException;
import oldschoolproject.lobby.Minigame;
import oldschoolproject.managers.LobbyManager;
import oldschoolproject.utils.loaders.command.BaseCommand;

public class CLobby extends BaseCommand {

	public CLobby() {
		super("lobby");
	}
	
	// TODO: Make command with CommandTabCompleter

	@Override
	public void onCommand(CommandSender sender, String[] args) {
		Player p = (Player)sender;
		
		if (args.length == 0) {
			p.sendMessage("§cErro: /lobby [create : remove : list : tags : set]");
			return;
		}
		
		if (args[0].equalsIgnoreCase("create")) {
			if (args.length <= 2) {
				p.sendMessage("§cErro: /lobby create [game-tag] [id]");
				return;
			}
			
			try {
				LobbyManager.createLobby(Minigame.getMinigame(args[1]), args[2]);
			} catch (LobbyOperationException e) {
				p.sendMessage("§cNão foi possivel criar um lobby: " + e.getMessage());
				return;
			}
			
			p.sendMessage("§aLobby criado!");
			return;
		}
		
		if (args[0].equalsIgnoreCase("delete")) {
			if (args.length < 2) {
				p.sendMessage("§cErro: /lobby delete [id]");
				return;
			}
			
			try {
				LobbyManager.deleteLobby(args[1]);
			} catch (LobbyOperationException e) {
				p.sendMessage("§cNão foi possivel deletar o lobby: " + e.getMessage());
				return;
			}
			
			p.sendMessage("§aLobby apagado!");
			return;
		}
		
		if (args[0].equalsIgnoreCase("list")) {
			LobbyManager.listLobbys(p);
			return;
		}
		
		if (args[0].equalsIgnoreCase("setloc")) {
			if (args.length <= 2) {
				p.sendMessage("§cErro: /lobby setloc [id] [key]");
				return;
			}
			
			try {
				LobbyManager.addLocationToLobby(args[1], args[2], p.getLocation());
			} catch (LobbyOperationException e) {
				p.sendMessage("§cNão foi possivel adicionar a localização: " + e.getMessage());
				return;
			}
			
			p.sendMessage("§aChave de localização adicionada!");
			return;
		}
		
		if (args[0].equalsIgnoreCase("tags")) {
			LobbyManager.listTags(p);
			return;
		}
		
		if (args[0].equalsIgnoreCase("set")) {
			if (args.length <= 3) {
				p.sendMessage("§cErro: /lobby set [id] [playermin : playermax : timelimit : location] [value]");
				return;
			}
			
			try {
				LobbyManager.updateLobby(args[1], args[2], args[3]);
			} catch (LobbyOperationException e) {
				p.sendMessage("§cNão foi possivel alterar um dado do lobby: " + e.getMessage());
				return;
			}
			
			p.sendMessage("§aLobby atualizado!");
			return;
		}
		

	}

}
