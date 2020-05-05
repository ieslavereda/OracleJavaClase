package es.ieslavereda.tienda.vista;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JIFUsuarios extends JInternalFrame {
	private JTable tableUsers;
	private JButton btnDelUser;
	private JButton btnEditUser;
	private JButton btnAddUser;

	/**
	 * Create the frame.
	 */
	public JIFUsuarios() {
		setClosable(true);
		setTitle("Usuarios");
		setBounds(100, 100, 688, 423);
		
		JPanel panelTabla = new JPanel();
		
		JPanel panel_1 = new JPanel();
		
		JPanel panelBotones = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panelBotones, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE)
						.addComponent(panelTabla, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panelTabla, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(panelBotones, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panelBotones.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		btnAddUser = new JButton("Add");
		panelBotones.add(btnAddUser);
		
		btnDelUser = new JButton("Delete");
		panelBotones.add(btnDelUser);
		
		btnEditUser = new JButton("Edit");
		panelBotones.add(btnEditUser);
		panelBotones.add(btnCancel);
		panelTabla.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panelTabla.add(scrollPane, BorderLayout.CENTER);
		
		tableUsers = new JTable();
		scrollPane.setViewportView(tableUsers);
		getContentPane().setLayout(groupLayout);

	}

	public JTable getTableUsers() {
		return tableUsers;
	}

	public JButton getBtnDelUser() {
		return btnDelUser;
	}

	public JButton getBtnEditUser() {
		return btnEditUser;
	}

	public JButton getBtnAddUser() {
		return btnAddUser;
	}
	
	
}
