/**
 * 
 */
package es.ieslavereda.tienda.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import es.ieslavereda.tienda.classes.Usuario;
import es.ieslavereda.tienda.modelo.Modelo;
import es.ieslavereda.tienda.vista.JFramePrincipal;
import es.ieslavereda.tienda.vista.JIFLogin;
import es.ieslavereda.tienda.vista.JIFUsuarios;

/**
 * Creado el 27 mar. 2019
 * 
 * @author <a href="mailto:joaalsai@ieslavereda.es">Joaquin Vicente Alonso
 *         Saiz</a>
 *
 */
public class Controlador implements ActionListener {

	private JFramePrincipal view;
	private Modelo modelo;

	// formularios hijo
	JIFLogin jifLogin;
	JIFUsuarios jifUsers;

	public Controlador(JFramePrincipal view, Modelo modelo) {
		this.view = view;
		this.modelo = modelo;
		iniciar();
	}

	public void iniciar() {

		view.setTitle("Aplicacion MVC 1ºDAW");

		// Añadir las accionew a los botones del formulario padre
		view.btnListUsers.setActionCommand("Listar usuarios");
		view.btnUsers.setActionCommand("Abrir formulario gestion usuarios");
		view.btnLogin.setActionCommand("Abrir formulario login");
		view.btnSalir.setActionCommand("Cerrar sesion");
		view.btnReport.setActionCommand("Report");
		

		// Ponemos a escuchar las accionew del usuario
		view.btnListUsers.addActionListener(this);
		view.btnUsers.addActionListener(this);
		view.btnLogin.addActionListener(this);
		view.btnSalir.addActionListener(this);
		view.btnReport.addActionListener(this);

	}

	public void start() {
		view.setVisible(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

		String comando = arg0.getActionCommand();
		if (comando.equals("Listar usuarios")) {
			
		} else if (comando.equals("Cerrar formulario listado usuarios")) {
			
		} else if (comando.equals("Abrir formulario gestion usuarios")) {
			openJIFUsers();
		} else if (comando.equals("Alta usuario")) {
			
		} else if (comando.equals("Eliminar usuarios")) {
			
		} else if (comando.equals("Abrir formulario login")) {
			abrirFormularioLogin();
		} else if (comando.equals("Loguear")) {
			loguearUsuario();
		} else if (comando.equals("Cerrar sesion")) {
			cerrarSesion();
		} else if (comando.equals("Editar usuario")) {
			
		} else if (comando.equals("Actualizar Usuario")) {
			
		} else if (comando.equals("Report")) {
			
		}

	}


	private void openJIFUsers() {
		
		if (!estaAbierto(jifUsers)) {
			
			jifUsers = new JIFUsuarios();
			
			view.desktopPane.add(jifUsers);
			jifUsers.setVisible(true);
			
			// Cargamos usuarios en la tabla
			
			ArrayList<Usuario> usuarios = modelo.obtenerUsuarios();
			Vector<String> rowData;
			
			DefaultTableModel dtm = new DefaultTableModel();
			dtm.addColumn("id");
			dtm.addColumn("login");
			dtm.addColumn("role");
			dtm.addColumn("mail");
			
			for(Usuario u : usuarios) {
				
				rowData= new Vector<String>();
				
				rowData.add(String.valueOf(u.getId()));
				rowData.add(u.getLogin());
				rowData.add(u.getRole());
				rowData.add(u.getMail());
				
				dtm.addRow(rowData);
				
			}
			
			jifUsers.getTableUsers().setModel(dtm);		
			
		}
		
	}

	private boolean estaAbierto(JInternalFrame jif) {
		boolean abierto = false;
		JInternalFrame[] internalFrames = view.desktopPane.getAllFrames();

		for (JInternalFrame internalFrame : internalFrames)
			if (jif == internalFrame)
				abierto = true;

		return abierto;
	}

	private void cerrarSesion() {

		int option = JOptionPane.showConfirmDialog(view, "Esta seguro de cerrar sesion?", "Cerrar sesion",
				JOptionPane.YES_NO_OPTION);

		if (option == JOptionPane.YES_OPTION) {
			view.btnListUsers.setEnabled(false);
			view.btnUsers.setEnabled(false);
			view.btnLogin.setEnabled(true);
			view.btnSalir.setEnabled(false);
			view.btnReport.setEnabled(false);

			JInternalFrame[] internalFrames = view.desktopPane.getAllFrames();

			for (JInternalFrame internalFrame : internalFrames)
				internalFrame.dispose();
		}

	}

	private void loguearUsuario() {
		String login = jifLogin.txtFieldLogin.getText();
		String password = String.valueOf(jifLogin.passwordField.getPassword());

		if (modelo.autentificar(login, password)) {
			view.btnUsers.setEnabled(true);
			view.btnListUsers.setEnabled(true);
			view.btnLogin.setEnabled(false);
			view.btnSalir.setEnabled(true);
			view.btnReport.setEnabled(true);
			jifLogin.dispose();
		}

	}

	
	private void abrirFormularioLogin() {
		if (!estaAbierto(jifLogin)) {
			jifLogin = new JIFLogin();
			view.desktopPane.add(jifLogin);
			jifLogin.setVisible(true);

			// Establecemos las acciones para el boton del formulario
			jifLogin.btnLogin.setActionCommand("Loguear");
			jifLogin.btnLogin.addActionListener(this);

		}
	}

	
}
