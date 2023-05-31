package oldschoolproject.commands;

import java.util.Arrays;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import oldschoolproject.exceptions.LobbyOperationException;
import oldschoolproject.lobby.Lobby;
import oldschoolproject.lobby.Minigame;
import oldschoolproject.managers.LobbyManager;
import oldschoolproject.managers.SignManager;
import oldschoolproject.utils.loaders.command.BaseCommand;

public class CLobby extends BaseCommand {

	public CLobby() {
		super("lobby");
	}
	
	// TODO: Reload signs command
	// TODO: Make command with CommandTabCompleter

	@Override
	public void onCommand(CommandSender sender, String[] args) {
		Player p = (Player)sender;
		
		if (args.length == 0) {
			p.sendMessage("§cErro: /lobby [create : remove : list : setloc : tags : set]");
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
		
		
//		if (args[0].equalsIgnoreCase("create")) {
//			if (args.length < 2) {
//				p.sendMessage("§cErro: /lobby create [game-tag] [id]");
//				return;
//			}
//			
//			Minigame minigame = Minigame.getMinigame(args[1]);
//			
//			if (minigame == null) {
//				p.sendMessage("§cEsse minigame não existe");
//				return;
//			}
//			
//			if (LobbyManager.lobbyExists(args[2])) { 
//				p.sendMessage("§cEsse lobby já existe");
//				return;
//			}
//			
//			LobbyManager.createLobby(minigame, args[2]);
//			p.sendMessage("§aLobby criado!");
//			return;
//		}
		
		if (args[0].equalsIgnoreCase("remove")) {
			if (args.length < 2) {
				p.sendMessage("§cErro: /lobby remove [id]");
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
		
//		if (args[0].equalsIgnoreCase("remove")) {
//			if (args.length < 2) {
//				p.sendMessage("§cErro: /lobby remove [id]");
//				return;
//			}
//			
//			if (!LobbyManager.lobbyExists(args[1])) {
//				p.sendMessage("§cEsse lobby não existe");
//				return;
//			}
//			
//			LobbyManager.deleteLobby(args[1]);
//			p.sendMessage("§aLobby " + args[1] + "§a apagado com sucesso");
//			return;
//		}
		
		if (args[0].equalsIgnoreCase("list")) {
			LobbyManager.listLobbys(p);
			return;
		}
		
		// /lobby setloc <game_id> <key>
		
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
		
//		if (args[0].equalsIgnoreCase("setloc")) {
//			if (args.length < 2) {
//				p.sendMessage("§cErro: /lobby setloc [id] [key]");
//				return;
//			}
//			
//			if (!LobbyManager.lobbyExists(args[1])) { 
//				p.sendMessage("§cEsse lobby não existe");
//				return;
//			}
//			
//			Lobby lobby = LobbyManager.getLobby(args[1]);
//			lobby.setLocation(args[2], p.getLocation());
//			
//			p.sendMessage("§aLocalização setada para o lobby " + lobby.getId());
//			p.sendMessage("§aChave de localização adicionada: " + args[2]);
//			return;
//		}
		
		if (args[0].equalsIgnoreCase("tags")) {
			LobbyManager.listTags(p);
			return;
		}
		
		if (args[0].equalsIgnoreCase("set")) {
			if (args.length <= 3) {
				p.sendMessage("§cErro: /lobby set [id] [playermin : playermax : timelimit] [value]");
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
		
//		if (args[0].equalsIgnoreCase("set")) {
//			if (args.length < 3) {
//				p.sendMessage("§cErro: /lobby set [id] [playermin : playermax : timelimit] [value]");
//				return;
//			}
//			
//			if (!LobbyManager.lobbyExists(args[1])) {
//				p.sendMessage("§cEsse lobby não existe");
//				return;
//			}
//			
//			if (!args[3].matches("[0-9]+")) {
//				p.sendMessage("§cO campo \"value\" deve ser um número inteiro");
//				return;
//			}
//			
//			if (args[2].equalsIgnoreCase("playermin")) {
//				LobbyManager.getLobby(args[1]).setMinPlayers(Integer.valueOf(args[3]));
//				p.sendMessage("§aValor \"minimo de jogadores\" setado " + args[3] + "§a para o lobby " + args[1]);
//				return;
//			}
//			
//			if (args[2].equalsIgnoreCase("playermax")) {
//				LobbyManager.getLobby(args[1]).setMaxPlayers(Integer.valueOf(args[3]));
//				p.sendMessage("§aValor \"maximo de jogadores\" setado " + args[3] + "§a para o lobby " + args[1]);
//				return;
//			}
//			
//			if (args[2].equalsIgnoreCase("timelimit")) {
//				LobbyManager.getLobby(args[1]).setTimeLimit(Integer.valueOf(args[3]));
//				p.sendMessage("§aValor \"tempo limite\" setado " + args[3] + "§a para o lobby " + args[1]);
//				return;
//			}
//			
//			p.sendMessage("§cErro: /lobby set [id] [playermin : playermax : timelimit] [value]");
//			return;
//		}
//		
//		p.sendMessage("§cErro: /lobby [create : remove : list : setloc : tags : set]");
//		return;
	}

}
