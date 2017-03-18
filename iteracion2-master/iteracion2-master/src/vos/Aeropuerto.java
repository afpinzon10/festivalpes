package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Aeropuerto implements Comparable<Aeropuerto> {

	@JsonProperty(value="iata")
	private String IATA;
	
	@JsonProperty(value="nombre")
	private String nombre;
	
	@JsonProperty(value="tipo")
	private String tipo;
	
	@JsonProperty(value="ciudad")
	private String ciudad;
	
	@JsonProperty(value="pais")
	private String pais;
	
	public Aeropuerto (@JsonProperty(value="iata") String pIATA, @JsonProperty(value="nombre") String pNombre, @JsonProperty(value="tipo")
	String pTipo, @JsonProperty(value="ciudad") String ciudad, @JsonProperty(value="pais") String pais)
	{
		this.ciudad = ciudad;
		this.pais = pais;
		this.IATA = pIATA;
		this.nombre = pNombre;
		this.tipo = pTipo;
	}

	public String getIATA() {
		return IATA;
	}

	public void setIATA(String iATA) {
		IATA = iATA;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}
	
	@Override
    public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((IATA == null) ? 0 : IATA.hashCode());
            return result;
    }
	
	 @Override
     public boolean equals(Object obj) {
		 
             if (this == obj)
                     return true;
             if (obj == null)
                     return false;
             if (getClass() != obj.getClass())
                     return false;
             Aeropuerto other = (Aeropuerto) obj;
             if (IATA == null) {
                     if (other.IATA != null)
                             return false;
             } else if (!IATA.equals(other.IATA))
                     return false;
             return true;
     }
	
	public int compareTo(Aeropuerto a) {
		int respuesta = 0;
		 if (this.IATA.compareTo(a.getIATA()) < 0)
				respuesta = -1;
		
		else if (this.IATA.compareTo(a.getIATA()) > 0)
			respuesta = 0;
		
		return respuesta;
	}
	
	public String toString()
	{
		return IATA;
	}

	
	
	
	
}
