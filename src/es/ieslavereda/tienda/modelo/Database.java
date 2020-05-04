/**
 * 
 */
package es.ieslavereda.tienda.modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import es.ieslavereda.tienda.configuracion.ConfiguracionSegura;

/**
 * Creado el 26 mar. 2019
 * 
 * @author <a href="mailto:joaalsai@ieslavereda.es">Joaquin Vicente Alonso
 *         Saiz</a>
 *
 */
public abstract class Database {

	private String host;
	private String port;
	private String database;
	private String user;
	private String password;

	private Connection conexion;

	public Database() {

		ConfiguracionSegura conf = new ConfiguracionSegura();
		
		this.host = conf.getHost();
		this.port = conf.getPort();
		this.database = "JAVA";
		this.user = conf.getUser();
		this.password = conf.getPassword();

	}

	public Connection conectar() {

		conexion = null;
		ConfiguracionSegura conf = new ConfiguracionSegura();

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String BaseDeDatos = "jdbc:oracle:thin:@" + host + ":" + port + ":XE";
			conexion = DriverManager.getConnection(BaseDeDatos, user, password);
			if (conexion != null)
				System.out.println("Conexión realizada con éxito a MUNDIAL");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return conexion;
	}

	public void desconectar() {
		try {
			conexion.close();
			System.out.println("Desconexion realizada con exito");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	

}
