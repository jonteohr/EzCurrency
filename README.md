![](http://dev.bukkit.org/media/images/87/395/logo.PNG)

**EzCurrency is a simple currency plugin, allowing players to receive and trade money for items.**

Do you want your players to have money they can use to trade for items with other players without all the advanced stuff? Then this is the plugin for your server!
All that this plugin is, is a VERY basic currency system, where players can transact money to another player with no questions asked. No missions, no achievements, no advanced stuff!

#Permissions
| Node| Gives |
| -------- | -------- |
|**ezcurrency.add**|Gives the player/group access to /currency add <player> <amount> command, spawning free credits for the specified player.
|**ezcurrency.reload**|Access to /currency reload, reloading the configuration and logging file(s).|

#Commands
**/currency** - The main command for the plugin. This one specifically gives the player his or her current balance.  
**/currency help** - Gives the player a full list of command that he/she has access to.  
**/currency add <player> <amount>** - A command to spawn free credits and give it to the specified player.  
**/currency give <player> <amount>** - A command that everyone can access, transfer a specified amount of credits to a specified player.  
**/currency reload** - Reload all configuration and logging files. Saving them in their current state.  

#Config.yml
```ruby
# What the currency should be called.
currency_name: 'credits'
# If a 'developer' tag is to be shown when a developer of this plugin joins the server.
# Please leave this to true, if you really don't want it; you can easily set the value to false and reload the config with /currency reload
dev_tag: true
```

#Player logging
The plugin logs the players that have joined with their UUID. All logs are contained in the PlayerLog.yml file inside the plugin/EzCurrency folder.  
The player-log contains information on how to change the players' current balance.
```ruby
# Players are logged with their UUID.
# Below, you'll find all UUID's of joined players and their current balance. You can add coins/credits to their account by issuing the /currency add <player> <amount> command
# or you can change their currency via this file. Find the player's UUID and then change the number. Don't forget to /currency reload for the log to actually save and take effect.
```
Bellow the information will be the UUID's and balance of the players.
<br />
----
[**Official Twitter**](http://twitter.com/hyprcsgo) • [**Development blog**](http://jonathan.ohrstrom.nu/blog/) • [**BukkitDev**](http://dev.bukkit.org/bukkit-plugins/ezcurrency)
