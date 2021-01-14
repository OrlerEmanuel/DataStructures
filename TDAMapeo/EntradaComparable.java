package TDAMapeo;
public class EntradaComparable<K extends Comparable<K>,V> implements Entry<K,V>, Comparable<EntradaComparable<K,V>> {
	protected K key;
	protected V value;
	
	public EntradaComparable(K key, V value) {
		this.key = key;
		this.value = value;
	}
	@Override
	public int compareTo(EntradaComparable<K, V> o) {
		return key.compareTo(o.getKey());
	}
	public void setKey(K key) {
		this.key = key;
	}
	public void setValue(V value) {
		this.value = value;
	}
	@Override
	public K getKey() {
		return key;
	}
	@Override
	public V getValue() {
		return value;
	}
}