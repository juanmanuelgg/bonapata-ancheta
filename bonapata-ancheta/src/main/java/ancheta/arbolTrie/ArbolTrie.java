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

import java.util.Iterator;

import ancheta.arrayChimbo.ArrayChimbo;

import ancheta.listasCYP.ListaYPila;
import ancheta.exceptions.ElementoRepetidoException;
import ancheta.exceptions.palabraInvalidaException;

public class ArbolTrie<T extends Comparable<T>> implements IArbolTrie<T> {

	// -------------------------------CONSTANTES-----------------------------
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// -------------------------------ATRIBUTOS------------------------------
	private NodoTrie<T> raiz;
	private int numeroElemtos;
	private ArrayChimbo<T> copia;

	// -------------------------------METODOS---------------------------------
	/**
	 * 
	 */
	public ArbolTrie() {
		numeroElemtos = 0;
		raiz = null;
		copia = new ArrayChimbo<T>();
	}

	/**
	 * 
	 */
	@Override
	public void agregar(String palabra, T elemento) throws ElementoRepetidoException, palabraInvalidaException {
		if (palabra.length() > 3) {
			boolean seMetio = false;
			if (raiz == null) {
				raiz = new NodoTrie<T>();
			}
			seMetio = raiz.agregarNodo(palabra, elemento);
			if (seMetio) {
				numeroElemtos++;
				copia.agregar(elemento);
			} else {
				throw new ElementoRepetidoException(elemento);
			}
		} else {
			throw new palabraInvalidaException(palabra);
		}
	}

	/**
	 * 
	 */
	@Override
	public Iterator<T> buscar(String palabra) {
		return raiz == null ? null : raiz.buscar(palabra);
	}

	/**
	 * 
	 */
	@Override
	public Iterator<T> buscarPorPrefijo(String prefijo) {
		// Tambien es PRE PARCIAL
		ListaYPila<T> respuesta = new ListaYPila<T>();
		if (raiz != null) {
			raiz.busquedaPrefijos(prefijo, respuesta);
		}
		return respuesta.iterator();
	}

	/**
	 * 
	 */
	@Override
	public Iterator<T> eliminar(String palabra) {

		Iterator<T> iter = raiz.eliminar(palabra);
		if (iter != null) {
			numeroElemtos--;
		}
		return (raiz == null ? null : iter);
	}

	/**
	 * 
	 * @param n
	 * @return
	 */
	public Iterator<T> ultimosN(int n) {
		ArrayChimbo<T> resp = new ArrayChimbo<T>();
		System.out.println("trie>>numElementos " + numeroElemtos + ", se piden " + n);
		if (n > 0) {
			boolean acabo = false;
			int cont = 1;
			for (int i = numeroElemtos - 1; i >= 0 && !acabo; i--) {
				if (cont > n) {
					acabo = true;
				} else {
					System.out.println("trie>>semos " + cont);
					resp.agregar(copia.get(i));
				}
				cont++;
			}
		}
		return resp.iterator();

	}

	// -------------------------------METODOS PRE PARCIAL-----------------------
	/**
	 * 
	 * @return
	 */
	public String darPalabraMasLarga() {
		String palabra = "";
		ListaYPila<String> palabras = new ListaYPila<String>();
		if (raiz != null) {
			raiz.darPalabraMasLarga(palabras);
		}
		Iterator<String> iter = palabras.iterator();
		int cont = 0;
		while (iter.hasNext()) {
			String esta = iter.next();
			System.out.println("<<<< " + esta + "  " + esta.length());
			if (esta.length() > cont) {
				cont = esta.length();
				palabra = esta;
			}
		}
		return palabra;
	}

	/**
	 * 
	 * @return
	 */
	public int contarPalabras() {
		ListaYPila<T> respuesta = new ListaYPila<T>();
		if (raiz != null) {
			raiz.todasLasPalabras(respuesta);
		}
		return respuesta.darCantidadDeElementos();
	}

	// -------------------------------------------------------------------------
	/**
	 * 
	 * @return
	 */
	public NodoTrie<T> getRaiz() {
		return raiz;
	}

	/**
	 * 
	 * @return
	 */
	public int getNumeroElemtos() {
		return numeroElemtos;
	}

	/**
	 * 
	 */
	public void vaciar() {
		numeroElemtos = 0;
		raiz = null;
	}

	/**
	 * 
	 */
	@Override
	public Iterator<T> iterator() {
		return copia.iterator();
	}

	/**
	 * 
	 */
	@Override
	public T buscarPorId(int id) {
		return copia.get(id);
	}

	/**
	 * 
	 */
	@Override
	public ArrayChimbo<T> mostrarComoArreglo() {
		return copia;
	}
}
