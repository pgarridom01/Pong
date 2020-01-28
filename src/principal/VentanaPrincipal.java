package principal;

import java.awt.GridLayout;
import javax.swing.JFrame;

/**
 * 
 * @author Pablo Garrido Marin
 *
 */
public class VentanaPrincipal {

	JFrame ventana;
	PanelJuego panelJuego;

	/**
	 * Constructor de la VentanaPrincipal
	 */
	public VentanaPrincipal() {
		ventana = new JFrame();
		ventana.setSize(800, 500);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setLocationRelativeTo(null);
		ventana.setResizable(false);
	}

	/**
	 * Metodo que inicializa todos los componentes de la ventana
	 */
	public void inicializarComponentes() {
		// Definimos el layout:
		ventana.setLayout(new GridLayout(1, 1));
		// PANEL JUEGO
		panelJuego = new PanelJuego();
		ventana.add(panelJuego);
	}

	/**
	 * Metodo que realiza todas las llamadas necesarias para inicializar la ventana
	 * correctamente.
	 */
	public void inicializar() {
		ventana.setVisible(true);
		inicializarComponentes();
	}
}
