package TDALista;

import java.util.Iterator;
public class ListaDoblementeEnlazada<E> implements PositionList<E> {
	protected DNode <E> header;
	protected DNode <E> trailer;
	protected int size;
	/**
	 * 
	 */
	public ListaDoblementeEnlazada() {
		header = new DNode<E>(null, null, null); // El r�tulo de la cabeza es null.
		trailer = new DNode<E>(header,null,null); // El r�tulo del rabo es null.
		trailer.setAnterior(header); // El previo del rabo es la cabeza.
		size = 0; // La lista no tiene elementos.
		}
	@Override
	public int size() {
		return size;
	}
	@Override
	public boolean isEmpty() {
		return size==0;
	}
	@Override
	public Position<E> first() throws EmptyListException{
		if (size==0)
			throw new EmptyListException("La lista est� vac�a.");
		return header.getSiguiente();
	}	
	@Override
	public Position<E> last() throws EmptyListException{
		if (size==0)
			throw new EmptyListException("La lista est� vac�a.");
		return trailer.getAnterior();
	}
	@Override
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException{
		DNode<E> posicion = checkPosition(p);
		DNode<E> next = posicion.getSiguiente();
		if (next==trailer)
			throw new BoundaryViolationException("Se exceden los l�mites de la lista.");
		return next;
	}
	@Override
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException{
		DNode<E> posicion = checkPosition(p);
		DNode<E> prev = posicion.getAnterior();
		if (prev==header)
			throw new BoundaryViolationException("Se exceden los l�mites de la lista.");
		return prev;
	}
	@Override
	public void addFirst(E element) {
		DNode<E> elemento = new DNode<E>(header, element, header.getSiguiente());
		header.getSiguiente().setAnterior(elemento);
		header.setSiguiente(elemento);
		size++;
	}
	@Override
	public void addLast(E element) {
		DNode<E> elemento = new DNode<E>(trailer.getAnterior(), element, trailer);
		trailer.getAnterior().setSiguiente(elemento);
		trailer.setAnterior(elemento);
		size++;
	}
	@Override
	public void addAfter(Position<E> p, E element) throws InvalidPositionException{
			DNode<E> elem1= checkPosition(p);
			DNode<E> elem2= new DNode<E>(elem1, element, elem1.getSiguiente());
			elem1.getSiguiente().setAnterior(elem2);
			elem1.setSiguiente(elem2);
			size++;
	}
	@Override
	public void addBefore(Position<E> p, E element) throws InvalidPositionException{
			DNode<E> elem1= checkPosition(p);
			DNode<E> elem2= new DNode<E>(elem1.getAnterior(), element, elem1);
			elem1.getAnterior().setSiguiente(elem2);
			elem1.setAnterior(elem2);
			size++;		
	}
	@Override
	public E remove(Position<E> p) throws InvalidPositionException{
		DNode<E> nodoP = checkPosition(p);
		DNode<E> elemPrev = nodoP.getAnterior();
		DNode<E> elemNext = nodoP.getSiguiente();
		elemPrev.setSiguiente(elemNext);
		elemNext.setAnterior(elemPrev);
		E elem = nodoP.element();
		nodoP.setAnterior(null);
		nodoP.setSiguiente(null);
		nodoP.setElemento(null);
		size--;
		return elem;		
	}
	@Override
	public E set(Position<E> p, E element) throws InvalidPositionException{
		DNode<E> nodoP = checkPosition(p);
		E elemAnterior = nodoP.element();
		nodoP.setElemento(element);
		return elemAnterior;
	}
	@Override
	public Iterator<E> iterator(){
		return new ElementIterator<E>(this);
	}
	@Override
	public Iterable<Position<E>> positions(){
		ListaDoblementeEnlazada<Position <E>> list = new ListaDoblementeEnlazada<Position <E>>();		
		DNode<E> nodoP = header.getSiguiente();
		while(nodoP != trailer){
			list.addLast(nodoP);
			nodoP = nodoP.getSiguiente();
		}
		return list;
	}
	/**
	 * Chequea si la posici�n es v�lida y la convierte en un DNodo.
	 * @param Position<E> Tipo de dato a chequear.
	 * @return DNodo si la posici�n es v�lida.
	 */
	private DNode<E> checkPosition(Position<E> p) throws InvalidPositionException{
        try {
            if (p == null)
                throw new InvalidPositionException("La posici�n es inv�lida");
            if (this.isEmpty())
                throw new InvalidPositionException("La lista est� vac�a.");
            if (p.element()==null)
            	throw new InvalidPositionException("La posici�n fue eliminada.");
        }
        catch(ClassCastException e) {
            throw new InvalidPositionException("La posici�n es inv�lida."); 
        }
        return ((DNode<E>)p);
    }
	public String toString() {
	    String ret=new String(" ");
	    DNode<E>h= header.getSiguiente();
	    while(h.getSiguiente()!=null) {
	        ret +=h.element()+" ";
	        h=h.getSiguiente();
	    }
	    return ret;
	}
}