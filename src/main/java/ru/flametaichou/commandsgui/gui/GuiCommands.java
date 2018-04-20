package ru.flametaichou.commandsgui.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.StatCollector;
import ru.flametaichou.commandsgui.Util.ConfigHelper;

public final class GuiCommands extends GuiScreen {
    private GuiTextField textField;
    private int buttonsOffset = 0;

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    public void drawScreen(int i, int j, float f) {
        drawDefaultBackground();
        textField.drawTextBox();
        drawString(fontRendererObj, StatCollector.translateToLocalFormatted("gui.params"), width / 2 - 170, 23 + 32 * 5, 0xffffff);
        drawCenteredString(fontRendererObj, StatCollector.translateToLocalFormatted("gui.commands"), width / 2, 7, 0xffffff);
        super.drawScreen(i, j, f);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void initGui() {
        buttonList.clear();
        buttonList.add(new GuiButton(100, width / 2 - 30, 20 + 32 * 6, 60, 20, StatCollector.translateToLocal("gui.close")));
        buttonList.add(new GuiButton(101, width / 2 - 170, 20 + 32 * 6, 20, 20, StatCollector.translateToLocal("<")));
        buttonList.add(new GuiButton(101, width / 2 + 150, 20 + 32 * 6, 20, 20, StatCollector.translateToLocal(">")));
        for (int j = 0; j < ConfigHelper.pageCount; j = j + 3) {
            for (int i = 0; i < 3; i++) {
                int xPosition = width / 2 - 170 + i * (120);
                int yPosition = 20 + 32 * (j) / 3;
                if (ConfigHelper.buttons[i + j + buttonsOffset].getEnabled()) {
                    GuiButton button = new GuiButton(i + j + buttonsOffset, xPosition, yPosition, 100, 20, ConfigHelper.buttons[i + j + buttonsOffset].getCommandName());
                    buttonList.add(button);
                }
            }
        }

        this.textField = new GuiTextField(this.fontRendererObj, width / 2 - 143 + 110, 20 + 32 * 5, 90 + 20 + 90, 16);
        textField.setMaxStringLength(255);
        textField.setText("");
        textField.setFocused(true);
    }

    protected void keyTyped(char par1, int par2)
    {
        super.keyTyped(par1, par2);
        textField.textboxKeyTyped(par1, par2);
    }

    public void updateScreen()
    {
        super.updateScreen();
        textField.updateCursorCounter();
    }

    protected void mouseClicked(int x, int y, int btn) {
        super.mouseClicked(x, y, btn);
        textField.mouseClicked(x, y, btn);
    }

    @Override
    public void onGuiClosed() {
    }

    @Override
    protected void actionPerformed(GuiButton guibutton) {
        if (guibutton.id == 100) {
            mc.displayGuiScreen(null);
            mc.setIngameFocus();
        } else if (guibutton.id == 101) {
            if (buttonsOffset == 0) {
                buttonsOffset = ConfigHelper.pageCount;
            } else {
                buttonsOffset = 0;
            }
            initGui();
        } else if (guibutton.id != 100) {
            Minecraft.getMinecraft().thePlayer.sendChatMessage(ConfigHelper.buttons[guibutton.id].getCommand() + " " + this.textField.getText());
            mc.displayGuiScreen(null);
            mc.setIngameFocus();
        }
    }
}
