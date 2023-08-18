package com.example.navbotdialog;

public class APIUtils {
    //Cambiar ip a la de la laptop considerando que el servidor y el dispositivo esten en la misma red
    private static final String BASE_URL = "http://192.168.0.9:3000/";

    public static String getFullUrl(String route) {
        return BASE_URL + route;
    }
}