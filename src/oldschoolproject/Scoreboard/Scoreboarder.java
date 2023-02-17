package oldschoolproject.Scoreboard;

import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import oldschoolproject.Scoreboard.ColorScroller.ScrollType;

public class Scoreboarder {
	
	/*
	 * 
        Team onlineCounter = board.registerNewTeam("onlineCounter");
        onlineCounter.addEntry(ChatColor.BLACK + "" + ChatColor.WHITE);
        onlineCounter.setPrefix("" + ChatColor.DARK_RED + Bukkit.getOnlinePlayers().size() + ChatColor.RED + "/" + ChatColor.DARK_RED + Bukkit.getMaxPlayers());
	 	obj.getScore(ChatColor.BLACK + "" + ChatColor.WHITE).setScore(14);
	 * 
	 */
	
	private Scoreboard scoreboard;
	
	private String title;

	private Map<String, Integer> scores;
	
	private List<Team> teams;
	
	private Objective obj;
	
	private ColorScroller cs;
	
	@SuppressWarnings("deprecation")
	public Scoreboarder(String title) {
		this.title = title;
		this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		this.scores = Maps.newLinkedHashMap();
		this.teams = Lists.newArrayList();
		this.cs = new ColorScroller(ChatColor.GOLD, title, "§e", "§e", "§e", true, false, ScrollType.FORWARD);
		this.obj = scoreboard.registerNewObjective(this.title, "dummy");
		
		obj.setDisplayName(title);
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
	}
	
	public void 
}
