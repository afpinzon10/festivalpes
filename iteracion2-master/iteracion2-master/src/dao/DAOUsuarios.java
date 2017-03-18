package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import vos.Usuario;

public class DAOUsuarios {

	private ArrayList<Object> recursos;

	private Connection conn;

	public DAOUsuarios ()
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
	
	public ArrayList<Usuario> darUsuarios() throws SQLException
	{
		ArrayList<Usuario> usuarios  = new ArrayList<Usuario>();
		String sql = "SELECT * FROM USUARIO";

		System.out.println("SQL stmt:" + sql);
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		ResultSet rs = prepStmt.executeQuery();
		while (rs.next())
		{
			int idUsuario = Integer.parseInt(rs.getString("IDUSUARIO"));
			String nombre = rs.getString("NOMBRE");
			String apellido = rs.getString("APELLIDO");
			String email = rs.getString("EMAIL");
			String identificacion = rs.getString("IDENTIFICACION");
			String login = rs.getString("LOGIN");
			String password = rs.getString("PASSWORD");
			int idRol = Integer.parseInt(rs.getString("IDROL"));
			usuarios.add(new Usuario(idUsuario, nombre, apellido, email, identificacion, login, password, idRol));
		}
		return usuarios;
	}

}
