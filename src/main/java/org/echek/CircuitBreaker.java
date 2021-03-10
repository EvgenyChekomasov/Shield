package org.echek;

import java.util.ArrayList;
import java.util.Arrays;

// Автоматический выключатель
public class CircuitBreaker {

    private String name; // наименование
    private int pole; // число полюсов
    private int current; // номинальный ток
    private String character; // характеристика расцепителя
    private boolean differential = false; // расцепитель дифференциального тока

    private final int[] rowCurrent = {6, 10, 16, 20, 25, 32, 40, 50, 63, 80, 100, 125, 160, 250, 320, 400, 500, 630};

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPole() {
        return pole;
    }

    public void setPole(int pole) {
        this.pole = pole;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public boolean isDifferential() {
        return differential;
    }

    public void setDifferential(boolean differential) {
        this.differential = differential;
    }

    int currentChoice(Attachment attachment) {
        int nominal = rowCurrent[0];
        int i = 0;
        while (attachment.getConsumer().getCurrent() * 1.15 > (double) rowCurrent[i]) {
            i++;
            nominal = rowCurrent[i];
        }
        return nominal;
    }

    int inputCurrentChoice (ArrayList<Attachment> shield, Attachment shieldAttachment) {
        int nominal = rowCurrent[0];
        int i = 0;
        while (shieldAttachment.getConsumer().getCurrent() * 1.15 > (double) rowCurrent[i]) {
            i++;
            nominal = rowCurrent[i];
        }
        for (Attachment attachment : shield) {
            if (nominal <= attachment.getCircuitBreaker().getCurrent()) {
                i = Arrays.binarySearch(rowCurrent, attachment.getCircuitBreaker().getCurrent());
                nominal = rowCurrent[i + 1];
            }
        }
        return nominal;
    }

    String characterChoice(Attachment attachment) {
        String str = "";
        if (differential) {
            str = " А /30 мА";
        } else if (Calculator.circuitCurrentCalc(attachment) / current < 5) {
            str = "B";
        } else {
            str = "C";
        }
        return str;
    }

    int poleChoice(Attachment attachment) {
        pole = 1;
        if (attachment.getConsumer().getVoltage() == 380) {
            pole = 3;
        }
        if (differential) {
            pole++;
        }
        return pole;
    }

    @Override
    public String toString() {
        return "Выключатель: " + name + " " + pole + "P" + " " + current + character + "\n";
    }
}
