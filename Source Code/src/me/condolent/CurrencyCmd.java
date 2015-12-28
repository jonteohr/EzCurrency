package me.condolent;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class CurrencyCmd implements CommandExecutor {

	EzCurrencyMain plugin;
	
	public CurrencyCmd(EzCurrencyMain instance) {
		plugin = instance;
	}
	
	public FileConfiguration getConfig() {
		return plugin.getConfig(); 
	}
	
	public FileConfiguration getPlayerLog() {
		return plugin.getPlayerLog();
	}
	
	public static boolean isInt(String s) {
	    try {
	        Integer.parseInt(s);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
		Player p = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("currency")) {
			
			if(args.length < 1) {
				p.sendMessage(ChatColor.AQUA + "§l[EzCurrency] " + ChatColor.YELLOW + "Your balance: " + getPlayerLog().getInt(p.getUniqueId().toString()) + " " +  getConfig().getString("currency_name") + ".");
			} else if(args.length == 1) {
				
				if(args[0].equalsIgnoreCase("info")) {
					p.sendMessage(ChatColor.AQUA + "§l[EzCurrency] " + ChatColor.YELLOW + "This server is running " + plugin.pdf.getName() + " version " + plugin.pdf.getVersion());
				} else if(args[0].equalsIgnoreCase("help")) {
					
					p.sendMessage(ChatColor.YELLOW + "================= EzCurrency =================");
					p.sendMessage(ChatColor.YELLOW + "/currency - Shows your current balance.");
					p.sendMessage(ChatColor.YELLOW + "/currency give <player> <amount> - Gives a specified player a amount from your own balance.");
					if(p.hasPermission(plugin.add)) {
						p.sendMessage(ChatColor.YELLOW + "/currency add <player> <amount> - Give a specified player free amounts of " + getConfig().getString("currency_name") + ".");
					}
					if(p.hasPermission(plugin.reload)) {
						p.sendMessage(ChatColor.YELLOW + "/currency reload - Reload all the files for the plugin.");
					}
					p.sendMessage(ChatColor.YELLOW + "==============================================");
					
				} else if(args[0].equalsIgnoreCase("reload")) {
					
					if(p.hasPermission(plugin.reload)) {
						plugin.reloadConfig();
						plugin.reloadPlayerLog();
						plugin.saveConfig();
						plugin.savePlayerLog();
						p.sendMessage(ChatColor.AQUA + "§l[EzCurrency] " + ChatColor.GREEN + "All files reloaded successfully!");
					} else {
						p.sendMessage(ChatColor.AQUA + "§l[EzCurrency] " + ChatColor.RED + "You do not have permission to that command.");
					}
					
				} else if(!args[0].equalsIgnoreCase("info") && !args[0].equalsIgnoreCase("help") && !args[0].equalsIgnoreCase("reload")) {
				
					p.sendMessage(ChatColor.AQUA + "§l[EzCurrency] " + ChatColor.RED + "Not a correct command.");
					p.sendMessage(ChatColor.AQUA + "§l[EzCurrency] " + ChatColor.YELLOW + "Type §o/currency help §r§efor a list of available commands.");
				}
				
			} else if(args.length == 2) {

				p.sendMessage(ChatColor.AQUA + "§l[EzCurrency] " + ChatColor.RED + "Incorrect use of command.");
				p.sendMessage(ChatColor.AQUA + "§l[EzCurrency] " + ChatColor.YELLOW + "Type §o/currency help §r§efor a list of available commands.");
				
			} else if(args.length == 3) {
				
				if(!args[0].equalsIgnoreCase("give") && !args[0].equalsIgnoreCase("add")) {
					p.sendMessage(ChatColor.AQUA + "§l[EzCurrency] " + ChatColor.RED + "Not a correct command.");
					p.sendMessage(ChatColor.AQUA + "§l[EzCurrency] " + ChatColor.YELLOW + "Type §o/currency help §r§efor a list of available commands.");
				}
				
				if(args[0].equalsIgnoreCase("give")) {
					@SuppressWarnings("deprecation")
					Player target = Bukkit.getPlayerExact(args[1]);
					if(target == null) {
						p.sendMessage(ChatColor.AQUA + "§l[EzCurrency] " + ChatColor.RED + "Player " + args[1] + " is not online.");
						return true;
					}
					String targetUUID = target.getUniqueId().toString();
					if(isInt(args[2])) {
						if(getPlayerLog().getInt(p.getUniqueId().toString()) > Integer.parseInt(args[2])) {
							getPlayerLog().set(targetUUID, getPlayerLog().getInt(targetUUID) + Integer.parseInt(args[2]));
							getPlayerLog().set(p.getUniqueId().toString(), getPlayerLog().getInt(p.getUniqueId().toString()) - Integer.parseInt(args[2]));
							plugin.savePlayerLog();
							p.sendMessage(ChatColor.AQUA + "§l[EzCurrency] " + ChatColor.YELLOW + "You've given " + target.getName() + " " + args[2] + " " + getConfig().getString("currency_name"));
							target.sendMessage(ChatColor.AQUA + "§l[EzCurrency] " + ChatColor.YELLOW + p.getName() + " has given you " + args[2] + " " + getConfig().getString("currency_name"));
						} else {
							p.sendMessage(ChatColor.AQUA + "§l[EzCurrency] " + ChatColor.RED + "You can't afford to transfer " + args[2] + ". Your current balance is: " + getPlayerLog().getInt(targetUUID));
						}
							
					} else {
						p.sendMessage(ChatColor.AQUA + "§l[EzCurrency] " + ChatColor.RED + args[2] + " is not a valid amount.");
					}
					
				} else if(args[0].equalsIgnoreCase("add")) {
					@SuppressWarnings("deprecation")
					Player target = Bukkit.getPlayerExact(args[1]);
					if(target == null) {
						p.sendMessage(ChatColor.AQUA + "§l[EzCurrency] " + ChatColor.RED + "Player " + args[1] + " is not online.");
						return true;
					}
					String targetUUID = target.getUniqueId().toString();
					
					if(p.hasPermission(plugin.add)) {
						if(isInt(args[2])) {
							getPlayerLog().set(targetUUID, getPlayerLog().getInt(targetUUID) + Integer.parseInt(args[2]));
							plugin.savePlayerLog();
							p.sendMessage(ChatColor.AQUA + "§l[EzCurrency] " + ChatColor.YELLOW + "You've successfully added " + args[2] + " to " + target.getName() + "'s balance.");
							target.sendMessage(ChatColor.AQUA + "§l[EzCurrency] " + ChatColor.YELLOW + p.getName() + " has given you " + args[2] + " " + getConfig().getString("currency_name"));
						} else {
							p.sendMessage(ChatColor.AQUA + "§l[EzCurrency] " + ChatColor.RED + args[2] + " is not a valid amount.");
						}
						
					} else {
						p.sendMessage(ChatColor.AQUA + "§l[EzCurrency] " + ChatColor.RED + "You do not have permission to that command.");
					}
				}
				
			} else if(args.length > 3) {
				p.sendMessage(ChatColor.AQUA + "§l[EzCurrency] " + ChatColor.RED + "Incorrect use of command.");
				p.sendMessage(ChatColor.AQUA + "§l[EzCurrency] " + ChatColor.YELLOW + "Type §o/currency help §r§efor a list of available commands.");
			}
			
			return true;
		}
		
		return false;
	}

}
