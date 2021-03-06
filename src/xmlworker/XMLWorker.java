package xmlworker;


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
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;


/**
 * Singleton class for operations on XML file.
 *
 */
public class XMLWorker {

    /** Instance of XMLWorker class  */
    private static XMLWorker xmlWorker;

    /** XML file to make operations */
    private File xmlFile;
    /** Instance of document builder, help to process XML file */
    private DocumentBuilder dBuilder;

    private XMLWorker() {
    }

    /**
     * Return instance of XML worker.
     * @return xml worker
     */
    public static XMLWorker getInstance() {
        if (xmlWorker == null) {
            xmlWorker = new XMLWorker();

        }
        return xmlWorker;
    }

    /**
     * Get xml file
     * @return xml file
     */
    public File getXmlFile(){
        return xmlFile;
    }
    /**
     * Set document builder
     *
     */
    public void setDocumentBuilder() {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try {
            dBuilder = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Set xml file
     * @param  xmlFile xml file
     */
    public void setXmlFile(File xmlFile) {
        this.xmlFile = xmlFile;
    }

    /**
     * Add items from xml file
     * @param  items list of items
     */
    public void addItemsDataFromXMLFile(ObservableList<Item> items) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        Document document = dBuilder.parse(xmlFile);

        // append new child
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

    /**
     * Add employees from xml file
     * @param  employees list of employees
     */
    public void addEmployeesDataFromXMLFile(ObservableList<Employee> employees) throws ParserConfigurationException, IOException, SAXException {
        Document document = dBuilder.parse(xmlFile);
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

    /**
     * Add owner from xml file
     * @return owner of store
     */
    public Owner addOwnerFromXMLFile() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document document = dBuilder.parse(xmlFile);
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

    /**
     * Read xml file
     * @return xml file string representation
     */
    public String readXMLFile() throws IOException, SAXException, ParserConfigurationException {
        StringBuilder stringBuilder = new StringBuilder();

        Document document = dBuilder.parse(xmlFile);
        document.getDocumentElement().normalize();

        // owner
        NodeList nodeList = document.getElementsByTagName("owner");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element xmlElement = (Element) node;
                String firstname = xmlElement.getElementsByTagName("firstname").item(0).getTextContent();
                String lastname = xmlElement.getElementsByTagName("lastname").item(0).getTextContent();
                String occupation = xmlElement.getElementsByTagName("occupation").item(0).getTextContent();
                String age = xmlElement.getAttribute("age");
                stringBuilder.append("owner(firstname,lastname,occupation)").append("\n").append(firstname)
                        .append("\n").append(lastname).append("\n").append(age).append("\n");
            }
        }

        nodeList = document.getElementsByTagName("employee");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element xmlElement = (Element) node;
                String firstname = xmlElement.getElementsByTagName("firstname").item(0).getTextContent();
                String lastname = xmlElement.getElementsByTagName("lastname").item(0).getTextContent();
                String age = xmlElement.getElementsByTagName("age").item(0).getTextContent();

                stringBuilder.append("employee(firstname,lastname,age)").append("\n").append(firstname).append("\n").append(lastname).append("\n").append(age).append("\n");
            }
        }

        nodeList = document.getElementsByTagName("item");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element xmlElement = (Element) node;
                String name = xmlElement.getElementsByTagName("name").item(0).getTextContent();
                String amount = xmlElement.getElementsByTagName("amount").item(0).getTextContent();
                String desc = xmlElement.getElementsByTagName("description").item(0).getTextContent();
                stringBuilder.append("item(name,amount,description)").append("\n").append(name).append("\n").append(amount).append("\n").append(desc).append("\n");
            }
        }
        return stringBuilder.toString();
    }

    /**
     * Add item to xml file
     * @param  item item to add
     */
    public void addItemToXMLFile(Item item) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document document = dBuilder.parse(xmlFile);


        document = dBuilder.parse(xmlFile);
        document.getDocumentElement().normalize();
        // append new child
        Element newItem = document.createElement("item");

        Element names = document.createElement("name");
        names.appendChild(document.createTextNode(item.getName()));
        newItem.appendChild(names);

        Element amounts = document.createElement("amount");
        amounts.appendChild(document.createTextNode(item.getAmount()));
        newItem.appendChild(amounts);

        Element descriptions = document.createElement("description");
        descriptions.appendChild(document.createTextNode(item.getDescription()));
        newItem.appendChild(descriptions);

        document.getElementsByTagName("items").item(0).appendChild(newItem);
        DOMSource src = new DOMSource(document);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transform = transformerFactory.newTransformer();
        StreamResult res = new StreamResult(xmlFile.getAbsolutePath());
        transform.transform(src, res);
    }

    /**
     * Remove item from xml file
     * @param  item item to remove
     */
    public void removeItemFromXMLFile(Item item) throws ParserConfigurationException, IOException, SAXException, TransformerException {

        Document document = dBuilder.parse(xmlFile);
        document.getDocumentElement().normalize();
        // append new child
        NodeList nodeList = document.getElementsByTagName("item");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element xmlElement = (Element) node;
                String name = xmlElement.getElementsByTagName("name").item(0).getTextContent();
                String amount = xmlElement.getElementsByTagName("amount").item(0).getTextContent();
                String desc = xmlElement.getElementsByTagName("description").item(0).getTextContent();
                if (item.getName().equals(name) && item.getAmount().equals(amount) && item.getDescription().equals(desc)) {
                    document.getElementsByTagName("items").item(0).removeChild(xmlElement);
                }
            }
        }
        DOMSource src = new DOMSource(document);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transform = transformerFactory.newTransformer();
        StreamResult res = new StreamResult(xmlFile.getAbsolutePath());
        transform.transform(src, res);
    }

    /**
     * Add employee to xml file
     * @param  employee employee to add
     */
    public void addEmployeeToXMLFile(Employee employee) throws ParserConfigurationException, IOException, SAXException, TransformerException {

        Document document = dBuilder.parse(xmlFile);

        document.getDocumentElement().normalize();
        // append new child
        Element newItem = document.createElement("employee");

        Element names = document.createElement("firstname");
        names.appendChild(document.createTextNode(employee.getFirstname()));
        newItem.appendChild(names);

        Element amounts = document.createElement("lastname");
        amounts.appendChild(document.createTextNode(employee.getLastname()));
        newItem.appendChild(amounts);

        Element descriptions = document.createElement("age");
        descriptions.appendChild(document.createTextNode(employee.getAge()));
        newItem.appendChild(descriptions);

        document.getElementsByTagName("employees").item(0).appendChild(newItem);
        DOMSource src = new DOMSource(document);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transform = transformerFactory.newTransformer();
        StreamResult res = new StreamResult(xmlFile.getAbsolutePath());
        transform.transform(src, res);
    }
    /**
     * Remove employee from xml file
     * @param  employee employee to remove
     */
    public void removeEmployeeFromXMLFile(Employee employee) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        Document document = dBuilder.parse(xmlFile);


        document.getDocumentElement().normalize();

        NodeList nodeList = document.getElementsByTagName("employee");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element xmlElement = (Element) node;
                String firstname = xmlElement.getElementsByTagName("firstname").item(0).getTextContent();
                String lastname = xmlElement.getElementsByTagName("lastname").item(0).getTextContent();
                String age = xmlElement.getElementsByTagName("age").item(0).getTextContent();

                if (employee.getFirstname().equals(firstname) && employee.getLastname().equals(lastname) && employee.getAge().equals(age)) {
                    document.getElementsByTagName("employees").item(0).removeChild(xmlElement);
                }
            }
        }
        DOMSource src = new DOMSource(document);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transform = transformerFactory.newTransformer();
        StreamResult res = new StreamResult(xmlFile.getAbsolutePath());
        transform.transform(src, res);
    }


}
