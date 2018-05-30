package com.example.ServiceLayer;

import com.example.model.CarModel;
import com.example.model.OrderModel;
import com.example.model.UserModel;

import java.util.Date;

public interface iOrderService {
    public long newOrder(CarModel car, UserModel userModel, Date creationDate, Date expirationDate);

    public OrderModel getOrder(long orderId);

    public Object[][] getOrderTableObjects();
}
