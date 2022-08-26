package ancheta.tablaHash;

import java.io.Serializable;
import java.util.Iterator;

import ancheta.exceptions.ElementoRepetidoException;

public interface ITablaHash<K,V> extends Serializable, Iterable<V> {
	/**
	 * 
	 * @param llave
	 * @param valor
	 * @throws ElementoRepetidoException
	 */
	public void put(K llave, V valor) throws ElementoRepetidoException;
	/**
	 * 
	 * @param llave
	 * @return
	 */
	public V get (K llave);
	/**
	 * 
	 * @param llave
	 * @return
	 */
	public Object[] getGroup(K llave);
	/**
	 * 
	 * @param llave
	 * @return
	 */
	public V remove(K llave);
	/**
	 * 
	 * @return
	 */
	public int darCantidadElementosIterables();
	/**
	 * 
	 * @return
	 */
	public int darCantidadElementosTabla();
	/**
	 * 
	 * @return
	 */
	public int darLargo();
	/**
	 * 
	 * @return
	 */
	public Iterator<V> iteratorInverso();
}
