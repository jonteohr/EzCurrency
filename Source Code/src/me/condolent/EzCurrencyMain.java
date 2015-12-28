package me.condolent;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class EzCurrencyMain extends JavaPlugin {
	
	private static Plugin plugin;
	public static Plugin getPlugin() {
		return plugin;
	}
	
	public Permission add = new Permission("ezcurrency.add");
	public Permission reload = new Permission("ezcurrency.reload");
	
	public void onEnable() {
		getLogger().info("Plugin successfully enabled.");
		
		getConfig().options().copyDefaults();
		saveDefaultConfig();
		getPlayerLog().options().copyDefaults();
		savePlayerLog();
		
		plugin = this;
		
		this.getServer().getPluginManager().registerEvents(new Events(this), this);
		
		getCommand("currency").setExecutor(new CurrencyCmd(this));
	}
	
	public void onDisable() {
		getLogger().info("Disabling plugin...");
	}
	
	PluginDescriptionFile pdf = getDescription();
	
	public boolean onCommand(Command cmd, CommandSender sender, String string, String args[]) {
		@SuppressWarnings("unused")
		Player p = (Player) sender;
		
		
		return false;
	}
	
	private FileConfiguration PlayerLog = null;
	private File PlayerLogFile = null;
	
	public void reloadPlayerLog() {
	    if (PlayerLogFile == null) {
	    	PlayerLogFile = new File(getDataFolder(), "PlayerLog.yml");
	    }
	    PlayerLog = YamlConfiguration.loadConfiguration(PlayerLogFile);
	 
	    // Look for defaults in the jar
	    InputStream defConfigStream = this.getResource("PlayerLog.yml");
	    if (defConfigStream != null) {
	        @SuppressWarnings("deprecation")
			YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
	        PlayerLog.setDefaults(defConfig);
	    }
	}
	
	public FileConfiguration getPlayerLog() {
	    if (PlayerLog == null) {
	        reloadPlayerLog();
	    }
	    return PlayerLog;
	}
	
	public void savePlayerLog() {
	    if (PlayerLog == null || PlayerLogFile == null) {
	        return;
	    }
	    try {
	        getPlayerLog().save(PlayerLogFile);
	    } catch (IOException ex) {
	        getLogger().log(Level.SEVERE, "Could not save config to " + PlayerLogFile, ex);
	    }
	}
	
	public void saveDefaultPlayerLog() {
	    if (PlayerLogFile == null) {
	    	PlayerLogFile = new File(getDataFolder(), "PlayerLog.yml");
	    }
	    if (!PlayerLogFile.exists()) {
	         plugin.saveResource("PlayerLog.yml", false);
	     }
	}

}