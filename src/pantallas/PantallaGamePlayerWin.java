package pantallas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;

import principal.PanelJuego;
import principal.Sprite;

/**
 * 
 * @author Pablo Garrido Marin.
 * 
 */
public class PantallaGamePlayerWin implements Pantalla {

	// Panel del juego
	PanelJuego panelJuego;

	// Variables para el tiempo
	double tiempoJuego;
	private DecimalFormat formatoDecimal = new DecimalFormat("#.##");;

	// Fuente y color iniciales
	Font fuenteInicial = new Font("Times New Roman", Font.BOLD, 30);
	Color colorLetraInicio = Color.RED;

	// Sprite pelota para obtener las puntuaciones
	Sprite pelota;

	/**
	 * Constructor de la pantalla final cuando gana el Jugador
	 * 
	 * @param panelJuego  Panel del Juego
	 * @param tiempoJuego Tiempo tardado en jugar
	 * @param pelota      Sprite pelota para obtener puntuaciones
	 */
	public PantallaGamePlayerWin(PanelJuego panelJuego, double tiempoJuego, Sprite pelota) {
		this.tiempoJuego = tiempoJuego;
		this.pelota = pelota;
		inicializarPantalla(panelJuego);
	}

	/**
	 * Inicializo la pantalla
	 */
	@Override
	public void inicializarPantalla(PanelJuego panelJuego) {
		this.panelJuego = panelJuego;
	}

	/**
	 * Pinto en la pantalla el fondo y la informacion mostrada
	 */
	@Override
	public void pintarPantalla(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, panelJuego.getWidth(), panelJuego.getHeight());
		g.setColor(colorLetraInicio);
		g.setFont(fuenteInicial);
		g.drawString("GAME OVER. Player WIN", panelJuego.getWidth() / 3, panelJuego.getHeight() / 3 + 50);
		g.drawString("Tiempo jugado: " + formatoDecimal.format(tiempoJuego / 1000000000d), panelJuego.getWidth() / 3,
				panelJuego.getHeight() / 3 + 100);
		g.setColor(Color.WHITE);
		g.drawString("MARCADOR : " + pelota.getPuntuacionJugador1() + ":" + pelota.getPuntuacionCPU(),
				panelJuego.getWidth() / 3, panelJuego.getHeight() / 3 + 150);
	}

	/**
	 * Método para cambiar el color de las letras
	 */
	@Override
	public void ejecutarFrame() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		colorLetraInicio = colorLetraInicio == Color.RED ? Color.WHITE : Color.RED;
	}

	/**
	 * Método que carga una pantalla u otra
	 */
	@Override
	public void pulsarRaton(MouseEvent e) {
		switch (e.getButton()) {
		// Boton izquierdo va a la Pantalla Inicial
		case 1:
			panelJuego.setPantallaActual(new PantallaInicial(panelJuego));
			break;
		// Boton derecho vuelve a jugar directamente
		case 3:
			panelJuego.setPantallaActual(new PantallaJuego(panelJuego));
			break;
		default:
			break;
		}

	}
}
