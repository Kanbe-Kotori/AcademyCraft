package cn.nulladev.extrathings;

import cn.academy.ability.api.Category;
import cn.academy.ability.api.CategoryManager;

public class MyRegistry {
	
	public static final MyRegistry INSTANCE = new MyRegistry();
	private boolean initialized = false;
	
	public static Category air_manipulator;
	
	private MyRegistry() {}
		
	public void register() {
		if (initialized)
			return;
		
		registerCat();
		
		initialized = true;
	}
	
	private void registerCat() {
		air_manipulator = new CatAirManipulator();
		CategoryManager.INSTANCE.register(air_manipulator);
	}

}
