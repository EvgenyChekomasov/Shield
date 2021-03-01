package org.echek;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Graphics {

    private final Font font = new Font("Arial", Font.PLAIN, 14);;
    private final Border border = BorderFactory.createLineBorder(Color.BLACK);
    private int numberOfAttachment = 0;
    private JFrame frame;
    private JPanel mainPanel;
    private JPanel dataPanel;
    private JPanel tablePanel;// заголовки таблицы
    private JPanel inputPanel; // панель ввода
    private JPanel attachmentPanel; // панель присоединения
    private JTextArea attachmentName;
    private JTextField attachmentLabel;
    private JTextField powerField;
    private JComboBox <String> volt;
    private JTextField cosField;
    private JTextField demandField;
    private JTextField circuitBreakerField;
    private JTextArea shieldNameField;
    private JTextField shieldLabel;
    private JTextField inputBreakerMark;
    private JTextField markInputCable;
    private JTextField distanceInputCable;
    private JTextField cable1Mark;
    private JTextField cable1Distance;
    private JTextField cable2Mark;
    private JTextField cable2Distance;
    private JTextField controlPostMark;
    private JTextField numOfPostButtons;
    private JTextField controlCableMark;
    private JTextField controlCableDistance;
    private JTextField contactorField;
    private JTextArea outputTextField;
    private JPanel bottomButtonsPanel;
    private JScrollPane output;
    private JLabel emptyLabel = new JLabel(" ");

    private String[] voltage = {"", "220", "380"};

    private ArrayList <Attachment> shield = new ArrayList<>(); // Массив присоединений - расчетный щит
    CircuitBreaker inputCircuitBreaker = new CircuitBreaker(); // Вводной автомат щита
    Cable inputCable = new Cable(); // Вводной кабель
    String shieldName; // Наименование щита
    String shieldLabelName; // Обозначение щита

    private Attachment shieldAttachment = new Attachment(inputCircuitBreaker, inputCable, new Contactor(), new Cable(), new Cable(), new Consumer(), new ControlPost());


    void go() {

        // формирование основного окна
        frame = new JFrame("Данные для построения схемы");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        makeHeadersTable();
        makeInput();
        addAttachment();
        makeBottom();

        frame.pack();
        frame.setVisible(true);
    }

    // Формирование основного окна и таблицы заголовков
    private void makeHeadersTable() {
        mainPanel = new JPanel();
        frame.getContentPane().add(BorderLayout.NORTH, mainPanel);
        dataPanel = new JPanel();
        mainPanel.add(dataPanel);
        tablePanel = new JPanel();
        inputPanel = new JPanel();
        dataPanel.add(tablePanel);
        dataPanel.add(inputPanel);

        tablePanel.setLayout(new GridLayout(13,1,5,3));
        tablePanel.setBackground(Color.lightGray);
        JTextArea fullName = new JTextArea(3,8);
        fullName.setText("Наименование" + "\n" + "потребителя"); //#1
        fullName.setEditable(false);
        fullName.setBackground(Color.lightGray);
        tablePanel.add(addComponent(fullName));
        JLabel shortName = new JLabel("Обозначение"); // #2
        tablePanel.add(addComponent(shortName));
        JLabel powerLabel = new JLabel("Мощность, кВт"); //#3
        tablePanel.add(addComponent(powerLabel));
        JLabel voltageLabel = new JLabel("Напряжение, В "); //#4
        tablePanel.add(addComponent(voltageLabel));
        JLabel cosLabel = new JLabel("cos fi"); //#5
        tablePanel.add(addComponent(cosLabel));
        JLabel demandLabel = new JLabel("Коэф. спроса"); //#6
        tablePanel.add(addComponent(demandLabel));
        JTextArea circuitBreakerField = new JTextArea(3,8); //#7
        circuitBreakerField.setText("Автоматический" + "\n" + "выключатель" + "\n" + "(марка)");
        circuitBreakerField.setEditable(false);
        circuitBreakerField.setBackground(Color.lightGray);
        tablePanel.add(addComponent(circuitBreakerField));
        JTextArea cable1Mark = new JTextArea(3,8); //#8
        cable1Mark.setText("Марка м длина, м," + "\n" + "кабеля" + "\n" +  "ШР - ШУ");
        cable1Mark.setEditable(false);
        cable1Mark.setBackground(Color.lightGray);
        tablePanel.add(addComponent(cable1Mark));
        JTextArea contactorField = new JTextArea(3,8); //#9
        contactorField.setText("Контактор" + "\n" + "или ШУ");
        contactorField.setEditable(false);
        contactorField.setBackground(Color.lightGray);
        tablePanel.add(addComponent(contactorField));
        JTextArea cable2Mark = new JTextArea(3,8); //#10
        cable2Mark.setText("Марка и длина, м," + "\n" + "кабеля" + "\n" +  "ШУ - нагрузка");
        cable2Mark.setEditable(false);
        cable2Mark.setBackground(Color.lightGray);
        tablePanel.add(addComponent(cable2Mark));
        JTextArea controlPostLabel = new JTextArea("Пост управления" + "\n" + "марка" + "\n" + "Число кнопок");
        controlPostLabel.setEditable(false);
        controlPostLabel.setBackground(Color.lightGray);
        tablePanel.add(addComponent(controlPostLabel));
        JTextArea cableContrMark = new JTextArea(3,8); //#11
        cableContrMark.setText("Марка и длина, м," + "\n" + "контрольного" + "\n" +  "кабеля");
        cableContrMark.setEditable(false);
        cableContrMark.setBackground(Color.lightGray);
        tablePanel.add(addComponent(cableContrMark));
        tablePanel.add(emptyLabel); //#13
    }

    // Формирование панели ввода
    private void makeInput() {
        inputPanel.setLayout(new GridLayout(13,1,5,3));
        inputPanel.setBackground(Color.YELLOW);
        shieldNameField = new JTextArea(3,10); //#1
        shieldNameField.setText("Наименование" + "\n" + "щита");
        shieldNameField.setLineWrap(true);
        shieldNameField.setWrapStyleWord(true);
        inputPanel.add(addComponent(shieldNameField));
        shieldLabel = new JTextField("Обозначение"); //#2
        inputPanel.add(addComponent(shieldLabel));
        inputPanel.add(emptyLabel); //#3
        JLabel emptyLabel2 = new JLabel();
        inputPanel.add(emptyLabel2); //#4
        JLabel emptyLabel3 = new JLabel();
        inputPanel.add(emptyLabel3);//#5
        JLabel emptyLabel4 = new JLabel();
        inputPanel.add(emptyLabel4); //#6
        JPanel inputBreaker = new JPanel(); //#7
        inputBreaker.setLayout(new BoxLayout(inputBreaker, BoxLayout.Y_AXIS));
        inputBreakerMark = new JTextField("марка");
        inputBreakerMark.setFont(font);
        inputBreaker.add(inputBreakerMark);
        JCheckBox isDifferential = new JCheckBox("Дифференц.");
        isDifferential.setFont(font);
        inputBreaker.add(isDifferential);
        isDifferential.addItemListener(e ->{
            inputCircuitBreaker.setDifferential(true);
        });
        inputPanel.add(addComponent(inputBreaker));
        JPanel inputCablePanel = new JPanel();
        inputCablePanel.setLayout(new BoxLayout(inputCablePanel,BoxLayout.Y_AXIS));
        markInputCable = new JTextField("марка");
        markInputCable.setFont(font);
        inputCablePanel.add(markInputCable);
        distanceInputCable = new JTextField("длина");
        distanceInputCable.setFont(font);
        inputCablePanel.add(distanceInputCable);
        inputPanel.add(inputCablePanel);
    }

    // добавление присоединения
    private void addAttachment() {

        shield.add(new Attachment(new CircuitBreaker(),new Cable(), new Contactor(), new Cable(), new Cable(), new Consumer(), new ControlPost()));

        attachmentPanel = new JPanel();
        attachmentPanel.setLayout(new GridLayout(13,1,5,3));
        attachmentName = new JTextArea(3,10); //#1
        attachmentName.setText("Наименование" + "\n" + "потребителя");
        attachmentName.setLineWrap(true);
        attachmentName.setWrapStyleWord(true);
        attachmentPanel.add(addComponent(attachmentName));
        attachmentLabel = new JTextField("Обозначение"); //#2
        attachmentPanel.add(addComponent(attachmentLabel));
        powerField = new JTextField("Мощность"); //#3
        attachmentPanel.add(addComponent(powerField));
        volt = new JComboBox<>(voltage);
        volt.addActionListener(new ChoiceVoltage());
        attachmentPanel.add(addComponent(volt));
        cosField = new JTextField("1.0");
        attachmentPanel.add(addComponent(cosField));
        demandField = new JTextField("1.0");
        attachmentPanel.add(addComponent(demandField));
        JPanel circuitBreakerPanel = new JPanel();
        circuitBreakerPanel.setLayout(new BoxLayout(circuitBreakerPanel, BoxLayout.Y_AXIS));
        circuitBreakerField = new JTextField("марка");
        circuitBreakerField.setFont(font);
        circuitBreakerPanel.add(circuitBreakerField);
        JCheckBox isDifferential = new JCheckBox("Дифференц.");
        isDifferential.setFont(font);
        isDifferential.addItemListener(e ->{
            shield.get(numberOfAttachment).getCircuitBreaker().setDifferential(true);
        });
        circuitBreakerPanel.add(isDifferential);
        attachmentPanel.add(addComponent(circuitBreakerPanel));
        JPanel cable1Panel = new JPanel();
        cable1Panel.setLayout(new BoxLayout(cable1Panel, BoxLayout.Y_AXIS));
        cable1Mark = new JTextField("марка");
        cable1Mark.setFont(font);
        cable1Panel.add(cable1Mark);
        cable1Distance = new JTextField("0");
        cable1Distance.setFont(font);
        cable1Panel.add(cable1Distance);
        attachmentPanel.add(addComponent(cable1Panel));
        JPanel contactorPanel = new JPanel();
        contactorPanel.setLayout(new BoxLayout(contactorPanel, BoxLayout.Y_AXIS));
        contactorField = new JTextField("марка");
        contactorField.setFont(new Font("Arial", Font.PLAIN, 11));
        contactorPanel.add(contactorField);
        JPanel checkPanel = new JPanel();
        JCheckBox reverseBox = new JCheckBox("Реверс");
        JCheckBox driveShieldBox = new JCheckBox("ШУ");
        reverseBox.setFont(new Font("Arial", Font.PLAIN, 11));
        reverseBox.addItemListener(e -> {
            driveShieldBox.setSelected(false);
            shield.get(numberOfAttachment).getContactor().setRevers(true);
            shield.get(numberOfAttachment).getContactor().setShield(false);

        });
        driveShieldBox.setFont(new Font("Arial", Font.PLAIN, 11));
        driveShieldBox.addItemListener(e -> {
            reverseBox.setSelected(false);
            shield.get(numberOfAttachment).getContactor().setShield(true);
            shield.get(numberOfAttachment).getContactor().setRevers(false);
        });
        checkPanel.add(reverseBox);
        checkPanel.add(driveShieldBox);
        contactorPanel.add(checkPanel);
        attachmentPanel.add(addComponent(contactorPanel));
        JPanel cable2Panel = new JPanel();
        cable2Panel.setLayout(new BoxLayout(cable2Panel, BoxLayout.Y_AXIS));
        cable2Mark = new JTextField("марка");
        cable2Mark.setFont(font);
        cable2Panel.add(cable2Mark);
        cable2Distance = new JTextField("0");
        cable2Distance.setFont(font);
        cable2Panel.add(cable2Distance);
        attachmentPanel.add(addComponent(cable2Panel));
        JPanel controlPostPanel = new JPanel();
        controlPostPanel.setLayout(new BoxLayout(controlPostPanel, BoxLayout.Y_AXIS));
        controlPostMark = new JTextField("марка");
        controlPostMark.setFont(font);
        controlPostPanel.add(controlPostMark);
        numOfPostButtons = new JTextField("0");
        numOfPostButtons.setFont(font);
        controlPostPanel.add(numOfPostButtons);
        attachmentPanel.add(addComponent(controlPostPanel));
        JPanel controlCablePanel = new JPanel();
        controlCablePanel.setLayout(new BoxLayout(controlCablePanel, BoxLayout.Y_AXIS));
        controlCableMark = new JTextField("марка");
        controlCableMark.setFont(font);
        controlCablePanel.add(controlCableMark);
        controlCableDistance = new JTextField("0");
        controlCableDistance.setFont(font);
        controlCablePanel.add(controlCableDistance);
        attachmentPanel.add(addComponent(controlCablePanel));
        JButton addAttachmentButton = new JButton("Следующий фидер");
        addAttachmentButton.setFont(font);
        addAttachmentButton.addActionListener(new newAttachment());
        attachmentPanel.add(addAttachmentButton);
        dataPanel.add(attachmentPanel);
    }

    private void makeBottom() {
        outputTextField = new JTextArea("Исходные данные вводить поочередно для каждого присоединения");
        outputTextField.setRows(10);
        outputTextField.setFont(font);
        output = new JScrollPane(outputTextField);
        output.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        frame.getContentPane().add(BorderLayout.CENTER, output);

        bottomButtonsPanel = new JPanel();
        bottomButtonsPanel.setLayout(new GridLayout(2,1));
        frame.getContentPane().add(BorderLayout.SOUTH, bottomButtonsPanel);
        JButton calculateButton = new JButton("Произвести расчет");
        calculateButton.setFont(font);
        calculateButton.addActionListener(new Calculate());
        bottomButtonsPanel.add(calculateButton);
        JButton clearAllButton = new JButton("Очистить все");
        clearAllButton.setFont(font);
        clearAllButton.addActionListener(new ClearAll());
        bottomButtonsPanel.add(clearAllButton);
    }

    // форматирование компонентов
    private JComponent addComponent(JComponent jComponent) {
        jComponent.setFont(font);
        jComponent.setBorder(border);
        return jComponent;
    }

    public void inputData() {
        //Потребитель
        shield.get(numberOfAttachment).getConsumer().setName(attachmentName.getText());
        shield.get(numberOfAttachment).setName(attachmentLabel.getText());
        shield.get(numberOfAttachment).getConsumer().setPower(Double.parseDouble(powerField.getText().replaceAll(",", ".")));
        shield.get(numberOfAttachment).getConsumer().setCosF(Double.parseDouble(cosField.getText().replaceAll(",", ".")));
        shield.get(numberOfAttachment).getConsumer().setRatioDemand(Double.parseDouble(demandField.getText().replaceAll(",", ".")));
        shield.get(numberOfAttachment).getConsumer().setCurrent(Calculator.currentCalc(shield.get(numberOfAttachment)));
        //Автомат
        shield.get(numberOfAttachment).getCircuitBreaker().setName(circuitBreakerField.getText());
        shield.get(numberOfAttachment).getCircuitBreaker().setCurrent(
                shield.get(numberOfAttachment).getCircuitBreaker().currentChoice(shield.get(numberOfAttachment)));
        shield.get(numberOfAttachment).getCircuitBreaker().setCharacter(
                shield.get(numberOfAttachment).getCircuitBreaker().characterChoice(shield.get(numberOfAttachment)));
        shield.get(numberOfAttachment).getCircuitBreaker().poleChoice(shield.get(numberOfAttachment));
        // Кабель-1
        shield.get(numberOfAttachment).getCable1().setName(shield.get(numberOfAttachment).getName() + "-н1");
        shield.get(numberOfAttachment).getCable1().setMark(cable1Mark.getText());
        shield.get(numberOfAttachment).getCable1().setDistance(Float.parseFloat(cable1Distance.getText().replaceAll(",", ".")));
        shield.get(numberOfAttachment).getCable1().setLines(
                shield.get(numberOfAttachment).getCable1().lineChoice(shield.get(numberOfAttachment)));
        shield.get(numberOfAttachment).getCable1().setProfile(
                shield.get(numberOfAttachment).getCable1().profileChoice(shield.get(numberOfAttachment), shield.get(numberOfAttachment).getCable1()));
        // Контактор или шкаф управления
        shield.get(numberOfAttachment).getContactor().setName(contactorField.getText());
        shield.get(numberOfAttachment).getContactor().setCurrent(
                shield.get(numberOfAttachment).getCircuitBreaker().getCurrent());
        // Кабель-2
        shield.get(numberOfAttachment).getCable2().setName(shield.get(numberOfAttachment).getName() + "-н2");
        shield.get(numberOfAttachment).getCable2().setMark(cable2Mark.getText());
        shield.get(numberOfAttachment).getCable2().setDistance(Float.parseFloat(cable2Distance.getText().replaceAll(",", ".")));
        shield.get(numberOfAttachment).getCable2().setLines(
                shield.get(numberOfAttachment).getCable2().lineChoice(shield.get(numberOfAttachment)));
        shield.get(numberOfAttachment).getCable2().setProfile(
                shield.get(numberOfAttachment).getCable2().profileChoice(shield.get(numberOfAttachment), shield.get(numberOfAttachment).getCable2()));
        // Пост управления
        shield.get(numberOfAttachment).getControlPost().setName(controlPostMark.getText());
        shield.get(numberOfAttachment).getControlPost().setNumButtons(Integer.parseInt(numOfPostButtons.getText()));
        // Контрольный кабель
        shield.get(numberOfAttachment).getCableControl().setName(shield.get(numberOfAttachment).getName() + "-к1");
        shield.get(numberOfAttachment).getCableControl().setMark(controlCableMark.getText());
        shield.get(numberOfAttachment).getCableControl().setDistance(Float.parseFloat(controlCableDistance.getText().replaceAll(",", ".")));
        shield.get(numberOfAttachment).getCableControl().setLines(
                shield.get(numberOfAttachment).getCableControl().controlLineChoice(shield.get(numberOfAttachment)));
        shield.get(numberOfAttachment).getCableControl().setProfile("1.5");

        if (numberOfAttachment == 0) {
            outputTextField.setText("");
        }
        outputTextField.append("\n " + shield.get(numberOfAttachment).getName() +
                shield.get(numberOfAttachment).getConsumer().toString() +
                shield.get(numberOfAttachment).getCircuitBreaker().toString() +
                shield.get(numberOfAttachment).getCable1().toString() +
                shield.get(numberOfAttachment).getContactor().toString() +
                shield.get(numberOfAttachment).getCable2().toString() +
                shield.get(numberOfAttachment).getControlPost().toString() +
                shield.get(numberOfAttachment).getCableControl().toString() +
                shield.get(numberOfAttachment).checkParameters() + "\n");

        attachmentName.setEditable(false);
        attachmentLabel.setEditable(false);
        powerField.setEditable(false);
        volt.setEnabled(false);
        cosField.setEditable(false);
        demandField.setEditable(false);
        circuitBreakerField.setEditable(false);
        contactorField.setEditable(false);
        numberOfAttachment++;
    }

    class newAttachment implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            inputData();
            addAttachment();
            frame.repaint();
            frame.pack();
        }
    }

    class ClearAll implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(mainPanel);
           // frame.remove(outputTextField);
            frame.remove(output);
            frame.remove(bottomButtonsPanel);
            makeHeadersTable();
            makeInput();
            addAttachment();
            makeBottom();
            frame.validate();
            frame.repaint();
            frame.pack();
        }
    }

    class ChoiceVoltage implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox box = (JComboBox)e.getSource();
            String item = (String)box.getSelectedItem();
            shield.get(numberOfAttachment).getConsumer().setVoltage(Integer.parseInt(item));
        }
    }

    class Calculate implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            // Последнее присоединение
            inputData();
            numberOfAttachment--;

            // Исходные данные по щиту
            shieldName = shieldNameField.getText();
            shieldLabelName = shieldLabel.getText();
            inputCircuitBreaker.setName(inputBreakerMark.getText());
            inputCable.setName(shieldLabelName + "-н1");
            inputCable.setMark(markInputCable.getText());
            inputCable.setDistance(Float.parseFloat(distanceInputCable.getText().replaceAll(",", ".")));

            // Расчеты
            shieldAttachment.getConsumer().setVoltage(380);
            shieldAttachment.getConsumer().setPower(Calculator.sumActivePowerCalc(shield));
            shieldAttachment.getConsumer().setCosF(Calculator.sumCosFiCalc(shield));
            shieldAttachment.getConsumer().setRatioDemand(Calculator.ratioDemandCalc(shield));
            shieldAttachment.getConsumer().setCurrent(Calculator.currentCalc(shieldAttachment));

            inputCircuitBreaker.setCurrent(inputCircuitBreaker.currentChoice(shieldAttachment));
            inputCircuitBreaker.setPole(inputCircuitBreaker.poleChoice(shieldAttachment));
            inputCircuitBreaker.setCharacter(inputCircuitBreaker.characterChoice(shieldAttachment));

            inputCable.setLines(inputCable.lineChoice(shieldAttachment));
            inputCable.setProfile(inputCable.profileChoice(shieldAttachment, inputCable));

            // Вывод результатов по щиту
            outputTextField.append("\n" + "Итого по щиту:" + "\n" +
                    "Наименование щита - " + shieldName + "\n" +
                    "Обозначение - " + shieldLabelName + "\n" +
                    "Установленная мощность - " + String.format("%.2f", Calculator.installedPower(shield)) + " кВт" + "\n" +
                    "Расчетная мощность - " + String.format("%.2f", shieldAttachment.getConsumer().getPower()) + " кВт" + "\n" +
                    "Расчетный коэффициент мощности - " + String.format("%.2f", shieldAttachment.getConsumer().getCosF()) + "\n" +
                    "Коэффициент спроса щита - " + String.format("%.2f", shieldAttachment.getConsumer().getRatioDemand()) + "\n" +
                    "Расчетный ток - " + String.format("%.3f", shieldAttachment.getConsumer().getCurrent()) + " A" + "\n" +
                    inputCircuitBreaker.toString() +
                    inputCable.toString() + "\n" +
                    "Потеря напряжения до щита - " + String.format("%.3f", Calculator.deltaUCalc(shieldAttachment)) + "%");
        }
    }
}
