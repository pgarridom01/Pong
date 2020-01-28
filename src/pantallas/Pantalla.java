package pantallas;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import principal.PanelJuego;

/**
 * 
 * @author Pablo Garrido Marin
 *
 */
public interface Pantalla {

	public void inicializarPantalla(PanelJuego panelJuego);

	public void pintarPantalla(Graphics g);

	public void ejecutarFrame();

	public void pulsarRaton(MouseEvent e);

	public void pulsarTecla(KeyEvent e);

	public void soltarTecla(KeyEvent e);

}
