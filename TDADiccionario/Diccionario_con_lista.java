  package TDADiccionario;

import TDALista.BoundaryViolationException;
import TDALista.EmptyListException;
import TDALista.InvalidPositionException;
import TDALista.DoubleLinkedList;
import TDALista.Position;

public class Diccionario_con_lista<K,V>  implements Dictionary<K,V> {
	protected DoubleLinkedList<Entry<K,V>> list;
	/**
	 * Crea un diccionario con lista.
	 */
	public Diccionario_con_lista() {
		list = new DoubleLinkedList<Entry<K,V>>();
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
	public Entry<K, V> find(K key) throws InvalidKeyException {
		Entry<K,V> toReturn = null;
		Position<Entry<K,V>> current, last;
		boolean found;
		if(key == null)
			throw new InvalidKeyException("La clave es nula");
		try {
			if(!list.isEmpty()) {
				current = list.first();
				last = list.last();
				found = current.element().getKey().equals(key);
				while(!found && current != last) {
					current = list.next(current);
					found = current.element().getKey().equals(key);
				}
				toReturn = (found)?current.element():null;
			}
		}
		catch(EmptyListException | InvalidPositionException | BoundaryViolationException e) {
			e.printStackTrace();
		}
		return toReturn;
	}

	@Override
	public Iterable<Entry<K, V>> findAll(K key) throws InvalidKeyException {
		DoubleLinkedList<Entry<K,V>> iterable = new DoubleLinkedList<Entry<K,V>>();
		if(key == null)
			throw new InvalidKeyException("La clave es nula");
		for(Position<Entry<K,V>> p:list.positions())
			if(p.element().getKey().equals(key))
				iterable.addLast(p.element());
		return iterable;
	}

	@Override
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		if(key == null)
			throw new InvalidKeyException("La clave es nula");
		Entry<K,V> new_entry = new Entrada<K,V>(key,value);
		list.addLast(new_entry);
		return new_entry;
	}

	@Override
	public Entry<K, V> remove(Entry<K, V> e) throws InvalidEntryException {
		Entry<K, V> toReturn = null;
		Position<Entry<K, V>> current, last;
		boolean found = false;
		if(e == null)
			throw new InvalidEntryException("La entrada es nula");
		try {
			if(!list.isEmpty()) {
				current = list.first();
				last = list.last();
				found = current.element() == e;
				while(!found && current != last) {
					current = list.next(current);
					found = current.element() == e;
				}
				if(found) {
					toReturn = current.element();
					list.remove(current);
				}
			}
			System.out.print(found);
			if(!found)
				throw new InvalidEntryException("La entrada no pertenece al diccionario");
		}
		catch(EmptyListException | InvalidPositionException | BoundaryViolationException ex) {
			ex.printStackTrace();
		}
		return toReturn;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		DoubleLinkedList<Entry<K,V>> iterable = new DoubleLinkedList<Entry<K,V>>();
		for(Position<Entry<K,V>> p: list.positions())
			iterable.addLast(p.element());
		return iterable;
	}
}
