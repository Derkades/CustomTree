package com.robinmc.customtree;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.robinmc.customtree.utils.EnumUtils;

public enum Tree {
	
	A_01,
	A_02,
	A_03,
	A_04,
	A_05,
	A_06,
	
	B_01,
	B_02,
	B_03,
	B_04,
	B_05,
	B_06,
	B_07,
	B_08,
	
	SPRUCE_1_01,
	SPRUCE_1_02,
	SPRUCE_1_03,
	SPRUCE_1_04,
	SPRUCE_1_05,
	SPRUCE_1_06,
	SPRUCE_1_07,
	SPRUCE_1_08,
	
	;
	
	public String getType(){
		String full = this.toString();
		return full.substring(0, full.length() - 3);
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
