package TDAGrafo;

import java.util.Iterator;

import TDALista.DoubleLinkedList;
import TDALista.EmptyListException;
import TDALista.InvalidPositionException;
import TDALista.PositionList;

public class Grafo_lista_adyacencia<V,E> implements Graph<V,E> {
	protected PositionList<Vertice<V,E>> nodes;
	protected PositionList<Arco<V,E>> edges;
	
	
	public Grafo_lista_adyacencia() {
		nodes = new DoubleLinkedList<Vertice<V,E>>();
		edges = new DoubleLinkedList<Arco<V,E>>();
	}
	@Override
	public Iterable<Vertex<V>> vertices() {
		DoubleLinkedList<Vertex<V>> iterable= new DoubleLinkedList<Vertex<V>>();
		for(Vertex<V> v: nodes)
			iterable.addLast(v);
		return iterable;
	}

	@Override
	public Iterable<Edge<E>> edges() {
		DoubleLinkedList<Edge<E>> iterable = new DoubleLinkedList<Edge<E>>();
		for(Edge<E> e: edges)
			iterable.addLast(e);
		return iterable;
	}

	@Override
	public Iterable<Edge<E>> incidentEdges(Vertex<V> v) throws InvalidVertexException {
		Vertice<V,E> ver = checkVertex(v);
		DoubleLinkedList<Edge<E>> iterable = new DoubleLinkedList<Edge<E>>();
		for(Edge<E> e: ver.getAdyacentes())
			iterable.addLast(e);
		return iterable;
	}

	@Override
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws InvalidVertexException, InvalidEdgeException {
		Vertice<V,E> ver = checkVertex(v);
		Arco<V,E> arco = checkEdge(e);
		Vertex<V> toReturn;
		if(arco.getV1().equals(ver))
			toReturn = arco.getV2();
		else
			if(arco.getV2().equals(ver))
				toReturn = arco.getV1();
			else
				throw new InvalidEdgeException("El arco no es valido");
		return toReturn;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Vertex<V>[] endvertices(Edge<E> e) throws InvalidEdgeException {
		Arco<V,E> arc = checkEdge(e);
		Vertex<V>[] toReturn = (Vertex<V>[]) new Vertex[2];
		toReturn[0] = arc.getV1();
		toReturn[1] = arc.getV2();
		return null;
	}


	@Override
	public boolean areAdjacent(Vertex<V> v, Vertex<V> w) throws InvalidVertexException {
		Vertice<V,E> v1 = checkVertex(v);
		Vertice<V,E> v2 = checkVertex(w);
		boolean found = false;
		Iterator<Arco<V, E>> iterator = v1.getAdyacentes().iterator();
		while(!found && iterator.hasNext())
			found = iterator.next().getV2().equals(v2);
		return found;
	}


	@Override
	public V replace(Vertex<V> v, V x) throws InvalidVertexException {
		Vertice<V,E> v1 = checkVertex(v);
		V toReturn = v1.element();
		v1.setElement(x);
		return toReturn;
	}

	@Override
	public Vertex<V> insertVertex(V x) {
		Vertice<V,E> v = new Vertice<V,E>(x);
		try {
			nodes.addLast(v);
			v.setPosicionEnNodos(nodes.last());
		} catch (EmptyListException e) {
			e.printStackTrace();
		}
		return v;
	}

	@Override
	public Edge<E> insertEdge(Vertex<V> v, Vertex<V> w, E e) throws InvalidVertexException {
		Vertice<V,E> v1 = checkVertex(v);
		Vertice<V,E> v2 = checkVertex(w);
		Arco<V,E> edge = new Arco<V,E>(e,v1,v2);
		try {
			edges.addLast(edge);
			v1.getAdyacentes().addLast(edge);
			v2.getAdyacentes().addLast(edge);
			edge.setPosicionEnArcos(edges.last());
			edge.setPosicionEnlv1(v1.getAdyacentes().last());
			edge.setPosicionEnlv2(v2.getAdyacentes().last());
		} catch (EmptyListException e1) {
			e1.printStackTrace();
		}
		return edge;
	}


	@Override
	public V removeVertex(Vertex<V> v) throws InvalidVertexException {
		Vertice<V,E> v1 = checkVertex(v);
		V toReturn = v.element();
		try {
			for(Arco<V, E> p: v1.getAdyacentes()) {
				p.getV1().getAdyacentes().remove(p.getPosicionEnlv1());
				p.getV2().getAdyacentes().remove(p.getPosicionEnlv2());
				edges.remove(p.getPosicionEnArcos());
			}
			nodes.remove(v1.getPosicionEnNodos());
		} catch (InvalidPositionException e) {
			e.printStackTrace();
		}
		v1.setPosicionEnNodos(null);
		v1.setElement(null);
		v1 = null;
		
		return toReturn;
	}

	@Override
	public E removeEdge(Edge<E> e) throws InvalidEdgeException {
		Arco<V,E> edge = checkEdge(e);
		E toReturn = null;
		try {
			toReturn = e.element();
			edge.getV1().getAdyacentes().remove(edge.getPosicionEnlv1());
			edge.getV2().getAdyacentes().remove(edge.getPosicionEnlv2());
			edges.remove(edge.getPosicionEnArcos());
		} catch (InvalidPositionException e1) {
			e1.printStackTrace();
		}
		return toReturn;
	}

	@SuppressWarnings("unchecked")
	private Vertice<V, E> checkVertex(Vertex<V> v) throws InvalidVertexException{
		Vertice<V, E> toReturn = null;
		if(v == null || v.element() == null)
			throw new InvalidVertexException("El vertice es invalido");
		try {
			toReturn = (Vertice<V, E>) v;
		}
		catch(ClassCastException e) {
			e.printStackTrace();
		}
		return toReturn;
	}
	@SuppressWarnings("unchecked")
	private Arco<V, E> checkEdge(Edge<E> e) throws InvalidEdgeException{
		Arco<V, E> toReturn = null;
		if(e == null)
			throw new InvalidEdgeException("El arco es invalido");
		try {
			toReturn = (Arco<V, E>) e;
		}
		catch(ClassCastException ex) {
			ex.printStackTrace();
		}
		return toReturn;
	}
}
