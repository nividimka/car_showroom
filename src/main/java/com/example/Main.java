package com.example;

import com.example.APILayer.HttpServer;
import com.example.ui.AuthorizationForm;

import javax.swing.*;
import java.text.ParseException;

public class Main {

    public static void main(String[] args) throws ParseException {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                AuthorizationForm MForm = new AuthorizationForm();

            }
        });
        HttpServer httpServer = new HttpServer();
        new Thread(httpServer).start();
    }
}











