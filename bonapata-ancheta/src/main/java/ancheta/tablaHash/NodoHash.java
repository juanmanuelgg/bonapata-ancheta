package ancheta.tablaHash;

import java.io.Serializable;

public class NodoHash<K,V> implements Serializable, Comparable<NodoHash<K, V>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private NodoHash<K,V> siguiente;
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
	public NodoHash(V elementoP,K llaveP){
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
	public void cambiarSiguiente(NodoHash<K,V> siguienteP) {
		siguiente = siguienteP;
	}
	/**
	 * 
	 * @return
	 */
	public NodoHash<K,V> darSiguiente() {
		return siguiente;
	}
	/**
	 * 
	 */
	@Override
	public int compareTo(NodoHash<K, V> otro) {
		if(elemento.toString().equals(otro.darElemento().toString())&&llave.toString().equals(otro.darLlave().toString())){
			return 0;
		}
		return 1;
	}
}
