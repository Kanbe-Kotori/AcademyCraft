package cn.nulladev.xinjiade;

import cn.academy.ability.api.Category;
import cn.academy.ability.api.CategoryManager;
import cn.nulladev.xinjiade.client.render.RenderBasicAirGun;
import cn.nulladev.xinjiade.entity.EntityBasicAirGun;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MyRegistry {
	
	public static final MyRegistry INSTANCE = new MyRegistry();
	private boolean initialized = false;
	
	public static Category air_manipulator;
	
	private MyRegistry() {}
		
	public void register(Object ModObject) {
		if (initialized)
			return;
		
		registerEntities(ModObject);
		registerCat();
		
		initialized = true;
	}
	
	public void registerClient() {
		registerRenderers();
	}
	
	private void registerCat() {
		System.out.println("Register my cat");

		air_manipulator = new CatAirManipulator();
		CategoryManager.INSTANCE.register(air_manipulator);
	}
	
	@SideOnly(Side.CLIENT)
	private void registerRenderers() {
		System.out.println("Register my render");
		RenderingRegistry.registerEntityRenderingHandler(EntityBasicAirGun.class, new RenderBasicAirGun());
	}
	
	private void registerEntities(Object ModObject) {
		System.out.println("Register my entity");
		int modID = 1;
    	EntityRegistry.registerModEntity(EntityBasicAirGun.class, "basic_air_gun", modID++, ModObject, 128, 1, true);
	}

}
