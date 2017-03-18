package tm;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;




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
		 * Método que  retorna la conexión a la base de datos
		 * @return Connection - la conexión a la base de datos
		 * @throws SQLException - Cualquier error que se genere durante la conexión a la base de datos
		 */
		private Connection darConexion() throws SQLException {
			System.out.println("Connecting to: " + url + " With user: " + user);
			return DriverManager.getConnection(url, user, password);
		}

		////////////////////////////////////////
		///////Transacciones////////////////////
		////////////////////////////////////////


		/**
		 * Método que modela la transacción que retorna todos los videos de la base de datos.
		 * @return ListaVideos - objeto que modela  un arreglo de videos. este arreglo contiene el resultado de la búsqueda
		 * @throws Exception -  cualquier error que se genere durante la transacción
		 */
		public ListaVideos darVideos() throws Exception {
			ArrayList<Video> videos;
			DAOTablaVideos daoVideos = new DAOTablaVideos();
			try 
			{
				//////Transacción
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
		 * Método que modela la transacción que busca el/los videos en la base de datos con el nombre entra como parámetro.
		 * @param name - Nombre del video a buscar. name != null
		 * @return ListaVideos - objeto que modela  un arreglo de videos. este arreglo contiene el resultado de la búsqueda
		 * @throws Exception -  cualquier error que se genere durante la transacción
		 */
		public ListaVideos buscarVideosPorName(String name) throws Exception {
			ArrayList<Video> videos;
			DAOTablaVideos daoVideos = new DAOTablaVideos();
			try 
			{
				//////Transacción
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
		 * Método que modela la transacción que agrega un solo video a la base de datos.
		 * <b> post: </b> se ha agregado el video que entra como parámetro
		 * @param video - el video a agregar. video != null
		 * @throws Exception - cualquier error que se genera agregando el video
		 */
		public void addVideo(Video video) throws Exception {
			DAOTablaVideos daoVideos = new DAOTablaVideos();
			try 
			{
				//////Transacción
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
		 * Método que modela la transacción que agrega los videos que entran como parámetro a la base de datos.
		 * <b> post: </b> se han agregado los videos que entran como parámetro
		 * @param videos - objeto que modela una lista de videos y se estos se pretenden agregar. videos != null
		 * @throws Exception - cualquier error que se genera agregando los videos
		 */
		public void addVideos(ListaVideos videos) throws Exception {
			DAOTablaVideos daoVideos = new DAOTablaVideos();
			try 
			{
				//////Transacción - ACID Example
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
		 * Método que modela la transacción que actualiza el video que entra como parámetro a la base de datos.
		 * <b> post: </b> se ha actualizado el video que entra como parámetro
		 * @param video - Video a actualizar. video != null
		 * @throws Exception - cualquier error que se genera actualizando los videos
		 */
		public void updateVideo(Video video) throws Exception {
			DAOTablaVideos daoVideos = new DAOTablaVideos();
			try 
			{
				//////Transacción
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
		 * Método que modela la transacción que elimina el video que entra como parámetro a la base de datos.
		 * <b> post: </b> se ha eliminado el video que entra como parámetro
		 * @param video - Video a eliminar. video != null
		 * @throws Exception - cualquier error que se genera actualizando los videos
		 */
		public void deleteVideo(Video video) throws Exception {
			DAOTablaVideos daoVideos = new DAOTablaVideos();
			try 
			{
				//////Transacción
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
		 * Método que modela la transacción que retorna el/los videos mas alquilados
		 * @return ListaVideos - objeto que modela  un arreglo de videos. este arreglo contiene el resultado de la búsqueda
		 * @throws Exception -  cualquier error que se genere durante la transacción
		 */
		public ListaVideos videosMasAlquilados() throws Exception {
			ArrayList<Video> videos;
			DAOTablaVideos daoVideos = new DAOTablaVideos();
			try 
			{
				//////Transacción
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
	
	
