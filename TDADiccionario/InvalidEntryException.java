package TDADiccionario;
@SuppressWarnings("serial")
/**
 * Excepcion utilizada en caso de que la entrada sea invalida.
 * @author Emanuel Orler
 */
public class InvalidEntryException extends Exception{
	/**
	 * Inicializa una excepcion.
	 * @param msg: Mensaje a mostra una vez lanzada la excepcion.
	 */
	public InvalidEntryException(String msg) {
		super(msg);
	}
}