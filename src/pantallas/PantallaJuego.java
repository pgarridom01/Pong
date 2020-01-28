package pantallas;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import principal.PanelJuego;
import principal.Sprite;

/**
 * 
 * @author Pablo Garrido Marin.
 * 
 */
public class PantallaJuego implements Pantalla {

	// Variable de magnitud de la pelota y las raquetas
	private static final int ANCHO_PELOTA = 25;
	private static final int ALTO_PELOTA = 25;
	private static final int ANCHO_RAQUETA = 10;
	private static final int ALTO_RAQUETA = 50;

	// Panel del juego
	PanelJuego panelJuego;

	// Imagen inicial
	BufferedImage imagen;

	// Variables para saber si voy arriba o abajo con la raqueta
	boolean arriba, abajo;

	// Puntuacion para finalizar el juego
	final static int FIN_JUEGO = 5;

	// Sprites del juego
	Sprite pelota, raquetaJugador, raquetaCPU;

	// Variables para el tiempo
	double tiempoInicial;
	double tiempoDeJuego = 0;
	private DecimalFormat formatoDecimal = new DecimalFormat("#.##");

	// Fuentes del juego
	Font fuenteTiempo = new Font("Arial", Font.BOLD, 20);;
	Font fuenteMarcador = new Font("Arial", Font.BOLD, 50);;

	public PantallaJuego(PanelJuego panelJuego) {
		inicializarPantalla(panelJuego);
	}

	/**
	 * Metodo para inicializar la pantalla y sus componentes
	 */
	@Override
	public void inicializarPantalla(PanelJuego panelJuego) {
		this.panelJuego = panelJuego;

		// Inicializo la pelota
		pelota = new Sprite(ANCHO_PELOTA, ALTO_PELOTA, 0, 0, 5, 5, getClass().getResource("/archivos/pelota.png"));

		// Inicializo las raquetas
		raquetaJugador = new Sprite(ANCHO_RAQUETA, ALTO_RAQUETA, ANCHO_RAQUETA, 200,
				getClass().getResource("/archivos/barra_izquierda.png"));
		raquetaCPU = new Sprite(ANCHO_RAQUETA, ALTO_RAQUETA, panelJuego.getWidth() - ANCHO_RAQUETA - 10, 200,
				getClass().getResource("/archivos/barra_derecha.png"));

		// Obtengo el tiempo cuando empiezo el juego
		tiempoInicial = System.nanoTime();
	}

	/**
	 * Metodo para pintar la pantalla
	 */
	@Override
	public void pintarPantalla(Graphics g) {
		// Pinto un rectagulo de color negro que sera el fondos
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, panelJuego.getWidth(), panelJuego.getHeight());

		// Pinto una linea que sera la divisoria del campo
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.WHITE);
		g2.setStroke(new BasicStroke(5));
		g2.drawLine(panelJuego.getWidth() / 2, 0, panelJuego.getWidth() / 2, panelJuego.getHeight());

		// Pinto el marcador y el tiempo
		pintarMarcador(g);
		pintarTiempo(g);

		// Pinto los sprites del juego
		pelota.pintarEnMundo(g);
		raquetaJugador.pintarEnMundo(g);
		raquetaCPU.pintarEnMundo(g);
	}

	public void pintarMarcador(Graphics g) {
		g.setFont(fuenteMarcador);

		g.drawString("" + pelota.getPuntuacionJugador1(), panelJuego.getWidth() / 2 - 50, 50);

		g.drawString("" + pelota.getPuntuacionCPU(), panelJuego.getWidth() / 2 + 20, 50);

		if (pelota.getPuntuacionJugador1() == FIN_JUEGO) {
			panelJuego.setPantallaActual(new PantallaGamePlayerWin(panelJuego, tiempoDeJuego, pelota));
		}

		if (pelota.getPuntuacionCPU() == FIN_JUEGO) {
			panelJuego.setPantallaActual(new PantallaFinalCPUWin(panelJuego, tiempoDeJuego, pelota));
		}

	}

	private void pintarTiempo(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(fuenteTiempo);
		actualizarTiempo();
		g.drawString(formatoDecimal.format(tiempoDeJuego / 1000000000d), 25, 25);
	}

	private void actualizarTiempo() {
		tiempoDeJuego = System.nanoTime() - tiempoInicial;
	}

	@Override
	public void ejecutarFrame() {

		try {
			Thread.sleep(10);
		} catch (Exception e) {
			// TODO: handle exception
		}
		moverSprites();
	}

	private void moverSprites() {
		moverRaquetaJugador();
		moverRaquetaCpu();
		pelota.moverSprite(panelJuego.getWidth(), panelJuego.getHeight(), colision(raquetaJugador),
				colision(raquetaCPU));
	}

	public void moverRaquetaJugador() {
		raquetaJugador.moverRaquetaJugador(panelJuego);
	}

	public void moverRaquetaCpu() {
		if (pelota.getPosY() < raquetaCPU.getPosY()) {
			raquetaCPU.moverRaquetaCpuArriba();
		}
		if (pelota.getPosY() > raquetaCPU.getPosY()) {
			raquetaCPU.moverRaquetaCpuAbajo(panelJuego);
		}

	}

	public boolean colision(Sprite raqueta) {
		return pelota.colisiona(raqueta);
	}

	@Override
	public void pulsarRaton(MouseEvent e) {

	}

}
