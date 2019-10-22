package me.Sp3eex.Nametags;

import java.text.DecimalFormat;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

public class Main extends JavaPlugin implements Listener {

	private Scoreboard s;
	public static Permission permission = null;
    public static Economy economy = null;
    public static Chat chat = null;
    private static ArrayList<String> ranks = new ArrayList<String>();
    
	@Override
	public void onEnable() {
		super.saveDefaultConfig();
		s = Bukkit.getScoreboardManager().getMainScoreboard();
		Bukkit.getPluginManager().registerEvents(this, this);
		setupChat();
		
		RegisterNameTag(); 
		for (Player player : Bukkit.getOnlinePlayers()) {
			RegisterHealthBar(player);
		}
		
		if (getConfig().getStringList("ranks").size() == 0) { return; }
		for (String rank : getConfig().getStringList("ranks")) {
			ranks.add(rank);
		}
		
		for (Player player : Bukkit.getOnlinePlayers()) {
			RegisterNewPlayer(player, chat.getPrimaryGroup(player));
		}
		
		BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
            	if (getConfig().getStringList("ranks").size() == 0) { return; }
        		for (String rank : getConfig().getStringList("ranks")) {
        			ranks.add(rank);
        		}
        		for (Player player : Bukkit.getOnlinePlayers()) {
        			RegisterNewPlayer(player, chat.getPrimaryGroup(player));
        		}
            }
        }, 20*5, 20*5);
	}
	
	@SuppressWarnings("deprecation")
	public void onPlayerJoin(PlayerJoinEvent event) {
		s.getTeam("ranksteams").addPlayer(event.getPlayer());
	}
	
    private boolean setupChat() {
        RegisteredServiceProvider<Chat> chatProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.chat.Chat.class);
        if (chatProvider != null) {
            chat = chatProvider.getProvider();
        }
        return (chat != null);
    }
	
	@SuppressWarnings("deprecation")
	public void RegisterHealthBar(Player player) {
		if (s.getObjective("health") != null) {
			s.getObjective("health").unregister(); 
		}
		
		Objective o = s.registerNewObjective("health", "health");
		
		Double playerhealth = player.getMaxHealth();
		DecimalFormat format = new DecimalFormat("0.#");
		String fixed = format.format(playerhealth);
		
		o.setDisplayName("/ " + fixed);
		o.setDisplaySlot(DisplaySlot.BELOW_NAME);
	}
	
	@SuppressWarnings("deprecation")
	public void RegisterNewPlayer(Player player, String prefix) {
		String LastPlayerGroup = ColorUtils.RemoveColors(prefix);
		if (ranks.size() == 0) { return; }
		for (int i = 0; i < ranks.size(); i++) {
			String configRank = ColorUtils.RemoveColors(ranks.get(i));
			if (LastPlayerGroup.equalsIgnoreCase(configRank)) {
				s.getTeam(configRank).addPlayer(player);
			}
		}
	}
	
	public void RegisterNameTag() {
		if (getConfig().getStringList("ranks").size() == 0) { return; }
		for (String rank : getConfig().getStringList("ranks")) {
			String rankPlain = ColorUtils.RemoveColors(rank);
			
			if (s.getTeam(rankPlain) != null) {
				s.getTeam(rankPlain).unregister(); 
			}
			
			Team team = s.registerNewTeam(rankPlain);
			team.setPrefix(ChatColor.translateAlternateColorCodes('&', rank) + " ");
		}
	}
}
