/**
* Copyright (c) Lambda Innovation, 2013-2016
* This file is part of the AcademyCraft mod.
* https://github.com/LambdaInnovation/AcademyCraft
* Licensed under GPLv3, see project root for more information.
*/

package cn.academy.vanilla.meltdowner.item;

import cn.academy.core.Resources;
import cn.academy.core.item.ACItem;
import cn.academy.vanilla.meltdowner.entity.EntitySilbarn;
import cn.lambdalib.annoreg.core.Registrant;
import cn.lambdalib.annoreg.mc.RegItem;
import cn.lambdalib.template.client.render.item.RenderModelItem;
import cn.lambdalib.util.deprecated.ItemModelCustom;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

@Registrant
public class ItemSilbarn extends ACItem {
    
    @SideOnly(Side.CLIENT)
    @RegItem.Render
    public static RenderSilbarn render;
    
    public ItemSilbarn() {
        super("silbarn");
    }
    
    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if(!world.isRemote) {
            world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
            world.spawnEntityInWorld(new EntitySilbarn(player));
        }
        if(!player.capabilities.isCreativeMode)
            --stack.stackSize;
        return stack;
    }

    @SideOnly(Side.CLIENT)
    public static class RenderSilbarn extends RenderModelItem {

        public RenderSilbarn() {
            super(new ItemModelCustom(Resources.getModel("silbarn")), Resources.getTexture("models/silbarn"));
            this.renderInventory = false;
            this.setStdRotation(90, 0, 0);
            this.setEquipRotation(0, 90, 0);
            this.setEquipOffset(.5, 0.1, -.2);
        }
        
    }
    
}