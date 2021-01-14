package TDAMapeo;

import TDALista.BoundaryViolationException;
import TDALista.EmptyListException;
import TDALista.InvalidPositionException;
import TDALista.DoubleLinkedList;
import TDALista.Position;
import TDALista.PositionList;

/**
 * Implementa un mapeo a traves de una lista.
 * @author Emanuel Orler
 */
public class Mapeo_con_lista <K,V> implements Map<K,V>{
	protected DoubleLinkedList<Entrada<K,V>> list;
	/**
	 * Crea una nuevo mapeo.
	 */
	public Mapeo_con_lista() {
		list = new DoubleLinkedList<Entrada<K,V>>();
	}
	@Override
	public int size() {
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public V get(K key) throws InvalidKeyException {
		V toReturn = null;
		Position<Entrada<K,V>> current, last;
		boolean found;
		if(key == null)
			throw new InvalidKeyException("La clave es nula");
		try {
			if(!list.isEmpty()) {
				current = list.first();
				last = list.last();
				found = key == current.element().getKey();
				while(!found && last != current) {
					current = list.next(current);
					found = key == current.element().getKey();
				}
				toReturn = (found)?current.element().getValue():null;
			}
		}
		catch(EmptyListException | InvalidPositionException | BoundaryViolationException e){
			e.printStackTrace();
		}
		return toReturn;
	}

	@Override
	public V put(K key, V value) throws InvalidKeyException {
		V toReturn = null;
		Position<Entrada<K,V>> current, last;
		boolean found;
		if(key == null)
			throw new InvalidKeyException("La clave es nula");
		try {
			if(list.isEmpty())
				list.addLast(new Entrada<K,V>(key,value));
			else {
				current = list.first();
				last = list.last();
				found = key == current.element().getKey();
				while(!found && last != current) {
					current = list.next(current);
					found = key == current.element().getKey();
				}
				if(found) {
					toReturn = current.element().getValue();
					current.element().setValue(value);
				}
				else
					list.addLast(new Entrada<K,V>(key,value));
			}
				
		}
		catch(EmptyListException | InvalidPositionException | BoundaryViolationException e){
			e.printStackTrace();
		}
		return toReturn;
	}

	@Override
	public V remove(K key) throws InvalidKeyException {
		V toReturn = null;
		Position<Entrada<K,V>> current, last;
		boolean found;
		if(key == null)
			throw new InvalidKeyException("La clave es nula");
		try {
			if(list.isEmpty()) {
				current = list.first();
				last = list.last();
				found = key == current.element().getKey();
				while(!found && last != current) {
					current = list.next(current);
					found = key == current.element().getKey();
				}
				if(found) {
					toReturn = current.element().getValue();
					list.remove(current);
				}
			}
		}
		catch(EmptyListException | InvalidPositionException | BoundaryViolationException e){
			e.printStackTrace();
		}
		return toReturn;
	}

	@Override
	public Iterable<K> keys() {
		PositionList<K> iterable = new DoubleLinkedList<K>();
		for(Position<Entrada<K, V>> p: list.positions()) {
			iterable.addLast(p.element().getKey());
		}
		return iterable;
	}

	@Override
	public Iterable<V> values() {
		PositionList<V> iterable = new DoubleLinkedList<V>();
		for(Position<Entrada<K, V>> p: list.positions()) {
			iterable.addLast(p.element().getValue());
		}
		return iterable;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K, V>> iterable = new DoubleLinkedList<Entry<K,V>>();
		for(Position<Entrada<K, V>> p: list.positions()) {
			iterable.addLast(p.element());
		}
		return iterable;
	}

}
