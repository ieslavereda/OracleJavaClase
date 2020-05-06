package es.ieslavereda.tienda.test;

import es.ieslavereda.tienda.classes.Usuario;
import es.ieslavereda.tienda.modelo.Modelo;

public class Test {

	public static void main(String[] args) {
		
		Modelo m = new Modelo();
		Usuario u = new Usuario(8, "TERESAS", "teresa@ieslavereda.es", "admin", "1111");		
		//System.out.println(m.insertarUsuario(u));
		
		m.actualizarUsuario(u);
		//m.eliminarUsuario(5);
		
		
	}

}
