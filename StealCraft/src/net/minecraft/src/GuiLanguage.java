package net.minecraft.src;

import org.lwjgl.LWJGLException;

public class GuiLanguage extends GuiScreen
{
    /** This GUI's parent GUI. */
    protected GuiScreen parentGui;

    /**
     * Timer used to update texture packs, decreases every tick and is reset to 20 and updates texture packs upon
     * reaching 0.
     */
    private int updateTimer = -1;
    private boolean unicode;

    /** This GUI's language list. */
    private GuiSlotLanguage languageList;

    /** For saving the user's language selection to disk. */
    private final GameSettings theGameSettings;

    /** This GUI's 'Done' button. */
    private GuiSmallButton doneButton;
    private GuiSmallButton isUnicodeButton;

    public GuiLanguage(GuiScreen par1GuiScreen, GameSettings par2GameSettings)
    {
        this.parentGui = par1GuiScreen;
        this.theGameSettings = par2GameSettings;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
        StringTranslate var1 = StringTranslate.getInstance();
        this.buttonList.add(this.doneButton = new GuiSmallButton(6, this.width / 2 + 5, this.height - 38, var1.translateKey("gui.done")));
        this.buttonList.add(this.isUnicodeButton = new GuiSmallButton(10, this.width / 3 - 80, this.height - 38, var1.translateKey("gui.isUnicode") +" "+ GuiLanguage.getGameSettings(this).isUnicode));
        this.languageList = new GuiSlotLanguage(this);
        this.languageList.registerScrollButtons(this.buttonList, 7, 8);
    }

    /**
     * Fired when a control is clicked. This is the equivalent of ActionListener.actionPerformed(ActionEvent e).
     */
    protected void actionPerformed(GuiButton par1GuiButton)
    {
    	unicode = StringTranslate.getInstance().isUnicode();
        if (par1GuiButton.enabled)
        {
            switch (par1GuiButton.id)
            {
                case 10:
                	unicode =! unicode;
                	StringTranslate.getInstance().setUnicode(unicode);
                	this.mc.fontRenderer.setUnicodeFlag(unicode);
                	GuiLanguage.getGameSettings(this).isUnicode = unicode;
                	GuiLanguage.getGameSettings(this).saveOptions();
                    try {
            			this.mc.loadScreen(100);
            			try {
            				Thread.sleep(1000L);
            			} catch (InterruptedException e) {
            				;
            			}
            		} catch (LWJGLException e) {
            			;
            		}
                    
                    break;

                case 6:
                    this.mc.displayGuiScreen(this.parentGui);
                    break;
                case 9:
                	break;

                default:
                    this.languageList.actionPerformed(par1GuiButton);
            }
        }
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int par1, int par2, float par3)
    {
        this.languageList.drawScreen(par1, par2, par3);

        if (this.updateTimer <= 0)
        {
            this.mc.texturePackList.updateAvaliableTexturePacks();
            this.updateTimer += 20;
        }

        StringTranslate var4 = StringTranslate.getInstance();
        this.drawCenteredString(this.fontRenderer, var4.translateKey("options.language"), this.width / 2, 16, 16777215);
        this.drawCenteredString(this.fontRenderer, "(" + var4.translateKey("options.languageWarning") + ")", this.width / 2, this.height - 56, 8421504);
        super.drawScreen(par1, par2, par3);
    }

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {
        super.updateScreen();
        --this.updateTimer;
    }

    /**
     * Gets the relevant instance of GameSettings. Synthetic method for use in GuiSlotLanguage
     */
    static GameSettings getGameSettings(GuiLanguage par0GuiLanguage)
    {
        return par0GuiLanguage.theGameSettings;
    }

    /**
     * Returns the private doneButton field.
     */
    static GuiSmallButton getDoneButton(GuiLanguage par0GuiLanguage)
    {
        return par0GuiLanguage.doneButton;
    }
}
