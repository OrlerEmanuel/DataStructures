package TDAColaCP;
@SuppressWarnings("serial")
/**
 * Modela la excepcion utilizada en caso de que la clave sea invalida.
 * @author Emanuel Orler
 */
public class InvalidKeyException extends Exception{
	/**
	 * Inicializa una excepcion.
	 * @param msg: Mensaje a mostra una vez lanzada la excepcion.
	 */
	public InvalidKeyException(String msg) {
		super(msg);
	}
}
