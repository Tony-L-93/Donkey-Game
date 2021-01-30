package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Jumpman 
{
	//variables de instancia
	private int x;
	private int y;
	private int alto;
	private int ancho;
	private boolean saltando;
	private boolean subeSaltando;
	private boolean escalando;
	private boolean avanzando;
	private boolean cayendo;
	private Image img_ida1,img_ida2, img_vuelta1, img_vuelta2,imgSaltaD,imgSaltaI;
	
	public Jumpman(int x1,int y1)
	{
		this.x=x1;
		this.y=y1;
		this.alto=60;
		this.ancho=30;
		this.saltando=false;
		this.subeSaltando=false;
		this.escalando=false;
		this.img_ida1 = Herramientas.cargarImagen("jumpanDerecha.png");
		this.img_ida2 = Herramientas.cargarImagen("jumpmanDerecha2.png");
		this.img_vuelta1 = Herramientas.cargarImagen("jumpanIzquierda.png");
		this.img_vuelta2 = Herramientas.cargarImagen("jumpmanIzquierda2.png");
		this.imgSaltaD = Herramientas.cargarImagen("JumpmanSaltaDerecha.png");
		this.imgSaltaI = Herramientas.cargarImagen("JumpmanSaltaIzquierda.png");
		this.avanzando=true;
		this.cayendo=false; 
	}

	public void dibujarse(Entorno entorno)
	{
		if (avanzando)
		{
			if (saltando)
			{
				entorno.dibujarImagen(this.imgSaltaD, x, y, x/15,  0.22);
			}
			else
			{	
				if ((this.x/20)%2==0)
			
					entorno.dibujarImagen(img_ida1, x, y, 0, 0.3);
				else
					entorno.dibujarImagen(img_ida2, x, y, 0, 0.3);
			}	
		}
		
		else
		{
			if (saltando)
			{
				entorno.dibujarImagen(this.imgSaltaI, x, y, x/15, 0.22);
 	 		}
			else
			{	
				if ((this.x/20)%2==0)
					entorno.dibujarImagen(img_vuelta2, x, y, 0, 0.3);
				else
					entorno.dibujarImagen(img_vuelta1, x, y, 0, 0.3);
			} 
		}
	}
	
	public void moverHorizontal(int dx,int limiteIzq, int limiteDer)
	{
		if (!this.escalando && !this.cayendo)
		{
			if((this.x-this.ancho/2)>=0 && dx<0 )
			{
				this.x = this.x + dx;
				this.avanzando=false;
				if (this.x+this.ancho/2<limiteIzq)
					this.cayendo=true;
				else
					this.cayendo=false;
			}
			
			if((this.x+this.ancho/2)<=800 && dx>0 )
			{
				this.x = this.x + dx;
				this.avanzando=true;
				if (this.x-this.ancho/2>limiteDer)
					this.cayendo=true;
				else
					this.cayendo=false;
			}
		}
	}
	
	public void caer(int dy,Piso[] piso)	
	{	
		if (this.cayendo && !this.saltando)
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
		this.cayendo=(!(estaEnSuelo(piso))&&!this.escalando&&!this.saltando);
	}
	
	public void subirEscaleras(int dy,int inf,int sup)
	{
		{
			if((this.y+this.alto/2)<=(inf) && dy<0 && (this.y+this.alto/2)>sup)//sube
				if ((inf-(this.y+this.alto/2))>dy)
				{
					this.y = this.y + dy;
				}
				else
				{
					this.y= this.y + inf-(this.y+this.alto/2);
				}
		
			if((this.y+this.alto/2)>=sup && dy>0 && (this.y+this.alto/2)<inf)//baja
				if (((this.y+this.alto/2)-inf)<=dy)
				{
					this.y = this.y + dy;
				}
				else
				{
					this.y= this.y + (inf-(this.y+this.alto/2));
				}
			this.escalando=!(((this.y+this.alto/2)==sup)||((this.y+this.alto/2)==inf));
		}
	}
	
	public void subirEscalerasRotas(int dy,int inf,int sup)
	{
		{
			if((this.y+this.alto/2)<=(inf) && dy<0 && ((this.y+this.alto/2)>sup+1))//sube
				if ((inf-(this.y+this.alto/2))>dy)
				{
					this.y = this.y + dy;
				}
				else
				{
					this.y= this.y + inf-(this.y+this.alto/2);
				}
		
			if((this.y+this.alto/2)>=sup && dy>0 && (this.y+this.alto/2)<inf)//baja
				if (((this.y+this.alto/2)-inf)<=dy)
				{
					this.y = this.y + dy;
				}
				else
				{
					this.y= this.y + (inf-(this.y+this.alto/2));
				}
			this.escalando=!(((this.y+this.alto/2)==inf));
		}
	}

	public void saltar(int pisoSalto)
	{
		int alturaSalto=46;
		if(saltando)
			if(this.subeSaltando)
			{
				this.y=this.y-2;
				this.subeSaltando=((this.y-this.alto/2)>pisoSalto-alturaSalto);
			}
				
			else
				
			{
				this.y=this.y+2;
				this.saltando=((this.y-this.alto/2)<pisoSalto);
			}
	}
	
	public boolean estaEnEscalera(Escalera escalera)
	{
		return ((!this.saltando)&&escalera!=null&&((escalera.getInferiorY()>=(this.y+this.alto/2))&&
				escalera.getSuperiorY()<=(this.y+this.alto/2))&&
				(escalera.getPosX()-escalera.getAncho()/2)<=this.x&&
				(escalera.getPosX()+escalera.getAncho()/2)>=this.x);
	}
	
	public boolean estaEnEscalera(EscaleraRota escaleraRota)
	{
		return ((!this.saltando)&&escaleraRota!=null&&((escaleraRota.getInferiorY()>=(this.y+this.alto/2))&&
				escaleraRota.getSuperiorY()<= (this.y+this.alto/2))&&
				(escaleraRota.getPosX()-escaleraRota.getAncho()/2)<=this.x&&
				(escaleraRota.getPosX()+escaleraRota.getAncho()/2)>=this.x);
	}		 

	public int pisoQueEsta(Piso[] pisos) 
	{
		for (int j=0;j<pisos.length-1;j++)
		{
			if ((this.y+((this.alto)/2))<=(pisos[j].getInicioY()-pisos[j].getAlto()) &&
			   (this.y+(this.alto/2)>(pisos[j+1].getInicioY()-pisos[j+1].getAlto())))
			{
				return j;
			}
		}
		return (pisos.length-1);
	}
	
	public boolean lePega(Barril barriles) 
	{
		int xx = this.x - barriles.getX();
		int yy = this.y - barriles.getY();
		double dist = Math.sqrt(xx*xx + yy*yy); 
				
		return dist <= this.alto/2;
	}
	
	public boolean seQuema(Llamarada llama) 
	{
		int xx = this.x - llama.getX();
		int yy = this.y - llama.getY();
		boolean cercaX=(-llama.getLargoDeLlama()/2-this.ancho/2<xx)&&(xx<llama.getLargoDeLlama()/2+this.ancho/2);
		boolean cercaY=(yy>-30)&&(yy<30);
		 
		return (cercaX &&cercaY);
	}
	
	public boolean tocaADonkey(Donkey2 donkey2)
	{
				
		return ((donkey2.getY()+donkey2.getAlto()/2)>=(this.y+this.alto/2)&&((this.x-this.ancho/2)<=(donkey2.getX()+(donkey2.getAncho()/2))));
	}
	
	public boolean estaEnBoton(BotonLlamarada boton)
	{
		return (boton.getY()==(this.y+this.alto/2)&&(boton.getX()-boton.getAncho()/2)<=this.x &&
				(boton.getX()+boton.getAncho()/2)>=this.x);
	}
	
	public boolean estaEnSuelo(Piso[] piso)
	{
		for (int i=0;i<piso.length;i++)
		{
			int suelo= (piso[i].getInicioY()-piso[i].getAlto());
			
			if (this.y+this.alto/2==suelo)
			{
				if (this.x>=(piso[i].getInicioX()-this.ancho/2) && this.x<=(piso[i].getInicioX()+piso[i].getAncho()+this.ancho/2))
				{
					return true;
				}
			}  
		}
		return false;
	}
	
	public int getY() 
	{
		return y;
	}
	public int getX() 
	{
		return x;
	}
	
	public void setSubeSaltando(boolean subeSaltando) 
	{
		this.subeSaltando = subeSaltando;
	}
	
	public void setSaltando(boolean saltando) 
	{
		this.saltando = saltando;
	}

	public boolean estaSaltando() 
	{
		return saltando;
	}

	public int getAlto() 
	{
		return alto;
	}

	public boolean getSubeSaltando() 
	{
		return subeSaltando;
	}

	public boolean estaEscalando() 
	{
		return escalando;
	}

	public boolean estaAvanzando() 
	{
		return avanzando;
	}

	public boolean estaCayendo() 
	{
		return cayendo;
	}	
}
