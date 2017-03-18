package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Avion;

public class DAOAviones {

private ArrayList<Object> recursos;
	
	private Connection conn;
	
	public DAOAviones ()
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
	
	
	public void addAvion(Avion avion) throws SQLException, Exception {

		String sql = "INSERT INTO ISIS2304B261620.CLIENTE VALUES (";
		sql += avion.getModelo() + ",'";
		sql += avion.getNumeroSerie()+ "',";
		sql += avion.getMarca()+ "',";
		sql += avion.getAnofabricacion()+ "',";
		sql += avion.getTipo()+ "',";
		sql += avion.getCapacidad()+ "',";
		sql += avion.getSillasEconomicas()+ "',";
		sql += avion.getSillasEjecutivas() + ")";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	
	public Avion buscarAvion(int pNumero, String pModelo) throws SQLException, Exception {

		Avion respuesta = null;
		String sql = "SELECT * FROM ISIS2304B261620.AVION WHERE NUMERO_SERIE='" + pNumero + "' AND MODELO ='"+ pModelo +"'";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			String modelo = rs.getString("MODELO");
			int numeroSerie = Integer.parseInt(rs.getString("NUMERO_SERIE"));
			String marca = rs.getString("MARCA");
			int anoFabricacion = Integer.parseInt(rs.getString("ANO_FABRICACION"));
			String tipo = rs.getString("TIPO");
			int capacidad = Integer.parseInt(rs.getString("CAPACIDAD"));
			int economicas = Integer.parseInt(rs.getString("SILLAS_ECONOMICAS"));
			int ejecutivas = Integer.parseInt(rs.getString("SILLAS_EJECTUVIAS"));
			respuesta = new Avion(tipo, ejecutivas, economicas, capacidad, modelo, marca, numeroSerie, anoFabricacion);
		}

		return respuesta;
	}
	
	
}
