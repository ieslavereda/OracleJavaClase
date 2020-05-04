/**
 * 
 */
package es.ieslavereda.tienda.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import javax.swing.JDesktopPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

/**
 * Creado el 27 mar. 2019
 * @author <a href="mailto:joaalsai@ieslavereda.es">Joaquin Vicente Alonso Saiz</a>
 *
 */
public class JFramePrincipal extends JFrame {

	private JPanel contentPane;
	public JButton btnListUsers;
	public JDesktopPane desktopPane;
	public JButton btnUsers;
	public JButton btnLogin;
	public JButton btnSalir;
	public JButton btnReport;

	/**
	 * Create the frame.
	 */
	public JFramePrincipal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(JFramePrincipal.class.getResource("/es/ieslavereda/tienda/images/logo_compacto.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1006, 652);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		btnLogin = new JButton("Login");
		btnLogin.setIcon(new ImageIcon(JFramePrincipal.class.getResource("/es/ieslavereda/tienda/images/login.png")));
		toolBar.add(btnLogin);
		
		btnUsers = new JButton("Users");
		btnUsers.setEnabled(false);
		btnUsers.setIcon(new ImageIcon(JFramePrincipal.class.getResource("/es/ieslavereda/tienda/images/alta_usuario.png")));
		toolBar.add(btnUsers);
		
		btnListUsers = new JButton("Clients");
		btnListUsers.setEnabled(false);
		btnListUsers.setIcon(new ImageIcon(JFramePrincipal.class.getResource("/es/ieslavereda/tienda/images/usuarios.png")));
		toolBar.add(btnListUsers);
		
		btnReport = new JButton("Report");
		btnReport.setEnabled(false);
		btnReport.setIcon(new ImageIcon(JFramePrincipal.class.getResource("/es/ieslavereda/tienda/images/Printer.png")));
		toolBar.add(btnReport);
		
		btnSalir = new JButton("Salir");
		btnSalir.setEnabled(false);
		btnSalir.setIcon(new ImageIcon(JFramePrincipal.class.getResource("/es/ieslavereda/tienda/images/salir.png")));
		toolBar.add(btnSalir);
		
		desktopPane = new JDesktopPane();
		contentPane.add(desktopPane, BorderLayout.CENTER);
	}
}
