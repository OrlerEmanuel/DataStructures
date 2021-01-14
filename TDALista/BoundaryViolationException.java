package TDALista;
@SuppressWarnings("serial")
/**
 * Excepcion utilizada en caso de que se excedan los limites de una lista.
 * @author Emanuel Orler.
 */
public class BoundaryViolationException extends Exception {
	/**
	 * Inicializa los valores de la excepcion.
	 * @param msg: mensaje de la excepcion.
	 */
	public BoundaryViolationException(String msg) {
		super(msg);
	}
}
