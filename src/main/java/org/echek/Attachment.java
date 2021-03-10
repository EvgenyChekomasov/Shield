package org.echek;

// Присоединение в щите
public class Attachment {

    private String name;
    private CircuitBreaker circuitBreaker;
    private Cable cable1; // кабель от щита до шкафа управления (при наличии)
    private Contactor contactor;
    private Cable cable2; // кабель от шкафа управления (при наличии) до нагрузки
    private Cable cableControl; // кабель до поста управления (при наличии)
    private Consumer consumer;
    private ControlPost controlPost;
    private double deltaU;
    private double circuitCurrent;

    public Attachment(CircuitBreaker circuitBreaker, Cable cable1, Contactor contactor, Cable cable2, Cable cableControl, Consumer consumer, ControlPost controlPost) {
        this.circuitBreaker = circuitBreaker;
        this.cable1 = cable1;
        this.contactor = contactor;
        this.cable2 = cable2;
        this.cableControl = cableControl;
        this.consumer = consumer;
        this.controlPost = controlPost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CircuitBreaker getCircuitBreaker() {
        return circuitBreaker;
    }

    public void setCircuitBreaker(CircuitBreaker circuitBreaker) {
        this.circuitBreaker = circuitBreaker;
    }

    public Cable getCable1() {
        return cable1;
    }

    public void setCable1(Cable cable1) {
        this.cable1 = cable1;
    }

    public Contactor getContactor() {
        return contactor;
    }

    public void setContactor(Contactor contactor) {
        this.contactor = contactor;
    }

    public Cable getCable2() {
        return cable2;
    }

    public void setCable2(Cable cable) {
        this.cable2 = cable;
    }

    public Cable getCableControl() {
        return cableControl;
    }

    public void setCableControl(Cable cableControl) {
        this.cableControl = cableControl;
    }

    public Consumer getConsumer() {
        return consumer;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

    public ControlPost getControlPost() {
        return controlPost;
    }

    public void setControlPost(ControlPost controlPost) {
        this.controlPost = controlPost;
    }

    public double getDeltaU() {
        return Calculator.deltaUCalc(this);
    }

    public double getCircuitCurrent() {
        return Calculator.circuitCurrentCalc(this);
    }

    public String checkParameters() {
        return "Потеря напряжения - " + String.format("%.3f", getDeltaU()) + "%," +
                "Однофазный ток КЗ - " + String.format("%.3f", getCircuitCurrent()) + " A";
    }
}
