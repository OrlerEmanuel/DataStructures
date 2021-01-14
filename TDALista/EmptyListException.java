package TDALista;
@SuppressWarnings("serial")
/**
 * Excepcion utilizada en caso de que una una lista este vacia.
 * @author Emanuel Orler.
 */
public class EmptyListException extends Exception{
	/**
	 * Inicializa los valores de la excepcion.
	 * @param msg: mensaje de la excepcion.
	 */
	public EmptyListException(String msg) {
		super(msg);
	}
}