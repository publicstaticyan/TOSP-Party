package oldschoolproject.listeners;

import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import oldschoolproject.managers.LobbyManager;
import oldschoolproject.managers.SignManager;
import oldschoolproject.utils.loaders.listener.BaseListener;

public class LSign extends BaseListener {
	
	@EventHandler
	public void createSign(SignChangeEvent e) {
		
		Player p = e.getPlayer();
		
		if (e.getLine(0).equalsIgnoreCase("lobbysign")) {
			
			if (!p.isOp()) {
				p.sendMessage("§cSem permissão");
				return;
			}
			
			if (!LobbyManager.lobbyExists(e.getLine(1))) {
				p.sendMessage("§cLobby " + e.getLine(1) + " não encontrado");
				return;
			}
			
			SignManager.create(e.getBlock().getLocation(), e.getLine(1));
			
			p.sendMessage("§aPlaca de entrada criada para o lobby: " + e.getLine(1));
			e.setCancelled(true);
			return;
		}
	}
	
	@EventHandler
	public void removeSign(BlockBreakEvent e) {
		Player p = e.getPlayer();
		
		Block block = e.getBlock();
		
		if (block.getState() instanceof Sign) {
			
			// essa placa está em alguma lista de placas de algum lobby existente?
			
			// pode ser refatorado por: "Esse lobby de id tal (getLine(1)) tem em sua lista de placas essa placa? 
			if (SignManager.isLinkedToLobby(block.getLocation())) {
				
				if (!p.isOp()) {
					e.setCancelled(true);
					return;
				}
				
				// 
				SignManager.destroy(block.getLocation());
				
				// TODO: Parei aqui
				
				p.sendMessage("§aPlaca de entrada removida para o lobby: " + ((Sign) block.getState()).getLine(1));
			}
		}
	}
	
	@EventHandler
	public void rightClickSign(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		Block block = e.getClickedBlock();
		Action action = e.getAction();
		
		if (action == Action.RIGHT_CLICK_BLOCK) {
			if (block.getState() instanceof Sign) {
				
				if (SignManager.isLinkedToLobby(block.getLocation())) {
					
					e.setCancelled(true);
					p.performCommand("game join " + ((Sign) block.getState()).getLine(1));
				}
			}
		}
	}
}
