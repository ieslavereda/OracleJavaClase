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
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

}
