/**
 * 
 */
package es.ieslavereda.tienda.modelo;

import java.awt.Image;
import java.io.File;
import java.sql.Blob;
import java.sql.Connection;
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
		
		try(Connection con = conectar();
				Statement st = con.createStatement();){

			st.execute(sql);
			insertado=true;			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return insertado;
	}
	public boolean actualizarUsuario(Usuario usuario) {
		boolean actualizado = false;
		
		return actualizado;
	}
	
	public boolean eliminarUsuario(int id) {
		
		boolean eliminado = false;
		
		String sql = "DELETE FROM Usuario WHERE id="+id;
				
		try(Connection con = conectar();
				Statement st = con.createStatement();){
			
			st.execute(sql);
			
			eliminado=true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return eliminado;
	}

	public ArrayList<Usuario> obtenerUsuarios() {

		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

		int id;
		String login;
		String mail;
		String role;
		String password;
		Usuario usuario;

		String sql = "SELECT * FROM Usuario";

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
