package com.example.BusinessLogic;


import com.example.model.ManufacturerModel;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

/**
 * Created by yuraf_000 on 03.12.2014.
 */
public class ManufacturerController {
    String name;

    public ManufacturerController(String name) {
        this.name = name;
    }

    public boolean checkManufacturer() {
        try {
            ManufacturerModel userModel = ManufacturerModel.findByName(name);
            if (userModel != null) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean newManufacturer() {
        if (!checkManufacturer()) {
            ManufacturerModel carNumberModel = new ManufacturerModel(UUID.randomUUID());
            carNumberModel.setName(name);
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

    public ManufacturerModel getManufacturer() {
        try {
            return ManufacturerModel.findByName(name);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<ManufacturerModel> getManufacturerList() {
        try {
            return ManufacturerModel.getManufacturerList();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
