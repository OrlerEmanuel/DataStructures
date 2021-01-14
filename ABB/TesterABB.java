package ABB;

public class TesterABB {

	public static void main(String[] args) {
		test_1();
		test_2();
	}

	

	public static Arbol_binario_busqueda<Integer>  test_1() {
		Arbol_binario_busqueda<Integer> t = new Arbol_binario_busqueda<Integer>();
		int[] carga = { 7, 2, 9, 0, 5, 6, 8, 1 };
		for (int i = 0; i < carga.length; i++) {
			t.expandir(t.buscar(carga[i]), carga[i]);
		}
		inorden(t,t.raiz());
		System.out.println();
		System.out.println("---------------");
		return t;
	}

	private static void inorden(Arbol_binario_busqueda<Integer> t, ABBNodo<Integer> n) {
		if(n.getLeft().element()!=null)
			inorden(t,n.getLeft());
		System.out.print(n.element()+" ");
		if(n.getRight().element()!=null)
			inorden(t,n.getRight());
	}
	
	private static void test_2() {
		
		Arbol_binario_busqueda<Integer> t= new Arbol_binario_busqueda<Integer>();
		int[] carga = { 7, 2, 9, 0, 5, 6, 8, 1 };
		for (int i = 0; i < carga.length; i++) {
			t.expandir(t.buscar(carga[i]), carga[i]);
		}
		inorden(t,t.raiz());
		System.out.println();
		System.out.println("se elimina el 2: ");
		t.eliminar(2);
		inorden(t,t.raiz());
		System.out.println();
		System.out.println("se elimina el 8: ");
		t.eliminar(8);
		inorden(t,t.raiz());
		System.out.println();
		System.out.println("se elimina el 7: ");
		t.eliminar(7);
		inorden(t,t.raiz());
		
		
	}
}