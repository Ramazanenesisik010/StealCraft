package com.emirenesgames;

import net.minecraft.src.*;

public class GuiSound extends GuiScreen{
	
	public GuiScreen parent;
	public GameSettings options;
	private String screenTitle = "Music And Sound";

	public GuiSound(GuiScreen parent, GameSettings setting) {
		this.options = setting;
		this.parent = parent;
	}

	public void drawScreen(int par1, int par2, float par3) {
		this.drawDefaultBackground();
        this.drawCenteredString(this.fontRenderer, this.screenTitle , this.width / 2, 15, 16777215);
        super.drawScreen(par1, par2, par3);
	}


	protected void actionPerformed(GuiButton a) {
		if(a.enabled) {
			if(a.id == 100) {
				this.options.saveOptions();
				this.mc.displayGuiScreen(this.parent);
			}
		}
	}
	
	public void initGui() {
		StringTranslate var1 = StringTranslate.getInstance();
		this.screenTitle = var1.translateKey("options.musicAndSound");
		this.buttonList.clear();
		this.buttonList.add(new GuiSlider(1, this.width / 2 - 155 + 0 % 2 * 160, this.height / 6 - 12 + 24 * (0 >> 1), EnumOptions.SOUND, this.options.getKeyBinding(EnumOptions.SOUND), this.options.getOptionFloatValue(EnumOptions.SOUND)));
		this.buttonList.add(new GuiSlider(2, this.width / 2 - 155 + 1 % 2 * 160, this.height / 6 - 12 + 24 * (1 >> 1), EnumOptions.MUSIC, this.options.getKeyBinding(EnumOptions.MUSIC), this.options.getOptionFloatValue(EnumOptions.MUSIC)));
		this.buttonList.add(new GuiButton(100, this.width / 2 - 100, this.height / 6 + 168, var1.translateKey("gui.done")));
	}
	
	

}
