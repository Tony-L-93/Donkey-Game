package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Vidas 
{
	private int vidas;
	private int posVidaX;
	private int posVidaY;
	private Image img_corazon; 

	public Vidas()
	{
		this.vidas= 3;
		this.posVidaX=600;
		this.posVidaY=25;
		this.img_corazon=Herramientas.cargarImagen("corazon.png");
	}
		
	public void dibujarseVidas(Entorno entorno, int x)
	{
		entorno.dibujarImagen(this.img_corazon, this.posVidaX+x, this.posVidaY,0, 0.03);
	}
	
	public void SetVidas(int numero)
	{
		this.vidas -= numero;
	}
	
	public int GetVidas()
	{
		return this.vidas;
	}	
}
 