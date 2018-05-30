package com.example.model;


import com.example.exception.ActiveRecordException;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class CarModel extends BaseModel {

    private final UUID uuid;
    private long _id;
    private String name;
    private Date creationDate;
    private CategoryModel category;
    private CarNumberModel carNumber;
    private ManufacturerModel manufacturer;
    private double price;

    public CarModel() {
        this.uuid = UUID.randomUUID();
    }

    public CarModel(UUID uuid) {
        this.uuid = uuid;
    }

    /***************************************************************
     *  Getters and Setters
     ***************************************************************/
    public UUID getUuid() {
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public CategoryModel getCategory() {
        return category;
    }

    public void setCategory(CategoryModel category) {
        this.category = category;
    }

    public CarNumberModel getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(CarNumberModel carNumber) {
        this.carNumber = carNumber;
    }

    public ManufacturerModel getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(ManufacturerModel manufacturer) {
        this.manufacturer = manufacturer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public synchronized static CarModel findByID(long id) throws SQLException {
        Connection db = null;
        PreparedStatement dbStatement = null;
        try {
            db = getDatabaseConnection();

            String statement = "SELECT `name`, `creationDate`," +
                    " `category`, `carNumber`, `uuid`, " +
                    "`manufacturer`, `price` FROM `car` where `_id`=?";
            dbStatement = db.prepareStatement(statement);
            dbStatement.setLong(1, id);
            ResultSet rs = dbStatement.executeQuery();

            while (rs.next()) {
                UUID uuid = UUID.fromString(rs.getString("uuid"));
                String name = rs.getString("name");
                Date creationDate = new Date(rs.getLong("creationDate"));
                long category = rs.getLong("category");
                long carNumber = rs.getLong("carNumber");
                long manufacturer = rs.getLong("manufacturer");
                int price = rs.getInt("price");

                CarModel car = new CarModel(uuid);
                car.name = name;
                car.creationDate = creationDate;
                car.category = CategoryModel.findByID(category);
                car.carNumber = CarNumberModel.findByID(carNumber);
                car.manufacturer = ManufacturerModel.findByID(manufacturer);
                car._id = id;
                car.price = price;

                return car;
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

    public synchronized static List<CarModel> getCarList() throws SQLException {
        Connection db = null;
        PreparedStatement dbStatement = null;
        try {
            db = getDatabaseConnection();

            String statement = "SELECT `_id`,`name`, `creationDate`," +
                    " `category`, `carNumber`, `uuid`, " +
                    "`manufacturer`, `price` FROM `car`";
            dbStatement = db.prepareStatement(statement);
            ResultSet rs = dbStatement.executeQuery();
            List<CarModel> cars = new ArrayList<>();
            while (rs.next()) {
                UUID uuid = UUID.fromString(rs.getString("uuid"));
                String name = rs.getString("name");
                Date creationDate = new Date(rs.getLong("creationDate"));
                long category = rs.getLong("category");
                long id = rs.getLong("_id");
                long carNumber = rs.getLong("carNumber");
                long manufacturer = rs.getLong("manufacturer");
                int price = rs.getInt("price");

                CarModel car = new CarModel(uuid);
                car._id = id;
                car.name = name;
                car.creationDate = creationDate;
                car.category = CategoryModel.findByID(category);
                car.carNumber = CarNumberModel.findByID(carNumber);
                car.manufacturer = ManufacturerModel.findByID(manufacturer);
                car.price = price;
                cars.add(car);
            }
            return cars;

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

    public synchronized static CarModel findByName(String name) throws SQLException {
        Connection db = null;
        PreparedStatement dbStatement = null;
        try {
            db = getDatabaseConnection();

            String statement = "SELECT `name`, `creationDate`, `_id`," +
                    " `category`, `carNumber`, `uuid`, " +
                    "`manufacturer`, `price` FROM `car` where `name`=?";
            dbStatement = db.prepareStatement(statement);
            dbStatement.setString(1, name);
            ResultSet rs = dbStatement.executeQuery();

            while (rs.next()) {
                UUID uuid = UUID.fromString(rs.getString("uuid"));
                Date creationDate = new Date(rs.getLong("creationDate"));
                long category = rs.getLong("category");
                long id = rs.getLong("_id");
                long carNumber = rs.getLong("carNumber");
                long manufacturer = rs.getLong("manufacturer");
                int price = rs.getInt("price");

                CarModel car = new CarModel(uuid);
                car.name = name;
                car.creationDate = creationDate;
                car.category = CategoryModel.findByID(category);
                car.carNumber = CarNumberModel.findByID(carNumber);
                car.manufacturer = ManufacturerModel.findByID(manufacturer);
                car._id = id;
                car.price = price;
                return car;
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


    public synchronized static CarModel findByCarNumber(CarNumberModel carNumberModel) throws SQLException {
        Connection db = null;
        PreparedStatement dbStatement = null;
        try {
            db = getDatabaseConnection();
            String statement = "SELECT `name`, `creationDate`, `_id`," +
                    " `category`, `carNumber`, `uuid`, " +
                    "`manufacturer`, `price` FROM `car` where `carNumber`=?";
            dbStatement = db.prepareStatement(statement);
            dbStatement.setLong(1, carNumberModel.getId());
            ResultSet rs = dbStatement.executeQuery();

            while (rs.next()) {
                UUID uuid = UUID.fromString(rs.getString("uuid"));
                Date creationDate = new Date(rs.getLong("creationDate"));
                long category = rs.getLong("category");
                long id = rs.getLong("_id");
                long carNumber = rs.getLong("carNumber");
                long manufacturer = rs.getLong("manufacturer");
                String name = rs.getString("name");
                int price = rs.getInt("price");

                CarModel car = new CarModel(uuid);
                car.name = name;
                car.creationDate = creationDate;
                car.category = CategoryModel.findByID(category);
                car.carNumber = CarNumberModel.findByID(carNumber);
                car.manufacturer = ManufacturerModel.findByID(manufacturer);
                car._id = id;
                car.price = price;
                return car;
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

    public synchronized void update() throws SQLException {
        Connection db = null;
        PreparedStatement dbStatement = null;
        try {
            db = getDatabaseConnection();

            String statement = "UPDATE `car` SET `name`=?, " +
                    "`creationDate`=?, `category`=?, " +
                    "`carNumber`=?, `uuid`=?, " +
                    "`manufacturer`=?, " +
                    "`price`=? where `_id`=?";
            dbStatement = db.prepareStatement(statement);
            dbStatement.setString(1, this.name);
            dbStatement.setLong(2, this.creationDate.getTime());
            if (category != null) {
                if (category.isSaved()) {
                    category.update();
                } else {
                    category.insert();
                }
                dbStatement.setLong(3, this.category.getId());
            } else {
                dbStatement.setNull(3, Types.INTEGER);
            }
            if (carNumber != null) {
                if (carNumber.isSaved()) {
                    carNumber.update();
                } else {
                    carNumber.insert();
                }
                dbStatement.setLong(4, this.carNumber.getId());
            } else {
                dbStatement.setNull(4, Types.INTEGER);
            }
            dbStatement.setString(5, this.uuid.toString());
            if (manufacturer != null) {
                if (manufacturer.isSaved()) {
                    manufacturer.update();
                } else {
                    manufacturer.insert();
                }
                dbStatement.setLong(6, this.manufacturer.getId());
            } else {
                dbStatement.setNull(6, Types.INTEGER);
            }
            dbStatement.setDouble(7, price);
            dbStatement.setLong(8, _id);
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

            String statement = "INSERT INTO `car` (`name`, `creationDate`, `category`, " +
                    "`carNumber`, `uuid`, `manufacturer`, `price`) VALUES (?, ?, ?, ?, ?, ?, ?)";
            dbStatement = db.prepareStatement(statement);
            dbStatement.setString(1, this.name);
            dbStatement.setLong(2, this.creationDate.getTime());
            if (category != null) {
                dbStatement.setLong(3, this.category.getId());
            } else {
                dbStatement.setNull(3, Types.INTEGER);
            }
            if (carNumber != null) {
                dbStatement.setLong(4, this.carNumber.getId());
            } else {
                dbStatement.setNull(4, Types.INTEGER);
            }
            dbStatement.setString(5, this.uuid.toString());
            if (manufacturer != null) {
                dbStatement.setLong(6, this.manufacturer.getId());
            } else {
                dbStatement.setNull(6, Types.INTEGER);
            }
            dbStatement.setDouble(7, price);
            dbStatement.executeUpdate();
            String selectStatement = "SELECT `_id` FROM `car` where `uuid`=?";
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

            String statement = "DELETE FROM `car` where `uuid`=?";
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
                this.getName(),
                dateFormat.format(this.getCreationDate()),
                this.getCategory().getName(),
                this.getCarNumber().getName(),
                this.getManufacturer().getName(),
                this.getPrice(),
                "Edit",
                "Delete"};
    }
}