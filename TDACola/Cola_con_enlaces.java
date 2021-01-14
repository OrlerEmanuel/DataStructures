package TDACola;

public class Cola_con_enlaces<E>  implements Queue<E>	{
	protected Nodo<E> head,tail;
	protected int size;
	
	public Cola_con_enlaces() {
		head = null;
		tail = null;
		size = 0;
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public E front() throws EmptyQueueException{
		if(size == 0) 
			throw new EmptyQueueException("La cola esta vacia");
		return head.getElemento();
	}
	
	public void enqueue(E element) {
		Nodo<E> new_tail = new Nodo<E>(element, null);
		if(size == 0) 
			head = new_tail;
		else {
			tail.setSiguiente(new_tail);
		}
		tail = new_tail;
		size++;
	}
	public E dequeue() throws EmptyQueueException{
		if(size == 0)
			throw new EmptyQueueException("La cola esta vaica");
		E temp = head.getElemento();
		head = head.getSiguiente();
		size--;
		if(size==0)
			tail = null;
		return temp;
		
	}
	
	public void invertir() throws EmptyQueueException { 
		invertirAux(size);
	}
	private void invertirAux(int s) throws EmptyQueueException {
		if(s>1) {
			E item = dequeue();
			invertirAux(s-1);
			enqueue(item);
		}
	}
}
