package vos;

import java.sql.Date;
import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonProperty;

public class RFC10 {

	@JsonProperty(value="aeropuerto1")
	private String aeropueto1;
	
	@JsonProperty(value="aeropuerto2")
	private String aeropuerto2;
	
	@JsonProperty(value="fecha1")
	private String fecha1;
	
	@JsonProperty(value="fecha2")
	private String fecha2;
	
	@JsonProperty(value="viajes")
	private ArrayList<Viaje2> viajes;
	
	
	public RFC10 (@JsonProperty(value="aeropuerto1") String aeropueto1,
			@JsonProperty(value="aeropuerto2")String aeropuerto2,
			@JsonProperty(value="fecha1")String fecha1,
			@JsonProperty(value="fecha2") String fecha2){
		this.aeropueto1 = aeropueto1;
		this.aeropuerto2 = aeropuerto2;
		this.fecha1 = fecha1;
		this.fecha2 = fecha2;
		viajes = new ArrayList<Viaje2>();
	}


	public String getAeropueto1() {
		return aeropueto1;
	}


	public void setAeropueto1(String aeropueto1) {
		this.aeropueto1 = aeropueto1;
	}


	public String getAeropuerto2() {
		return aeropuerto2;
	}


	public void setAeropuerto2(String aeropuerto2) {
		this.aeropuerto2 = aeropuerto2;
	}


	public String getFecha1() {
		return fecha1;
	}


	public void setFecha1(String fecha1) {
		this.fecha1 = fecha1;
	}


	public String getFecha2() {
		return fecha2;
	}


	public void setFecha2(String fecha2) {
		this.fecha2 = fecha2;
	}
	
	public void addViaje(Viaje2 viaje)
	{
		viajes.add(viaje);
	}
	
}
