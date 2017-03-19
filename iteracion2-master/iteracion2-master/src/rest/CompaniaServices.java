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

import tm.FestivAndes;
import tm.VuelAndesMaster;
import vos.Cliente;
import vos.Compania;


@Path("")
@Produces({ MediaType.APPLICATION_JSON })

public class CompaniaServices {

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
	
	@POST
	@Path("usuarios/{idUsuario}/companias")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registrarCliente(Compania c){
		try {
			FestivAndes tm = new FestivAndes(getPath());
			tm.registrarCompania(c);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(c).build();
	}
	
}
