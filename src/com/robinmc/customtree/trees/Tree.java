package com.robinmc.customtree.trees;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

import com.robinmc.customtree.Main;
import com.robinmc.customtree.TreeBlock;

public abstract class Tree {
	
	public abstract String getName();
	
	public abstract List<TreeBlock> getTreeBlocks();
	
	public void spawn(Location location){
		new BukkitRunnable(){
			
			List<TreeBlock> treeBlocks = Tree.this.getTreeBlocks();
			
			public void run(){
				if (treeBlocks.size() == 0){
					this.cancel();
					return;
				}
				
				//Get first and remove first, so next run there will be a different first
				TreeBlock treeBlock = treeBlocks.get(0);
				treeBlocks.remove(0);
				
				Location blockLocation = new Location(location.getWorld(), 
						location.getX() + treeBlock.getRelativeX(), 
						location.getY() + treeBlock.getRelativeY(), 
						location.getZ() + treeBlock.getRelativeZ());
				
				blockLocation.getBlock().setType(treeBlock.getMaterial());
			}
		}.runTaskTimer(Main.getPlugin(), 0, 2);
	}
	
	public static Tree fromString(String name){
		for (Tree tree : Main.TREE_LIST){
			if (tree.getName().equalsIgnoreCase(name)){
				return tree;
			}
		}
		return null;
	}

}
