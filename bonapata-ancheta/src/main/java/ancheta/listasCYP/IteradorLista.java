package ancheta.listasCYP;

import java.io.Serializable;
import java.util.Iterator;

public class IteradorLista<T extends Comparable<T>> implements Iterator<T> , Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private Nodo<T> actual;
	/**
	 * 
	 * @param actualP
	 * @param lol
	 */
	public IteradorLista(Nodo<T> actualP, ILista<T> lol)
	{
		actual=actualP;
	}
	/**
	 * 
	 */
	@Override
	public boolean hasNext() {
		return (actual != null);
	}
	/**
	 * 
	 */
	@Override
	public T next() {
		T x= (T) actual.darElemento();
		actual= actual.darSiguiente();
		return x;
	}
	/**
	 * 
	 */
	@Override
	public void remove() {
	}
}