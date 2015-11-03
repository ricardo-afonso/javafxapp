package org.academiadecodigo.Model;

public interface UserService {

    boolean authenticate (String user, String password);
    void addUser (User user);
    User findByName(String user);
    int count();



}
