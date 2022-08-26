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

package ancheta.arbolTrie;

import java.io.Serializable;
import java.util.Iterator;

import ancheta.arrayChimbo.ArrayChimbo;

import ancheta.exceptions.ElementoRepetidoException;
import ancheta.exceptions.palabraInvalidaException;

public interface IArbolTrie<T> extends Serializable, Iterable<T> {

	/**
	 * 
	 * @param palabra
	 * @param elemento
	 * @throws ElementoRepetidoException
	 * @throws palabraInvalidaException
	 */
	public void agregar(String palabra, T elemento) throws ElementoRepetidoException, palabraInvalidaException;

	/**
	 * 
	 * @param palabra
	 * @return
	 */
	public Iterator<T> buscar(String palabra);

	/**
	 * 
	 * @param palabra
	 * @return
	 */
	public Iterator<T> eliminar(String palabra);

	/**
	 * 
	 * @param prefijo
	 * @return
	 */
	public Iterator<T> buscarPorPrefijo(String prefijo);

	/**
	 * 
	 * @param id
	 * @return
	 */
	public T buscarPorId(int id);

	/**
	 * 
	 * @return
	 */
	public ArrayChimbo<T> mostrarComoArreglo();
}
