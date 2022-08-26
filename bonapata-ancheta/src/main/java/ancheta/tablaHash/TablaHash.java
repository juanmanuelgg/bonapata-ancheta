package ancheta.tablaHash;

import java.util.Iterator;

import ancheta.listasCYP.ILista;
import ancheta.listasCYP.ListaYCola;
import ancheta.listasCYP.ListaYPila;

import ancheta.exceptions.ElementoRepetidoException;

public class TablaHash<K,V extends Comparable<V>> implements ITablaHash<K, V> {

	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private int cantidadDeElementosTabla;
	/**
	 * 
	 */
	private int cantidadElementosIterables;
	/**
	 * 
	 */
	private int tamanioTabla;
	/**
	 * 
	 */
	private ILista<V> copia;
	/**
	 * 
	 */
	private IListaHash<K, V>[] listas;
	/**
	 * 
	 */
	private ILista<V> copiaInevrsa; 
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public TablaHash(){
		cantidadDeElementosTabla=0;
		cantidadElementosIterables=0;
		tamanioTabla= 10;
		copia= new ListaYPila<V>();
		copiaInevrsa= new ListaYCola<V>();
		listas= new ListaHash[tamanioTabla];		
	}
	/**
	 * 
	 */
	@Override
	public int darCantidadElementosIterables() {
		return cantidadElementosIterables;
	}
	/**
	 * 
	 */
	@Override
	public int darCantidadElementosTabla() {
		return cantidadDeElementosTabla;
	}
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public V get(K llave) {
		int pos=funcionHash(llave);
		ListaHash<K,V> esta=(ListaHash<K, V>) listas[pos];
		if(esta!=null)
		{
			IteradorListaHash<K, V> iter= (IteradorListaHash<K, V>) esta.iterator();
			while (iter.hasNext()) {
				NodoHash<K, V> objeto= iter.nextChimbo();
				if(objeto.darLlave().equals(llave))
				{
					return objeto.darElemento();
				}
			}
		}
		return null;
	}
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object[] getGroup(K llave) {
		Object[]grupo=null;
		int pos=funcionHash(llave);
		ListaHash<K,V> esta=(ListaHash<K, V>) listas[pos];
		if(esta!=null)
		{
			grupo= new Object[esta.darCantidadDeElementos()];
			IteradorListaHash<K, V> iter= (IteradorListaHash<K, V>) esta.iterator();
			int cont=0;
			while (iter.hasNext()) {
				NodoHash<K, V> objeto= iter.nextChimbo();
				if(objeto.darLlave().equals(llave))
				{
					grupo[cont]=objeto.darElemento();
					cont++;
				}
			}
		}
		return  grupo;
	}
	/**
	 * 
	 */
	@Override
	public void put(K llave, V valor) throws ElementoRepetidoException  {
		try{
			copia.agregar(valor);
			copiaInevrsa.agregar(valor);
			cantidadElementosIterables++;
		}
		catch (ElementoRepetidoException e) {
		}
		int pos=funcionHash(llave);
		if(listas[pos]==null)
		{
			listas[pos]=new ListaHash<K,V>();
		}
		listas[pos].agregar(valor, llave);
		cantidadDeElementosTabla++;
		if(cantidadDeElementosTabla==tamanioTabla*1.2)
		{
			System.out.println("-Rehash<"+cantidadDeElementosTabla+">-");
			reHash();
		}
	}
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	private void reHash() {
		System.out.println("-RH-");
		tamanioTabla = tamanioTabla*2;
		cantidadDeElementosTabla=0;
		IListaHash<K, V>[] listasB= new ListaHash[tamanioTabla];
		for (int i = 0; i < listas.length; i++) {
			ListaHash<K, V> esta= (ListaHash<K, V>) listas[i];
			if(esta!=null)
			{
				NodoHash<K, V> actual= esta.darPrimerNodo();
				while (actual!=null) 
				{
					K miLlave=actual.darLlave();
					V miValor = actual.darElemento();
					int pos=funcionHash(miLlave);
					if(listasB[pos]==null)
					{
						listasB[pos]=new ListaHash<K,V>();
					}
					try {
						listasB[pos].agregar(miValor, miLlave);
					} catch (ElementoRepetidoException e) {
						e.printStackTrace();
						//esto no deveria pasar ni por el putas
					}
					cantidadDeElementosTabla++;
					actual=actual.darSiguiente();
				}
			}
		}
		listas=listasB;
	}
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public V remove(K llave) {
		int pos=funcionHash(llave);
		ListaHash<K,V> esta=(ListaHash<K, V>) listas[pos];
		if(esta!=null)
		{
			IteradorListaHash<K, V> iter= (IteradorListaHash<K, V>) esta.iterator();
			while (iter.hasNext()) {
				NodoHash<K, V> objeto= iter.nextChimbo();
				if(objeto.darLlave().equals(llave))
				{
					V x = objeto.darElemento();
					copia.eliminar(x);
					copiaInevrsa.eliminar(x);
					esta.eliminar(llave);
					cantidadDeElementosTabla--;
					return x;
				}
			}
		}
		return null;
	}
	/**
	 * 
	 */
	@Override
	public Iterator<V> iterator() {
		return copia.iterator();
	}
	/**
	 * 
	 */
	@Override
	public Iterator<V> iteratorInverso()
	{
		return copiaInevrsa.iterator();
	}
	/**
	 * 
	 * @param llaveP
	 * @return
	 */
	private int funcionHash(K llaveP)
	{
		return Math.abs(llaveP.hashCode()%tamanioTabla);
	}
	/**
	 * 
	 */
	@Override
	public int darLargo() {
		return listas.length;
	}
}