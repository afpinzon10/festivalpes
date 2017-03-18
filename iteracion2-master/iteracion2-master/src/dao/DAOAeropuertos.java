package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Aeropuerto;

public class DAOAeropuertos {

private ArrayList<Object> recursos;
	
	private Connection conn;
	
	public DAOAeropuertos ()
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
	
	
	public ArrayList<Aeropuerto> darAeropuertos() throws SQLException, Exception {
		ArrayList<Aeropuerto> aeropuertos = new ArrayList<Aeropuerto>();
		String sql = "SELECT * FROM ISIS2304B261620.AEROPUERTOS";
		System.out.println(sql);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			String iata = rs.getString("IATA");
			String nombre = rs.getString("NOMBRE");
			String tipo = rs.getString("TIPO");
			String ciudad = rs.getString("CIUDAD");
			String pais = rs.getString("PAIS");
			aeropuertos.add(new Aeropuerto(iata, nombre, tipo, ciudad, pais));
		}
		return aeropuertos;
	}
	
	public Aeropuerto buscarAeropuerto(String IATA) throws SQLException, Exception {
		
		Aeropuerto respuesta = null;
		String sql = "SELECT * FROM ISIS2304B261620.AEROPUERTOS WHERE IATA ='"+IATA+"'";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			String iata = rs.getString("IATA");
			String nombre = rs.getString("NOMBRE");
			String tipo = rs.getString("TIPO");
			String ciudad = rs.getString("CIUDAD");
			String pais = rs.getString("PAIS");
			respuesta =new Aeropuerto(iata, nombre, tipo, ciudad, pais);
		}
		return respuesta;
	}
	
	
	
}
