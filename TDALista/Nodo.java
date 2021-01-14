package TDALista;

public class Nodo<E> implements Position<E> {
	
	protected E element;
	protected Nodo<E> next;
	
	
	
	//Constructores:
	public Nodo(E item,Nodo<E>sig) {
		element = item;
		next = sig;
	}	
	
	public Nodo(E item) {
		this(item,null);
	}
	
	public Nodo() {
		this(null,null);
	}
	
	//Setters:
	public void setElemento(E elemento) {
		this.element = elemento;
	}
	
	public void setSiguiente(Nodo<E> siguiente) {
		this.next = siguiente;
	}
	
	//Gettters:
	public E element(){
		return element;
	}
	
	public Nodo<E> getSiguiente(){
		return next;
	}
	
	public Nodo<E> clone(){
		Nodo<E> clon =new Nodo<E>(element);
		clon.setSiguiente(next.clone());
		return clon;
	}
}
