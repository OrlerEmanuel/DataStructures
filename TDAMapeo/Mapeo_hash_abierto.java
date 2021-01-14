package TDAMapeo;

import TDALista.BoundaryViolationException;
import TDALista.EmptyListException;
import TDALista.InvalidPositionException;
import TDALista.DoubleLinkedList;
import TDALista.Position;
import TDALista.PositionList;

/**
 * Modela un mapeo a traves de una tabla de dispersion abierta.
 * @author Emanuel Orler
 */
public class Mapeo_hash_abierto<K,V> implements Map<K,V>{
	protected PositionList<Entrada<K,V>>[] hash;
	protected int n,size;
	protected final double load_factor = 0.9f;
	/**
	 * Crea un mapeo vacio.
	 */
	@SuppressWarnings("unchecked")
	public Mapeo_hash_abierto() {
		size = 0;
		n = 5;
		hash = (PositionList<Entrada<K,V>>[]) new PositionList[n];
		int i = 0;
		while(i<n)
			hash[i++] = new DoubleLinkedList<Entrada<K,V>>();
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
		PositionList<Entrada<K,V>> list = hash[bucket(key)];
		Position<Entrada<K,V>> current, last;
		V toReturn = null;
		boolean found;
		try {
			if(!list.isEmpty()) {
				current = list.first();
				last = list.last();
				found = key.equals(current.element().getKey());
				while(!found && last != current) {
					current = list.next(current);
					found = key.equals(current.element().getKey());
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
		if(key == null)
			throw new InvalidKeyException("La clave es nula");
		Position<Entrada<K,V>> current, last;
		V toReturn = null;
		boolean found;
		if((size/n)>=load_factor)
			reHash();
		try {
			if(!hash[bucket(key)].isEmpty()) {		
				current = hash[bucket(key)].first();
				last = hash[bucket(key)].last();
				found = key.equals(current.element().getKey());
				while(!found && current != last) {
					current = hash[bucket(key)].next(current);
					found = key.equals(current.element().getKey());
				}
				if(found) {
					toReturn = current.element().getValue();
					current.element().setValue(value);
				}
				else {
					hash[bucket(key)].addLast(new Entrada<K,V>(key,value));
					size++;
				}
			}
			else {
				hash[bucket(key)].addLast(new Entrada<K,V>(key,value));
				size++;
			}
		}
		catch(EmptyListException | InvalidPositionException | BoundaryViolationException e){
			e.printStackTrace();
		}
		return toReturn;
	}
	@Override
	public V remove(K key) throws InvalidKeyException {
		if(key == null)
			throw new InvalidKeyException("La clave es nula");
		PositionList<Entrada<K,V>> list = hash[bucket(key)];
		Position<Entrada<K,V>> current, last;
		V toReturn = null;
		boolean found;
		try {
			if(!list.isEmpty()) {
				current = list.first();
				last = list.last();
				found = key.equals(current.element().getKey());
				while(!found && last != current) {
					current = list.next(current);
					found = key.equals(current.element().getKey());
				}
				if(found) {
					toReturn = current.element().getValue();
					list.remove(current);
					size--;
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
		int i = 0;
		while(i<n) {
			for(Position<Entrada<K, V>> p: hash[i].positions()) 
				iterable.addLast(p.element().getKey());
			i++;
		}
		return iterable;
	}
	@Override
	public Iterable<V> values() {
		PositionList<V> iterable = new DoubleLinkedList<V>();
		int i = 0;
		while(i<n) {
			for(Position<Entrada<K, V>> p: hash[i].positions()) 
				iterable.addLast(p.element().getValue());
			i++;
		}
		return iterable;
	}
	@Override
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K, V>> iterable = new DoubleLinkedList<Entry<K, V>>();
		int i = 0;
		while(i<n) {
			for(Position<Entrada<K, V>> p: hash[i].positions()) 
				iterable.addLast(p.element());
			i++;
		}
		return iterable;
	}
	/**
	 * Retorna el codigo hash para una clave dada.
	 * @param key: valor de la clave.
	 * @return p, donde p es la posicion en la tabla hash.
	 */
	private int bucket(K key) {
		return Math.abs(key.hashCode() % n);
	}
	/**
	 * Realiza un redimensionamiento de la tabla hash.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void reHash() {
		Iterable<Entry<K,V>> entries = entries();
		n = nextPrime(n);
		PositionList[] aux = (PositionList<Entrada<K,V>>[]) new PositionList[n];
		int i = 0;
		while(i<n)
			aux[i++] = new DoubleLinkedList<Entrada<K,V>>();
		for(Entry<K,V> p:entries) {
			aux[bucket(p.getKey())].addLast(p);;
		hash = aux;
		}
	}
	/**
	 * Retorna el siguiente primo a numero dado.
	 * @param n: Numero del que queremos hallar su proximo primo.
	 * @return p, donde p es el proximo primo de n.
	 */
	private int nextPrime(int n) {
		int toReturn;
		n = n+2;
		toReturn = isPrime(n)?n:nextPrime(n+2);
		return toReturn;
	}
	/**
	 * Verifica si un numero es primo o no.
	 * @param n: Numero a verificar.
	 * @return true en caso de que n sea primo, false en caso contrario.
	 */
	private boolean isPrime(int n) {
		boolean isPrime = true;
		int i = 2;
		while(isPrime && i<Math.sqrt(n))
			isPrime = n % i++ == 0?false:true;
		return isPrime;
	}
	public String toString() {
        String hashstring= "";
        for (Entry<K,V> entry : this.entries()) {
            hashstring += entry.getKey() + "=" + entry.getValue() + "|"; //ELIMINAR YA
        }
        return hashstring;
    }
}