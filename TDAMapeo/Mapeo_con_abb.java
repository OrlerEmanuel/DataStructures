package TDAMapeo;

import ABB.ABBNodo;
import ABB.Arbol_binario_busqueda;
import TDALista.DoubleLinkedList;

public class Mapeo_con_abb<K extends Comparable<K>,V> implements Map<K,V> {
	protected Arbol_binario_busqueda<EntradaComparable<K,V>> abb;
	protected int size;
	
	public  Mapeo_con_abb() {
		abb = new Arbol_binario_busqueda<EntradaComparable<K,V>>();
		size = 0;
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
	public V get(K key) throws InvalidKeyException {
		if(key == null)
			throw new InvalidKeyException("La clave es nula");
		V toReturn = null; 
		EntradaComparable<K,V> toFind = new EntradaComparable<K,V>(key,null);
		ABBNodo<EntradaComparable<K,V>> place = abb.buscar(toFind);
		if(place.element()!=null)
			toReturn = place.element().getValue();
		return toReturn;
	}


	@Override
	public V put(K key, V value) throws InvalidKeyException {
		if(key == null)
			throw new InvalidKeyException("la clave es nula");
		EntradaComparable<K,V> newEntry = new EntradaComparable<K,V>(key,value);
		EntradaComparable<K,V> toFind = new EntradaComparable<K,V>(key,null);
		ABBNodo<EntradaComparable<K,V>> place = abb.buscar(toFind);
		V toReturn = null;
		if(place.element() == null) {
			abb.expandir(place, newEntry);
			size++;
		}
		else {
			toReturn = place.element().getValue();
			place.element().setValue(value);
		}
		return toReturn;
	}

	@Override
	public V remove(K key) throws InvalidKeyException {
		if(key == null)
			throw new InvalidKeyException("la clave es nula");
		V toReturn=null;
		EntradaComparable<K,V> toFind = new EntradaComparable<K,V>(key,null);
		ABBNodo<EntradaComparable<K,V>> place = abb.buscar(toFind);
		if(place.element()!=null) {
			toReturn = place.element().getValue();
			abb.eliminar(place.element());
			size--;
		}
		return toReturn;
	}

	@Override
	public Iterable<K> keys() {
		DoubleLinkedList<K> iterable = new DoubleLinkedList<K>();
		keyAux(iterable,abb.raiz());
		return iterable;
	}

	private void keyAux(DoubleLinkedList<K> iterable, ABBNodo<EntradaComparable<K, V>> node) {
		if(node.element()!=null) {
			iterable.addLast(node.element().getKey());
			keyAux(iterable,node.getLeft());
			keyAux(iterable,node.getRight());
		}
	}

	@Override
	public Iterable<V> values() {
		DoubleLinkedList<V> iterable = new DoubleLinkedList<V>();
		valueAux(iterable,abb.raiz());
		return iterable;
	}
	private void valueAux(DoubleLinkedList<V> iterable, ABBNodo<EntradaComparable<K, V>> node) {
		if(node.element()!=null) {
			iterable.addLast(node.element().getValue());
			valueAux(iterable,node.getLeft());
			valueAux(iterable,node.getRight());
		}
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		DoubleLinkedList<Entry<K, V>> iterable = new DoubleLinkedList<Entry<K, V>>();
		entriesAux(iterable,abb.raiz());
		return iterable;
	}
	private void entriesAux(DoubleLinkedList<Entry<K, V>> iterable, ABBNodo<EntradaComparable<K, V>> node) {
		if(node.element()!=null) {
			iterable.addLast(node.element());
			entriesAux(iterable,node.getLeft());
			entriesAux(iterable,node.getRight());
		}
	}

}