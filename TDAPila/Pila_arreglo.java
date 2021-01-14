package TDAPila;

public class Pila_arreglo <E> implements Stack <E> {
	protected E[] array;
	protected int top;
	

	@SuppressWarnings("unchecked")
	public Pila_arreglo() {
		array = (E[]) new Object [1];
		top = 0;
	}
	
	@SuppressWarnings("unchecked")
	private void expand() {
		E[] aux = (E[]) new Object [array.length*2];
		for(int i=0;i<=top;i++)
			aux[i]=array[i];
		array = aux;
	}
	
	public int size() {
		return top;
	}
	
	public boolean isEmpty() {
		return top == 0;
	}

	public E top()throws EmptyStackException{
		if(top==0) 
			throw new EmptyStackException("La pila esta vacia");
		return array[top-1];
		
	}
	
	public void push(E element) {
		if(top+1==array.length) 
			expand();	
		array[top++] = element;
	}
	
	public E pop() throws EmptyStackException{
		if(top==0)
			throw new EmptyStackException("La pila esta vacia");
		E top_element = array[top-1];
		array[--top] = null;
		return top_element;
	}
	
	public void invertir() {
		invertirAux(0,top-1);
	}
	private void invertirAux(int first,int last) {
		if(last>first) {
			E aux = array[first];
			array[first] = array[last];
			array[last] =  aux;
			invertirAux(first+1,last-1);
		}
	}
	
	

}
