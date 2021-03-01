package org.echek;

import java.util.ArrayList;

public class Calculator {

    // Вычисление расчетного тока
    public static double currentCalc(Attachment attachment) {
        double current;
        if (attachment.getConsumer().getVoltage() == 220) {
            current = attachment.getConsumer().getPower() / (0.22 * attachment.getConsumer().getCosF());
        } else {
            current = attachment.getConsumer().getPower() / (0.66 * attachment.getConsumer().getCosF());
        }
        return current;
    }

    // Вычисление установленной мощности щита
    public static double installedPower (ArrayList<Attachment> shield) {
        double sumPower = 0;
        for (Attachment attachment : shield) {
            sumPower += attachment.getConsumer().getPower();
        }
        return sumPower;
    }

    // Вычисление расчетной активной нагрузки щита
    public static double sumActivePowerCalc(ArrayList<Attachment> shield) {
        double sumActivePower = 0;
        for (Attachment attachment : shield) {
            sumActivePower += attachment.getConsumer().getPower() * attachment.getConsumer().getRatioDemand();
        }
        return sumActivePower;
    }

    // Вычисление коэффициента мощности щита
    public static double sumCosFiCalc(ArrayList<Attachment> shield) {
        double sumCosFi = 1;
        double sumFullPower = 0;
        for (Attachment attachment : shield) {
            sumFullPower += attachment.getConsumer().getPower() *
                            attachment.getConsumer().getRatioDemand() /
                            attachment.getConsumer().getCosF();
        }
        if (sumFullPower > 0) {
            sumCosFi = sumActivePowerCalc(shield) / sumFullPower;
        }
        return sumCosFi;
    }

    // Вычисление коэффициента спроса щита
    public static double ratioDemandCalc(ArrayList<Attachment> shield) {
        double ratioDemand = 1;
        double installedPower = 0;
        for (Attachment attachment : shield) {
            installedPower += attachment.getConsumer().getPower();
        }
        if (installedPower > 0) {
            ratioDemand = sumActivePowerCalc(shield) / installedPower;
        }
        return ratioDemand;
    }

    //Расчет сопротивления петли фаза-ноль
    static double loopResistance(Attachment attachment) {
        return (attachment.getCable1().getzLoop() * attachment.getCable1().getDistance() +
                attachment.getCable2().getzLoop() * attachment.getCable2().getDistance());
    }

    static double activeResistance(Attachment attachment) {
        return (attachment.getCable1().getrActiv() * attachment.getCable1().getDistance() +
                attachment.getCable2().getrActiv() * attachment.getCable2().getDistance());
    }

    static double reactiveResistance(Attachment attachment) {
        return (attachment.getCable1().getxReactive() * attachment.getCable1().getDistance() +
                attachment.getCable2().getxReactive() * attachment.getCable2().getDistance());
    }

    // Расчет тока однофазного короткого замыкания
    public static double circuitCurrentCalc(Attachment attachment) {
        return 220 * 1000 / loopResistance(attachment);
    }

    // Расчет потери напряжения
    public static double deltaUCalc(Attachment attachment) {
        double deltaU;
        if (attachment.getConsumer().getVoltage() == 220) {
            deltaU = attachment.getConsumer().getPower() * loopResistance(attachment) * 100 /
                    Math.pow(attachment.getConsumer().getVoltage(), 2);
        } else {
            deltaU = attachment.getConsumer().getPower() * 100 *
                    (activeResistance(attachment) + Math.tan(Math.acos(attachment.getConsumer().getCosF())) * reactiveResistance(attachment)) /
                    Math.pow(attachment.getConsumer().getVoltage(), 2);
        }
        return deltaU;
    }
}
