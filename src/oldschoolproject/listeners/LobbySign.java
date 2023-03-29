package oldschoolproject.listeners;

import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.SignChangeEvent;

import oldschoolproject.managers.SignManager;
import oldschoolproject.utils.loaders.listener.BaseListener;

public class LobbySign extends BaseListener {
	
	@EventHandler
	public void createSign(SignChangeEvent e) {
		
		Player p = e.getPlayer();
		
		if (e.getLine(0).equalsIgnoreCase("lobbysign")) {
			
			if (!p.isOp()) {
				p.sendMessage("§cSem permissão");
				return;
			}
			
			Sign sign = (Sign)e.getBlock().getState();
			
			p.sendMessage(sign.getLocation().toString());
			
			if (SignManager.newSign(sign, e.getLine(1))) {
				p.sendMessage("§aPlaca de entrada criada para o lobby: " + e.getLine(1));
				
				// Can be replaced with a Scheduler if it causes bugs later on
				e.setCancelled(true);
				return;
			}
			
			p.sendMessage("§cLobby invalido");
			return;
		}
	}

}
