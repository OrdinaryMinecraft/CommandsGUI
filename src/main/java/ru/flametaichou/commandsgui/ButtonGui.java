package ru.flametaichou.commandsgui;

public class ButtonGui {

    public ButtonGui() {}

    public ButtonGui(Boolean enabled, String command, String commandName, Integer cost) {
        this.enabled = enabled;
        this.command = command;
        this.commandName = commandName;
        this.cost = cost;
    }

    private Boolean enabled;
    private String command;
    private String commandName;
    private Integer cost;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
