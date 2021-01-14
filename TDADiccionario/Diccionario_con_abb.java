package TDADiccionario;

import ABB.ABBNodo;
import ABB.Arbol_binario_busqueda;
import TDALista.BoundaryViolationException;
import TDALista.DoubleLinkedList;
import TDALista.EmptyListException;
import TDALista.InvalidPositionException;
import TDALista.Position;
import TDALista.PositionList;

public class Diccionario_con_abb<K extends Comparable<K>,V> implements Dictionary<K,V> {
	Arbol_binario_busqueda<EntradaComparable<K,PositionList<Entry<K,V>>>> abb;
	int size;
	
	public Diccionario_con_abb() {
		abb = new Arbol_binario_busqueda<EntradaComparable<K,PositionList<Entry<K,V>>>>();
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
		if(key == null)
			throw new InvalidKeyException("la clave es nula");
		Entry<K,V> toReturn = null;
		EntradaComparable<K,PositionList<Entry<K,V>>> toFind = new EntradaComparable<K,PositionList<Entry<K,V>>>(key,null);
		ABBNodo<EntradaComparable<K,PositionList<Entry<K,V>>>> place = abb.buscar(toFind);
		try {
			if(place.element()!=null && !place.element().getValue().isEmpty())
				toReturn = place.element().getValue().last().element();
		} catch (EmptyListException e) {
			e.printStackTrace();
		}
		return toReturn;
			
	}

	@Override
	public Iterable<Entry<K, V>> findAll(K key) throws InvalidKeyException {
		if(key == null)
			throw new InvalidKeyException("la clave es nula");
		PositionList<Entry<K,V>> toReturn = new DoubleLinkedList<Entry<K,V>>();
		EntradaComparable<K,PositionList<Entry<K,V>>> toFind = new EntradaComparable<K,PositionList<Entry<K,V>>>(key,null);
		ABBNodo<EntradaComparable<K,PositionList<Entry<K,V>>>> place = abb.buscar(toFind);
		if(place.element()!=null)
			for(Entry<K,V> p :place.element().getValue())
				toReturn.addFirst(p);
		return toReturn;
	}

	@Override
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		if(key == null)
			throw new InvalidKeyException("la clave es nula");
		Entry<K,V> newEntry = new Entrada<K,V>(key,value);
		EntradaComparable<K,PositionList<Entry<K,V>>> toFind = new EntradaComparable<K,PositionList<Entry<K,V>>>(key,null);
		ABBNodo<EntradaComparable<K,PositionList<Entry<K,V>>>> place = abb.buscar(toFind);
		if(place.element()!=null)
			place.element().getValue().addLast(newEntry);
		else {
			abb.expandir(place,toFind);
			toFind.setValue(new DoubleLinkedList<Entry<K,V>>());
			toFind.getValue().addLast(newEntry);
		}
		size++;
		return newEntry;
	}

	@Override
	public Entry<K, V> remove(Entry<K, V> e) throws InvalidEntryException {
		if(e==null || e.getKey()==null)
			throw new InvalidEntryException("la entrada es invalida");
		boolean found = false;
		Entry<K,V> toReturn = null;
		PositionList<Entry<K,V>> list;
		Position<Entry<K,V>> current, last;
		EntradaComparable<K,PositionList<Entry<K,V>>> toFind = new EntradaComparable<K,PositionList<Entry<K,V>>>(e.getKey(),null);
		ABBNodo<EntradaComparable<K,PositionList<Entry<K,V>>>> place = abb.buscar(toFind);
		try {
			if(place.element()!=null && !place.element().getValue().isEmpty()) {
				list = place.element().getValue();
				current = list.first();
				last = list.last();
				found = current.element().equals(e);
				while(!found && current!=last) {
					current = list.next(current);
					found = current.element().equals(e);
				}
				if(found) {
					toReturn = list.remove(current);
					size--;
				}
			}
			if(!found)
				throw new InvalidEntryException("la entrada no esta");
		} catch (InvalidPositionException | EmptyListException | BoundaryViolationException e1) {
			e1.printStackTrace();
		}
		return toReturn;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		DoubleLinkedList<Entry<K,V>> iterable = new DoubleLinkedList<Entry<K,V>>();
		entriesAux(iterable,abb.raiz());
		return iterable;
	}
	private void entriesAux(DoubleLinkedList<Entry<K, V>> iterable, ABBNodo<EntradaComparable<K, PositionList<Entry<K, V>>>> nodo) {
		if(nodo.element()!=null) {
			for(Entry<K,V> p: nodo.element().getValue())
				iterable.addLast(p);
			entriesAux(iterable,nodo.getLeft());
			entriesAux(iterable,nodo.getRight());
		}
		
	}
	

}