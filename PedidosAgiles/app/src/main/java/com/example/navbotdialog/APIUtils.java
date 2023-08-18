package com.example.navbotdialog;

public class APIUtils {
    //Cambiar ip a la de la laptop considerando que el servidor y el dispositivo esten en la misma red
<<<<<<< HEAD
    private static final String BASE_URL = "http://192.168.0.9:3000/";
=======
    private static final String BASE_URL = "http://10.0.7.19:3000/";
>>>>>>> 2d0043b74e6e92b60fadc9955a911c9f7ebba6aa

    public static String getFullUrl(String route) {
        return BASE_URL + route;
    }
}