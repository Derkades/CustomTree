package com.robinmc.customtree;

import org.bukkit.Material;

public class TreeBlock {
	
	private int x;
	private int y;
	private int z;
	private Material material;
	
	public TreeBlock (int x, int y, int z, Material material){
		this.x = x;
		this.y = y;
		this.z = z;
		this.material = material;
	}
	
	public int getRelativeX(){
		return x;
	}
	
	public int getRelativeY(){
		return y;
	}
	
	public int getRelativeZ(){
		return z;
	}
	
	public Material getMaterial(){
		return material;
	}

}
