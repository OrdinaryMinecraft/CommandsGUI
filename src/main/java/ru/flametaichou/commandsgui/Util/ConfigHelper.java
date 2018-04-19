package ru.flametaichou.commandsgui.Util;

import net.minecraftforge.common.config.Configuration;
import ru.flametaichou.commandsgui.ButtonGui;

public class ConfigHelper {

    public ConfigHelper() {
    }

    public static int buttonsCount = 15;
    private static String[] buttonCommands = {
            "/time set",
            "/time set night",
            "/time set night",
            "/time set night",
            "/time set night",
            "/time set night",
            "/time set night",
            "/time set night",
            "/time set night",
            "/time set night",
            "/time set night",
            "/time set night",
            "/time set night",
            "/time set night",
            "/time set night"
    };
    private static String[] buttonNames = {
            "Time",
            "Night",
            "/time set night",
            "/time set night",
            "/time set night",
            "/time set night",
            "/time set night",
            "/time set night",
            "/time set night",
            "/time set night",
            "/time set night",
            "/time set night",
            "/time set night",
            "/time set night",
            "/time set night"
    };

    public static ButtonGui[] buttons;

    private static void setupConfig(Configuration config) {
        try {
            config.load();
            config.addCustomCategoryComment("Enabled", "Buttons enabled");
            config.addCustomCategoryComment("Commands", "Button commands");
            config.addCustomCategoryComment("Command Names", "Button command names");
            config.addCustomCategoryComment("Costs", "Button use costs");

            buttons = new ButtonGui[buttonsCount];

            for (int i = 0; i < buttonsCount; i++) {
                buttons[i] = new ButtonGui();
                buttons[i].setEnabled(config.getBoolean("Button"+i+"Enabled", "Enabled", true, "Enable button "+i));
                buttons[i].setCommand(config.getString("Button"+i+"Command", "Commands", buttonCommands[i], "Command for button "+i));
                buttons[i].setCommandName(config.getString("Button"+i+"Name", "Command Names", buttonNames[i], "Button name for button "+i));
                buttons[i].setCost(config.getInt("Button"+i+"Cost", "Costs", 0, 0, 9999,"Command cost for button "+i));
            }
        } catch(Exception e) {
            System.out.println("A severe error has occured when attempting to load the config file for this mod!");
        } finally {
            if(config.hasChanged()) {
                config.save();
            }
        }
    }

    public static void loadConfig(Configuration config) {
        setupConfig(config);
    }
}
