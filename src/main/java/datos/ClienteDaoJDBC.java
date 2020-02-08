
package datos;


import dominio.Cliente;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ClienteDaoJDBC {
    private static final String SQL_SELECT = "SELECT id_cliente, nombre, apellido, email, telefono, saldo "
            + "FROM cliente";
    
    private static final String SQL_SELECT_BY_ID = "SELECT id_cliente, nombre, apellido, email, telefono, saldo "
            + "FROM cliente WHERE id_cliente= ?";
    
    private static final String SQL_INSERT = "INSERT INTO cliente(nombre, apellido, email, telefono, saldo) "
            + " VALUES(?, ? ,? ,? ,?)";
    
    private static final String SQL_UPDATE = "PDATE cliente "
            + "SET nombre=?, apellido=?, email??, telefono=?, saldo=?, WHERE id_cliente=?";
    
    private static final String SQL_DELETE = "DELETE FROM cliente WHERE id_cliente = ?";
    
    //Listar
    public List<Cliente> listar(){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cliente cliente = null;
        
        //Arreglo de clientes
        List<Cliente> clientes = new ArrayList<>();
        try {
            conn =  Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while(rs.next()){
                int idCliente = rs.getInt("id_cliente");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email =  rs.getString("email");
                String telefono =  rs.getString("telefono");
                double saldo =  rs.getDouble("saldo");
                
                //Constructor del Cliente
                cliente = new Cliente(idCliente, nombre, apellido, email, telefono, saldo);
                clientes.add(cliente);//Adicionar cliente a la lista
            }
        } catch (SQLException ex) {
           ex.printStackTrace(System.out);
        }
        finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return clientes;
        
    }
}
