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

package ancheta.arrayChimbo;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayChimbo<T> implements IArrayChimbo<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int tamanio;
	private Object[] elementos;
	private int posActual;
	private T ultimaGuardada;

	/**
	 * 
	 */
	public ArrayChimbo() {
		tamanio = 10;
		posActual = 0;
		elementos = new Object[tamanio];
	}

	/**
	 * 
	 */
	@Override
	public void agregar(T elemento) {

		if (tamanio * 0.8 > posActual) {
			Object[] temp = elementos;
			tamanio = tamanio * 2;
			elementos = new Object[tamanio];
			for (int i = 0; i < temp.length; i++) {
				elementos[i] = temp[i];
			}
			elementos[posActual] = elemento;
			posActual++;
		} else {
			elementos[posActual] = elemento;
			posActual++;
		}
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T get(int pos) {
		return posActual == 0 ? null : (T) elementos[pos];
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T getRandom() {
		if (posActual == 0) {
			return null;
		} else {
			int numRandom = (int) (Math.random() * posActual);
			T temp = (T) elementos[numRandom];
			if (temp == ultimaGuardada) {
				return getRandom();
			}
			ultimaGuardada = temp;
			return temp;
		}
	}

	/**
	 * 
	 * @return
	 */
	public int getSize() {
		return posActual;
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T remove(int pos) {
		if (posActual == 0) {
			return null;
		} else {
			T aRetornar = (T) elementos[pos];
			elementos[pos] = null;
			Object[] aux = new Object[tamanio - 1];
			int j = 0;
			for (int i = 0; i < elementos.length; i++) {
				if (elementos[i] != null) {
					aux[j] = elementos[i];
					j++;
				}
			}
			elementos = aux;
			tamanio--;
			posActual--;
			return aRetornar;
		}
	}

	/**
	 * 
	 */
	@Override
	public Iterator<T> iterator() {
		IteradorChimbo<T> nuevo = new IteradorChimbo<T>(0, elementos);
		return nuevo;
	}

	/**
	 * 
	 */
	@Override
	public Object[] sort() {
		Object[] aux = new Object[posActual];
		for (int i = 0; i < aux.length; i++) {
			aux[i] = elementos[i];
		}
		Arrays.sort(aux);
		return aux;
	}

}
