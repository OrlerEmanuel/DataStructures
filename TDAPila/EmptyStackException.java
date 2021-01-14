package TDAPila;
@SuppressWarnings("serial")
/**
 * Modela la excepcion utilizada en caso de que una pila este vacia.
 * @author Emanuel Orler.
 */
public class EmptyStackException extends Exception{
	/**
	 * Inicializa los valores de la excepcion.
	 * @param msg: mensaje de la excepcion.
	 */
	public EmptyStackException(String msg) {
		super(msg);
	}
}
