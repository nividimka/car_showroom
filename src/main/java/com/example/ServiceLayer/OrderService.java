package com.example.ServiceLayer;

import com.example.BusinessLogic.CarController;
import com.example.BusinessLogic.OrderController;
import com.example.model.CarModel;
import com.example.model.OrderModel;
import com.example.model.UserModel;

import java.util.Date;

public class OrderService implements iOrderService{


    public long newOrder(CarModel car, UserModel userModel, Date creationDate, Date expirationDate) {
        OrderController orderController = new OrderController();
        orderController.setCarModel(car);
        orderController.setUserModel(userModel);
        orderController.setCreationDate(creationDate);
        orderController.setExpirationDate(expirationDate);
        return orderController.createOrder();
    }

    public OrderModel getOrder(long orderId) {
        OrderController orderController = new OrderController();
        return orderController.getOrder(orderId);
    }

    public Object[][] getOrderTableObjects() {
        return OrderController.getOrderTableObject();
    }
}
