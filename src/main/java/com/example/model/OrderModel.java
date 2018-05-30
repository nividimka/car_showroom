package com.example.model;

import com.example.exception.ActiveRecordException;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class OrderModel extends BaseModel {
    long _id;
    UUID uuid;
    CarModel car;
    Date expirationDate;
    Date creationDate;
    UserModel user;


    public OrderModel() {
        this.uuid = UUID.randomUUID();
    }

    public OrderModel(UUID uuid) {
        this.uuid = uuid;
    }


    /***************************************************************
     *  Getters and Setters
     ***************************************************************/


    public long getId() {
        return _id;
    }

    public void setId(long _id) {
        this._id = _id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public CarModel getCar() {
        return car;
    }

    public void setCar(CarModel car) {
        this.car = car;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }


    /***************************************************************
     *  Domain Logic Methods
     ***************************************************************/


    public boolean isSaved() {    // a grade of D or F
        // results in being put on probation…
        return _id != 0;
    }

    public boolean isPassed() {
        return expirationDate.getTime() < Calendar.getInstance().getTimeInMillis();
    }

    public synchronized static OrderModel findByID(long id) throws SQLException {
        Connection db = null;
        PreparedStatement dbStatement = null;
        try {
            db = getDatabaseConnection();

            String statement = "SELECT `uuid`, `car`, `user`," +
                    " `start_date`, `end_date` FROM `order` where `_id`=?";
            dbStatement = db.prepareStatement(statement);
            dbStatement.setLong(1, id);
            ResultSet rs = dbStatement.executeQuery();

            while (rs.next()) {
                UUID uuid = UUID.fromString(rs.getString("uuid"));
                long car = rs.getLong("car");
                long user = rs.getLong("user");
                Date startDate = new Date(rs.getLong("start_date"));
                Date endDate = new Date(rs.getLong("end_date"));

                OrderModel order = new OrderModel(uuid);
                order._id = id;
                order.user = UserModel.findById(user);
                order.creationDate = startDate;
                order.uuid = uuid;
                order.expirationDate = endDate;
                order.car = CarModel.findByID(car);
                return order;
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

    public synchronized static OrderModel findByUUID(UUID uuid) throws SQLException {
        Connection db = null;
        PreparedStatement dbStatement = null;
        try {
            db = getDatabaseConnection();

            String statement = "SELECT `_id`, `car`, `user`," +
                    " `start_date`, `end_date` FROM `order` where `uuid`=?";
            dbStatement = db.prepareStatement(statement);
            dbStatement.setString(1, uuid.toString());
            ResultSet rs = dbStatement.executeQuery();

            while (rs.next()) {
                long id = rs.getLong("_id");
                long car = rs.getLong("car");
                long user = rs.getLong("user");
                Date startDate = new Date(rs.getLong("start_date"));
                Date endDate = new Date(rs.getLong("end_date"));

                OrderModel order = new OrderModel(uuid);
                order._id = id;
                order.user = UserModel.findById(user);
                order.creationDate = startDate;
                order.uuid = uuid;
                order.expirationDate = endDate;
                order.car = CarModel.findByID(car);
                return order;
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


    public static List<OrderModel> getOrderList() throws SQLException {
        Connection db = null;
        PreparedStatement dbStatement = null;
        try {
            db = getDatabaseConnection();

            String statement = "SELECT `_id`, `car`, `user`," +
                    " `start_date`, `end_date`, `uuid` FROM `order`";
            dbStatement = db.prepareStatement(statement);
            ResultSet rs = dbStatement.executeQuery();
            List<OrderModel> orders = new ArrayList<>();
            while (rs.next()) {
                long id = rs.getLong("_id");
                long car = rs.getLong("car");
                long user = rs.getLong("user");
                String uuid = rs.getString("uuid");
                Date startDate = new Date(rs.getLong("start_date"));
                Date endDate = new Date(rs.getLong("end_date"));

                OrderModel order = new OrderModel(UUID.fromString(uuid));
                order._id = id;
                order.user = UserModel.findById(user);
                order.creationDate = startDate;
                order.expirationDate = endDate;
                order.car = CarModel.findByID(car);
                orders.add(order);
            }
            return orders;
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

            String statement = "UPDATE `order` SET `uuid`=?, " +
                    "`car`=?,`user`=?,`start_date`=?, `end_date`=? where `_id`=?";
            dbStatement = db.prepareStatement(statement);
            dbStatement.setString(1, this.uuid.toString());
            if (car != null) {
                if (car.isSaved()) {
                    car.update();
                } else {
                    car.insert();
                }
                dbStatement.setLong(2, this.car.getId());
            } else {
                dbStatement.setNull(2, Types.INTEGER);
            }
            if (user != null) {
                if (user.isSaved()) {
                    user.update();
                } else {
                    user.insert();
                }
                dbStatement.setLong(3, this.user.getId());
            } else {
                dbStatement.setNull(3, Types.INTEGER);
            }
            dbStatement.setLong(4, this.creationDate.getTime());
            dbStatement.setLong(5, this.expirationDate.getTime());
            dbStatement.setLong(6, _id);
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

            String statement = "INSERT INTO `order` (`uuid`, `car`, `user`, `start_date`, `end_date`) VALUES (?, ?, ?, ?, ?)";
            dbStatement = db.prepareStatement(statement);
            dbStatement.setString(1, this.uuid.toString());
            if (car != null) {
                if (car.isSaved()) {
                    car.update();
                } else {
                    car.insert();
                }
                dbStatement.setLong(2, this.car.getId());
            } else {
                dbStatement.setNull(2, Types.INTEGER);
            }
            if (user != null) {
                if (user.isSaved()) {
                    user.update();
                } else {
                    user.insert();
                }
                dbStatement.setLong(3, this.user.getId());
            } else {
                dbStatement.setNull(3, Types.INTEGER);
            }
            dbStatement.setLong(4, this.creationDate.getTime());
            dbStatement.setLong(5, this.expirationDate.getTime());
            dbStatement.executeUpdate();
            String selectStatement = "SELECT `_id` FROM `order` where `uuid`=?";
            dbStatement = db.prepareStatement(selectStatement);
            dbStatement.setString(1, uuid.toString());
            ResultSet rs = dbStatement.executeQuery();
            if (rs.next()) {
                this._id = rs.getLong("_id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
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

            String statement = "DELETE FROM `order` where `uuid`=?";
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

    public Object[] toObject() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return new Object[]{this.getId(),
                this.getCar().getName(),
                this.getUser().getUsername(),
                dateFormat.format(this.getCreationDate()),
                dateFormat.format(this.getExpirationDate())};
    }
}
