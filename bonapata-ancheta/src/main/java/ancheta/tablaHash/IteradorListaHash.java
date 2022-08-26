package ancheta.tablaHash;

import java.io.Serializable;
import java.util.Iterator;

public class IteradorListaHash<K,V> implements Iterator<V>,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private NodoHash<K, V> actual;
	/**
	 * 
	 * @param primero
	 */
	public IteradorListaHash(NodoHash<K, V> primero) {
		actual=primero;
	}
	/**
	 * 
	 */
	@Override
	public boolean hasNext() {
		return (actual!=null);
	}
	/**
	 * 
	 */
	@Override
	public V next() {
		V x= actual.darElemento();
		actual=actual.darSiguiente();
		return x;
	}
	/**
	 * 
	 * @return
	 */
	public NodoHash<K, V> nextChimbo() {
		NodoHash<K, V> x = actual;
		actual=actual.darSiguiente();
		return x;
	}
	/**
	 * 
	 */
	@Override
	public void remove() {

	}
}