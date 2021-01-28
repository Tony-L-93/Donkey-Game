package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas; 

public class Piso 
{
	//variables de instancia
	private int alto;
	private int ancho;
	private int inicioX;
	private int inicioY;
	private Image viga;
	
	public Piso(int alto, int ancho, int inicioX,int inicioY)
	{
		this.alto=alto;
		this.ancho=ancho;
		this.inicioX=inicioX;
		this.inicioY=inicioY;
		this.viga= Herramientas.cargarImagen("vigueta.png");
	}
	
	public void dibujarse(Entorno entorno)
	{
		int centroX=this.inicioX+this.ancho/2;
		int centroY=this.inicioY-this.alto/2;
		entorno.dibujarImagen(this.viga, centroX, centroY, 0, 1);
	}
	
	public int getAlto() 
	{
		return alto;
	}

	public int getAncho() 
	{
		return ancho;
	}

	public int getInicioX() 
	{
		return inicioX;
	}

	public int getInicioY() 
	{
		return inicioY;
	}

}
	