package org.echek;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.util.ArrayList;

public class XMLBuilder {
    private static DocumentBuilderFactory factory;
    private  static DocumentBuilder builder;

    //ublic static void main(String[] args) {
    //   buildingXML("D:\\aaaaa.xml");
    //

    public static void buildingXML(Attachment shieldAttachment, ArrayList<Attachment> shield, String address) {
         factory = DocumentBuilderFactory.newInstance();

         try {
             builder = factory.newDocumentBuilder();
             // создаем xml-файл
             Document document = builder.newDocument();
             // создаем корневой элемент
             Element rootElement = document.createElementNS("org.echek.outxml/attachments", "Attachment");
             // и добавляем его в документ
             document.appendChild(rootElement);

             // добавляем таблицу заголовков
             rootElement.appendChild(tableBuild(document, "headers"));
             // заносим итоговые данные по щиту
             rootElement.appendChild(getAttachment(document, shieldAttachment));
             for (Attachment attachment : shield) {
                 rootElement.appendChild(getAttachment(document, attachment));
             }
             // создаем объект для печати
             TransformerFactory transformerFactory = TransformerFactory.newInstance();
             Transformer transformer = transformerFactory.newTransformer();
             transformer.setOutputProperty(OutputKeys.INDENT, "yes");
             DOMSource source = new DOMSource(document);

             // печать в консоль и файл
             StreamResult console = new StreamResult(System.out);
             StreamResult file = new StreamResult(address);

             // запись данных
             transformer.transform(source, console);
             transformer.transform(source, file);

             System.out.println("Создание завершено");

         } catch (Exception e) {
             e.printStackTrace();
         }
    }

    // создание шапки таблицы
    private static Node tableBuild(Document document, String headers) {
        Element table = document.createElement(headers);

        table.appendChild(getElement(document, "power", "Мощность, кВт"));
        table.appendChild(getElement(document, "cosFi", "cos ф"));
        table.appendChild(getElement(document, "ratio_demand", "Kc"));
        table.appendChild(getElement(document, "current", "Расчетный ток"));
        table.appendChild(getElement(document, "breaker_mark", "Автоматический выключатель"));
        table.appendChild(getElement(document, "poles", "Число полюсов"));
        table.appendChild(getElement(document, "breaker_current", "Уставка расцепителя, А"));
        table.appendChild(getElement(document, "cable1_name", "Кабель"));
        table.appendChild(getElement(document, "cable1_mark", "Марка"));
        table.appendChild(getElement(document, "cable1_profile", "Сечение"));
        table.appendChild(getElement(document, "contactor", "Контактор или ШУ"));
        table.appendChild(getElement(document, "contactor_current", "Номинальный ток, А"));
        table.appendChild(getElement(document, "cable2_name", "Кабель"));
        table.appendChild(getElement(document, "cable2_mark", "Марка"));
        table.appendChild(getElement(document, "cable2_profile", "Сечение"));
        table.appendChild(getElement(document, "control_cable_name", "Контрольный кабель"));
        table.appendChild(getElement(document, "control_cable_mark", "Марка"));
        table.appendChild(getElement(document, "control_cable_profile", "Сечение"));
        table.appendChild(getElement(document, "control_post", "Пост управления"));
        table.appendChild(getElement(document, "consumer", "Потребитель"));

        return table;
    }

    // создание нового узла для присоединения XML-файла
    private static Node getAttachment(Document document, Attachment attachment) {
        Element attach = document.createElement("attachment");

        // установка атрибута (имя присоединения)
        attach.setAttribute("name", attachment.getName());

        // данные по потребителю
        attach.appendChild(getElement(document, "power", String.format("%.1f", attachment.getConsumer().getPower())));
        attach.appendChild(getElement(document, "cosFi", String.format("%.2f", attachment.getConsumer().getCosF())));
        attach.appendChild(getElement(document, "ratio_demand", String.format("%.2f",attachment.getConsumer().getRatioDemand())));
        attach.appendChild(getElement(document, "current", String.format("%.2f", attachment.getConsumer().getCurrent())));

        // данные по автоматическому выключателю
        attach.appendChild(getElement(document, "breaker_mark", attachment.getCircuitBreaker().getName()));
        attach.appendChild(getElement(document, "poles", attachment.getCircuitBreaker().getPole() + "P"));
        attach.appendChild(getElement(document, "breaker_current", attachment.getCircuitBreaker().getCurrent() + attachment.getCircuitBreaker().getCharacter()));
        //attach.appendChild(getElement(document,attach, "character", attachment.getCircuitBreaker().getCharacter()));

        // данные по первому кабелю
        if (attachment.getCable1().getDistance() == 0) {
            attach.appendChild(getElement(document, "cable1_name", ""));
            attach.appendChild(getElement(document, "cable1_mark", ""));
            attach.appendChild(getElement(document, "cable1_profile", ""));
        } else {
            attach.appendChild(getElement(document, "cable1_name", attachment.getCable1().getName()));
            attach.appendChild(getElement(document, "cable1_mark", attachment.getCable1().getMark()));
            attach.appendChild(getElement(document, "cable1_profile", attachment.getCable1().getLines() + "x" + attachment.getCable1().getProfile()));
        }

        // данные по контактору (ШУ)
        if (attachment.getContactor().getName().equals("марка") || attachment.getContactor().getName().equals("")) {
            attach.appendChild(getElement(document, "contactor", ""));
            attach.appendChild(getElement(document, "contactor_current", ""));
        } else {
            attach.appendChild(getElement(document, "contactor", attachment.getContactor().getName()));
            attach.appendChild(getElement(document, "contactor_current", Integer.toString(attachment.getContactor().getCurrent())));
        }

        // данные по второму кабелю
        if (attachment.getCable2().getDistance() == 0) {
            attach.appendChild(getElement(document, "cable2_name", ""));
            attach.appendChild(getElement(document, "cable2_mark", ""));
            attach.appendChild(getElement(document, "cable2_profile", ""));
        } else {
            attach.appendChild(getElement(document, "cable2_name", attachment.getCable2().getName()));
            attach.appendChild(getElement(document, "cable2_mark", attachment.getCable2().getMark()));
            attach.appendChild(getElement(document, "cable2_profile", attachment.getCable2().getLines() + "x" + attachment.getCable2().getProfile()));
        }

        // данные по контрольному кабелю
        if (attachment.getCableControl().getDistance() == 0) {
            attach.appendChild(getElement(document, "control_cable_name", ""));
            attach.appendChild(getElement(document, "control_cable_mark", ""));
            attach.appendChild(getElement(document, "control_cable_profile", ""));

        } else {
            attach.appendChild(getElement(document, "control_cable_name", attachment.getCableControl().getName()));
            attach.appendChild(getElement(document, "control_cable_mark", attachment.getCableControl().getMark()));
            attach.appendChild(getElement(document, "control_cable_profile", attachment.getCableControl().getLines() + "x" + attachment.getCableControl().getProfile()));
        }

        // данные по посту управления
        if (attachment.getControlPost().getNumButtons() == 0) {
            attach.appendChild(getElement(document, "control_post", ""));
        } else {
            attach.appendChild(getElement(document, "control_post", attachment.getControlPost().getName()));
        }

        // наименование потребителя
        attach.appendChild(getElement(document, "consumer", attachment.getConsumer().getName()));

        return attach;
    }

    //утилитный метод для создания узла
    private static Node getElement(Document document, String parameter, String value) {
        Element node = document.createElement(parameter);
        node.setTextContent(value);
        return node;
    }
}
