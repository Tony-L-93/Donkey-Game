package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Barril 
{
	//variables de instancia
	private int x;
	private int y;
	private int tamaño;
	public boolean cayendo;
	private boolean avanzando;
	private Image imagenBarril;
	
	public Barril(int x,int y)
	{
		this.x=x;
		this.y=y;
		this.tamaño=30;
		this.cayendo=false;
		this.avanzando=true;
		this.imagenBarril = Herramientas.cargarImagen("barriljuego.png");			
	}
	
	public void dibujarse(Entorno entorno)
	{
		entorno.dibujarImagen(this.imagenBarril, x, y, x/30.0+y/10, 0.12);
	}
	
	public void rodar(int dx,int bordeIzq,int bordeDer)
	{
		if(!cayendo)
		{
				if (avanzando)//hacia la derecha
					if (bordeDer-(this.x+this.tamaño/2)>dx)
					{
						this.x+=dx;
					}
					else
					{
						this.x+=(bordeDer-(this.x+this.tamaño/2));
					}
				else //hacia izquierda
					if (((this.x-this.tamaño/2)-bordeIzq)>dx)
					{
						this.x-=dx;
					}
					else
					{
						this.x-=((this.x-this.tamaño/2)-bordeIzq);
					}
				
				if (this.x+this.tamaño/2 == bordeDer)
					avanzando = false;
				
				if (this.x-this.tamaño/2 == bordeIzq)
					avanzando = true;
		}
	}	
	
	public void caer(int dy,Piso[] piso)	
	{	
	if (this.cayendo)
			{
				this.y+=dy;	
				if (estaEnSuelo(piso) && this.pisoQueEsta(piso)!=piso.length-1)
				{
					if (this.avanzando)
					{
						this.avanzando=false;
					}
					else
					{
						this.avanzando=true;
					}
				}
			}
	this.cayendo=!(estaEnSuelo(piso));
	}
	
	public boolean estaEnSuelo(Piso[] piso)
	{
		for (int i=0;i<piso.length;i++)
		{
			int suelo= (piso[i].getInicioY()-piso[i].getAlto());
			
			if (this.y+this.tamaño/2==suelo)
			{
				if (this.x>=(piso[i].getInicioX()-this.tamaño/2) && this.x<=(piso[i].getInicioX()+piso[i].getAncho()+this.tamaño/2))
				{
					return true;
				}
			}
		}
		return false;
	}
	
	public int pisoQueEsta(Piso[] pisos) 
	{
		for (int j=0;j<pisos.length-1;j++)
		{
			if ((this.y<=(pisos[j].getInicioY()-pisos[j].getAlto()-((this.tamaño)/2))) &&
			   (this.y>(pisos[j+1].getInicioY()-pisos[j+1].getAlto()-(this.tamaño/2))))
			{
			
				return j;
			}
		}
		
		return (pisos.length-1);
	}
	
	public int getX() 
	{
		return x;
	}

	public int getY() 
	{
		return y;
	}
	
	public int getTamaño()
	{
		return this.tamaño;
	}

}
