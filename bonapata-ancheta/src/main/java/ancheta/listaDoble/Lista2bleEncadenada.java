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

package ancheta.listaDoble;

import java.util.Iterator;
import ancheta.exceptions.ElementoRepetidoException;
import ancheta.listasCYP.ILista;

public class Lista2bleEncadenada<T extends Comparable<T>> implements ILista<T>, IlistaDoble<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private NodoDoble<T> primero;
	/**
	 * 
	 */
	private NodoDoble<T> actualEnUso;
	/**
	 * 
	 */
	private int cantidadDeElementos;

	/**
	 * 
	 */
	public Lista2bleEncadenada() {
		primero = null;
		actualEnUso = null;
		cantidadDeElementos = 0;
	}

	/**
	 * 
	 * @return
	 */
	public NodoDoble<T> darPrimero() {
		return primero;
	}

	/**
	 * 
	 */
	@Override
	public void agregar(T elemento) throws ElementoRepetidoException {
		NodoDoble<T> nuevo = new NodoDoble<T>(elemento);
		if (primero == null) {
			primero = nuevo;
			primero.cambiarAnterior(primero);
			primero.cambiarSiguiente(primero);
			cantidadDeElementos++;
		} else {
			NodoDoble<T> actual = primero;
			int i = 0;
			while (i <= cantidadDeElementos) {
				int comparacion = actual.darElemento().compareTo(elemento);
				if (comparacion == 0) {
					throw new ElementoRepetidoException(elemento);
				}
				i++;
			}
			NodoDoble<T> auxUltimo = primero.darAnterior();
			auxUltimo.cambiarSiguiente(nuevo);
			nuevo.cambiarAnterior(auxUltimo);
			primero.cambiarAnterior(nuevo);
			nuevo.cambiarSiguiente(primero);
			cantidadDeElementos++;
		}
	}

	/**
	 * 
	 */
	@Override
	public T buscar(T elemento) {
		T aRetornar = null;
		if (primero != null) {
			NodoDoble<T> actual = primero;
			int i = 0;
			while (i <= cantidadDeElementos) {
				int comparacion = actual.darElemento().compareTo(elemento);
				if (comparacion == 0) {
					return actual.darElemento();
				}
				actual = actual.darSiguiente();
				i++;
			}
		}
		return aRetornar;
	}

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
		Object[] aRetornar = new Object[cantidadDeElementos];
		if (primero != null) {
			NodoDoble<T> actual = primero;
			int i = 0;
			while (i <= cantidadDeElementos) {
				aRetornar[i] = actual.darElemento();
				actual = actual.darSiguiente();
				i++;
			}
		}
		return aRetornar;
	}

	/**
	 * 
	 */
	@Override
	public T eliminar(T elemento) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 */
	@Override
	public boolean esVacia() {
		return primero == null;
	}

	/**
	 * 
	 */
	@Override
	public Object[] sort() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 */
	@Override
	public boolean vaciar() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 
	 */
	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * @param actualEnUso
	 */
	public void cambiarActualEnUso(NodoDoble<T> actualEnUso) {
		this.actualEnUso = actualEnUso;
	}

	/**
	 * 
	 */
	@Override
	public T darActualEnUso() {
		return actualEnUso.darElemento();
	}

	/**
	 * 
	 */
	@Override
	public T darSiguienteElemento() {
		return actualEnUso.darSiguiente().darElemento();
	}

	/**
	 * 
	 */
	@Override
	public T darAnteriorElemento() {
		return actualEnUso.darAnterior().darElemento();
	}

}
