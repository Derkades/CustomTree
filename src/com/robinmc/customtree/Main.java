package com.robinmc.customtree;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
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

import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.world.DataException;

@SuppressWarnings("deprecation")
public class Main extends JavaPlugin implements Listener {

	private static Plugin plugin;
	
	private static final List<String> COOLDOWN = new ArrayList<String>();
	public static final Map<String, String> SELECTED_TYPE = new HashMap<>();

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
			
			if (!SELECTED_TYPE.containsKey(player.getName())){
				player.sendMessage(ChatColor.RED + "No tree type has been selected.");
				new BukkitRunnable(){
					public void run(){
						COOLDOWN.remove(player.getName());
					}
				}.runTaskLater(this, 3);
			}
			
			String type = SELECTED_TYPE.get(player.getName());
			
			Block block = event.getClickedBlock();
			Location loc = block.getLocation();
			double x = loc.getX();
			double y = loc.getY();
			y++;
			double z = loc.getZ();
			Tree tree = Tree.getRandomTree(type);
			File file = tree.getFile();
			try {
				pasteSchematic(player.getWorld(), file, new Vector(x, y, z));
			} catch (MaxChangedBlocksException | DataException | IOException e) {
				player.sendMessage(ChatColor.RED + "An error occured! Are the required files in the plugin directory?");
			}
		}
		
		new BukkitRunnable(){
			public void run(){
				COOLDOWN.remove(player.getName());
			}
		}.runTaskLater(this, 3);
	}

	private void pasteSchematic(World world, File file, Vector origin)
			throws DataException, IOException, MaxChangedBlocksException {
		EditSession session = new EditSession(new BukkitWorld(world), 99999999);
		CuboidClipboard clipboard = CuboidClipboard.loadSchematic(file);
		clipboard.paste(session, origin, true);
	}

}
