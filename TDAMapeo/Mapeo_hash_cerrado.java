package TDAMapeo;

import TDALista.DoubleLinkedList;

public class Mapeo_hash_cerrado<K,V> implements Map<K,V> {
	protected int n;
	protected int size;
	protected Entrada<K,V> [] hash;
	protected final float load_factor = 0.5f;
	protected final Entrada<K,V> bucket_no_usado = new Entrada<K,V>(null, null);
	protected final Entrada<K,V> bucket_disponible = new Entrada<K,V>(null, null);
	
	@SuppressWarnings("unchecked")
	public Mapeo_hash_cerrado() {
		n = 1;
		size = 0;
		hash = (Entrada<K,V>[]) new Entrada[n];
		for(int i=0;i<n;i++) 
			hash[i] = bucket_no_usado;
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
		int bucket;
		if(key == null)
			throw new InvalidKeyException("La clave es invalida");
		bucket = key.hashCode() % n; 
		bucket = searchPos(key,bucket);
		return bucket!=-1?hash[bucket].getValue():null;
	}
	private int searchPos(K key, int i) {
		boolean found = key.equals(hash[i].getKey());
		boolean traveled = false;
		while(!found && !traveled && hash[i]!=bucket_no_usado) {
			i = i==n-1?i=0:i+1;
			found = key.equals(hash[i].getKey());
			traveled = i==key.hashCode() % n;
		}
		return found?i:-1;
	}
	@Override
	public V put(K key, V value) throws InvalidKeyException {
		V toReturn = null;
		int pos;
		boolean found;
		if(key == null)
			throw new InvalidKeyException("La clave es invalida");
		pos = key.hashCode() % n;
		found = key.equals(hash[pos].getKey());
		while(!found && hash[pos].getKey()!=null) {
			pos = pos==n-1?0:pos+1;
			found = key.equals(hash[pos].getKey());
		}
		if(found) {
			toReturn = hash[pos].getValue();
			hash[pos].setValue(value);
		}
		else {
			hash[pos] = new Entrada<K,V>(key,value);
			size++;
		}
		return toReturn;
	}
	@SuppressWarnings({ "unchecked", "unused" })
	private void reHash() {
		int sigPrime = nextPrime(n);
		int j;
		Entrada<K,V> [] aux = hash;
		hash = (Entrada<K,V>[]) new Entrada[sigPrime];
		for (int i=0;i<sigPrime;i++) {
			hash[i] = bucket_no_usado;
		}
		for(int i=0;i<n;i++) 
			if(aux[i]!=null) {
				j = aux[i].getKey().hashCode()%sigPrime;
				while(hash[j]!=bucket_no_usado) {
					j = j==sigPrime-1?0:j+1;
				}
				hash[j] = aux[i];
			}
		n = sigPrime;
	}
	@Override
	public V remove(K key) throws InvalidKeyException {
		V toReturn = null;
		if(key == null)
			throw new InvalidKeyException("La clave es invalida");
		int bucket = key.hashCode() % n; 
		bucket = searchPos(key,bucket);
		if(bucket != -1) {
			toReturn = hash[bucket].getValue();
			hash[bucket] = bucket_disponible;
			size--;
		}
		return toReturn;
	}
	@Override
	public Iterable<K> keys() {
		DoubleLinkedList<K> iterable = new DoubleLinkedList<K>();
		for(int i=0;i<n;i++) 
			if(hash[i].getKey()!=null)
				iterable.addFirst(hash[i].getKey());
		return iterable;
	}
	@Override
	public Iterable<V> values() {
		DoubleLinkedList<V> iterable = new DoubleLinkedList<V>();
		for(int i=0;i<n;i++) 
			if(hash[i].getKey()!=null)
				iterable.addFirst(hash[i].getValue());
		return iterable;
	}
	@Override
	public Iterable<Entry<K, V>> entries() {
		DoubleLinkedList<Entry<K, V>> iterable = new DoubleLinkedList<Entry<K, V>>();
		for(int i=0;i<n;i++) 
			if(hash[i].getKey()!=null)
				iterable.addFirst(hash[i]);
		return iterable;
	}
	
	private int nextPrime(int n) {
		int toReturn;
		n = n+2;
		toReturn = isPrime(n)?n:nextPrime(n+2);
		return toReturn;
	}
	private boolean isPrime(int n) {
		boolean isPrime = true;
		int i = 2;
		while(isPrime && i<n)
			isPrime = n % i++ == 0?false:true;
		return isPrime;
	}
}