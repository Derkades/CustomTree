package com.robinmc.customtree;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.robinmc.customtree.utils.EnumUtils;

public enum Tree {
	
	A_1,
	A_2,
	A_3,
	A_4,
	A_5,
	A_6,
	
	B_1,
	B_2,
	B_3,
	B_4,
	B_5,
	B_6,
	B_7,
	B_8,
	
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
	
	public static List<String> getTreeTypesList(){
		List<String> list = new ArrayList<String>();
		for (Tree tree : Tree.values()){
			String type = tree.getType();
			if (!list.contains(type)){
				list.add(type);
			}
		}
		return list;
	}

}
