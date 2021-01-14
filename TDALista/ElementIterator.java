package TDALista;

import java.util.Iterator;
/**
 * Implementa un iterador.
 * @author Emanuel Orler
 * @param <E>: tipo de dato abstracto a utilizar.
 */
public class ElementIterator<E> implements Iterator<E> {
	protected PositionList<E> list;
	protected Position<E> pos;
	/**
	 * Inicializa los valores del iterador.
	 * @param l: lista que se quiere iterar.
	 */
	public ElementIterator(PositionList<E> l) {
		list = l;
		if(l.isEmpty())
			pos = null;
		else
			try {
				pos = list.first();
			} catch (EmptyListException e) {
				e.printStackTrace();
			}
	}
	/**
	 * Verifica si existe un siguiente elemento
	 * @return true en caso de que exista otro elemento a iterar, false en caso contrario.
	 */
	public boolean hasNext() {
		return !(pos == null);
	}
	/**
	 * Obtiene el siguiente elemento en la iteracion.
	 * @return el siguiente elemento a iterar.
	 */
	public E next() {
		E toReturn = pos.element();
		try {
			pos = (pos == list.last()) ? null : list.next(pos);
		} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {
			e.printStackTrace();
		}
		return toReturn;
	}
	public void remove() {}
}
