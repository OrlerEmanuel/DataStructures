package TDACola;
@SuppressWarnings("unchecked")
/**
 * Implementa una cola a traves de un arreglo circular.
 * @author Emanuel Orler
 * @param <E>: Tipo de dato abstracto utilizado.
 */
public class Cola_con_arreglo_circular <E> implements Queue <E>{
	
	protected int header;
	protected int tail;
	protected E[] c_array;
	
	/**
	 *Inicializa los atributos de la clase
	 *@param max define la logintud de la estructura
	 */

	public Cola_con_arreglo_circular(int max) {
		header = 0;
		tail = 0;
		c_array = (E[]) new Object[max];
	}
	/**
	 *Inicializa los atributos de la clase con una estructura de tamaño minimo;
	 */
	public Cola_con_arreglo_circular() {
		this(1);
	}
	@Override
	public int size() {
		return (c_array.length-header+tail)%c_array.length;
	}
	@Override
	public boolean isEmpty() {
		return header == tail;
	}
	@Override
	public E front() throws EmptyQueueException{
		if(header == tail)
			throw new EmptyQueueException("La cola esta vacia");
		return c_array[header];
	}
	@Override
	public void enqueue(E element) {
		if(size()==c_array.length-1)
			expand();
		c_array[tail] = element;
		tail = (tail+1) % c_array.length;
	}
	@Override
	public E dequeue() throws EmptyQueueException{
		E toReturn;
		if(header == tail) 
			throw new EmptyQueueException("La cola esta vacia");
		toReturn = c_array[header]; 
		c_array[header] = null;
		header = (header+1)%c_array.length;
		return toReturn;
	}
	
	
	/**
	 *Incrementa el tamaño de la estructura el doble de su longitud
	 */
	private void expand() {
		E[] expanded = (E[]) new Object[c_array.length*2];
		if(header <= tail) 
			 //Coloca los elementos de c_array dentro de expanded en las mismas posiciones.
			for(int i = header; i<tail; i++)
				expanded[i] = c_array[i];
		else {
			 //Coloca los elementos de c_array dentro de expanded en las primeras y ultimas posiciones.	 
			for(int i = 0; i<tail; i++) 
				expanded[i] = c_array[i];
			for(int i = header; i< c_array.length; i++)
				expanded[i+c_array.length] = c_array[i];
			header = header+c_array.length;
		}
		c_array = expanded;
	}
}
