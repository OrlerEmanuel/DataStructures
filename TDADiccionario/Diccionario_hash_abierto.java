package TDADiccionario;

import TDALista.BoundaryViolationException;
import TDALista.DoubleLinkedList;
import TDALista.EmptyListException;
import TDALista.InvalidPositionException;
import TDALista.Position;
import TDALista.PositionList;

public class Diccionario_hash_abierto<K,V> implements Dictionary<K,V>{
	protected int n;
	protected int size;
	PositionList<Entrada<K,V>>[] hash;
	protected final float load_factor = 0.5f;
	
	@SuppressWarnings("unchecked")
	public Diccionario_hash_abierto() {
		n = 13;
		size = 0;
		hash = (PositionList<Entrada<K,V>>[]) new PositionList[n];
		for(int i=0;i<n;i++)
			hash[i] = new DoubleLinkedList<Entrada<K,V>>();
	}
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Entry<K, V> find(K key) throws InvalidKeyException {
		int bucket;
		boolean found;
		Entrada<K,V> toReturn = null;
		PositionList<Entrada<K,V>> list;
		Position<Entrada<K,V>> current, last;
		if(key == null)
			throw new InvalidKeyException("La clave es nula");
		try {
			bucket = key.hashCode()%n;
			list = hash[bucket];
			if(!list.isEmpty()) {
				current = hash[bucket].first();
				last = hash[bucket].last();
				found = key.equals(current.element().getKey());
				while(!found && current != last) {
					current = list.next(current);
					found = key.equals(current.element().getKey());
				}
				toReturn = found?current.element():null;	
			}
		} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {
			e.printStackTrace();
		}
		return toReturn;
	}

	@Override
	public Iterable<Entry<K, V>> findAll(K key) throws InvalidKeyException {
		int bucket;
		PositionList<Entry<K,V>> iterable = new DoubleLinkedList<Entry<K,V>>();
		PositionList<Entrada<K,V>> list;
		Position<Entrada<K,V>> current, last;
		if(key == null)
			throw new InvalidKeyException("La clave es nula");
		try {
			bucket = key.hashCode()%n;
			list = hash[bucket];
			if(!list.isEmpty()) {
				current = hash[bucket].first();
				last = hash[bucket].last();
				if(key.equals(current.element().getKey()))
					iterable.addLast(current.element());
				while(current != last) {
					current = list.next(current);
					if(key.equals(current.element().getKey()))
						iterable.addLast(current.element());
				}	
			}
		} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {
			e.printStackTrace();
		}
		return iterable;
	}

	@Override
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		Entrada<K,V> newEntry = new Entrada<K,V>(key,value);
		PositionList<Entrada<K,V>> list;
		if(key == null)
			throw new InvalidKeyException("La clave es nula");
		if(size/n>=load_factor) 
			reHash();
		list = hash[key.hashCode()%n];
		list.addLast(newEntry);
		size++;
		return newEntry;
	}

	private void reHash() {
	
		
	}
	@Override
	public Entry<K, V> remove(Entry<K, V> e) throws InvalidEntryException {
		Position<Entrada<K,V>> current, last;
		PositionList<Entrada<K,V>> list;
		Entry<K,V> toReturn = null;
		boolean found;
		if(e ==null || e.getKey() == null)
			throw new InvalidEntryException("La entrada es nula");
		try {
			list = hash[e.getKey().hashCode()%n];
			if(!list.isEmpty()) {
				current = list.first();
				last = list.last();
				found = e.getKey().equals(current.element().getKey());
				while(!found && current!=last) {
					current = list.next(current);
					found = e.getKey().equals(current.element().getKey());
				}
				if(found) {
					toReturn = new Entrada<K,V>(current.element().getKey(),current.element().getValue());
					list.remove(current);
					size--;
				}
				else
					throw new InvalidEntryException("La entrada no esta");
			}
			else
				throw new InvalidEntryException("La entrada no esta");
		} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e1) {
			e1.printStackTrace();
		}
		return toReturn;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entrada<K,V>> list;
		Position<Entrada<K,V>> current, last;
		DoubleLinkedList<Entry<K,V>> iterable = new DoubleLinkedList<Entry<K,V>>();
		try {
			for(int i = 0; i < n; i++) {
				list = hash[i];
				if(!list.isEmpty()) {
					current = list.first();
					last = list.last();
					iterable.addLast(current.element());
					while(current != last) {
						current = list.next(current);
						iterable.addLast(current.element());
					}
				}
			}
		} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {
			e.printStackTrace();
		}
		return iterable;
	}

}
