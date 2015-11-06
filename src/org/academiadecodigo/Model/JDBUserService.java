package org.academiadecodigo.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBUserService implements UserService {

    Connection dbConnection;

    @Override
    public boolean authenticate(String user, String password) {

        User u = findByName(user);

        return u != null && u.getPassword().equals(password);


    }

    @Override
    public void addUser(User user) {
        try {
            Statement sta = dbConnection.createStatement();
            String username = user.getUsername();
            String userpass = user.getPassword();
            String usermail = user.getEmail();

            sta.execute("insert into user(username, password, email) values('" + username + "','" + userpass + "','" + usermail + " ');" );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findByName(String user) {

        User u = null;
        Statement statement = null;
        try {

            statement = dbConnection.createStatement();
            String query = "Select username FROM user WHERE username='" + user +"';";
            ResultSet result = statement.executeQuery(query);

            if ( result.next()) {
                String usernameValue = result.getString("username");
                String passwordValue = result.getString("password");
                String emailValue = result.getString("email");

                u = new User(usernameValue, passwordValue, emailValue);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {

                if(statement != null) {
                    statement.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return u;

    }

    public int count() {

        int result = 0;



        try {
            Statement statement = dbConnection.createStatement();
            // create a query
            String query = "SELECT COUNT(*) FROM user";

            // execute the query
            ResultSet resultSet = statement.executeQuery(query);

            // get the results
            if (resultSet.next()) {
                result = resultSet.getInt(1);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}

