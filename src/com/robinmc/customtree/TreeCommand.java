package com.robinmc.customtree;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TreeCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)){
			return false;
		}
		
		if (args.length != 1){
			return false;
		}

		
		
		if (args[0].equalsIgnoreCase("list")){
			sender.sendMessage(String.join(", ", Tree.getTreeTypesList()));
			return true;
		}
		
		String type = args[0].toUpperCase();
		
		if (!Tree.getTreeTypesList().contains(type)){
			sender.sendMessage(ChatColor.RED + "Invalid tree type. Use /" + label + " list to display a list of all tree types.");
			return true;
		}
		
		Player player = (Player) sender;
		
		Main.SELECTED_TYPE.put(player.getName(), type);
		
		player.sendMessage(ChatColor.AQUA + "Set tree type to " + ChatColor.DARK_AQUA + type + ".");
		return true;
	}

}
