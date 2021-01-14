package TDAPila;
/**
 * Modola un nodo.
 * @author Emanuel Orler.
 * @param <E>: tipo de dato abstracto a utilizar.
 */
public class Nodo<E> {
	private E elemento;
	private Nodo<E>siguiente;
	
	/**
	 * Inicializa los valores de un Nodo.
	 * @param item: valor que se le asigna al nodo.
	 * @param sig: nodo siguiente al nodo actual.
	 */
	public Nodo(E item,Nodo<E>sig) {
		elemento = item;
		siguiente = sig;
	}	
	/**
	 * Establece otro valor al nodo.
	 * @param elemento: nuevo valor del nodo.
	 */
	public void setElemento(E elemento) {
		this.elemento = elemento;
	}
	
	/**
	 * Establce el nodo siguiente al actual.
	 * @param siguiente: nodo a establecer como siguiente.
	 */
	public void setSiguiente(Nodo<E> siguiente) {
		this.siguiente = siguiente;
	}
	/**
	 * Obtiene el elemento del nodo actual.
	 * @return e, donde e es el elemento actual del nodo.
	 */
	public E getElemento() {
		return elemento;
	}
	/**
	 * Obtiene el nodo siguiente.
	 * @return n, donde n es el nodo siguiente al nodo actual.
	 */
	public Nodo<E> getSiguiente(){
		return siguiente;
	}
}
