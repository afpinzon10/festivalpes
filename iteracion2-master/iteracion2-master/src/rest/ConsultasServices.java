package rest;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.VuelAndesMaster;
import vos.*;


@Path("consultas/")
@Produces({ MediaType.APPLICATION_JSON })
public class ConsultasServices {

		@Context
		private ServletContext context;
		
		private String getPath() {
			return context.getRealPath("WEB-INF/ConnectionData");
		}
		
		
		private String doErrorMessage(Exception e){
			return "{ \"ERROR\": \""+ e.getMessage() + "\"}" ;
		}
		
		@GET
		@Path("RFC7/{aeropuerto}")
		@Consumes(MediaType.APPLICATION_JSON)
		public Response RFC7(@PathParam("aeropuerto")String aeropuerto,
				@DefaultValue("null") @QueryParam("hora_llegada") String horaLlegada, 
				@DefaultValue("null") @QueryParam("hora_salida") String horaSalida,
				@DefaultValue("null") @QueryParam("fecha_inicial") String fechaInicial, 
				@DefaultValue("null") @QueryParam("fecha_final") String fechaFinal,
				@DefaultValue("null") @QueryParam("tipo_aeronave") String tipoAeronave,
				@DefaultValue("null") @QueryParam("aerolinea") String aerolinea)
		{
			RFC7y8 objeto = new RFC7y8(aeropuerto, horaLlegada, horaSalida, fechaInicial, fechaFinal, tipoAeronave, aerolinea);
			try
			{
				VuelAndesMaster tm = new VuelAndesMaster(getPath());
				tm.RFC7(objeto);
			}
			catch (Exception e)
			{
				return Response.status(500).entity(doErrorMessage(e)).build();
			}
			return Response.status(200).entity(objeto).build();
		}
		
		
		@GET
		@Path("RFC8/{aeropuerto}")
		@Consumes(MediaType.APPLICATION_JSON)
		public Response RFC8(@PathParam("aeropuerto")String aeropuerto,
				@DefaultValue("null") @QueryParam("hora_llegada") String horaLlegada, 
				@DefaultValue("null") @QueryParam("hora_salida") String horaSalida,
				@DefaultValue("null") @QueryParam("fecha_inicial") String fechaInicial, 
				@DefaultValue("null") @QueryParam("fecha_final") String fechaFinal,
				@DefaultValue("null") @QueryParam("tipo_aeronave") String tipoAeronave,
				@DefaultValue("null") @QueryParam("aerolinea") String aerolinea)
		{
			RFC7y8 objeto = new RFC7y8(aeropuerto, horaLlegada, horaSalida, fechaInicial, fechaFinal, tipoAeronave, aerolinea);
			try
			{
				VuelAndesMaster tm = new VuelAndesMaster(getPath());
				tm.RFC8(objeto);
			}
			catch (Exception e)
			{
				return Response.status(500).entity(doErrorMessage(e)).build();
			}
			return Response.status(200).entity(objeto).build();
		}
		
		@GET
		@Path("RFC9/{distancia}")
		@Consumes(MediaType.APPLICATION_JSON)
		public Response RFC9(@PathParam("distancia")String distancia, @DefaultValue("null") @QueryParam("usuario") String usuario, 
				@DefaultValue("null") @QueryParam("fecha_inicial") String fechaInicial, 
				@DefaultValue("null") @QueryParam("fecha_final") String fechaFinal)
		{
			int distanciaI = Integer.parseInt(distancia);
			RFC9 objeto = new RFC9(distanciaI, usuario, fechaInicial, fechaFinal);
			System.out.println(objeto);
			try
			{
				VuelAndesMaster tm = new VuelAndesMaster(getPath());
				tm.RFC9(objeto);
			}
			catch (Exception e)
			{
				return Response.status(500).entity(doErrorMessage(e)).build();
			}
			return Response.status(200).entity(objeto).build();
		}
		
		
		@GET
		@Path("RFC10/{aeropuerto1}-{aeropuerto2}")
		@Consumes(MediaType.APPLICATION_JSON)
		public Response RFC10(@PathParam("aeropuerto1")String aeropuerto1, 
				@PathParam("aeropuerto2")String aeropuerto2,
				@DefaultValue("null") @QueryParam("fecha_inicial") String fecha1, 
				@DefaultValue("null") @QueryParam("fecha_final") String fecha2)
		{
			RFC10 objeto = new RFC10(aeropuerto1, aeropuerto2, fecha1, fecha2);
			System.out.println(objeto);
			try
			{
				VuelAndesMaster tm = new VuelAndesMaster(getPath());
				tm.RFC10(objeto);
				tm.generarReservas();
			}
			catch (Exception e)
			{
				return Response.status(500).entity(doErrorMessage(e)).build();
			}
			return Response.status(200).entity(objeto).build();
		}
		
		
}
