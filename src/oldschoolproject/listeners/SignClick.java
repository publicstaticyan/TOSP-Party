package oldschoolproject.listeners;

import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import oldschoolproject.managers.SignManager;
import oldschoolproject.utils.loaders.listener.BaseListener;

public class SignClick extends BaseListener {
	
	@EventHandler
	public void clickSign(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		Block block = e.getClickedBlock();
		Action action = e.getAction();
		
		if (action == Action.LEFT_CLICK_BLOCK || action == Action.RIGHT_CLICK_BLOCK) {
			if (block.getState() instanceof Sign) {
				Sign sign = (Sign) block.getState();
				
				if (SignManager.signIsValid(sign.getLocation())) {
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
