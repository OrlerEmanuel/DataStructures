package TDAColaCP;

import java.util.Comparator;

import TDALista.BoundaryViolationException;
import TDALista.DoubleLinkedList;
import TDALista.EmptyListException;
import TDALista.InvalidPositionException;
import TDALista.Position;
import TDALista.PositionList;

public class ColaCP_con_lista<K extends Comparable<K>,V> implements PriorityQueue<K,V> {
	PositionList<Entry<K,V>> list;
	Comparator<K> comparator;
	
	public ColaCP_con_lista(Comparator<K> c) {
		list = new DoubleLinkedList<Entry<K,V>>();
		comparator = c;
	}
	
	public ColaCP_con_lista() {
		list = new DoubleLinkedList<Entry<K,V>>();
		comparator = new Default_comparator<K>();
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
	public Entry<K, V> min() throws EmptyPriorityQueueException {
		Entry<K,V> toReturn = null;
		if(list.isEmpty())
			throw new EmptyPriorityQueueException("La lista esta vacia brodi");
		try {
			toReturn = list.last().element();
		} catch (EmptyListException e) {
			e.printStackTrace();
		}
		return toReturn;
	}

	@Override
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		TDALista.Position<Entry<K,V>> p;
		Entry<K,V> toReturn = new Entrada<K,V>(key,value);
		if(key == null)
			throw new InvalidKeyException("La clave es invalida mi rey");
		try {
			if(list.isEmpty())
					list.addFirst(toReturn);
			else {
				p = findPos(key);
				if(p==list.last()&&0>comparator.compare(key, p.element().getKey()))
					list.addLast(toReturn);
				else
					list.addBefore(p, toReturn);
			}
		} 
		catch (InvalidPositionException | EmptyListException e) {
			e.printStackTrace();
		}
		return toReturn;
	}

	private Position<Entry<K, V>> findPos(K key) {
		Position<Entry<K,V>> current=null;
		Position<Entry<K,V>> last;
		boolean found;		
		try {
			current = list.first();
			last = list.last();
			found = 0<comparator.compare(key, current.element().getKey());
			while(current!=last&&!found) {
				current = list.next(current);
				found = 0<comparator.compare(key, current.element().getKey());
			}
		} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {
			e.printStackTrace();
		}
		return current;
	}

	@Override
	public Entry<K, V> removeMin() throws EmptyPriorityQueueException {
		Entry<K,V> toReturn = null;
		if(list.isEmpty())
			throw new EmptyPriorityQueueException("La lista esta vacia brodi");
		try {
			toReturn = list.remove(list.last());
		} catch (InvalidPositionException | EmptyListException e) {
			e.printStackTrace();
		}
		return toReturn;
	}

}
