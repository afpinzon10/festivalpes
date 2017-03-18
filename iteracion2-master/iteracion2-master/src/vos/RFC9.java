package vos;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonProperty;

public class RFC9 {
	
	@JsonProperty(value="distancia")
	private int distancia;
	
	@JsonProperty(value="usuario")
	private String usuario;

	@JsonProperty(value="fecha_inicial")
	private String fecha_inicial;
	
	@JsonProperty(value="fecha_final")
	private String fecha_final;
	
	@JsonProperty(value="viajes")
	private ArrayList<Viaje> viajes;
	
	
	public RFC9(int distancia, String usuario, String fechaInicial, String fechaFinal)
	{
		this.distancia = distancia;
		this.usuario= usuario;
		this.fecha_inicial=fechaInicial;
		this.fecha_final=fechaFinal;
		viajes = new ArrayList<>();
	}


	public String getUsuario() {
		return usuario;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	public String getFecha_inicial() {
		return fecha_inicial;
	}


	public void setFecha_inicial(String fecha_inicial) {
		this.fecha_inicial = fecha_inicial;
	}


	public String getFecha_final() {
		return fecha_final;
	}


	public void setFecha_final(String fecha_final) {
		this.fecha_final = fecha_final;
	}


	public ArrayList<Viaje> getViajes() {
		return viajes;
	}

	public void setViajes(ArrayList<Viaje> viajes) {
		this.viajes = viajes;
	}
	
	public int getDistancia() {
		return distancia;
	}


	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}


	public void addViajes(Viaje viaje)
	{
		viajes.add(viaje);
	}
	
	
}
