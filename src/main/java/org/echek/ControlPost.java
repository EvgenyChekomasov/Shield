package org.echek;

public class ControlPost {

    private String name; // марка
    private int numButtons; // количество кнопок

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumButtons() {
        return numButtons;
    }

    public void setNumButtons(int numButtons) {
        this.numButtons = numButtons;
    }

    @Override
    public String toString() {
        if (numButtons == 0) {
            return "";
        } else {
            return "Пост управления " + numButtons + "-кнопочный" + name + "\n";
        }
    }
}
