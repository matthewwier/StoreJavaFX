package dataClass;

import javafx.beans.property.SimpleStringProperty;

public class Item {

    private final SimpleStringProperty name;
    private final SimpleStringProperty amount;
    private final SimpleStringProperty description;

    public Item(String name, String amount, String description) {
        this.name = new SimpleStringProperty(name);
        this.amount = new SimpleStringProperty(amount);
        this.description = new SimpleStringProperty(description);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getAmount() {
        return amount.get();
    }

    public SimpleStringProperty amountProperty() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount.set(amount);
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }
}
