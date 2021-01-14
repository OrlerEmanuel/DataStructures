package TDALista;
@SuppressWarnings("serial")
/**
 * Excepcion utilizada en caso de que una posicion en la lista no sea valida.
 * @author Emanuel Orler.
 */
public class InvalidPositionException extends Exception {
	/**
	 * Inicializa los valores de la excepcion.
	 * @param msg: mensaje de la excepcion.
	 */
	public InvalidPositionException(String msg) {
		super(msg);
	}
}
