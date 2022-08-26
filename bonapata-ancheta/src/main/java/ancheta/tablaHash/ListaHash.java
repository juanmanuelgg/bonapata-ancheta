/*
 * MIT License
 * 
 * Copyright (c) 2022 Juan Manuel González Garzón
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package ancheta.tablaHash;

import java.util.Iterator;
import ancheta.exceptions.ElementoRepetidoException;

public class ListaHash<K, V extends Comparable<V>> implements IListaHash<K, V> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private NodoHash<K, V> primero;
	/**
	 * 
	 */
	private NodoHash<K, V> ultimo;
	/**
	 * 
	 */
	private int cantidadDeElementos;

	/**
	 * 
	 */
	public ListaHash() {
		cantidadDeElementos = 0;
		primero = null;
		ultimo = null;
	}

	/**
	 * 
	 */
	public void agregar(V elemento, K llave) throws ElementoRepetidoException {

		NodoHash<K, V> nuevo = new NodoHash<K, V>(elemento, llave);

		if (primero == null) {
			primero = nuevo;
			ultimo = primero;
			cantidadDeElementos++;
		} else {
			NodoHash<K, V> actual = primero;
			boolean acabo = false;
			while (!acabo) {

				if (actual.compareTo(nuevo) == 0) {
					acabo = true;
					throw new ElementoRepetidoException(elemento);
				} else {
					if (actual.darSiguiente() == null) {
						acabo = true;
					} else {
						actual = actual.darSiguiente();
					}
				}
			}
			ultimo.cambiarSiguiente(nuevo);
			ultimo = nuevo;
			cantidadDeElementos++;
		}
	}

	/**
	 * 
	 */
	public V eliminar(K llave) {
		if (primero != null) {
			NodoHash<K, V> actual = primero;
			NodoHash<K, V> siguiente = actual.darSiguiente();
			if (primero.darSiguiente() == null) {
				primero = null;
				if (actual.darLlave().equals(llave)) {
					cantidadDeElementos--;
					return actual.darElemento();
				}
				return null;
			}
			if (primero.darLlave().equals(llave)) {
				primero = primero.darSiguiente();
				cantidadDeElementos--;
				return actual.darElemento();
			}
			while (siguiente != null) {
				if (siguiente.darLlave().equals(llave)) {
					actual.cambiarSiguiente(siguiente.darSiguiente());
					cantidadDeElementos--;
					return siguiente.darElemento();
				}
				actual = actual.darSiguiente();
				siguiente = actual.darSiguiente();
			}
			return null;
		}
		return null;
	}

	/**
	 * 
	 */
	public Object[] darCopiaEnArreglo() {
		NodoHash<K, V> actual = primero;
		Object[] aRetornar = new Object[cantidadDeElementos];
		int contador = 0;
		while (actual != null) {
			Object elObjeto = actual.darElemento();
			aRetornar[contador] = elObjeto;
			actual = actual.darSiguiente();
			contador++;
		}
		return aRetornar;
	}

	/**
	 * 
	 */
	public boolean esVacia() {
		return (primero == null || ultimo == null);
	}

	/**
	 * 
	 */
	public boolean vaciar() {
		if (primero == null || ultimo == null) {
			return false;
		} else {
			primero = null;
			ultimo = null;
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
		Object[] baseParaOrdenar = new Object[cantidadDeElementos];
		for (int i = 0; i < baseParaOrdenar.length; i++) {
			baseParaOrdenar[i] = darCopiaEnArreglo()[i];
		}

		for (int i = 1; i <= cantidadDeElementos; i++)
			for (int j = cantidadDeElementos - 1; j >= i; j--) {
				if (((V) baseParaOrdenar[j - 1]).compareTo((V) baseParaOrdenar[j]) < 0) {
					Object v;
					v = baseParaOrdenar[j - 1];
					baseParaOrdenar[j - 1] = baseParaOrdenar[j];
					baseParaOrdenar[j] = v;
				}
			}
		return baseParaOrdenar;
	}

	/**
	 * 
	 */
	public Iterator<V> iterator() {
		return new IteradorListaHash<K, V>(primero);
	}

	/**
	 * 
	 */
	public NodoHash<K, V> darPrimerNodo() {
		return primero;
	}
}