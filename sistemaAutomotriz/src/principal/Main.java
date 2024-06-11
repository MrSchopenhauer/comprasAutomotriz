package principal;

import java.sql.Connection;

import daos.*;
import funcional.*;
import general.*;
import gui.Login;

import java.util.Date;

import javax.swing.*;


import com.formdev.flatlaf.FlatLightLaf;

public class Main {
    public static void main(String[] args) {
    	
        try {
            // Establecer FlatLaf como la apariencia y sensación
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Crear y mostrar la ventana de inicio de sesión
                Login login = new Login();
                login.setVisible(true);
            }
        });
    	
    	
    	
    	
    	
    	
    /* Connection connection = Conexion.getConnection();
        if (connection != null) {
            // Crear instancia de Tablas y crear tabla persona
            Tabla tablas = new Tabla(connection);
            tablas.crearTablaPersona();

            // Crear instancia de PersonaDAO
            PersonaDAO personaDAO = new PersonaDAO(connection);

            // Crear una instancia de Persona
            Persona nuevaPersona = new Persona(1, "Juan", "Perez", "Garcia", new Date(), null);

            // Agregar la persona a la base de datos
            personaDAO.agregarPersona(nuevaPersona);


            // Aquí puedes agregar más operaciones como obtener, actualizar y eliminar personas

            // Cerrar la conexión al final del programa
            Conexion.closeConnection();
        */}
    }
