package ru.flametaichou.commandsgui;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;
import ru.flametaichou.commandsgui.Util.ConfigHelper;

@Mod(modid = CommandsGui.ID, name = "CommandsGui", version = "0.1", acceptableRemoteVersions="*")
public final class CommandsGui {
    public final static String ID = "commandsgui";
    @Mod.Instance(value = ID)
    public static CommandsGui instance;
    @SidedProxy(clientSide = "ru.flametaichou.commandsgui.KeyClientProxy", serverSide = "ru.flametaichou.commandsgui.KeyProxy")
    public static KeyProxy proxy;

    @EventHandler
    public void load(FMLInitializationEvent event) {
        proxy.registerGui();
    }

    @EventHandler
    public void load(FMLPreInitializationEvent event) {
        ConfigHelper.loadConfig(new Configuration(event.getSuggestedConfigurationFile()));

        if (event.getSourceFile().getName().endsWith(".jar")) {
            proxy.tryUseMUD();
        }
    }
}
