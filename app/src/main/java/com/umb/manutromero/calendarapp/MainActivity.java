package com.umb.manutromero.calendarapp;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        Button mick;
        mick = (Button)findViewById(R.id.conectarDB);

        mick.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                BDConnection BDConnection = new BDConnection(); // se crea la instacia de la clase que tiene el codigo de conexion
                Connection conexion = BDConnection.BDConnection(); //Se ejecuta la funcion de conectar.


                    try{
                        Statement sentencia = conexion.createStatement();
                        ResultSet rs = sentencia.executeQuery("SELECT * FROM Agenda;");
                        while (rs.next()){
                            System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
                        }
                    }
                    catch (SQLException e){
                        e.printStackTrace();

                    }



            }


        });
    };
}
