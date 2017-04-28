package com.robinmc.customtree;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.robinmc.customtree.trees.Tree;

public class TreeCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length != 1){
			return false;
		}
		
		if (args[0].equalsIgnoreCase("list")){
			List<String> namesList = new ArrayList<>();
			for (Tree tree : Main.TREE_LIST){
				namesList.add(tree.getName());
			}
			sender.sendMessage(String.join(", ", namesList));
			return true;
		}
		
		if (!(sender instanceof Player)){
			sender.sendMessage(ChatColor.RED + "You must be a player in order to execute this command.");
			return true;
		}
		
		Player player = (Player) sender;
		
		Tree tree = Tree.fromString(args[0]);
		
		if (tree == null){
			sender.sendMessage(ChatColor.RED + "Invalid tree type. Use /" + label + " list to display a list of all tree types.");
			return true;
		}
		
		Main.SELECTED_TREE.put(player.getName(), tree);
		
		player.sendMessage(ChatColor.AQUA + "Set tree type to " + ChatColor.DARK_AQUA + tree.getName() + ".");
		return true;
	}

}
