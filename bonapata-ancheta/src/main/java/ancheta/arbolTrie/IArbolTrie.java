package ancheta.arbolTrie;

import java.io.Serializable;
import java.util.Iterator;

import ancheta.arrayChimbo.ArrayChimbo;

import ancheta.exceptions.ElementoRepetidoException;
import ancheta.exceptions.palabraInvalidaException;

public interface IArbolTrie<T> extends Serializable, Iterable<T> {
	
	/**
	 * 
	 * @param palabra
	 * @param elemento
	 * @throws ElementoRepetidoException
	 * @throws palabraInvalidaException
	 */
	public void agregar(String palabra,T elemento )throws ElementoRepetidoException, palabraInvalidaException;
	/**
	 * 
	 * @param palabra
	 * @return
	 */
	public  Iterator<T> buscar(String palabra);
	/**
	 * 
	 * @param palabra
	 * @return
	 */
	public Iterator<T> eliminar(String palabra);
	/**
	 * 
	 * @param prefijo
	 * @return
	 */
	public Iterator<T> buscarPorPrefijo(String prefijo);
	/**
	 * 
	 * @param id
	 * @return
	 */
	public T buscarPorId(int id);
	/**
	 * 
	 * @return
	 */
	public ArrayChimbo<T> mostrarComoArreglo();
}
