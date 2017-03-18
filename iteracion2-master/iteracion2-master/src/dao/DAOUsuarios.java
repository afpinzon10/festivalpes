package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Administra;
import vos.Cliente;
import vos.Opera;
import vos.Usuario;

public class DAOUsuarios {
	
	private ArrayList<Object> recursos;

	private Connection conn;
	public DAOUsuarios() 
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
	 * RF1
	 * @param u
	 * @throws SQLException
	 */
	public ArrayList<Usuario> darUsuarios() throws SQLException
	{
		int i = 0;
		ArrayList<Usuario> usuarios = new ArrayList<>();
		String sql = "SELECT * FROM ISIS2304B261620.USUARIOS";
		System.out.println(sql);
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		System.out.println(prepStmt+" probando");
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			String id = rs.getString("ID");
			String  nombre = rs.getString("NOMBRE");
			String rol = rs.getString("ROL");
			String email = rs.getString("EMAIL");
			usuarios.add(new Usuario(nombre, email, rol, id));
			i++;
		}
		System.out.println("el numero de usuarios es: "+ i);
		return usuarios;
	}
	
	
	public Usuario buscarUsuario(String pId) throws SQLException
	{
		Usuario respuesta = null;
		String sql = "SELECT * FROM ISIS2304B261620.USUARIOS WHERE ID ='" + pId + "'";
		System.out.println(sql);
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		
		while (rs.next()) {
			String id = rs.getString("ID");
			String  nombre = rs.getString("NOMBRE");
			String rol = rs.getString("ROL");
			String email = rs.getString("EMAIL");
			respuesta = new Usuario(nombre, email, rol, id);
		}
		return respuesta;
		
		
	}
	
	
	
	public void registrarUsuario(Usuario u) throws SQLException{
		String sql = "INSERT INTO ISIS2304B261620.USUARIOS VALUES("+ u.getId()+','+ u.getNombre()+','+u.getRol()+','+u.getEmail()+')';
		PreparedStatement stmt = conn.prepareStatement(sql);
		
	}
	
	
	/**
	 * RF1
	 * @param a
	 * @throws SQLException
	 */
	public void asociarUsuarioAerolinea(Administra a) throws SQLException{
		String sql = "INSERT INTO ISIS2304B261620.ADMINISTRA VALUES(" + a.getIdUsuario()+','+a.getAerolinea()+')';
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.executeQuery();
		stmt.close();
	}
	
	/**
	 * RF1
	 * @param o
	 * @throws SQLException
	 */
	public void asociarUsuarioAeropuerto(Opera o) throws SQLException{
		String sql = "INSERT INTO ISIS2304B261620.OPERA VALUES(" + o.getIdUsuario()+','+o.getAeropuerto()+')';
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.executeQuery();
		stmt.close();
	}
}

