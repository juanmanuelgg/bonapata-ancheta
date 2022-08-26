package ancheta.arbolAVL;

import java.io.Serializable;

import ancheta.arrayChimbo.ArrayChimbo;

public interface IArbolAVL<T> extends Serializable,Iterable<T> {

	/**
	 * 
	 * @param elemento
	 * @param llave
	 */
	public void agregar(T elemento, String llave);
	/**
	 * 
	 * @param busqueda
	 * @return
	 */
	public ArrayChimbo<T> getGroup(String busqueda);
	/**
	 * 
	 * @return
	 */
	public int getPeso();
	/**
	 * 
	 * @return
	 */
	public int getAltura();
	/**
	 * 
	 */
	public void vaciar();

}
