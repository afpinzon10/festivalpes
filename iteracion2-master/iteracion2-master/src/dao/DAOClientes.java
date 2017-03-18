package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Cliente;

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
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();

		
		String sql = "SELECT * FROM CLIENTES";
		System.out.println(sql);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			String nombre = rs.getString("NOMBRE");
			String tipo = rs.getString("TIPO_ID");
			String id = rs.getString("ID");
			int remitente = Integer.parseInt(rs.getString("REMITENTE"));
			int viajero = Integer.parseInt(rs.getString("VIAJERO"));
			int frecuente = Integer.parseInt(rs.getString("FRECUENTE"));
			String creador = rs.getString("CREADOR");
			clientes.add(new Cliente(creador, remitente, viajero, tipo, id, frecuente, nombre));
		}
		return clientes;
	}
	
	public ArrayList<Cliente> darClientesUsuario(String pCreador) throws SQLException, Exception {
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();

		String sql = "SELECT * FROM ISIS2304B261620.CLIENTES WHERE CREADOR ='" + pCreador + "'";

		System.out.println(sql);
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			String nombre = rs.getString("NOMBRE");
			String tipo = rs.getString("TIPO_ID");
			String id = rs.getString("ID");
			int remitente = Integer.parseInt(rs.getString("REMITENTE"));
			int viajero = Integer.parseInt(rs.getString("VIAJERO"));
			int frecuente = Integer.parseInt(rs.getString("FRECUENTE"));
			String creador = rs.getString("CREADOR");
			clientes.add(new Cliente(creador, remitente, viajero, tipo, id, frecuente, nombre));
		}
		return clientes;
	}
	
	
	public void addCliente(Cliente cliente) throws SQLException, Exception {

		String sql = "INSERT INTO TABLA.CLIENTE VALUES (";
		sql += cliente.getTipoId() + ",'";
		sql += cliente.getId()+ "',";
		sql += cliente.getNombre()+ "',";
		sql += cliente.isRemitente()+ "',";
		sql += cliente.isViajero()+ "',";
		sql += cliente.isFrecuente() + ")";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
		
		
	
	
	
	
}
