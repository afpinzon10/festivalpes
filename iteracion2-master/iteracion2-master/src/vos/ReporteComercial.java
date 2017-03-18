package vos;

public class ReporteComercial {

	private int costoEconomicas;
	
	private int costoEjecutivas;
	
	private int sillasEconomicasVendidas;
	
	private int sillasEjecutivasVendidas;
	
	private int producidoEconomicas;
	
	private int producidoEjecutivas;
	
	private int producido;
	
	
	public ReporteComercial (int costoEconomicas, int costoEjecutivas, int sillasEconomicasVendidas, int sillasEjecutivasVendidas)
	{
		this.costoEconomicas = costoEconomicas;
		this.costoEjecutivas = costoEjecutivas;
		this.sillasEconomicasVendidas = sillasEconomicasVendidas;
		this.sillasEjecutivasVendidas = sillasEjecutivasVendidas;
		this.producidoEconomicas = costoEconomicas*sillasEconomicasVendidas;
		this.producidoEjecutivas = costoEjecutivas*sillasEjecutivasVendidas;
		this.producido = producidoEconomicas + producidoEjecutivas;
	}


	public int getCostoEconomicas() {
		return costoEconomicas;
	}


	public void setCostoEconomicas(int costoEconomicas) {
		this.costoEconomicas = costoEconomicas;
	}


	public int getCostoEjecutivas() {
		return costoEjecutivas;
	}


	public void setCostoEjecutivas(int costoEjecutivas) {
		this.costoEjecutivas = costoEjecutivas;
	}


	public int getSillasEconomicasVendidas() {
		return sillasEconomicasVendidas;
	}


	public void setSillasEconomicasVendidas(int sillasEconomicasVendidas) {
		this.sillasEconomicasVendidas = sillasEconomicasVendidas;
	}


	public int getSillasEjecutivasVendidas() {
		return sillasEjecutivasVendidas;
	}


	public void setSillasEjecutivasVendidas(int sillasEjecutivasVendidas) {
		this.sillasEjecutivasVendidas = sillasEjecutivasVendidas;
	}


	public int getProducidoEconomicas() {
		return producidoEconomicas;
	}


	public void setProducidoEconomicas(int producidoEconomicas) {
		this.producidoEconomicas = producidoEconomicas;
	}


	public int getProducidoEjecutivas() {
		return producidoEjecutivas;
	}


	public void setProducidoEjecutivas(int producidoEjecutivas) {
		this.producidoEjecutivas = producidoEjecutivas;
	}


	public int getProducido() {
		return producido;
	}


	public void setProducido(int producido) {
		this.producido = producido;
	}
	
	
}
