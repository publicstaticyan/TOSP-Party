package oldschoolproject.Scoreboard;

import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.md_5.bungee.api.ChatColor;
import oldschoolproject.Main;

import java.util.AbstractMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class SimpleScoreboard {
	
	public Scoreboard scoreboard;

	private String title;

	private Map<String, Integer> scores;

	private List<Team> teams;

	public SimpleScoreboard(String title) {
		this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		this.title = title;
		this.scores = Maps.newLinkedHashMap();
		this.teams = Lists.newArrayList();
		
		generate();
	}
	
	public void generate() {
		blankLine();
		add("§aFaçam seus vot§aos!");
		blankLine();
		add("Sabotador:§6 0");
		add("Batata-Quente: §6§6§60");
		blankLine();
		add("Timer:§7 0");
	    blankLine();
		build();
	    
		new BukkitRunnable() {
			public void run() {
				for (Player all : Bukkit.getOnlinePlayers()) {
					send(all);
				}
			}
		}.runTaskTimer(Main.getInstance(), 0, 1);
	}
	
	public void blankLine() {
		add(ChatColor.WHITE + " ");
	}

	public void add(String text) {
		add(text, null);
	}

	public void add(String text, Integer score) {
		Preconditions.checkArgument((text.length() < 48), "text cannot be over 48 characters in length");
		text = fixDuplicates(text);
		this.scores.put(text, score);
	}

	private String fixDuplicates(String text) {
		while (this.scores.containsKey(text))
			text = String.valueOf(text) + "§r";
		if (text.length() > 48)
			text = text.substring(0, 47);
		return text;
	}

	private Map.Entry<Team, String> createTeam(String text) {
		String result = "";
		if (text.length() <= 16)
			return new AbstractMap.SimpleEntry<>(null, text);
		Team team = this.scoreboard.registerNewTeam("text-" + this.scoreboard.getTeams().size());
		Iterator<String> iterator = Splitter.fixedLength(16).split(text).iterator();
		team.setPrefix(iterator.next());
		result = iterator.next();
		if (text.length() > 32)
			team.setSuffix(iterator.next());
		this.teams.add(team);
		return new AbstractMap.SimpleEntry<>(team, result);
	}

	public void build() {
		Objective obj = this.scoreboard.registerNewObjective((this.title.length() > 16) ? this.title.substring(0, 15) : this.title, "dummy");
		obj.setDisplayName(this.title);
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		
		int index = this.scores.size();
		
		for (Map.Entry<String, Integer> text : this.scores.entrySet()) {
			Map.Entry<Team, String> team = createTeam(text.getKey());
			Integer score = Integer.valueOf((text.getValue() != null) ? ((Integer) text.getValue()).intValue() : index);
			OfflinePlayer player = Bukkit.getOfflinePlayer(team.getValue());
			if (team.getKey() != null)
				((Team) team.getKey()).addPlayer(player);
			obj.getScore(player).setScore(score.intValue());
			index--;
		}
	}

	public void reset() {
		this.title = null;
		this.scores.clear();
		for (Team t : this.teams)
			t.unregister();
		this.teams.clear();
	}

	public Scoreboard getScoreboard() {
		return this.scoreboard;
	}

	public void send(Player... players) {
		byte b;
		int i;
		Player[] arrayOfPlayer;
		for (i = (arrayOfPlayer = players).length, b = 0; b < i;) {
			Player p = arrayOfPlayer[b];
			p.setScoreboard(this.scoreboard);
			b++;
		}
	}
}
