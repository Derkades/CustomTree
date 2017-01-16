package com.robinmc.customtree;

import java.io.File;

import com.robinmc.customtree.utils.EnumUtils;

public enum Tree {
	
	A_1,
	A_2,
	A_3,
	A_4,
	A_5,
	A_6,
	;
	
	public String getType(){
		return this.toString().substring(0, 1);
	}
	
	public File getFile(){
		return new File(Main.getPlugin().getDataFolder(), this + ".schematic");
	}
	
	public static Tree getRandomTree(String type){
		Tree tree = EnumUtils.getRandomEnum(Tree.class);
		if (tree.getType().equals(type)){
			return tree;
		} else {
			return getRandomTree(type);
		}
	}

}
