package com.emirenesgames;

import org.lwjgl.Sys;

import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.GuiSmallButton;
import net.minecraft.src.StringTranslate;

public class GuiLinkScreen extends GuiScreen{

	protected GuiScreen parentScreen;

    /** First line of text. */
    protected String message1;
    
    /** First line of text. */
    protected String url = "";

    /** Second line of text. */
    private String message2;

    /** The text shown for the first button in GuiYesNo */
    protected String buttonText1;

    /** The text shown for the second button in GuiYesNo */
    protected String buttonText2;

    /** World number to be deleted. */
    public GuiLinkScreen(GuiScreen par1GuiScreen, String url)
    {
        this.parentScreen = par1GuiScreen;
        this.message1 = "Bu Yeri Göndermek İstiyor \"" + url +  "\"";
        if(url.startsWith("https://")) {
        	this.message2 = "Bundan Emin Misiniz?";
        } else {
        	this.message2 = "Bundan Emin Misiniz? Bu Url Güvenli Değil!";
        }
        
        this.url = url;
        StringTranslate var5 = StringTranslate.getInstance();
        this.buttonText1 = var5.translateKey("gui.yes");
        this.buttonText2 = var5.translateKey("gui.no");
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
        this.buttonList.add(new GuiSmallButton(0, this.width / 2 - 155, this.height / 6 + 96, this.buttonText1));
        this.buttonList.add(new GuiSmallButton(1, this.width / 2 - 155 + 160, this.height / 6 + 96, this.buttonText2));
    }

    /**
     * Fired when a control is clicked. This is the equivalent of ActionListener.actionPerformed(ActionEvent e).
     */
    protected void actionPerformed(GuiButton var1)
    {
    	if(var1.id == 0) {
    		Sys.openURL(url);
    		this.mc.displayGuiScreen(parentScreen);
    	}
    	if(var1.id == 1) {
    		this.mc.displayGuiScreen(parentScreen);
    	}
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int par1, int par2, float par3)
    {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRenderer, this.message1, this.width / 2, 70, 16777215);
        this.drawCenteredString(this.fontRenderer, this.message2, this.width / 2, 90, 16777215);
        super.drawScreen(par1, par2, par3);
    }

}
