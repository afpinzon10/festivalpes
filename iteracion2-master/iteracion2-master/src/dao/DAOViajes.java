package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import vos.Avion;
import vos.Viaje;
import vos.Video;

public class DAOViajes {

	private ArrayList<Object> recursos;

	private Connection conn;

	public DAOViajes ()
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


	public void addViajes(Viaje viaje) throws SQLException, Exception {

		String sql = "INSERT INTO ISIS2304B261620.VIAJES VALUES (";
		sql += viaje.getIata() + ",'";
		sql += viaje.getFecha()+ "',";
		sql += viaje.getModelo()+ "',";
		sql += viaje.getNumeroSerie()+ "',";
		sql += viaje.getTipo()+ "',";
		sql += viaje.getCapacidadDisponible()+ "',";
		sql += viaje.getSillasEconomicasDisponibles()+ "',";
		sql += viaje.getSillasEjecutivasdisponibles() + ")";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}


	public void updateViaje(Avion avion, String id, Date fecha) throws SQLException, Exception {

		String sql = "UPDATE ISIS2304B261620.VIAJES SET ";
		sql += "MODELO_AVION='" + avion.getModelo() + "',";
		sql += "NUMERO_SERIE_AVION=" + avion.getNumeroSerie();
		sql += "CAPACIDAD=" + avion.getCapacidad();
		sql += "SILLAS_ECONOMICAS_DISPONIBLES=" + avion.getSillasEconomicas();
		sql += "SILLAS_EJECUTIVAS_DISPONIBLES=" + avion.getSillasEjecutivas();
		sql += " WHERE IATA = '"+id+"' AND FECHA='"+fecha+"'"  ;

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	public Viaje buscarViaje(String iata, Date fecha) throws NumberFormatException, SQLException
	{
		Viaje respuesta = null;
		String sql = "SELECT * FROM TABLA.VIAJE WHERE IATA='" + iata + "' AND FECHA ='"+ fecha +"'";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			String modelo = rs.getString("MODELO_AVION");
			int numeroSerie = Integer.parseInt(rs.getString("NUMERO_SERIE_AVION"));
			String tipo = rs.getString("TIPO");
			int capacidad = Integer.parseInt(rs.getString("CAPACIDAD_DISPONIBLE"));
			int economicas = Integer.parseInt(rs.getString("SILLAS_ECONOMICAS_DISPONIBLE"));
			int ejecutivas = Integer.parseInt(rs.getString("SILLAS_EJECTUVIAS_DISPONIBLE"));
			boolean acabado = rs.getBoolean("FINALIZADO");
			respuesta = new Viaje(tipo, numeroSerie, modelo, capacidad, economicas, ejecutivas, fecha, iata, acabado);
		}

		return respuesta;
	}

	public ArrayList<Viaje> darViajes() throws NumberFormatException, SQLException
	{
		ArrayList<Viaje> viajes = new ArrayList<Viaje>();
		String sql = "SELECT * FROM VIAJES";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			String IATA = (String)rs.getString("IATA");
			Date fecha = (Date) rs.getDate("FECHA");	
			String modelo = (String) rs.getString("MODELO_AERONAVE");
			int numeroSerie = (int) rs.getInt("NUMERO_SERIE_AERONAVE");
			String tipo = (String) rs.getString("TIPO");
			int capacidad = (int) rs.getInt("CAPACIDAD_DISPONIBLE");
			int economicas = (int) rs.getInt("SILLAS_ECONOMICAS_DISPONIBLES");
			int ejecutivas = (int) rs.getInt("SILLAS_EJECUTIVAS_DISPONIBLES");
			boolean finalizado = rs.getBoolean("FINALIZADO");
			viajes.add(new Viaje(tipo, numeroSerie, modelo, capacidad, economicas, ejecutivas, fecha, IATA, finalizado));
		}

		return viajes;
	}



	/**
	 * R10
	 * @param idUsuario
	 * @param iata
	 * @param fechaViaje
	 * @throws Exception 
	 */
	public void finalizarViaje(String idUsuario, String iata, Date fechaViaje) throws Exception {
		//Comprobar que el usuario opere el aeropuerto dado
		String sql = "SELECT DESTINO FROM VUELOS WHERE IATA = '"+iata+"'";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		String destino="a";
		while(rs.next()){
			destino = rs.getString("DESTINO");
		}
		sql= "SELECT * FROM OPERA WHERE ID_USUARIO='"+idUsuario+"' AND AEROPUERTO='"+destino+"'";
		stmt = conn.prepareStatement(sql);
		rs = stmt.executeQuery();
		if(rs.first()==false) throw new Exception("Usuario no tiene permisos para finalizar viaje");
		sql = "UPDATE VIAJES SET FINALIZADO = '1' WHERE IATA='"+iata+"' AND FECHA='"+fechaViaje+"'";
		stmt=conn.prepareStatement(sql);
		stmt.executeUpdate();
	}

}
