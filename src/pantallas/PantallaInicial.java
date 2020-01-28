package pantallas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import principal.PanelJuego;

/**
 * 
 * @author Pablo Garrido Marin.
 *
 */
public class PantallaInicial implements Pantalla {

	// Panel del juego
	PanelJuego panelJuego;

	// Imagen inicial
	BufferedImage imagen;

	// Fuente y color inicial para las letras
	Font fuenteInicial = new Font("Arial", Font.BOLD, 30);
	Color colorLetraInicio = Color.RED;

	/**
	 * Constructor de la pantalla
	 * 
	 * @param panelJuego Panel del juego
	 */
	public PantallaInicial(PanelJuego panelJuego) {
		inicializarPantalla(panelJuego);
	}

	/**
	 * Metodo donde inicializo la pantalla y cargo la imagen inicial
	 */
	@Override
	public void inicializarPantalla(PanelJuego panelJuego) {
		this.panelJuego = panelJuego;
		try {
			imagen = ImageIO.read(new File(getClass().getResource("/archivos/fondoInicial.png").toURI()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo para pintar la pantalla
	 */
	@Override
	public void pintarPantalla(Graphics g) {
		g.drawImage(reescalarImagen(), 0, 0, null);
		g.setColor(colorLetraInicio);
		g.setFont(fuenteInicial);
		g.drawString("¡¡Haz click para jugar!!", panelJuego.getWidth() / 3 - 30, panelJuego.getHeight() / 2 + 120);
	}

	/**
	 * Metodo donde cambio el color de las letras
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
	 * Metodo para cuando pulso el raton pasa a la pantalla de juego
	 */
	@Override
	public void pulsarRaton(MouseEvent e) {
		panelJuego.setPantallaActual(new PantallaJuego(panelJuego));
	}

	/**
	 * Metodo para reescalar la imagen inicial
	 * 
	 * @return Imagen inicial del fondo reescalada
	 */
	private Image reescalarImagen() {
		return imagen.getScaledInstance(panelJuego.getWidth(), panelJuego.getHeight(), Image.SCALE_SMOOTH);
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
