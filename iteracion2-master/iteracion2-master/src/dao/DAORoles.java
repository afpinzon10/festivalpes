package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Rol;

public class DAORoles {

	private ArrayList<Object> recursos;
	
	private Connection conn;
	
	public DAORoles ()
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
	
	
	public ArrayList<Rol> darRols() throws SQLException, Exception {
		ArrayList<Rol> roles = new ArrayList<Rol>();

		
		String sql = "SELECT * FROM ROL";
		System.out.println(sql);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			int idRol = Integer.parseInt(rs.getString("IDROL"));
			String nombre = rs.getString("NOMBRE");
			roles.add(new Rol());
		}
		return roles;
	}

	
		
		
	
	
	
	
}
