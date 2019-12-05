package classes;

public class Owner {
    private final String firstname;
    private final String lastname;
    private final String occupation;
    private final String age;


    public Owner(String firstname, String lastname, String occupation, String age) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.occupation = occupation;
        this.age = age;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getAge() {
        return age;
    }
}
