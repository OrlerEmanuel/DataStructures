package TDAPila;
/**
 * Implenta una pila.
 * @author Emanuel Orler.
 * @param <E>: tipo de abstracto a utilizar.
 */
public class Pila_con_enlaces <E> implements Stack <E> {
	protected Nodo <E> head;
	protected int size;
	
	public Pila_con_enlaces() {
		size = 0;
		head = null;
	}
	@Override
	public int size() {
		return size;
	}
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	@Override
	public E top()throws EmptyStackException{
		if(size == 0)
			throw new EmptyStackException("La pila esta vacia");
		return head.getElemento();
	}
	@Override
	public void push(E element) {
		head = new Nodo<E>(element,head);
		size++;
	}
	@Override
	public E pop() throws EmptyStackException{
		E toReturn;
		if(size == 0)
			throw new EmptyStackException("La pila esta vacia");
		toReturn = head.getElemento();
		head = head.getSiguiente();
		size--;
		return toReturn;
	}
}
