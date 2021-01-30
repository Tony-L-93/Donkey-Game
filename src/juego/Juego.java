package juego;

import java.awt.Color;
import java.awt.Image;
import java.util.Random;
import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	
	// Variables y métodos propios de cada grupo
	Barril[] barriles;
	Jumpman jumpman;
	int pisoSalto;
	Piso[] pisos;
	Vidas vidas;
	Carteles carteles;
	Escalera[] escaleras,escalerasOptativas;
	EscaleraRota[] escalerasRotas;
	int altoVigas=10;
	int cantPisos=5;
	Donkey2 donkey2;
	Random rango = new Random();
	FugaDeGas[] fugas;
	BotonLlamarada[] botonEncendido;
	Llamarada[] llamas;
	int cantidadBarriles=5;
	int frecuenciaBarriles=250;
	int vB=3; //Velocidad de los Barriles
	int frecuenciaLlamas=750;
	int tiempoFuga=0;
	int tiempoEsperaLanzarBarril=100;
	int tiempoBarril=0;
	Color colorFuga;
	Image fondo;
	
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Donkey Prueba", 800, 600);
		fondo = Herramientas.cargarImagen("laboratorio.jpg");
		
		// Inicializar lo que haga falta para el juego
		jumpman = new Jumpman(entorno.ancho()-700, (entorno.alto()-40));
		pisos=new Piso[cantPisos+1];
		escaleras=new Escalera[cantPisos];
		escalerasOptativas=new Escalera[cantPisos];
		donkey2=new Donkey2(40,110);
		barriles= new Barril[cantidadBarriles];
		fugas=new FugaDeGas[cantPisos];
		botonEncendido=new BotonLlamarada[cantPisos];
		llamas=new Llamarada[cantPisos];
		vidas=new Vidas();
		carteles=new Carteles();
		
		for(int i=0;i<=cantPisos;i++) //Recorre los pisos
		{	
			if(i==0) //dibuja el piso de abajo 
			{
				pisos[i]=new Piso(altoVigas,entorno.ancho(),0,entorno.alto());
			}
			else
			{
				if (i%2!=0) //piso 1,3,5 son impares y comienzan desde el eje de ordenada con x=0
				{
					pisos[i]=new Piso(altoVigas,(entorno.ancho()-90),0,(entorno.alto()-(i*90)));
				}
				else //piso 2,4 son pares y comienzan dese x=90
				{
					pisos[i]=new Piso(altoVigas,(entorno.ancho()-90),90,(entorno.alto()-(i*90)));
				}
			}
			
		}
		
		escalerasOptativas=new Escalera[cantPisos];
		escalerasRotas=new EscaleraRota[cantPisos];
		donkey2=new Donkey2(40,110);
		barriles= new Barril[cantidadBarriles];
		
		for (int i=0;i<cantPisos;i++) //Recorre escaleras
		{
			int optativa= rango.nextInt(2);
			int rota= rango.nextInt(2);
			int ubicacionOptativa=rango.nextInt(200);
			int ubicacionRota=rango.nextInt(150);
			
			if (pisos[i+1].getInicioX()==0) //Dibujas las escaleras en donde los pisos comienzan en la posicion x=0
			{
				escaleras[i]=new Escalera(pisos[i+1].getAncho()-30,30,pisos[i].getInicioY()-pisos[i].getAlto(),
						pisos[i+1].getInicioY()-pisos[i+1].getAlto());
				if (rota==1)
				{
					escalerasRotas[i]=new EscaleraRota(pisos[i+1].getAncho()/2+ubicacionRota,30,pisos[i].getInicioY()-pisos[i].getAlto(),
											pisos[i].getInicioY()-pisos[i].getAlto()-45);
				}
				else
				{
					escalerasRotas[i]=null;
				}
				if (optativa==1)
				{
					escalerasOptativas[i]=new Escalera(pisos[i+1].getAncho()/2-ubicacionOptativa,30,pisos[i].getInicioY()-pisos[i].getAlto(),
											pisos[i+1].getInicioY()-pisos[i+1].getAlto());
				}
				else
				{
					escalerasOptativas[i]=null;
				}
				fugas[i]=new FugaDeGas(entorno.ancho()-5,pisos[i].getInicioY()-pisos[i].getAlto()-45,(Math.PI),frecuenciaLlamas);
				botonEncendido[i]=new BotonLlamarada(entorno.ancho()-30,pisos[i].getInicioY()-pisos[i].getAlto());
			}
			
			else //Dibuja los pisos que comienzan en la posicion x=90
			{
				escaleras[i]=new Escalera(pisos[i+1].getInicioX()+30,30,pisos[i].getInicioY()-pisos[i].getAlto(),
						pisos[i+1].getInicioY()-pisos[i+1].getAlto());
				if (rota==1)
				{
					escalerasRotas[i]=new EscaleraRota((pisos[i+1].getAncho()-pisos[i+1].getInicioX())/2-ubicacionRota,30,pisos[i].getInicioY()-pisos[i].getAlto(),
										pisos[i].getInicioY()-pisos[i].getAlto()-45);
				}
				else
				{
					escalerasRotas[i]=null;
				}
				if (optativa==1)
				{
					escalerasOptativas[i]=new Escalera((pisos[i+1].getAncho()-pisos[i+1].getInicioX())/2+ubicacionOptativa,30,pisos[i].getInicioY()-pisos[i].getAlto(),
										pisos[i+1].getInicioY()-pisos[i+1].getAlto());
				}
				else
				{
					escalerasOptativas[i]=null;
				}
				
				fugas[i]=new FugaDeGas(5,pisos[i].getInicioY()-pisos[i].getAlto()-45, 0,frecuenciaLlamas);
				
				botonEncendido[i]=new BotonLlamarada(30,pisos[i].getInicioY()-pisos[i].getAlto());	
			}
		}
			
		// Inicia el juego!
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick()
	{
		// Procesamiento de un instante de tiempo
		
		if(verificarVidas()>0 && verificarVidas()<7) //En caso que la vida de Jumpan sea en ese rango se dibuja todas las clases en pantalla excepto la clase Carteles
		{
			dibujarcosas();
			correTiempo();
			moverCosas();
			verificarAccionesDelUsuario();
			verificarBordesDePantalla();
			verificarChoques();
		}
		else 
		{
			if(verificarVidas()==7) //Dibuja la clase Carteles en este caso con el cartel ganador si se cumple el condicional sino dibuja el cartel perdedor
			{
				ganarJuego();
			}
			else
			{
				terminarJuego(); 
			}
		}
	}
	
	private int verificarVidas() //Verifica la vida de Jumpman
	{
		return(vidas.GetVidas());
	}
	
	
	private void terminarJuego() //Dibuja una pantalla cuando Jumpman pierde todas las vidas
	{

		carteles.dibujarsePerdedor(entorno);
	}
	
	private void ganarJuego() //Dibuja una pantalla cuando la vida Jumpman es igual a 7
	{
		carteles.dibujarseGanador(entorno);
	}

	private void correTiempo() 
	{
		++tiempoFuga;
		++tiempoBarril;
		
		for (int i=0;i<fugas.length;++i)
		{
			fugas[i].colorFuga(tiempoFuga);
		}
				
	}

	private void moverCosas() 
	{	
		if(jumpman!=null)
			{
			if (jumpman.estaSaltando())
				{
					jumpman.saltar(pisoSalto);
				}
			}
		
		for (int i=0;i<barriles.length;i++)
		{
			if(barriles[i]!=null)
				{
				barriles[i].rodar(vB, 0, entorno.ancho());
				}
		}
			
		for (int j=0;j<barriles.length;j++)
		{
			if(barriles[j]!=null)
					barriles[j].caer(1,pisos);
		}
		
		for (int i=0;i<fugas.length;i++)
		{	
				fugas[i].apagarFuga(botonEncendido[i]);
		}
		
		for (int i=0;i<fugas.length;++i)
		{
			if ((fugas[i].isAbierto() && (tiempoFuga/(frecuenciaLlamas/5))%5==4))
			{
				llamas[i]=fugas[i].lanzarLlamas();
			}
			if ((fugas[i].isAbierto() && (tiempoFuga/(frecuenciaLlamas/5))%5==0))
			{
				llamas[i]=null;
			}
		}
	}
	
	private void dibujarcosas()
	{
		entorno.dibujarImagen(fondo, entorno.ancho()/2, entorno.alto()/2, 0, 0.55);
		
		int pos = 0;
		for(int i=1 ; i <= vidas.GetVidas(); i++)  //Dibuja las vidas 
		{
			vidas.dibujarseVidas(entorno, pos+=25);//suma intervalos de 25 en la posicion x de vidas
		}
	
		for (int i=0;i<=cantPisos;i++)
		{
			pisos[i].dibujarse(entorno);
		}
		
		for (int i=0;i<cantPisos;i++)
		{
			escaleras[i].dibujarse(entorno);
			if (escalerasOptativas[i]!=null)
			{
				escalerasOptativas[i].dibujarse(entorno);
			}
			if (escalerasRotas[i]!=null)
			{
				escalerasRotas[i].dibujarse(entorno);
			}
			fugas[i].dibujarse(entorno);
			botonEncendido[i].dibujarse(entorno);
			if (llamas[i]!=null)
				llamas[i].dibujarse(entorno);
		}
		
		Random rango = new Random();
		int lanzamiento= rango.nextInt(frecuenciaBarriles);
		if (tiempoBarril==tiempoEsperaLanzarBarril)
		{
			donkey2.puedeLanzar();
		}
		if (lanzamiento==15 && jumpman!=null && donkey2!=null)
		{
			for (int i = 0; i < barriles.length; i++) 
			{
				if (barriles[i] == null && tiempoBarril>tiempoEsperaLanzarBarril)
				{
					barriles[i] = donkey2.lanzarBarril();
					tiempoBarril=0;
					break;
				}
			}
		}
		
		for (int i=0;i<barriles.length;i++)
		{
		
			if(barriles[i]!=null)
			{
				barriles[i].dibujarse(entorno);
			}
		}
		
		if(jumpman!=null)
		{
			jumpman.dibujarse(entorno);
		}
		
		if(donkey2!=null)
			{
			donkey2.dibujarse(entorno);
			}
	}
	
	private void verificarChoques()
	{
		for (int i = 0; i < barriles.length; i++) 
		{
			if(barriles[i]!=null && jumpman!=null && jumpman.lePega(barriles[i]))
			{
				vidas.SetVidas(1);
				inicializarPersonajes();
				
				for(int j=0;j<barriles.length;j++)
				{
					barriles[j]=null;
				}
			}
		}
	
		for (int i=0;i<llamas.length;i++)
		{
			if (jumpman!=null && llamas[i]!=null && jumpman.seQuema(llamas[i]))
			{
				vidas.SetVidas(1);
				inicializarPersonajes();
			}
		}
		
		if(jumpman!=null && donkey2!=null && jumpman.tocaADonkey(donkey2))//toca a Donkey y pasa de nivel
		{
			donkey2=null;
			vidas.SetVidas(-1);
			
			for(int j=0;j<barriles.length;j++)
			{
				barriles[j]=null;
			}
			
			cantidadBarriles+=2;
 			vB+=1;
 			tiempoFuga=0;
 			tiempoBarril=0;
 			
 			if (frecuenciaBarriles>=50)
 			{	
 				frecuenciaBarriles-=10;
 			}
 			
 			inicializarPersonajes();
		}
	}
	
	private void verificarBordesDePantalla() 
	{
		for (int i = 0; i < barriles.length; i++) 
		{
			if (barriles[i] != null && barriles[i].getY()+(barriles[i].getTamaño()/2)==pisos[0].getInicioY()-pisos[0].getAlto()	&& barriles[i].getX()-barriles[i].getTamaño()/2==0)
			{
				barriles[i] = null;
			}
		}
		
		if (jumpman!=null)
		{
			jumpman.caer(2, pisos);
		}
	}
	
	private void verificarAccionesDelUsuario() 
	{
		if(jumpman!=null)
		{
			int limIzq=pisos[jumpman.pisoQueEsta(pisos)].getInicioX();
			int limDer=pisos[jumpman.pisoQueEsta(pisos)].getInicioX()+pisos[jumpman.pisoQueEsta(pisos)].getAncho();
			
			
			if (entorno.estaPresionada(entorno.TECLA_DERECHA))
				jumpman.moverHorizontal(+3,limIzq,limDer);
			
			if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA))
				jumpman.moverHorizontal(-3,limIzq,limDer);
			
			if (puedeEscalar(escaleras)||puedeEscalar(escalerasOptativas))
			{	
				if (entorno.estaPresionada(entorno.TECLA_ABAJO))
					if (enEscaleraRota()!=null)
					{
						jumpman.subirEscalerasRotas(+2,enEscaleraRota().getInferiorY(),enEscaleraRota().getSuperiorY());
					}
					else
					{	
						jumpman.subirEscaleras(+2,enEscalera().getInferiorY(),enEscalera().getSuperiorY());
					}
				if (entorno.estaPresionada(entorno.TECLA_ARRIBA))
					if (enEscaleraRota()!=null)
					{
						jumpman.subirEscalerasRotas(-2,enEscaleraRota().getInferiorY(),enEscaleraRota().getSuperiorY());
					}
					else
					{
						jumpman.subirEscaleras(-2,enEscalera().getInferiorY(),enEscalera().getSuperiorY());
					}
					
			} 
			
			if (puedePulsar(botonEncendido))
			{
				if (entorno.sePresiono(entorno.TECLA_ABAJO))
				{
					enBoton().pulsarBoton();
				}
			}
			
			if(entorno.sePresiono(entorno.TECLA_ESPACIO))
			{
				
				if (!jumpman.estaSaltando() && !jumpman.estaEscalando()&& !jumpman.estaCayendo())
				{
					pisoSalto=(jumpman.getY()-jumpman.getAlto()/2);
					jumpman.setSaltando(true);
					jumpman.setSubeSaltando(true);
				}
			}
		}
		
	}
	
	private boolean puedePulsar (BotonLlamarada[] botonEncendido)
	{
		for (int i=0;i<botonEncendido.length;i++)
		{
			if (jumpman.estaEnBoton(botonEncendido[i]))
			{
				return true;
			}
		}
		return false;
	}
	
	public BotonLlamarada enBoton()
	{
		for (int i=0;i<botonEncendido.length;i++)
		{
			if (jumpman!=null  &&jumpman.estaEnBoton(botonEncendido[i]))
			{
				return botonEncendido[i];
			}
		}
		return null;
	}
	
	private boolean puedeEscalar(Escalera[] escaleras)
	{
		for (int i=0; i<escaleras.length;i++)
		{
			if ((escalerasOptativas[i]!=null && jumpman.estaEnEscalera(escalerasOptativas[i])) || (escalerasRotas[i]!=null && jumpman.estaEnEscalera(escalerasRotas[i]) )
					||jumpman.estaEnEscalera(escaleras[i]) && jumpman!=null)
			{
				return true;
			}
			
		}
		return false;
	}
	
	public Escalera enEscalera()
	{
		for (int i=0;i<(escaleras.length);i++)
		{
			if (jumpman.estaEnEscalera(escaleras[i])||((escalerasOptativas[i]!=null)&& jumpman.estaEnEscalera(escalerasOptativas[i]) ||((escalerasRotas[i]!=null)&& jumpman.estaEnEscalera(escalerasRotas[i])))&& jumpman!=null)
			{
				return escaleras[i];
			}
		}
		return null;
	}	
	
	public EscaleraRota enEscaleraRota()
	{
		for (int i=0;i<(escaleras.length);i++)
		{
			if ((escalerasRotas[i]!=null)&& jumpman.estaEnEscalera(escalerasRotas[i])&& jumpman!=null)
			{
				return escalerasRotas[i];
			}
		}
		return null;
	}
	
	public void inicializarPersonajes()
	{	
		jumpman = new Jumpman(entorno.ancho()-700, entorno.alto()-40);
		
		escalerasOptativas=new Escalera[cantPisos];
		escalerasRotas=new EscaleraRota[cantPisos];
		donkey2=new Donkey2(40,110);
		barriles= new Barril[cantidadBarriles];
		
		for (int i=0;i<cantPisos;i++) //Recorre escaleras
		{
			int optativa= rango.nextInt(2);
			int rota= rango.nextInt(2);
			int ubicacionOptativa=rango.nextInt(200);
			int ubicacionRota=rango.nextInt(150);
			if (pisos[i+1].getInicioX()==0) //Dibujas las escaleras en donde los pisos comienzan en la posicion x=0
			{
				if (rota==1)
				{
					escalerasRotas[i]=new EscaleraRota(pisos[i+1].getAncho()/2+ubicacionRota,30,pisos[i].getInicioY()-pisos[i].getAlto(),
											pisos[i].getInicioY()-pisos[i].getAlto()-45);
				}
				
				else
				{
					escalerasRotas[i]=null;
				}
				
				if (optativa==1)
				{
					escalerasOptativas[i]=new Escalera(pisos[i+1].getAncho()/2-ubicacionOptativa,30,pisos[i].getInicioY()-pisos[i].getAlto(),
											pisos[i+1].getInicioY()-pisos[i+1].getAlto());
				}
				
				else
				{
					escalerasOptativas[i]=null;
				}
				fugas[i]=new FugaDeGas(entorno.ancho()-5,pisos[i].getInicioY()-pisos[i].getAlto()-45,(Math.PI),frecuenciaLlamas);
				botonEncendido[i]=new BotonLlamarada(entorno.ancho()-30,pisos[i].getInicioY()-pisos[i].getAlto());
			}
			
			else //Dibuja los piso en  donde los pisos comienzan en la posicion x=90
			{
				if (rota==1)
				{
					escalerasRotas[i]=new EscaleraRota((pisos[i+1].getAncho()-pisos[i+1].getInicioX())/2-ubicacionRota,30,pisos[i].getInicioY()-pisos[i].getAlto(),
										pisos[i].getInicioY()-pisos[i].getAlto()-45);
				}
				
				else
				{
					escalerasRotas[i]=null;
				}
				
				if (optativa==1)
				{
					escalerasOptativas[i]=new Escalera((pisos[i+1].getAncho()-pisos[i+1].getInicioX())/2+ubicacionOptativa,30,pisos[i].getInicioY()-pisos[i].getAlto(),
										pisos[i+1].getInicioY()-pisos[i+1].getAlto());
				}
				
				else
				{
					escalerasOptativas[i]=null;
				}
				
				fugas[i]=new FugaDeGas(5,pisos[i].getInicioY()-pisos[i].getAlto()-45, 0,frecuenciaLlamas);
				botonEncendido[i]=new BotonLlamarada(30,pisos[i].getInicioY()-pisos[i].getAlto());
			}
		}
	}
      
	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
