package data;

import classes.Item;
import classes.User;

import java.util.ArrayList;
import java.util.List;


/**
 * Class provides list of users.
 *
 */
public class Users {
    public static List<User> userList = new ArrayList<>();

    public static void fillArrayList() {
        Items.data.addAll(new Item("Stół", "12", "Brązowy"),
                new Item("Sofa", "15", "Żółta"),
                new Item("Okno", "20", "Duże"),
                new Item("Parapet", "11", "Kolorowy"),
                new Item("Krzesło", "4", "Czarne"));
    }


    public static void addUsers() {
        Users.userList.add(new User("", "", "danny@gmail.com"));
    }

    public static void addUser(String username, String password, String email) {
        Users.userList.add(new User(username, password, email));
    }
}
