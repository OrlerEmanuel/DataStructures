package TDALista;

import java.util.Iterator;

public class Lista_simple_enlazada<E> implements PositionList<E>{
		protected Nodo<E> first;
		
		public Lista_simple_enlazada(E element){
			first = new Nodo<E>(element);
		}
		
		public Lista_simple_enlazada() {
			first = null;
		}
		
		private Lista_simple_enlazada(Nodo<E> clonado) {
			first = clonado;
		}
		
		public int size() {
			int cant=0;
			Nodo<E> current = first;
			while(current!= null) {
				cant++;
				current = current.getSiguiente();
			}
			return cant;
		}
		
		public boolean isEmpty() {
			return first == null;
		}
		
		public Position<E> first() throws EmptyListException  {
			if(isEmpty()) {
				throw new EmptyListException("La lista esta vacia");
			}
			return first;
		}
		
		public Position<E> last() throws EmptyListException {
			Nodo<E> last = first;
			if(isEmpty())
				throw new EmptyListException("La lista esta vacia");
			else 				
				while(last.getSiguiente()!=null) 
					last = last.getSiguiente();	
			return last;
		}
		
		public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
			Nodo<E> n = checkPosition(p);
			if(n.getSiguiente()==null)
				throw new BoundaryViolationException("Esta siendo excedido el maximo de la lista");
			return n.getSiguiente();
		}
		
		public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
			Nodo<E> n = checkPosition(p);
			if(n == first) 
				throw new BoundaryViolationException("Esta siendo excedido el minimo de la lista");
			Nodo<E> aux = first;
			while(aux.getSiguiente() != n)
				aux = aux.getSiguiente();
			if(aux.getSiguiente() == null)
				throw new InvalidPositionException("La posicion no pertenece a la lista");
			return aux;
		}
		
		public void addFirst(E element) {
			if(isEmpty())
				first = new Nodo<E>(element);
			else
				first = new Nodo<E>(element, first);
		}
		
		public void addLast(E element) {
			try {
				if(!isEmpty())
					addAfter(last(), element);
				else
					addFirst(element);
			} catch (InvalidPositionException | EmptyListException e) {
				e.printStackTrace();
			}
		}
		
		public void addAfter(Position<E> p, E element) throws InvalidPositionException {
			Nodo<E> n = checkPosition(p);
			Nodo<E> new_position = new Nodo<E>(element);
			new_position.setSiguiente(n.getSiguiente());
			n.setSiguiente(new_position);
			
		}
		
		public void addBefore(Position<E> p, E element) throws InvalidPositionException {
			checkPosition(p);
			try {
				if(p == first()) 
					addFirst(element);
				else 
					addAfter(prev(p),element);	
			}
			catch(InvalidPositionException | EmptyListException | BoundaryViolationException e) {
				e.printStackTrace();
			}	
		}
		
		public E remove(Position<E> p) throws InvalidPositionException {
			Nodo<E> n = checkPosition(p);
				if(n == first)
					first = first.getSiguiente();
				else
					try {
						checkPosition(prev(p)).setSiguiente(n.getSiguiente());
					} catch (InvalidPositionException | BoundaryViolationException e) {
						e.printStackTrace();
					}
			E temp = n.element();
			n.setElemento(null);
			n.setSiguiente(null);
			return temp;
		}
		
		public E set(Position<E> p, E element) throws InvalidPositionException {
			Nodo<E> n = checkPosition(p);
			E temp = n.element();
			n.setElemento(element);
			return temp;
		}
		
		public Iterator<E> iterator(){
			return new ElementIterator<E>( this );
			}
		
		public Iterable<Position<E>> positions() {
			PositionList<Position<E>> iterable = new Lista_simple_enlazada<Position<E>>();
			if(!isEmpty()) {
				iterable.addFirst(first);
				Nodo<E> aux = first;
				while(aux.getSiguiente()!= null) {
					iterable.addLast(aux.getSiguiente());
					aux = aux.getSiguiente();
					
				}
			}
			return iterable;	
		}
		
		private Nodo<E> checkPosition(Position<E> p) throws InvalidPositionException {
			try {
				if(p == null)
					throw new InvalidPositionException("Posicion nula");
				if(p.element() == null)
					throw new InvalidPositionException("Posicion eliminada anteriormente");
				return (Nodo<E>) p;
			}
			catch(ClassCastException e) {
				throw new InvalidPositionException("La posicion es de otra lista");
			}
		}	
		
		public Lista_simple_enlazada<E> clone() {
			Nodo<E> clonado = first.clone();
			return new Lista_simple_enlazada<E>(clonado);		
		}
		
		public void eliminar_consecutivos(E e1, E e2) {
			Nodo<E> aux = first;
			while(aux!=null) {
				if(aux.element() == e1 && aux.getSiguiente()!=null && aux.getSiguiente().element() == e2) 
					try {
						remove(aux.getSiguiente());
						remove(aux);
					} catch (InvalidPositionException e) {
						e.printStackTrace();
					}
				aux = aux.getSiguiente();
			}
		}
		
}