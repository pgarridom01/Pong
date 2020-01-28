package principal;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import pantallas.Pantalla;
import pantallas.PantallaInicial;

/**
 * 
 * @author Pablo Garrido Marin
 *
 */
public class PanelJuego extends JPanel implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8003209847218496500L;

	// Pantalla en la que esta el juego en cada momento
	Pantalla pantallaActual;

	// Variables para saber si muevo arriba o abajo
	protected static boolean arriba, abajo;

	public PanelJuego() {
		// Inicializo la pantalla con una Pantalla Inicial
		pantallaActual = new PantallaInicial(this);

		// Arranco el hilo
		new Thread(this).start();

		// Listener para cuando pulso el raton
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				pantallaActual.pulsarRaton(e);
			}
		});

		// Listener para cuando pulso las teclas
		addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					arriba = true;
					break;
				case KeyEvent.VK_DOWN:
					abajo = true;
					break;
				default:
					break;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					arriba = false;
					break;
				case KeyEvent.VK_DOWN:
					abajo = false;
					break;
				default:
					break;
				}
			}
		});
	}

	/**
	 * Metodo dpnde llamo a pintar pantalla de cada metodo
	 */
	@Override
	public void paintComponent(Graphics g) {
		pantallaActual.pintarPantalla(g);
	}

	@Override
	public void run() {
		this.setFocusable(true);
		this.requestFocusInWindow();
		while (true) {
			repaint();
			pantallaActual.ejecutarFrame();
		}
	}

	// Getters y Setters

	public Pantalla getPantallaActual() {
		return pantallaActual;
	}

	public void setPantallaActual(Pantalla pantallaActual) {
		this.pantallaActual = pantallaActual;
	}

}