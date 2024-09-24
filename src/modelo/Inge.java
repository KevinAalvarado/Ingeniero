/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import vista.jIngeniero;

public class Inge {
    private String UUID_Ingeniero;
    private String Nombre_Ingeniero;
    private int Edad_Ingeniero;
    private double Peso_Ingeniero;
    private String Correo_Ingeniero;

    
    public String getUUID_Ingeniero() {
        return UUID_Ingeniero;
    }

    public void setUUID_Ingeniero(String UUID_Ingeniero) {
        this.UUID_Ingeniero = UUID_Ingeniero;
    }
    
    public String getNombre_Ingeniero() {
        return Nombre_Ingeniero;
    }

    public void setNombre_Ingeniero(String Nombre_Ingeniero) {
        this.Nombre_Ingeniero = Nombre_Ingeniero;
    }

    public int getEdad_Ingeniero() {
        return Edad_Ingeniero;
    }

    public void setEdad_Ingeniero(int Edad_Ingeniero) {
        this.Edad_Ingeniero = Edad_Ingeniero;
    }

    public double getPeso_Ingeniero() {
        return Peso_Ingeniero;
    }

    public void setPeso_Ingeniero(double Peso_Ingeniero) {
        this.Peso_Ingeniero = Peso_Ingeniero;
    }

    public String getCorreo_Ingeniero() {
        return Correo_Ingeniero;
    }

    public void setCorreo_Ingeniero(String Correo_Ingeniero) {
        this.Correo_Ingeniero = Correo_Ingeniero;
    }
    
    
    public void Agregar() {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        try {
            //Creamos el PreparedStatement que ejecutará la Query
            PreparedStatement addIngeniero = conexion.prepareStatement("INSERT INTO tbIngeniero (UUID_Ingeniero, Nombre_Ingeniero, Edad_Ingeniero, Peso_Ingeniero, Correo_Ingeniero) VALUES (?, ?, ?, ?, ?)");
            //Establecer valores de la consulta SQL
            addIngeniero.setString(1, UUID.randomUUID().toString());
            addIngeniero.setString(2, getNombre_Ingeniero());
            addIngeniero.setInt(3, getEdad_Ingeniero());
            addIngeniero.setDouble(4, getPeso_Ingeniero());
            addIngeniero.setString(5, getCorreo_Ingeniero());

            //Ejecutar la consulta
            addIngeniero.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("este es el error en el modelo:metodo agregar " + ex);
        }
    }
    
    
    public void Mostrar(JTable tabla) {
        //Creamos una variable de la clase de conexion
        Connection conexion = ClaseConexion.getConexion();
        //Definimos el modelo de la tabla
        DefaultTableModel modeloDeDatos = new DefaultTableModel();
        
        modeloDeDatos.setColumnIdentifiers(new Object[]{"UUID_Ingeniero", "Nombre_Ingeniero", "Edad_Ingeniero", "Peso_Ingeniero", "Correo_Ingeniero"});
        try {
            //Creamos un Statement
            Statement statement = conexion.createStatement();
            //Ejecutamos el Statement con la consulta y lo asignamos a una variable de tipo ResultSet
            ResultSet rs = statement.executeQuery("SELECT * FROM tbIngeniero");
            //Recorremos el ResultSet
            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modeloDeDatos.addRow(new Object[]{rs.getString("UUID_Ingeniero"), 
                    rs.getString("Nombre_Ingeniero"), 
                    rs.getInt("Edad_Ingeniero"), 
                    rs.getDouble("Peso_Ingeniero"),
                    rs.getString("Correo_Ingeniero")});
            }
            //Asignamos el nuevo modelo lleno a la tabla
            tabla.setModel(modeloDeDatos);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo mostrar " + e);
        }
    }
    
    public void Eliminar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();

        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        //Obtenemos el id de la fila seleccionada
        String miId = tabla.getValueAt(filaSeleccionada, 0).toString();
        
        //borramos 
        try {
            PreparedStatement deleteIngeniero = conexion.prepareStatement("delete from tbIngeniero where UUID_Ingeniero = ?");
            deleteIngeniero.setString(1, miId);
            deleteIngeniero.executeUpdate();
        } catch (Exception e) {
            System.out.println("este es el error metodo de eliminar" + e);
        }
    }
    
    public void cargarDatosTabla(jIngeniero vista) {
    // Obtén la fila seleccionada 
        int filaSeleccionada = vista.jtbIngenieros.getSelectedRow();

        // Debemos asegurarnos que haya una fila seleccionada antes de acceder a sus valores
        if (filaSeleccionada != -1) {
            String UUIDDeTb = vista.jtbIngenieros.getValueAt(filaSeleccionada, 0).toString();
            String NombreDeTB = vista.jtbIngenieros.getValueAt(filaSeleccionada, 1).toString();
            String EdadDeTb = vista.jtbIngenieros.getValueAt(filaSeleccionada, 2).toString();
            String PesoDeTB = vista.jtbIngenieros.getValueAt(filaSeleccionada, 3).toString();
            String CorreoDeTB = vista.jtbIngenieros.getValueAt(filaSeleccionada, 4).toString();


            // Establece los valores en los campos de texto
            vista.txtNombre.setText(NombreDeTB);
            vista.txtEdad.setText(EdadDeTb);
            vista.txtPeso.setText(PesoDeTB);
            vista.txtCorreo.setText(CorreoDeTB);
        }
    }
    
    public void Actualizar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();

        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada != -1) {
            //Obtenemos el id de la fila seleccionada
            String miUUId = tabla.getValueAt(filaSeleccionada, 0).toString();
            try { 
                //Ejecutamos la Query
                PreparedStatement updateUser = conexion.prepareStatement("update tbIngeniero set Nombre_Ingeniero = ?, Edad_Ingeniero = ?, Peso_Ingeniero = ?, Correo_Ingeniero = ? where UUID_Ingeniero = ?");

                updateUser.setString(1, getNombre_Ingeniero());
                updateUser.setInt(2, getEdad_Ingeniero());
                updateUser.setDouble(3, getPeso_Ingeniero());
                updateUser.setString(4, getCorreo_Ingeniero());
                updateUser.setString(5, miUUId);
                updateUser.executeUpdate();
            } catch (Exception e) {
                System.out.println("este es el error en el metodo de actualizar" + e);
            }
        } else {
            System.out.println("no");
        }
    }
    
    public void limpiar(jIngeniero vista) {
        vista.txtNombre.setText("");
        vista.txtEdad.setText("");
        vista.txtPeso.setText("");
        vista.txtCorreo.setText("");
        }
        
    }
    
    
