package rest;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.VuelAndesMaster;
import vos.Usuario;
import vos.Vuelo;


@Path("vuelos")
@Produces({ MediaType.APPLICATION_JSON })
public class VueloServices {


	@Context
	private ServletContext context;

	private String getPath()
	{
		return context.getRealPath("WEB-INF/ConnectionData");
	}

	private String doErrorMessage(Exception e){
		return "{ \"ERROR\": \""+ e.getMessage() + "\"}" ;
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Response darVuelos()
	{
		ArrayList<Vuelo> vuelos = new ArrayList<>();
		try
		{
			VuelAndesMaster tm = new VuelAndesMaster(getPath());
			vuelos = tm.darVuelos();
		}
		catch (Exception e)
		{
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(vuelos).build();
	}
	
	@GET
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response darVuelo(@PathParam("id") String IATA)
	{
		Vuelo respuesta;
		try
		{
			VuelAndesMaster tm = new VuelAndesMaster(getPath());
			respuesta = tm.buscarVuelo(IATA);
		}
		catch (Exception e)
		{
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(respuesta).build();
	}
	
	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void eliminarVuelo(@PathParam("id") String IATA)
	{
		try
		{
			VuelAndesMaster tm = new VuelAndesMaster(getPath());
			tm.eliminarViaje(IATA);
		}
		catch (Exception e)
		{
			Response.status(500).entity(doErrorMessage(e)).build();
		}
	}
	
	
	
	
	
	
	
	
}
