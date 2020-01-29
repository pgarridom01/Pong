package principal;

import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import pantallas.PantallaJuego;

/**
 * 
 * @author Pablo Garrido Marin.
 *
 */

public class Sprite {

	// Imagen del Sprite
	private BufferedImage buffer;

	// Variables de magnitud
	private int ancho;
	private int alto;

	// Variables de posicion
	private int posX;
	private int posY;

	// Variables de velocidad
	private int velocidadX;
	private int velocidadY;

	// Variables para la puntuacion
	private int puntuacionJugador1 = 0;
	private int puntuacionCPU = 0;

	// Sonidos del Juego
	Sonido sonido = new Sonido();
	AudioClip golpeJugador1 = sonido.getAudio("/archivos/golpe1.wav");
	AudioClip golpeJugador2 = sonido.getAudio("/archivos/golpe2.wav");

	/**
	 * Constructor para las raquetas, ya que estas no tienen velocidad
	 * 
	 * @param ancho Ancho de la raqueta
	 * @param alto  Alto de la raqueta
	 * @param posX  Posicion en el Eje X de la raqueta
	 * @param posY  Posicion en el Eje Y de la raqueta
	 * @param ruta  URL de la imagen de la raqueta
	 */
	public Sprite(int ancho, int alto, int posX, int posY, URL ruta) {
		this.ancho = ancho;
		this.alto = alto;
		this.posX = posX;
		this.posY = posY;
		pintarBuffer(ruta);
	}

	/**
	 * Constructor para la pelota
	 * 
	 * @param ancho      Ancho de la pelota
	 * @param alto       Alto de la pelota
	 * @param posX       Posicion en el Eje X de la pelota
	 * @param posY       Posicion en el Eje Y de la pelota
	 * @param velocidadX Velocidad en el Eje X de la pelota
	 * @param velocidadY Velocidad en el Eje Y de la pelota
	 * @param ruta       URL de la imagen de la pelota
	 */
	public Sprite(int ancho, int alto, int posX, int posY, int velocidadX, int velocidadY, URL ruta) {
		this.ancho = ancho;
		this.alto = alto;
		this.posX = posX;
		this.posY = posY;
		this.velocidadX = velocidadX;
		this.velocidadY = velocidadY;
		pintarBuffer(ruta);
	}

	/**
	 * Metodo para pintar el Sprite
	 * 
	 * @param ruta URL de la imagen
	 */
	public void pintarBuffer(URL ruta) {
		buffer = new BufferedImage(this.ancho, this.alto, BufferedImage.TYPE_INT_ARGB);
		Graphics g = buffer.getGraphics();
		try {

			BufferedImage img = ImageIO.read(ruta);
			g.drawImage(img.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH), 0, 0, null);

		} catch (IOException e) {
			e.printStackTrace();
		}
		g.dispose();
	}

	/**
	 * Metodo para mover el Sprite
	 * 
	 * @param anchoPantalla Ancho de la pantalla del juego
	 * @param altoPantalla  Alto de la pantalla del juego
	 * @param colisionR1    Colision con la raqueta del jugador
	 * @param colisionR2    Colision con la raqueta de la CPU
	 */
	public void moverSprite(int anchoPantalla, int altoPantalla, boolean colisionR1, boolean colisionR2) {
		colisionConRaquetas(colisionR1, colisionR2);
		conseguirPunto(anchoPantalla, altoPantalla);
		colisionBorde(altoPantalla);
		posX = posX + velocidadX;
		posY = posY + velocidadY;

	}

	/**
	 * Compruebo las colisiones con los bordes de la pantalla
	 * 
	 * @param altoPantalla Alto de la pantalla del juego
	 */
	private void colisionBorde(int altoPantalla) {
		if (posY >= altoPantalla - alto) {
			velocidadY = -1 * Math.abs(velocidadY);
		}
		if (posY <= 0) {
			velocidadY = Math.abs(velocidadY);

		}

	}

	/**
	 * Metodo para comprobar si no hay colision y la bola supera a la raqueta
	 * 
	 * @param anchoPantalla ancho de la pantalla del juego
	 * @param altoPantalla  alto de la pantalla del juego
	 */
	private void conseguirPunto(int anchoPantalla, int altoPantalla) {
		// Compruebo si hay gol del Jugador si sale por la derecha de la pantalla
		if (posX >= anchoPantalla) {
			puntuacionJugador1++;
			posX = anchoPantalla / 2;
			posY = altoPantalla / 2;
			velocidadX = -1 * Math.abs((int) (Math.random() * 5) + 3);
		}
		// Compruebo si hay gol de la CPU si sale por la izquierda de la pantalla
		if (posX < -ancho) {
			puntuacionCPU++;
			posX = anchoPantalla / 2;
			posY = altoPantalla / 2;
			velocidadX = Math.abs((int) (Math.random() * 5) + 3);
		}
	}

	/**
	 * Metodo para saber la colision de la bola con las raquetas
	 * 
	 * @param colisionR1 Colision con la raqueta del Jugador
	 * @param colisionR2 Colision con la raqueta de la CPU
	 */
	private void colisionConRaquetas(boolean colisionR1, boolean colisionR2) {
		if (colisionR1) {
			velocidadX = -(int) (Math.random() * 5) + 3;
			golpeJugador1.play();

		}
		if (colisionR2) {
			velocidadX = -1 * Math.abs((int) (Math.random() * 5) + 3);
			golpeJugador2.play();
		}
	}

	/**
	 * Metodo para mover la raqueta del jugador
	 * 
	 * @param pantalla Pantalla de juego
	 */
	public void moverRaquetaJugador(PantallaJuego pantalla) {
		if (pantalla.arriba && posY > 0) {
			posY -= 5;
		}
		if (pantalla.abajo && posY < pantalla.panelJuego.getHeight() - this.alto) {
			posY += 5;
		}
	}

	/**
	 * Metodo para mover la raqueta de la CPU hacia arriba
	 */
	public void moverRaquetaCpuArriba() {
		if (posY > 0) {
			posY -= 5;
		}
	}

	/**
	 * Metodo para mover la raqueta de la CPU hacia abajo
	 * 
	 * @param panelJuego Panel de Juego
	 */
	public void moverRaquetaCpuAbajo(PanelJuego panelJuego) {
		if (posY < panelJuego.getHeight() - this.alto) {
			posY += 5;
		}
	}

	/**
	 * Metodo para pintar el Sprite en pantalla
	 * 
	 * @param g Graficos
	 */
	public void pintarEnMundo(Graphics g) {
		g.drawImage(buffer, posX, posY, null);
	}

	/**
	 * Comprueba si hay colisión entre este Sprite y otro que viene por parámetros.
	 * La colisión es cuadrada.
	 * 
	 * @param otro Sprite que se le pasa para comprobar la colision
	 * @return True si hay colision en los dos ejes y false si no hay
	 */
	public boolean colisiona(Sprite otro) {
		boolean colisionX = posX < otro.posX ? (posX + ancho >= otro.posX) : (otro.posX + otro.ancho >= posX);
		boolean colisionY = posY < otro.posY ? (posY + alto >= otro.posY) : (otro.posY + otro.alto >= posY);
		return colisionX && colisionY;
	}

	// Getters y Setters

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public int getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}

	public void setVelocidadX(int i) {
		this.velocidadX = i;

	}

	public void setVelocidadY(int i) {
		this.velocidadY = i;

	}

	public int getPuntuacionJugador1() {
		return puntuacionJugador1;
	}

	public void setPuntuacion1(int puntuacionJugador1) {
		this.puntuacionJugador1 = puntuacionJugador1;
	}

	public int getPuntuacionCPU() {
		return puntuacionCPU;
	}

	public void setPuntuacionCPU(int puntuacionCPU) {
		this.puntuacionCPU = puntuacionCPU;
	}

}
