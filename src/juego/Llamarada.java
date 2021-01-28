package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Llamarada 
{
	//variables de instancia
	private int x;
	private int y;
	private int largoDeLlama;
	private boolean prendida;
	private Image llama;
	
	public Llamarada (int x, int y)
	{
		this.x=x;
		this.y=y;
		this.largoDeLlama=120;
		this.prendida=true;
		this.llama= Herramientas.cargarImagen("llama1.png");	
	}
	
	public void dibujarse (Entorno entorno)
	{
		if (this.prendida)
		{
			if (this.x<entorno.ancho()/2)
				entorno.dibujarImagen(this.llama, x, y, 0, 0.16);
			else
				entorno.dibujarImagen(this.llama, x, y, Math.PI, 0.16);
		}
	}

	public int getX() 
	{
		return x;
	}

	public int getY() 
	{
		return y;
	}

	public int getLargoDeLlama() 
	{
		return largoDeLlama;
	}

	public boolean isPrendida() 
	{
		return prendida;
	}
}
