package vos;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class Funcion {
	
	private ArrayList<Boleta> boletas;
	
	@JsonProperty(value="idfuncion")
	private int idfuncion;
	
	@JsonProperty(value="fecha")
	private Date fecha;
	
	@JsonProperty(value="hora")
	private Time hora;
	
	@JsonProperty(value="idespacio")
	private int idespacio;
	
	@JsonProperty(value="idobra")
	private int idobra;

	public Funcion(ArrayList<Boleta> boletas, @JsonProperty(value="idfuncion") int idfuncion, 
			@JsonProperty(value="fecha") Date fecha, @JsonProperty(value="hora") Time hora, 
			@JsonProperty(value="idespacio") int idespacio, @JsonProperty(value="idobra") int idobra) {
		this.boletas = boletas;
		this.idfuncion = idfuncion;
		this.fecha = fecha;
		this.hora = hora;
		this.idespacio = idespacio;
		this.idobra = idobra;
	}

	public ArrayList<Boleta> getBoletas() {
		return boletas;
	}

	public void setBoletas(ArrayList<Boleta> boletas) {
		this.boletas = boletas;
	}

	public int getIdfuncion() {
		return idfuncion;
	}

	public void setIdfuncion(int idfuncion) {
		this.idfuncion = idfuncion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Time getHora() {
		return hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}

	public int getIdespacio() {
		return idespacio;
	}

	public void setIdespacio(int idespacio) {
		this.idespacio = idespacio;
	}

	public int getIdobra() {
		return idobra;
	}

	public void setIdobra(int idobra) {
		this.idobra = idobra;
	}
	
	
}
