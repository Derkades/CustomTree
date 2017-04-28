package com.robinmc.customtree.trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;

import com.robinmc.customtree.Main;
import com.robinmc.customtree.TreeBlock;

public class FancyOak extends Tree {

	@Override
	public String getName() {
		return "FancyOak";
	}

	@Override
	public List<TreeBlock> getTreeBlocks() {
List<TreeBlock> list = new ArrayList<>();
		
		int stemHeight = new Random().nextInt(3) + 4; //Stem height 4-6 (inclusive)
		
		for (int y = 0; y <= stemHeight; y++){
			list.add(new TreeBlock(0, y, 0, Material.LOG));
		}
		
		int secondaryX = 0; 
		int secondaryZ = 0;
		
		BlockFace secondaryDirection = Main.getRandomDirection();
		
		if (secondaryDirection == BlockFace.NORTH){
			secondaryZ--;
		} else if (secondaryDirection == BlockFace.EAST){
			secondaryX++;
		} else if (secondaryDirection == BlockFace.SOUTH){
			secondaryZ++;
		} else {
			secondaryX--;
		}
		
		int secondaryStemHeight = new Random().nextInt(2) + stemHeight / 2;
		
		for (int y = 0; y <= secondaryStemHeight; y++){
			list.add(new TreeBlock(secondaryX, y, secondaryZ, Material.LOG));
		}
		
		int tertiaryX = 0; 
		int tertiaryZ = 0;
		
		BlockFace tertiaryDirection = Main.getRandomDirection();
		if (tertiaryDirection == secondaryDirection)
			tertiaryDirection = Main.getRandomDirection();
		if (tertiaryDirection == secondaryDirection)
			tertiaryDirection = Main.getRandomDirection();
		
		if (tertiaryDirection == BlockFace.NORTH){
			tertiaryZ--;
		} else if (tertiaryDirection == BlockFace.EAST){
			tertiaryX++;
		} else if (tertiaryDirection == BlockFace.SOUTH){
			tertiaryZ++;
		} else {
			tertiaryX--;
		}
		
		int tertiaryStemHeight = (int) (secondaryStemHeight / 1.5);
		
		for (int y = 0; y <= tertiaryStemHeight; y++){
			list.add(new TreeBlock(tertiaryX, y, tertiaryZ, Material.LOG));
		}
		
		BlockFace lastBlockDirection = Main.getRandomDirection();
		if (lastBlockDirection == secondaryDirection || lastBlockDirection == tertiaryDirection)
			lastBlockDirection = Main.getRandomDirection();
		if (lastBlockDirection == secondaryDirection || lastBlockDirection == tertiaryDirection)
			lastBlockDirection = Main.getRandomDirection();
		
		int lastX = 0;
		int lastZ = 0;
		if (lastBlockDirection == BlockFace.NORTH){
			lastZ--;
		} else if (lastBlockDirection == BlockFace.EAST){
			lastX++;
		} else if (lastBlockDirection == BlockFace.SOUTH){
			lastZ++;
		} else {
			lastX--;
		}
		
		list.add(new TreeBlock(lastX, 0, lastZ, Material.LOG));
		
		list.addAll(Main.getLeaves(0, stemHeight, 0, Material.LEAVES));
		
		list.add(new TreeBlock(0, stemHeight, 0, Material.LOG));
		list.add(new TreeBlock(0, stemHeight - 1, 0, Material.LOG));
		list.add(new TreeBlock(0, stemHeight + 1, 0, Material.LOG));
	
		
		return list;
	}

}
