package com.umb.manutromero.calendarapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Created by manutromero on 2/01/18.
 */

public class BDConnection {

    public Connection BDConnection() {

        System.out.println("-------- MySQL JDBC Connection Testing ------------");

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Dónde está tu controlador MySQL JDBC?");
            e.printStackTrace();

        }

        System.out.println("MySQL JDBC Driver Registrado!");
        Connection connection = null;

        try {
            connection = DriverManager
                    .getConnection("jdbc:mysql://192.168.1.170:3306/Android_App","android", "");

        } catch (SQLException e) {
            System.out.println("¡La conexión falló! Verificar consola de salida");
            e.printStackTrace();
        }

        if (connection != null) {
            System.out.println("¡Lo lograste, toma el control de tu base de datos ahora!!");
            return connection;
        } else {
            System.out.println("¡Fallo al hacer la conexión!");
        }
        return connection;
    };
}
