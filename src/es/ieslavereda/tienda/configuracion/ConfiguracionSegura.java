/**
 * 
 */
package es.ieslavereda.tienda.configuracion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

/**
 * Creado el 2 abr. 2019
 * 
 * @author <a href="mailto:joaalsai@ieslavereda.es">Joaquin Vicente Alonso
 *         Saiz</a>
 *
 */
public class ConfiguracionSegura {

	private final String FICHERO = "default.config.properties";

	// LLave para encriptar/desencriptar
	private String key = "ieslavereda.es";

	private LinkedProperties prop;
	private Map<String, String> propiedadesSeguras;

	// Propiedades para la BD
	private String host;
	private String port;
	private String user;
	private String password;

	public ConfiguracionSegura() {

		propiedadesSeguras = new HashMap<String, String>();
		propiedadesSeguras.put("password", "is.password.encripted");

		prop = new LinkedProperties();
		OutputStream output = null;

		File fc = new File(FICHERO);

		if (!fc.isFile()) {

			try {

				output = new FileOutputStream(fc);

				// Propiedades de acceso a la BD
				prop.setProperty("host", "127.0.0.1");
				prop.setProperty("port", "3306");
				prop.setProperty("user", "user");
				prop.setProperty("password", "password");
				prop.setProperty("is.password.encripted", "false");

				// Guardamos las propiedades
				prop.store(output, null);

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (output != null)
					try {
						output.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}

		}

		// cargar las propiedades
		cargar();
	}

	public void guardar() {

		OutputStream output = null;

		File fc = new File(FICHERO);

		try {

			output = new FileOutputStream(fc);

			prop = new LinkedProperties();

			// Propiedades de acceso a la BD
			prop.setProperty("host", this.host);
			prop.setProperty("port", this.port);
			prop.setProperty("user", this.user);
			prop.setProperty("password", encrypt(this.password));
			prop.setProperty("is.password.encripted", "true");

			// Guardamos las propiedades
			prop.store(output, null);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (output != null)
				try {
					output.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

	}

	public void cargar() {

		prop = new LinkedProperties();

		InputStream input = null;

		try {
			// Encriptamos todas las propiedades sin encriptar
			for (String property : propiedadesSeguras.keySet())
				encryptPropertyValue(property, propiedadesSeguras.get(property));

			input = new FileInputStream(FICHERO);

			prop.load(input);

			this.host = prop.getProperty("host");
			this.port = prop.getProperty("port");
			this.user = prop.getProperty("user");

			this.password = decryptPropertyValue("password");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (input != null)
				try {
					input.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

	}

	private void encryptPropertyValue(String propertyKey, String isPropertyKeyEncrypted) throws ConfigurationException {

		// Apache Commons Configuration
		PropertiesConfiguration prop = new PropertiesConfiguration(FICHERO);

		// Retrieve boolean properties value to see if password is already
		// encrypted or not
		String isEncrypted = prop.getString(isPropertyKeyEncrypted);

		// Check if password is encrypted?
		if (isEncrypted.equals("false")) {
			String tmpPwd = prop.getString(propertyKey);
			String encryptedPassword = encrypt(tmpPwd);

			// Overwrite password with encrypted password in the properties file
			// using Apache Commons Cinfiguration library
			prop.setProperty(propertyKey, encryptedPassword);
			// Set the boolean flag to true to indicate future encryption
			// operation that password is already encrypted
			prop.setProperty(isPropertyKeyEncrypted, "true");
			// Save the properties file
			prop.save(FICHERO);
		}
	}

	private String encrypt(String tmpPwd) {
		// Encrypt
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword(key);
		String encryptedPassword = encryptor.encrypt(tmpPwd);
		return encryptedPassword;
	}

	private String decryptPropertyValue(String propertyKey) throws ConfigurationException {
		// System.out.println("Starting decryption");
		PropertiesConfiguration prop = new PropertiesConfiguration(FICHERO);
		String encryptedPropertyValue = prop.getString(propertyKey);

		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword(key);
		String decryptedPropertyValue = encryptor.decrypt(encryptedPropertyValue);

		return decryptedPropertyValue;
	}

	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param host the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * @return the port
	 */
	public String getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(String port) {
		this.port = port;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
