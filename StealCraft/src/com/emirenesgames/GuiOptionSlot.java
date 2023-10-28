package com.emirenesgames;

import java.util.Date;

import org.lwjgl.opengl.GL11;

import net.minecraft.src.GuiOptions;
import net.minecraft.src.GuiSlot;
import net.minecraft.src.Tessellator;

public class GuiOptionSlot extends GuiSlot
{
    final GuiOptions parentWorldGui;

    public GuiOptionSlot(GuiOptions par1GuiSelectWorld)
    {
        super(par1GuiSelectWorld.mc, par1GuiSelectWorld.width, par1GuiSelectWorld.height - 15, 18, par1GuiSelectWorld.height - 55 + 4, 36);
        this.parentWorldGui = par1GuiSelectWorld;
    }

    /**
     * Gets the size of the current slot list.
     */
    protected int getSize()
    {
        return GuiOptions.getSize(this.parentWorldGui).size();
    }

    /**
     * the element in the slot that was clicked, boolean for wether it was double clicked or not
     */
    protected void elementClicked(int par1, boolean par2)
    {
    	GuiOptions.onElementSelected(this.parentWorldGui, par1);
    	if(par2) {
    		this.parentWorldGui.selectWorld(this.parentWorldGui.getSelectedWorld(this.parentWorldGui));
    	}
    	
    }

    /**
     * returns true if the element passed in is currently selected
     */
    protected boolean isSelected(int par1)
    {
        return par1 == GuiOptions.getSelectedWorld(this.parentWorldGui);
    }

    /**
     * return the height of the content being scrolled
     */
    protected int getContentHeight()
    {
        return GuiOptions.getSize(this.parentWorldGui).size() * 36;
    }

    protected void drawBackground()
    {
        this.parentWorldGui.drawDefaultBackground();
    }

    protected void drawSlot(int par1, int par2, int par3, int par4, Tessellator var9)
    {
        this.parentWorldGui.drawString(this.parentWorldGui.fontRenderer, this.parentWorldGui.getText(par1), par2 + 34, par3 + 1, 16777215);
        this.parentWorldGui.drawString(this.parentWorldGui.fontRenderer, this.parentWorldGui.getDesc(par1), par2 + 34, par3 + 12, 8421504);
        this.parentWorldGui.mc.renderEngine.bindTexture("/gui/options.png");
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.parentWorldGui.drawTexturedModalRect(par2, par3, this.parentWorldGui.getImgU(par1), this.parentWorldGui.getImgV(par1), 32, 32);
        var9.startDrawingQuads();
        var9.addVertexWithUV((double)(par2 + 0), (double)(par3 + 32), 0, (double)((float)(this.parentWorldGui.getImgU(par1) + 0)), (double)((float)(this.parentWorldGui.getImgV(par1) + 32)));
        var9.addVertexWithUV((double)(par2 + 32), (double)(par3 + 32), 0, (double)((float)(this.parentWorldGui.getImgU(par1) + 32)), (double)((float)(this.parentWorldGui.getImgV(par1) + 32)));
        var9.addVertexWithUV((double)(par2 + 32), (double)(par3 + 0), 0, (double)((float)(this.parentWorldGui.getImgU(par1) + 32)), (double)((float)(this.parentWorldGui.getImgV(par1) + 0)));
        var9.addVertexWithUV((double)(par2 + 0), (double)(par3 + 0), 0, (double)((float)(this.parentWorldGui.getImgU(par1) + 0)), (double)((float)(this.parentWorldGui.getImgV(par1) + 0)));
        var9.draw();

    }
}
