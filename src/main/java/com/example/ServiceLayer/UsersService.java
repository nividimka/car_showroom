package com.example.ServiceLayer;

import com.example.BusinessLogic.UserController;
import com.example.model.CategoryModel;
import com.example.model.UserModel;

import java.util.List;

/**
 * Created by yuraf_000 on 25.12.2014.
 */
public class UsersService {
    private UserModel mainUser = null;

    public boolean newUser(String login, String password) {
        UserController userWorker = new UserController(login,password);
        return userWorker.newUser();
    }



    public Object login(String login, String password) {
        UserController userWorker = new UserController(login,password);
        if (userWorker.checkAuthorization()) {
            return UserController.findUserByUsername(login);
        }
        return null;
    }




    public void setCurrentUser(Object user) {
        mainUser = (UserModel) user;
    }

    public UserModel getMainUser() {
        return mainUser;
    }

    public String getCurrentUserLogin() { return mainUser.getUsername(); }


    public void close() {
    }

    public List<UserModel> getUserList() {
        return UserController.getUserList();
    }
}
