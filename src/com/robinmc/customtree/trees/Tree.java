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
			
			int counter = 0;
			List<TreeBlock> treeBlocks = Tree.this.getTreeBlocks();
			
			public void run(){
				if (counter > treeBlocks.size()){
					this.cancel();
					return;
				}
				
				TreeBlock treeBlock = treeBlocks.get(counter);
				
				Location blockLocation = new Location(location.getWorld(), 
						location.getX() + treeBlock.getRelativeX(), 
						location.getY() + treeBlock.getRelativeY(), 
						location.getZ() + treeBlock.getRelativeZ());
				
				blockLocation.getBlock().setType(treeBlock.getMaterial());
				
				counter++;
			}
		}.runTaskTimer(Main.getPlugin(), 0, 5);
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
