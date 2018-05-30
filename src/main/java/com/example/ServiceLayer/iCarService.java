package com.example.ServiceLayer;

import com.example.model.CarModel;
import com.example.model.CarNumberModel;
import com.example.model.CategoryModel;
import com.example.model.ManufacturerModel;

import java.util.Date;
import java.util.List;

public interface iCarService {
    public boolean newCarNumber(String name, Date creationDate, Date expirationDate);
    public CarNumberModel getCarNumber(String text);
    public List<CarNumberModel> getCarNumberList();
    public CategoryModel getCarCategory(String text);
    public boolean newCarCategory(String text);
    public List<CategoryModel> getCarCategoryList();
    public ManufacturerModel getCarManufacturer(String text);
    public List<ManufacturerModel> getManufacturerList();
    public boolean newCarManufacturer(String text);
    public long newCar(String name, CarNumberModel carNumberModel, CategoryModel categoryModel, ManufacturerModel manufacturerModel, Date creationDate, double price);
    public long updateCar(long carId, String name, CarNumberModel carNumberModel, CategoryModel categoryModel, ManufacturerModel manufacturerModel, Date creationDate, double price);
    public boolean isNumberAlreadyAssigned(CarNumberModel carNumberModel);
    public boolean deleteCar(long id);
    public CarModel getCar(long carId);

}
