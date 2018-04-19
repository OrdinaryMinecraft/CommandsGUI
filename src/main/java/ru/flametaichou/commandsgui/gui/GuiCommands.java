package ru.flametaichou.commandsgui.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import ru.flametaichou.commandsgui.CommandsGui;
import ru.flametaichou.commandsgui.Util.ConfigHelper;

import java.awt.*;

public final class GuiCommands extends GuiScreen {
    private GuiTextField textField;

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    public void drawScreen(int i, int j, float f) {
        drawDefaultBackground();
        textField.drawTextBox();
        drawString(fontRendererObj, StatCollector.translateToLocalFormatted("Параметры (название, игрок):"), width / 2 - 155, 23 + 32 * 5, 0xffffff);
        drawCenteredString(fontRendererObj, StatCollector.translateToLocalFormatted("Команды:"), width / 2, 7, 0xffffff);
        super.drawScreen(i, j, f);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void initGui() {
        buttonList.clear();
        buttonList.add(new GuiButton(100, width / 2 - 48, 20 + 32 * 6, 96, 20, StatCollector.translateToLocal("gui.cancel")));
        for (int j = 0; j < ConfigHelper.buttonsCount; j = j + 3) {
            for (int i = 0; i < 3; i++) {
                int xPosition = width / 2 - 155 + i * (110);
                int yPosition = 20 + 32 * (j) / 3;
                GuiButton button = new GuiButton(i + j, xPosition, yPosition, 90, 20, ConfigHelper.buttons[i + j].getCommandName());
                buttonList.add(button);
            }
        }

        this.textField = new GuiTextField(this.fontRendererObj, width / 2 - 155 + 110, 20 + 32 * 5, 90 + 20 + 90, 16);
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
        } else if (guibutton.id != 100) {
            Minecraft.getMinecraft().thePlayer.sendChatMessage(ConfigHelper.buttons[guibutton.id].getCommand() + " " + this.textField.getText());
        }
    }
}
