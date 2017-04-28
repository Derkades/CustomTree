package com.robinmc.customtree.trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Material;

import com.robinmc.customtree.TreeBlock;

public class TestTree extends Tree {

	@Override
	public String getName() {
		return "TestTree";
	}

	@Override
	public List<TreeBlock> getTreeBlocks() {
		List<TreeBlock> list = new ArrayList<>();
		
		list.add(new TreeBlock(0, 0, 0, Material.LOG));
		list.add(new TreeBlock(0, 1, 0, Material.LEAVES));
		if (new Random().nextBoolean()){
			list.add(new TreeBlock(0, 2, 0, Material.LEAVES));
		}
		
		return list;
	}

}
