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
		int counter = 0;
		new BukkitRunnable(){
			public void run(){
				if (counter > Tree.this.getTreeBlocks().size()){
					this.cancel();
					return;
				}
				
				TreeBlock treeBlock = Tree.this.getTreeBlocks().get(counter);
				location.add(treeBlock.getRelativeX(), treeBlock.getRelativeY(), treeBlock.getRelativeZ())
					.getBlock()
					.setType(treeBlock.getMaterial());
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
