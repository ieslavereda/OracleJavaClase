package es.ieslavereda.tienda.vista;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.BorderLayout;

/**
 * Creado el 1 abr. 2019
 * @author <a href="mailto:joaalsai@ieslavereda.es">Joaquin Vicente Alonso Saiz</a>
 *
 */
public class JIFLogin extends JInternalFrame {
	public JTextField txtFieldLogin;
	public JPasswordField passwordField;
	public JButton btnLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JIFLogin frame = new JIFLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JIFLogin() {
		setTitle("Login");
		setClosable(true);
		setBounds(100, 100, 426, 339);
		
		JPanel panelSuperior = new JPanel();
		
		JPanel panelInferior = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panelSuperior, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
						.addComponent(panelInferior, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelSuperior, GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelInferior, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panelSuperior.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panelIzq = new JPanel();
		panelSuperior.add(panelIzq);
		panelIzq.setLayout(new BorderLayout(0, 0));
		
		JLabel lblFoto = new JLabel("");
		panelIzq.add(lblFoto, BorderLayout.CENTER);
		
		JPanel panelDer = new JPanel();
		panelSuperior.add(panelDer);
		panelDer.setLayout(new MigLayout("", "[grow]", "[][][][][][]"));
		
		JLabel lblLogin = new JLabel("Login");
		panelDer.add(lblLogin, "cell 0 1");
		
		txtFieldLogin = new JTextField();
		panelDer.add(txtFieldLogin, "cell 0 2,growx");
		txtFieldLogin.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		panelDer.add(lblPassword, "cell 0 4");
		
		passwordField = new JPasswordField();
		panelDer.add(passwordField, "cell 0 5,growx");
		panelInferior.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnLogin = new JButton("Login");
		panelInferior.add(btnLogin);
		getContentPane().setLayout(groupLayout);

	}
}