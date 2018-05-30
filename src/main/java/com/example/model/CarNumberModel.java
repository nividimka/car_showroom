package com.example.model;


import com.example.exception.ActiveRecordException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CarNumberModel extends BaseModel {

    private final UUID uuid;
    private long _id;
    private String name;
    private Date creationDate;
    private Date expirationDate;
    private static final int MONTH_IN_SECONDS = 30 * 24 * 60 * 60 * 1000;


    public CarNumberModel() {
        this.uuid = UUID.randomUUID();
    }

    public CarNumberModel(UUID uuid) {
        this.uuid = uuid;
    }

    /***************************************************************
     *  Getters and Setters
     ***************************************************************/
    public UUID getUUID() {
        return uuid;
    }
    public long getId(){
        return _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return name;
    }

    /***************************************************************
     *  Domain Logic Methods
     ***************************************************************/

    public boolean shouldNotifyForChanging() {
        return Math.abs(Calendar.getInstance().getTimeInMillis() - expirationDate.getTime()) >= MONTH_IN_SECONDS;
    }

    public boolean isOverdue() {
        return Calendar.getInstance().getTimeInMillis() > expirationDate.getTime();
    }

    public boolean isSaved() {    // a grade of D or F
        // results in being put on probation…
        return _id != 0;
    }

    /***************************************************************
     *  SQL Operation Methods
     ***************************************************************/

    public synchronized static CarNumberModel findByID(long id) throws SQLException {
        Connection db = null;
        PreparedStatement dbStatement = null;
        try {
            db = getDatabaseConnection();
            String statement = "SELECT `_id`, `uuid`, `name`, `creationDate`, `expirationDate` FROM `car_number` where `_id`=?";
            dbStatement = db.prepareStatement(statement);
            dbStatement.setLong(1, id);
            ResultSet rs = dbStatement.executeQuery();

            while (rs.next()) {
                UUID uuid = UUID.fromString(rs.getString("uuid"));
                String name = rs.getString("name");
                Date creationDate = new Date(rs.getLong("creationDate"));
                Date expirationDate = new Date(rs.getLong("expirationDate"));
                long _id = rs.getLong("_id");

                CarNumberModel carNumber = new CarNumberModel(uuid);
                carNumber.name = name;
                carNumber.expirationDate = expirationDate;
                carNumber.creationDate = creationDate;
                carNumber._id = _id;
                return carNumber;
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
    public synchronized static CarNumberModel findByName(String name) throws SQLException {
        Connection db = null;
        PreparedStatement dbStatement = null;
        try {
            db = getDatabaseConnection();
            String statement = "SELECT `_id`, `uuid`, `name`, `creationDate`, `expirationDate` FROM `car_number` where `name`=?";
            dbStatement = db.prepareStatement(statement);
            dbStatement.setString(1, name);
            ResultSet rs = dbStatement.executeQuery();

            if (rs.next()) {
                UUID uuid = UUID.fromString(rs.getString("uuid"));
                Date creationDate = new Date(rs.getLong("creationDate"));
                Date expirationDate = new Date(rs.getLong("expirationDate"));
                long _id = rs.getLong("_id");

                CarNumberModel carNumber = new CarNumberModel(uuid);
                carNumber.name = name;
                carNumber.expirationDate = expirationDate;
                carNumber.creationDate = creationDate;
                carNumber._id = _id;
                return carNumber;
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


    public static List<CarNumberModel> getCarNumberList() throws SQLException {
        Connection db = null;
        PreparedStatement dbStatement = null;
        List<CarNumberModel> carNumberModels = new ArrayList<>();
        try {
            db = getDatabaseConnection();
            String statement = "SELECT `_id`, `uuid`, `name`, `creationDate`, `expirationDate` FROM `car_number`";
            dbStatement = db.prepareStatement(statement);
            ResultSet rs = dbStatement.executeQuery();

            while (rs.next()) {
                UUID uuid = UUID.fromString(rs.getString("uuid"));
                Date creationDate = new Date(rs.getLong("creationDate"));
                Date expirationDate = new Date(rs.getLong("expirationDate"));
                String name = rs.getString("name");
                long _id = rs.getLong("_id");

                CarNumberModel carNumber = new CarNumberModel(uuid);
                carNumber.name = name;
                carNumber.expirationDate = expirationDate;
                carNumber.creationDate = creationDate;
                carNumber._id = _id;
                carNumberModels.add(carNumber);
            }
            return carNumberModels;

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
            String statement = "UPDATE `car_number` SET `name`=?, `creationDate`=?, `expirationDate`=? where `_id`=?";
            dbStatement = db.prepareStatement(statement);
            dbStatement.setString(1, this.name);
            dbStatement.setLong(2, this.creationDate.getTime());
            dbStatement.setLong(3, this.expirationDate.getTime());
            dbStatement.setLong(4, this._id);
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
                    statement = "INSERT INTO `car_number` (`name`, `creationDate`, `expirationDate`, `uuid`) VALUES (?, ?, ?, ?)";
            dbStatement = db.prepareStatement(statement);
            dbStatement.setString(1, this.name);
            dbStatement.setLong(2, this.creationDate.getTime());
            dbStatement.setLong(3, this.expirationDate.getTime());
            dbStatement.setString(4, this.uuid.toString());
            dbStatement.executeUpdate();
            String selectStatement = "SELECT `_id` FROM `car_number` where `uuid`=?";
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
            String statement = "DELETE FROM `car_number` where `uuid`=?";
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