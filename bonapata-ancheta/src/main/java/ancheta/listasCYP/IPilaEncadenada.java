package ancheta.listasCYP;

import java.io.Serializable;

public interface IPilaEncadenada<T> extends Serializable,Iterable<T>{
	
	/**
	 * 
	 * @param elemento
	 */
	public void agregarEnPila(T elemento);
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
