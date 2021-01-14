package TDAArbol;

import java.util.Iterator;

import TDALista.DoubleLinkedList;
import TDALista.EmptyListException;
import TDALista.PositionList;
/**
 * Modela un arbol a traves de un nodos enlazados.
 * @author Emanuel Orler
 * @param <E>: Tipo de dato asbtracto a utilizar.
 */
public class Arbol_enlazado<E> implements Tree<E>{
		protected TNodo<E> root;
		protected int size;
		/**
		 * Inicialia un arbol vacio.
		 */
		public Arbol_enlazado() {
			root = null;
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
		public Iterator<E> iterator() {
			PositionList<E> iterable = new DoubleLinkedList<E>();
			for(Position<E> p:positions())
				iterable.addLast(p.element());
			return iterable.iterator();
		}

		@Override
		public Iterable<Position<E>> positions() {
			PositionList<Position<E>> iterable = new DoubleLinkedList<Position<E>>();
			if(root != null)
				iteratorAux(root,iterable);
			return iterable;
		}
		/**
		 * Recorre el arbol agregando sus nodos a un lista iterable.
		 * @param current_node: Nodo actual a agregar.
		 * @param iterable: Lista iterable de posiciones.
		 */
		private void iteratorAux(TNodo<E> current_node,PositionList<Position<E>> iterable) {
			iterable.addLast(current_node);
			for(TNodo<E> n: current_node.getChilds())
				iteratorAux(n,iterable);
		}
		@Override
		public E replace(Position<E> v, E e) throws InvalidPositionException {
			TNodo<E> n = checkPosition(v);
			E toReturn = n.element();
			n.setElement(e);
			return toReturn;
		}

		@Override
		public Position<E> root() throws EmptyTreeException {
			if(root == null)
				throw new EmptyTreeException("El arbol esta vacio");
			return root;
		}

		@Override
		public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
			TNodo<E> n = checkPosition(v);
			if(n == root)
				throw new BoundaryViolationException("Se exceden los limetes del arbol");
			return n.getParent();
		}

		@Override
		public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException {
			TNodo<E> n = checkPosition(v);
			PositionList<Position<E>> iterable = new DoubleLinkedList<Position<E>>();
			for(TDALista.Position<TNodo<E>> p:n.getChilds().positions())
				iterable.addLast(p.element());
			return iterable;
		}

		@Override
		public boolean isInternal(Position<E> v) throws InvalidPositionException {
			TNodo<E> n = checkPosition(v);
			return !n.getChilds().isEmpty();
		}

		@Override
		public boolean isExternal(Position<E> v) throws InvalidPositionException {
			TNodo<E> n = checkPosition(v);
			return n.getChilds().isEmpty();
		}

		@Override
		public boolean isRoot(Position<E> v) throws InvalidPositionException {
			TNodo<E> n = checkPosition(v);
			return n == root;
		}

		@Override
		public void createRoot(E e) throws InvalidOperationException {
			if(root != null)
				throw new InvalidOperationException("El arbol no esta vacio.");
			root = new TNodo<E>(e,null);	
			size++;
		}

		@Override
		public Position<E> addFirstChild(Position<E> p, E e) throws InvalidPositionException {
			TNodo<E> n = checkPosition(p);
			TNodo<E> c = new TNodo<E>(e,n);
			n.getChilds().addFirst(c);
			size++;
			return c;
		}

		@Override
		public Position<E> addLastChild(Position<E> p, E e) throws InvalidPositionException {
			TNodo<E> n = checkPosition(p);
			TNodo<E> c = new TNodo<E>(e,n);
			n.getChilds().addLast(c);
			size++;
			return c;
		}
		@Override
		public Position<E> addBefore(Position<E> p, Position<E> rb, E e) throws InvalidPositionException {
			TNodo<E> parent = checkPosition(p);
			TNodo<E> toSearch = checkPosition(rb);
			TNodo<E> newNodo = new TNodo<E>(e,parent);
			PositionList<TNodo<E>> childs = parent.getChilds();
			TDALista.Position<TNodo<E>> place;
			if(toSearch.getParent()!=parent)
				throw new InvalidPositionException("El nodo del segundo argumento no es hijo del nodo del primer argumento");
			try {
				place = searchFor(toSearch,childs);
				if(place != null) {
					childs.addBefore(place, newNodo);
					newNodo.setParent(parent);
				}
				else
					throw new InvalidPositionException("La posicion es invalida");			
			}
			catch(TDALista.InvalidPositionException ex) {
				ex.printStackTrace();
			}
			size++;
			return newNodo;
		}

		@Override
		public Position<E> addAfter(Position<E> p, Position<E> lb, E e) throws InvalidPositionException {
			TNodo<E> parent = checkPosition(p);
			TNodo<E> toSearch = checkPosition(lb);
			TNodo<E> newNodo = new TNodo<E>(e,parent);
			TDALista.Position<TNodo<E>> place;
			PositionList<TNodo<E>> childs = parent.getChilds();
			if(toSearch.getParent()!=parent)
				throw new InvalidPositionException("El nodo del segundo argumento no es hijo del nodo del primer argumento");
			try {	
				place = searchFor(toSearch,childs);
				if(place != null) {
					childs.addAfter(place, newNodo);
					newNodo.setParent(parent);
				}
				else
					throw new InvalidPositionException("La posicion es invalida");
			}
			catch(TDALista.InvalidPositionException ex) {
				ex.printStackTrace();
			}
			size++;
			return newNodo;
		}
		/**
		 * Busca una nodo dentro de una lista de nodos y retorna su posicion en la lista.
		 * @param toSearch: Nodo a buscar dentro de la lista.
		 * @param list: Lista en la cual se va a buscar.
		 * @return La posicion de la lista en la que se encuentra el nodo, o null en caso de que no sea encontrado.
		 */
		private TDALista.Position<TNodo<E>> searchFor(TNodo<E> toSearch, PositionList<TNodo<E>> list) {
			TDALista.Position<TNodo<E>> current = null;
			TDALista.Position<TNodo<E>> last;
			try {
				current = list.first();
				last = list.last();
				while(current.element() != null && current.element() != toSearch) 
					current = current==last?null:list.next(current);
			} 
			catch (TDALista.InvalidPositionException | TDALista.BoundaryViolationException | EmptyListException e) {
				e.printStackTrace();
			}
			return current;
		}
		@Override
		public void removeExternalNode(Position<E> p) throws InvalidPositionException {
			TNodo<E> toRemove = checkPosition(p);
			if(!toRemove.getChilds().isEmpty())
				throw new InvalidPositionException("El nodo no es externo");
			removeNode(toRemove);
		}

		@Override
		public void removeInternalNode(Position<E> p) throws InvalidPositionException {
			TNodo<E> toRemove = checkPosition(p);
			if(toRemove.getChilds().isEmpty())
				throw new InvalidPositionException("El nodo no es interno");
			removeNode(toRemove);
		}

		@Override
		public void removeNode(Position<E> p) throws InvalidPositionException {
			TNodo<E> toRemove = checkPosition(p);
			TNodo<E> parent = toRemove.getParent();
			TNodo<E> child;
			PositionList<TNodo<E>> brothers;
			PositionList<TNodo<E>> childs = toRemove.getChilds();
			TDALista.Position<TNodo<E>> listPlace;
			try {
				if(toRemove == root)
					if(childs.size() == 0) 
						root = null;		
					else 
						if(childs.size() == 1) {
							child = childs.remove(childs.first());
							child.setParent(null);
							root = child;
						}
						else 
							throw new InvalidPositionException("No es posible eliminar la raiz si la cantidad de hijos es mayor a uno.");
				else {
					brothers = parent.getChilds();
					listPlace = brothers.isEmpty()?null:brothers.first();
					while(listPlace != null && listPlace.element() != toRemove)
						listPlace = listPlace==brothers.last()?null:brothers.next(listPlace);
					if(listPlace == null)
						throw new InvalidPositionException("La posicion no pertenece a el arbol");
					while(!childs.isEmpty()) {
						child = childs.remove(childs.first());
						child.setParent(parent);
						brothers.addBefore(listPlace, child);					
					}
					brothers.remove(listPlace);
				}
				toRemove.setParent(null);
				toRemove.setElement(null);
				size--;
			}
			catch(TDALista.InvalidPositionException | EmptyListException | TDALista.BoundaryViolationException e) {
				e.printStackTrace();
			}
		}
		/**
		 * Corrobora que una posicion pertenezca al arbol.
		 * @param v: Posicion a verificar.
		 * @return n, donde n es el nodo que corresponde a la posicion v.
		 * @throws InvalidPositionException en caso de que la posicion sea nula, o el arbol este vacio.
		 */
		private TNodo<E> checkPosition(Position<E> v) throws InvalidPositionException{
			TNodo<E> toReturn = null;
			if(v == null || v.element() == null || root == null)
				throw new InvalidPositionException("La posicion es invalida");
			try {
				toReturn = (TNodo<E>) v;
			}
			catch(ClassCastException e) {
				e.printStackTrace();
			}
			return toReturn;
		}
}