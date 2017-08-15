package cn.nulladev.xinjiade.client.render;

import org.lwjgl.opengl.GL11;

import cn.nulladev.xinjiade.entity.EntityBasicAirGun;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderBasicAirGun extends Render {

	@Override
	public void doRender(Entity entity, double x, double y, double z, float p_76986_8_, float p_76986_9_) {
        this.doRender((EntityBasicAirGun)entity, x, y, z, p_76986_8_, p_76986_9_);
    }
	
	public void doRender(EntityBasicAirGun entity, double x, double y, double z, float p_76986_8_, float p_76986_9_) {
		System.out.println("render");
		GL11.glPushMatrix();
        GL11.glTranslated(x, y, z);

        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDepthMask(false);
        
        Tessellator tessellator = Tessellator.instance;
        
        double r = entity.width;
        
        System.out.println(r);

        GL11.glColor4f(0.5F, 0.5F, 0.5F, 0.5F);
                
        for (int i = 0; i < 4; ++i) {
            GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
            GL11.glNormal3f(0.0F, 0.0F, 1.0F);
            tessellator.startDrawingQuads();
            tessellator.addVertex( r, -r, r); // 右下
            tessellator.addVertex( r, r, r); // 右上
            tessellator.addVertex(-r, r, r); // 左上
            tessellator.addVertex(-r, -r, r); // 左下
            tessellator.draw();
        }
        
        for (int i = 0; i < 2; ++i) {
            GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
            GL11.glNormal3f(0.0F, 0.0F, 1.0F);
            tessellator.startDrawingQuads();
            tessellator.addVertex( r, -r, r); // 右下
            tessellator.addVertex( r, r, r); // 右上
            tessellator.addVertex(-r, r, r); // 左上
            tessellator.addVertex(-r, -r, r); // 左下
            tessellator.draw();
        }

        GL11.glDepthMask(true);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_TEXTURE_2D);

        GL11.glPopMatrix();
    }

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		return null;
	}
}