package TDACola;
import TDAPila.*;
public class Cola_con_pila<E> implements Queue<E>{
	protected Pila_con_enlaces<E> pila;
	
	public Cola_con_pila(int max) {
		pila = new Pila_con_enlaces<E>();
	}
	
	public int size() {
		return pila.size();
	}
	
	public boolean isEmpty() {
		return pila.isEmpty();
	}
	
	public E front() throws EmptyQueueException {
		if(size() == 0)
			throw new EmptyQueueException("La cola esta vacia");
		Pila_con_enlaces<E> aux = new Pila_con_enlaces<E>();
		E temp = null;
		try {
		while(!pila.isEmpty()) 
			aux.push(pila.pop());
		temp = aux.top();
		while(!aux.isEmpty()) 
			pila.push(aux.pop());
		}
		catch(EmptyStackException e) {
			e.printStackTrace();
		}
		return temp;		
	}	
	
	public void enqueue(E element) {
		pila.push(element);
	}
	
	public E dequeue() throws EmptyQueueException {
		if(size() == 0)
			throw new EmptyQueueException("La cola esta vacia");
		Pila_con_enlaces<E> aux = new Pila_con_enlaces<E>();
		E temp = null;
		try {
		while(!pila.isEmpty()) 
			aux.push(pila.pop());
		temp = aux.pop();
		while(!aux.isEmpty()) 
			pila.push(aux.pop());
		}
		catch(EmptyStackException e) {
			e.printStackTrace();
		}
		return temp;
	}
}
