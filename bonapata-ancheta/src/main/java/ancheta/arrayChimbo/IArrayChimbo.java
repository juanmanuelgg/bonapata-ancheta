package ancheta.arrayChimbo;

import java.io.Serializable;

public interface IArrayChimbo<T> extends Serializable,Iterable<T>{
	
	/**
	 * 
	 * @param elemento
	 */
	public void agregar (T elemento);
	/**
	 * 
	 * @param pos
	 * @return
	 */
	public T get (int pos);
	/**
	 * 
	 * @return
	 */
	public T getRandom ();
	/**
	 * 
	 * @param pos
	 * @return
	 */
	public T remove(int pos);
	/**
	 * 
	 * @return
	 */
	public Object[] sort();

}
