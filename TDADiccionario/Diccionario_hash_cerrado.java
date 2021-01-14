package TDADiccionario;

import TDALista.DoubleLinkedList;
import TDALista.PositionList;


public class Diccionario_hash_cerrado<K,V> implements Dictionary<K,V> {
	
	protected int n;
	protected int size;
	protected Entrada<K,V> [] hash;
	protected final float load_factor = 0.5f;
	protected final Entrada<K,V> bucket_no_usado = new Entrada<K,V>(null, null);
	protected final Entrada<K,V> bucket_disponible = new Entrada<K,V>(null, null);


	@SuppressWarnings("unchecked")
	public Diccionario_hash_cerrado() {
		n = 13;
		size = 0;
		hash = (Entrada<K,V> []) new Entrada [n];
		for(int i = 0; i < n; i++) {
			hash[i] = bucket_no_usado;
		}
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
	public Entry<K, V> find(K key) throws InvalidKeyException{
		if(key == null)
			throw new InvalidKeyException("La clave es nula");
		int first_position = bucket(key);
		int i = first_position;
		Entrada<K,V> toReturn = null;
		boolean traveled = false;
		boolean found = false;
		while(!found && !traveled && !hash[i].equals(bucket_no_usado)) {
			found = hash[i].getKey()!=null && hash[i].getKey().equals(key);
			i = (i+1)%n ;
			traveled = i == first_position;
		}
		toReturn = found?hash[(i+n-1)%n]:null;
		return toReturn;
	}
	@Override
	public Iterable<Entry<K,V>> findAll(K key) throws InvalidKeyException{
		if(key == null)
			throw new InvalidKeyException("La clave es nula");
		int first_position = bucket(key);
		int i = first_position;
		DoubleLinkedList<Entry<K,V>> iterable = new DoubleLinkedList<Entry<K,V>>();
		boolean traveled = false;
		while(!traveled && !hash[i].equals(bucket_no_usado)) {
			if(hash[i].getKey()!=null && hash[i].getKey().equals(key))
				iterable.addLast(hash[i]);
			i = (i+1)%n;
			traveled = i == first_position;
		}
		return iterable;
	}
	@Override
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException{
		if(key == null)
			throw new InvalidKeyException("La clave es nula");
		if(size/n >= load_factor)
			reHash();
		int i = bucket(key);
		boolean inserted = false;
		Entrada<K,V> toReturn = new Entrada<K,V>(key,value);
		while(!inserted) {
			if(hash[i].getKey() == null) {
				hash[i] = toReturn;
				inserted = true;
				size++;
			}
			i = (i+1)%n;	
		}
		return toReturn;
	}
	@Override
	public Entry<K, V> remove(Entry<K,V> e) throws InvalidEntryException{
        if(e == null ||e.getKey() == null)
            throw new InvalidEntryException("La entrada no es valida");
        int first_position = bucket(e.getKey());
        int i = first_position;
        Entrada<K,V> toReturn = null;
        boolean traveled = false;
        boolean found = false;
        while(!found && !traveled && !hash[i].equals(bucket_no_usado)) {
            found = hash[i].getKey()!=null && hash[i].getKey().equals(e.getKey());
            i = (i+1)%n;
            traveled = i == first_position;
        }
        if(!found)
            throw new InvalidEntryException("La entrada no es valida");
        else {
            toReturn = hash[(i+n-1)%n];
            hash[(i+n-1)%n] = bucket_disponible;
            size--;
            }
        return toReturn;
    }
	@Override
	public Iterable<Entry<K, V>> entries(){
		PositionList<Entry<K, V>> iterable = new DoubleLinkedList<Entry<K,V>>();
		for(int i = 0; i < n; i++) 
			if(hash[i].getKey()!= null) 
				iterable.addLast(hash[i]);
		return iterable;
	}

	protected int bucket(K key) {
		return Math.abs(key.hashCode() % n);
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
		while(isPrime && i<Math.sqrt(n))
			isPrime = n % i++ == 0?false:true;
		return isPrime;
	}
	@SuppressWarnings("unchecked")
	protected void reHash() {
		Entrada<K,V>[] aux = hash;
		n = nextPrime(n);
        size = 0;     
        hash = (Entrada<K,V>[]) new Entrada[n];
        for(int i = 0; i < n; i++) 
            hash[i] = bucket_no_usado;
        try {
            for(int i = 0; i < aux.length; i++) 
                if(aux[i].getKey()!=null) 
                    insert(aux[i].getKey(), aux[i].getValue());        
        }
        catch(InvalidKeyException e1) {
            e1.printStackTrace();
        }
	}
}