package oldschoolproject.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import oldschoolproject.utils.loaders.listener.BaseListener;

public class ChatEvent extends BaseListener {
	
	public static boolean chatPaused = false;

	@EventHandler
	public void playerChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		
		if (chatPaused) {
			p.sendMessage("§cNão é possivel digitar, o chat foi pausado");
			e.setCancelled(true);
			return;
		}

		// Set format
	}
	
}
