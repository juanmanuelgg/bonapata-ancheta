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

package ancheta.listasCYP;

import java.util.Arrays;
import java.util.Iterator;

import ancheta.exceptions.ElementoRepetidoException;

public class ListaYCola<T extends Comparable<T>> implements ILista<T>, IColaEncadenada<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private Nodo<T> primero;
	/**
	 * 
	 */
	private Nodo<T> ultimo;
	/**
	 * 
	 */
	private int cantidadDeElementos;

	/**
	 * constructor de la Lista; esta lista tambien puede ser llamada Cola; pues se
	 * comporta como esa estructura de Datos agrega los elementos por el primero,
	 * pero los entrega desde el ultimo
	 */
	public ListaYCola() {
		cantidadDeElementos = 0;
		primero = null;
		ultimo = null;
	}

	// -----------------------------------------------------------------
	// Metodos Para lista Inversa
	// -----------------------------------------------------------------
	/**
	 * 
	 */
	@Override
	public void agregar(T elemento) throws ElementoRepetidoException {
		Nodo<T> nuevo = new Nodo<T>(elemento);
		if (primero == null) {
			primero = nuevo;
			ultimo = primero;
			cantidadDeElementos++;
		} else {
			Nodo<T> actual = primero;
			boolean acabo = false;
			while (!acabo) {
				if (actual.darElemento().compareTo(elemento) == 0) {
					// solo por precaucion
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
			Nodo<T> aux = primero;
			primero = nuevo;
			primero.cambiarSiguiente(aux);
			cantidadDeElementos++;
		}
	}

	/**
	 * 
	 */
	public T buscar(T elemento) {
		Nodo<T> actual = primero;
		while (actual != null) {
			T elObjeto = actual.darElemento();
			if (elObjeto.compareTo(elemento) == 0) {
				return elObjeto;
			}
			actual = actual.darSiguiente();
		}
		return null;
	}

	/**
	 * 
	 */
	@Override
	public T eliminar(T t) {
		if (primero != null) {
			Nodo<T> actual = primero;
			Nodo<T> siguiente = actual.darSiguiente();
			if (primero.darElemento().compareTo(t) == 0) {
				primero = siguiente;
				cantidadDeElementos--;
				return actual.darElemento();
			}
			while (siguiente != null) {
				if (siguiente.darElemento().compareTo(t) == 0) {
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
	@Override
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
	@Override
	public boolean esVacia() {
		return (primero == null || ultimo == null);
	}

	/**
	 * 
	 */
	@Override
	public Object[] sort() {
		Object[] aux = darEnArreglo();
		Arrays.sort(aux);
		return aux;
	}

	// -----------------------------------------------------------------
	// Metodos Para Ambos (Cola y lista Inversa)
	// -----------------------------------------------------------------
	/**
	 * 
	 */
	@Override
	public int darCantidadDeElementos() {
		return cantidadDeElementos;
	}

	/**
	 * 
	 */
	@Override
	public Object[] darEnArreglo() {
		Nodo<T> actual = primero;
		Object[] aRetornar = new Object[cantidadDeElementos];
		int contador = 0;
		while (actual != null) {
			aRetornar[contador] = actual.darElemento();
			actual = actual.darSiguiente();
			contador++;
		}
		return aRetornar;
	}

	/**
	 * 
	 */
	@Override
	public Iterator<T> iterator() {
		return new IteradorLista<T>(primero, this);
	}

	// -----------------------------------------------------------------
	// Metodos para usar como Cola
	// -----------------------------------------------------------------
	/**
	 * 
	 */
	@Override
	public void agregarEnCola(T elemento) {
		Nodo<T> nuevo = new Nodo<T>(elemento);
		Nodo<T> aux = primero;
		primero = nuevo;
		primero.cambiarSiguiente(aux);
		cantidadDeElementos++;
	}

	/**
	 * 
	 */
	@Override
	public T tomarElemento() {
		if (primero != null) {
			T aRetornar = primero.darElemento();
			Nodo<T> nuevoPrimero = primero.darSiguiente();
			primero = nuevoPrimero;
			cantidadDeElementos--;
			return aRetornar;
		} else {
			return null;
		}
	}
}