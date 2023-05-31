package oldschoolproject.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.server.ServerListPingEvent;

import oldschoolproject.utils.loaders.listener.BaseListener;

public class LMotd extends BaseListener {
	
	@EventHandler
	public void setMotd(ServerListPingEvent e) {
		e.setMotd("§6§l[§e§lOLD SCHOOL§6§l] §aA festa vai começar! Venha jogar! \n§7Aguardando jogadores para começar...");
	}
	
}
