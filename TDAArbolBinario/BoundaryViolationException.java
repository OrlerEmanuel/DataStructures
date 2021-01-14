package TDAArbolBinario;
@SuppressWarnings("serial")
/**
 * Modela una excepcion utilizada en caso de que se exedan los limites del arbol.
 * @author Emanuel Orler
 */
public class BoundaryViolationException extends Exception {
	/**
	 * Inicializa la excepcion.
	 * @param msg: Mensaje a mostrar una vez lanzada la excepcion.
	 */
	public BoundaryViolationException(String msg) {
		super(msg);
	}
}
