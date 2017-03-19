package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

import vos.Funcion;

public class DAOFunciones {
	
private ArrayList<Object> recursos;
	
	private Connection conn;
	
	public DAOFunciones ()
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
	
	public ArrayList<Funcion> darFunciones() throws SQLException, Exception {
		ArrayList<Funcion> Funciones = new ArrayList<Funcion>();
		String sql = "SELECT * FROM FUNCION";
		System.out.println(sql);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			int idFuncion = Integer.parseInt(rs.getString("IDFUNCION"));
			Date fecha = Date.parse(rs.getString("FECHA"));
			Time hora = Time.parse(rs.getString("HORA"));
			int idEspacio = Integer.parseInt(rs.getString("IDESPACIO"));
			int idObra = Integer.parseInt(rs.getString("IDOBRA"));
			Funciones.add(new Funcion(null, idFuncion, fecha, hora, idEspacio, idObra));
		}
		return Funciones;
	}

	public void addFuncion(Funcion funcion) throws SQLException {
		
		String sql = "INSERT INTO FUNCION VALUES (FECHA, HORA, IDESPACIO, IDOBRA)";
		sql += funcion.getFecha() + ",'";
		sql += funcion.getHora() + ",'";
		sql += funcion.getIdespacio() + ",'";
		sql += funcion.getIdobra() +")";
		
		System.out.println("SQL stmt    addCliente:" + sql);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		
	}
}
