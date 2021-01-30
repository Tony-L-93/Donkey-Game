package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Donkey2 
{
	//variables de instancia
	private int posx;
	private int posy;
	private int alto;
	private int ancho;
	private boolean lanzando;
	private Image monoLanzando;
	private Image monoEsperando;
	
	public Donkey2(int x, int y)
	{
		this.posx=x;
		this.posy=y;
		this.alto=80;
		this.ancho=40;
		this.lanzando=false;
		this.monoEsperando= Herramientas.cargarImagen("DonkeyEspera.png");
		this.monoLanzando= Herramientas.cargarImagen("DonkeyBarriles.png");
	}
	
	public void dibujarse(Entorno entorno)
	{
		if (lanzando)
		{
			entorno.dibujarImagen(this.monoLanzando, posx, posy,0,0.18);
		}
		else
		{
			entorno.dibujarImagen(this.monoEsperando, posx, posy,0,0.4);
		}
	}
	
	public Barril lanzarBarril() 
	{
		this.lanzando=true;
		int x_barril = this.posx;
		int y_barril = this.posy;
		
		return new Barril(x_barril, y_barril);
	}
	
	public void puedeLanzar()
	{
		this.lanzando=false;
	}
	
	public int getX()
	{
		return posx;
	}
	
	public int getY()
	{
		return posy;
	}
	
	public int getAlto()
	{
		return this.alto;
	}
	
	public int getAncho()
	{
		return this.ancho;
	}
}
