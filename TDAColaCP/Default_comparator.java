package TDAColaCP;

import java.util.Comparator;

public class Default_comparator<K extends Comparable<K>> implements Comparator<K>{

	@Override
	public int compare(K a, K b) {
		return a.compareTo(b);
	}
}
