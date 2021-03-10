package org.echek;

public class Contactor {

    private String name = "";
    private boolean isContactor = false;
    private boolean isShield = false;
    private boolean revers = false;
    private int current;

    private static final int[] rowCurrent = {10, 16, 25, 40, 63, 100, 160, 250, 400, 630};

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isContactor() {
        return isContactor;
    }

    public void setContactor(boolean contactor) {
        isContactor = contactor;
    }

    public boolean isShield() {
        return isShield;
    }

    public void setShield(boolean shield) {
        isShield = shield;
    }

    public boolean isRevers() {
        return revers;
    }

    public void setRevers(boolean revers) {
        this.revers = revers;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public static int currentChoice(double currentCircuitBreaker) {
        int nominal = rowCurrent[0];
        int i = 0;
        while (currentCircuitBreaker > (double) rowCurrent[i]) {
            nominal = rowCurrent[i + 1];
            i++;
        }
        return nominal;
    }

    @Override
    public String toString() {
        if (name.equals("марка") || name.equals("")) {
            return "";
        }else if (isShield) {
            return name + "\n";
        } else if (isRevers()){
            return "Контактор реверсивный " + name + current + " А" + "\n";
        } else {
            return "Контактор " + name + " " + current + " А" + "\n";
        }
    }
}
