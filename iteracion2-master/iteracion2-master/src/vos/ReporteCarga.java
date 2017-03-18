package vos;

public class ReporteCarga {

	private double capacidadVendida;
	
	private int costoCarga;
	
	private double producido;
	
	
	public ReporteCarga(double capacidadVendida, int costoCarga)
	{
		this.capacidadVendida = capacidadVendida;
		this.costoCarga = costoCarga;
		this.producido = costoCarga*capacidadVendida;
	}


	public double getCapacidadVendida() {
		return capacidadVendida;
	}


	public void setCapacidadVendida(double capacidadVendida) {
		this.capacidadVendida = capacidadVendida;
	}


	public int getCostoCarga() {
		return costoCarga;
	}


	public void setCostoCarga(int costoCarga) {
		this.costoCarga = costoCarga;
	}


	public double getProducido() {
		return producido;
	}


	public void setProducido(double producido) {
		this.producido = producido;
	}
	
	
	
	
	
}
