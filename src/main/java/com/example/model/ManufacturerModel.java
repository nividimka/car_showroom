package com.example.model;


import com.example.exception.ActiveRecordException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ManufacturerModel extends BaseModel {
    private long _id;
    private final UUID uuid;
    private String name;


    public ManufacturerModel() {
        this.uuid = UUID.randomUUID();
    }

    public ManufacturerModel(UUID uuid) {
        this.uuid = uuid;
    }

    /***************************************************************
     *  Getters and Setters
     ***************************************************************/
    public UUID getUUID() {
        return uuid;
    }

    public long getId() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    /***************************************************************
     *  Domain Logic Methods
     ***************************************************************/


    public boolean isSaved() {    // a grade of D or F
        // results in being put on probation…
        return _id != 0;
    }

    @Override
    public String toString() {
        return name;
    }

    /***************************************************************
     *  SQL Operation Methods
     ***************************************************************/

    public synchronized static ManufacturerModel findByID(long id) throws SQLException {
        Connection db = null;
        PreparedStatement dbStatement = null;
        try {
            db = getDatabaseConnection();
            String statement = "SELECT `_id`, `uuid`, `name` FROM `manufacturer` where `_id`=?";
            dbStatement = db.prepareStatement(statement);
            dbStatement.setLong(1, id);
            ResultSet rs = dbStatement.executeQuery();

            while (rs.next()) {
                UUID uuid = UUID.fromString(rs.getString("uuid"));
                String name = rs.getString("name");
                long _id = rs.getLong("_id");
                ManufacturerModel manufacturer = new ManufacturerModel(uuid);
                manufacturer.name = name;
                manufacturer._id = _id;
                return manufacturer;
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
    public synchronized static ManufacturerModel findByName(String name) throws SQLException {
        Connection db = null;
        PreparedStatement dbStatement = null;
        try {
            db = getDatabaseConnection();
            String statement = "SELECT `_id`, `uuid`, `name` FROM `manufacturer` where `name`=?";
            dbStatement = db.prepareStatement(statement);
            dbStatement.setString(1, name);
            ResultSet rs = dbStatement.executeQuery();

            if (rs.next()) {
                UUID uuid = UUID.fromString(rs.getString("uuid"));
                long _id = rs.getLong("_id");
                ManufacturerModel manufacturer = new ManufacturerModel(uuid);
                manufacturer.name = name;
                manufacturer._id = _id;
                return manufacturer;
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

    public static List<ManufacturerModel> getManufacturerList() throws SQLException {
        Connection db = null;
        PreparedStatement dbStatement = null;
        try {
            db = getDatabaseConnection();
            String statement = "SELECT `_id`, `uuid`, `name` FROM `manufacturer`";
            dbStatement = db.prepareStatement(statement);
            ResultSet rs = dbStatement.executeQuery();
            List<ManufacturerModel> manufacturerModels = new ArrayList<>();
            while (rs.next()) {
                UUID uuid = UUID.fromString(rs.getString("uuid"));
                long _id = rs.getLong("_id");
                String name = rs.getString("name");
                ManufacturerModel manufacturer = new ManufacturerModel(uuid);
                manufacturer.name = name;
                manufacturer._id = _id;
                manufacturerModels.add(manufacturer);
            }
            return manufacturerModels;

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
            String statement = "UPDATE `manufacturer` SET `name`=? where `uuid`=?";
            dbStatement = db.prepareStatement(statement);
            dbStatement.setString(1, this.name);
            dbStatement.setString(2, this.uuid.toString());
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
                    statement = "INSERT INTO `manufacturer` (`name`, `uuid`) VALUES (?, ?)";
            dbStatement = db.prepareStatement(statement);
            dbStatement.setString(1, this.name);
            dbStatement.setString(2, this.uuid.toString());
            dbStatement.executeUpdate();
            String selectStatement = "SELECT `_id` FROM `manufacturer` where `uuid`=?";
            dbStatement = db.prepareStatement(selectStatement);
            dbStatement.setString(1, uuid.toString());
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
            String statement = "DELETE FROM `manufacturer` where `uuid`=?";
            dbStatement = db.prepareStatement(statement);
            dbStatement.setString(1, this.uuid.toString());
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