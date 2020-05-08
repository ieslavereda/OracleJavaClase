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
import es.ieslavereda.tienda.vista.JIFFormularioUsuarios;
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
	JIFFormularioUsuarios jifFormularioUsuarios;
	

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
		} else if (comando.equals("Abrir formulario login")) {
			abrirFormularioLogin();
		} else if (comando.equals("Loguear")) {
			loguearUsuario();
		} else if (comando.equals("Cerrar sesion")) {
			cerrarSesion();
		} else if (comando.equals("Update user")) {
			updateUser();
		} else if (comando.equals("Edit user")) {
			openEditUserJIFrame();
		} else if (comando.equals("Actualizar tabla usuarios")) {
			actualizarTablaUsuarios();
		} else if (comando.equals("Del users")) {
			delUsers();
		} else if (comando.equals("Add user")) {
			openAddUserInternalFrame();
		} else if (comando.equals("Add new user")) {
			addNewUser();
		}
		
	}



	private void updateUser() {
		int option = JOptionPane.showConfirmDialog(jifUsers, "Esta usted seguro que desea modificar el usuario?", "Question",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		if (option == JOptionPane.YES_OPTION) {
			
			Usuario u = jifFormularioUsuarios.getUsuario();
			
			u.setLogin(jifFormularioUsuarios.getTextFieldLogin().getText());
			u.setMail(jifFormularioUsuarios.getTextFieldMail().getText());
			u.setPassword(String.valueOf(jifFormularioUsuarios.getPasswordField().getPassword()));
			u.setRole(jifFormularioUsuarios.getComboBoxRole().getSelectedItem().toString());
			
			if(modelo.actualizarUsuario(u)) {
				JOptionPane.showMessageDialog(jifFormularioUsuarios, "Usuario actualizado correctamente.", "Info", JOptionPane.INFORMATION_MESSAGE);
				u = null;
				jifFormularioUsuarios.dispose();
			}else {
				JOptionPane.showMessageDialog(jifFormularioUsuarios, "El usuario no se ha podido actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
		
	}

	private void openEditUserJIFrame() {
		
		int fila = jifUsers.getTableUsers().getSelectedRow();

		if (fila == -1) {
			JOptionPane.showMessageDialog(jifUsers, "Debe seleccionar previamente el usuario a editar", "Error",
					JOptionPane.ERROR_MESSAGE);
		} else {
			
			int id = Integer.parseInt(jifUsers.getTableUsers().getValueAt(fila, 0).toString());
			String login = jifUsers.getTableUsers().getValueAt(fila, 1).toString();
			String role = jifUsers.getTableUsers().getValueAt(fila, 2).toString();
			String mail = jifUsers.getTableUsers().getValueAt(fila, 3).toString();
			
			
			Usuario u = new Usuario(id,login,mail,role,"");
			
			jifFormularioUsuarios = new JIFFormularioUsuarios();
			
			jifFormularioUsuarios.getTextFieldLogin().setText(login);
			jifFormularioUsuarios.getTextFieldMail().setText(mail);
			jifFormularioUsuarios.getComboBoxRole().setSelectedItem(role);
			
			view.desktopPane.add(jifFormularioUsuarios);
			jifFormularioUsuarios.setVisible(true);
			
			jifFormularioUsuarios.getBtnAction().setText("Update");
			jifFormularioUsuarios.getBtnAction().addActionListener(this);
			jifFormularioUsuarios.getBtnAction().setActionCommand("Update user");
			
			
			jifFormularioUsuarios.setUsuario(u);
			
		}
		
	}

	private void addNewUser() {

		int option = JOptionPane.showConfirmDialog(jifUsers, "Esta usted seguro de añadir al usuario?", "Question",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		if (option == JOptionPane.YES_OPTION) {

			String login = jifFormularioUsuarios.getTextFieldLogin().getText().replaceAll(" ", "");
			String mail = jifFormularioUsuarios.getTextFieldMail().getText().replaceAll(" ", "");
			String role = jifFormularioUsuarios.getComboBoxRole().getSelectedItem().toString();
			String password = String.valueOf(jifFormularioUsuarios.getPasswordField().getPassword());

			if (modelo.insertarUsuario(new Usuario(login, mail, role, password))) {
				JOptionPane.showMessageDialog(jifFormularioUsuarios, "Usuario insertado", "Info", JOptionPane.INFORMATION_MESSAGE);
				actualizarTablaUsuarios();
				jifFormularioUsuarios.dispose();
			}else {
				JOptionPane.showMessageDialog(jifFormularioUsuarios, "Usuario no insertado", "Error", JOptionPane.ERROR_MESSAGE);
			}

		}
	}

	private void openAddUserInternalFrame() {

		jifFormularioUsuarios = new JIFFormularioUsuarios();

		// Añadimos los listeners
		jifFormularioUsuarios.getBtnAction().addActionListener(this);
		jifFormularioUsuarios.getBtnAction().setActionCommand("Add new user");

		// modificamos el texto del boton
		jifFormularioUsuarios.getBtnAction().setText("Add");

		// Hacemos visible en el desktop pane
		jifFormularioUsuarios.setVisible(true);
		view.desktopPane.add(jifFormularioUsuarios);

	}

	private void delUsers() {

		int[] filas = jifUsers.getTableUsers().getSelectedRows();
		int option;
		int id;

		if (filas.length == 0) {
			JOptionPane.showMessageDialog(jifUsers, "Debe seleccionar previamente los usuarios a elminar", "Error",
					JOptionPane.ERROR_MESSAGE);
		} else {
			option = JOptionPane.showConfirmDialog(jifUsers,
					"Esta usted seguro de eliminar los " + filas.length + " usuarios seleccionados?",
					"Eliminacion de usuarios", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

			if (option == JOptionPane.YES_OPTION) {

				for (int fila : filas) {

					id = Integer.parseInt(jifUsers.getTableUsers().getValueAt(fila, 0).toString());
					modelo.eliminarUsuario(id);

				}

				JOptionPane.showMessageDialog(jifUsers, "Usuarios eliminados", "Info", JOptionPane.INFORMATION_MESSAGE);

				actualizarTablaUsuarios();
			}
		}

	}

	private void openJIFUsers() {

		if (!estaAbierto(jifUsers)) {

			jifUsers = new JIFUsuarios();

			view.desktopPane.add(jifUsers);
			jifUsers.setVisible(true);

			// Establecemos los listeners
			jifUsers.getBtnDelUser().addActionListener(this);
			jifUsers.getBtnAddUser().addActionListener(this);
			jifUsers.getBtnEditUser().addActionListener(this);
			jifUsers.getComboBoxSort().addActionListener(this);
			jifUsers.getComboBoxOrder().addActionListener(this);
			jifUsers.getBtnSearch().addActionListener(this);

			jifUsers.getBtnDelUser().setActionCommand("Del users");
			jifUsers.getBtnAddUser().setActionCommand("Add user");
			jifUsers.getBtnEditUser().setActionCommand("Edit user");
			jifUsers.getComboBoxSort().setActionCommand("Actualizar tabla usuarios");
			jifUsers.getComboBoxOrder().setActionCommand("Actualizar tabla usuarios");
			jifUsers.getBtnSearch().setActionCommand("Actualizar tabla usuarios");

			// Cargamos usuarios en la tabla

			actualizarTablaUsuarios();

		}

	}
	private void actualizarTablaUsuarios() {
		
		String where="";
		String order;
		
		if(!jifUsers.getTextFieldWhere().getText().isBlank()) {
			where+=jifUsers.getComboBoxWhere().getSelectedItem().toString() + " LIKE '%" + jifUsers.getTextFieldWhere().getText() + "%' ";
		}
		
		order = jifUsers.getComboBoxSort().getSelectedItem().toString();
		order += (jifUsers.getComboBoxOrder().getSelectedItem().toString().equals("Ascendente"))?" ASC":" DESC";
		
		ArrayList<Usuario> usuarios = modelo.obtenerUsuarios(where, order);
		
		actualizarTablaUsuarios(usuarios);
		
	}

	private void actualizarTablaUsuarios(ArrayList<Usuario> usuarios) {

		Vector<String> rowData;

		DefaultTableModel dtm = new DefaultTableModel();
		dtm.addColumn("id");
		dtm.addColumn("login");
		dtm.addColumn("role");
		dtm.addColumn("mail");

		for (Usuario u : usuarios) {

			rowData = new Vector<String>();

			rowData.add(String.valueOf(u.getId()));
			rowData.add(u.getLogin());
			rowData.add(u.getRole());
			rowData.add(u.getMail());

			dtm.addRow(rowData);

		}

		jifUsers.getTableUsers().setModel(dtm);

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
