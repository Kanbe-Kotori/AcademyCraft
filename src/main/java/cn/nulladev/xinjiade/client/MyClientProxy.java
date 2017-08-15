package cn.nulladev.xinjiade.client;

import org.lwjgl.opengl.Display;

import cn.nulladev.xinjiade.MyCommonProxy;
import cn.nulladev.xinjiade.MyRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class MyClientProxy extends MyCommonProxy {
	
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		Display.setTitle("Yourcraft 1.7.10");
	}
	
	@Override
	public void init(FMLInitializationEvent event) {
    	MyRegistry.INSTANCE.registerClient();
	}
	
	@Override
	public void postInit(FMLPostInitializationEvent event) {
		
	}

}
