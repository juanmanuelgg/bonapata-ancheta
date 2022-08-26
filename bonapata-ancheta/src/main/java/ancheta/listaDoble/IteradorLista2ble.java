package ancheta.listaDoble;

import java.io.Serializable;
import java.util.Iterator;

public class IteradorLista2ble<T> implements Iterator<T> , Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private NodoDoble<?> actual;
	/**
	 * 
	 * @param actualP
	 */
	public IteradorLista2ble(NodoDoble<?> actualP)
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
	@SuppressWarnings("unchecked")
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