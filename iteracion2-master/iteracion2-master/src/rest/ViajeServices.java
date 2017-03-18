package rest;

import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.VuelAndesMaster;
import vos.Administra;
import vos.Avion;
import vos.ReporteCarga;
import vos.ReporteComercial;
import vos.Viaje;
import vos.Vuelo;



@Path("")
@Produces({ MediaType.APPLICATION_JSON })
public class ViajeServices {
	
	VuelAndesMaster tm = new VuelAndesMaster(getPath());

	@Context
	private ServletContext context;
		
	private String getPath()
	{
		return context.getRealPath("WEB-INF/ConnectionData");
	}
	
	private String doErrorMessage(Exception e){
		return "{ \"ERROR\": \""+ e.getMessage() + "\"}" ;
	}
	
	
	@Path("usuarios/{idUsuario}/vuelos/{iata}/viajes/{id}-{fecha}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response updateViaje(Avion avion, @PathParam("idViaje") String id, @PathParam("fecha") Date fecha) throws SQLException, Exception
	{		Viaje viaje = tm.buscarViaje(id, fecha);
		try {
			tm.updateViaje(avion, id, fecha);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(viaje).build();
	}
	

	@Path("usuarios/{idUsuario}/vuelos/{iata}/viajes/{id}-{fecha}")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response generarReporte(@PathParam("idViaje") String id, @PathParam("fecha") Date fecha) throws SQLException, Exception
	{
		Viaje viaje = tm.buscarViaje(id, fecha);
		Avion avion  = tm.buscarAvion(viaje.getNumeroSerie(), viaje.getModelo());
		Vuelo vuelo = tm.buscarVuelo(id);
		if (viaje.getTipo().equalsIgnoreCase("CARGA"))
		{
			double capacidadVendida = avion.getCapacidad() - viaje.getCapacidadDisponible();
			int costoCarga = vuelo.getTarifaDensidad();
			ReporteCarga carga = new ReporteCarga(capacidadVendida, costoCarga);
			return Response.status(200).entity(carga).build();
		}
		else
		{
			int costoEconomicas = vuelo.getTarifaEconomica();
			int costoEjecutivas = vuelo.getTarifaEjecutiva();
			int sillasEconomicasVendidas = avion.getSillasEconomicas() - viaje.getSillasEconomicasDisponibles();
			int sillasEjecutivasVendidas = avion.getSillasEjecutivas() - viaje.getSillasEjecutivasdisponibles();
			ReporteComercial comercial = new ReporteComercial(costoEconomicas, costoEjecutivas, sillasEconomicasVendidas, sillasEjecutivasVendidas);
			return Response.status(200).entity(comercial).build();
		}
		
	}
	
	@PUT
	@Path("viajes/{iata}-{fechaViaje}/finalizar")
	public Response finalizarViaje(@HeaderParam("idUsuario") String idUsuario, @PathParam("iata") String iata, @PathParam("fechaViaje") String fecha )throws Exception{
		Date fechaViaje = new Date(Long.valueOf(fecha).longValue());
		try {
			tm.finalizarViaje(idUsuario, iata, fechaViaje);
		} catch (SQLException e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity("Vuelo finalizado").build();
	}
}
