package TDAArbolBinario;
@SuppressWarnings("serial")
/**
 * Modela la excepcion utilizada en caso de que el arbol este vacio.
 * @author Emanuel Orler
 */
public class EmptyTreeException extends Exception{
	/**
	 * Inicializa una excepcion.
	 * @param msg: Mensaje a mostrar una vez lanzada la excepcion.
	 */
	public EmptyTreeException(String msg) {
		super(msg);
	}
}