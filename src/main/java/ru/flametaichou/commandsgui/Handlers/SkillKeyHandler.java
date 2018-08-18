package ru.flametaichou.commandsgui.Handlers;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;
import ru.flametaichou.commandsgui.gui.GuiCommands;


public final class SkillKeyHandler {
    public static final SkillKeyHandler INSTANCE = new SkillKeyHandler();
    public final KeyBinding keyGui = new KeyBinding("commandsgui.key.opengui", Keyboard.KEY_G, "commandsgui.category");

    private SkillKeyHandler() {
        ClientRegistry.registerKeyBinding(keyGui);
    }

    @SubscribeEvent
    public void keyDown(InputEvent.KeyInputEvent event) {
        if (keyGui.getIsKeyPressed() && Minecraft.getMinecraft().currentScreen == null && Minecraft.getMinecraft().thePlayer != null) {
            Minecraft.getMinecraft().displayGuiScreen(new GuiCommands());
        }
    }
}
