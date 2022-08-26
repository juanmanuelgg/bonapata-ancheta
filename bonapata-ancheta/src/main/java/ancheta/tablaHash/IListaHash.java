package ancheta.tablaHash;

import java.io.Serializable;

import ancheta.exceptions.ElementoRepetidoException;

public interface IListaHash<K,V extends Comparable<V>> extends Serializable ,Iterable<V> {
	
	/**
	 * 
	 * @param elemento
	 * @param llave
	 * @throws ElementoRepetidoException
	 */
	public void agregar(V elemento, K llave) throws ElementoRepetidoException;
	/**
	 * 
	 * @param llave
	 * @return
	 */
	public V eliminar(K llave);
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
	public Object[] darCopiaEnArreglo();
	/**
	 * 
	 * @return
	 */
	public int darCantidadDeElementos();
	/**
	 * 
	 * @return
	 */
	public Object[] entregarCopiaOrdenada();
	/**
	 * 
	 * @return
	 */
	public NodoHash<K, V> darPrimerNodo();

}
