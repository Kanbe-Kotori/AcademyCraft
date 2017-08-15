package cn.nulladev.xinjiade;

import cn.academy.ability.api.Skill;
import cn.academy.ability.api.context.ClientRuntime;
import cn.academy.ability.api.context.Context;
import cn.lambdalib.s11n.network.NetworkMessage.Listener;
import cn.lambdalib.util.generic.MathUtils;
import cn.nulladev.xinjiade.entity.EntityBasicAirGun;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class SkillBasicAirGun extends Skill {
	
	public static final SkillBasicAirGun INSTANCE = new SkillBasicAirGun();

	private SkillBasicAirGun() {
		super("basic_air_gun", 1);
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void activate(ClientRuntime rt, int keyID) {
		activateSingleKey2(rt, keyID, (EntityPlayer p) -> new BasicAirGunContext(p));
	}
	
}