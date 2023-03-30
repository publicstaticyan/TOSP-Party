package oldschoolproject.commands;

import java.util.Arrays;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import oldschoolproject.game.Minigame;
import oldschoolproject.managers.LobbyManager;
import oldschoolproject.utils.loaders.command.BaseCommand;

public class LobbyAdmin extends BaseCommand {

	public LobbyAdmin() {
		super("lobby");
	}
	
	// TODO: Reload signs command
	// TODO: Make command with CommandTabCompleter

	@Override
	public void onCommand(CommandSender sender, String[] args) {
		Player p = (Player)sender;
		
		if (args.length == 0) {
			p.sendMessage("§cErro: /lobby [create : remove : list : tags : set]");
			return;
		}
		
		if (args[0].equalsIgnoreCase("create")) {
			if (args.length < 2) {
				p.sendMessage("§cErro: /lobby create [game-tag] [id]");
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
		
		
		if (args[0].equalsIgnoreCase("set")) {
			if (args.length < 3) {
				p.sendMessage("§cErro: /lobby set [id] [playermin : playermax : timelimit] [value]");
				return;
			}
			
			if (!LobbyManager.lobbyExists(args[1])) {
				p.sendMessage("§cEsse lobby não existe");
				return;
			}
			
			if (!args[3].matches("[0-9]+")) {
				p.sendMessage("§cO campo \"value\" deve ser um número inteiro");
				return;
			}
			
			if (args[2].equalsIgnoreCase("playermin")) {
				LobbyManager.getLobby(args[1]).setMinPlayers(Integer.valueOf(args[3]));
				p.sendMessage("§aValor \"minimo de jogadores\" setado " + args[3] + "§a para o lobby " + args[1]);
				return;
			}
			
			if (args[2].equalsIgnoreCase("playermax")) {
				LobbyManager.getLobby(args[1]).setMaxPlayers(Integer.valueOf(args[3]));
				p.sendMessage("§aValor \"maximo de jogadores\" setado " + args[3] + "§a para o lobby " + args[1]);
				return;
			}
			
			if (args[2].equalsIgnoreCase("timelimit")) {
				LobbyManager.getLobby(args[1]).setTimeLimit(Integer.valueOf(args[3]));
				p.sendMessage("§aValor \"tempo limite\" setado " + args[3] + "§a para o lobby " + args[1]);
				return;
			}
			
			p.sendMessage("§cErro: /lobby set [id] [playermin : playermax : timelimit] [value]");
			return;
		}
		
		if (args[0].equalsIgnoreCase("remove")) {
			if (args.length < 2) {
				p.sendMessage("§cErro: /lobby remove [id]");
				return;
			}
			
			if (!LobbyManager.lobbyExists(args[1])) {
				p.sendMessage("§cEsse lobby não existe");
				return;
			}
			
			LobbyManager.deleteLobby(args[1]);
			p.sendMessage("§aLobby " + args[1] + "§a apagado com sucesso");
			return;
		}
		
		if (args[0].equalsIgnoreCase("list")) {
			p.sendMessage("");
			p.sendMessage("§eAqui estão todos os lobbys existentes: ");
			p.sendMessage("");
			// TODO: Code Time class to make "TEMPO" Work
			p.sendMessage("§7| §cMINIGAME §7| §cID §7| §cSTATUS §7| §cPLAYERS §7| §cTEMPO §7|");
			p.sendMessage("");
			 
			LobbyManager.lobbyList.forEach(lobby -> {
				p.sendMessage("§a" + lobby.getMinigame().getTag() + " §7- §a" + lobby.getId() + " §7- §a" + lobby.getStage().toString() + " §7- §a" + lobby.getPlayerList().size() + "§a/" + lobby.getMaxPlayers() + " §7: §a" + lobby.getMinPlayers());
			});
			return;
		}
		
		if (args[0].equalsIgnoreCase("tags")) {
			p.sendMessage("");
			p.sendMessage("§eAqui estão todas as TAGS para os tipos de lobby que podem ser criados: ");
			p.sendMessage("");
			
			Arrays.asList(Minigame.values()).forEach(minigame -> p.sendMessage("§6- " + minigame.getTag()));
			return;
		}
		
		p.sendMessage("§cErro: /lobby [create : remove : list : type : set]");
		return;
	}

}
