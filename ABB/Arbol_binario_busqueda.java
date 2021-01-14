package ABB;

import java.util.Comparator;

public class Arbol_binario_busqueda<E extends Comparable<E>> {
	protected ABBNodo<E> root;
	protected Comparator<E> comp;
	
	public Arbol_binario_busqueda() {
		root = new ABBNodo<E>(null,null,null,null);
		comp = new Default_comparator<E>();
	}
	
	public ABBNodo<E> raiz(){
		return root;
	}
	
	public ABBNodo<E> buscar(E e){
		return buscarAux(root,e);
	}

	private ABBNodo<E> buscarAux(ABBNodo<E> nodo, E e) {
		ABBNodo<E> toReturn = null;
		int comparation;
		if(e!=null) {
			if(nodo.element()==null)
				toReturn = nodo;
			else {
				comparation = comp.compare(e, nodo.element());
				if(comparation != 0)
					if(comparation<0)
						toReturn = buscarAux(nodo.getLeft(),e);
					else
						toReturn = buscarAux(nodo.getRight(),e);
				else
					toReturn = nodo;
				}
		}
		return toReturn;
	}
	
	public void expandir(ABBNodo<E> n, E e) {
		if(n!=null)
			if(n.element()==null) {
				n.setElement(e);
				n.setLeft(new ABBNodo<E>(null,n,null,null));
				n.setRight(new ABBNodo<E>(null,n,null,null));
			}
			else
				if(comp.compare(e, n.element())>0)
					expandir(n.getRight(),e);
				else
					if(comp.compare(e, n.element())<0)
						expandir(n.getLeft(),e);
			
	}
	
	public void eliminar(E e) {
		 ABBNodo<E> toRemove = buscar(e);
		 if(toRemove != null) {	
			 if(toRemove.getLeft().element()!=null && toRemove.getRight().element()==null) {
				 toRemove.getLeft().setParent(toRemove.getParent());
				 if(toRemove!=root) 
					 if(toRemove.getParent().getLeft()==toRemove)
					 	toRemove.getParent().setLeft(toRemove.getLeft());
				 	else
				 		toRemove.getParent().setRight(toRemove.getLeft());
				 else
					 root = toRemove.getLeft();
				toRemove.setElement(null);
				toRemove.setParent(null);
				toRemove.setRight(null);
				toRemove.setLeft(null);
				toRemove = null;
			 }
			 else
				 if(toRemove.getLeft().element()==null && toRemove.getRight().element()!=null) {
					 toRemove.getRight().setParent(toRemove.getParent());
					 if(toRemove!=root)
						 if(toRemove.getParent().getLeft()==toRemove)
						 	toRemove.getParent().setLeft(toRemove.getRight());
					 	 else
					 		toRemove.getParent().setRight(toRemove.getRight());
					 else
						 root = toRemove.getRight();
					toRemove.setElement(null);
					toRemove.setParent(null);
					toRemove.setRight(null);
					toRemove.setLeft(null);
					toRemove = null;
				 }
				 else {
					 if(toRemove.getLeft().element()==null && toRemove.getRight().element()==null) {
						toRemove.setElement(null);
						toRemove.setRight(null);
						toRemove.setLeft(null);
					 }
					 else 
						 toRemove.setElement(eliminarMinimo(toRemove.getRight()));
				 }  
		 }
	}
	
	private E eliminarMinimo(ABBNodo<E> node) {
		E toReturn;
		if(node.getLeft().element() == null) {
			toReturn = node.element();
			if(node.getRight().element() == null ) {
				node.setElement(null);
				node.setRight(null);
				node.setLeft(null);
				}
			else {
				node.getParent().setRight(node.getRight());
				node.getRight().setParent(node.getParent());
				node.setElement(null);
				node.setParent(null);
				node.setRight(null);
				node.setLeft(null);
				node = null;
			}
		}
		else
			toReturn = eliminarMinimo(node.getLeft());
		return toReturn;
	}
	
}
