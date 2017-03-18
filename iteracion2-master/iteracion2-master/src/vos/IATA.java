package vos;

import java.util.ArrayList;

public class IATA {
	
	private String actual;
	private int indice;
	public IATA()
	{
	
		actual = "AAAA";
		indice = 0;
	}
	
	
	
	
	public ArrayList<String> listaCombinaciones()
	{
		ArrayList<String> respuesta = new ArrayList<String>();
		int letters = 26;
		int count = 4;
		final int combinations = (int) Math.pow(letters, count);
		StringBuilder sb = new StringBuilder(count);
		for (int i = 0; i < combinations; i++) {
		    sb.setLength(0);
		    for (int j = 0, i2 = i; j < count; j++, i2 /= letters)
		        sb.insert(0, (char) ('A' + i2 % letters));
		    respuesta.add(sb.toString());
		}
		return respuesta;
	}
	
	public String numero() throws Exception
	{
		String respuesta ="";
		if (indice == 9999)
			throw new Exception("No hy más códigos");
		else  
		{
			if ( indice < 10)
				respuesta += "000"+indice;
			else if (indice < 100)
				respuesta += "00"+indice;
			else if (indice < 1000)
				respuesta += "0"+indice;
			else
				respuesta += indice;
			indice++;
		}
		return respuesta;
	}
	

}
