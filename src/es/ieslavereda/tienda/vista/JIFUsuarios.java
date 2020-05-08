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
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;

public class JIFUsuarios extends JInternalFrame {
	private JTable tableUsers;
	private JButton btnDelUser;
	private JButton btnEditUser;
	private JButton btnAddUser;
	private JComboBox comboBoxOrder;
	private JComboBox comboBoxSort;
	private JTextField textFieldWhere;
	private JComboBox comboBoxWhere;
	private JButton btnSearch;

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
		panel_1.setLayout(new MigLayout("", "[][grow][grow][][][12.00][][][13.00][]", "[]"));
		
		JLabel lblNewLabel_2 = new JLabel("Search by");
		panel_1.add(lblNewLabel_2, "cell 0 0,alignx trailing");
		
		comboBoxWhere = new JComboBox();
		comboBoxWhere.setModel(new DefaultComboBoxModel(new String[] {"login", "mail", "role"}));
		panel_1.add(comboBoxWhere, "cell 1 0,growx");
		
		textFieldWhere = new JTextField();
		panel_1.add(textFieldWhere, "cell 2 0,growx");
		textFieldWhere.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Sort by");
		panel_1.add(lblNewLabel, "cell 3 0,alignx trailing");
		
		comboBoxSort = new JComboBox();
		comboBoxSort.setModel(new DefaultComboBoxModel(new String[] {"id", "login", "mail", "role"}));
		panel_1.add(comboBoxSort, "cell 4 0,growx");
		
		JLabel lblNewLabel_1 = new JLabel("Orden");
		panel_1.add(lblNewLabel_1, "cell 6 0,alignx trailing");
		
		comboBoxOrder = new JComboBox();
		comboBoxOrder.setModel(new DefaultComboBoxModel(new String[] {"Ascendente", "Descendente"}));
		panel_1.add(comboBoxOrder, "cell 7 0,growx");
		
		btnSearch = new JButton("Search");
		panel_1.add(btnSearch, "cell 9 0");
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

	public JComboBox getComboBoxOrder() {
		return comboBoxOrder;
	}

	public JComboBox getComboBoxSort() {
		return comboBoxSort;
	}

	public JTextField getTextFieldWhere() {
		return textFieldWhere;
	}

	public JComboBox getComboBoxWhere() {
		return comboBoxWhere;
	}

	public JButton getBtnSearch() {
		return btnSearch;
	}
	
	
}
