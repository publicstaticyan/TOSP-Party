package oldschoolproject.Scoreboard;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import oldschoolproject.Main;
import oldschoolproject.Scoreboard.ColorScroller.ScrollType;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class ScoreBuilder {
	
	public Scoreboard scoreboard;

	private String title;

	private Map<String, Integer> scores;

	private List<Team> teams;
	
	public ScoreBuilder(String title) {
		this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		this.title = title;
		this.scores = Maps.newLinkedHashMap();
		this.teams = Lists.newArrayList();
		
		generate();
	}
	
	public void generate() {
		
		ColorScroller cs = new ColorScroller(ChatColor.GOLD, "OLD SCHOOL", "§e", "§e", "§e", true, false, ScrollType.FORWARD);
		
		blankLine();
		add("§aFaçam seus votos!");
		blankLine();
		add("Sabotador: §60");
		add("Batata-Quente: §60");
		blankLine();
		add("Timer: §70");
	    blankLine();
		build();
	    
		new BukkitRunnable() {
			public void run() {
				for (Player all : Bukkit.getOnlinePlayers()) {
					send(all);
				}
			}
		}.runTaskTimer(Main.getInstance(), 0, 1);
		new BukkitRunnable() {
			@Override
			public void run() {
				
                if(cs.getScrollType() == ScrollType.FORWARD) {
                    if(cs.getPosition() == cs.getString().length()) {
                        cs.setScrollType(ScrollType.BACKWARD);
                    }
                } else {
                    if(cs.getPosition() <= -1) {
                        cs.setScrollType(ScrollType.FORWARD);
                    }
                }
				
				scoreboard.getObjective(title).setDisplayName(cs.next());
			}
		}.runTaskTimer(Main.getInstance(), 0, 2);
	}
	
	public void blankLine() {
		add(ChatColor.WHITE + " ");
	}

	public void add(String text) {
		setScore(text, null);
	}

	public void setScore(String text, Integer score) {
		text = fixDuplicates(text);
		scores.put(text, score);
	}

	private String fixDuplicates(String text) {
		while (scores.containsKey(text)) {
			text = String.valueOf(text) + "§r"; // Se um "text" já existir no map, adicionar "§r" no final
		}
		return text;
	}
	
	@SuppressWarnings("deprecation")
	public void build() {
		Objective obj = scoreboard.registerNewObjective(this.title, "dummy");
		obj.setDisplayName(title);
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		
		int index = this.scores.size(); // Numero total de linhas na Scoreboard
		
		for (Entry<String, Integer> text : scores.entrySet()) { // copia pares Key e Value do map "scores" para o map "text"
			
			Entry<Team, String> team = createTeam(text.getKey()); // Cria um map que contem um time associado com a linha da scoreboard
			
//			Integer score = Integer.valueOf((text.getValue() != null) ? ((Integer) text.getValue()).intValue() : index);
			
			FastOfflinePlayer offlinePlayer = new FastOfflinePlayer(team.getValue());
			
//			if (team.getKey() != null) {
//				((Team) team.getKey()).addPlayer(offlinePlayer);
//			}
			
			obj.getScore(offlinePlayer).setScore(index);
			index--;
		}
	}

	public Entry<Team, String> createTeam(String text) {
//		String result = "";
		
//		if (text.length() <= 16)
//			return new AbstractMap.SimpleEntry<>(null, text);
		
		Team team = scoreboard.registerNewTeam("text-" + scoreboard.getTeams().size());
		
//		Iterator<String> iterator = Splitter.fixedLength(16).split(text).iterator();
		
//		team.setPrefix(iterator.next());
//		result = iterator.next();
//		if (text.length() > 32) {
//			team.setSuffix(iterator.next());
//		}
		
		team.setPrefix(text);
		
		this.teams.add(team);
		
		return new AbstractMap.SimpleEntry<>(team, text);
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
		for (Player p : players)
			p.setScoreboard(scoreboard);
	}
}
