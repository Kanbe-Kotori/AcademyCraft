/**
* Copyright (c) Lambda Innovation, 2013-2016
* This file is part of the AcademyCraft mod.
* https://github.com/LambdaInnovation/AcademyCraft
* Licensed under GPLv3, see project root for more information.
*/
package cn.academy.vanilla.electromaster.entity;

import cn.academy.vanilla.electromaster.client.effect.ArcFactory;
import cn.academy.vanilla.electromaster.client.effect.ArcFactory.Arc;
import cn.lambdalib.annoreg.core.Registrant;
import cn.lambdalib.annoreg.mc.RegEntity;
import cn.lambdalib.util.deprecated.ViewOptimize;
import cn.lambdalib.util.deprecated.ViewOptimize.IAssociatePlayer;
import cn.lambdalib.util.entityx.EntityAdvanced;
import cn.lambdalib.util.generic.MathUtils;
import cn.lambdalib.util.helper.Motion3D;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * @author WeAthFolD
 *
 */
@Registrant
@RegEntity(clientOnly = true)
@SideOnly(Side.CLIENT)
@RegEntity.HasRender
public class EntityArc extends EntityAdvanced implements IAssociatePlayer {
    
    static final int GEN = 20;
    
    // Default patterns
    static Arc[] defaultPatterns = new Arc[GEN];
    static {
        ArcFactory fac = new ArcFactory();
        for(int i = 0; i < GEN; ++i) {
            defaultPatterns[i] = fac.generate(20);
        }
    }
    
    @SideOnly(Side.CLIENT)
    @RegEntity.Render
    public static Renderer render;
    
    final Arc[] patterns;
    
    int [] iid;
    int n = 1;//RandUtils.rangei(1, 2);
    boolean show = true;
    
    /**
     * Render properties
     */
    public double 
        showWiggle = .2,
        hideWiggle = .2,
        texWiggle = .5;
    
    public double length = 20.0;
    public boolean lengthFixed = true;
    
    public boolean viewOptimize = true;
    
    final EntityPlayer player;

    public EntityArc(EntityPlayer _player, Arc[]_patterns) {
        super(_player.worldObj);
        this.player = _player;
        
        new Motion3D(player, true).applyToEntity(this);
        ignoreFrustumCheck = true;
        iid = new int[n];
        
        this.patterns = _patterns;
    }
    
    public EntityArc(EntityPlayer _player) {
        this(_player, defaultPatterns);
    }
    
    @Override
    public void onUpdate() {
        super.onUpdate();
        for(int i = 0; i < iid.length; ++i) {
            if(rand.nextDouble() < texWiggle)
                iid[i] = rand.nextInt(patterns.length);
        }
        if(show && rand.nextDouble() < showWiggle) {
            show = !show;
        }
        else if(!show && rand.nextDouble() < hideWiggle) {
            show = !show;
        }
    }
    
    public void setFromTo(double x0, double y0, double z0, double x1, double y1, double z1) {
        setPosition(x0, y0, z0);
        
        double dx = x1 - x0, dy = y1 - y0, dz = z1 - z0;
        double dxzsq = dx * dx + dz * dz;
        rotationYaw = (float) (-Math.atan2(dx, dz) * 180 / Math.PI);
        rotationPitch = (float) (-Math.atan2(dy, Math.sqrt(dxzsq)) * 180 / Math.PI);
        
        length = MathUtils.distance(x0, y0, z0, x1, y1, z1);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound nbt) {
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound p_70014_1_) {
    }
    
    @Override
    public boolean shouldRenderInPass(int pass) {
        return pass == 1;
    }
    
    public static class Renderer extends Render {
        
        @Override
        public void doRender(Entity e, double x, double y, double z, float f, float g) {
            EntityArc arc = (EntityArc) e;
            if(!arc.show)
                return;
            
            GL11.glPushMatrix();
            
            GL11.glTranslated(x, y, z);
            GL11.glRotatef(arc.rotationYaw + 90, 0, -1, 0);
            GL11.glRotatef(arc.rotationPitch, 0, 0, -1);

            if(arc.viewOptimize) {
                ViewOptimize.fix(arc);
            }
            
            if(arc.lengthFixed) {
                for(int i = 0; i < arc.n; ++i)
                    arc.patterns[arc.iid[i]].draw();
            } else {
                for(int i = 0; i < arc.n; ++i) {
                    arc.patterns[arc.iid[i]].draw(arc.length);
                }
            }
            
            GL11.glPopMatrix();
        }

        @Override
        protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
            return null;
        }
        
    }

    @Override
    public EntityPlayer getPlayer() {
        return player;
    }

}
