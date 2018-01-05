package com.umb.manutromero.calendarapp;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.PreparedStatement;
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



        Button Conectar,Consultar, Guardar;
        Conectar = (Button)findViewById(R.id.conectarDB);
        Consultar = (Button)findViewById(R.id.conectarDB2);
        Guardar = (Button)findViewById(R.id.conectarDB3);

        Consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        Guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        Conectar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                String Datafecha, DataAsunto, DataActividad, ReciveDataFecha, ReciveDataAsunto, ReciveDataActividad;
                EditText editFecha, editAsunto, editActividad;
                TextView PrintData, PrintAsunto, PrintActividad;
                PreparedStatement psInsertar;


                BDConnection BDConnection = new BDConnection(); // se crea la instacia de la clase que tiene el codigo de conexion
                Connection conexion = BDConnection.BDConnection(); //Se ejecuta la funcion de conectar.


                    try{
                        Statement sentencia = conexion.createStatement();
                        ResultSet rs = sentencia.executeQuery("SELECT * FROM Agenda;");

                        editFecha = (EditText)findViewById(R.id.editFecha);
                        editAsunto = (EditText)findViewById(R.id.editAsunto);
                        editActividad = (EditText)findViewById(R.id.editActividad);

                        ReciveDataFecha = editFecha.getText().toString();
                        ReciveDataAsunto = editAsunto.getText().toString();
                        ReciveDataActividad = editActividad.getText().toString();


                        psInsertar = conexion.prepareStatement("INSERT Android_App.Agenda (fecha,asunto,actividad) values (" + "'" + ReciveDataFecha + "'" + "," + "'" + ReciveDataAsunto + "'" + "," + "'" + ReciveDataActividad + "'" + ");");
                        psInsertar.executeUpdate();

                        while (rs.next()){

                            Datafecha = rs.getString(2);
                            DataAsunto = rs.getString(3);
                            DataActividad = rs.getString(4);

                            PrintData = (TextView)findViewById(R.id.ViewFecha);
                            PrintAsunto = (TextView)findViewById(R.id.ViewAsunto);
                            PrintActividad = (TextView)findViewById(R.id.ViewActividad);


                            PrintData.setText(Datafecha);
                            PrintAsunto.setText(DataAsunto);
                            PrintActividad.setText(DataActividad);

                            System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));

                        }

                    }
                    catch (SQLException e){
                        e.printStackTrace();
                    }
            }

        });
    };

}
