package tm;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import dao.DAOClientes;
import dao.DAOUsuarios;
import vos.Cliente;
import vos.ListaClientes;
import vos.Usuario;


public class FestivAndes {

	
		private static final String CONNECTION_DATA_FILE_NAME_REMOTE = "/conexion.properties";
		private String connectionDataPath;
		private String user;
		private String password;
		private String url;
		private String driver;
		private Connection conn;
		
		
		public FestivAndes(String contextPathP) {
			connectionDataPath = contextPathP + CONNECTION_DATA_FILE_NAME_REMOTE;
			initConnectionData();
		}

		
		private void initConnectionData() {
			try {
				File arch = new File(this.connectionDataPath);
				Properties prop = new Properties();
				FileInputStream in = new FileInputStream(arch);
				prop.load(in);
				in.close();
				this.url = prop.getProperty("url");
				this.user = prop.getProperty("usuario");
				this.password = prop.getProperty("clave");
				this.driver = prop.getProperty("driver");
				Class.forName(driver);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * M茅todo que  retorna la conexi贸n a la base de datos
		 * @return Connection - la conexi贸n a la base de datos
		 * @throws SQLException - Cualquier error que se genere durante la conexi贸n a la base de datos
		 */
		private Connection darConexion() throws SQLException {
			System.out.println("Connecting to: " + url + " With user: " + user);
			return DriverManager.getConnection(url, user, password);
		}

		////////////////////////////////////////
		///////Transacciones////////////////////
		////////////////////////////////////////

		
		//-----------------------------------------------------
		// RF1 REGISTRAR USUARIO
		//----------------------------------------------------
		
		public void registrarUsuario(Usuario user ) throws SQLException{
			DAOUsuarios daoUsuario = new DAOUsuarios();
			try {
			this.conn = darConexion();
			daoUsuario.setConn(conn);
			daoUsuario.addUsuario(user);
			conn.close();
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			}finally {
				try {
					daoUsuario.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}
		
		
		//-----------------------------------------------------
		// RF2 REGISTRAR CLIENTE
		//----------------------------------------------------
				
		public void registrarCliente(Cliente cliente ) throws SQLException{
			DAOClientes daoCliente = new DAOClientes();
			try {
			this.conn = darConexion();
			daoCliente.setConn(conn);
			daoCliente.addCliente(cliente);
			conn.close();
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			}finally {
				try {
					daoCliente.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}
		
		
		//-----------------------------------------------------
		// RF3 REGISTRAR COMPA淹A DE TEATRO
		//----------------------------------------------------
				
		public void registrarCompania(Usuario u ){
			DAOCompania dao = new D();
			this.conn = darConexion();
			dao.setConn(conn);
			dao.registrarUsuario(u);
			conn.close();
		}
		
		
		//-----------------------------------------------------
		// RF3 REGISTRAR COMPA淹A DE TEATRO
		//----------------------------------------------------
		
		
		

		/**
		 * M茅todo que modela la transacci贸n que retorna todos los videos de la base de datos.
		 * @return ListaVideos - objeto que modela  un arreglo de videos. este arreglo contiene el resultado de la b煤squeda
		 * @throws Exception -  cualquier error que se genere durante la transacci贸n
		 */
		
		public ListaClientes darClientes() throws Exception {
			ArrayList<Cliente> videos;
			DAOTablaVideos daoVideos = new DAOTablaVideos();
			try 
			{
				//////Transacci贸n
				this.conn = darConexion();
				daoVideos.setConn(conn);
				videos = daoVideos.darVideos();

			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} finally {
				try {
					daoVideos.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return new ListaVideos(videos);
		}

		/**
		 * M茅todo que modela la transacci贸n que busca el/los videos en la base de datos con el nombre entra como par谩metro.
		 * @param name - Nombre del video a buscar. name != null
		 * @return ListaVideos - objeto que modela  un arreglo de videos. este arreglo contiene el resultado de la b煤squeda
		 * @throws Exception -  cualquier error que se genere durante la transacci贸n
		 */
		public ListaVideos buscarVideosPorName(String name) throws Exception {
			ArrayList<Video> videos;
			DAOTablaVideos daoVideos = new DAOTablaVideos();
			try 
			{
				//////Transacci贸n
				this.conn = darConexion();
				daoVideos.setConn(conn);
				videos = daoVideos.buscarVideosPorName(name);

			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} finally {
				try {
					daoVideos.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return new ListaVideos(videos);
		}
		
		/**
		 * M茅todo que modela la transacci贸n que agrega un solo video a la base de datos.
		 * <b> post: </b> se ha agregado el video que entra como par谩metro
		 * @param video - el video a agregar. video != null
		 * @throws Exception - cualquier error que se genera agregando el video
		 */
		public void addVideo(Video video) throws Exception {
			DAOTablaVideos daoVideos = new DAOTablaVideos();
			try 
			{
				//////Transacci贸n
				this.conn = darConexion();
				daoVideos.setConn(conn);
				daoVideos.addVideo(video);
				conn.commit();

			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} finally {
				try {
					daoVideos.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}
		
		/**
		 * M茅todo que modela la transacci贸n que agrega los videos que entran como par谩metro a la base de datos.
		 * <b> post: </b> se han agregado los videos que entran como par谩metro
		 * @param videos - objeto que modela una lista de videos y se estos se pretenden agregar. videos != null
		 * @throws Exception - cualquier error que se genera agregando los videos
		 */
		public void addVideos(ListaVideos videos) throws Exception {
			DAOTablaVideos daoVideos = new DAOTablaVideos();
			try 
			{
				//////Transacci贸n - ACID Example
				this.conn = darConexion();
				conn.setAutoCommit(false);
				daoVideos.setConn(conn);
				for(Video video : videos.getVideos())
					daoVideos.addVideo(video);
				conn.commit();
			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				conn.rollback();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				conn.rollback();
				throw e;
			} finally {
				try {
					daoVideos.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}
		
		/**
		 * M茅todo que modela la transacci贸n que actualiza el video que entra como par谩metro a la base de datos.
		 * <b> post: </b> se ha actualizado el video que entra como par谩metro
		 * @param video - Video a actualizar. video != null
		 * @throws Exception - cualquier error que se genera actualizando los videos
		 */
		public void updateVideo(Video video) throws Exception {
			DAOTablaVideos daoVideos = new DAOTablaVideos();
			try 
			{
				//////Transacci贸n
				this.conn = darConexion();
				daoVideos.setConn(conn);
				daoVideos.updateVideo(video);

			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} finally {
				try {
					daoVideos.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}

		/**
		 * M茅todo que modela la transacci贸n que elimina el video que entra como par谩metro a la base de datos.
		 * <b> post: </b> se ha eliminado el video que entra como par谩metro
		 * @param video - Video a eliminar. video != null
		 * @throws Exception - cualquier error que se genera actualizando los videos
		 */
		public void deleteVideo(Video video) throws Exception {
			DAOTablaVideos daoVideos = new DAOTablaVideos();
			try 
			{
				//////Transacci贸n
				this.conn = darConexion();
				daoVideos.setConn(conn);
				daoVideos.deleteVideo(video);

			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} finally {
				try {
					daoVideos.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}

		/**
		 * M茅todo que modela la transacci贸n que retorna el/los videos mas alquilados
		 * @return ListaVideos - objeto que modela  un arreglo de videos. este arreglo contiene el resultado de la b煤squeda
		 * @throws Exception -  cualquier error que se genere durante la transacci贸n
		 */
		public ListaVideos videosMasAlquilados() throws Exception {
			ArrayList<Video> videos;
			DAOTablaVideos daoVideos = new DAOTablaVideos();
			try 
			{
				//////Transacci贸n
				this.conn = darConexion();
				daoVideos.setConn(conn);
				videos = daoVideos.darVideoMasAlquilado();

			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} finally {
				try {
					daoVideos.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return new ListaVideos(videos);
		}
		
	}
	
	

