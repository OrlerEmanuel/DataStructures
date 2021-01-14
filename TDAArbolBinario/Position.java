package TDAArbolBinario;
/**
 * Modela una posicion.
 * @author Emanuel Orler.
 * @param <E>: tipo de dato abstracto a utilizar.
 */
public interface Position<E> {
	/**
	 * Obtiene el elemento de la posicion.
	 * @return e, donde e es elemento de la posicion.
	 */
	public E element();
}
