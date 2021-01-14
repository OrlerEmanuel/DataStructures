package TDAArbol;

import TDALista.DoubleLinkedList;
import TDALista.PositionList;

public class TNodo<E> implements Position<E>{
	protected E element;
	protected TNodo<E> parent;
	protected PositionList<TNodo<E>> childs;
	
	public TNodo(E element, TNodo<E> parent) {
		this.element = element;
		this.parent = parent;
		childs = new DoubleLinkedList<TNodo<E>>();
	}
	
	@Override
	public E element() {
		return element;
	}

	public TNodo<E> getParent() {
		return parent;
	}
	
	public PositionList<TNodo<E>> getChilds(){
		return childs;
	}
	
	public void setElement(E element) {
		this.element = element;
	}
	
	public void setParent(TNodo<E> parent) {
		this.parent = parent;
	}
}
