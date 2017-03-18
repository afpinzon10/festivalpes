package rest;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.VuelAndesMaster;
import vos.Cliente;
import vos.Reserva;

@Path("clientes")
@Produces({ MediaType.APPLICATION_JSON })
public class ReservasServices {

	VuelAndesMaster tm = new VuelAndesMaster(getPath());

	@Context
	private ServletContext context;
	
	private String getPath() {
		return context.getRealPath("WEB-INF/ConnectionData");
	}
	
	
	private String doErrorMessage(Exception e){
		return "{ \"ERROR\": \""+ e.getMessage() + "\"}" ;
	}
	
	/**
	 * R8 y R9
	 * @return
	 */
	@POST
	@Path("/{tipo}-{id: \\d+}/reserva")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crearReserva(@PathParam("tipo") String tipoIdCliente, @PathParam("id") int idCliente, Reserva r){
		try {
			tm.crearReserva(tipoIdCliente, idCliente, r);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(r).build();
	}
	
	@POST
	@Path("/{tipo}-{id: \\d+}/reserva2")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crearReserva2(@PathParam("tipo") String tipoIdCliente, @PathParam("id") int idCliente, Reserva r){
		try {
			tm.crearReserva2(r, tipoIdCliente, idCliente);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(r).build();
	}
	
	
	
	@GET
	@Path("/{idCliente}/reservas")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response darReservasCliente(@PathParam("idCliente") int pIdCliente)
	{
		VuelAndesMaster tm = new VuelAndesMaster(getPath());
		ArrayList<Reserva> reservas = new ArrayList<Reserva>();
		try
		{
			reservas = tm.darReservasCliente(pIdCliente);
		}
		catch(Exception e)
		{
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(reservas).build();
	}
	
	@GET
	@Path("/{idCliente}/reservas/{numeroReserva}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response buscarReservaCliente(@PathParam("numeroReserva") int pNumeroReserva)
	{
		VuelAndesMaster tm = new VuelAndesMaster(getPath());
		Reserva respuesta = null;
		try
		{
			respuesta = tm.buscarReserva(pNumeroReserva);
		}
		catch(Exception e)
		{
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(respuesta).build();
	}
	
	@DELETE
	@Path("/{idCliente}/reservas/{numeroReserva}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void eliminarReserva(@PathParam("numeroReserva") int pNumeroReserva)
	{
		VuelAndesMaster tm = new VuelAndesMaster(getPath());
		try
		{
			tm.eliminarReserva(pNumeroReserva);
		}
		catch(Exception e)
		{
			System.out.println("Error eliminar reserva reservasServices");
		}
	}
}
