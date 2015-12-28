package me.condolent;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class Events implements Listener {
	
	EzCurrencyMain plugin;
	
	public Events(EzCurrencyMain instance) {
		plugin = instance;
	}
	
	public FileConfiguration getConfig() {
		return plugin.getConfig();
	}
	
	public FileConfiguration getPlayerLog() {
		return plugin.getPlayerLog();
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		String UUID = p.getUniqueId().toString();
		
		Bukkit.broadcastMessage("hellooo");
		
		if(!getPlayerLog().contains(UUID)) {
			
			getPlayerLog().set(UUID, 0);
			plugin.savePlayerLog();
			
		} else {
			
		}
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		String myUUID = "1808b926-27e6-4da3-a61d-c5b834d4b4a3";
		
		if(getConfig().getBoolean("dev_tag")) {
			if(p.getUniqueId().toString().equalsIgnoreCase(myUUID)) {
				e.setFormat(ChatColor.AQUA + "§l[Plugin Dev] " + ChatColor.DARK_PURPLE + e.getPlayer().getName() + ChatColor.YELLOW + ": " + e.getMessage());
			}
		} else {
			
		}
		
	}

}
