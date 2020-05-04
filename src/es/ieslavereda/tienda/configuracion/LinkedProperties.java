/**
 * 
 */
package es.ieslavereda.tienda.configuracion;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Properties;

/**
 * Creado el 2 abr. 2019
 * 
 * @author <a href="mailto:joaalsai@ieslavereda.es">Joaquin Vicente Alonso
 *         Saiz</a>
 *
 */
public class LinkedProperties extends Properties {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1193666510797113239L;
	private final HashSet<Object> keys = new LinkedHashSet<Object>();

	public LinkedProperties() {

	}

	public Iterable<Object> orderedKeys() {
		return Collections.list(keys());
	}

	public Enumeration<Object> keys() {
		return Collections.<Object>enumeration(keys);
	}
	
	public Object put(Object key,Object value) {
		keys.add(key);
		return super.put(key, value);
	}
	

}
