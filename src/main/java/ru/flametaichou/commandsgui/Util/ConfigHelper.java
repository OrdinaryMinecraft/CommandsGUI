package ru.flametaichou.commandsgui.Util;

import net.minecraftforge.common.config.Configuration;
import ru.flametaichou.commandsgui.ButtonGui;

public class ConfigHelper {

    public ConfigHelper() {
    }

    public static int buttonsCount = 30;
    public static int pageCount = 15;
    private static String[] buttonCommands = {
            "/home",
            "/t spawn",
            "/money",
            "/spawn",
            "/t join",
            "/money pay",
            "/coins stack",
            "/t leave",
            "/plot claim",
            "/coins unstack",
            "/t add",
            "/plot forsale",
            "/help",
            "/t kick",
            "plot notforsale",

            "/t new",
            "/t toggle pvp",
            "/resident friend add",
            "/t claim",
            "/t list",
            "/resident friend remove",
            "/t withdraw",
            "/towny map",
            "/t delete",
            "/t deposit",
            "/vote day",
            "/",
            "/t outpost",
            "/vote sun",
            "/"
    };
    private static String[] buttonNames = {
            "Телепорт в дом",
            "Телепорт в город",
            "Мой баланс",
            "Телепорт на спавн",
            "Towny: вступить",
            "Заплатить игроку",
            "Совместить монетки",
            "Towny: покинуть",
            "Towny: купить участок",
            "Разменять монетки",
            "Towny: пригласить",
            "Towny: чанк на продажу",
            "Помощь",
            "Towny: выгнать",
            "Towny: чанк с продажи",

            "Towny: создать город",
            "Towny: вкючить PvP",
            "Добавить друга",
            "Towny: присоед. чанк",
            "Towny: список городов",
            "Удалить друга",
            "Towny: деньги в казну",
            "Towny: карта",
            "Towny: удалить город",
            "Towny: деньги из казны",
            "Голосование за день",
            "/",
            "Towny: создать аванпост",
            "Голос. за ясную погоду",
            "/"
    };
    private static Boolean[] buttonEnabled = {
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            false,
            true,
            true,
            false
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
                buttons[i].setEnabled(config.getBoolean("Button"+i+"Enabled", "Enabled", buttonEnabled[i], "Enable button "+i));
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
