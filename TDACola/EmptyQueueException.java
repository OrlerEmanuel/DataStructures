package TDACola;
@SuppressWarnings("serial")
/**
 * Excepcion utilizada en caso de que una cola este vacia.
 * @author Emanuel Orler.
 */
public class EmptyQueueException extends Exception{
	/**
	 * Inicializa los valores de la excepcion.
	 * @param msg: mensaje de la excepcion.
	 */
	public EmptyQueueException(String msg) {
		super(msg);
	}
}
