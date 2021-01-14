package TDAArbol;
/**
 * Modela la excepcion utilizada en caso de que una operacion sea invalida.
 * @author Emanuel Orler
 */
@SuppressWarnings("serial")
public class InvalidPositionException extends Exception {
	/**
	 * Inicializa una excepcion.
	 * @param msg: Mensaje a mostrar una vez lanzada la excepcion.
	 */
	public InvalidPositionException(String msg) {
		super(msg);
	}
}
