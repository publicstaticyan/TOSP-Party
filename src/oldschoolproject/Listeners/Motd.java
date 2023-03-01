package oldschoolproject.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.server.ServerListPingEvent;

import oldschoolproject.Modules.Loaders.Listener.BaseListener;

public class Motd extends BaseListener {
	
	@EventHandler
	public void setMotd(ServerListPingEvent e) {
		e.setMotd("§6§l[§e§lOLD SCHOOL§6§l] §aA festa vai começar! Venha jogar! \n§7Aguardando jogadores para começar...");
	}
	
}
