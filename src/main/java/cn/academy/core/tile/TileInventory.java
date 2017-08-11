/**
* Copyright (c) Lambda Innovation, 2013-2016
* This file is part of the AcademyCraft mod.
* https://github.com/LambdaInnovation/AcademyCraft
* Licensed under GPLv3, see project root for more information.
*/
package cn.academy.core.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

/**
 * You better directly extend this class rather than delegating it. Too much interfaces could be a burden.
 * <br/>
 * @author WeAthFolD
 */
public class TileInventory extends TileEntity implements IInventory {
    
    protected final ItemStack[] inventory;
    
    private final String invName;
    
    public TileInventory(String _invName, int size) {
        inventory = new ItemStack[size];
        invName = _invName;
    }
    
    public ItemStack[] getInventory() {
        return inventory;
    }

    @Override
    public int getSizeInventory() {
        return inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return inventory[slot];
    }
    
    @Override
    public ItemStack decrStackSize(int slot, int count) {
        if (inventory[slot] != null) {
            ItemStack itemstack;

            if (inventory[slot].stackSize <= count) {
                itemstack = inventory[slot];
                inventory[slot] = null;
            } else {
                itemstack = inventory[slot].splitStack(count);

                if (inventory[slot].stackSize == 0) {
                    inventory[slot] = null;
                }
            }
            this.markDirty();
            return itemstack;
        } else {
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        if (inventory[slot] != null) {
            ItemStack itemstack = inventory[slot];
            inventory[slot] = null;
            return itemstack;
        } else {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        inventory[slot] = stack;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
            stack.stackSize = this.getInventoryStackLimit();
        }

        this.markDirty();
    }
    
    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        NBTTagCompound tag = nbt.getCompoundTag("inventory");
        for(int i = 0; i < inventory.length; ++i) {
            String name = "" + i;
            if(tag.hasKey(name)) {
                inventory[i] = ItemStack.loadItemStackFromNBT(tag.getCompoundTag(name));
            }
        }
    }
    
    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        NBTTagCompound tag = new NBTTagCompound();
        for(int i = 0; i < inventory.length; ++i) {
            if(inventory[i] != null) {
                NBTTagCompound tag2 = new NBTTagCompound();
                inventory[i].writeToNBT(tag2);
                tag.setTag("" + i, tag2);
            }
        }
        
        nbt.setTag("inventory", tag);
    }

    @Override
    public String getInventoryName() {
        return invName;
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return true;
    }

    @Override
    public void openInventory() {}

    @Override
    public void closeInventory() {}

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        return true;
    }

}
