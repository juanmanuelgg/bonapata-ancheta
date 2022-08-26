package ancheta.tablaGrafo;

import java.io.Serializable;
import java.util.Iterator;


import ancheta.exceptions.ElementoRepetidoException;

public class ListaHashG<K ,V extends Comparable<V>> implements Serializable, Iterable<V> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private NodoHashG<K, V> primero;
	/**
	 * 
	 */
	private NodoHashG<K, V> ultimo;
	/**
	 * 
	 */
	private int cantidadDeElementos;
	/**
	 * 
	 */
	public ListaHashG(){
		cantidadDeElementos=0;
		primero=null;
		ultimo=null;
	}
	/**
	 * 
	 */
	public void agregar(V elemento,K llave) throws ElementoRepetidoException {
		
		NodoHashG<K, V> nuevo = new NodoHashG<K,V>(elemento,llave);
		
		if(primero==null)
		{
			primero = nuevo;
			ultimo=primero;
			cantidadDeElementos++;
		}
		else
		{
			NodoHashG<K,V> actual= primero;
			boolean acabo=false;
			while (!acabo) {
				
				if(actual.compareTo(nuevo)==0)
				{
					acabo=true;
					throw new ElementoRepetidoException(elemento);
				}
				else
				{
					if(actual.darSiguiente()==null)
					{
						acabo=true;
					}
					else
					{
						actual=actual.darSiguiente();
					}
				}
			}
			ultimo.cambiarSiguiente(nuevo);
			ultimo=nuevo;
			cantidadDeElementos++;
		}
	}
	/**
	 * 
	 */
	public V eliminar(K llave) {
		if(primero!=null)
		{
			NodoHashG<K,V> actual= primero;
			NodoHashG<K, V> siguiente= actual.darSiguiente();
			if(primero.darSiguiente()==null)
			{
				primero=null;
				if( actual.darLlave().equals(llave))
				{
					cantidadDeElementos--;
					return actual.darElemento();
				}
				return null;
			}
			if(primero.darLlave().equals(llave))
			{
				primero = primero.darSiguiente();
				cantidadDeElementos--;
				return actual.darElemento();
			}
			while (siguiente!=null) {
				if(siguiente.darLlave().equals(llave))
				{
					actual.cambiarSiguiente(siguiente.darSiguiente());
					cantidadDeElementos--;
					return siguiente.darElemento();
				}
				actual= actual.darSiguiente();
				siguiente= actual.darSiguiente();
			}
			return null;
		}
		return null;
	}
	/**
	 * 
	 */
	public Object[] darCopiaEnArreglo() {
		NodoHashG<K, V> actual= primero;
		Object[] aRetornar= new Object[cantidadDeElementos];
		int contador=0;
		while (actual!=null) {
			Object elObjeto=actual.darElemento();
			aRetornar[contador]= elObjeto;
			actual= actual.darSiguiente();
			contador++;
		}
		return aRetornar;
	}
	/**
	 * 
	 */
	public boolean esVacia() {
		return (primero==null||ultimo==null);
	}
	/**
	 * 
	 */
	public boolean vaciar() {
		if(primero==null||ultimo==null)
		{
			return false;
		}	
		else{
			primero=null;
			ultimo=null;
			cantidadDeElementos = 0;
			return true;
		}
	}
	/**
	 * 
	 */
	public int darCantidadDeElementos() {
		return cantidadDeElementos;
	}	
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public Object[] entregarCopiaOrdenada() {
		Object[] baseParaOrdenar= new Object[cantidadDeElementos];
		for (int i = 0; i < baseParaOrdenar.length; i++) {
			baseParaOrdenar[i]=darCopiaEnArreglo()[i];
		}

		for(int i=1;i<=cantidadDeElementos;i++)
			for(int j=cantidadDeElementos-1;j>=i;j--)
			{
				if(((V) baseParaOrdenar[j-1]).compareTo((V) baseParaOrdenar[j])<0)
					{
					Object v;
					v=baseParaOrdenar[j-1];
					baseParaOrdenar[j-1]=baseParaOrdenar[j];
					baseParaOrdenar[j]=v;
					}
			}
		return baseParaOrdenar;
	}
	/**
	 * 
	 */
	public Iterator<V> iterator() {
		return new IteradorListaHashG<K, V>(primero);
	}
	/**
	 * 
	 */
	public NodoHashG<K, V> darPrimerNodo() {
		return primero;
	}
}