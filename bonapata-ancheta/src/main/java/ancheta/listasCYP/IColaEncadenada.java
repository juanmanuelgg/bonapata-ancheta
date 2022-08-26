package ancheta.listasCYP;

import java.io.Serializable;

public interface IColaEncadenada<T> extends Serializable,Iterable<T>{
	
	/**
	 * 
	 * @param elemento
	 */
	public void agregarEnCola(T elemento);
	/**
	 * 
	 * @return
	 */
	public T tomarElemento();
	/**
	 * 
	 * @return
	 */
	public int darCantidadDeElementos();
	/**
	 * 
	 * @return
	 */
	public Object[] darEnArreglo();
}
