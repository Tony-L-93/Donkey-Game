package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class EscaleraRota 
{
	//Variables de instancia
	private int posX;
	private int ancho;
	private int inferiorY;
	private int superiorY;
	private Image  escalera;
	 	 
	public EscaleraRota(int posX, int ancho,int inferiorY,int superiorY)
	{
		 this.inferiorY=inferiorY;
		 this.ancho=ancho;
		 this.posX=posX;
		 this.superiorY=superiorY;
		 this.escalera = Herramientas.cargarImagen("escalaRota.png");
	}
	 
	public void dibujarse(Entorno entorno)
	 {
		{
			entorno.dibujarImagen(this.escalera, posX, (this.inferiorY-this.alturaEscalera()),0,0.34);	
		}
	 }
	
	private int alturaEscalera()
	{
		return (-this.superiorY+this.inferiorY);
	}
	
	public int getInferiorY() 
	{
		return inferiorY;
	}
	
	public int getSuperiorY() 
	{
		return superiorY;
	}
	
	public int getPosX() 
	{
		return posX;
	}
	
	public int getAncho() 
	{
		return ancho;
	}

}