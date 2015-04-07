/**
 * Copyright (c) Lambda Innovation, 2013-2015
 * 本作品版权由Lambda Innovation所有。
 * http://www.li-dev.cn/
 *
 * This project is open-source, and it is distributed under  
 * the terms of GNU General Public License. You can modify
 * and distribute freely as long as you follow the license.
 * 本项目是一个开源项目，且遵循GNU通用公共授权协议。
 * 在遵照该协议的情况下，您可以自由传播和修改。
 * http://www.gnu.org/licenses/gpl.html
 */
package cn.academy.misc.util;

import net.minecraft.util.ResourceLocation;
import cn.academy.core.proxy.ACClientProps;
import cn.liutils.util.ClientUtils;
import cn.liutils.util.render.LambdaFont.Align;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Just some very generic utils.
 * @author WeathFolD
 */
public class ACUtils {

	@SideOnly(Side.CLIENT)
	public static void drawText(String text, double x, double y, double size) {
		ACClientProps.font().draw(text, x, y, size);
	}
	
	@SideOnly(Side.CLIENT)
	public static void drawText(String text, double x, double y, double size, Align align) {
		ACClientProps.font().draw(text, x, y, size, align);
	}
	
	@SideOnly(Side.CLIENT)
	public static void drawText(String text, double x, double y, double size, double cst) {
		ACClientProps.font().drawAdjusted(text, x, y, size, cst);
	}
	
	@SideOnly(Side.CLIENT)
	public static void drawText(String text, double x, double y, double size, Align align, double cst) {
		ACClientProps.font().drawAdjusted(text, x, y, size, align, cst);
	}
	
	public static final ResourceLocation abortSound = new ResourceLocation("academy:deny");
	public static void playAbortSound() {
		ClientUtils.playSound(abortSound, 1.0f);
	}

}