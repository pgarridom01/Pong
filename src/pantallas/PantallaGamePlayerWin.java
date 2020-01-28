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
 */
public class PantallaGamePlayerWin implements Pantalla {

	PanelJuego panelJuego;

	double tiempoJuego;
	private DecimalFormat formatoDecimal;

	Font fuenteInicial = new Font("Arial", Font.BOLD, 30);
	Color colorLetraInicio = Color.RED;

	Sprite pelota;

	public PantallaGamePlayerWin(PanelJuego panelJuego, double tiempoJuego, Sprite pelota) {
		this.tiempoJuego = tiempoJuego;
		this.pelota = pelota;
		inicializarPantalla(panelJuego);
	}

	/**
	 * Inicializo la pantalla con su fondo correspondiente y reescalo.
	 */
	@Override
	public void inicializarPantalla(PanelJuego panelJuego) {
		this.panelJuego = panelJuego;

		fuenteInicial = new Font("Times New Roman", Font.BOLD, 30);
		formatoDecimal = new DecimalFormat("#.##");
	}

	/**
	 * Pinto en la pantalla la imagen y la información que deseo mostrar.
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

	/**
	 * Método que carga un panel u otro dependiendo de la opción que eliga el
	 * usuario.
	 */
	@Override
	public void pulsarRaton(MouseEvent e) {
		switch (e.getButton()) {
		case 1:
			panelJuego.setPantallaActual(new PantallaInicial(panelJuego));
			break;
		case 3:
			panelJuego.setPantallaActual(new PantallaJuego(panelJuego));
			break;
		default:
			break;
		}

	}
}
