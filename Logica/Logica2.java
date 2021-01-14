package Logica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;

import ABB.ABBNodo;
import ABB.Arbol_binario_busqueda;
import TDAArbol.Arbol_enlazado;
import TDAArbol.BoundaryViolationException;
import TDAArbol.EmptyTreeException;
import TDAArbol.InvalidOperationException;
import TDAArbol.InvalidPositionException;
import TDAArbol.Position;
import TDAArbol.Tree;
import TDAArbolBinario.BinaryTree;
import TDAArbolBinario.LinkedBinaryTree;
import TDACola.Cola_con_enlaces;
import TDACola.Queue;
import TDAColaCP.ColaCP_con_heap;
import TDAColaCP.ColaCP_con_lista;
import TDAColaCP.PriorityQueue;
import TDADiccionario.Diccionario_hash_cerrado;
import TDADiccionario.Dictionary;
import TDADiccionario.Entry;
import TDALista.DoubleLinkedList;
import TDALista.EmptyListException;
import TDALista.PositionList;
import TDAMapeo.InvalidKeyException;
import TDAMapeo.Map;
import TDAMapeo.Mapeo_hash_abierto;
import TDAPila.EmptyStackException;
import TDAPila.Pila_con_enlaces;
/**
 * Modela los metodos propuestos por la catedra.
 * @author Emanuel Orler.
 * @param <E>: Tipo de dato abstracto a utilizar.
 */
@SuppressWarnings("unused")
public class Logica2<E >{
	/**
	 * Crea un par segun un archivo de texto, en el cual su primera componente
	 * es un mapeo que cuenta la cantidad de apariciones de una palabra y 
	 * la segunda componente es un diccionario que almacena las palabras y sus iniciales.
	 * @param path: Ruta absoluta del archivo de texto.
	 * @return p: Par que contiene un mapeo y un diccionario .
	 */
	public static Pair<Map<String,Integer>,Dictionary<Character,String>> estadisticas(String path) {
		String line,word;
		char initial;
		int i,size;
		Map<String,Integer> m = new Mapeo_hash_abierto<String,Integer>();
		Dictionary<Character,String> d = new Diccionario_hash_cerrado<Character,String>();
		Pair<Map<String,Integer>,Dictionary<Character,String>> p = new Pair<Map<String,Integer>,Dictionary<Character,String>>(m,d);
		BufferedReader bf; 
		FileReader fr;
			try {
				fr = new FileReader(path);
				bf = new BufferedReader(fr);
				line = bf.readLine();
				while(line!= null) {
					i=0;
					size=line.length();
					System.out.println(size);
					while(i<size) {
						word = "";
						while(i<size && line.charAt(i)!=' ')
							word = word+line.charAt(i++);
						i++;
						initial = word.charAt(0);
						System.out.print(m.get(word) == null);
						System.out.print("  "+initial+":  ");
						System.out.print(word);
						if(m.get(word) == null) 
							m.put(word, 1);
						else 
							m.put(word, m.get(word)+1);
						if(!pertenece(word,d.findAll(initial))) 
							d.insert(initial, word);
					}
					line = bf.readLine();
				}	
				bf.close();
			} 
			catch (IOException | InvalidKeyException | TDADiccionario.InvalidKeyException e) {
				e.printStackTrace();
			}
		return p;
	}
	/**
	 * Verifica si una palabra ya pertenece a al diccionario.
	 * @param word: Palabra a verificar.
	 * @param iterable: Iterable de palabras.
	 * @return Retorna true si la palabra pertenece false en caso contrario.
	 */
	private static boolean pertenece(String word, Iterable<Entry<Character, String>> iterable) {
		boolean pertenece = false;
		Iterator<Entry<Character, String>> iterador = iterable.iterator();
		while(!pertenece && iterador.hasNext())
			pertenece = iterador.next().getValue().equals(word);
		return pertenece;
	}
	/**
	 * Encuentra y retorna un camino entre dos nodos p1 y p2 que pertencen al arbol t1.
	 * @param <E>: Tipo de dato abstracto a utilizar.
	 * @param t1: Arbol que contiene los nodos p1 y p2.
	 * @param p1: Nodo de inicio del camino.
	 * @param p2: Nodo destino del camino.
	 * @return Retorna una lista de posiciones de los nodos que se recorren desde p1 hasta p2.
	 */
	public static <E> PositionList<Position<E>> camino(Tree<E> t1, Position<E> p1, Position<E> p2){
		PositionList<Position<E>> toReturn = new DoubleLinkedList<Position<E>>();
		Pila_con_enlaces<Position<E>> pathP1 = new Pila_con_enlaces<Position<E>>();
		Pila_con_enlaces<Position<E>> pathP2 = new Pila_con_enlaces<Position<E>>();
		Position<E> middle = null;
		try {
			if(t1!=null && p1!=null && p1!=null && t1.size()>1 && p1!=p2) {
				pathP1 = findPathToRoot(t1,p1,pathP1);
				pathP2 = findPathToRoot(t1,p2,pathP2);
				while(pathP1.top() == pathP2.top()) {
					pathP1.pop();
					middle = pathP2.pop();
				}
				while(!pathP2.isEmpty())
					toReturn.addLast(pathP2.pop());
				toReturn.addFirst(middle);
				while(!pathP1.isEmpty())
					toReturn.addFirst(pathP1.pop());
			}
		} catch (EmptyStackException e) {
			e.printStackTrace();
		}
		return toReturn;
	}
	/**
	 * Encuentra y retorna el camino de desde un nodo de un arbol hasta su raiz.
	 * @param <E>: Tipo de dato abstracto a utilizar.
	 * @param t: Arbol en el cual esta contenido el nodo p.
	 * @param p: Nodo de inicio de la ruta.
	 * @param path: Camino recorrido del nodo.
	 * @return Retorna una pila de posiciones, las cuales indican los nodos recorridos desde p hasta la raiz.
	 */
	private static <E>Pila_con_enlaces<Position<E>> findPathToRoot(Tree<E> t, Position<E> p,Pila_con_enlaces<Position<E>> path) {
		try {
			if(p == t.root())
				path.push(p);
			else {
				path.push(p);
				path = findPathToRoot(t,t.parent(p),path);
			}
		} catch (EmptyTreeException | InvalidPositionException | BoundaryViolationException e) {
			e.printStackTrace();
		}
		return path;
	}
	/**
	 * Crea y retorna un arbol espejado.
	 * @param <E>: Tipo de dato abstracto a utilizar.
	 * @param t1: Arbol a espejar.
	 * @return t2, donde t2 es el arbol espejado de t1. 
	 */
	public static <E>Tree<E> clonar_espejado(Tree<E> t1){
		Tree<E> t2 = new Arbol_enlazado<E>();
		try {
			if(!t1.isEmpty()) {
				t2.createRoot(t1.root().element());
				clonar_espejadoAux(t1,t2,t1.root(),t2.root());
			}
		} catch (InvalidOperationException | EmptyTreeException e) {
			e.printStackTrace();
		}
		return t2;
	}
	/**
	 * Modifica el arbol mirrorTree hasta obtener el arbol espejado de t.
	 * @param <E>: TIpo de dato abstracto a utilizar.
	 * @param t: Arbol a espejar.
	 * @param mirrorTree: Arbol espejado en construccion.
	 * @param nodoT: Nodo padre del sub-arbol de t que se esta espejando.
	 * @param nodoM: Nodo padre del sub-arbol de mirrorTree que se esta espejando.
	 */
	private static <E>void clonar_espejadoAux(Tree<E> t, Tree<E> mirrorTree, Position<E> nodoT, Position<E> nodoM) {
		try {
			for(Position<E>c: t.children(nodoT))
				clonar_espejadoAux(t,mirrorTree,c,mirrorTree.addFirstChild(nodoM, c.element()));
		} catch (InvalidPositionException e) {
			e.printStackTrace();
		}		
	}
	public static <E extends Comparable<E>> void por_niveles(LinkedBinaryTree<E> t) {
		DoubleLinkedList<TDAArbolBinario.Position<E>> current = new DoubleLinkedList<TDAArbolBinario.Position<E>>();
		try {
			if(t.size()!=0) {
				current.addFirst(t.root());
				por_niveles_aux(current,t);
			}
		} catch (TDAArbolBinario.EmptyTreeException e) {
			e.printStackTrace();
		}
	}
	private static <E> void por_niveles_aux(DoubleLinkedList<TDAArbolBinario.Position<E>> current,LinkedBinaryTree<E> t) {
		DoubleLinkedList<TDAArbolBinario.Position<E>> nextLevel = new DoubleLinkedList<TDAArbolBinario.Position<E>>();
		try {
			for(TDAArbolBinario.Position<E> p : current) {
				System.out.print(p.element());
				for(TDAArbolBinario.Position<E> q: t.children(p))
					nextLevel.addLast(q);
			}
			System.out.println();
			por_niveles_aux(nextLevel,t);
		} catch (TDAArbolBinario.InvalidPositionException e) {
			e.printStackTrace();
		}
	}
	
	public static <E extends Comparable<E>> void por_niveles_invertido(LinkedBinaryTree<E> t) {
		DoubleLinkedList<TDAArbolBinario.Position<E>> current = new DoubleLinkedList<TDAArbolBinario.Position<E>>();
		try {
			if(t.size()!=0) {
				current.addFirst(t.root());
				por_niveles_invertido_aux(current,t);
			}
		} catch (TDAArbolBinario.EmptyTreeException e) {
			e.printStackTrace();
		}
	}
	private static <E> void por_niveles_invertido_aux(DoubleLinkedList<TDAArbolBinario.Position<E>> current, LinkedBinaryTree<E> t) {
		DoubleLinkedList<TDAArbolBinario.Position<E>> nextLevel = new DoubleLinkedList<TDAArbolBinario.Position<E>>();
		try {
			for(TDAArbolBinario.Position<E> p : current) {
				for(TDAArbolBinario.Position<E> q: t.children(p))
					nextLevel.addLast(q);
			}
			por_niveles_invertido_aux(nextLevel,t);
			System.out.println();
			for(TDAArbolBinario.Position<E> p : current)
				System.out.print(p.element());
		} catch (TDAArbolBinario.InvalidPositionException e) {
			e.printStackTrace();
		}
	}
	
	public static <E> void inOrder(Tree<E> t) {
		if(!t.isEmpty())
			try {
				inOrderAux(t,t.root());
			}
		catch(EmptyTreeException e) {
			e.printStackTrace();
		}
	}
	private static <E> void inOrderAux(Tree<E> t, Position<E> pos) {
		Iterator<Position<E>> iterador;
		try {
			if(t.isExternal(pos))
				System.out.print(pos.element());
			else {
				iterador = t.children(pos).iterator();
				inOrderAux(t,iterador.next());
				System.out.print(pos.element());
				while(iterador.hasNext())
					inOrderAux(t,iterador.next());
			}
		} catch (InvalidPositionException e) {
			e.printStackTrace();
		}	
	}

	public static <E> void clonar_arbol_perfecto(int h, PositionList<E> list) {
		LinkedBinaryTree<E> t = new LinkedBinaryTree<E>();
		TDALista.Position<E> pos;
		try {
			if(Math.pow(2,h)-1==list.size()&&!list.isEmpty())
				t.createRoot(list.first().element());
				if(h>1) {
					pos = list.next(list.first());
					clonar_arbol_perfectoAux(h-1,t.root(),pos,list,t);
				}
		} catch (TDAArbolBinario.InvalidOperationException | EmptyListException | 
				TDALista.InvalidPositionException | TDALista.BoundaryViolationException | 
				TDAArbolBinario.EmptyTreeException e) {
			e.printStackTrace();
		}
	}
	private static <E> void clonar_arbol_perfectoAux(int h, TDAArbolBinario.Position<E> parent, TDALista.Position<E> pos, PositionList<E> list, LinkedBinaryTree<E> t) {
		try {
			if(h == 1) {
				t.addLeft(parent, pos.element());
				pos = list.next(pos);
				t.addRight(parent, pos.element());
				if(pos!=list.last())
					pos = list.next(pos);
			}
			else {
				t.addLeft(parent, pos.element());
				pos = list.next(pos);
				clonar_arbol_perfectoAux(h-1,t.left(parent),pos,list,t);
				t.addRight(parent, pos.element());
				pos = list.next(pos);
				clonar_arbol_perfectoAux(h-1,t.right(parent),pos,list,t);
			}
		} catch (TDAArbolBinario.InvalidPositionException | TDAArbolBinario.InvalidOperationException
				| TDALista.InvalidPositionException | TDALista.BoundaryViolationException
				| TDAArbolBinario.BoundaryViolationException | EmptyListException e) {
			e.printStackTrace();
		}
	}
	//Se asume que las opeaciones last, remove, next se realizan en O(1).
	//Entrada una lista con elementos no repetidos de tamañao n.
	public static void eliminarRepetidos(PositionList<Integer> l1) {
		TDALista.Position<Integer> current,toRemove;
		try {
			if(!l1.isEmpty()) { 										//--C
				toRemove = l1.first();									//--C
				current = toRemove;										//--C
				while(toRemove!=l1.last()) {							//la sumatoria de 1 hasta n de 		
					while(current!=null) {								//(n-i)* donde i es el numero del ciclo
						current = l1.next(toRemove);					//--C
						if(current.element()==toRemove.element())   	//--C
							l1.remove(current);							//--C
						if(current == l1.last())						//--C
							current = null;								//--C
					}
					if(toRemove!=l1.last())								//--C
						toRemove = l1.next(toRemove);					//--C
				}
			}
		} catch (EmptyListException | TDALista.InvalidPositionException | TDALista.BoundaryViolationException e) {
			e.printStackTrace();
		}
	}
	//T(n) = c1 + sumatoria 1 hasta n((n-i)*c2)
	//T(n) = por propiedad de la suma T(n) es de orden del mayor => T(n) es de orden(n)
	
//	public static <Persona, E> void listadoPeso(Tree<Persona> t) {
//		PriorityQueue<Persona,E> cp = new ColaCP_con_lista<Persona,E>(new Comparador_persona<Persona>());
//		preOrder(t,cp);
//		while(!cp.isEmpty()) {
//			System.out.println(cp.removeMin().toString());
//		}
//	}
	public static <V,E> void preOrder(Tree<E> t,PriorityQueue<E,V> cp) {
		if(!t.isEmpty())
			try {
				preOrderAux(t,t.root(),cp);
			}
		catch(EmptyTreeException e) {
			e.printStackTrace();
		}
	}
	private static <E,V> void preOrderAux(Tree<E> t, Position<E> pos, PriorityQueue<E,V> cp) {
		try {
			cp.insert(pos.element(), null);
			for(Position<E> child: t.children(pos)) 
				preOrderAux(t,child,cp);
		} catch (TDAArbol.InvalidPositionException | TDAColaCP.InvalidKeyException e) {
			e.printStackTrace();
		}
	}
	
//	public static float evaluarExpresion(BinaryTree<String>t1) {
//		float toReturn=0;
//		if(!t1.isEmpty())
//			try {
//				toReturn = evaluarExpresionAux(t1,t1.root());
//			} catch (TDAArbolBinario.EmptyTreeException e) {
//				e.printStackTrace();
//			}
//		return toReturn;
//	}
//	private static float evaluarExpresionAux(BinaryTree<String> t1, TDAArbolBinario.Position<String> nodo) {
//		float toReturn = 0;
//		String elemento = nodo.element();
//		try {
//			if(elemento == "/"||elemento == "*"||elemento == "+"||elemento == "-") {
//				if(elemento == "/" ) 
//					toReturn = evaluarExpresionAux(t1,t1.left(nodo))/evaluarExpresionAux(t1,t1.right(nodo));
//				if(elemento == "*" ) 
//					toReturn = evaluarExpresionAux(t1,t1.left(nodo))*evaluarExpresionAux(t1,t1.right(nodo));
//				if(elemento == "+" ) 
//					toReturn = evaluarExpresionAux(t1,t1.left(nodo))+evaluarExpresionAux(t1,t1.right(nodo));
//				if(elemento == "-" ) 
//					toReturn = evaluarExpresionAux(t1,t1.left(nodo))-evaluarExpresionAux(t1,t1.right(nodo));
//			}
//			else
//				toReturn = Integer.parseInt(nodo.element());
//		} catch (NumberFormatException | TDAArbolBinario.InvalidPositionException
//				| TDAArbolBinario.BoundaryViolationException e) {
//			e.printStackTrace();
//		}
//		return toReturn;
//	}
//	public static float evaluarExpresion2(BinaryTree<String>t1) {
//		float toReturn=0;
//		int i;
//		String expresion = "";
//		if(!t1.isEmpty())
//			try {
//				expresion = inOrder(t1,t1.root(),expresion);
//				i = 0;
//				while(i<expresion.length()) {
//					if(expresion.charAt(i) == '+'||expresion.charAt(i) == '-'||expresion.charAt(i) == '*'||expresion.charAt(i) == '/') {
//				}
//			} catch (TDAArbolBinario.EmptyTreeException e) {
//				e.printStackTrace();
//			}
//		return toReturn;
//	}

    @SuppressWarnings("unused")
	private static String inOrder(Tree<String> t, Position<String> pos,String expresion) {
        Iterator<Position<String>> iterador;
        try {
            if(t.isExternal(pos))
                expresion = pos.element();
            else {
                iterador = t.children(pos).iterator();
                expresion = pos.element();
                while(iterador.hasNext())
                    expresion = expresion + inOrder(t,iterador.next(),expresion);
            }
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        return expresion;
    }
	
}