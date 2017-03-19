package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import vos.Espacio;

public class DAOEspacios {

	private ArrayList<Object> recursos;

	private Connection conn;

	public DAOEspacios ()
	{
		recursos = new ArrayList<Object>();
	}

	//Cierra el recuerso
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
	
	public ArrayList<Espacio> darEspacios() throws SQLException
	{
		ArrayList<Espacio> usuarios  = new ArrayList<Espacio>();
		String sql = "SELECT * FROM ESPACIO";

		System.out.println("SQL stmt:" + sql);
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		ResultSet rs = prepStmt.executeQuery();
		while (rs.next())
		{
			int idEspacio = Integer.parseInt(rs.getString("IDESPACIO"));
			String nombre = rs.getString("NOMBRE");
			int capacidad = Integer.parseInt(rs.getString("CAPACIDAD"));
			String ubicacion = rs.getString("UBICACION");
			String tipo = rs.getString("TIPO");
			String ciudad = rs.getString("CIUDAD");
			String telefono = rs.getString("TELEFONO");
			Date horaInicio = Date.parse(rs.getString("HORAINICIO"));
			Date horaFin = Date.parse(rs.getString("HORAFIN"));
			usuarios.add(new Espacio());
		}
		return usuarios;
	}
}
