package oldschoolproject.game;

import org.bukkit.scheduler.BukkitRunnable;

import oldschoolproject.Main;

public class Timer {
	
	// TODO: Develop this class
	
	private int seconds;
	private Lobby game;
	
	public Timer(Lobby game, int seconds) {
		this.seconds = seconds;
		this.game = game;
		countdown();
	}
	
	public void countdown() {
		new BukkitRunnable() {
			int counter = seconds;
			public void run() {
				if (counter > -1) {
					counter--;
				} else {
					counter = seconds;
					game.start();
					cancel();
				}
			}
		}.runTaskTimer(Main.getInstance(), 0, 20 * 1);
	}

}
