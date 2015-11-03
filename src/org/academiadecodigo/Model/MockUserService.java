package org.academiadecodigo.Model;

import java.util.HashMap;
import java.util.Map;

public class MockUserService implements UserService {


    private Map<String, User> userDatabase = new HashMap<String, User>();


    @Override
    public boolean authenticate(String username, String password) {
        return userDatabase.containsKey(username) && userDatabase.get(username).getPassword().equals(password);
    }

    @Override
    public void addUser(User user) {
        if (!userDatabase.containsKey(user)) {
            userDatabase.put(user.getUsername(), user);
        }
    }

    @Override
    public User findByName(String username) {
        return userDatabase.get(username);
    }

    @Override
    public int count() {
        return userDatabase.size();
    }
}
