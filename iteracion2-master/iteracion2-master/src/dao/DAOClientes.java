package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Cliente;
import vos.Usuario;

public class DAOClientes {
	
private ArrayList<Object> recursos;
	
	private Connection conn;
	
	public DAOClientes ()
	{
		recursos = new ArrayList<Object>();
	}
	
	public void cerrarRecursos() 
	{
		for(Object ob : recursos){
			if(ob instanceof PreparedStatement)
				try {
					((PreparedStatement) ob).close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		}
	}
	
	public void setConn(Connection con){
		this.conn = con;
	}
	
	public ArrayList<Cliente> darClientes() throws SQLException, Exception {
		ArrayList<Cliente> Clientes = new ArrayList<Cliente>();
		String sql = "SELECT * FROM CLIENTE";
		System.out.println(sql);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			int idUsuario = Integer.parseInt(rs.getString("IDUSUARUIO"));
			String nombre = rs.getString("NOMBRE");
			String apellido = rs.getString("APELLIDO");
			String email = rs.getString("EMAIL");
			String identificacion = rs.getString("IDENTIFICACION");
			Clientes.add(new Cliente(null, idUsuario, nombre, apellido, email, identificacion));
		}
		return Clientes;
	}

	public void addCliente(Cliente cliente) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO CLIENTE VALUES (IDUSUARIO, NOMBRE, APELLIDO, EMAIL, IDENTIFICACION)";
		sql += cliente.getIdusuario() + ",'";
		sql += cliente.getNombre() + ",'";
		sql += cliente.getApellido() + ",'";
		sql += cliente.getEmail() + ",'";
		sql += cliente.getIdentificacion() + ")";
		
		System.out.println("SQL stmt    addCliente:" + sql);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		
		
	}
}
