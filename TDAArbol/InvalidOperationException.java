package TDAArbol;
@SuppressWarnings("serial")
/**
 * MOdela la excepcion utilizada en caso de que una operacion sea invalida.
 * @author Emanuel Orler
 */
public class InvalidOperationException extends Exception{
	/**
	 * Inicializa una excepcion.
	 * @param msg: Mensaje a mostrar una vez lanzada la excepcion.
	 */
	public InvalidOperationException(String msg) {
		super(msg);
	}
}