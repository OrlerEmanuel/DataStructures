package ABB;

public class ABBNodo<E> implements Position<E> {
	
	protected E element;
	protected ABBNodo<E> parent,left,right;

	public ABBNodo(E element, ABBNodo<E> parent, ABBNodo<E> left, ABBNodo<E> right) {
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

	public ABBNodo<E> getParent() {
		return parent;
	}

	public void setParent(ABBNodo<E> parent) {
		this.parent = parent;
	}

	public ABBNodo<E> getLeft() {
		return left;
	}

	public void setLeft(ABBNodo<E> left) {
		this.left = left;
	}

	public ABBNodo<E> getRight() {
		return right;
	}

	public void setRight(ABBNodo<E> right) {
		this.right = right;
	}

}
