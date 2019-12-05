package xmloperations;

import classes.Employee;
import classes.Item;
import classes.Owner;
import javafx.collections.ObservableList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class XMLOperations {

    public static void addItemsDataFromXMLFile(File file, ObservableList<Item> items) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document document = dBuilder.parse(file);
        document.getDocumentElement().normalize();
        NodeList nodeList = document.getElementsByTagName("item");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element xmlElement = (Element) node;
                String name = xmlElement.getElementsByTagName("name").item(0).getTextContent();
                String amount = xmlElement.getElementsByTagName("amount").item(0).getTextContent();
                String desc = xmlElement.getElementsByTagName("description").item(0).getTextContent();

                items.add(new Item(name, amount, desc));
            }
        }
    }

    public static void addEmployeesDataFromXMLFile(File file, ObservableList<Employee> employees) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document document = dBuilder.parse(file);
        document.getDocumentElement().normalize();
        NodeList nodeList = document.getElementsByTagName("employee");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element xmlElement = (Element) node;
                String firstname = xmlElement.getElementsByTagName("firstname").item(0).getTextContent();
                String lastname = xmlElement.getElementsByTagName("lastname").item(0).getTextContent();
                String age = xmlElement.getElementsByTagName("age").item(0).getTextContent();

                employees.add(new Employee(firstname, lastname, age));
            }
        }
    }

    public static Owner addOwnerFromXMLFile(File file) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document document = dBuilder.parse(file);
        document.getDocumentElement().normalize();
        NodeList nodeList = document.getElementsByTagName("owner");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element xmlElement = (Element) node;
                String firstname = xmlElement.getElementsByTagName("firstname").item(0).getTextContent();
                String lastname = xmlElement.getElementsByTagName("lastname").item(0).getTextContent();
                String occupation = xmlElement.getElementsByTagName("occupation").item(0).getTextContent();
                String age = xmlElement.getAttribute("age");
                return new Owner(firstname, lastname, occupation, age);
            }
        }
        return null;
    }
}
