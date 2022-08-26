package ancheta.listasCYP;

import java.io.Serializable;

import ancheta.exceptions.ElementoRepetidoException;

public interface ILista<T> extends Serializable, Iterable<T>{
	
	/**
	 * 
	 * @param elemento
	 * @throws ElementoRepetidoException
	 */
	public void agregar(T elemento) throws ElementoRepetidoException;
	/**
	 * 
	 * @param elemento
	 * @return
	 */
	public T eliminar(T elemento);
	/**
	 * 
	 * @param elemento
	 * @return
	 */
	public T buscar(T elemento);
	/**
	 * 
	 * @return
	 */
	public boolean esVacia();
	/**
	 * 
	 * @return
	 */
	public boolean vaciar();
	/**
	 * 
	 * @return
	 */
	public Object[] darEnArreglo();
	/**
	 * 
	 * @return
	 */
	public int darCantidadDeElementos();
	/**
	 * 
	 * @return
	 */
	public Object[] sort();
}
