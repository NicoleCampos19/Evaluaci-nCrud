package Controlador;

import Vista.frmInformacionCocinero; 
import Modelo.tbCocinero;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;


public class CtrlCocinero implements MouseListener, KeyListener{
    
    private frmInformacionCocinero Vista;
    private tbCocinero Modelo;
    
    public CtrlCocinero (tbCocinero Modelo, frmInformacionCocinero Vista){
     this.Modelo = Modelo;
     this.Vista = Vista;
     
     
     Vista.btnGuardar.addMouseListener(this);
     Vista.btnEliminar.addMouseListener(this);
     Vista.btnActualizar.addMouseListener(this);
     Vista.btnLimpiar.addMouseListener(this);
     Vista.tbCocinero.addMouseListener(this);
     Modelo.Mostrar(Vista.tbCocinero);

     Vista.txtNombre.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (Vista.txtNombre.getText().length() >= 50 || 
                    !Character.isLetter(e.getKeyChar()) && e.getKeyChar() != ' ') {
                    e.consume();  // Evita más entradas si supera 50 caracteres o no es letra
                }
            }
        });
    }

    @Override
    public void mouseClicked(MouseEvent e) {
         if (e.getSource() == Vista.btnGuardar) {
            // Validar que los campos no estén vacíos
            if (Vista.txtNombre.getText().isEmpty() || Vista.txtEdad.getText().isEmpty() || 
                Vista.txtPeso.getText().isEmpty() || Vista.txtCorreo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(Vista, "Todos los campos deben estar llenos", "Error", JOptionPane.ERROR_MESSAGE);
                return;  // No se continúa si hay campos vacíos
            }

            // Validar formato de correo
            if (!Vista.txtCorreo.getText().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                JOptionPane.showMessageDialog(Vista, "El correo no tiene un formato válido", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Captura de posibles errores en la conversión de los valores
            try {
                Modelo.setNombre_Cocinero(Vista.txtNombre.getText());
                Modelo.setEdad_Cocinero(Integer.parseInt(Vista.txtEdad.getText()));
                Modelo.setPeso_Cocinero(Double.parseDouble(Vista.txtPeso.getText()));
                Modelo.setCorreo_Cocinero(Vista.txtCorreo.getText());
                Modelo.Guardar();
                Modelo.Mostrar(Vista.tbCocinero);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(Vista, "Error al convertir los valores numéricos", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(Vista, "Ocurrió un error al guardar", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
                
        if (e.getSource() == Vista.btnEliminar) {
            Modelo.Eliminar(Vista.tbCocinero);
            Modelo.Mostrar(Vista.tbCocinero);
        }
        
        if (e.getSource() == Vista.tbCocinero) {
            Modelo.cargarDatosTabla(Vista);
        }
                
        if (e.getSource() == Vista.btnActualizar) {
            if (Vista.txtNombre.getText().isEmpty() || Vista.txtEdad.getText().isEmpty() || 
                Vista.txtPeso.getText().isEmpty() || Vista.txtCorreo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(Vista, "Todos los campos deben estar llenos", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!Vista.txtCorreo.getText().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                JOptionPane.showMessageDialog(Vista, "El correo no tiene un formato válido", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                Modelo.setNombre_Cocinero(Vista.txtNombre.getText());
                Modelo.setEdad_Cocinero(Integer.parseInt(Vista.txtEdad.getText()));
                Modelo.setPeso_Cocinero(Double.parseDouble(Vista.txtPeso.getText()));
                Modelo.setCorreo_Cocinero(Vista.txtCorreo.getText());
                Modelo.Actualizar(Vista.tbCocinero);
                Modelo.Mostrar(Vista.tbCocinero);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(Vista, "Error al convertir los valores numéricos", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(Vista, "Ocurrió un error al actualizar", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
         if (e.getSource() == Vista.btnLimpiar) {
            Modelo.limpiar(Vista);
        }

        if (e.getSource() == Vista.tbCocinero) {
            Modelo.cargarDatosTabla(Vista);
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

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    
}
