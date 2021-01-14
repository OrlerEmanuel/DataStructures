package TDALista;
/**
 * Implementa una posicion a traves de un nodo.
 * @author Emanuel Orler.
 * @param <E>: tipo de dato abstracto a utilizar.
 */

public class DNode<E> implements Position<E> {
	
	protected DNode<E> prev;
	protected E element;
	protected DNode<E> next; 
	/**
	 * Inicializa los valores de un nodo.
	 * @param p: Nodo previo.
	 * @param e: Elemento a establecer del nodo actual. 
	 * @param n: Nodo siguiente.
	 */
	public DNode(DNode<E> p, E e,DNode<E> n) {
		prev = p;
		element = e;
		next = n;
	}
	public E element() {
		return element;
	}
	
	/**
	 * Obtiene el siguiente nodo.
	 * @return n, donde n es el nodo siguiente al actual.
	 */
	public DNode<E> getSiguiente() {
		return next;
	}
	/**
	 * Obtiene el anterior nodo.
	 * @return n, donde n es el nodo anterior al actual.
	 */
	public DNode<E> getAnterior() {
		return prev;
	}

	/**
	 * Establece el valor del nodo acutal.
	 * @param element: nuevo valor del elemento del nodo actual.
	 */
	public void setElemento(E element) {
		this.element = element;
	}
	/**
	 * Establece el nodo siguiente.
	 * @param siguiente: nuevo nodo siguiente del nodo acutal.
	 */
	public void setSiguiente(DNode<E> siguiente) {
		next = siguiente;
	}
	/**
	 * Establece el nodo anterior.
	 * @param anterior: nuevo nodo anterior del nodo acutal.
	 */
	public void setAnterior(DNode<E> anterior) {
		prev = anterior;
	}
	
}
