package es.ieslavereda.tienda.vista;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

import es.ieslavereda.tienda.classes.Usuario;
import net.miginfocom.swing.MigLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class JIFFormularioUsuarios extends JInternalFrame {
	private JButton btnAction;
	private JTextField textFieldLogin;
	private JPasswordField passwordField;
	private JTextField textFieldMail;
	private JComboBox comboBoxRole;
	private Usuario usuario;

	/**
	 * Create the frame.
	 */
	public JIFFormularioUsuarios() {
		setClosable(true);
		setTitle("Usuario");
		setBounds(100, 100, 259, 300);

		JPanel panel = new JPanel();

		JPanel panel_1 = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout
				.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
						groupLayout.createSequentialGroup().addContainerGap()
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 414,
												Short.MAX_VALUE)
										.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 414,
												Short.MAX_VALUE))
								.addContainerGap()));
		groupLayout
				.setVerticalGroup(
						groupLayout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
								groupLayout.createSequentialGroup().addContainerGap()
										.addComponent(panel, GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(panel_1,
												GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
										.addContainerGap()));
		panel_1.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		btnAction = new JButton("New button");
		panel_1.add(btnAction);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel_1.add(btnCancel);
		panel.setLayout(new MigLayout("", "[][grow]", "[][][][][]"));

		JLabel lblNewLabel = new JLabel("Login");
		panel.add(lblNewLabel, "cell 0 1,alignx trailing");

		textFieldLogin = new JTextField();
		panel.add(textFieldLogin, "cell 1 1,growx");
		textFieldLogin.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Password");
		panel.add(lblNewLabel_1, "cell 0 2,alignx trailing");

		passwordField = new JPasswordField();
		panel.add(passwordField, "cell 1 2,growx");

		JLabel lblNewLabel_2 = new JLabel("Mail");
		panel.add(lblNewLabel_2, "cell 0 3,alignx trailing,aligny baseline");

		textFieldMail = new JTextField();
		panel.add(textFieldMail, "cell 1 3,growx");
		textFieldMail.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Rol");
		panel.add(lblNewLabel_3, "cell 0 4,alignx trailing");

		comboBoxRole = new JComboBox();
		comboBoxRole.setModel(new DefaultComboBoxModel(new String[] { "admin", "user" }));
		panel.add(comboBoxRole, "cell 1 4,growx");
		getContentPane().setLayout(groupLayout);

	}

	public JTextField getTextFieldLogin() {
		return textFieldLogin;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public JTextField getTextFieldMail() {
		return textFieldMail;
	}

	public JComboBox getComboBoxRole() {
		return comboBoxRole;
	}

	public JButton getBtnAction() {
		return btnAction;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
