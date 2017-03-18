package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ArrayList;

import vos.Cliente;
import vos.Reserva;

public class DAOReservas {

private ArrayList<Object> recursos;
	
	private Connection conn;
	
	public DAOReservas ()
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

	/**
	 * R8 y R9
	 * @param r
	 * @param tipoIdCliente
	 * @param idCliente
	 * @throws Exception
	 */
	public void crearReserva(Reserva r, String tipoIdCliente, int idCliente) throws Exception{
		Savepoint save = conn.setSavepoint("crearReserva");
		if(tipoIdCliente != r.getTipoIdCliente() || idCliente != r.getIdCliente())
			throw new Exception("Este usuario no tiene permiso para crear la reserva");
		String sql = "SELECT REMITENTE, VIAJERO FROM ISIS2304B261620.CLIENTES WHERE CLIENTES.TIPO_ID= '"+tipoIdCliente+"' AND CLIENTES.ID="+idCliente;
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		int remitente=2;
		int viajero=2;
		while(rs.next()){
			remitente = rs.getInt("REMITENTE");
			viajero = rs.getInt("VIAJERO");
		}
		stmt.close();
		if(r.getTipo().equalsIgnoreCase("COMERCIAL") && viajero==1){
			sql = "INSERT INTO RESERVAS (TIPO_ID_CLIENTE, ID_CLIENTE, IATA_VUELO, FECHA, TIPO, TIPO_SILLA) VALUES ("
					+ r.getTipoIdCliente() + ',' + r.getIdCliente() + ',' + r.getIataVuelo() + ',' + r.getFecha() + ',' + r.getTipo() + ','
					+ r.getTipoSilla() + ')';
			
		}
		else if(r.getTipo().equalsIgnoreCase("CARGA") && remitente==1){
			sql = "INSERT INTO RESERVAS (TIPO_ID_CLIENTE, ID_CLIENTE, IATA_VUELO, FECHA, TIPO, VOLUMEN, PESO, CONTENIDO) VALUES ("
					+ r.getTipoIdCliente() + ',' + r.getIdCliente() + ',' + r.getIataVuelo() + ',' + r.getFecha() + ',' + r.getTipo() + ','
					+ r.getVolumen() + ',' + r.getPeso() + ',' + r.getContenido() + ')';
		}
		else{
			throw new Exception ("Error al agregar reserva");
		}
		stmt = conn.prepareStatement(sql);
		stmt.executeQuery();
		stmt.close();
	}
	
	
	public ArrayList<Reserva> darReservasVuelo(String vuelo) throws SQLException
	{
		ArrayList<Reserva> reservas = new ArrayList<Reserva>();
		String sql = "SELECT * FROM ISIS2304B261620.RESERVAS WHERE IATA_VUELO ='" + vuelo + "'";

		System.out.println(sql);
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		
		while (rs.next()) {
			int numeroReserva = Integer.parseInt(rs.getString("NUMERO_RESERVA"));
			String tipoId = rs.getString("TIPO_ID_CLIENTE");
			int idCliente = Integer.parseInt(rs.getString("ID_CIENTE"));
			String IATAVuelo = rs.getString("IATA_VUELO");
			Date fecha = rs.getDate("FECHA");
			String tipo = rs.getString("TIPO");
			String tipoSilla = rs.getString("TIPO_SILLA");
			Double volumen = Double.parseDouble(rs.getString("VOLUMEN"));
			Double peso = Double.parseDouble(rs.getString("PESO"));
			String contenido = rs.getString("CONTENIDO");
			
			reservas.add(new Reserva(numeroReserva, tipoId, idCliente, IATAVuelo, fecha, tipo, tipoSilla, volumen, peso, contenido));
			}
		return reservas;
	}
	
	
	
	
	
	public ArrayList<Reserva> darReservas(int pIdCliente) throws SQLException
	{
		ArrayList<Reserva> reservas = new ArrayList<Reserva>();
		String sql = "SELECT * FROM ISIS2304B261620.RESERVAS WHERE ID_CLIENTE ='" + pIdCliente + "'";

		System.out.println(sql);
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		
		while (rs.next()) {
			int numeroReserva = Integer.parseInt(rs.getString("NUMERO_RESERVA"));
			String tipoId = rs.getString("TIPO_ID_CLIENTE");
			int idCliente = Integer.parseInt(rs.getString("ID_CIENTE"));
			String IATAVuelo = rs.getString("IATA_VUELO");
			Date fecha = rs.getDate("FECHA");
			String tipo = rs.getString("TIPO");
			String tipoSilla = rs.getString("TIPO_SILLA");
			Double volumen = Double.parseDouble(rs.getString("VOLUMEN"));
			Double peso = Double.parseDouble(rs.getString("PESO"));
			String contenido = rs.getString("CONTENIDO");
			
			reservas.add(new Reserva(numeroReserva, tipoId, idCliente, IATAVuelo, fecha, tipo, tipoSilla, volumen, peso, contenido));
			}
		return reservas;
	}
	
	public Reserva buscarReserva(int pId) throws SQLException
	{
		Reserva respuesta = null;
		String sql = "SELECT * FROM ISIS2304B261620.RESERVAS WHERE NUMERO_RESERVA ='" + pId + "'";

		System.out.println(sql);
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		
		while (rs.next()) {
			int numeroReserva = Integer.parseInt(rs.getString("NUMERO_RESERVA"));
			String tipoId = rs.getString("TIPO_ID_CLIENTE");
			int idCliente = Integer.parseInt(rs.getString("ID_CIENTE"));
			String IATAVuelo = rs.getString("IATA_VUELO");
			Date fecha = rs.getDate("FECHA");
			String tipo = rs.getString("TIPO");
			String tipoSilla = rs.getString("TIPO_SILLA");
			Double volumen = Double.parseDouble(rs.getString("VOLUMEN"));
			Double peso = Double.parseDouble(rs.getString("PESO"));
			String contenido = rs.getString("CONTENIDO");
			
			respuesta = new Reserva(numeroReserva, tipoId, idCliente, IATAVuelo, fecha, tipo, tipoSilla, volumen, peso, contenido);
			}
		return respuesta;
	}
	
	public void updateReserva(int pId, String IATA, Date fecha) throws SQLException
	{
		conn.setSavepoint("updateReserva");
		String sql = "UPDATE ISIS2304B261620.RESERVAS SET IATA_VUELO"+IATA+" SET FECHA"+fecha+" WHERE NUMERO_RESERVA ='" + pId + "'";
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
	}
	
	
	
	
	
	public void eliminarReserva(int pId ) throws SQLException, Exception 
	{
		Savepoint save = conn.setSavepoint("eliminarReserva");
		Reserva a = buscarReserva(pId);
		Long diferencia= a.getFecha().getTime() - System.currentTimeMillis();
		if (diferencia < 86400000)
			throw new Exception ("No se puede eliminar una reserva el dia antes");
	
		String sql = "DELETE FROM ISIS2304B261620.RESERVAS WHERE NUMERO_RESERVA ='" + pId + "'";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	
}
