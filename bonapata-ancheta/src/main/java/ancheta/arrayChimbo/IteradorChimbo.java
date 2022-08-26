package ancheta.arrayChimbo;

import java.io.Serializable;
import java.util.Iterator;

public class IteradorChimbo<T>  implements Serializable,Iterator<T>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private T actual;
	
	private int pos;

	private T[] elementos;
	
	/**
	 * 
	 * @param actualP
	 * @param elementosP
	 */
	@SuppressWarnings("unchecked")
	public IteradorChimbo(int actualP, Object[] elementosP)
	{
		pos = actualP;
		elementos = (T[]) elementosP;
		actual = (T) elementosP[pos];
		
	}
	/**
	 * 
	 */
	@Override
	public boolean hasNext() {
		
		return (actual!= null);
	}
	/**
	 * 
	 */
	@Override
	public T next() {
		T temp = actual;
		pos++;
		actual = elementos[pos];

		return temp;
	}
	/**
	 * 
	 */
	@Override
	public void remove() {

		
	}


	

}
