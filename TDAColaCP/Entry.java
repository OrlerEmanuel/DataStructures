package TDAColaCP;
/**
 * Modela una entrada en un mapeo.
 * @author Emanuel Orler
 * @param <K>: Tipo de dato de la clave.
 * @param <V>: TIpo de dato del valor.
 */
public interface Entry<K,V> {
	/**
	 * Obtiene el valor de la clave de la entrada.
	 * @return k, donde k es la clave de la entrada.
	 */
	public K getKey();
	/**
	 * Obtiene el valor del valor de la entrada.
	 * @return v, donde v es el valor de la entrada.
	 */
	public V getValue();
}
