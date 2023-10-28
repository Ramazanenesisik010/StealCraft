package net.minecraft.src;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.emirenesgames.*;

public class GuiOptions extends GuiScreen
{
    /** simple date formater */
    private final DateFormat dateFormatter = new SimpleDateFormat();

    /**
     * A reference to the screen object that created this. Used for navigating between screens.
     */
    protected GuiScreen parentScreen;

    /** The title string that is displayed in the top-center of the screen. */
    protected String screenTitle = "Options";

    /** True if a world has been selected. */
    private boolean selected = false;

    /** the currently selected world */
    private int selectedWorld;

    /** The save list for the world selection screen */
    private List saveList;
    private GuiOptionSlot worldSlotContainer;

	private GameSettings setting;

    public GuiOptions(GuiScreen par1GuiScreen,GameSettings setting)
    {
        this.parentScreen = par1GuiScreen;
        this.setting = setting;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
        StringTranslate var1 = StringTranslate.getInstance();
        this.screenTitle = var1.translateKey("menu.options");

        try
        {
            this.loadSaves();
        }
        catch (AnvilConverterException var3)
        {
            var3.printStackTrace();
            this.mc.displayGuiScreen(new GuiErrorScreen("Unable to load words", var3.getMessage()));
            return;
        }
        this.worldSlotContainer = new GuiOptionSlot(this);
        this.worldSlotContainer.registerScrollButtons(this.buttonList, 4, 5);
        this.initButtons();
    }

    /**
     * loads the saves
     */
    private void loadSaves() throws AnvilConverterException
    {
    	StringTranslate var1 = StringTranslate.getInstance();
    	this.saveList = new ArrayList();
        this.saveList.add(new Setting(new GuiVideoSettings(this,this.mc.gameSettings),0,0,var1.translateKey("options.video"),"Video Set."));
        this.saveList.add(new Setting(new GuiControls(this,this.mc.gameSettings),32,0,var1.translateKey("options.controls"),"Controls Set."));
        this.saveList.add(new Setting(new GuiLanguage(this,this.mc.gameSettings),96,0,var1.translateKey("options.language"),"Lang Set."));
        this.saveList.add(new Setting(new GuiGamePlay(this,this.mc.gameSettings),128,0,var1.translateKey("options.gameplay"),"Gameplay Set."));
        this.saveList.add(new Setting(new GuiSound(this,this.mc.gameSettings),64,0,var1.translateKey("options.musicAndSound"),"Sound Set."));
        this.saveList.add(new Setting(new GuiSnooper(this,this.mc.gameSettings),224,0,var1.translateKey("options.snooper.view"),"Snooper Set."));
        this.saveList.add(new Setting(new ScreenChatOptions(this,this.mc.gameSettings),192,0,var1.translateKey("options.chat.title"),"Chat Set."));
        this.saveList.add(new Setting(new GuiTexturePacks(this,this.mc.gameSettings),160,0,var1.translateKey("options.texture.pack"),"Texture Set."));
        this.selectedWorld = -1;
    }

    /**
     * returns the file name of the specified save number
     */
    public int getImgU(int par1)
    {
        return ((Setting)this.saveList.get(par1)).u;
    }
    
    public int getImgV(int par1)
    {
        return ((Setting)this.saveList.get(par1)).v;
    }
    
    public String getText(int par1)
    {
        return ((Setting)this.saveList.get(par1)).text;
    }
    
    public String getDesc(int par1)
    {
        return ((Setting)this.saveList.get(par1)).desc;
    }

    /**
     * returns the name of the saved game
     */

    /**
     * intilize the buttons for this GUI
     */
    public void initButtons()
    {
        StringTranslate var1 = StringTranslate.getInstance();
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height - 28,var1.translateKey("gui.cancel")));
    }

    /**
     * Fired when a control is clicked. This is the equivalent of ActionListener.actionPerformed(ActionEvent e).
     */
    protected void actionPerformed(GuiButton par1GuiButton)
    {
        if (par1GuiButton.enabled)
        {
            if (par1GuiButton.id == 0)
            {
                this.mc.displayGuiScreen(this.parentScreen);
            }
            else
            {
            	this.mc.gameSettings.saveOptions();
                this.worldSlotContainer.actionPerformed(par1GuiButton);
            }
        }
    }

    /**
     * Gets the selected world.
     */
    public void selectWorld(int par1)
    {
        this.mc.displayGuiScreen(new GuiLoading((((Setting)this.saveList.get(par1)).screen),0100));
    }

    

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int par1, int par2, float par3)
    {
        this.worldSlotContainer.drawScreen(par1, par2, par3);
        this.drawCenteredString(this.fontRenderer, this.screenTitle, this.width / 2, 5, 16777215);
        super.drawScreen(par1, par2, par3);
    }


    public static List getSize(GuiOptions par0GuiSelectWorld)
    {
        return par0GuiSelectWorld.saveList;
    }

    /**
     * called whenever an element in this gui is selected
     */
    public static int onElementSelected(GuiOptions par0GuiSelectWorld, int par1)
    {
        return par0GuiSelectWorld.selectedWorld = par1;
    }

    /**
     * returns the world currently selected
     */
    public static int getSelectedWorld(GuiOptions par0GuiSelectWorld)
    {
        return par0GuiSelectWorld.selectedWorld;
    }
}
