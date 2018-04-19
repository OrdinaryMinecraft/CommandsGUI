package ru.flametaichou.commandsgui;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.entity.player.EntityPlayer;
import ru.flametaichou.commandsgui.Handlers.SkillKeyHandler;

@SuppressWarnings("UnusedDeclaration")
public final class KeyClientProxy extends KeyProxy {

    @Override
    public void registerGui() {
        FMLCommonHandler.instance().bus().register(SkillKeyHandler.INSTANCE);
    }

    @Override
    public EntityPlayer getPlayer() {
        return FMLClientHandler.instance().getClient().thePlayer;
    }
}
