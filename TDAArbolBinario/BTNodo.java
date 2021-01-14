package TDAArbolBinario;

public class BTNodo<E> implements Position<E> {
	
	protected E element;
	protected BTNodo<E> parent,left,right;

	public BTNodo(E element, BTNodo<E> parent, BTNodo<E> left, BTNodo<E> right) {
		this.element = element; 
		this.parent = parent;
		this.left = left;
		this.right = right;
	}

	@Override
	public E element() {
		return element;
	}

	public void setElement(E element) {
		this.element = element;
	}

	public BTNodo<E> getParent() {
		return parent;
	}

	public void setParent(BTNodo<E> parent) {
		this.parent = parent;
	}

	public BTNodo<E> getLeft() {
		return left;
	}

	public void setLeft(BTNodo<E> left) {
		this.left = left;
	}

	public BTNodo<E> getRight() {
		return right;
	}

	public void setRight(BTNodo<E> right) {
		this.right = right;
	}

}
