package decorators;

import data.Employees;
import data.Items;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.xml.sax.SAXException;
import context.ApplicationContext;
import scenes.DetailsSceneCreator;
import scenes.StoreSceneCreator;
import xmlworker.XMLWorker;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class UserFormDecorator implements FormDecorator {

    @Override
    public void addControls(GridPane gridPane, Stage stage) {
        Label infoLabel = new Label("List of elements of your XML file:");
        infoLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 20));
        gridPane.add(infoLabel, 1, 0, 2, 1);
        GridPane.setHalignment(infoLabel, HPos.CENTER);
        GridPane.setMargin(infoLabel, new Insets(20, 0, 20, 0));

        FileChooser fileChooser = new FileChooser();

        Button openButton = new Button("Select XML file...");
        TextArea textAreaXML = ApplicationContext.getInstance().textArea();
        textAreaXML.setWrapText(true);

        if (XMLWorker.getInstance().getXmlFile() != null) {
            XMLWorker workers = XMLWorker.getInstance();
            try {
                textAreaXML.setText(workers.readXMLFile());
            } catch (IOException | SAXException | ParserConfigurationException e) {
                e.printStackTrace();
            }
        }

        openButton.setOnAction(
                e -> {
                    try {
                        XMLWorker worker = XMLWorker.getInstance();
                        worker.setDocumentBuilder();
                        XMLWorker.getInstance().setXmlFile(fileChooser.showOpenDialog(stage));
                        //worker.setXmlFile(xmlFile);
                        textAreaXML.setText(worker.readXMLFile());
                        if (Employees.employees.size() == 0) {

                            worker.addItemsDataFromXMLFile(Items.data);
                            worker.addEmployeesDataFromXMLFile(Employees.employees);
                            ApplicationContext.getInstance().setOwner(worker.addOwnerFromXMLFile());
                        }

                    } catch (IOException | ParserConfigurationException | SAXException | TransformerException ex) {
                        ex.printStackTrace();
                    }


                });
        openButton.setMinWidth(150);
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        Button closeAppButton = new Button("Close app");
        closeAppButton.setMinWidth(150);
        closeAppButton.setOnAction(e -> {
                    Stage stage1 = (Stage) closeAppButton.getScene().getWindow();
                    stage1.close();
                }
        );

        Button detailsButton = new Button("See store details");
        detailsButton.setMinWidth(150);
        detailsButton.setOnAction(e -> {
                    stage.setScene(new DetailsSceneCreator().createScene());
                }
        );

        vbox.getChildren().addAll(openButton, detailsButton, closeAppButton);

        gridPane.add(vbox, 0, 2);
        gridPane.add(textAreaXML, 1, 2);
        Button toStore = new Button();
        toStore.setText("To Store");
        toStore.setOnAction(e -> {
            stage.setScene(new StoreSceneCreator().createScene());
        });


        gridPane.add(toStore, 3, 2);
    }


}
