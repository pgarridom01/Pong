package pantallas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;

import principal.PanelJuego;
import principal.Sprite;

/**
 * 
 * @author Pablo Garrido Marin.
 *
 */
public class PantallaFinalCPUWin implements Pantalla {

	// Panel del juego
	PanelJuego panelJuego;

	// Imagen inicial
	BufferedImage imagen;

	// Variables para el tiempo
	double tiempoJuego;
	private DecimalFormat formatoDecimal = new DecimalFormat("#.##");;

	// Fuente y color inicial
	Font fuenteInicial = new Font("Times New Roman", Font.BOLD, 30);
	Color colorLetraInicio = Color.WHITE;

	// Spring pelota para obtener las puntuaciones
	Sprite pelota;

	/**
	 * Constructor de la pantalla cuando gana la CPU
	 * 
	 * @param panelJuego  Panel del Juego
	 * @param tiempoJuego Tiempo tardado en jugar
	 * @param pelota      Sprite pelota
	 */
	public PantallaFinalCPUWin(PanelJuego panelJuego, double tiempoJuego, Sprite pelota) {
		this.tiempoJuego = tiempoJuego;
		this.pelota = pelota;
		inicializarPantalla(panelJuego);
	}

	/**
	 * Inicializo la pantalla.
	 */
	@Override
	public void inicializarPantalla(PanelJuego panelJuego) {
		this.panelJuego = panelJuego;
		try {
			imagen = ImageIO.read(new File(getClass().getResource("/archivos/gameOver.jpg").toURI()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Pinto en la pantalla el fondo y la información.
	 */
	@Override
	public void pintarPantalla(Graphics g) {
		g.drawImage(reescalarImagen(), 0, 0, null);
		g.setColor(colorLetraInicio);
		g.setFont(fuenteInicial);
		g.drawString("Player WIN. MARCADOR : " + pelota.getPuntuacionJugador1() + "-" + pelota.getPuntuacionCPU(), panelJuego.getWidth() / 3-50, panelJuego.getHeight() / 2 + 150);
		g.drawString("Tiempo jugado: " + formatoDecimal.format(tiempoJuego / 1000000000d)+" seg.", panelJuego.getWidth() / 3,
				panelJuego.getHeight() / 2 + 200);
		
	}

	/**
	 * Metodo para reescalar la imagen inicial
	 * 
	 * @return Imagen inicial del fondo reescalada
	 */
	private Image reescalarImagen() {
		return imagen.getScaledInstance(panelJuego.getWidth(), panelJuego.getHeight(), Image.SCALE_SMOOTH);
	}

	/**
	 * Método para cambiar el color de las letras
	 */
	@Override
	public void ejecutarFrame() {
		
	}

	/**
	 * Método que carga una pantalla u otra
	 */
	@Override
	public void pulsarRaton(MouseEvent e) {
		switch (e.getButton()) {
		// Boton izquierdo carga la Pantalla Inicial
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

	@Override
	public void pulsarTecla(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void soltarTecla(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
