package classes;

import javafx.beans.property.SimpleStringProperty;

public class Employee {
    private final SimpleStringProperty firstname;
    private final SimpleStringProperty lastname;
    private final SimpleStringProperty age;

    public Employee(String firstname, String lastname, String age) {
        this.firstname = new SimpleStringProperty(firstname);
        this.lastname = new SimpleStringProperty(lastname);
        this.age = new SimpleStringProperty(age);
    }

    public String getFirstname() {
        return firstname.get();
    }

    public SimpleStringProperty firstnameProperty() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname.set(firstname);
    }

    public String getLastname() {
        return lastname.get();
    }

    public SimpleStringProperty lastnameProperty() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname.set(lastname);
    }

    public String getAge() {
        return age.get();
    }

    public SimpleStringProperty ageProperty() {
        return age;
    }

    public void setAge(String age) {
        this.age.set(age);
    }
}
