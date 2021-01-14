package Logica;
import TDACola.Cola_con_arreglo_circular;
import TDACola.EmptyQueueException;
import TDALista.BoundaryViolationException;
import TDALista.DoubleLinkedList;
import TDALista.EmptyListException;
import TDALista.InvalidPositionException;
import TDALista.PositionList;
import TDAPila.EmptyStackException;
import TDAPila.Pila_con_enlaces;
import TDALista.Position;
/**
 * Modela los metodos propuestos en el proyecto.
 * @author Emanuel Orler.
 */
public class Logica<E> {
	/**
	 * Chequea si la cadena respeta el formato propuesto.
	 * @param cad: Cadena a chequear.
	 * @return retorna true si la cadena respeta el formato false en caso contrario.
	 */ 
	public static boolean chequear_cadena(String cad) {
		int size = cad.length();
		int i = 0;
		//Las cadenas solo cumplen con el formato si son impares y tienen una longitud mínima de 3
		boolean satisfy = size % 2 == 1 && size>2;
		Cola_con_arreglo_circular<Character> segunda = new Cola_con_arreglo_circular<Character>();
		Cola_con_arreglo_circular<Character> tercera = new Cola_con_arreglo_circular<Character>();
		Pila_con_enlaces<Character> inversa = new Pila_con_enlaces<Character>();
		try {
			while(satisfy && i<size) {
				//En este bloque se corrobora que los caracteres el formato de 'C' en en A
				while(satisfy && i<size && cad.charAt(i)!='z') {
					satisfy = caracterValido(cad.charAt(i));
					segunda.enqueue(cad.charAt(i));
					tercera.enqueue(cad.charAt(i));
					inversa.push(cad.charAt(i++));
				}				
				satisfy = satisfy && i<size && cad.charAt(i++) == 'z';	
				inversa.push('z');
				//Corroboramos que A cumpla el formato
				while(satisfy && i<size && !segunda.isEmpty()) {
					inversa.push(segunda.front());
					satisfy = cad.charAt(i++) == segunda.dequeue();	
				}		
				satisfy = satisfy && segunda.isEmpty() && i<size;	
				while(satisfy && i<size && !tercera.isEmpty()) {
					inversa.push(tercera.front());
					satisfy = cad.charAt(i++) == tercera.dequeue();
				}
				satisfy = satisfy && tercera.isEmpty() && i<size && cad.charAt(i++) == 'x';	
				//Corroboramos que A'  cumpla con el formato
				while(satisfy && i<size && !inversa.isEmpty()) 
					satisfy = cad.charAt(i++) == inversa.pop();
				//Solo satisface el formato si es el final de la cadena o está parado sobre una x y hay algo que recorrer de la cadena
				satisfy = satisfy && inversa.isEmpty() && (i == size || cad.charAt(i) == 'x' && ++i<size);
			}
		}
		catch(EmptyStackException | EmptyQueueException e) {
			e.printStackTrace();
		}
		return satisfy;
	}	
	/**
	 * Verifica si el caracter es un caracter valido.
	 * @param c: caracter a definir como valido o no.
	 * @return retorna true si el caracter es valido false en caso contrario.
	 */ 
	private static boolean caracterValido(char c) {
		return c == 'a' || c == 'b';
	}	
	/**
	 * Intercala los elementos de dos listas.
	 * @param l1: Lista en orden ascendete a intercalar.
	 * @param l2: Lista en orden ascendete a intercalar.
	 * @return l3 donde l3 es una lista con los elementos de l1 y l2 intercalados en orden descendente. 
	 */
	public static PositionList<Integer> intercalar(PositionList<Integer> l1, PositionList<Integer> l2) {
		DoubleLinkedList<Integer> toReturn = new DoubleLinkedList<Integer>();
		Position<Integer> current1, current2, last1, last2;
		boolean traveled1, traveled2;
		try {
			//Si las listas no están vacias se les asigna valor a las variables útiles para recorrerlas.
			current1 = (!l1.isEmpty())?l1.first():null;
			current2 = (!l2.isEmpty())?l2.first():null;
			last1 = (!l1.isEmpty())?l1.last():null;
			last2 = (!l2.isEmpty())?l2.last():null;
			traveled1 = l1.isEmpty();
			traveled2 = l2.isEmpty();
			//En este bloque se recorren ambas listas comparando sus elementos.
			while(!traveled1 && !traveled2) {
				if(current1.element() <= current2.element()) {
					agregar(current1,toReturn);
					traveled1 = current1 == last1;
					current1 = (traveled1)?null:l1.next(current1);
				}
				else {
					agregar(current2,toReturn);
					traveled2 = current2 == last2;	
					current2 = (traveled2)?null:l2.next(current2);
				}		
			}
			//En los siguientes bloques recorremos la lista que no esté vacía en el este punto de la ejecución.
			while(!traveled1) {
				agregar(current1,toReturn);
				traveled1 = current1 == last1;
				current1 = (traveled1)?null:l1.next(current1);
			}
			while(!traveled2) {
				agregar(current2,toReturn);
				traveled2 = current2 == last2;
				current2 = (traveled2)?null:l2.next(current2);
			}
		}
		catch(EmptyListException | InvalidPositionException |BoundaryViolationException e) {
			e.printStackTrace();
		}
		return toReturn;
	}
	/**
	 * Verifica si el elemento pertenece a la lista y avanza en la posicion.
	 * @param p: posicion actual.
	 * @param original: lista original del metodo.
	 * @param toReturn : lista a retornar. 
	 */
	private static void agregar(Position<Integer> p, PositionList<Integer> toReturn) {
		try {
			if(toReturn.isEmpty() || p.element()>toReturn.first().element())
				toReturn.addFirst(p.element());
		}
		catch(EmptyListException e ) {
			e.printStackTrace();
		}
	}
	/**
	 * Crea la version zigzag de una lista.
	 * @param <E>: tipo de dato abstracto utilizado.
	 * @param l1: lista para obtener su formato zigzag.
	 * @return Retorna l2, donde l2 es la version zigzag de l1.
	 */
	public static <E> PositionList<E> zigzag (PositionList<E> l1){
		PositionList<E> toReturn = new DoubleLinkedList<E>();
		try {
			if(!l1.isEmpty())
				toReturn = zigzagAux(l1.first(),l1.last(),l1,toReturn);
		} 
		catch (EmptyListException e) {
			e.printStackTrace();
		}
		return toReturn;
	}
	/**
	 * Crea la version zigzag de la lista original.
	 * @param <E>: Tipo de dato abstracto utilizado.
	 * @param first: primer elemento de la lista
	 * @param last: ultimo elemento de la lista.
	 * @param original: lista original.
	 * @param toReturn: lista a retornar.
	 * @return l2, tal que l2 es una lista en formato zigzag con los elementos de original.
	 */
	private static <E> PositionList<E> zigzagAux(Position<E> first,Position<E> last, PositionList<E> original, PositionList<E> toReturn){
		try {
			if(first == last) 
				toReturn.addLast(first.element());
			else 
				//Si es la primera vez que se ejecuta o no de cruzan lo índices se ejecuta el caso recursivo.
				if(first == original.first() || !(original.prev(first) == last) ) {
					toReturn.addLast(first.element());
					toReturn.addLast(last.element());
					toReturn = zigzagAux(original.next(first),original.prev(last),original,toReturn);
				}
		}
		catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {
			e.printStackTrace();
		}
		return toReturn;
	}
}