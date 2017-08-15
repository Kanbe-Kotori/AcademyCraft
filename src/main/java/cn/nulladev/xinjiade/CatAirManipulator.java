package cn.nulladev.xinjiade;

import cn.academy.ability.api.Category;
import cn.academy.ability.api.Skill;
import cn.academy.vanilla.ModuleVanilla;

public class CatAirManipulator extends Category {
	
	public static Skill basic_air_gun = SkillBasicAirGun.INSTANCE;

	public CatAirManipulator() {
		super("airmanipulator");
		this.colorStyle.setColor4i(127, 127, 255, 80);
		
		addSkill(basic_air_gun);
        ModuleVanilla.addGenericSkills(this);
		
		basic_air_gun.setPosition(24, 46);
	}

}
