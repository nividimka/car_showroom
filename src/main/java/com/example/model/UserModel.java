package com.example.model;

import com.example.exception.ActiveRecordException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserModel extends BaseModel {
    private String username;
    private String password;
    public long _id;


    public UserModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /***************************************************************
     *  Getters and Setters
     ***************************************************************/
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return _id;
    }

    public void setId(long _id) {
        this._id = _id;
    }

    @Override
    public String toString() {
        return username;
    }

    /***************************************************************
     *  Domain Logic Methods
     ***************************************************************/


    public boolean isSaved() {    // a grade of D or F
        // results in being put on probation…
        return _id != 0;
    }

    /***************************************************************
     *  SQL Operation Methods
     ***************************************************************/

    public synchronized static UserModel findByUsername(String username) throws SQLException {
        Connection db = null;
        PreparedStatement dbStatement = null;
        try {
            db = getDatabaseConnection();
            String statement = "SELECT `_id`, `password` FROM `user` where `username`=?";
            dbStatement = db.prepareStatement(statement);
            dbStatement.setString(1, username);
            ResultSet rs = dbStatement.executeQuery();

            while (rs.next()) {
                long _id = rs.getLong("_id");
                String password = rs.getString("password");
                UserModel user = new UserModel(username, password);
                user.setId(_id);
                return user;
            }
            return null;

        } catch (SQLException e) {
            // We don't want any types which use the Active Record
            // to be coupled to java.sql.SQLException
            // So instead, we throw a custom ActiveRecordException
            throw new ActiveRecordException("Error occured reading Students from the data source.", e);
        } finally {
            if (dbStatement != null) dbStatement.close();
            if (db != null) db.close();
        }
    }
    public synchronized static UserModel findById(long id) throws SQLException {
        Connection db = null;
        PreparedStatement dbStatement = null;
        try {
            db = getDatabaseConnection();
            String statement = "SELECT `username`, `password` FROM `user` where `_id`=?";
            dbStatement = db.prepareStatement(statement);
            dbStatement.setLong(1, id);
            ResultSet rs = dbStatement.executeQuery();

            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                UserModel user = new UserModel(username, password);
                user.setId(id);
                return user;
            }
            return null;

        } catch (SQLException e) {
            // We don't want any types which use the Active Record
            // to be coupled to java.sql.SQLException
            // So instead, we throw a custom ActiveRecordException
            throw new ActiveRecordException("Error occured reading Students from the data source.", e);
        } finally {
            if (dbStatement != null) dbStatement.close();
            if (db != null) db.close();
        }
    }
    public synchronized static List<UserModel> getUserList() throws SQLException {
        Connection db = null;
        PreparedStatement dbStatement = null;
        try {
            db = getDatabaseConnection();
            String statement = "SELECT `_id`, `password`,`username` FROM `user`";
            dbStatement = db.prepareStatement(statement);
            ResultSet rs = dbStatement.executeQuery();
            List<UserModel> users = new ArrayList<>();
            while (rs.next()) {
                long _id = rs.getLong("_id");
                String password = rs.getString("password");
                String username = rs.getString("username");
                UserModel user = new UserModel(username, password);
                user.setId(_id);
                users.add(user);
            }
            return users;

        } catch (SQLException e) {
            // We don't want any types which use the Active Record
            // to be coupled to java.sql.SQLException
            // So instead, we throw a custom ActiveRecordException
            throw new ActiveRecordException("Error occured reading Students from the data source.", e);
        } finally {
            if (dbStatement != null) dbStatement.close();
            if (db != null) db.close();
        }
    }

    public synchronized void update() throws SQLException {
        Connection db = null;
        PreparedStatement dbStatement = null;
        try {
            db = getDatabaseConnection();
            String statement = "UPDATE `user` SET `username`=?, `password`=? where `_id`=?";
            dbStatement = db.prepareStatement(statement);
            dbStatement.setString(1, this.username);
            dbStatement.setString(2, this.password);
            dbStatement.setLong(3, this._id);
            dbStatement.executeUpdate();

        } catch (SQLException e) {
            // We don't want any types which use the Active Record
            // to be coupled to java.sql.SQLException
            // So instead, we throw a custom ActiveRecordException
            throw new ActiveRecordException("Error occured reading Students from the data source.", e);
        } finally {
            if (dbStatement != null) dbStatement.close();
            if (db != null) db.close();
        }
    }

    public synchronized void insert() throws SQLException {
        Connection db = null;
        PreparedStatement dbStatement = null;
        try {
            db = getDatabaseConnection();
            String
                    statement = "INSERT INTO `user` (`username`, `password`) VALUES (?, ?)";
            dbStatement = db.prepareStatement(statement);
            dbStatement.setString(1, this.username);
            dbStatement.setString(2, this.password);
            dbStatement.executeUpdate();
            String selectStatement = "SELECT `_id` FROM `user` where `username`=?";
            dbStatement = db.prepareStatement(selectStatement);
            dbStatement.setString(1, username);
            ResultSet rs = dbStatement.executeQuery();
            if (rs.next()) {
                this._id = rs.getLong("_id");
            }

        } catch (SQLException e) {
            // We don't want any types which use the Active Record
            // to be coupled to java.sql.SQLException
            // So instead, we throw a custom ActiveRecordException
            throw new ActiveRecordException("Error occured reading Students from the data source.", e);
        } finally {
            if (dbStatement != null) dbStatement.close();
            if (db != null) db.close();
        }
    }

    public synchronized void delete() throws SQLException {
        Connection db = null;
        PreparedStatement dbStatement = null;
        try {
            db = getDatabaseConnection();
            String statement = "DELETE FROM `user` where `_id`=?";
            dbStatement = db.prepareStatement(statement);
            dbStatement.setLong(1, this._id);
            dbStatement.executeUpdate();

        } catch (SQLException e) {
            // We don't want any types which use the Active Record
            // to be coupled to java.sql.SQLException
            // So instead, we throw a custom ActiveRecordException
            throw new ActiveRecordException("Error occured reading Students from the data source.", e);
        } finally {
            if (dbStatement != null) dbStatement.close();
            if (db != null) db.close();
        }
    }

}
