package cn.nulladev.extrathings;

import cn.academy.ability.api.Skill;
import cn.academy.ability.api.context.ClientRuntime;
import cn.academy.ability.api.context.Context;
import cn.lambdalib.s11n.network.NetworkMessage.Listener;
import cn.lambdalib.util.generic.MathUtils;
import cn.nulladev.extrathings.entity.EntityBasicAirGun;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class SkillBasicAirGun extends Skill {

	public SkillBasicAirGun() {
		super("basic_air_gun", 1);
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void activate(ClientRuntime rt, int keyID) {
		activateSingleKey2(rt, keyID, (EntityPlayer p) -> new BasicAirGunContext(p));
	}
	
}

class BasicAirGunContext extends Context {
	
	final String MSG_PERFORM = "perform";
	
	private float cp;

	public BasicAirGunContext(EntityPlayer _player) {
		super(_player, new SkillBasicAirGun());
		
		cp = MathUtils.lerpf(200, 400, ctx.getSkillExp());
	}
	
	private boolean consume() {
		float overload = MathUtils.lerpf(10, 5, ctx.getSkillExp());
		return ctx.consume(overload, cp);
	}
	
	@Listener(channel=MSG_KEYDOWN, side=Side.CLIENT)
	private void l_keydown()  {
		sendToServer(MSG_PERFORM);
	}
	
	@Listener(channel=MSG_PERFORM, side=Side.SERVER)
	private void s_perform()  {
		if(consume()) {
			World world = player.worldObj;
			ctx.addSkillExp(getExpIncr());
			
			EntityBasicAirGun gun = new EntityBasicAirGun(world, player, ctx.getSkillExp(), player.getLookVec());
			
			ctx.setCooldown((int)MathUtils.lerpf(15, 5, ctx.getSkillExp()));
		}
	    terminate();
	}

	private float getExpIncr()  {
		return MathUtils.lerpf(0.008f, 0.004f, ctx.getSkillExp());
	}
		
}
	
