package com.example.BusinessLogic;


import com.example.model.CategoryModel;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

/**
 * Created by yuraf_000 on 03.12.2014.
 */
public class CarCategoryController {
    String name;

    public CarCategoryController(String name) {
        this.name = name;
    }

    public boolean checkCategory() {
        try {
            CategoryModel userModel = CategoryModel.findByName(name);
            if (userModel != null) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean newCarCategory() {
        if (!checkCategory()) {
            CategoryModel carNumberModel = new CategoryModel(UUID.randomUUID());
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

    public CategoryModel getCarCategory() {
        try {
            return CategoryModel.findByName(name);
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public static List<CategoryModel> getCarCategoryList() {
        try{
            return CategoryModel.getCarCategoryList();
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
