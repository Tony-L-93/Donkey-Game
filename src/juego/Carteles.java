package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Carteles 
{
	//variables de instancia
	private int posCartelX;
	private int posCartelY;
	private Image img_winner,img_looser,img_stars;
	
	public Carteles()
	{
		this.posCartelX=400;
		this.posCartelY=300;
		this.img_looser=Herramientas.cargarImagen("gameOver.png");
		this.img_winner=Herramientas.cargarImagen("YouWin.png");
		this.img_stars=Herramientas.cargarImagen("Stars.gif");
	}
	
	public void dibujarseGanador(Entorno entorno)
	{
		entorno.dibujarImagen(this.img_stars, this.posCartelX, this.posCartelY, 0, 1.6);
		entorno.dibujarImagen(this.img_winner, this.posCartelX, this.posCartelY,0, 0.9);
	}
	
	public void dibujarsePerdedor(Entorno entorno)
	{
		entorno.dibujarImagen(this.img_looser, this.posCartelX, this.posCartelY,0, 0.45);	
	}
}
