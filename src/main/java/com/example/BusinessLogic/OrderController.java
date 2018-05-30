package com.example.BusinessLogic;

import com.example.model.CarModel;
import com.example.model.OrderModel;
import com.example.model.UserModel;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class OrderController {
    public UserModel userModel;
    public CarModel carModel;
    public Date creationDate;
    public Date expirationDate;

    public OrderController() {
    }

    public OrderController(UserModel userModel, CarModel carModel, Date creationDate, Date expirationDate) {
        this.userModel = userModel;
        this.carModel = carModel;
        this.creationDate = creationDate;
        this.expirationDate = expirationDate;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public CarModel getCarModel() {
        return carModel;
    }

    public void setCarModel(CarModel carModel) {
        this.carModel = carModel;
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

    public long createOrder() {
        try {
            OrderModel orderModel = new OrderModel(UUID.randomUUID());
            orderModel.setCreationDate(creationDate);
            orderModel.setExpirationDate(expirationDate);
            orderModel.setCar(carModel);
            orderModel.setUser(userModel);
            if (!orderModel.isSaved()) {
                orderModel.insert();
                return orderModel.getId();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public OrderModel getOrder(long carId) {
        try {
            OrderModel orderModel = OrderModel.findByID(carId);
            return orderModel;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object[][] getOrderTableObject() {
        Object[][] data = null;
        try {
            List<OrderModel> orderModels = OrderModel.getOrderList();
            data = new Object[orderModels.size()][9];
            for (int i = 0; i < orderModels.size(); i++) {
                OrderModel model = orderModels.get(i);
                data[i] = model.toObject();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
}
