package TDAArbolBinario;

import java.util.Iterator;

import TDALista.DoubleLinkedList;

public class LinkedBinaryTree<E> implements BinaryTree<E> {
	protected int size;
	protected BTNodo<E> root;
	
	public LinkedBinaryTree() {
		size = 0;
		root = null;
	}
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return root == null;
	}

	@Override
	public Iterator<E> iterator() {
		DoubleLinkedList<E> iterator = new DoubleLinkedList<E>();
		if(size != 0) {
			iterator.addFirst(root.element());
			iteratorAux(root.getLeft(),iterator);
			iteratorAux(root.getRight(),iterator);
		}
		return iterator.iterator();
	}

	private void iteratorAux(BTNodo<E> p, DoubleLinkedList<E> list) {
		if(p!=null) {
			list.addLast(p.element());
			iteratorAux(p.getLeft(),list);
			iteratorAux(p.getRight(),list);
		}
		
	}
	@Override
	public Iterable<Position<E>> positions() {
		DoubleLinkedList<Position<E>> iterable = new DoubleLinkedList<Position<E>>();
		if(root!=null) {
			iterable.addFirst(root);
			positionAux(root.getLeft(),iterable);
			positionAux(root.getRight(),iterable);
		}
		return iterable;
	}

	private void positionAux(BTNodo<E> p, DoubleLinkedList<Position<E>> list) {
		if(p!=null) {
			list.addLast(p);
			positionAux(p.getLeft(),list);
			positionAux(p.getRight(),list);
		}
		
	}
	@Override
	public E replace(Position<E> v, E e) throws InvalidPositionException {
		BTNodo<E> n = checkPosition(v);
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
		BTNodo<E> n = checkPosition(v);
		if(n == root)
			throw new BoundaryViolationException ("La raiz no tiene padre");
		return n.getParent();
	}

	@Override
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException {
		BTNodo<E> n = checkPosition(v);
		DoubleLinkedList<Position<E>> iterable = new DoubleLinkedList<Position<E>>();
		if(n.getLeft()!=null)
			iterable.addFirst(n.getLeft());
		if(n.getRight()!=null)
			iterable.addLast(n.getRight());
		return iterable;
	}

	@Override
	public boolean isInternal(Position<E> v) throws InvalidPositionException {
		BTNodo<E> n = checkPosition(v);
		return n.getLeft()!=null ||n.getRight()!=null;
	}

	@Override
	public boolean isExternal(Position<E> v) throws InvalidPositionException {
		BTNodo<E> n = checkPosition(v);
		return n.getLeft()==null && n.getRight()==null;
	}

	@Override
	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		BTNodo<E> n = checkPosition(v);
		return root == n;
	}

	@Override
	public Position<E> left(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		BTNodo<E> n = checkPosition(v);
		if(n.getLeft()==null)
			throw new BoundaryViolationException("no tiene nodo izq");
		return n.getLeft();

	}

	@Override
	public Position<E> right(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		BTNodo<E> n = checkPosition(v);
		if(n.getRight()==null)
			throw new BoundaryViolationException("no tiene nodo izq");
		return n.getRight();
	}

	@Override
	public boolean hasLeft(Position<E> v) throws InvalidPositionException {
		BTNodo<E> n = checkPosition(v);
		return n.getLeft()!=null;
	}

	@Override
	public boolean hasRight(Position<E> v) throws InvalidPositionException {
		BTNodo<E> n = checkPosition(v);
		return n.getRight()!=null;
	}

	@Override
	public Position<E> createRoot(E r) throws InvalidOperationException {
		if(root!=null)
			throw new InvalidOperationException("El arbol ya tiene raiz");
		root = new BTNodo<E>(r,null,null,null);
		size++;
		return root;
	}

	@Override
	public Position<E> addLeft(Position<E> v, E r) throws InvalidOperationException, InvalidPositionException {
		BTNodo<E> n = checkPosition(v);
		BTNodo<E> newLeft = new BTNodo<E>(r,n,null,null); 
		if(n.getLeft()!=null)
			throw new InvalidOperationException("El nodo ya tiene izq");
		n.setLeft(newLeft);
		size++;
		return newLeft;
	}

	@Override
	public Position<E> addRight(Position<E> v, E r) throws InvalidOperationException, InvalidPositionException {
		BTNodo<E> n = checkPosition(v);
		BTNodo<E> newRight = new BTNodo<E>(r,n,null,null); 
		if(n.getRight()!=null)
			throw new InvalidOperationException("El nodo ya tiene der");
		n.setRight(newRight);
		size++;
		return newRight;
	}


	@Override
	public E remove(Position<E> v) throws InvalidOperationException, InvalidPositionException {
		BTNodo<E> replaced;
		BTNodo<E> n = checkPosition(v);
		BTNodo<E> parent = n.getParent(); 
		E toReturn;
		if(n.getLeft()!=null && n.getRight()!=null) 
			throw new InvalidOperationException("la posicion tiene 2 hijos");
		toReturn = n.element();
		replaced = n;
		n = n.getLeft()!=null?n.getLeft():n.getRight();
		if(n!=null) 
			n.setParent(parent);
		if(replaced!=root)
			if(parent.getLeft() == replaced)
				parent.setLeft(n);
			else
				parent.setRight(n);	
		replaced.setElement(null);
		replaced.setParent(null);
		replaced.setRight(null);
		replaced.setLeft(null);
		replaced = null;
		size--;
		return toReturn;
	}

	@Override
	public void attach(Position<E> v, BinaryTree<E> t1, BinaryTree<E> t2) throws InvalidPositionException {
		BTNodo<E> n = checkPosition(v);
		if(n.getLeft()!=null||n.getRight()!=null)
			throw new InvalidPositionException("La posicion ya tiene hijos");
		try {
			if(!t1.isEmpty()) 
				cloneTreeLeft(n,t1.root(),t1);
			if(!t2.isEmpty()) 
				cloneTreeRight(n,t2.root(),t2);
		} catch (EmptyTreeException e) {
			e.printStackTrace();
		}
	}

	private void cloneTreeRight(BTNodo<E> n, Position<E> toClone, BinaryTree<E> t) {
		try {
			addRight(n, toClone.element());
			if(t.hasLeft(toClone))
				cloneTreeLeft(n.getRight(),t.left(toClone),t);
			if(t.hasRight(toClone))
				cloneTreeRight(n.getRight(),t.right(toClone),t);
		} catch (InvalidOperationException | InvalidPositionException | BoundaryViolationException e) {
			e.printStackTrace();
		}
		
	}
	private void cloneTreeLeft(BTNodo<E> n, Position<E> toClone, BinaryTree<E> t) {
		try {
			addLeft(n, toClone.element());
			if(t.hasLeft(toClone))
				cloneTreeLeft(n.getLeft(),t.left(toClone),t);
			if(t.hasRight(toClone))
				cloneTreeRight(n.getLeft(),t.right(toClone),t);
		} catch (InvalidOperationException | InvalidPositionException | BoundaryViolationException e) {
			e.printStackTrace();
		}
		
	}
	private BTNodo<E> checkPosition(Position<E> v) throws InvalidPositionException {
		BTNodo<E> n =null;
		if(v == null || v.element() == null || root==null)
			throw new InvalidPositionException("la posicion no es valida");
		try {
			n = (BTNodo<E>) v;
		}
		catch(ClassCastException e) {
			e.printStackTrace();
		}
		return n;
	}
	

}
