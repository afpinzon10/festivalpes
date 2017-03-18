package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import vos.*;
import vos.Viaje;

public class DAOConsultas {


	private ArrayList<Object> recursos;

	private Connection conn;
	public DAOConsultas() 
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



	public void RFC7(RFC7y8 pObjeto) throws SQLException
	{
		ArrayList<Viaje> arribosArray = new ArrayList<>();
		ArrayList<Viaje> salidasArray = new ArrayList<>();
		String horaLlegada = pObjeto.getHoraLlegada();
		String horaSalida = pObjeto.getHoraSalida();
		String aeropuerto = pObjeto.getAeropuerto();
		String fechaInicial = pObjeto.getFechaInicial();
		String fechaFinal = pObjeto.getFechaFinal();
		String aerolinea = pObjeto.getAerolinea();
		String arribos = "SELECT * FROM  VUELOS a JOIN VIAJES b on a.iata=b.iata  WHERE a.DESTINO='"+aeropuerto+"'";
		String salidas = "SELECT * FROM VUELOS a JOIN VIAJES b on a.iata=b.iata WHERE a.ORIGEN='"+aeropuerto+"'";
		String tipoAeronave = pObjeto.getTipoAeronave();
		if(!fechaInicial.equals("null")){
			arribos +="AND b.FECHA >= TO_DATE('"+fechaInicial+"', 'dd-mm-yyyy')";
			salidas +="AND b.FECHA >= TO_DATE('"+fechaInicial+"', 'dd-mm-yyyy')";
		}
		if(!fechaFinal.equals("null")){
			arribos +="AND b.FECHA <= TO_DATE('"+fechaFinal+"', 'dd-mm-yyyy')";
			salidas +="AND b.FECHA <= TO_DATE('"+fechaFinal+"', 'dd-mm-yyyy')";
		}
		if(!tipoAeronave.equals("null")){
			arribos +="AND a.TIPO = '"+tipoAeronave+"'";
			salidas +="AND a.TIPO = '"+tipoAeronave+"'";
		}
		if(!aerolinea.equals("null")){
			arribos +="AND a.AEROLINEA = '"+aerolinea+"'";
			salidas +="AND a.AEROLINEA = '"+aerolinea+"'";
		}
		if(!horaSalida.equals("null")){
			arribos +="AND a.HORA_SALIDA = '"+horaSalida+"'";
			salidas +="AND a.HORA_SALIDA = '"+horaSalida+"'";
		}
		if(!horaLlegada.equals("null")){
			arribos +="AND a.HORA_LLEGADA = '"+horaLlegada+"'";
			salidas +="AND a.HORA_LLEGADA = '"+horaLlegada+"'";
		}
		System.out.println(arribos);
		PreparedStatement prepStmt = conn.prepareStatement(arribos);
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

			arribosArray.add(new Viaje(tipo, numeroSerie, modelo, capacidad, economicas, ejecutivas, fecha, IATA, finalizado));
		}

		pObjeto.setArribos(arribosArray);

		PreparedStatement prepStmt1 = conn.prepareStatement(salidas);
		ResultSet rs1 = prepStmt1.executeQuery();

		while (rs1.next()) {
			String IATA = (String)rs1.getString("IATA");
			Date fecha = (Date) rs1.getDate("FECHA");	
			String modelo = (String) rs1.getString("MODELO_AERONAVE");
			int numeroSerie = (int) rs1.getInt("NUMERO_SERIE_AERONAVE");
			String tipo = (String) rs1.getString("TIPO");
			int capacidad = (int) rs1.getInt("CAPACIDAD_DISPONIBLE");
			int economicas = (int) rs1.getInt("SILLAS_ECONOMICAS_DISPONIBLES");
			int ejecutivas = (int) rs1.getInt("SILLAS_EJECUTIVAS_DISPONIBLES");
			boolean finalizado = rs1.getBoolean("FINALIZADO");

			salidasArray.add(new Viaje(tipo, numeroSerie, modelo, capacidad, economicas, ejecutivas, fecha, IATA, finalizado));
		}

		pObjeto.setSalidas(salidasArray);
	}


	public void RFC8(RFC7y8 pObjeto) throws SQLException
	{
		ArrayList<Viaje> arribosArray = new ArrayList<>();
		ArrayList<Viaje> salidasArray = new ArrayList<>();
		String horaLlegada = pObjeto.getHoraLlegada();
		String horaSalida = pObjeto.getHoraSalida();
		String aeropuerto = pObjeto.getAeropuerto();
		String fechaInicial = pObjeto.getFechaInicial();
		String fechaFinal = pObjeto.getFechaFinal();
		String aerolinea = pObjeto.getAerolinea();
		String arribos = "SELECT * FROM  VUELOS a JOIN VIAJES b on a.iata=b.iata  WHERE a.DESTINO='"+aeropuerto+"'";
		String salidas = "SELECT * FROM VUELOS a JOIN VIAJES b on a.iata=b.iata WHERE a.ORIGEN='"+aeropuerto+"'";
		String tipoAeronave  = pObjeto.getTipoAeronave();
		if(!fechaInicial.equals("null")){
			arribos +="AND b.FECHA >= TO_DATE('"+fechaInicial+"', 'dd-mm-yyyy')";
			salidas +="AND b.FECHA >= TO_DATE('"+fechaInicial+"', 'dd-mm-yyyy')";
		}
		if(!fechaFinal.equals("null")){
			arribos +="AND b.FECHA <= TO_DATE('"+fechaFinal+"', 'dd-mm-yyyy')";
			salidas +="AND b.FECHA <= TO_DATE('"+fechaFinal+"', 'dd-mm-yyyy')";
		}
		if(!tipoAeronave.equals("null")){
			if (tipoAeronave.equals("CARGA"))
				tipoAeronave = "COMERCIAL";
			else if (tipoAeronave.equals("COMERCIAL"))
				tipoAeronave = "CARGA";
			arribos +="AND a.TIPO = '"+tipoAeronave+"'";
			salidas +="AND a.TIPO = '"+tipoAeronave+"'";
		}
		if(!aerolinea.equals("null")){
			arribos +="AND a.AEROLINEA != '"+aerolinea+"'";
			salidas +="AND a.AEROLINEA != '"+aerolinea+"'";
		}
		if(!horaSalida.equals("null")){
			arribos +="AND a.HORA_SALIDA != '"+horaSalida+"'";
			salidas +="AND a.HORA_SALIDA != '"+horaSalida+"'";
		}
		if(!horaLlegada.equals("null")){
			arribos +="AND a.HORA_LLEGADA != '"+horaLlegada+"'";
			salidas +="AND a.HORA_LLEGADA != '"+horaLlegada+"'";
		}

		System.out.println(arribos);
		PreparedStatement prepStmt = conn.prepareStatement(arribos);
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

			arribosArray.add(new Viaje(tipo, numeroSerie, modelo, capacidad, economicas, ejecutivas, fecha, IATA, finalizado));
		}

		pObjeto.setArribos(arribosArray);

		PreparedStatement prepStmt1 = conn.prepareStatement(salidas);
		ResultSet rs1 = prepStmt1.executeQuery();

		while (rs1.next()) {
			String IATA = (String)rs1.getString("IATA");
			Date fecha = (Date) rs1.getDate("FECHA");	
			String modelo = (String) rs1.getString("MODELO_AERONAVE");
			int numeroSerie = (int) rs1.getInt("NUMERO_SERIE_AERONAVE");
			String tipo = (String) rs1.getString("TIPO");
			int capacidad = (int) rs1.getInt("CAPACIDAD_DISPONIBLE");
			int economicas = (int) rs1.getInt("SILLAS_ECONOMICAS_DISPONIBLES");
			int ejecutivas = (int) rs1.getInt("SILLAS_EJECUTIVAS_DISPONIBLES");
			boolean finalizado = rs1.getBoolean("FINALIZADO");

			salidasArray.add(new Viaje(tipo, numeroSerie, modelo, capacidad, economicas, ejecutivas, fecha, IATA, finalizado));
		}

		pObjeto.setSalidas(salidasArray);
	}

	public void RFC9(RFC9 objeto) throws SQLException
	{
		int distancia = objeto.getDistancia();
		String usuario = objeto.getUsuario();
		String fechaInicial = objeto.getFecha_inicial();
		String fechaFinal = objeto.getFecha_final();
		String sql = "SELECT * FROM ((SELECT ID_CLIENTE ID_C, NOMBRE, SUM(DISTANCIA) TOTAL FROM"
				+ " ((SELECT ID, MODELO_AERONAVE, TIPO TIPOA, DESTINO, ORIGEN, DISTANCIA,"
				+ " AEROLINEA FROM ((SELECT IATA ID, FECHA, MODELO_AERONAVE, TIPO FROM"
				+ " VIAJES WHERE FECHA <= TO_DATE('13/11/2016') )S1 INNER JOIN "
				+ " (SELECT IATA, DESTINO, ORIGEN, DISTANCIA, AEROLINEA FROM VUELOS )S2 ON S1.ID=S2.IATA)  ORDER BY ID)S5 INNER JOIN"
				+ " (SELECT ID_CLIENTE, NOMBRE, IATA_VUELO FROM ((SELECT IATA_VUELO, FECHA, ID_CLIENTE FROM RESERVAS) S3"
				+ " INNER JOIN (SELECT ID, NOMBRE FROM CLIENTES )S4 ON S3.ID_CLIENTE = S4.ID))S6 ON S5.ID =S6.IATA_VUELO) GROUP BY ID_CLIENTE, NOMBRE)F1"
				+ " INNER JOIN  (SELECT * FROM((SELECT * FROM (( SELECT * FROM VIAJES WHERE FECHA <= TO_DATE('13/11/2016') )T1 INNER JOIN "
				+ " (SELECT IATA ID, DESTINO, ORIGEN, DISTANCIA, AEROLINEA FROM VUELOS )T2 ON T1.IATA=T2.ID) ORDER BY ID) T3 INNER JOIN "
				+ "(SELECT ID_CLIENTE, IATA_VUELO FROM RESERVAS)T4 ON T3.ID = T4.IATA_VUELO))F2 ON F1.ID_C = F2.ID_CLIENTE) WHERE DISTANCIA="+distancia+"";

		if ( !usuario.equals("null"))
			sql+= "AND ID_C='"+usuario+"'";

		if (!fechaInicial.equals("null"))
			sql+= "AND FECHA >= TO_DATE('"+fechaInicial+"', 'dd-mm-yyyy')";

		if (!fechaFinal.equals("null"))
			sql+= "AND FECHA <= TO_DATE('"+fechaFinal+"', 'dd-mm-yyyy')";
		System.out.println(sql);
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

			objeto.addViajes(new Viaje(tipo, numeroSerie, modelo, capacidad, economicas, ejecutivas, fecha, IATA, finalizado));
		}
	}

	public void RFC10(RFC10 objeto) throws SQLException
	{
		String aeropuerto1 = objeto.getAeropueto1();
		String aeropuerto2 = objeto.getAeropuerto2();
		String fecha1 = objeto.getFecha1();
		String fecha2 = objeto.getFecha2();
		String aeropuertos = "('"+aeropuerto1+"', '"+aeropuerto2+"')";
		String sql = "SELECT * FROM VUELOS T1 INNER JOIN VIAJES T2 ON T1.IATA=T2.IATA"
				+ " INNER JOIN  AERONAVES T4 ON T2.MODELO_AERONAVE=T4.MODELO AND T2.NUMERO_SERIE_AERONAVE=T4.NUMERO_SERIE"
				+ " WHERE ORIGEN IN "+aeropuertos+
				" AND DESTINO IN "+aeropuertos;
		if(!fecha1.equals("null"))

			sql+="AND b.FECHA >= TO_DATE('"+fecha1+"', 'dd-mm-yyyy')";
		if(!fecha2.equals("null"))
			sql+="AND b.FECHA <= TO_DATE('"+fecha2+"', 'dd-mm-yyyy')";

		System.out.println(sql);
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
			int sillasEco = rs.getInt("SILLAS_ECONOMICAS");
			int sillasEje = rs.getInt("SILLAS_EJECUTIVAS");
			int total = sillasEco+sillasEje;
			double capacidadUsada = rs.getDouble("CAPACIDAD");

			objeto.addViaje(new Viaje2(IATA, fecha, modelo, numeroSerie, tipo, capacidad, economicas, ejecutivas, finalizado, total, capacidadUsada));
		}
	}
}

