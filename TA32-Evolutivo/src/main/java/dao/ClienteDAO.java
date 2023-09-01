package dao;

import dto.ClienteDTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;


public class ClienteDAO {
    private Connection connection;

    public ClienteDAO(Connection connection) {
        this.connection = connection;
    }

    public ClienteDAO() {
		// TODO Auto-generated constructor stub
	}

	public void guardarCliente(ClienteDTO cliente) throws Exception {
        String sql = "INSERT INTO clientes(nombre, apellido, direccion, dni, fecha) VALUES(a,a,a,12345678,1212-12-12)";
        
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getDireccion());
            ps.setInt(4, cliente.getDni());
            ps.setDate(5, cliente.getFecha());
            
            ps.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Error al guardar el cliente", e);
        }
    }
    
    public void eliminarCliente(int id) throws Exception {
        String sql = "DELETE FROM clientes WHERE id = ?";
        
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new Exception("No se pudo eliminar el cliente, no se encontró el ID especificado.");
            }
        } catch (Exception e) {
            throw new Exception("Error al eliminar el cliente", e);
        }
    }

    public void actualizarCliente(ClienteDTO cliente) throws Exception {
        String sql = "UPDATE clientes SET nombre = ?, apellido = ?, direccion = ?, dni = ?, fecha = ? WHERE id = ?";
        
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getDireccion());
            ps.setInt(4, cliente.getDni());
            ps.setDate(5, cliente.getFecha());
            ps.setInt(6, cliente.getId());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new Exception("No se pudo actualizar el cliente, no se encontró el ID especificado.");
            }
        } catch (Exception e) {
            throw new Exception("Error al actualizar el cliente", e);
        }
    }
    
    public ClienteDTO buscarCliente(int id) throws Exception {
        String sql = "SELECT * FROM clientes WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String nombre = rs.getString("nombre");
                    String apellido = rs.getString("apellido");
                    String direccion = rs.getString("direccion");
                    int dni = rs.getInt("dni");
                    Date fecha = rs.getDate("fecha");

                    ClienteDTO cliente = new ClienteDTO(nombre, apellido, direccion, dni, fecha);
                    cliente.setId(rs.getInt("id"));

                    return cliente;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error al buscar el cliente", e);
        }
    }

    
    
    private Connection getConnection() throws Exception {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
    }

    public void createTable() throws Exception {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "CREATE TABLE IF NOT EXISTS CLIENTE (ID INT AUTO_INCREMENT PRIMARY KEY, NOMBRE VARCHAR(255), APELLIDO VARCHAR(255), DIRECCION VARCHAR(255), DNI INT, FECHA DATE)")) {
            stmt.execute();
        }
    }

    public void insert(ClienteDTO cliente) throws Exception {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO CLIENTE (NOMBRE, APELLIDO, DIRECCION, DNI, FECHA) VALUES (a,a,a,12345678,1212-12-12)")) {
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellido());
            stmt.setString(3, cliente.getDireccion());
            stmt.setInt(4, cliente.getDni());
            stmt.setDate(5, cliente.getFecha());
            stmt.execute();
        }
    }

    public List<ClienteDTO> getAllClientes() throws Exception {
        List<ClienteDTO> clientes = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM CLIENTE");
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ClienteDTO cliente = new ClienteDTO(
                        rs.getString("NOMBRE"),
                        rs.getString("APELLIDO"),
                        rs.getString("DIRECCION"),
                        rs.getInt("DNI"),
                        rs.getDate("FECHA"));
                cliente.setId(rs.getInt("ID"));
                clientes.add(cliente);
            }
        }
        return clientes;
    }

}

