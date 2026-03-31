package fr.miniaturesolid.domain.entity;

import java.util.ArrayList;
import java.util.List;

public class User implements Entity{
    private static int nbUsers = 0;
    private int id;
    private String login;
    private String email;
    private String password;
    private List<User> subscriptions;

    
    public User(String login, String email, String password) {
        this.id = ++nbUsers;
        this.login = login;
        this.email = email;
        this.password = password;
        this.subscriptions = new ArrayList<>();
    }

    @Override
    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<User> getSubscriptions() {
        return subscriptions;
    }

    public boolean isSubscribed(User user) {
        return subscriptions.contains(user);
    }

    public void updateSubscriptions(User user) {
        if (!isSubscribed(user)) {
            subscriptions.add(user);
        } else {
            subscriptions.remove(user);
        }
    }

    public List<User> subscribe(User user) {
        subscriptions.add(user);
        return subscriptions;
    }

    public List<User> unsubscribe(User user) {
        subscriptions.remove(user);
        return subscriptions;
    }
}
