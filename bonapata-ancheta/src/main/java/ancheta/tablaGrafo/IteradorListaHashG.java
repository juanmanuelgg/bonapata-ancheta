package ancheta.tablaGrafo;


import java.io.Serializable;
import java.util.Iterator;

public class IteradorListaHashG<K,V> implements Iterator<V>,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private NodoHashG<K, V> actual;
	/**
	 * 
	 * @param primero
	 */
	public IteradorListaHashG(NodoHashG<K, V> primero) {
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
	public NodoHashG<K, V> nextChimbo() {
		NodoHashG<K, V> x = actual;
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