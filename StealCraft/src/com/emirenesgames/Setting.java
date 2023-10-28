package com.emirenesgames;

import net.minecraft.src.GuiScreen;

public class Setting {
	
	public GuiScreen screen;
	public int u;
	public int v;
	public String text;
	public String desc;

	public Setting(GuiScreen screen,int u, int v,String text,String desc) {
		// TODO Auto-generated constructor stub
		this.desc = desc;
		this.text = text;
		this.screen = screen;
		this.u = u;
		this.v = v;
	}

}
