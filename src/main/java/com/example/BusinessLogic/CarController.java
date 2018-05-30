package com.example.BusinessLogic;


import com.example.model.CarModel;
import com.example.model.CarNumberModel;
import com.example.model.CategoryModel;
import com.example.model.ManufacturerModel;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by yuraf_000 on 03.12.2014.
 */
public class CarController {
    public CategoryModel categoryModel;
    public ManufacturerModel manufacturerModel;
    public CarNumberModel carNumberModel;
    public Date creationDate;
    public String name;
    public double price;

    public CarController(CategoryModel categoryModel, ManufacturerModel manufacturerModel, CarNumberModel carNumberModel, Date creationDate, String name, double price) {
        this.categoryModel = categoryModel;
        this.manufacturerModel = manufacturerModel;
        this.carNumberModel = carNumberModel;
        this.creationDate = creationDate;
        this.name = name;
        this.price = price;
    }

    public CarController() {
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public CarNumberModel getCarNumberModel() {
        return carNumberModel;
    }

    public void setCarNumberModel(CarNumberModel carNumberModel) {
        this.carNumberModel = carNumberModel;
    }

    public ManufacturerModel getManufacturerModel() {
        return manufacturerModel;
    }

    public void setManufacturerModel(ManufacturerModel manufacturerModel) {
        this.manufacturerModel = manufacturerModel;
    }

    public CategoryModel getCategoryModel() {
        return categoryModel;
    }

    public void setCategoryModel(CategoryModel categoryModel) {
        this.categoryModel = categoryModel;
    }




    public boolean isNumberAlreadyAssigned(CarNumberModel carNumberModel) {
        try {
            return CarModel.findByCarNumber(carNumberModel) != null;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public long createCar() {
        try {
            CarModel carModel = new CarModel();
            carModel.setCreationDate(creationDate);
            carModel.setName(name);
            carModel.setManufacturer(manufacturerModel);
            carModel.setCarNumber(carNumberModel);
            carModel.setCategory(categoryModel);
            carModel.setPrice(price);
            if (!carModel.isSaved()) {
                carModel.insert();
                return carModel.getId();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public long updateCar(long carId) {
        try {
            CarModel carModel = CarModel.findByID(carId);
            if (carModel == null) {
                return -1;
            }
            carModel.setCreationDate(creationDate);
            carModel.setName(name);
            carModel.setManufacturer(manufacturerModel);
            carModel.setCarNumber(carNumberModel);
            carModel.setCategory(categoryModel);
            carModel.setPrice(price);
            if (carModel.isSaved()) {
                carModel.update();
                return carModel.getId();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static Object[][] getCarTableObjects() {
        Object[][] data = null;
        try {
            List<CarModel> carModels = CarModel.getCarList();
            data = new Object[carModels.size()][9];
            for (int i = 0; i < carModels.size(); i++) {
                CarModel model = carModels.get(i);
                data[i] = model.toObject();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public boolean deleteCar(long id) {
        try {
            CarModel carModel = CarModel.findByID(id);
            if (carModel.isSaved()) {
                carModel.delete();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public CarModel getCar(long carId) {
        try {
            CarModel carModel = CarModel.findByID(carId);
            return carModel;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<CarModel> getCarList() {
        try {
            return CarModel.getCarList();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
