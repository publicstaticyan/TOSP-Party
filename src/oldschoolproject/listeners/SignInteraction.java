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
import oldschoolproject.managers.SettingsManager;
import oldschoolproject.managers.SignManager;
import oldschoolproject.utils.loaders.listener.BaseListener;

public class SignInteraction extends BaseListener {
	
	@EventHandler
	public void breakSign(BlockBreakEvent e) {
		Player p = e.getPlayer();
		
		Block block = e.getBlock();
		
		if (block.getState() instanceof Sign) {
			
			Sign sign = (Sign) e.getBlock().getState();
			
			if (SignManager.boundToLobby(sign)) {
				
				if (!p.isOp()) {
					e.setCancelled(true);
					return;
				}
				
				SettingsManager.load("signs").set(sign.getLocation().getBlockX() + "#" + sign.getLocation().getBlockY() + "#" + sign.getLocation().getBlockZ(), null);
			
				SignManager.unbindFromLobby(sign);
				
				// TODO: Parei aqui
				
				p.sendMessage("§aPlaca removida para o lobby " + sign.getLine(1));
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
				Sign sign = (Sign) block.getState();
				
				if (SignManager.boundToLobby(sign)) {
					p.performCommand("game join " + sign.getLine(1));
				}
			}
		}
	}
	
	@EventHandler
	public void createSign(SignChangeEvent e) {
		
		Player p = e.getPlayer();
		
		if (e.getLine(0).equalsIgnoreCase("lobbysign")) {
			
			if (!p.isOp()) {
				p.sendMessage("§cSem permissão");
				return;
			}
			
			Sign lobbySign = SignManager.newSign((Sign) e.getBlock().getState(), e.getLine(1));
			
			// Can be replaced with a Scheduler if it causes bugs later on
			if (lobbySign == null) {
				p.sendMessage("§cLobby invalido");
				return;
			}
			
			p.sendMessage("§aPlaca de entrada criada para o lobby: " + e.getLine(1));
			e.setCancelled(true);
			return;
		}
	}
}
