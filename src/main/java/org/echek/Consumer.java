package org.echek;

// Потребитель (нагрузка)
public class Consumer {

    private String name; // наименование
    private double power; // установленная мощность
    private double current; // расчетный ток
    private int voltage; // уровень напряжения
    private double cosF; // коэффициент мощности
    private double ratioDemand; // коэффициент спроса

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public double getCurrent() {
        return current;
    }

    public void setCurrent(double current) {
        this.current = current;
    }

    public int getVoltage() {
        return voltage;
    }

    public void setVoltage(int voltage) {
        this.voltage = voltage;
    }

    public double getCosF() {
        return cosF;
    }

    public void setCosF(double cosF) {
        this.cosF = cosF;
    }

    public double getRatioDemand() {
        return ratioDemand;
    }

    public void setRatioDemand(double ratioDemand) {
        this.ratioDemand = ratioDemand;
    }

    @Override
    public String toString() {
        return " " + name + " " + "мощность - " + power + " напряжение - " + voltage + " ток -" + String.format("%.3f",current) + "\n";
    }
}
