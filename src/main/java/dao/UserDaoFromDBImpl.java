package dao;

import entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoFromDBImpl implements UserDao {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost/webdb?serverTimezone=UTC";

    static final String USER = "root";
    static final String PASSWORD = "root";

    @Override
    public void create(User user) {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try(Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            Statement statement = connection.createStatement()) {

            statement.execute("insert into user (login, password, first_name, last_name) values (" +
                    "'" + user.getLogin() + "'," +
                    "'" + user.getPassword() + "'," +
                    "'" + user.getFirstName() + "'," +
                    "'" + user.getLastName() + "')");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try(Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            Statement statement = connection.createStatement()) {

            statement.execute("delete from user where id = " + id);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try(Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            Statement statement = connection.createStatement()) {

            statement.execute("update user set first_name = '" + user.getFirstName() + "', " +
                    "last_name = '" + user.getLastName()+"' where id =" + user.getId() + ";");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public User findById(Long id) {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        User user = null;

        try(Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            Statement statement = connection.createStatement()) {

            final ResultSet rs = statement.executeQuery("select * from user where id = '" + id + "'");
            if (rs.next()){
                user = new User(rs.getLong("id"), rs.getString("login"), rs.getString("password"),
                        rs.getString("first_name"), rs.getString("last_name"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return user;
    }

    @Override
    public List<User> findByLastName(String lastName) {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<User> list = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            Statement statement = connection.createStatement()) {

            final ResultSet rs = statement.executeQuery("select * from user where last_name = '" + lastName + "'");

            while (rs.next()){
                User user = new User(rs.getLong("id"), rs.getString("login"), rs.getString("password"),
                        rs.getString("first_name"), rs.getString("last_name"));
                list.add(user);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    @Override
    public List<User> findAll() {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<User> list = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            Statement statement = connection.createStatement()) {

            final ResultSet rs = statement.executeQuery("select * from user");

            while (rs.next()){
                User user = new User(rs.getLong("id"), rs.getString("login"), rs.getString("password"),
                        rs.getString("first_name"), rs.getString("last_name"));
                list.add(user);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }
}
