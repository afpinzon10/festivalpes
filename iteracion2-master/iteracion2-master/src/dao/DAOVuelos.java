package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.Date;

import vos.Reserva;
import vos.Viaje;
import vos.Vuelo;

public class DAOVuelos {

	private ArrayList<Object> recursos;
	
	private Connection conn;
	
	public DAOVuelos ()
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
	
	
	public ArrayList<Vuelo> darVuelos() throws NumberFormatException, SQLException
	{
		ArrayList<Vuelo> vuelos = new ArrayList<Vuelo>();
		String sql = "SELECT * FROM ISIS2304B261620.VUELOS";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			String aerolinea = rs.getString("AEROLINEA");
			String origen = rs.getString("ORIGEN");
			String destino = rs.getString("DESTINO");
			String tipo = rs.getString("TIPO");
			String frecuencia = rs.getString("FRECUENCIA");
			String llegada = rs.getString("HORA_LLEGADA");
			String salida = rs.getString("HORA_SALIDA");
			double distancia = Double.parseDouble(rs.getString("DISTANCIA"));
			String duracion = rs.getString("DURACION");
			String IATA = rs.getString("IATA");
			int costoDensidad = rs.getInt("COSTO_DENSIDAD");
			int costoEconomicas = rs.getInt("COSTO_ECONOMICA");
			int costoEjecutivas = rs.getInt("COSTO_EJECUTIVA");
			vuelos.add(new Vuelo(costoEconomicas, costoEjecutivas, costoDensidad, IATA, frecuencia, llegada, salida, distancia, duracion, origen, destino));
		}

		return vuelos;
	}
	
	
	
	public Vuelo buscarVuelo(String IATA) throws NumberFormatException, SQLException
	{
		Vuelo respuesta = null;
		String sql = "SELECT * FROM ISIS2304B261620.VUELOS WHERE IATA='" + IATA+"'";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			String aerolinea = rs.getString("AEROLINEA");
			String origen = rs.getString("ORIGEN");
			String destino = rs.getString("DESTINO");
			String tipo = rs.getString("TIPO");
			String frecuencia = rs.getString("FRECUENCIA");
			String llegada = rs.getString("HORA_LLEGADA");
			String salida = rs.getString("HORA_SALIDA");
			double distancia = Double.parseDouble(rs.getString("DISTANCIA"));
			String duracion = rs.getString("DURACION");
			int costoDensidad = Integer.parseInt(rs.getString("COSTO_DENSIDAD"));
			int costoEconomicas = Integer.parseInt(rs.getString("COSTO_ECONOMICA"));
			int costoEjecutivas = Integer.parseInt(rs.getString("COSTO_EJECUTIVA"));
			respuesta = new Vuelo(costoEconomicas, costoEjecutivas, costoDensidad, IATA, frecuencia, llegada, salida, distancia, duracion, origen, destino);
		}

		return respuesta;
	}
	
	
	public ArrayList<Vuelo> VuelosSalidaArribos(String IATA) throws NumberFormatException, SQLException
	{
		ArrayList<Vuelo> respuesta = new ArrayList<Vuelo>();
		String sql = "SELECT * FROM ISIS2304B261620.VIAJE WHERE ORIGEN='" + IATA+"' OR DESTINO='"+IATA+"'";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			String aerolinea = rs.getString("AEROLINEA");
			String origen = rs.getString("ORIGEN");
			String destino = rs.getString("DESTINO");
			String tipo = rs.getString("TIPO");
			String frecuencia = rs.getString("FRECUENCIA");
			String llegada = rs.getString("HORA_LLEGADA");
			String salida = rs.getString("HORA_SALIDA");
			double distancia = Double.parseDouble(rs.getString("DISTANCIA"));
			String duracion = rs.getString("DURACION");
			int costoDensidad = Integer.parseInt(rs.getString("COSTO_DENSIDAD"));
			int costoEconomicas = Integer.parseInt(rs.getString("COSTO_ECONOMICA"));
			int costoEjecutivas = Integer.parseInt(rs.getString("COSTO_EJECUTIVA"));
			respuesta.add(new Vuelo(costoEconomicas, costoEjecutivas, costoDensidad, IATA, frecuencia, llegada, salida, distancia, duracion, origen, destino));
		}

		return respuesta;
	}
	
	public void eliminarVuelo(String IATA) throws SQLException
	{
		Savepoint s = conn.setSavepoint();
		String sql = "DELETE FROM ISIS2304B261620.RESERVAS WHERE NUMERO_RESERVA ='" + IATA + "'";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	public void agregarVuelos(String sql) throws SQLException
	{
		try
		{
		System.out.println("paso");
		conn.setAutoCommit(true);
		System.out.println("autocommit");
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		System.out.println("sentencia preparada");
		preparedStatement.executeQuery();
		System.out.println("agrego");
		}
		catch (SQLException e)
		{
			throw new SQLException("Error en "+sql);
		}
	}
	
	
	
	
}
