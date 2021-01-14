package TDAMapeo;
/**
 * Implenta una entrada
 * @author Emanuel Orler
 * @param <K>: Tipo de dato de la clave.
 * @param <V>: Tipo de dato del valor.
 */
public class Entrada<K,V> implements Entry<K,V> {
	protected K key;
	protected V value;
	/**
	 * Inicializa los valores de una entrada.
	 * @param key: Valor a dar a la clave.
	 * @param value: Valor a asignar.
	 */
	public Entrada(K key, V value) {
		this.key = key;
		this.value = value;
	}
	@Override
	public K getKey() {
		return key;
	}

	@Override
	public V getValue() {
		return value;
	}
	/**
	 * Modifica la clave.
	 * @param key: Valor de la nueva clave.
	 */
	public void setKey(K key) {
		this.key = key;
	}
	/**
	 * Modifica el valor.
	 * @param value: Nuevo valor a asignar.
	 */
	public void setValue(V value) {
		this.value = value;
	}
}
