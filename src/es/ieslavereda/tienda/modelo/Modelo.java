/**
 * 
 */
package es.ieslavereda.tienda.modelo;

import java.awt.Image;
import java.io.File;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.ImageIcon;

import es.ieslavereda.tienda.classes.Usuario;

/**
 * Creado el 27 mar. 2019
 * 
 * @author <a href="mailto:joaalsai@ieslavereda.es">Joaquin Vicente Alonso
 *         Saiz</a>
 *
 */
public class Modelo extends Database {

	public Modelo() {
		super();
	}

	public boolean autentificar(String login, String password) {
		boolean exito = false;
		int cantidad = contar(login, password);

		if (cantidad == 1)
			exito = true;

		return exito;
	}

	public boolean validar(String login, String password) throws SQLException {

		boolean validado = false;

		String sql = "SELECT COUNT(*) AS CANTIDAD FROM Usuario WHERE login=? AND password=? ";

		try (Connection con = conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

			int pos = 0, cantidad;

			ps.setString(++pos, login);
			ps.setString(++pos, password);

//			ps.setString(1, login);
//			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			rs.next();

			cantidad = rs.getInt("CANTIDAD");

			if (cantidad == 1)
				validado = true;

		}

		return validado;
	}

	protected int contar(String login, String password) {

		int cantidad;
		String sql = "SELECT COUNT(*) AS CANTIDAD FROM Usuario WHERE login='" + login + "' AND password='" + password
				+ "'";

		try (Connection con = conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql);) {

			rs.next();

			cantidad = rs.getInt("CANTIDAD");

			return cantidad;

		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public boolean insertarUsuario(Usuario usuario) {
		boolean insertado = false;

		String sql = "INSERT INTO Usuario (login ,mail ,role ,password) VALUES ('" + usuario.getLogin() + "','"
				+ usuario.getMail() + "','" + usuario.getRole() + "','" + usuario.getPassword() + "')";

		try (Connection con = conectar(); Statement st = con.createStatement();) {

			st.execute(sql);
			insertado = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return insertado;
	}

//	public boolean actualizarUsuario(Usuario u) {
//		boolean actualizado = false;
//
//		String sql = "UPDATE Usuario SET login='" + u.getLogin() + "' ,mail='" + u.getMail() + "' ,role='" + u.getRole()
//				+ "' ,password='" + u.getPassword() + "' WHERE id=" + u.getId();
//		
//		System.out.println(sql);
//
//		try (Connection con = conectar(); Statement st = con.createStatement()) {
//
//			st.execute(sql);
//			actualizado = true;
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		return actualizado;
//	}

	public boolean actualizarUsuario(Usuario u) {
		boolean actualizado = false;

		String sql = "UPDATE Usuario SET login=? ,mail=? ,role=? ,password=? WHERE id=?";

		try (Connection con = conectar(); PreparedStatement pst = con.prepareStatement(sql)) {

			int pos = 0, cantidad;

			pst.setString(++pos, u.getLogin());
			pst.setString(++pos, u.getMail());
			pst.setString(++pos, u.getRole());
			pst.setString(++pos, u.getPassword());
			pst.setInt(++pos, u.getId());

			pst.execute();

			actualizado = true;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return actualizado;
	}
	
	public boolean eliminarUsuario(int id) {

		boolean eliminado = false;

		String sql = "DELETE FROM Usuario WHERE id=?";

		try (Connection con = conectar(); PreparedStatement st = con.prepareStatement(sql);) {
			
			st.setInt(1, id);

			int cantidad = st.executeUpdate();

			System.out.println(cantidad);
			
			eliminado = true;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return eliminado;
	}

//	public boolean eliminarUsuario(int id) {
//
//		boolean eliminado = false;
//
//		String sql = "DELETE FROM Usuario WHERE id=" + id;
//
//		try (Connection con = conectar(); Statement st = con.createStatement();) {
//
//			st.execute(sql);
//
//			eliminado = true;
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		return eliminado;
//	}

	public ArrayList<Usuario> obtenerUsuarios(String where) {
		return obtenerUsuarios(where, "");
	}

	public ArrayList<Usuario> obtenerUsuarios() {
		return obtenerUsuarios("", "");
	}

	public ArrayList<Usuario> obtenerUsuarios(String where, String order) {

		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

		int id;
		String login;
		String mail;
		String role;
		String password;
		Usuario usuario;

		String sql = "SELECT * FROM Usuario";

		if (!where.equals("")) {
			sql += " WHERE " + where;
		}

		if (!order.equals("")) {
			sql += " ORDER BY " + order;
		}

		System.out.println(sql);

		try (Connection con = conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql);) {

			while (rs.next()) {

				id = rs.getInt("id");
				login = rs.getString("login");
				mail = rs.getString("mail");
				role = rs.getString("role");
				password = rs.getString("password");

				usuario = new Usuario(id, login, mail, role, password);

				usuarios.add(usuario);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return usuarios;
	}

}
