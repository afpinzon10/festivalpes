package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Compania;

public class DAOCompanias {
private ArrayList<Object> recursos;
	
	private Connection conn;
	
	public DAOCompanias ()
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
	
	public ArrayList<Compania> darCompanias() throws SQLException, Exception {
		ArrayList<Compania> Companias = new ArrayList<Compania>();
		String sql = "SELECT * FROM COMPANIAS";
		System.out.println(sql);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			int idUsuario = Integer.parseInt(rs.getString("IDUSUARUIO"));
			String nombre = rs.getString("NOMBRE");
			String email = rs.getString("EMAIL");
			String identificacion = rs.getString("IDENTIFICACION");
			Companias.add(new Compania(null, null, idUsuario, nombre, email, identificacion));
		}
		return Companias;
	}

	public void addCompania(Compania comp) throws SQLException {
		String sql = "INSERT INTO COMPANIA VALUES (IDUSUARIO, NOMBRE, EMAIL)";
		sql += comp.getIdusuario() + ",'";
		sql += comp.getNombre() + ",'";
		sql += comp.getEmail() +")";
		
		System.out.println("SQL stmt    addCompania:" + sql);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();// TODO Auto-generated method stub
		
		
		
	}
}
