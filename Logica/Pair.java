package Logica;
/**
 * Modela un par de elementos abstractos.
 * @author Emanuel Orler
 * @param <A>: Tipo de dato abstracto que utiliza la primera componente.
 * @param <B>: Tipo de dato abstracto que utiliza la segunda componente.
 */
 class Pair<A,B> {
	protected A a;
	protected B b;
	/**
	 * Inicializa el par con los valores de sus componentes.
	 * @param a: Primera componente del par.
	 * @param b: Segunda componente del par.
	 */
	public Pair(A a, B b) {
		this.a = a;
		this.b = b;
	}
	/**
	 * Retorna el valor de la primera componente del par.
	 * @return a, donde a es el valor de la primera componente.
	 */
	public A getA() {
		return a;
	}
	/**
	 * Retorna el valor de la segunda componente del par.
	 * @return b, donde b es el valor de la segunda componente.
	 */
	public B getB() {
		return b;
	}
}