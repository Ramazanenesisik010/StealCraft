package com.emirenesgames;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.Random;

import org.lwjgl.input.Keyboard;

import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiMainMenu;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.GuiTextField;
import net.minecraft.src.Packet;
import net.minecraft.src.Packet250CustomPayload;
import net.minecraft.src.Session;
import net.minecraft.src.StatFileWriter;
import net.minecraft.src.StringTranslate;
import net.minecraft.src.TileEntityCommandBlock;

public class GuiSession extends GuiScreen {
	
	 private GuiTextField commandTextField;

	    /** Command block being edited. */
	    private GuiButton doneBtn;
	    private GuiButton cancelBtn;

	    /**
	     * Called from the main game loop to update the screen.
	     */
	    public void updateScreen()
	    {
	        this.commandTextField.updateCursorCounter();
	    }

	    /**
	     * Adds the buttons (and other controls) to the screen in question.
	     */
	    public void initGui()
	    {
	        StringTranslate var1 = StringTranslate.getInstance();
	        Keyboard.enableRepeatEvents(true);
	        this.buttonList.clear();
	        this.buttonList.add(this.doneBtn = new GuiButton(0, this.width / 2 - 100, this.height / 4 + 96 + 12, var1.translateKey("gui.done")));
	        this.buttonList.add(this.cancelBtn = new GuiButton(1, this.width / 2 - 100, this.height / 4 + 120 + 12, var1.translateKey("menu.quit")));
	        this.commandTextField = new GuiTextField(this.fontRenderer, this.width / 2 - 150, 60, 300, 20);
	        this.commandTextField.setMaxStringLength(32767);
	        this.commandTextField.setFocused(true);
	        String username = "Player" + System.currentTimeMillis();
	        this.commandTextField.setText(username);
	        this.doneBtn.enabled = this.commandTextField.getText().trim().length() > 0;
	    }

	    /**
	     * Called when the screen is unloaded. Used to disable keyboard repeat events
	     */
	    public void onGuiClosed()
	    {
	        Keyboard.enableRepeatEvents(false);
	    }

	    /**
	     * Fired when a control is clicked. This is the equivalent of ActionListener.actionPerformed(ActionEvent e).
	     */
	    protected void actionPerformed(GuiButton var1)
	    {
	        if(var1.id == 1) {
	        	this.mc.shutdown();
	        }
	        if(var1.id == 0 && this.commandTextField.getText() != null) {
	        	int randomID = new Random().nextInt(1000 + 1);
	        	this.mc.session = new Session(this.commandTextField.getText(),"" + randomID);
	        	this.mc.statFileWriter = new StatFileWriter(this.mc.session, this.mc.mcDataDir);
	        	this.mc.displayGuiScreen(new GuiMainMenu());
	        }
	    }

	    /**
	     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
	     */
	    protected void keyTyped(char par1, int par2)
	    {
	        this.commandTextField.textboxKeyTyped(par1, par2);
	        this.doneBtn.enabled = this.commandTextField.getText().trim().length() > 0;

	        if (par2 != 28 && par1 != 13)
	        {
	            if (par2 == 1)
	            {
	                this.actionPerformed(this.cancelBtn);
	            }
	        }
	        else
	        {
	            this.actionPerformed(this.doneBtn);
	        }
	    }

	    /**
	     * Called when the mouse is clicked.
	     */
	    protected void mouseClicked(int par1, int par2, int par3)
	    {
	        super.mouseClicked(par1, par2, par3);
	        this.commandTextField.mouseClicked(par1, par2, par3);
	    }

	    /**
	     * Draws the screen and all the components in it.
	     */
	    public void drawScreen(int par1, int par2, float par3)
	    {
	        StringTranslate var4 = StringTranslate.getInstance();
	        this.drawDefaultBackground();
	        this.drawCenteredString(this.fontRenderer, var4.translateKey("session.title"), this.width / 2, this.height / 4 - 60 + 20, 16777215);
	        this.drawString(this.fontRenderer, var4.translateKey("session.note"), this.width / 2 - 150, 119, 10526880);
	        this.commandTextField.drawTextBox();
	        super.drawScreen(par1, par2, par3);
	    }

}
