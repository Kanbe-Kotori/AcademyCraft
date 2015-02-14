package cn.academy.core.register;

import cn.academy.core.block.dev.BlockDeveloper;
import cn.academy.core.block.dev.BlockMagInducer;
import cn.academy.energy.block.BlockMat;
import cn.academy.energy.block.BlockNode;
import cn.academy.energy.block.BlockSolarGenerator;
import cn.academy.misc.block.ACOre;
import cn.annoreg.core.RegistrationClass;
import cn.annoreg.mc.RegBlock;

@RegistrationClass
public class ACBlocks {
	
	@RegBlock
	public static BlockDeveloper developer;
	
	//@RegBlock
	//TODO: Wait until formal version
	//public static BlockWindGenerator windGen;
	
	@RegBlock
	public static BlockSolarGenerator solarGen;
	
	@RegBlock
	public static BlockMat grid;
	
	@RegBlock
	public static BlockNode node;
	
	@RegBlock
	public static BlockMagInducer magInducer;
	
	@RegBlock
	@RegBlock.OreDict("oreCopper")
	public static ACOre oreCopper = new ACOre("copperore", 1);
	
	@RegBlock
	@RegBlock.OreDict("oreTin")
	public static ACOre oreTin = new ACOre("tinore", 1);
	
	@RegBlock
	@RegBlock.OreDict("oreAluminum")
	public static ACOre oreAl = new ACOre("aluminumore", 1);
	
	@RegBlock
	@RegBlock.OreDict("oreMg")
	public static ACOre oreMg = new ACOre("mg_ore", 1);
	
	@RegBlock
	@RegBlock.OreDict("oreNi")
	public static ACOre oreNi = new ACOre("ni_ore", 1);
	
	@RegBlock
	@RegBlock.OreDict("oreCrystal")
	public static ACOre oreCrystal = new ACOre("crystal_ore", 1);
	
	@RegBlock
	@RegBlock.OreDict("oreShadow")
	public static ACOre oreShadow = new ACOre("shadow_ore", 1);
	
}
