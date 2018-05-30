package com.example.ServiceLayer;

import com.example.BusinessLogic.CarCategoryController;
import com.example.BusinessLogic.CarController;
import com.example.BusinessLogic.CarNumberController;
import com.example.BusinessLogic.ManufacturerController;
import com.example.model.*;

import java.util.Date;
import java.util.List;

/**
 * Created by yuraf_000 on 25.12.2014.
 */
public class CarService implements iCarService{

    public boolean newCarNumber(String name, Date creationDate, Date expirationDate) {
        CarNumberController controller = new CarNumberController();
        controller.setName(name);
        controller.setCreationDate(creationDate);
        controller.setExpirationDate(expirationDate);
        return controller.newCarNumber();
    }


    public CarNumberModel getCarNumber(String text) {
        CarNumberController controller = new CarNumberController();
        controller.setName(text);
        return controller.getCarNumber();
    }

    public List<CarNumberModel> getCarNumberList() {
        return CarNumberController.getCarNumberList();
    }

    public CategoryModel getCarCategory(String text) {
        CarCategoryController controller = new CarCategoryController(text);
        return controller.getCarCategory();
    }

    public boolean newCarCategory(String text) {
        CarCategoryController controller = new CarCategoryController(text);
        return controller.newCarCategory();
    }

    public List<CategoryModel> getCarCategoryList() {
        return CarCategoryController.getCarCategoryList();
    }


    public ManufacturerModel getCarManufacturer(String text) {
        ManufacturerController controller = new ManufacturerController(text);
        return controller.getManufacturer();
    }

    public List<ManufacturerModel> getManufacturerList() {
        return ManufacturerController.getManufacturerList();
    }

    public boolean newCarManufacturer(String text) {
        ManufacturerController controller = new ManufacturerController(text);
        return controller.newManufacturer();
    }

    public long newCar(String name, CarNumberModel carNumberModel, CategoryModel categoryModel, ManufacturerModel manufacturerModel, Date creationDate, double price) {
        CarController carWorker = new CarController();
        carWorker.setName(name);
        carWorker.setPrice(price);
        carWorker.setCarNumberModel(carNumberModel);
        carWorker.setCategoryModel(categoryModel);
        carWorker.setManufacturerModel(manufacturerModel);
        carWorker.setCreationDate(creationDate);
        return carWorker.createCar();
    }

    public long updateCar(long carId, String name, CarNumberModel carNumberModel, CategoryModel categoryModel, ManufacturerModel manufacturerModel, Date creationDate, double price) {
        CarController carWorker = new CarController();
        carWorker.setName(name);
        carWorker.setPrice(price);
        carWorker.setCarNumberModel(carNumberModel);
        carWorker.setCategoryModel(categoryModel);
        carWorker.setManufacturerModel(manufacturerModel);
        carWorker.setCreationDate(creationDate);
        return carWorker.updateCar(carId);
    }

    public boolean isNumberAlreadyAssigned(CarNumberModel carNumberModel) {
        CarController carWorker = new CarController();
        return carWorker.isNumberAlreadyAssigned(carNumberModel);
    }

    public Object[][] getCarTableObjects() {
        return CarController.getCarTableObjects();
    }

    public boolean deleteCar(long id) {
        CarController carWorker = new CarController();
        return carWorker.deleteCar(id);
    }

    public CarModel getCar(long carId) {
        CarController carWorker = new CarController();
        return carWorker.getCar(carId);
    }

    public List<CarModel> getCars() {
        return CarController.getCarList();
    }
}
