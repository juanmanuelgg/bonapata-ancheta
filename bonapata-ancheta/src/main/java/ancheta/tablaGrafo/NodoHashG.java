package ancheta.tablaGrafo;

import java.io.Serializable;

public class NodoHashG<K,V> implements Serializable, Comparable<NodoHashG<K, V>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private NodoHashG<K,V> siguiente;
	/**
	 * 
	 */
	private V elemento;
	/**
	 * 
	 */
	private K llave;
	/**
	 * 
	 * @param elementoP
	 * @param llaveP
	 */
	public NodoHashG(V elementoP,K llaveP){
		elemento=elementoP;
		llave=llaveP;
		siguiente=null;
	}
	/**
	 * 
	 * @param elementoP
	 */
	public void cambiarElemento(V elementoP) {
		elemento = elementoP;
	}
	/**
	 * 
	 * @return
	 */
	public K darLlave() {
		return llave;
	}
	/**
	 * 
	 * @return
	 */
	public V darElemento() {
		return elemento;
	}
	/**
	 * 
	 * @param siguienteP
	 */
	public void cambiarSiguiente(NodoHashG<K,V> siguienteP) {
		siguiente = siguienteP;
	}
	/**
	 * 
	 * @return
	 */
	public NodoHashG<K,V> darSiguiente() {
		return siguiente;
	}
	/**
	 * 
	 */
	@Override
	public int compareTo(NodoHashG<K, V> otro) {
		if(llave.toString().equals(otro.darLlave().toString())){
			return 0;
		}
		return 1;
	}
}
