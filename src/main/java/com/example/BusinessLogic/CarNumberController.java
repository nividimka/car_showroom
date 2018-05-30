package com.example.BusinessLogic;


import com.example.model.CarNumberModel;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by yuraf_000 on 03.12.2014.
 */
public class CarNumberController {
    Date creationDate;
    String name;
    Date expirationDate;

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public boolean checkCarNumber() {
        try {
            CarNumberModel userModel = CarNumberModel.findByName(name);
            if (userModel != null) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean newCarNumber() {
        if (!checkCarNumber()) {
            CarNumberModel carNumberModel = new CarNumberModel(UUID.randomUUID());
            carNumberModel.setName(name);
            carNumberModel.setCreationDate(creationDate);
            carNumberModel.setExpirationDate(expirationDate);
            if (!carNumberModel.isSaved())
                try {
                    carNumberModel.insert();
                    return true;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return false;
    }

    public CarNumberModel getCarNumber() {
        try {
            return CarNumberModel.findByName(name);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<CarNumberModel> getCarNumberList() {
        try {
            return CarNumberModel.getCarNumberList();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
