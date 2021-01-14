package TDALista;

import java.util.Iterator;
/**
 * Implementa una lista a traves de nodos doblemente enlazados
 * @author Emanuel Orler.
 * @param <E>: tipo de dato abstracto a utilizar.
 */
public class DoubleLinkedList<E> implements PositionList<E>{
	protected DNode<E> header;
	protected DNode<E> trailer;
	protected int size;
	/**
	 * Inicializa los valores de una lista doble enlazada.
	 */
	public DoubleLinkedList() {
		header = new DNode<E>(null,null,null);
		trailer = new DNode<E>(header,null,null);
		header.setSiguiente(trailer);	
		size = 0;
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
	public Position<E> first() throws EmptyListException {
		if(isEmpty())
			throw new EmptyListException("La lista esta vacia");
		return header.getSiguiente();
	}
	@Override
	public Position<E> last() throws EmptyListException {
		if(isEmpty())
			throw new EmptyListException("La lista esta vacia");
		return trailer.getAnterior();
	}
	@Override
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		DNode<E> n = checkPosition(p);
		if(n == trailer.getAnterior())
			throw new BoundaryViolationException("La posicion excede a la limetes de la lista");
		return n.getSiguiente();
	}
	@Override
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		DNode<E> n = checkPosition(p);
		if(n == header.getSiguiente())
			throw new BoundaryViolationException("La posicion excede a la limetes de la lista");
		return n.getAnterior();
	}
	@Override
	public void addFirst(E element) {
		DNode<E> new_header = new DNode<E>(header,element,header.getSiguiente());
		header.getSiguiente().setAnterior(new_header);
		header.setSiguiente(new_header);
		size++;
	}
	@Override
	public void addLast(E element) {
		DNode<E> new_trailer = new DNode<E>(trailer.getAnterior(),element,trailer);
		trailer.getAnterior().setSiguiente(new_trailer);
		trailer.setAnterior(new_trailer);
		size++;
	}
	@Override
	public void addAfter(Position<E> p, E element) throws InvalidPositionException {
		DNode<E> n = checkPosition(p);
		DNode<E> new_position = new DNode<E>(n,element,n.getSiguiente());
		n.getSiguiente().setAnterior(new_position);
		n.setSiguiente(new_position);
		size++;
	}
	@Override
	public void addBefore(Position<E> p, E element) throws InvalidPositionException {
		DNode<E> n = checkPosition(p);
		DNode<E> new_position = new DNode<E>(n.getAnterior(),element,n);
		n.getAnterior().setSiguiente(new_position);
		n.setAnterior(new_position);
		size++;
	}
	@Override
	public E remove(Position<E> p) throws InvalidPositionException{
		DNode<E> n = checkPosition(p);
		n.getAnterior().setSiguiente(n.getSiguiente());
		n.getSiguiente().setAnterior(n.getAnterior());
		E toReturn = n.element();
		n.setAnterior(null);
		n.setSiguiente(null);
		n.setElemento(null);
		size--;
		return toReturn;
	}
	@Override
	public E set(Position<E> p, E element) throws InvalidPositionException{
		DNode<E> n = checkPosition(p);
		E toReturn = n.element();
		n.setElemento(element);
		return toReturn;
	}
	@Override
	public Iterator<E> iterator(){
		return new ElementIterator<E>(this);
	}
	@Override
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> iterable = new DoubleLinkedList<Position<E>>();
		if(!isEmpty()) {
			DNode<E> aux = header.getSiguiente();
			while(aux != trailer) {
				iterable.addLast(aux);
				aux = aux.getSiguiente();
			}	
		}
		return iterable;
	}
	/**
	 * Verifica que una posicion sea valida dentro de la lista.
	 * @param p: posicion a chequear.
	 * @return n, donde n es un nodo identificado con la posicion p, en caso de que p sea una posicion valida. 
	 * @throws InvalidPositionException
	 */
	private DNode<E> checkPosition(Position<E> p) throws InvalidPositionException {
		try {
			if(p == null)
				throw new InvalidPositionException("Posicion nula");
			if(p.element() == null)
				throw new InvalidPositionException("Posicion eliminada anteriormente");
			return (DNode<E>) p;
		}
		catch(ClassCastException e) {
			throw new InvalidPositionException("La posicion es de otra lista");
		}
	}
}