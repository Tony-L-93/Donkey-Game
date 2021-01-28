package juego;

import java.awt.Color;

import entorno.Entorno;

public class FugaDeGas 
{
	//Variables de instancia
	private int x;
	private int y;
	private int tamaño;
	private boolean abierto;
	private Color color;
	private double angulo;
	private int frecuenciaLlamas;
	
	public FugaDeGas (int x, int y,double angulo,int frec)
	{
		this.x=x;
		this.y=y;
		this.tamaño=20;
		this.abierto=true;
		this.color=color.BLUE;
		this.angulo=angulo;
		this.frecuenciaLlamas=frec; 
	}
	
	public void dibujarse(Entorno entorno)
	{
		entorno.dibujarTriangulo(x, y, this.tamaño/2, this.tamaño, this.angulo, this.color);
	}
	
	public Llamarada lanzarLlamas()
	{
		if (this.abierto)
		{	
			int xllamarada;
			if (this.angulo==0)
			{
				xllamarada = this.x+60;
			}
			else
			{
				xllamarada = this.x-60;
			}
			int yllamarada = this.y;		
			return new Llamarada(xllamarada, yllamarada);
			
		}
		return null;
	}
	
	public void apagarFuga(BotonLlamarada boton)
	{
		this.abierto=!boton.esPresionado();
	}
	
	public void colorFuga(int tiempo)
	{	
			if ((tiempo/(frecuenciaLlamas/5))%5==0)
				color=Color.BLUE;
			if ((tiempo/(frecuenciaLlamas/5))%5==1)
				color=Color.GREEN;
			if ((tiempo/(frecuenciaLlamas/5))%5==2)
				color=Color.YELLOW;
			if ((tiempo/(frecuenciaLlamas/5))%5==3)
				color=Color.ORANGE;
			if ((tiempo/(frecuenciaLlamas/5))%5==4)
				color=Color.RED;
	}
	
	public boolean isAbierto() 
	{
		return abierto;
	}

}