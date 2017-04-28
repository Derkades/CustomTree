package com.robinmc.customtree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.robinmc.customtree.trees.FancyOak;
import com.robinmc.customtree.trees.TestTree;
import com.robinmc.customtree.trees.Tree;

public class Main extends JavaPlugin implements Listener {

	private static Plugin plugin;
	
	private static final List<String> COOLDOWN = new ArrayList<String>();
	public static final Map<String, Tree> SELECTED_TREE = new HashMap<>();
	
	public static final Tree[] TREE_LIST = new Tree[]{
			new TestTree(),
			new FancyOak(),
	};

	@Override
	public void onEnable() {
		plugin = this;
		getServer().getPluginManager().registerEvents(this, this);
		getCommand("customtree").setExecutor(new TreeCommand());
	}

	public static Plugin getPlugin() {
		return plugin;
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void interact(PlayerInteractEvent event) {
		if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
			return;
		}

		Player player = event.getPlayer();
		
		if (COOLDOWN.contains(player.getName())){
			return;
		}
		
		COOLDOWN.add(player.getName());

		PlayerInventory inventory = player.getInventory();
		if (inventory.getItemInHand().getType() == Material.CARROT_STICK) {
			
			if (!SELECTED_TREE.containsKey(player.getName())){
				player.sendMessage(ChatColor.RED + "No tree type has been selected.");
				new BukkitRunnable(){
					public void run(){
						COOLDOWN.remove(player.getName());
					}
				}.runTaskLater(this, 3);
				return;
			}
			
			Tree tree = SELECTED_TREE.get(player.getName());
			
			Location location = event.getClickedBlock().getLocation().add(0, 1, 0);
			
			tree.spawn(location);
		}
		
		new BukkitRunnable(){
			public void run(){
				COOLDOWN.remove(player.getName());
			}
		}.runTaskLater(this, 3);
	}
	
	public static BlockFace getRandomDirection(){
		int random = new Random().nextInt(4);
		
		if (random == 0){
			return BlockFace.NORTH;
		} else if (random == 1){
			return BlockFace.EAST;
		} else if (random == 2){
			return BlockFace.SOUTH;
		} else {
			return BlockFace.WEST;
		}
	}

}
