package tm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;
import java.util.spi.TimeZoneNameProvider;

import dao.*;
import estructuras.*;
import vos.*;

public class VuelAndesMaster {

	/**
	 * Atributo estático que contiene el path relativo del archivo que tiene los datos de la conexión
	 */
	private Graph grafo;
	private DijkstraGraph grafoDijkstra;
	private DijkstraAlgorithm dijkstra;
	
	
	private static final String CONNECTION_DATA_FILE_NAME_REMOTE = "/conexion.properties";

	/**
	 * Atributo estático que contiene el path absoluto del archivo que tiene los datos de la conexión
	 */
	private  String connectionDataPath;

	/**
	 * Atributo que guarda el usuario que se va a usar para conectarse a la base de datos.
	 */
	private String user;

	private String password;

	private String url;

	private String driver;

	private Connection conn;
	
	private Random random;

	public VuelAndesMaster(String contextPathP) {
		connectionDataPath = contextPathP + CONNECTION_DATA_FILE_NAME_REMOTE;
		initConnectionData();
		random = new Random();
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
	
	public ArrayList<Aeropuerto> darAeropuertos() throws Exception {
		ArrayList<Aeropuerto> aeropuertos;
		DAOAeropuertos daoAeropuertos = new DAOAeropuertos();
		try 
		{
			//////Transacción
			this.conn = darConexion();
			daoAeropuertos.setConn(conn);
			aeropuertos = daoAeropuertos.darAeropuertos();
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
				daoAeropuertos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return aeropuertos;
	}
	
	
	public ArrayList<Usuario> darUsuarios() throws SQLException, Exception
	{
		ArrayList<Usuario> usuarios;
		DAOUsuarios daoUsuario = new DAOUsuarios();
		try 
		{
			//////Transacción
			this.conn = darConexion();
			daoUsuario.setConn(conn);
			usuarios = daoUsuario.darUsuarios();
		} catch (SQLException e) {
			conn.rollback();
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
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
		return usuarios;
	}
	
	
	public Usuario buscarUsuario(String pId) throws SQLException
	{
		Usuario respuesta = null;
		DAOUsuarios daoUsuarios = new DAOUsuarios();
		try 
		{
			//////Transacción
			this.conn = darConexion();
			daoUsuarios.setConn(conn);
			respuesta = daoUsuarios.buscarUsuario(pId);

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
				daoUsuarios.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return respuesta;
	}
	
	
	
	
	
	
	//----------------------------------------------------
	//RF1
	//----------------------------------------------------
	/**
	 * R1
	 * @param u
	 * @throws SQLException 
	 */
	public void registrarUsuario(Usuario u) throws SQLException {
		DAOUsuarios dao = new DAOUsuarios();
		this.conn = darConexion();
		dao.setConn(conn);
		dao.registrarUsuario(u);
		conn.close();
	}

	/**
	 * R1
	 * @param administra
	 * @throws SQLException 
	 */
	public void asociarUsuarioAerolinea(Administra administra) throws SQLException {
		DAOUsuarios dao = new DAOUsuarios();
		this.conn = darConexion();
		dao.setConn(conn);
		dao.asociarUsuarioAerolinea(administra);
		conn.close();
	}

	/**
	 * R1
	 * @param o
	 * @throws SQLException 
	 */
	public void asociarUsuarioAeropuerto(Opera o) throws SQLException {
		DAOUsuarios dao = new DAOUsuarios();
		this.conn = darConexion();
		dao.setConn(conn);
		dao.asociarUsuarioAeropuerto(o);
		conn.close();
	}


	public ArrayList<Cliente> darClientes() throws SQLException, Exception
	{
		ArrayList<Cliente> clientes;
		DAOClientes daoClientes = new DAOClientes();
		try 
		{
			//////Transacción
			this.conn = darConexion();
			daoClientes.setConn(conn);
			clientes = daoClientes.darClientes();

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
				daoClientes.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return clientes;
	}

	public ArrayList<Cliente> darClientesUsuario(String idUsuario) throws SQLException, Exception
	{
		ArrayList<Cliente> clientes;
		DAOClientes daoClientes = new DAOClientes();
		try 
		{
			//////Transacción
			this.conn = darConexion();
			daoClientes.setConn(conn);
			clientes = daoClientes.darClientesUsuario(idUsuario);

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
				daoClientes.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return clientes;
	}

	public void addCliente(Cliente cliente) throws SQLException, Exception {
		DAOClientes daoClientes = new DAOClientes();
		try 
		{
			this.conn = darConexion();
			daoClientes.setConn(conn);
			daoClientes.addCliente(cliente);
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
				daoClientes.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	public void addAvion(Avion avion) throws SQLException, Exception {
		DAOAviones daoAviones = new DAOAviones();
		try 
		{
			this.conn = darConexion();
			daoAviones.setConn(conn);
			daoAviones.addAvion(avion);
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
				daoAviones.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}



	public Avion buscarAvion(int pNumero, String pModelo) throws SQLException, Exception
	{
		Avion respuesta = null;
		DAOAviones daoAviones = new DAOAviones();
		try 
		{
			//////Transacción
			this.conn = darConexion();
			daoAviones.setConn(conn);
			respuesta = daoAviones.buscarAvion(pNumero, pModelo);

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
				daoAviones.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return respuesta;
	}
	
	public ArrayList<Viaje> darViajes() throws SQLException
	{
		ArrayList<Viaje> viajes = new ArrayList<Viaje>();
		DAOViajes daoViajes = new DAOViajes();
		try 
		{
			//////Transacción
			this.conn = darConexion();
			daoViajes.setConn(conn);
			viajes = daoViajes.darViajes();

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
				daoViajes.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return viajes;
	}
	


	public Viaje buscarViaje(String IATA, Date fecha) throws SQLException, Exception
	{
		Viaje respuesta = null;
		DAOViajes daoViajes = new DAOViajes();
		this.conn = darConexion();
		daoViajes.setConn(conn);
		Savepoint s = conn.setSavepoint("buscarViaje");
		try 
		{
			//////Transacción
			
			respuesta = daoViajes.buscarViaje(IATA, fecha);

		} catch (SQLException e) {
			conn.rollback(s);
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoViajes.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return respuesta;
	}
	
	
	public ArrayList<Vuelo> SalidasArribos(String IATA) throws SQLException, Exception
	{
		ArrayList<Vuelo> respuesta = new ArrayList<>();
		DAOVuelos daoVuelos = new DAOVuelos();
		try 
		{
			//////Transacción
			this.conn = darConexion();
			daoVuelos.setConn(conn);
			respuesta = daoVuelos.VuelosSalidaArribos(IATA);

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
				daoVuelos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return respuesta;
	}
	
	
	
	


	public void updateViaje(Avion avion, String id, Date fecha ) throws Exception {
		DAOViajes daoViajes = new DAOViajes();
		try 
		{
			//////Transacción
			this.conn = darConexion();
			daoViajes.setConn(conn);
			daoViajes.updateViaje(avion, id, fecha);

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
				daoViajes.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}


	public Vuelo buscarVuelo(String IATA) throws SQLException, Exception
	{
		Vuelo respuesta = null;
		DAOVuelos daoVuelos = new DAOVuelos();
		this.conn = darConexion();
		daoVuelos.setConn(conn);
		Savepoint s = conn.setSavepoint("buscarVuelo");
		try 
		{
			//////Transacción
			
			respuesta = daoVuelos.buscarVuelo(IATA);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			conn.rollback(s);
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoVuelos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return respuesta;
	}
	
	public ArrayList<Vuelo> darVuelos() throws SQLException, Exception
	{
		ArrayList<Vuelo> vuelos = new ArrayList<Vuelo>();
		DAOVuelos daoVuelos = new DAOVuelos();
		this.conn = darConexion();
		daoVuelos.setConn(conn);
		Savepoint s = conn.setSavepoint("darVuelos");
		try 
		{
			//////Transacción
			vuelos = daoVuelos.darVuelos();

		} catch (SQLException e) {
			conn.rollback(s);
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoVuelos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return vuelos;
	}
	
	
	
	public Aeropuerto buscarAeropuerto(String IATA) throws SQLException, Exception
	{
		Aeropuerto respuesta = null;
		DAOAeropuertos daoAeropuertos = new DAOAeropuertos();
		try 
		{
			//////Transacción
			this.conn = darConexion();
			daoAeropuertos.setConn(conn);
			respuesta = daoAeropuertos.buscarAeropuerto(IATA);

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
				daoAeropuertos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return respuesta;
	}
	
	public ArrayList<Reserva> darReservasCliente(int pIdCliente) throws SQLException
	{
		ArrayList<Reserva> reservas;
		DAOReservas daoReservas = new DAOReservas();
		try 
		{
			//////Transacción
			this.conn = darConexion();
			daoReservas.setConn(conn);
			reservas = daoReservas.darReservas(pIdCliente);

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
				daoReservas.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return reservas;
	}
	public ArrayList<String> darAerolineas() throws SQLException
	{
		ArrayList<String> aerolineas;
		DAOAerolineas daoAerolinea = new DAOAerolineas();
		try 
		{
			//////Transacción
			this.conn = darConexion();
			daoAerolinea.setConn(conn);
			aerolineas = daoAerolinea.darAerolineas();

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
				daoAerolinea.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return aerolineas;
	}
	
	public Reserva buscarReserva(int pId) throws SQLException
	{
		Reserva respuesta = null;
	    DAOReservas daoReservas = new DAOReservas();
	    try 
	    {
	      //////Transacción
	      this.conn = darConexion();
	      daoReservas.setConn(conn);
	      respuesta = daoReservas.buscarReserva(pId);

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
	        daoReservas.cerrarRecursos();
	        if(this.conn!=null)
	          this.conn.close();
	      } catch (SQLException exception) {
	        System.err.println("SQLException closing resources:" + exception.getMessage());
	        exception.printStackTrace();
	        throw exception;
	      }
	    }
	    return respuesta;
	}
	
	public void eliminarReserva(int pId) throws SQLException, Exception
	{
		DAOReservas daoReservas = new DAOReservas();
		this.conn = darConexion();
	    daoReservas.setConn(conn);
	    Savepoint s = conn.setSavepoint("eliminarReserva");
	    try 
	    {
	      //////Transacción
	      
	      daoReservas.eliminarReserva(pId);
	      } catch (SQLException e) {
	    	  conn.rollback(s);
	      System.err.println("SQLException:" + e.getMessage());
	      e.printStackTrace();
	      throw e;
	    } catch (Exception e) {
	      System.err.println("GeneralException:" + e.getMessage());
	      e.printStackTrace();
	      throw e;
	    } finally {
	      try {
	        daoReservas.cerrarRecursos();
	        if(this.conn!=null)
	          this.conn.close();
	      } catch (SQLException exception) {
	        System.err.println("SQLException closing resources:" + exception.getMessage());
	        exception.printStackTrace();
	        throw exception;
	      }
	    }
	}
	


	//----------------------------------------------------
	//RF8 y RF9
	//----------------------------------------------------

	public void crearReserva(String tipoIdCliente, int idCliente, Reserva r) throws Exception {
		DAOReservas dao = new DAOReservas();
		this.conn = darConexion();
		Savepoint s = conn.setSavepoint("crearReserva");
		try
		{
		
		dao.setConn(conn);
		dao.crearReserva(r, tipoIdCliente, idCliente);
		conn.close();
		}
		catch (SQLException e)
		{
			conn.rollback(s);
		}
	}

	//----------------------------------------------------
	//R10
	//----------------------------------------------------


	public void finalizarViaje(String idUsuario, String iata, Date fechaViaje) throws SQLException, Exception {
		conn = darConexion();
		DAOViajes dao = new DAOViajes();
		dao.setConn(conn);
		dao.finalizarViaje(idUsuario, iata, fechaViaje);
		dao.cerrarRecursos();
		conn.close();
	}
	
	public void cargarVertices() throws Exception 
	{
		ArrayList<Aeropuerto> aeropuertos = darAeropuertos();
		int contador = 0;
		for (int i = 0; i < aeropuertos.size(); i++)
		{
			grafo.addAeropuerto(aeropuertos.get(i));
			System.out.println(aeropuertos.get(i).getIATA());
			contador++;
		}
	}
	
	public void cargarArcos() throws Exception
	{
		try
		{
		ArrayList<Vuelo> vuelos= darVuelos();
		int contador = 0;
		for (int i = 0; i < vuelos.size(); i++)
		{
			Vuelo vuelo = vuelos.get(i);
			Aeropuerto from = buscarAeropuerto(vuelo.getOrigen());
			Aeropuerto to = buscarAeropuerto(vuelo.getDestino());
			grafo.addEdge(from, to);
			contador++;
		}
		}
		catch (Exception e)
		{
			e.getMessage();
		}
	}
	
	public void cargarDijkstra(Aeropuerto a)
	{
		try
		{
		ArrayList<Aeropuerto> vertexes = darAeropuertos();
		ArrayList<Vuelo> edges = darVuelos();
		grafoDijkstra = new DijkstraGraph(vertexes, edges);
		dijkstra = new DijkstraAlgorithm(grafoDijkstra, a);
		}
		catch (Exception e)
		{
			e.getMessage();
		}
	}
	
	public void crearReserva2(Reserva r, String tipoIdCliente, int idCliente) throws Exception
	{
		DAOReservas dao = new DAOReservas();
		this.conn = darConexion();
		dao.setConn(conn);
		Savepoint s = conn.setSavepoint("crear reserva 2");
		try {
		Vuelo vuelo = buscarVuelo(r.getIataVuelo());
		String origen = vuelo.getOrigen();
		String destino = vuelo.getOrigen();
		Aeropuerto a = buscarAeropuerto(origen);
		Aeropuerto b = buscarAeropuerto(origen);
		cargarDijkstra(a);
		ArrayList<Vuelo> vuelos = dijkstra.getPathEdges(b);
		
		for (int i = 0; i < vuelos.size(); i++)
		{
			Reserva nueva = r;
			Vuelo x = vuelos.get(i);
			nueva.setIataVuelo(x.getIATA());
			crearReserva(tipoIdCliente, idCliente, r);
		}
		}
		catch (SQLException e)
		{
			e.getMessage();
			conn.rollback(s);
		}
		
	}
	
	public Vuelo buscarVueloRespaldo(String origen, String destino)
	{
		Vuelo respuesta = null;
		try
		{
		ArrayList<Vuelo> vuelos = darVuelos();
		for (int i = 0; i < vuelos.size(); i++)
		{
			Vuelo a = vuelos.get(i);
			if (a.getOrigen() == origen && a.getDestino() == destino)
				respuesta = a;
		}
		}
		catch (Exception e)
		{
			e.getMessage();
		}
		return respuesta;
	}
	
	public void eliminarViaje(String IATA) throws SQLException
	{
		DAOVuelos daoVuelos = new DAOVuelos();
		DAOReservas daoReservas = new DAOReservas();
		this.conn = darConexion();
		Savepoint s = conn.setSavepoint("eliminarViaje");
		try
		{
		
		daoVuelos.setConn(conn);
		Vuelo original = buscarVuelo(IATA);
		Vuelo respaldo = buscarVueloRespaldo(original.getOrigen(), original.getDestino());
		daoReservas.setConn(conn);
		ArrayList<Reserva> reservas = daoReservas.darReservasVuelo(IATA);
		for (int i = 0; i < reservas.size(); i++ )
		{
			Reserva vieja = reservas.get(i);
			Viaje viaje = buscarViaje(respaldo.getIATA(), vieja.getFecha());
			Reserva r = new Reserva(0, vieja.getTipoIdCliente(), vieja.getIdCliente(), respaldo.getIATA(), viaje.getFecha(), viaje.getTipo(), viaje.getModelo(), vieja.getVolumen(), vieja.getPeso(), vieja.getContenido());
			crearReserva( vieja.getContenido(), vieja.getIdCliente(), r);
		}
		daoVuelos.eliminarVuelo(IATA);
		}
		catch (Exception e)
		{
			conn.rollback(s);
		}
	}

	public void RFC7(RFC7y8 objeto) throws SQLException 
	{
		DAOConsultas daoConsultas = new DAOConsultas();
		try 
		{
			this.conn = darConexion();
			daoConsultas.setConn(conn);
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			daoConsultas.RFC7(objeto);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				daoConsultas.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	public void RFC8(RFC7y8 objeto) throws SQLException 
	{
		DAOConsultas daoConsultas = new DAOConsultas();
		try 
		{
			this.conn = darConexion();
			daoConsultas.setConn(conn);
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			daoConsultas.RFC8(objeto);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				daoConsultas.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	public void RFC9(RFC9 objeto) throws SQLException 
	{
		DAOConsultas daoConsultas = new DAOConsultas();
		try 
		{
			this.conn = darConexion();
			daoConsultas.setConn(conn);
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			daoConsultas.RFC9(objeto);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				daoConsultas.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	
	public void RFC10(RFC10 objeto) throws SQLException 
	{
		DAOConsultas daoConsultas = new DAOConsultas();
		try 
		{
			this.conn = darConexion();
			daoConsultas.setConn(conn);
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			daoConsultas.RFC10(objeto);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				daoConsultas.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	public ArrayList<String> genearVuelos() throws Exception
	{
		ArrayList respuesta = new ArrayList<>();
		DAOVuelos daovuelos = new DAOVuelos();
		IATA ia = new IATA();
		ArrayList<String> codigos = ia.listaCombinaciones();
		ArrayList<Aeropuerto> aeropuertos = darAeropuertos();
		int aeroSize = aeropuertos.size();
		ArrayList<String> aerolineas = darAerolineas();
		int aeroliSize = aerolineas.size();
		String sql = "";
		for (int i = 2; i < 456976; i++)
		{
		String iata = codigos.get(i);
		String origen = aeropuertos.get(random.nextInt(aeroSize)+1).getIATA();
		String destino = aeropuertos.get(random.nextInt(aeroSize)+1).getIATA();
		String aerolinea = aerolineas.get(random.nextInt(aeroliSize+1));
		String tipo = "COMERCIAL";
		String frecuencia = ""+random.nextInt(8);
		String horaLlegada = random.nextInt(10)+""+random.nextInt(10)+":"+random.nextInt(10)+""+random.nextInt(10);
		String horaSalida = random.nextInt(10)+""+random.nextInt(10)+":"+random.nextInt(10)+""+random.nextInt(10);
		String distancia= ""+random.nextInt(2500);
		String duracion = random.nextInt(10)+""+random.nextInt(10)+":"+random.nextInt(10)+""+random.nextInt(10);
		String costoEconomico = ""+random.nextInt(2000);
		String costoEjecutivo = ""+random.nextInt(232323);
		String creador = "aerolinea5";
		sql = "INSERT INTO VUELOS(IATA, ORIGEN, DESTINO, AEROLINEA, TIPO, FRECUENCIA, HORA_LLEGADA, HORA_SALIDA, DISTANCIA, DURACION, COSTO_ECONOMICA, COSTO_EJECUTIVA, CREADOR) VALUES"
				+ "('"+iata+"', '"+origen+"', '"+destino+"', '"+aerolinea+"', '"+tipo+"', '"+frecuencia+"', '"+horaLlegada+"', '"+horaSalida+"', "+distancia+", '"+duracion+"',  '"+costoEconomico+"', "+costoEjecutivo+", '"+creador+"')";
		respuesta.add(sql);
		}
		return respuesta;
	}
	
	public void escribirArchivo() throws IOException, Exception
	{
			try 
			{
				ArrayList<String> array = genearVuelos();
				String path = "C:/Users/Sebas/Desktop/Sentencia/vuelos.txt";
				File file = new File(path);
				PrintWriter pw = new PrintWriter(file);
				for (int i = 0; i < array.size(); i++)
				{
					pw.println(array.get(i));
				}
				pw.close();
				System.out.println("termino");
			}
			catch (IOException e)
			{
				throw new IOException("Error al generar el archivo");
			}
	}
	
	public ArrayList<String> generarReservas() throws Exception
	{
		ArrayList<String> respuesta = new ArrayList<>();
		String sql;
		ArrayList<Cliente> clientes = darClientes();
		int sizeCliente = clientes.size();
		ArrayList<Viaje> viajes = darViajes();
		int sizeViajes = viajes.size();
		for (int i = 1; i < 10; i++)
		{
			Cliente cliente = clientes.get(random.nextInt(sizeCliente)+1);
			String tipoId = cliente.getTipoId();
			String idCliente = cliente.getId();
			Viaje viaje = viajes.get(random.nextInt(sizeViajes)+1);
			String iata = viaje.getIata();
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
			System.out.println(viaje.getFecha());
			String fecha = (String) format.format(viaje.getFecha());
			String tipo = "COMERCIAL";
			String tipoSilla;
			if (i%2 == 0)
				tipoSilla = "ECONOMICA";
			else
				tipoSilla = "EJECUTIVA";
		sql = "INSERT INTO RESERVAS(TIPO_ID_CLIENTE, ID_CLIENTE, IATA_VUELO, FECHA, TIPO, TIPO_SILLA, FECHA_RESERVA) VALUES "
				+ "('"+tipoId+"', "+idCliente+" , '"+iata+"' ,"+"to_date('"+fecha+"','mm/dd/yyyy')"+", '"+tipo+"' ,'"+tipoSilla+"', to_date('"+fecha+"','mm/dd/yyyy'));";
		System.out.println(sql);
		respuesta.add(sql);
		}
		return respuesta;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	



	
}
