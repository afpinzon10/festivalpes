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

@Path("")
@Produces({ MediaType.APPLICATION_JSON })
public class ClienteServices
{
		
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
	@Path("usuarios/{idUsuario}/clientes")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response darClientesUsuario(@PathParam("idUsuario") String id)
	{
		System.out.println("prueba");
		VuelAndesMaster tm = new VuelAndesMaster(getPath());
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		try
		{
			clientes = tm.darClientesUsuario(id);
		}
		catch(Exception e)
		{
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(clientes).build();
	}
	
	
	@GET
	@Path("clientes")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response darClientes()
	{
		VuelAndesMaster tm = new VuelAndesMaster(getPath());
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		try
		{
			clientes = tm.darClientes();
		}
		catch(Exception e)
		{
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(clientes).build();
	}
	
	
	@POST
	@Path("usuarios/{idUsuario}/clientes")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registrarCliente(Cliente c){
		try {
			FestivAndes tm = new FestivAndes(getPath());
			tm.registrarCliente(c);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(c).build();
	}
	

}
	
