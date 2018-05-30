package com.example.BusinessLogic;


import com.example.model.UserModel;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by yuraf_000 on 03.12.2014.
 */
public class UserController {
    String login;
    String password;

    public UserController(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public boolean checkUserLogin() {
        try {
            UserModel userModel = UserModel.findByUsername(login);
            if (userModel != null) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static UserModel findUserByUsername(String login) {
        UserModel newUser = null;
        try {
            newUser = UserModel.findByUsername(login);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newUser;
    }

    public boolean checkAuthorization() {
        try {
            UserModel userModel = UserModel.findByUsername(login);
            if (userModel != null) {
                if (password.equals(userModel.getPassword())) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateUser() {
        UserModel userModel = null;
        try {
            userModel = UserModel.findByUsername(login);
            if (userModel != null) {
                userModel.setPassword(password);
                userModel.update();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean newUser() {
        System.out.println("login = " + login + ", password = " + password);
        if (!checkUserLogin()) {
            UserModel userModel = new UserModel(login, password);
            if (!userModel.isSaved())
                try {
                    userModel.insert();
                    return true;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return false;
    }


    public boolean deleteUser() {
        try {
            UserModel user = UserModel.findByUsername(login);
            if (user != null) {
                user.delete();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<UserModel> getUserList(){
        try {
            return UserModel.getUserList();
        }catch (Exception ignorred){}
        return null;
    }

}
