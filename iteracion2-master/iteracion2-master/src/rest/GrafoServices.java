package rest;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.VuelAndesMaster;
import vos.Usuario;

@Path("grafo")
@Produces({ MediaType.APPLICATION_JSON })
public class GrafoServices {


	@Context
	private ServletContext context;

	private String getPath() {
		return context.getRealPath("WEB-INF/ConnectionData");
	}


	private String doErrorMessage(Exception e){
		return "{ \"ERROR\": \""+ e.getMessage() + "\"}" ;
	}


	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public void cargarGrafo(){
		try {
			VuelAndesMaster tm = new VuelAndesMaster(getPath());
			tm.generarReservas();
		} catch (Exception e) {
		}
	}
}
