package rest;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.VuelAndesMaster;
import vos.Aeropuerto;
import vos.Cliente;
import vos.Viaje;
import vos.Vuelo;

@Path("aeropuertos")
@Produces({ MediaType.APPLICATION_JSON })
public class AeropuertoServices {

	@Context
	private ServletContext context;
		
	private String getPath()
	{
		return context.getRealPath("WEB-INF/ConnectionData");
	}
		
	private String doErrorMessage(Exception e)
	{
		return "{ \"ERROR\": \""+ e.getMessage() + "\"}" ;
	}
	
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Response getAeropuertos()
	{
		VuelAndesMaster tm = new VuelAndesMaster(getPath());
		ArrayList<Aeropuerto> aeropuertos = new ArrayList();
		try
		{
			aeropuertos = tm.darAeropuertos();
		} catch(Exception e)
		{
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(aeropuertos).build();
	}
	
	
	@GET
	@Path("/{iata}/vuelos")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response darSalidasArribos(@PathParam("iata") String IATA)
	{
		VuelAndesMaster tm = new VuelAndesMaster(getPath());
		ArrayList<Vuelo> viajes = new ArrayList<>();
		try {
				viajes = tm.SalidasArribos(IATA);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(viajes).build();
	}
}
