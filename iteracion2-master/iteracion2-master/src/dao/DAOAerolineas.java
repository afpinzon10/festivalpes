package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Vuelo;

public class DAOAerolineas {

	private ArrayList<Object> recursos;

	private Connection conn;

	public DAOAerolineas ()
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
	
	public ArrayList<String> darAerolineas() throws SQLException
	{
		ArrayList<String> aerolineas  = new ArrayList<String>();
		String sql = "SELECT IATA FROM AEROLINEAS ORDER BY IATA ASC";

		System.out.println("SQL stmt:" + sql);
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		ResultSet rs = prepStmt.executeQuery();
		while (rs.next())
		{
			String iata = rs.getString("IATA");
			aerolineas.add(iata);
		}
		return aerolineas;
	}

}
