package ancheta.tablaGrafo;

import java.io.Serializable;

public class PaqueteHash<K,V> implements Serializable , Comparable<PaqueteHash<K, V>> {

	private static final long serialVersionUID = 1L;
	
	private K llave;
	private V elemento;
	public PaqueteHash(K llaveP, V elementoP)
	{
		llave= llaveP;
		elemento= elementoP;
	}
	public K getLlave() {
		return llave;
	}
	public V getElemento() {
		return elemento;
	}
	@Override
	public int compareTo(PaqueteHash<K, V> arg0) {
		String yo = llave.toString();//+elemento.toString();
		String el = arg0.getLlave().toString();//+arg0.getElemento().toString();
		return yo.compareTo(el);
	}
}
