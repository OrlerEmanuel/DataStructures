package TDAArbol;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import TDALista.*;

/**
 * Tree Test - Estructura de Datos (DCIC-UNS).
 * Define casos de pruebas a aplicar sobre el TDA Tree<E>.
 * @author Estructuras de Datos, DCIC-UNS.
 * @version 1.0 - MarÃ­a Lujan Ganuza (2013-2018) 
 * @version 2.0 - Federico JoaquÃ­n (2019-2020) 
 * @see Tree<E>
 */
public class TreeTest {
	private Tree<Integer> T;
	
	/*
	 * Retorna la clase tipo Tree a testear.
	 * Indique la clase que implementa Tree<E> que desea testear.
	 * Por ejemplo: LinkedTree<String>.
	 */
	private Tree<Integer> getTree() {
		return new Arbol_enlazado<Integer>();
	}
	
	/*
	 * Inicializa el Ã¡rbol antes de cada test individual
	 */
	@Before
	public void setUp() {
		T = getTree();
	}
	
	/* _______________________TESTS METODO size()_____________________________*/

	@Test
	public void size() {
		Position<Integer> h1, h2, h3, h4;
		h1 = h2 = h3 = h4 = null;
		
		// Caso de prueba: Ã�rbol VacÃ­o.
		assertTrue("TamaÃ±o del Ã¡rbol justo despuÃ©s de ser creado != 0", T.size() == 0);
		
		// Caso de prueba: Solo raÃ­z.
		try {
		
			T.createRoot(1);
			assertTrue("TamaÃ±o del Ã¡rbol con 1 elemento != 1", T.size() == 1);
		
		} catch (InvalidOperationException e) {
			fail("Al crear la raÃ­z de un Ã¡rbol vacÃ­o lanza la excepciÃ³n InvalidOperationException");
		}
		
		try {
		
			h1 = T.addFirstChild(T.root(), 2);
			assertTrue("TamaÃ±o del Ã¡rbol con 2 elementos != 2", T.size() == 2);
		
		} catch (InvalidPositionException e) {
			fail("Al invocar addFirstChild(root,e) lanza la excepciÃ³n InvalidPositionException");
		
		} catch (EmptyTreeException e2) {
			fail("Al solicitar la raÃ­z de un Ã¡rbol no vacÃ­o lanza la excepciÃ³n EmptyTreeException");
		}
		
		try {
		
			h4 = T.addLastChild(T.root(), 5);
			assertTrue("TamaÃ±o del Ã¡rbol con 3 elementos != 3", T.size() == 3);
		
		} catch (InvalidPositionException e) {
			fail("Al invocar addLastChild(root,e) lanza la excepciÃ³n InvalidPositionException");
		
		} catch (EmptyTreeException e2) {
			fail("Al solicitar la raÃ­z de un Ã¡rbol no vacÃ­o lanza la excepciÃ³n EmptyTreeException");
		}
		
		try {
		
			h2 = T.addAfter(T.root(), h1, 3);
			assertTrue("TamaÃ±o del Ã¡rbol con 4 elementos != 4", T.size() == 4);
		
		} catch (InvalidPositionException e) {
			fail("Al invocar addAfter(root,primerHijo,e) lanza la excepciÃ³n InvalidPositionException");
		
		} catch (EmptyTreeException e2) {
			fail("Al solicitar la raÃ­z de un Ã¡rbol no vacÃ­o lanza la excepciÃ³n EmptyTreeException");
		}
		
		try {
		
			h3 = T.addBefore(T.root(), h4, 4);
			assertTrue("TamaÃ±o del Ã¡rbol con 5 elementos != 5", T.size() == 5);
		
		} catch (InvalidPositionException e) {
			fail("Al invocar addBefore(root,UltimoHijo,e) lanza la excepciÃ³n InvalidPositionException. ");
		
		} catch (EmptyTreeException e2) {
			fail("Al solicitar la raÃ­z de un Ã¡rbol no vacÃ­o lanza la excepciÃ³n EmptyTreeException");
		}
		
		try {
		
			T.addFirstChild(h3, 8);
			assertTrue("TamaÃ±o del Ã¡rbol con 6 elementos != 6", T.size() == 6);
			
			T.addFirstChild(h3, 7);
			assertTrue("TamaÃ±o del Ã¡rbol con 7 elementos != 7", T.size() == 7);
		
		} catch (InvalidPositionException e) {
			fail("Al invocar addFirstChild(h3,e) lanza la excepciÃ³n InvalidPositionException");
		}
		
		// Eliminando
		try {
			
			T.removeInternalNode(h3);
			assertTrue("TamaÃ±o del Ã¡rbol con 6 elementos != 6", T.size() == 6);
		
		} catch (InvalidPositionException e) {
			fail("Al invocar removeInternalNode con una posiciÃ³n vÃ¡lida lanza la excepciÃ³n InvalidPositionException (testing size())");
		}
		
		try {
		
			T.removeExternalNode(h2);
			assertTrue("TamaÃ±o del Ã¡rbol con 5 elementos != 5", T.size() == 5);
		
		} catch (InvalidPositionException e) {
			fail("Al invocar removeExternalNode con una posiciÃ³n vÃ¡lida lanza la excepciÃ³n InvalidPositionException (testing size())");
		}
		
		try {
		
			T.removeNode(h4);
			assertTrue("TamaÃ±o del Ã¡rbol con 4 elementos != 4", T.size() == 4);
		
		} catch (InvalidPositionException e) {
			fail("Al invocar removeNode con una posiciÃ³n vÃ¡lida lanza la excepciÃ³n InvalidPositionException (testing size())");
		}
	}
	
	/* _______________________TESTS METODO isEmpty()_____________________________*/
	
	@Test
	public void isEmpty() {
		Position<Integer> h1, h2, h3, h4;
		h1 = h2 = h3 = h4 = null;
		
		// Caso de prueba: Ã�rbol VacÃ­o.
		assertTrue("El Ã¡rbol justo despuÃ©s de ser creado no estÃ¡ vacÃ­o", T.isEmpty());
		
		// Caso de prueba: Solo raÃ­z.
		try {
		
			T.createRoot(1);
			assertFalse("El Ã¡rbol con 1 elemento estÃ¡ vacÃ­o", T.isEmpty());
		
		} catch (InvalidOperationException e) {
			fail("Al crear la raÃ­z de un Ã¡rbol vacÃ­o lanza la excepciÃ³n InvalidOperationException");
		}
		
		try {
		
			h1 = T.addFirstChild(T.root(), 2);
			assertFalse("El Ã¡rbol con 2 elementos estÃ¡ vacÃ­o", T.isEmpty());
		
		} catch (InvalidPositionException e) {
			fail("Al invocar addFirstChild(root,e) lanza la excepciÃ³n InvalidPositionException");
		
		} catch (EmptyTreeException e2) {
			fail("Al solicitar la raÃ­z de un Ã¡rbol no vacÃ­o lanza la excepciÃ³n EmptyTreeException");
		}
		
		try {
	
			h4 = T.addLastChild(T.root(), 5);
			assertFalse("El Ã¡rbol con 3 elementos estÃ¡ vacÃ­o", T.isEmpty());
		
		} catch (InvalidPositionException e) {
			fail("Al invocar addLastChild(root,e) lanza la excepciÃ³n InvalidPositionException");
		
		} catch (EmptyTreeException e2) {
			fail("Al solicitar la raÃ­z de un Ã¡rbol no vacÃ­o lanza la excepciÃ³n EmptyTreeException");
		}
		
		try {
		
			h2 = T.addAfter(T.root(), h1, 3);
			assertFalse("El Ã¡rbol con 4 elementos estÃ¡ vacÃ­o", T.isEmpty());
		
		} catch (InvalidPositionException e) {
			fail("Al invocar addAfter(root,primerHijo,e) lanza la excepciÃ³n InvalidPositionException");
		
		} catch (EmptyTreeException e2) {
			fail("Al solicitar la raÃ­z de un Ã¡rbol no vacÃ­o lanza la excepciÃ³n EmptyTreeException");
		}
		
		try {
		
			h3 = T.addBefore(T.root(), h4, 4);
			assertFalse("El Ã¡rbol con 5 elementos estÃ¡ vacÃ­o", T.isEmpty());
		
		} catch (InvalidPositionException e) {
			fail("Al invocar addBefore(root,UltimoHijo,e) lanza la excepciÃ³n InvalidPositionException");
		
		} catch (EmptyTreeException e2) {
			fail("Al solicitar la raÃ­z de un Ã¡rbol no vacÃ­o lanza la excepciÃ³n EmptyTreeException");
		}
		
		try {
		
			T.addFirstChild(h3, 8);
			assertFalse("El Ã¡rbol con 6 elementos estÃ¡ vacÃ­o", T.isEmpty());
			
			T.addFirstChild(h3, 7);
			assertFalse("El Ã¡rbol con 7 elementos estÃ¡ vacÃ­o", T.isEmpty());
		
		} catch (InvalidPositionException e) {
			fail("Al invocar addFirstChild(h3,e) lanza la excepciÃ³n InvalidPositionException");
		}
		
		// Eliminando
		try {
	
			T.removeInternalNode(h3);
			assertFalse("El Ã¡rbol con 6 elementos estÃ¡ vacÃ­o", T.isEmpty());
		
		} catch (InvalidPositionException e) {
			fail("Al invocar removeInternalNode con una posiciÃ³n vÃ¡lida lanza la excepciÃ³n InvalidPositionException (testing size())");
		}
		
		try {
		
			T.removeExternalNode(h2);
			assertFalse("El Ã¡rbol con 5 elementos estÃ¡ vacÃ­o", T.isEmpty());
		
		} catch (InvalidPositionException e) {
			fail("Al invocar removeExternalNode con una posiciÃ³n vÃ¡lida lanza la excepciÃ³n InvalidPositionException (testing size())");
		}
		
		try {
		
			T.removeNode(h4);
			assertFalse("El Ã¡rbol con 4 elementos estÃ¡ vacÃ­o", T.isEmpty());
		
		} catch (InvalidPositionException e) {
			fail("Al invocar removeNode con una posiciÃ³n vÃ¡lida lanza la excepciÃ³n InvalidPositionException (testing size())");
		}
	}
	
	/* _______________________TESTS METODO iterator()_____________________________*/
	
	@Test
	public void iterator() {
		java.util.Iterator<Integer> it;
		int i = 1;
		
		// Caso de prueba: Ã�rbol vacÃ­o.
		it = T.iterator();
		assertTrue("El iterador debe estar vacÃ­o", it.hasNext() == false);
		
		// Caso de prueba: Ã�rbol con un solo elemento.
		try {
		
			T.createRoot(1);
			it = T.iterator();
		
		} catch (InvalidOperationException e) {
			fail("Al invocar createRoot() con un Ã¡rbol vacÃ­o lanza la excepciÃ³n InvalidOperationException");
		}
		
		assertTrue("El iterador no debe estar vacÃ­o", it.hasNext() == true);
		assertTrue("El iterador no funciona correctamente", it.next().equals(1));
		assertTrue("El iterador debe estar vacÃ­o", it.hasNext() == false);

		// Caso de prueba: Ã�rbol con mÃ¡s de un elemento
		T = getTree();
		cargarArbol(T);
		
		it = T.iterator();
		while (it.hasNext()) {
			assertTrue("El iterador no funciona correctamente", it.next().equals(i));
			i++;
		}
		
		assertTrue("El iterador no funciona correctamente", i == T.size() + 1);
	}

	/* _______________________TESTS METODO positions()_____________________________*/
	
	@Test
	public void positions() {
		java.util.Iterator<Position<Integer>> it;
		int i = 1;
		
		// Caso de prueba: Ã�rbol vacÃ­o.
		it = T.positions().iterator();
		assertTrue("El iterador debe estar vacÃ­o", it.hasNext() == false);
		
		// Caso de prueba: Ã�rbol con un solo elemento.
		try {
	
			T.createRoot(1);
			it = T.positions().iterator();
		
		} catch (InvalidOperationException e) {
			fail("Al invocar createRoot() con un Ã¡rbol vacÃ­o lanza la excepciÃ³n InvalidOperationException");
		}

		assertTrue("El iterador no debe estar vacÃ­o", it.hasNext() == true);
		assertTrue("El iterador no funciona correctamente", it.next().element().equals(1));
		assertTrue("El iterador debe estar vacÃ­o", it.hasNext() == false);

		// Caso de prueba: Ã�rbol con mÃ¡s de un elemento
		T = getTree();
		cargarArbol(T);
		
		it = T.positions().iterator();
		while (it.hasNext()) {
			assertTrue("El iterador no funciona correctamente", it.next().element().equals(i));
			i++;
		}
		
		assertTrue("El iterador no funciona correctamente", i == T.size() + 1);
		
	}
	
	/* _______________________TESTS METODO replace()_____________________________*/
	
	@Test
	public void replace() {
		java.util.Iterator<Position<Integer>> hijos;
		Position<Integer> h1 = null;
		
		// PosiciÃ³n InvÃ¡lida
		try {
		
			T.replace(null, 0);
			fail("Replace() deberÃ­a lanzar la excepciÃ³n InvalidPositionException con una posiciÃ³n InvÃ¡lida");
		
		} catch (InvalidPositionException e) {}
		
		// Ã�rbol con 1 solo elemento
		try {
			
			T.createRoot(1);
			T.replace(T.root(), 2);
			assertTrue("Replace no funciona correctamente para un Ã¡rbol con un solo elemento.", T.root().element().equals(2));
		
		} catch (InvalidOperationException e) {
			fail("createRoot() no deberÃ­a lanzar InvalidOperationException con un Ã¡rbol vacÃ­o");
		
		} catch (InvalidPositionException e2) {
			fail("replace() no deberÃ­a lanzar InvalidPositionException con una posiciÃ³n vÃ¡lida.");
		
		} catch (EmptyTreeException e) {
			fail("root() no deberÃ­a lanzar EmptyTreeException con un Ã¡rbol con elementos");
		}
		
		// Ã�rbol con varios elementos
		T = getTree();
		cargarArbol(T);
		
		// Nodo interno
		try {
			hijos = T.children(T.root()).iterator();
			
			// Nodo hoja
			h1 = hijos.next();
			assertTrue("Arbol mal armado", h1.element().equals(2));
			
			T.replace(h1, 10);
			assertTrue("Replace no funciona correctamente", h1.element().equals(10));
			
			// Nodo interno
			h1 = hijos.next();
			assertTrue("Arbol mal armado", h1.element().equals(3));
			
			T.replace(h1, 10);
			assertTrue("Replace no funciona correctamente", h1.element().equals(10));
		
		} catch (EmptyTreeException e2) {
			fail("Al solicitar la raÃ­z de un Ã¡rbol no vacÃ­o lanza la excepciÃ³n EmptyTreeException");
		
		} catch (InvalidPositionException e2) {
			fail("replace() no deberÃ­a lanzar InvalidPositionException con una posiciÃ³n vÃ¡lida.");
		}
	}
	
	/* _______________________TESTS METODO root()_____________________________*/
	
	@Test
	public void root() {
		// Ã�rbol vacÃ³o
		try {
		
			T.root();
			fail("root() deberÃ­a haber lanzado la excepciÃ³n EmptyTreeException con un Ã¡rbol vacÃ­o");
		
		} catch (EmptyTreeException e) {}
		
		// Ã�rbol con un elemento
		try {
		
			T.createRoot(1);
			assertTrue("root() no funciona correctamente para un Ã¡rbol con un solo elemento.", T.root().element().equals(1));
			
		} catch (InvalidOperationException e) {
			fail("createRoot() no deberÃ­a lanzar InvalidOperationException con un Ã¡rbol vacÃ­o");
		
		} catch (EmptyTreeException e) {
			fail("root() no deberÃ­a lanzar EmptyTreeException con un Ã¡rbol con elementos");
		}
		
		// Ã�rbol con mÃ¡s de un elemento
		T = getTree();
		cargarArbol(T);
		
		try {
			
			assertTrue("root() no funciona correctamente para un Ã¡rbol con muchos solo elementos.", T.root().element().equals(1));
			
		} catch (EmptyTreeException e) {
			fail("root() no deberÃ­a lanzar EmptyTreeException con un Ã¡rbol con elementos");
		}
	}
	
	/* _______________________TESTS METODO parent()_____________________________*/
	
	@Test
	public void parent() {
		// PosiciÃ³n InvÃ¡lida
		try {
		
			T.parent(null);
			fail("parent() deberÃ­a lanzar la excepciÃ³n InvalidPositionException al invocarse con una posiciÃ³n invÃ¡lida");
		
		} catch (InvalidPositionException e) {
			// SerÃ­a lo que debe suceder
			
		} catch (BoundaryViolationException e) {
			fail("parent() no deberÃ­a lanzar la excepciÃ³n BoundaryViolationException con una posiciÃ³n distinta a la de la raÃ­z");
		}
		
		// RaÃ­z
		T = getTree();
		cargarArbol(T);

		try {
			
			T.parent(T.root());
			fail("parent() deberÃ­a lanzar la excepciÃ³n BoundaryViolationException al invocarse con la posiciÃ³n de la raÃ­z");
		
		} catch (InvalidPositionException e1) {
			fail("parent() no deberÃ­a lanzar la excepciÃ³n InvalidPositionException con una posiciÃ³n vÃ¡lida");
		
		} catch (BoundaryViolationException e2) {
			// SerÃ­a lo que debe suceder
			
		} catch (EmptyTreeException e3) {
			fail("root() no deberÃ­a lanzar EmptyTreeException con un Ã¡rbol con elementos");
		}

		try {
		
			chequearPadre(T.root());
		
		} catch (EmptyTreeException e3) {
			fail("root() no deberÃ­a lanzar EmptyTreeException con un Ã¡rbol con elementos");
		}
	}
	
	/* _______________________TESTS METODO children()_____________________________*/
	
	@Test
	public void children() {
		java.util.Iterator<Position<Integer>> hijos;
		java.util.Queue<Position<Integer>> cola;
		Position<Integer> p;
		int i;
		
		// PosiciÃ³n InvÃ¡lida
		try {
		
			T.children(null);
			fail("children() deberÃ­a lanzar la excepciÃ³n InvalidPositionException con una posiciÃ³n InvÃ¡lida");
		
		} catch (InvalidPositionException e) {
			// SerÃ­a lo que debe suceder
		}
		
		// Ã�rbol con un solo elemento
		try {
			
			T.createRoot(1);
			hijos = T.children(T.root()).iterator();
			assertTrue(	"children() no funciona correctamente para un Ã¡rbol con un solo elemento, la lista de hijos de la raÃ­z no es vacÃ­a.", !hijos.hasNext());
		
		} catch (InvalidOperationException e) {
			fail("createRoot() no deberÃ­a lanzar InvalidOperationException con un Ã¡rbol vacÃ­o");
		
		} catch (InvalidPositionException e2) {
			fail("children() no deberÃ­a lanzar InvalidPositionException con una posiciÃ³n vÃ¡lida.");
		
		} catch (EmptyTreeException e) {
			fail("root() no deberÃ­a lanzar EmptyTreeException con un Ã¡rbol con elementos");
		}
		
		// Ã�rbol con varios elementos
		T = getTree();
		cargarArbol2(T);
		
		hijos = null;
		i = 1;
		cola = new java.util.LinkedList<Position<Integer>>();
		
		try {
		
			p = T.root();
		
		} catch (EmptyTreeException e1) {
			fail("root() no deberÃ­a lanzar la excepciÃ³n EmptyTreeException con un Ã¡rbol con elementos.");
			p = null;
		}
		
		// Recorre el Ã¡rbol por niveles, chequeando mÃ©todo children.
		if (p != null) {
			cola.add(p);
			cola.add(null);
			
			while (!cola.isEmpty()) {
				
				try {
				
					p = cola.remove();
				
				} catch (java.util.NoSuchElementException e) {
					fail("dequeue() no deberÃ­a lanzar EmptyQueueException para una cola con elementos.");
				}
				
				if (p == null) {
					if (!cola.isEmpty())
						cola.add(null);
				} else {
					assertTrue("children() no funciona correctamente.", p.element().equals(i));
					i++;
					
					try {
					
						hijos = T.children(p).iterator();
						if (T.isExternal(p)) {
							assertFalse("children no funciona correctamente para listas de hijos vacÃ­as.", hijos.hasNext());
						} else {
							assertTrue("children no funciona correctamente para listas de hijos vacÃ­as.", hijos.hasNext());
							while (hijos.hasNext()) {
								cola.add(hijos.next());
							}
						}
					
					} catch (InvalidPositionException e) {
						fail("children() no deberÃ­a lanzar la excepciÃ³n InvalidPositionException para una posiciÃ³n vÃ¡lida");
					}
				}

			}
			assertTrue("children() no funciona correctamente.", T.size() == (i - 1));
		}
	}
	
	/* _______________________TESTS METODO isInternalExternal()_____________________________*/
	
	@Test
	public void isInternalExternal() {
		java.util.Iterator<Position<Integer>> hijos = null;
		java.util.Queue<Position<Integer>> cola = new java.util.LinkedList<Position<Integer>>();
		Position<Integer> p;
		
		cargarArbol(T);
		
		// PosiciÃ³n invÃ¡lida
		try {
			
			T.isExternal(null);
			fail("isExternal deberÃ­a lanzar la excepciÃ³n InvalidPositionException con una posiciÃ³n invÃ¡lida");
		
		} catch (InvalidPositionException e) {
			// SerÃ­a lo que debe suceder
		}
		
		try {
		
			T.isInternal(null);
			fail("isInternal deberÃ­a lanzar la excepciÃ³n InvalidPositionException con una posiciÃ³n invÃ¡lida");
		
		} catch (InvalidPositionException e) {
			// SerÃ­a lo que debe suceder
		}

		try {
			
			p = T.root();
			
		} catch (EmptyTreeException e1) {
			fail("root() no deberÃ­a lanzar la excepciÃ³n EmptyTreeException con un Ã¡rbol con elementos.");
			p = null;
		}
		
		if (p != null) {
			cola.add(p);
			cola.add(null);
			while (!cola.isEmpty()) {
				try {
				
					p = cola.remove();
				
				} catch (java.util.NoSuchElementException e) {
					fail("dequeue() no deberÃ­a lanzar EmptyQueueException para una cola con elementos.");
				}
				
				if (p == null) {
					if (!cola.isEmpty()) {
						cola.add(null);
					}
				} else {
					try {
						
						hijos = T.children(p).iterator();
						if (hijos.hasNext()) {
							
							assertTrue("isInternal no funciona correctamente.", T.isInternal(p));
							assertFalse("isExternal no funciona correctamente.", T.isExternal(p));
							
						} else {
							
							assertTrue("isExternal no funciona correctamente.", T.isExternal(p));
							assertFalse("isInternal no funciona correctamente.", T.isInternal(p));
							
							while (hijos.hasNext()) {
								cola.add(hijos.next());
							}
						}
					
					} catch (InvalidPositionException e) {
						fail("isInternal() o isExternal() no deberÃ­an lanzar la excepciÃ³n InvalidPositionException para una posiciÃ³n vÃ¡lida");
					}
				}
			}
		}
	}

	/* _______________________TESTS METODO isRoot()_____________________________*/
	
	@Test
	public void isroot() {
		java.util.Iterator<Position<Integer>> hijos = null;
		java.util.Queue<Position<Integer>> cola = new java.util.LinkedList<Position<Integer>>();
		Position<Integer> p = null;
		
		cargarArbol(T);
		
		try {
			
			T.isRoot(null);
			fail("isRoot deberÃ­a lanzar la excepciÃ³n InvalidPositionException con una posiciÃ³n invÃ¡lida");
		
		} catch (InvalidPositionException e) {
			// Seria lo que debe suceder
		}

		try {
			
			p = T.root();
			assertTrue("isroot() no funciona correctamente", T.isRoot(p));
			
		} catch (EmptyTreeException e1) {
			fail("root() no deberÃ­a lanzar la excepciÃ³n EmptyTreeException con un Ã¡rbol con elementos.");
			p = null;
		
		} catch (InvalidPositionException e1) {
			fail("root() no deberÃ­a lanzar la excepciÃ³n InvalidPositionException con una posiciÃ³n vÃ¡lida.");
		}
		
		if (p != null) {
			cola.add(p);
			cola.add(null);
			
			while (!cola.isEmpty()) {
				try {
					
					p = cola.remove();
					
				} catch (java.util.NoSuchElementException e) {
					fail("dequeue() no deberÃ­a lanzar EmptyQueueException para una cola con elementos.");
				}
				
				if (p == null) {
					if (!cola.isEmpty()) {
						cola.add(null);
					}
				} else {
					try {

						hijos = T.children(p).iterator();
						while (hijos.hasNext()) {
							p = hijos.next();
							assertTrue("isroot() no funciona correctamente", !T.isRoot(p));
							cola.add(p);
						}
					
					} catch (InvalidPositionException e) {
						fail("isInternal() o isExternal no deberÃ­an lanzar la excepciÃ³n InvalidPositionException para una posiciÃ³n vÃ¡lida");
					}
				}
			}
		}
	}
	
	/* _______________________TESTS METODO createRoot()_____________________________*/
	
	@Test
	public void createRoot() {
		try {
			
			T.createRoot(1);
			assertTrue("createRoot no funciona correctamente", T.root().element().equals(1));
			assertTrue("createRoot no actualiza el size", T.size() == 1);
			assertFalse("createRoot no funciona correctamente", T.children(T.root()).iterator().hasNext());

		} catch (InvalidOperationException e) {
			fail("createRoot() no deberÃ­a lanzar la excepciÃ³n InvalidOperationException para un Ã¡rbol vacÃ­o.");
		
		} catch (EmptyTreeException e) {
			fail("root() no deberÃ­a lanzar la excepciÃ³n EmptyTreeException para un Ã¡rbol no vacÃ­o.");
		
		} catch (InvalidPositionException e) {
			fail("children() no deberÃ­a lanzar la excepciÃ³n InvalidPositionException para una posiciÃ³n vÃ¡lida.");
		}
		
		// Crear raÃ­z de un Ã¡rbol no vacÃ­o
		try {
		
			T.createRoot(2);
			fail("createRoot() deberÃ­a haber lanzado la excepciÃ³n InvalidOperationException sobre un Ã¡rbol no vacÃ­o.");
		
		} catch (InvalidOperationException e) {
			// SerÃ­a lo que debe suceder
		}

	}

	/* _______________________TESTS METODO addFirstChild()_____________________________*/
	
	@Test
	public void addFirstChild() {
		java.util.Iterator<Position<Integer>> hijos;
		Position<Integer> p = null;
		int i;
		
		// Agrego hijos a la raÃ­z
		try {
			
			T.createRoot(1);
			p = T.root();
			
			T = getTree();
			T.addFirstChild(p, 2);
			
			fail("addFirstChild() deberÃ­a lanzar la excepciÃ³n InvalidPositionException cuando el Ã¡rbol estÃ¡ vacÃ­o");
		
		} catch (InvalidOperationException e) {
			fail("createRoot() no deberÃ­a lanzar la excepciÃ³n InvalidOperationException para un Ã¡rbol vacÃ­o.");
		
		} catch (EmptyTreeException e) {
			fail("root() no deberÃ­a lanzar la excepciÃ³n EmptyTreeException para un Ã¡rbol no vacÃ­o.");
		
		} catch (InvalidPositionException e) {
			// SerÃ­a lo que debe suceder
		}
		
		try {
		
			T.createRoot(1);
			for (i = 2; i < 10; i++) {
				p = T.addFirstChild(T.root(), i);
				assertTrue("addFirstChild() no actualiza correctamente el size ", T.size() == i);
			}
			
			chequearPadre(T.root());
		
		} catch (InvalidPositionException e) {
			fail("addFirstChild() no deberÃ­a lanzar la excepciÃ³n InvalidPositionException con una posiciÃ³n vÃ¡lida.");
		
		} catch (EmptyTreeException e) {
			fail("root() no deberÃ­a lanzar la excepciÃ³n EmptyTreeException para un Ã¡rbol no vacÃ­o.");
		
		} catch (InvalidOperationException e) {
			fail("createRoot() no deberÃ­a lanzar la excepciÃ³n InvalidOperationException para un Ã¡rbol vacÃ­o.");
		}

		try {
			
			hijos = T.children(T.root()).iterator();
			i = 9;
			while (hijos.hasNext()) {
				p = hijos.next();
				assertTrue("addFirstChild() no funciona correctamente", p.element().equals(i));
				i--;
			}
			assertTrue("addFirstChild() no funciona correctamente", i == 1);

		} catch (InvalidPositionException e) {
			fail("addFirstChild() no deberÃ­a lanzar la excepciÃ³n InvalidPositionException con una posiciÃ³n vÃ¡lida.");
		
		} catch (EmptyTreeException e) {
			fail("root() no deberÃ­a lanzar la excepciÃ³n EmptyTreeException para un Ã¡rbol no vacÃ­o.");
		}
		
		// Agrego hijos al hijo extremo izquierdo de la raÃ­z.
		try {
			for (i = 10; i < 20; i++) {
				T.addFirstChild(p, i);
				assertTrue("addFirstChild() no actualiza correctamente el size ", T.size() == i);
			}
			chequearPadre(T.root());
		
		} catch (InvalidPositionException e) {
			fail("addFirstChild() no deberÃ­a lanzar la excepciÃ³n InvalidPositionException con una posiciÃ³n vÃ¡lida.");
		
		} catch (EmptyTreeException e) {
			fail("root() no deberÃ­a lanzar la excepciÃ³n EmptyTreeException para un Ã¡rbol no vacÃ­o.");
		}

		try {
			hijos = T.children(p).iterator();
			i = 19;
			
			while (hijos.hasNext()) {
				p = hijos.next();
				assertTrue("addFirstChild() no funciona correctamente", p.element().equals(i));
				i--;
			}
			assertTrue("addFirstChild() no funciona correctamente", i == 9);
		
		} catch (InvalidPositionException e) {
			fail("addFirstChild() no deberÃ­a lanzar la excepciÃ³n InvalidPositionException con una posiciÃ³n vÃ¡lida.");
		}
	}
	
	/* _______________________TESTS METODO addLastChild()_____________________________*/
	
	@Test
	public void addLastChild() {
		java.util.Iterator<Position<Integer>> hijos;
		Position<Integer> p = null;
		int i;
		
		// Agrego hijos a la raÃ­z
		try {
			
			T.createRoot(1);
			p = T.root();
			
			T = getTree();
			T.addLastChild(p, 2);
			
			fail("addLastChild() deberÃ­a lanzar la excepciÃ³n InvalidPositionException cuando el Ã¡rbol estÃ¡ vacÃ­o");
		
		} catch (InvalidOperationException e) {
			fail("createRoot() no deberÃ­a lanzar la excepciÃ³n InvalidOperationException para un Ã¡rbol vacÃ­o.");
		
		} catch (EmptyTreeException e) {
			fail("root() no deberÃ­a lanzar la excepciÃ³n EmptyTreeException para un Ã¡rbol no vacÃ­o.");
		
		} catch (InvalidPositionException e) {
			// SerÃ­a lo que debe suceder
		}
		
		try {
			
			T.createRoot(1);
			for (i = 2; i < 10; i++) {
				T.addLastChild(T.root(), i);
				assertTrue("addLastChild() no actualiza correctamente el size ", T.size() == i);
			}
			chequearPadre(T.root());
		
		} catch (InvalidPositionException e) {
			fail("addLastChild() no deberÃ­a lanzar la excepciÃ³n InvalidPositionException con una posiciÃ³n vÃ¡lida.");
		
		} catch (EmptyTreeException e) {
			fail("root() no deberÃ­a lanzar la excepciÃ³n EmptyTreeException para un Ã¡rbol no vacÃ­o.");
		
		} catch (InvalidOperationException e) {
			fail("createRoot() no deberÃ­a lanzar la excepciÃ³n InvalidOperationException para un Ã¡rbol vacÃ­o.");
		}
		
		try {
			
			hijos = T.children(T.root()).iterator();
			i = 2;
			while (hijos.hasNext()) {
				p = hijos.next();
				assertTrue("addLastChild() no funciona correctamente", p.element().equals(i));
				i++;
			}
			assertTrue("addLastChild() no funciona correctamente", i == 10);
		
		} catch (InvalidPositionException e) {
			fail("addLastChild() no deberÃ­a lanzar la excepciÃ³n InvalidPositionException con una posiciÃ³n vÃ¡lida.");
		
		} catch (EmptyTreeException e) {
			fail("root() no deberÃ­a lanzar la excepciÃ³n EmptyTreeException para un Ã¡rbol no vacÃ­o.");
		}
		
		// Agrego hijos al hijo extremo izquierdo de la raiz.
		try {
			
			for (i = 10; i < 20; i++) {
				T.addLastChild(p, i);
				assertTrue("addLastChild() no actualiza correctamente el size ", T.size() == i);
			}
			chequearPadre(T.root());
		
		} catch (InvalidPositionException e) {
			fail("addLastChild() no deberÃ­a lanzar la excepciÃ³n InvalidPositionException con una posiciÃ³n vÃ¡lida.");
		
		} catch (EmptyTreeException e) {
			fail("root() no deberÃ­a lanzar la excepciÃ³n EmptyTreeException para un Ã¡rbol no vacÃ­o.");
		}

		try {
			
			hijos = T.children(p).iterator();
			i = 10;
			
			while (hijos.hasNext()) {
				p = hijos.next();
				assertTrue("addLastChild() no funciona correctamente", p.element().equals(i));
				i++;
			}
			assertTrue("addFirstChild() no funciona correctamente", i == 20);

		} catch (InvalidPositionException e) {
			fail("addFirstChild() no deberÃ­a lanzar la excepciÃ³n InvalidPositionException con una posiciÃ³n vÃ¡lida.");
		}

	}
	
	/* _______________________TESTS METODO addBefore()_____________________________*/
	
	@Test
	public void addBefore() {
		Position<Integer> h1, h2, h3, h5, padre, hijo;
		int tres = 0;
		PositionList<Integer> hijos, hijosRoot, hijosH1, hjsH1;
		
		h1 = h2 = h3 = h5 = padre = hijo = null;
		
		try {
			
			T.createRoot(1);
			T.addBefore(null, T.root(), 2);
			fail("addBefore() deberÃ­a lanzar la excepciÃ³n InvalidPositionException con una posiciÃ³n invÃ¡lida.");

		} catch (InvalidOperationException e1) {
			fail("createRoot() no deberÃ­a lanzar la excepciÃ³n InvalidOperationException para un Ã¡rbol vacÃ­o.");
		
		} catch (EmptyTreeException e1) {
			fail("root() no deberÃ­a lanzar la excepciÃ³n EmptyTreeException para un Ã¡rbol no vacÃ­o");
		
		} catch (InvalidPositionException e1) {
			// SerÃ­a lo que debe suceder
		}
		
		try {
			T = getTree();
			T.createRoot(4);
			
			padre = T.root();
			hijo = T.addFirstChild(padre, 5);
			
			T = getTree();
			T.addBefore(padre, hijo, 6);
			fail("addBefore() deberÃ­a lanzar la excepcion InvalidPositionException cuando el Ã¡rbol esta vacÃ­o");
		
		} catch (InvalidPositionException e1) {
			// SerÃ­a lo que debe suceder
		
		} catch (InvalidOperationException e) {
			fail("addFirstChild() no deberÃ­a lanzar una excepciÃ³n InvalidOperationException para un Ã¡rbol vacÃ­o");
		
		} catch (EmptyTreeException e) {
			fail("root() no deberÃ­a lanzar una excepcion EmptyTreeException en un arbol no vacÃ­o");
		}
		
		try {

			T = getTree();
			T.createRoot(3);
			
			padre = T.root();
			
			T = getTree();
			T.createRoot(1);
			
			h1 = T.addFirstChild(T.root(), 2);
		
		} catch (InvalidPositionException e1) {
			fail("addFirstChild() no deberÃ­a lanzar la excepciÃ³n InvalidPositionException para una posiciÃ³n vÃ¡lida.");
		
		} catch (InvalidOperationException e1) {
			fail("createRoot() no deberÃ­a lanzar la excepciÃ³n InvalidOperationException para un Ã¡rbol vacÃ­o.");
		
		} catch (EmptyTreeException e1) {
			fail("root() no deberÃ­a lanzar la excepciÃ³n EmptyTreeException para un Ã¡rbol no vacÃ­o");
		}
		
		try {
		
			T.addBefore(padre, h1, 4);
			fail("addBefore() deberÃ­a lanzar la excepcion InvalidPositionException cuando el nodo primer argumento no es el padre del nodo segundo argumento");
		
		} catch (InvalidPositionException e1) {
			// SerÃ­a lo que debe suceder
		}

		try {
			
			T = getTree();
			T.createRoot(1);
			
			h5 = T.addFirstChild(T.root(), 2);
			h1 = T.addBefore(T.root(), h5, 3);
			chequearPadre(T.root());
			
			// prueba para 3 como primer hijo de root
			tres = T.children(T.root()).iterator().next().element();
			assertTrue("addBefore() no funciona correctamente", tres == 3);
			
			h2 = T.addFirstChild(h1, 4);
			h3 = T.addBefore(T.root(), h1, 6);
			
			T.addBefore(T.root(), h5, 7);
			T.addBefore(T.root(), h3, 9);
			T.addBefore(h1, h2, 5);
			chequearPadre(T.root());
			
			// caso de prueba para los hijos de root
			hijosRoot = new DoubleLinkedList<Integer>();
			hijosRoot.addFirst(2);
			hijosRoot.addFirst(7);
			hijosRoot.addFirst(3);
			hijosRoot.addFirst(6);
			hijosRoot.addFirst(9);
			
			hijos = new DoubleLinkedList<Integer>();
			for (Position<Integer> n : T.children(T.root())) {
				hijos.addLast(n.element());
			}
			
			assertTrue("addBefore() no funciona correctamente", hijos.size() == hijosRoot.size());
			
			while (!hijos.isEmpty()) {
				assertTrue("addBefore() no funciona correctamente", hijos.first().element().equals(hijosRoot.first().element()));
				hijos.remove(hijos.first());
				hijosRoot.remove(hijosRoot.first());
			}
			
			// caso de prueba para los hijos de h1
			hijosH1 = new DoubleLinkedList<Integer>();
			hijosH1.addFirst(4);
			hijosH1.addFirst(5);
			
			hjsH1 = new DoubleLinkedList<Integer>();
			for (Position<Integer> n : T.children(h1)) {
				hjsH1.addLast(n.element());
			}
			
			assertTrue("addBefore() no funciona correctamente", hjsH1.size() == hijosH1.size());
			
			while (!hjsH1.isEmpty()) {
				assertTrue("addBefore() no funciona correctamente", hjsH1.first().element().equals(hijosH1.first().element()));
				hjsH1.remove(hjsH1.first());
				hijosH1.remove(hijosH1.first());
			}
			
		} catch (TDALista.InvalidPositionException e) {
			fail("remove() no deberÃ­a lanzar InvalidPositionException con una posiciÃ³n de lista vÃ¡lida");
		
		} catch (TDALista.EmptyListException e1){
			fail("los mÃ©todos first() o last() no deberÃ­an lanzar la excepciÃ³n EmptyListException para una Lista con elementos");
		
		}catch (InvalidPositionException e1) {
			fail("addBefore(), children(), addFirstChild() no deberÃ­an lanzar InvalidPositionException con una posiciÃ³n vÃ¡lida");
		
		} catch (EmptyTreeException e1) {
			fail("root() no deberÃ­a lanzar la excepciÃ³n EmptyTreeException para un Ã¡rbol no vacÃ­o");
		
		} catch (InvalidOperationException e1) {
			fail("createRoot() no deberÃ­a lanzar la excepciÃ³n InvalidOperationException para un Ã¡rbol vacÃ­o.");
		}
	}
	
	/* _______________________TESTS METODO addAfter()_____________________________*/
	
	@Test
	public void addAfter() {
		java.util.Iterator<Position<Integer>> it; 
		Position<Integer> padre, hijo, h1, h2, h3, h5;
		PositionList<Integer> hijos, hijosH1, hjsH1, hijosRoot;
		
		padre = hijo = h1 = null;
		
		try {
			
			T.createRoot(1);
			T.addAfter(null, T.root(), 2);
			
			fail("addAfter() deberÃ­a lanzar la excepciÃ³n InvalidPositionException con una posiciÃ³n invÃ¡lida.");
		
		} catch (InvalidOperationException e1) {
			fail("createRoot() no deberÃ­a lanzar la excepciÃ³n InvalidOperationException para un Ã¡rbol vacÃ­o.");
		
		} catch (EmptyTreeException e1) {
			fail("root() no deberÃ­a lanzar la excepciÃ³n EmptyTreeException para un Ã¡rbol no vacÃ­o");
		
		} catch (InvalidPositionException e1) {
			// SerÃ­a lo debe suceder
		}
		
		try {
		
			T = getTree();
			T.createRoot(4);
			
			padre = T.root();
			hijo = T.addFirstChild(padre, 5);
		
		} catch (InvalidPositionException e1) {
			fail("addFirstChild() no deberÃ­a lanzar la excepciÃ³n InvalidPositionException para una posiciÃ³n vÃ¡lida");
		
		} catch (InvalidOperationException e) {
			fail("addFirstChild() no deberÃ­a lanzar una excepciÃ³n InvalidOperationException para un Ã¡rbol vacÃ­o");
		
		} catch (EmptyTreeException e) {
			fail("root() no deberÃ­a lanzar una excepcion EmptyTreeException en un arbol no vacÃ­o");
		}

		try {
			
			T = getTree();
			T.addAfter(padre, hijo, 6);
			fail("addAfter() deberÃ­a lanzar la excepcion InvalidPositionException cuando el Ã¡rbol esta vacÃ­o");
		
		} catch (InvalidPositionException e1) {
			// SerÃ­a lo debe suceder
		}

		try {
			
			T = getTree();
			T.createRoot(3);
			
			padre = T.root();
			
			T = getTree();
			T.createRoot(1);
			h1 = T.addFirstChild(T.root(), 2);
		
		} catch (InvalidPositionException e1) {
			fail("addFirstChild() no deberÃ­a lanzar la excepciÃ³n InvalidPositionException para una posiciÃ³n vÃ¡lida");
		
		} catch (InvalidOperationException e) {
			fail("addFirstChild() no deberÃ­a lanzar una excepciÃ³n InvalidOperationException para un Ã¡rbol vacÃ­o");
		
		} catch (EmptyTreeException e) {
			fail("root() no deberÃ­a lanzar una excepcion EmptyTreeException en un arbol no vacÃ­o");
		}
		
		try {
		
			T.addAfter(padre, h1, 4);
			fail("addAfter() deberÃ­a lanzar la excepcion InvalidPositionException cuando el nodo primer argumento no es el padre del nodo segundo argumento");
		
		} catch (InvalidPositionException e1) {
			// SerÃ­a lo que debe suceder
		}
		
		try {
			
			T = getTree();
			T.createRoot(1);
			
			h5 = T.addFirstChild(T.root(), 2);
			h1 = T.addAfter(T.root(), h5, 3);
			
			// prueba para 3 como primer hijo de root
			it = T.children(T.root()).iterator();
			assertTrue("addAfter() no funciona correctamente", it.next().element() == 2);
			assertTrue("addAfter() no funciona correctamente", it.next().element() == 3);
			
			h2 = T.addFirstChild(h1, 4);
			h3 = T.addAfter(T.root(), h1, 6);
			
			T.addAfter(T.root(), h5, 7);
			T.addAfter(T.root(), h3, 9);
			T.addAfter(h1, h2, 5);
			chequearPadre(T.root());
			
			// caso de prueba para los hijos de root
			hijosRoot = new DoubleLinkedList<Integer>();
			hijosRoot.addFirst(2);
			hijosRoot.addFirst(7);
			hijosRoot.addFirst(3);
			hijosRoot.addFirst(6);
			hijosRoot.addFirst(9);
			
			hijos = new DoubleLinkedList<Integer>();
			for (Position<Integer> n : T.children(T.root())) {
				hijos.addFirst(n.element());
			}
			
			assertTrue("addAfter() no funciona correctamente", hijos.size() == hijosRoot.size());
			
			while (!hijos.isEmpty()) {
				assertTrue("addAfter() no funciona correctamente", hijos.first().element().equals(hijosRoot.first().element()));
				hijos.remove(hijos.first());
				hijosRoot.remove(hijosRoot.first());
			}
			
			// caso de prueba para los hijos de h1
			hijosH1 = new DoubleLinkedList<Integer>();
			hijosH1.addFirst(5);
			hijosH1.addFirst(4);
			
			hjsH1 = new DoubleLinkedList<Integer>();
			for (Position<Integer> n : T.children(h1)) {
				hjsH1.addLast(n.element());
			}
			
			assertTrue("addAfter() no funciona correctamente", hjsH1.size() == hijosH1.size());
			
			while (!hjsH1.isEmpty()) {
				assertTrue("addAfter() no funciona correctamente", hjsH1.first().element().equals(hijosH1.first().element()));
				hjsH1.remove(hjsH1.first());
				hijosH1.remove(hijosH1.first());
			}
			
		} catch (TDALista.InvalidPositionException e1) {
			fail("remove() no deberÃ­a lanzar InvalidPositionException con una posiciÃ³n vÃ¡lida");
		
		} catch (TDALista.EmptyListException e1){
			fail("los mÃ©todos first() o last() no deberÃ­a lanzar la excepciÃ³n EmptyListException para una Lista con elementos");
		
		} catch (InvalidPositionException e1) {
			fail("addAfter(), children(), remove() o addFirstChild() no deberÃ­a lanzar InvalidPositionException con una posiciÃ³n vÃ¡lida");
		
		} catch (EmptyTreeException e1) {
			fail("root() no deberÃ­a lanzar la excepciÃ³n EmptyTreeException para un Ã¡rbol no vacÃ­o");
		
		} catch (InvalidOperationException e1) {
			fail("createRoot() no deberÃ­a lanzar la excepciÃ³n InvalidOperationException para un Ã¡rbol vacÃ­o.");
		}
	}
	
	/* _______________________TESTS METODO removeExternal()_____________________________*/
	
	@Test
	public void removeExternal() {
		java.util.Iterator<Position<Integer>> hijos;
		Position<Integer> h1, h3;
		Position<Integer> nodo;
		
		try {

			T.createRoot(1);
			nodo = T.root();
			
			T = getTree();
			T.removeExternalNode(nodo);
			fail("removeExternalNode() deberÃ­a lanzar una excepciÃ³n InvalidPositionException cuando el Ã¡rbol esta vacio");
		
		} catch (InvalidPositionException e) {
			// SerÃ­a lo que debe suceder
			
		} catch (InvalidOperationException e) {
			fail("createRoot() no deberÃ­a lanzar InvaliOperationException en un Ã¡rbol vacÃ­o");
		
		} catch (EmptyTreeException e) {
			fail("root() no deberÃ­a lanzar EmptyTreeExceptionen un Ã¡rbol no vacÃ­o");
		}
		
		try {
		
			T = getTree();
			T.createRoot(1);
			T.addFirstChild(T.root(), 2);

		} catch (InvalidPositionException e) {
			fail("addFirstChild() no deberÃ­a lanzar la excepciÃ³n InvalidPositionException con una posiciÃ³n vÃ¡lida.");
		
		} catch (InvalidOperationException e) {
			fail("createRoot() no deberÃ­a lanzar InvaliOperationException en un Ã¡rbol vacÃ­o");
		
		} catch (EmptyTreeException e) {
			fail("root() no deberÃ­a lanzar EmptyTreeException en un Ã¡rbol no vacÃ­o");
		}
		
		try {
		
			T.removeExternalNode(T.root());
			fail("removeExternalNode() deberÃ­a lanzar una excepciÃ³n InvalidPositionException cuando su argumento es un nodo interno ");
		
		} catch (InvalidPositionException e) {
			// SerÃ­a lo debe suceder
			
		} catch (EmptyTreeException e) {
			fail("root() no deberÃ­a lanzar EmptyTreeException en un Ã¡rbol no vacÃ­o");
		}
		
		try {
			
			T = getTree();
			T.createRoot(1);
			T.removeExternalNode(T.root());
		
		} catch (InvalidPositionException e) {
			fail("removeExternalNode() no deberÃ­a lanzar un excepciÃ³n InvalidPositionException cuando el argumento es la raiz sin hijos");
		
		} catch (InvalidOperationException e) {
			fail("createRoot() no deberÃ­a lanzar InvaliOperationException en un Ã¡rbol vacÃ­o");
		
		} catch (EmptyTreeException e) {
			fail("root() no deberÃ­a lanzar EmptyTreeException en un Ã¡rbol no vacÃ­o");
		}
		
		try {

			T = getTree();
			T.createRoot(1);
			h1 = T.addFirstChild(T.root(), 2);
			
			T.addFirstChild(T.root(), 3);
			h3 = T.addFirstChild(T.root(), 4);
			
			T.removeExternalNode(h1);
			
			// caso de prueba para remover el primer hijo de tres siendo este un
			// nodo externo
			assertTrue("removeExternal() no funciona correctamente", T.children(T.root()).iterator().next().element() == 4);
			T.removeExternalNode(h3);
			
			// caso de prueba para remover el segundo hijo de dos siendo este un
			// nodo externo
			hijos = T.children(T.root()).iterator();
			assertTrue("removeExternal() no funciona correctamente", hijos.next().element() == 3);
			assertTrue("removeExternal() no funciona correctamente", hijos.hasNext() == false);
			
		} catch (InvalidOperationException e) {
			fail("createRoot() no deberÃ­a lanzar InvaliOperationException en un Ã¡rbol vacÃ­o");
		
		} catch (EmptyTreeException e) {
			fail("root() no deberÃ­a lanzar EmptyTreeException en un Ã¡rbol no vacÃ­o");
		
		} catch (InvalidPositionException e) {
			fail("addFirstChild(), removeExternalNode() o children() no deberÃ­an lanzar InvalidPositionException al recibir como argumento la raiz");
		}
	}
	
	/* _______________________TESTS METODO removeInternal()_____________________________*/
	
	@Test
	public void removeInternal() {
		java.util.Iterator<Position<Integer>> it;
		TDALista.Position<Integer> p, p1, p2;
		Position<Integer> nodo, pos, raiz, hijo;
		PositionList<Integer> lista;
		boolean paso = false;
		int i = 0;
		
		// Arbol VacÃ­o
		try {
			
			T.createRoot(1);
			nodo = T.root();
			
			T = getTree();
			T.removeInternalNode(nodo);
			fail("removeInternalNode() deberÃ­a lanzar una excepciÃ³n InvalidPositionException cuando el Ã¡rbol esta vacio");
		
		} catch (InvalidPositionException e) {
			// SerÃ­a lo debe pasar
			
		} catch (InvalidOperationException e) {
			fail("root() no deberÃ­a lanzar InvaliOperationException en un Ã¡rbol no vacÃ­o");
		
		} catch (EmptyTreeException e) {
			fail("createRoot() no deberÃ­a lanzar InvaliOperationException en un Ã¡rbol vacÃ­o");
		}
		
		// Raiz con hijos
		try {
			
			cargarArbol(T);
			i = T.size();
			
			T.removeInternalNode(T.root());
			fail("removeInternalNode() deberÃ­a lanzar una excepciÃ³n InvalidPositionException cuando su argumento es la raiz con hijos ");

		} catch (InvalidPositionException e) {
			assertTrue("RemoveInternal no funciona correctamente",T.size() == i);
			
		} catch (EmptyTreeException e) {
			fail("root() no deberÃ­a lanzar InvaliOperationException en un Ã¡rbol no vacÃ­o");
		}
		
		//RaÃ­z con 1 solo hijo
		T = getTree();
		cargarArbol3(T);
		raiz = null;
		hijo = null;
		
		try{
			
			raiz = T.root();
			hijo = T.children(T.root()).iterator().next();
		
		}catch (EmptyTreeException e){
			fail("children() deberÃ­a lanzar una excepciÃ³n EmptyTreeException cuando el Ã¡rbol no estÃ¡ vacÃ­o");
		
		}catch (InvalidPositionException e){
			fail("children() no deberÃ­a lanzar una excepciÃ³n InvalidPositionException para una posiciÃ³n vÃ¡lida");
		}
		
		try{
			
			T.removeInternalNode(raiz);
			raiz = T.parent(hijo);
			fail("El mÃ©todo parent deberÃ­a haber lanzado la excepciÃ³n BoundaryViolationException al intentar eliminar la raÃ­z del Ã¡rbol.");
			
		}catch(InvalidPositionException e){
			fail("El mÃ©todo removeInternalNode no deberÃ­a lanzar esta excepciÃ³n al intentar eliminar la raÃ­z de un Ã¡rbol cuando esta tiene un sÃ³lo hijo.");
			
	    }catch(BoundaryViolationException e){
	    	// SerÃ­a lo de que debe suceder
	    }
		
		// Raiz sin hijos.
		try {
		
			T = getTree();
			T.createRoot(1);
			T.removeInternalNode(T.root());
			fail("removeInternalNode() deberÃ­a lanzar una excepciÃ³n InvalidPositionException cuando su argumento es un nodo externo ");

		} catch (InvalidPositionException e) {
			// SerÃ­a lo que debe suceder
			
		} catch (InvalidOperationException e) {
			fail("createRoot() no deberÃ­a lanzar InvaliOperationException en un Ã¡rbol vacÃ­o");
		
		} catch (EmptyTreeException e) {
			fail("root() no deberÃ­a lanzar InvaliOperationException en un Ã¡rbol no vacÃ­o");
		}
		
		// Nodo interno.
		T = getTree();
		cargarArbol2(T);
		
		// Intento eliminar la raiz
		try {
		
			T.removeInternalNode(T.root());
			fail("removeInternal deberÃ­a lanzar la excepciÃ³n InvalidPositionException al invocarlo con la raÃ­z con hijos.");
		
		} catch (InvalidPositionException e) {
			// SerÃ­a lo que debe suceder
			
		} catch (EmptyTreeException e) {
			fail("root() no deberÃ­a lanzar la excepciÃ³n EmptyTreeException para un Ã¡rbol no vacÃ­o.");
		}
		
		// Intento eliminar un nodo externo
		try {
			
			it = T.children(T.root()).iterator();
			paso = true;
			T.removeInternalNode(it.next());
			fail("removeInternalNode() deberÃ­a haber lanzado la excepciÃ³n InvalidPositionException");
		
		} catch (EmptyTreeException e) {
			fail("root() no deberÃ­a lanzar la excepciÃ³n EmptyTreeException para un Ã¡rbol no vacÃ­o.");
		
		} catch (InvalidPositionException e) {
			if (!paso) {
				fail("children() no deberÃ­a lanzar la excepciÃ³n InvalidPositionException para una posiciÃ³n vÃ¡lida.");
			}
		}
		
		// Intento eliminar nodos internos.
		lista = new DoubleLinkedList<Integer>();
		for (i = 1; i <= 12; i++) {
			lista.addLast(i);
		}
		
		chequearHijosNiveles2(lista);
		
		try {
	
			it = T.positions().iterator();
			pos = it.next();
			
			while ((it.hasNext()) && (!pos.element().equals(5))) {
				pos = it.next();
			}
			
			T.removeInternalNode(pos);
			chequearPadre(T.root());
			lista = new DoubleLinkedList<Integer>();
			
			for (i = 1; i <= 4; i++) {
				lista.addLast(i);
			}
			
			p1 = lista.last();
			
			lista.addLast(9);
			lista.addLast(10);
			
			p = lista.last();
			for (i = 6; i <= 8; i++) {
				lista.addLast(i);
			}
			
			p2 = lista.last();
			
			lista.addLast(12);
			lista.addLast(11);
			
			chequearHijosNiveles2(lista);
			
			// elimino el nodo 12
			lista.set(p, 12);
			lista.remove(lista.prev(lista.last()));
			
			it = T.positions().iterator();
			pos = it.next();
			while ((it.hasNext()) && (!pos.element().equals(10))) {
				pos = it.next();
			}
			
			T.removeInternalNode(pos);
			chequearPadre(T.root());
			chequearHijosNiveles2(lista);
			
			// elimino el nodo 4
			lista.set(p1, 8);
			lista.set(p2, 11);
			
			lista.remove(lista.last());
			it = T.positions().iterator();
			
			pos = it.next();
			while ((it.hasNext()) && (!pos.element().equals(4))) {
				pos = it.next();
			}
			
			T.removeInternalNode(pos);
			chequearPadre(T.root());
			chequearHijosNiveles2(lista);
			
			// elimino el nodo externo 2
			it = T.positions().iterator();
			pos = it.next();
			
			while ((it.hasNext()) && (!pos.element().equals(2))) {
				pos = it.next();
			}
			
			T.removeExternalNode(pos);
			it = T.children(T.root()).iterator();
			
			pos = it.next();
			assertTrue("error al eliminar nodo externo", pos.element().equals(3));
			
			T.removeInternalNode(pos);
			chequearPadre(T.root());
			
			lista = new DoubleLinkedList<Integer>();
			lista.addLast(1);
			lista.addLast(6);
			lista.addLast(7);
			lista.addLast(8);
			lista.addLast(9);
			lista.addLast(12);
			lista.addLast(11);
			chequearHijosNiveles2(lista);
		
		} catch (TDALista.InvalidPositionException e) {
			fail("remove(), prev(), o set() no deberÃ­an lanzar la excepciÃ³n InvalidPositionException para una posiciÃ³n vÃ¡lida.");
		
		} catch (TDALista.EmptyListException e1){
			fail("los mÃ©todos first() o last() no deberÃ­a lanzar la excepciÃ³n EmptyListException para una Lista con elementos");
		
		} catch (TDAArbol.InvalidPositionException e) {
			fail("removeInternalNode(), children(), removeExternalNode(), remove(), o set() no deberÃ­a lanzar la excepciÃ³n InvalidPositionException para una posiciÃ³n vÃ¡lida.");
		
		} catch (EmptyTreeException e) {
			fail("root() no deberÃ­a lanzar la excepciÃ³n EmptyTreeException para un Ã¡rbol no vacÃ­o.");
		
		} catch (TDALista.BoundaryViolationException e) {
			fail("prev() no deberÃ­a lanzar la excepciÃ³n BoundaryViolationException con una posiciÃ³n .");
		}
	}
	
	/* _______________________TESTS METODO remove()_____________________________*/
	
	@Test
	public void remove() {
		java.util.Iterator<Position<Integer>> it;
		PositionList<Position<Integer>> lista;
		Position<Integer> pos, nodo;
		int i = 0;
		
		// Ã�rbol vacÃ­o
		try {
		
			T.createRoot(1);
			nodo = T.root();
			
			T = getTree();
			T.removeNode(nodo);
			fail("removeNode() deberÃ­a lanzar una excepciÃ³n InvalidPositionException cuando el Ã¡rbol esta vacio");
		
		} catch (InvalidPositionException e) {
			// SerÃ­a lo que debe suceder
			
		} catch (InvalidOperationException e) {
			fail("createRoot() no deberÃ­a lanzar InvaliOperationException en un Ã¡rbol vacÃ­o");
		
		} catch (EmptyTreeException e) {
			fail("root() no deberÃ­a lanzar InvaliOperationException en un Ã¡rbol no vacÃ­o");
		}
		
		// Raiz con hijos
		try {
			
			cargarArbol(T);
			i = T.size();
			
			T.removeNode(T.root());
			fail("removeNode() deberÃ­a lanzar una excepciÃ³n InvalidPositionException cuando su argumento es la raiz con hijos ");

		} catch (InvalidPositionException e) {
			assertTrue("removeNode no funciona correctamente", T.size() == i);
		
		} catch (EmptyTreeException e) {
			fail("createRoot() no deberÃ­a lanzar InvaliOperationException en un Ã¡rbol no vacÃ­o");
		}
		
		// Raiz sin hijos.
		try {
		
			T = getTree();
			T.createRoot(1);
			
			T.removeNode(T.root());
			assertTrue("removeNode no funciona correctamente", T.size() == 0);

		} catch (InvalidPositionException e) {
			fail("removeNode() no deberÃ­a lanzar un excepciÃ³n InvalidPositionException cuando el argumento es la raiz sin hijos");
		
		} catch (InvalidOperationException e) {
			fail("createRoot() no deberÃ­a lanzar InvaliOperationException en un Ã¡rbol vacÃ­o");
		
		} catch (EmptyTreeException e) {
			fail("root() no deberÃ­a lanzar InvaliOperationException en un Ã¡rbol no vacÃ­o");
		}
		
		// Elimino todos los nodos del nivel 1
		T = getTree();
		cargarArbol2(T);
		lista = new DoubleLinkedList<Position<Integer>>();
		
		try {
			
			nodosEnNivel(T.root(), 1, 1, lista);
			it = lista.iterator();

			while (it.hasNext()) {
				T.removeNode(it.next());
			}
			
			chequearPadre(T.root());
			it = T.children(T.root()).iterator();
			
			for (i = 6; i < 11; i++) {
				pos = it.next();
				assertTrue("remove() no funciona correctamente", pos.element().equals(i));
				assertTrue("remove() no setea correctamente el padre", T.parent(pos) == T.root());
			}
			
			assertTrue("remove() no funciona correctamente", !it.hasNext());
			
		} catch (EmptyTreeException e) {
			fail("root() no deberÃ­a lanzar EmptyTreeException para un Ã¡rbol no vacÃ­o.");
		
		} catch (InvalidPositionException e) {
			fail("removeNode(), children o parent() no deberÃ­a lanzar InvalidPositionException para una posiciÃ³n vÃ¡lida.");
		
		} catch (BoundaryViolationException e) {
			fail("parent() no deberÃ­a lanzar BoundaryViolationException para una posiciÃ³n distinta a la raÃ­z.");
		}
		
		// Elimino todos los nodos del nivel 1
		try {
		
			lista = new DoubleLinkedList<Position<Integer>>();
			nodosEnNivel(T.root(), 1, 1, lista);
			it = lista.iterator();
			
			while (it.hasNext()) {
				T.removeNode(it.next());
			}
			
			chequearPadre(T.root());
			it = T.children(T.root()).iterator();
			
			for (i = 11; i < 13; i++) {
				pos = it.next();
				assertTrue("remove() no funciona correctamente", pos.element().equals(i));
				assertTrue("remove() no setea correctamente el padre", T.parent(pos) == T.root());
			}
			
			assertTrue("remove() no funciona correctamente", !it.hasNext());
			
		} catch (EmptyTreeException e) {
			fail("root() no deberÃ­a lanzar EmptyTreeException para un Ã¡rbol no vacÃ­o.");
		
		} catch (InvalidPositionException e) {
			fail("removeNode(),children o parent() no deberÃ­a lanzar InvalidPositionException para una posiciÃ³n vÃ¡lida.");
		
		} catch (BoundaryViolationException e) {
			fail("parent() no deberÃ­a lanzar BoundaryViolationException para una posiciÃ³n distinta a la raÃ­z.");
		}

		try {
			
			lista = new DoubleLinkedList<Position<Integer>>();
			nodosEnNivel(T.root(), 1, 1, lista);
			
			it = lista.iterator();
			while (it.hasNext()) {
				T.removeNode(it.next());
			}
			
			chequearPadre(T.root());
			it = T.children(T.root()).iterator();
			
			assertTrue("remove() no funciona correctamente", !it.hasNext());
			assertTrue("remove() no funciona correctamente", T.size() == 1);
		
		} catch (EmptyTreeException e) {
			fail("root() no deberÃ­a lanzar EmptyTreeException para un Ã¡rbol no vacÃ­o.");
		
		} catch (InvalidPositionException e) {
			fail("removeNode(), children o parent() no deberÃ­a lanzar InvalidPositionException para una posiciÃ³n vÃ¡lida.");
		}
	}
	
	
	private void cargarArbol(Tree<Integer> Arbol) {
		Position<Integer> h1, h2, h3, h4, h5;
		h1 = h2 = h3 = h4 = h5 = null;
		
		try {
			
			Arbol.createRoot(1);
			h1 = Arbol.addFirstChild(Arbol.root(), 2);
			h1 = Arbol.addAfter(Arbol.root(), h1, 3);
			h2 = Arbol.addAfter(Arbol.root(), h1, 6);
			h3 = Arbol.addAfter(Arbol.root(), h2, 9);
			Arbol.addFirstChild(h1, 4);
			Arbol.addLastChild(h1, 5);
			h4 = Arbol.addFirstChild(h2, 7);
			Arbol.addFirstChild(h4, 8);
			Arbol.addFirstChild(h3, 10);
			h5 = Arbol.addLastChild(h3, 11);
			Arbol.addFirstChild(h5, 12);

		} catch (InvalidPositionException e) {
			fail("Al invocar addFirstChild(), addLastChild() o addAfter() con una posiciÃ³n vÃ¡lida lanza la excepciÃ³n InvalidPositionException");
		
		} catch (EmptyTreeException e2) {
			fail("Al solicitar la raÃ­z de un Ã¡rbol no vacÃ­o lanza la excepciÃ³n EmptyTreeException");
		
		} catch (InvalidOperationException e3) {
			fail("Al invocar createRoot() con un Ã¡rbol vacÃ­o lanza la excepciÃ³n InvalidOperationException");
		}
	}	
	
	private void cargarArbol3(Tree<Integer> Arbol) {
		Position<Integer> h1;
		h1 = null;
		
		try {
		
			Arbol.createRoot(1);
			h1 = Arbol.addFirstChild(Arbol.root(), 2);
			Arbol.addFirstChild(h1, 6);
		
		} catch (InvalidPositionException e) {
			fail("Al invocar addFirstChild(), addLastChild() o addAfter() con una posiciÃ³n vÃ¡lida lanza la excepciÃ³n InvalidPositionException");
		
		} catch (EmptyTreeException e2) {
			fail("Al solicitar la raÃ­z de un Ã¡rbol no vacÃ­o lanza la excepciÃ³n EmptyTreeException");
		
		} catch (InvalidOperationException e3) {
			fail("Al invocar createRoot() con un Ã¡rbol vacÃ­o lanza la excepciÃ³n InvalidOperationException");
		}
	}

	private void cargarArbol2(Tree<Integer> Arbol) {
		Position<Integer> h1, h2, h3, h4, h5;
		h1 = h2 = h3 = h4 = h5 = null;
		
		try {
		
			Arbol.createRoot(1);
			h1 = Arbol.addFirstChild(Arbol.root(), 2);
			h1 = Arbol.addAfter(Arbol.root(), h1, 3);
			h2 = Arbol.addAfter(Arbol.root(), h1, 4);
			h3 = Arbol.addAfter(Arbol.root(), h2, 5);
			Arbol.addFirstChild(h1, 6);
			Arbol.addLastChild(h1, 7);
			h4 = Arbol.addFirstChild(h2, 8);
			Arbol.addFirstChild(h4, 11);
			Arbol.addFirstChild(h3, 9);
			h5 = Arbol.addLastChild(h3, 10);
			Arbol.addFirstChild(h5, 12);

		} catch (InvalidPositionException e) {
			fail("Al invocar addFirstChild(), addLastChild() o addAfter() con una posiciÃ³n vÃ¡lida lanza la excepciÃ³n InvalidPositionException");
		
		} catch (EmptyTreeException e2) {
			fail("Al solicitar la raÃ­z de un Ã¡rbol no vacÃ­o lanza la excepciÃ³n EmptyTreeException");
		
		} catch (InvalidOperationException e3) {
			fail("Al invocar createRoot() con un Ã¡rbol vacÃ­o lanza la excepciÃ³n InvalidOperationException");
		}
	}

	private void nodosEnNivel(Position<Integer> v, int nivel, int j, PositionList<Position<Integer>> lista) {
		java.util.Iterator<Position<Integer>> hijos = null;
		Position<Integer> p;
		
		try {
		
			hijos = T.children(v).iterator();
		
		} catch (InvalidPositionException e) {
			fail("childen() no deberÃ­a lanzar InvalidPositionException con una posiciÃ³n vÃ¡lida.");
		}

		while (hijos.hasNext()) {
			p = hijos.next();
			if (nivel == j) {
				lista.addLast(p);
			}
			nodosEnNivel(p, nivel + 1, j, lista);
		}
	}

	private void chequearPadre(Position<Integer> v) {
		java.util.Iterator<Position<Integer>> hijos = null;
		Position<Integer> p;
		
		try {
			
			hijos = T.children(v).iterator();
		
		} catch (InvalidPositionException e) {
			fail("childen() no deberÃ­a lanzar InvalidPositionException con una posiciÃ³n vÃ¡lida.");
		}

		while (hijos.hasNext()) {
			p = hijos.next();
			
			try {
				
				assertTrue("parent() no funciona correctamente", T.parent(p) == v);
			
			} catch (InvalidPositionException e) {
				fail("parent() no deberÃ­a lanzar InvalidPositionException con una posiciÃ³n vÃ¡lida.");
			
			} catch (BoundaryViolationException e) {
				fail("parent() no deberÃ­a lanzar BoundaryViolationException con una posiciÃ³n distinta a la de la raÃ­z.");
			}
			chequearPadre(p);
		}
	}

	private void chequearHijosNiveles2(PositionList<Integer> lista) {
		java.util.Iterator<Integer> it;
		java.util.Iterator<Position<Integer>> hijos;
		java.util.Queue<Position<Integer>> cola;
		Position<Integer> p;
		int el = 0;
		
		it = lista.iterator();
		cola = new java.util.LinkedList<Position<Integer>>();
		hijos = null;
		
		try {
		
			p = T.root();

		} catch (EmptyTreeException e1) {
			fail("root() no deberÃ­a lanzar la excepciÃ³n EmptyTreeException con un Ã¡rbol con elementos.");
			p = null;
		}
		
		if (p != null) {
			cola.add(p);
			cola.add(null);
			while (!cola.isEmpty()) {
				try {
					
					p = cola.remove();
				
				} catch (java.util.NoSuchElementException e) {
					fail("dequeue() no deberÃ­a lanzar EmptyQueueException para una cola con elementos.");
				}
				
				if (p == null) {
					if (!cola.isEmpty()) {
						cola.add(null);
					}
				} else {
					el = it.next();
					assertTrue("error", p.element().equals(el));

					try {
					
						hijos = T.children(p).iterator();
					
					} catch (InvalidPositionException e) {
						fail("children() no deberÃ­a lanzar la excepciÃ³n InvalidPositionException para una posiciÃ³n vÃ¡lida");
					}
					
					while (hijos.hasNext()) {
						cola.add(hijos.next());
					}
				}
			}
		}
	}
}
