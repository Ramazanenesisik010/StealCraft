package com.emirenesgames;

import net.minecraft.src.*;

public class GuiGamePlay extends GuiScreen{
	
	public GuiScreen parent;
	public GameSettings options;
	private String screenTitle = "Game Play";
	private static final EnumOptions[] relevantOptions = new EnumOptions[] {EnumOptions.INVERT_MOUSE, EnumOptions.SENSITIVITY, EnumOptions.FOV, EnumOptions.DIFFICULTY, EnumOptions.TOUCHSCREEN};

	public GuiGamePlay(GuiScreen parent, GameSettings setting) {
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
			if (a.id < 100 && a instanceof GuiSmallButton)
            {
                this.options.setOptionValue(((GuiSmallButton)a).returnEnumOptions(), 1);
                a.displayString = this.options.getKeyBinding(EnumOptions.getEnumOptions(a.id));
            }
			if(a.id == 100) {
				this.options.saveOptions();
				this.mc.displayGuiScreen(this.parent);
			}
		}
	}
	
	public void initGui() {
		StringTranslate var1 = StringTranslate.getInstance();
		this.screenTitle = var1.translateKey("options.gameplay");
		int var2 = 0;
        EnumOptions[] var3 = relevantOptions;
        int var4 = var3.length;

        for (int var5 = 0; var5 < var4; ++var5)
        {
            EnumOptions var6 = var3[var5];

            if (var6.getEnumFloat())
            {
                this.buttonList.add(new GuiSlider(var6.returnEnumOrdinal(), this.width / 2 - 155 + var2 % 2 * 160, this.height / 6 - 12 + 24 * (var2 >> 1), var6, this.options.getKeyBinding(var6), this.options.getOptionFloatValue(var6)));
            }
            else
            {
                GuiSmallButton var7 = new GuiSmallButton(var6.returnEnumOrdinal(), this.width / 2 - 155 + var2 % 2 * 160, this.height / 6 - 12 + 24 * (var2 >> 1), var6, this.options.getKeyBinding(var6));

                if (var6 == EnumOptions.DIFFICULTY && this.mc.theWorld != null && this.mc.theWorld.getWorldInfo().isHardcoreModeEnabled())
                {
                    var7.enabled = false;
                    var7.displayString = StatCollector.translateToLocal("options.difficulty") + ": " + StatCollector.translateToLocal("options.difficulty.hardcore");
                }

                this.buttonList.add(var7);
            }

            ++var2;
        }
		this.buttonList.add(new GuiButton(100, this.width / 2 - 100, this.height / 6 + 168, var1.translateKey("gui.done")));
	}
	
	

}
