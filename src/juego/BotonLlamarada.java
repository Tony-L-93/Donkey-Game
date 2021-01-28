package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class BotonLlamarada 
{
	//variables de instancia
	private int x;
	private int y;
	private int ancho;
	private boolean presionado;
	private Image botonOn, botonOff;
	
	public BotonLlamarada (int x, int y)
	{
		this.x=x;
		this.y=y;
		this.ancho=20;
		this.presionado=false;
		this.botonOn= Herramientas.cargarImagen("botonOn.png");
		this.botonOff= Herramientas.cargarImagen("botonOff.png");
	}

	public void dibujarse(Entorno entorno)
	{
		if (this.presionado)
		{
			entorno.dibujarImagen(this.botonOff, this.x, this.y-35,0,0.4);
		}
		else
		{
			entorno.dibujarImagen(this.botonOn, this.x, this.y-35,0,0.4);
		}
	}
	
	public boolean esPresionado() 
	{
		return presionado;
	}

	public int getAncho() 
	{
		return ancho;
	}

	public void pulsarBoton()
	{
		if (this.presionado)
			this.presionado=false;
		else
			this.presionado=true;
	}
	
	public int getX() 
	{
		return x;
	}

	public int getY() 
	{
		return y;
	}
}
