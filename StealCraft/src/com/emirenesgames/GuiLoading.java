package com.emirenesgames;

import org.lwjgl.input.Keyboard;

import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiMainMenu;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.GuiTextField;
import net.minecraft.src.StringTranslate;

public class GuiLoading extends GuiScreen {

	public GuiScreen parent = new GuiMainMenu();
	public long time = 20;
	
	public GuiLoading(GuiScreen onScreen,long time) {
		// TODO Auto-generated constructor stub
		this.time = time;
		this.parent = onScreen;
	}
	
	public void initGui()
    {
        this.buttonList.clear();
    }
	
	public void drawScreen(int par1, int par2, float par3) {
		this.drawDefaultBackground();
		this.drawCenteredString(this.fontRenderer, "Loading...", this.width / 2, this.height / 4 - 60 + 20, 16777215);
		--this.time;
		if(this.time < 0) {
			this.mc.displayGuiScreen(parent);
		}
	}

}
