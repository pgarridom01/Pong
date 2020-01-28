package pantallas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
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

	PanelJuego panelJuego;
	BufferedImage imagen;

	Font fuenteInicial = new Font("Arial", Font.BOLD, 30);
	Color colorLetraInicio = Color.RED;

	public PantallaInicial(PanelJuego panelJuego) {
		inicializarPantalla(panelJuego);
	}

	/**
	 * Inicializo la pantalla con su fondo correspondiente.
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
	 * Pinto en la pantalla la imagen y la información que deseo mostrar.
	 */
	@Override
	public void pintarPantalla(Graphics g) {
		g.drawImage(reescalarImagen(), 0, 0, null);
		g.setColor(colorLetraInicio);
		g.setFont(fuenteInicial);
		g.drawString("¡¡Haz click para jugar!!", panelJuego.getWidth() / 3 - 30, panelJuego.getHeight() / 2 + 120);
	}

	/**
	 * Método que cambia de color las letras gracias al reescalado.
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

	@Override
	public void pulsarRaton(MouseEvent e) {
		panelJuego.setPantallaActual(new PantallaJuego(panelJuego));
	}

	private Image reescalarImagen() {
		return imagen.getScaledInstance(panelJuego.getWidth(), panelJuego.getHeight(), Image.SCALE_SMOOTH);
	}

}
