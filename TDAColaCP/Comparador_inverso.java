package TDAColaCP;

import java.util.Comparator;

public class Comparador_inverso implements Comparator<Integer>{

	@Override
	public int compare(Integer o1, Integer o2) {
		int toRet;
		if(o1.compareTo(o2)!=0)
			toRet = -o1.compareTo(o2);
		else
			toRet = 0;
		return toRet;
	}

}
