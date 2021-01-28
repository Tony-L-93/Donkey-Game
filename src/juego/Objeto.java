package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Objeto
{
	// Variables de instancia
	private int x;
	private int y;
	private boolean gravedad;
	//private Image escalera, pisos;
	
	public Objeto(int x, int y)
	{
		this.x=x;
		this.y=y;
		this.gravedad=true;
	}
	
	public void dibujarseEscalera(Entorno entorno)
	{
		entorno.dibujarRectangulo(this.x,this.y, 30, 114, 0, Color.BLACK);
	}
	
	public void dibujarsePiso(Entorno entorno)
	{
		entorno.dibujarRectangulo(this.x,this.y, entorno.ancho()-100, 30, 0, Color.DARK_GRAY);
	}
	
	public void dibujarsePisoPrincipal(Entorno entorno)
	{
		entorno.dibujarRectangulo(this.x,this.y, entorno.ancho(), 30, 0, Color.DARK_GRAY);
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
