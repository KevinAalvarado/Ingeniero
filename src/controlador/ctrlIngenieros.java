/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import modelo.Inge;
import vista.jIngeniero;

/**
 *
 * @author ricar
 */
public class ctrlIngenieros implements MouseListener{
    
    private jIngeniero vista;
    private Inge modelo;
    
    public ctrlIngenieros(jIngeniero Vista, Inge Modelo){
        this.vista = Vista;
        this.modelo = Modelo;

        Vista.btnAgregar.addMouseListener(  this);
        modelo.Mostrar(vista.jtbIngenieros);
        vista.btnEliminar.addMouseListener(this);
        vista.jtbIngenieros.addMouseListener(this);
        vista.btnActualizar.addMouseListener(this);
        vista.btnLimpiar.addMouseListener(this);
    }
    
    
    @Override
    public void mouseClicked(MouseEvent e) {
      if(e.getSource() == vista.btnAgregar){
          
          modelo.setNombre_Ingeniero(vista.txtNombre.getText());
          modelo.setEdad_Ingeniero( Integer.parseInt(vista.txtEdad.getText()));
          modelo.setPeso_Ingeniero( Double.parseDouble(vista.txtPeso.getText()));
          modelo.setCorreo_Ingeniero(vista.txtCorreo.getText());

          modelo.Agregar();
          modelo.Mostrar(vista.jtbIngenieros);
          System.err.println("Click");
      }
      
        if(e.getSource() == vista.btnEliminar){
            modelo.Eliminar(vista.jtbIngenieros);
            modelo.Mostrar(vista.jtbIngenieros);
            System.out.println("xlixk");
        }
        
        if(e.getSource() == vista.jtbIngenieros){
            modelo.cargarDatosTabla(vista);
        }
        
        if(e.getSource() == vista.btnActualizar){
            modelo.setNombre_Ingeniero(vista.txtNombre.getText());
            modelo.setEdad_Ingeniero(Integer.parseInt(vista.txtEdad.getText()));
            modelo.setPeso_Ingeniero(Double.parseDouble(vista.txtPeso.getText()));
            modelo.setCorreo_Ingeniero(vista.txtCorreo.getText());

            modelo.Actualizar(vista.jtbIngenieros);
            modelo.Mostrar(vista.jtbIngenieros);
        }
        
        if(e.getSource() == vista.btnLimpiar){
            modelo.limpiar(vista);
        }
    }
    
    

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
}
