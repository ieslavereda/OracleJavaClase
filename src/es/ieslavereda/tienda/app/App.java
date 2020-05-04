/**
 * 
 */
package es.ieslavereda.tienda.app;

import java.awt.EventQueue;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import es.ieslavereda.tienda.controlador.Controlador;
import es.ieslavereda.tienda.modelo.Modelo;
import es.ieslavereda.tienda.vista.JFramePrincipal;

/**
 * Creado el 27 mar. 2019
 * @author <a href="mailto:joaalsai@ieslavereda.es">Joaquin Vicente Alonso Saiz</a>
 *
 */
public class App {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				// Creamos los objetos Modelo y Vista
				Modelo modelo = new Modelo();
				JFramePrincipal vista = new JFramePrincipal();
				try {
					
					for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
						if ("Nimbus".equals(info.getName())) {
							UIManager.setLookAndFeel(info.getClassName());
							SwingUtilities.updateComponentTreeUI(vista);
							break;
						}
					}
					
					Controlador c = new Controlador(vista,modelo);
					c.start();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
