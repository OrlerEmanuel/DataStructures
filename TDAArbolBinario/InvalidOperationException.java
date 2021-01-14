package TDAArbolBinario;
@SuppressWarnings("serial")
/**
 * Excepcion utilizada en caso de que una posicion en la lista no sea valida.
 * @author Emanuel Orler.
 */
public class InvalidOperationException extends Exception {
	/**
	 * Inicializa los valores de la excepcion.
	 * @param msg: mensaje de la excepcion.
	 */
	public InvalidOperationException(String msg) {
		super(msg);
	}
}
