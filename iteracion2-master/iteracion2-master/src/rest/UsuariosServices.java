package rest;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.FestivAndes;
import tm.VuelAndesMaster;
import vos.Administra;
import vos.Opera;
import vos.Usuario;

@Path("usuarios")
@Produces({ MediaType.APPLICATION_JSON })
public class UsuariosServices {
	

	@Context
	private ServletContext context;
	
	private String getPath() {
		return context.getRealPath("WEB-INF/ConnectionData");
	}
	
	
	private String doErrorMessage(Exception e){
		return "{ \"ERROR\": \""+ e.getMessage() + "\"}" ;
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registrarUsuario(Usuario u){
		try {
			FestivAndes tm = new FestivAndes(getPath());
			tm.registrarUsuario(u);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(u).build();
	}
	
	@POST
	@Path("/{idUsuario}/aerolinea/{aerolinea}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response asociarUsuarioAerolinea(@PathParam("idUsuario") String id, @PathParam("aerolinea") String aerolinea){
		Administra a = new Administra(id, aerolinea);
		try {
			VuelAndesMaster tm = new VuelAndesMaster(getPath());
			tm.asociarUsuarioAerolinea(a);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(a).build();
	}
	
	@POST
	@Path("/{idUsuario}/aeropuerto/{aeropuerto}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response asociarUsuarioAeropuertoa(@PathParam("idUsuario") String id, @PathParam("aeropuerto") String aeropuerto){
		Opera o = new Opera(id, aeropuerto);
		try {
			VuelAndesMaster tm = new VuelAndesMaster(getPath());
			tm.asociarUsuarioAeropuerto(o);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(o).build();
	}
	
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Response darUsuarios()
	{
		ArrayList<Usuario> usuarios = new ArrayList<>();
		try
		{
			VuelAndesMaster tm = new VuelAndesMaster(getPath());
			usuarios = tm.darUsuarios();
		}
		catch (Exception e)
		{
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(usuarios).build();
	}
	
	@GET
	@Path("/{idUsuario}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response buscarUsuario(@PathParam("idUsuario") String id)
	{
		Usuario respuesta = null;
		try
		{
			VuelAndesMaster tm = new VuelAndesMaster(getPath());
			respuesta = tm.buscarUsuario(id);
		}
		catch (Exception e)
		{
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(respuesta).build(); 
	}
	
	
	
}
