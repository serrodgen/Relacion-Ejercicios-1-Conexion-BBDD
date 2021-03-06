package com.mycompany.relacion_ejercicios1_bbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Ej11 {

    public static void main(String[] args) {
    
        try {
            
            String cadcon = "jdbc:mysql://localhost/empresa?serverTimezone=UTC";
            String user = "root";
            String password = "";

            Connection conexion = DriverManager.getConnection(cadcon, user, password);
            
            Statement sentencia = conexion.createStatement();

            ResultSet resultado = sentencia.executeQuery("SELECT count(numem),sum(salar) FROM empleados GROUP BY numde having numde in(SELECT distinct (numde) FROM empleados WHERE salar > 2500);");
            
            System.out.println("NUMERO DE EMPLEADOS\t\tSUMA DE SALARIOS");
            System.out.println("--------------------\t\t-----------------");
            
            while (resultado.next()) {
                
                String nomem = resultado.getString(1);
                int sum_salario = resultado.getInt(2);
                
                System.out.println(nomem +"\t\t\t\t"+ sum_salario);
            }
            
            resultado.close();
            sentencia.close();
            conexion.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}