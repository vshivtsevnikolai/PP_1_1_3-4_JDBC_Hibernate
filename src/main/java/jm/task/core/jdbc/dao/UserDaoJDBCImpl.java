package jm.task.core.jdbc.dao;

import com.mysql.cj.x.protobuf.MysqlxPrepare;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    Connection connection = Util.getConnection();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sqlCreate = "CREATE TABLE IF NOT EXISTS users (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), lastName VARCHAR(255), age INT)";
        try {
            Statement statementCreate = connection.createStatement();
            statementCreate.executeUpdate(sqlCreate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String sqlDrop = "DROP TABLE IF EXISTS users";
        try {
            Statement statementDrop = connection.createStatement();
            statementDrop.executeUpdate(sqlDrop);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void saveUser(String name, String lastName, byte age) {
        String sqlSave = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
        try {
            PreparedStatement statementSave = connection.prepareStatement(sqlSave);
            statementSave.setString(1, name);
            statementSave.setString(2, lastName);
            statementSave.setByte(3, age);
            statementSave.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String sqlRemove = "DELETE FROM users WHERE id = ?";
        try {
            PreparedStatement statementRemove = connection.prepareStatement(sqlRemove);
            statementRemove.setLong(1, id);
            statementRemove.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        String sqlAll = "SELECT * FROM users";
        List <User> users = new ArrayList<>();
        try {
            ResultSet result = connection.createStatement().executeQuery(sqlAll);
            while (result.next()) {
                User user = new User (result.getString("name"),
                        result.getString("lastName"),
                        result.getByte("age"));
                user.setId(result.getLong("id"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        String sqlClean = "TRUNCATE TABLE users";
        try {
            Statement statementCreate = connection.createStatement();
            statementCreate.executeUpdate(sqlClean);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
