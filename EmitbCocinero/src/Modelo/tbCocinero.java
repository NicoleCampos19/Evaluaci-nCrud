package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.UUID;
import javax.swing.JTable;
import Vista.frmInformacionCocinero;

/**
 *
 * @author emily
 */
public class tbCocinero {
    
    private String Nombre_Cocinero;
    private int Edad_Cocinero;
    private double Peso_Cocinero;
    private String Correo_Cocinero;

    public String getNombre_Cocinero() {
        return Nombre_Cocinero;
    }

    public void setNombre_Cocinero(String Nombre_Cocinero) {
        this.Nombre_Cocinero = Nombre_Cocinero;
    }

    public int getEdad_Cocinero() {
        return Edad_Cocinero;
    }

    public void setEdad_Cocinero(int Edad_Cocinero) {
        this.Edad_Cocinero = Edad_Cocinero;
    }

    public double getPeso_Cocinero() {
        return Peso_Cocinero;
    }

    public void setPeso_Cocinero(double Peso_Cocinero) {
        this.Peso_Cocinero = Peso_Cocinero;
    }

    public String getCorreo_Cocinero() {
        return Correo_Cocinero;
    }

    public void setCorreo_Cocinero(String Correo_Cocinero) {
        this.Correo_Cocinero = Correo_Cocinero;
    }
    
    public void Guardar(){
    Connection conexion = ClassConexion.getConexion();
      try {
            //Creamos el PreparedStatement que ejecutará la Query
            PreparedStatement addCocinero = conexion.prepareStatement("INSERT INTO tbCocinero(UUID_Cocinero, Nombre_Cocinero, Edad_Cocinero, Peso_Cocinero, Correo_Cocinero) VALUES (?, ?, ?, ?, ?)");
            //Establecer valores de la consulta SQL
            addCocinero.setString(1, UUID.randomUUID().toString());
            addCocinero.setString(2, getNombre_Cocinero());
            addCocinero.setInt(3, getEdad_Cocinero());
            addCocinero.setDouble(4, getPeso_Cocinero());
            addCocinero.setString(5, getCorreo_Cocinero());
            //Ejecutar la consulta
            addCocinero.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Este es el error en el modelo: método guardar " + ex);
        }
    }
    
    public void Mostrar(JTable tabla) {
        //Creamos una variable de la clase de conexion
        Connection conexion = ClassConexion.getConexion();
        //Definimos el modelo de la tabla
        DefaultTableModel modeloDeDatos = new DefaultTableModel();
        modeloDeDatos.setColumnIdentifiers(new Object[]{"UUID_Cocinero", "Nombre", "Edad", "Peso", "Correo Electrónico"});
        try {
            //Creamos un Statement
            Statement statement = conexion.createStatement();
            //Ejecutamos el Statement con la consulta y lo asignamos a una variable de tipo ResultSet
            ResultSet rs = statement.executeQuery("SELECT * FROM tbCocinero");
            //Recorremos el ResultSet
            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modeloDeDatos.addRow(new Object[]{
                    rs.getString("UUID_Cocinero"), 
                    rs.getString("Nombre_Cocinero"), 
                    rs.getInt("Edad_Cocinero"), 
                    rs.getString("Peso_Cocinero"),
                    rs.getString("Correo_Cocinero")
                });
            }
            //Asignamos el nuevo modelo lleno a la tabla
            tabla.setModel(modeloDeDatos);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo: método mostrar " + e);
        }
    }
    
    public void Eliminar(JTable tabla){
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClassConexion.getConexion();
        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        //Obtenemos el id de la fila seleccionada
        String miId = tabla.getValueAt(filaSeleccionada, 0).toString();
        //borramos 
        try {
            PreparedStatement deleteCocinero = conexion.prepareStatement("DELETE FROM tbCocinero where UUID_Cocinero = ?");
            deleteCocinero.setString(1, miId);
            deleteCocinero.executeUpdate();

        } catch (Exception e) {
            System.out.println("Este es el error metodo de eliminar: método eliminar" + e);
        }

    }

     public void Actualizar(JTable tabla){
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClassConexion.getConexion();
        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada != -1) {
            //Obtenemos el id de la fila seleccionada
            String miUUId = tabla.getValueAt(filaSeleccionada, 0).toString();
            try { 
                //Ejecutamos la Query
                PreparedStatement updateUser = conexion.prepareStatement("UPDATE tbCocinero set Nombre_Cocinero = ?, Edad_Cocinero = ?, Peso_Cocinero = ?, Correo_Cocinero = ? WHERE UUID_Cocinero = ?");
 
                updateUser.setString(1, getNombre_Cocinero());
                updateUser.setInt(2, getEdad_Cocinero());
                updateUser.setDouble(3, getPeso_Cocinero());
                updateUser.setString(4, getCorreo_Cocinero());
                updateUser.setString(5, miUUId);
                updateUser.executeUpdate();
            } catch (Exception e) {
                System.out.println("Este es el error en el método de actualizar" + e);
            }
        } else {
            System.out.println("no");
        }
    }
  
     public void limpiar (frmInformacionCocinero Vista) {
        Vista.txtNombre.setText("");
        Vista.txtEdad.setText("");
        Vista.txtPeso.setText(""); 
        Vista.txtCorreo.setText("");
    }

       public void cargarDatosTabla(frmInformacionCocinero Vista) {
        // Obtén la fila seleccionada 
        int filaSeleccionada = Vista.tbCocinero.getSelectedRow();
 
        // Debemos asegurarnos que haya una fila seleccionada antes de acceder a sus valores
        if (filaSeleccionada != -1) {
            String UUIDDeTb = Vista.tbCocinero.getValueAt(filaSeleccionada, 0).toString();
            String NombreDeTB = Vista.tbCocinero.getValueAt(filaSeleccionada, 1).toString();
            String EdadDeTb = Vista.tbCocinero.getValueAt(filaSeleccionada, 2).toString();
            String PesoDeTB = Vista.tbCocinero.getValueAt(filaSeleccionada, 3).toString();
            String CorreoDeTB = Vista.tbCocinero.getValueAt(filaSeleccionada, 4).toString();
 
            // Establece los valores en los campos de texto
            Vista.txtNombre.setText(NombreDeTB);
            Vista.txtEdad.setText(EdadDeTb);
            Vista.txtPeso.setText(PesoDeTB);
            Vista.txtCorreo.setText(CorreoDeTB);
        }
    }
}
