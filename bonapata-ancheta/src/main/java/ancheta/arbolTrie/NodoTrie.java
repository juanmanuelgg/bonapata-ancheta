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

import ancheta.listasCYP.ListaYPila;
import ancheta.exceptions.ElementoRepetidoException;

public class NodoTrie<T extends Comparable<T>> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private char caracter;
	private ListaYPilaToString<T> elementos;
	private NodoTrie<T> hijoIzq;
	private NodoTrie<T> hermanoDer;

	/**
	 * 
	 */
	public NodoTrie() {
		caracter = 0;
		elementos = new ListaYPilaToString<T>();
		hijoIzq = null;
		hermanoDer = null;

	}

	/**
	 * 
	 * @return
	 */
	public char getCaracter() {
		return caracter;
	}

	/**
	 * 
	 * @return
	 */
	public ListaYPilaToString<T> getElementos() {
		return elementos;
	}

	/**
	 * 
	 * @return
	 */
	public NodoTrie<T> getHijoIzq() {
		return hijoIzq;
	}

	/**
	 * 
	 * @return
	 */
	public NodoTrie<T> getHermanoDer() {
		return hermanoDer;
	}

	/**
	 * 
	 * @param elementosP
	 */
	public void setElementos(ListaYPilaToString<T> elementosP) {
		elementos = elementosP;
	}

	/**
	 * 
	 * @param palabra
	 * @param elementoP
	 * @return
	 * @throws ElementoRepetidoException
	 */
	public boolean agregarNodo(String palabra, T elementoP) throws ElementoRepetidoException {
		char letra = palabra.charAt(0);
		if (palabra.length() == 1) {
			if (caracter == 0) {
				caracter = letra;
				elementos.agregar(elementoP);
				System.out.println("trie>>Agrego: " + elementoP.toString());
				return true;
			} else {
				if (caracter == letra) {
					boolean repetido = (elementos.buscar(elementoP) != null) ? true : false;
					if (repetido) {
						return false;
					} else {
						elementos.agregar(elementoP);
						System.out.println("trie>>Agrego: " + elementoP.toString());

						return true;
					}
				} else {
					boolean estaEnlosHermanos = false;
					NodoTrie<T> actual = hermanoDer;
					NodoTrie<T> donde = null;
					while (actual != null && estaEnlosHermanos == false) {
						if (actual.getCaracter() == letra) {
							donde = actual;
							estaEnlosHermanos = true;
						}
						actual = actual.getHermanoDer();
					}
					if (estaEnlosHermanos) {
						return donde.agregarNodo(palabra, elementoP);
					} else {
						NodoTrie<T> nuevo = new NodoTrie<T>();
						NodoTrie<T> aux = hermanoDer;
						hermanoDer = nuevo;
						nuevo.setHermanoDer(aux);
						return nuevo.agregarNodo(palabra, elementoP);
					}
				}

			}
		} else {
			if (caracter == 0) {
				caracter = letra;
				hijoIzq = new NodoTrie<T>();
				return hijoIzq.agregarNodo(palabra.substring(1), elementoP);
			} else {
				if (caracter == letra) {
					return hijoIzq.agregarNodo(palabra.substring(1), elementoP);
				} else {
					boolean estaEnlosHermanos = false;
					NodoTrie<T> actual = hermanoDer;
					NodoTrie<T> donde = null;
					while (actual != null && estaEnlosHermanos == false) {
						if (actual.getCaracter() == letra) {
							donde = actual;
							estaEnlosHermanos = true;
						}
						actual = actual.getHermanoDer();
					}
					if (estaEnlosHermanos) {
						return donde.agregarNodo(palabra, elementoP);
					} else {
						NodoTrie<T> nuevo = new NodoTrie<T>();
						NodoTrie<T> aux = hermanoDer;
						hermanoDer = nuevo;
						nuevo.setHermanoDer(aux);
						return nuevo.agregarNodo(palabra, elementoP);
					}
				}
			}
		}
	}

	/**
	 * 
	 * @param hermanoDerP
	 */
	public void setHermanoDer(NodoTrie<T> hermanoDerP) {
		hermanoDer = hermanoDerP;
	}

	/**
	 * 
	 * @param palabra
	 * @return
	 */
	public Iterator<T> buscar(String palabra) {

		char letra = palabra.charAt(0);
		if (palabra.length() == 1) {
			if (letra == caracter) {
				return elementos.iterator();
			} else {
				boolean estaEnlosHermanos = false;
				NodoTrie<T> actual = hermanoDer;
				NodoTrie<T> donde = null;
				while (actual != null && estaEnlosHermanos == false) {
					if (actual.getCaracter() == letra) {
						donde = actual;
						estaEnlosHermanos = true;
					}
					actual = actual.getHermanoDer();
				}
				return estaEnlosHermanos == true ? donde.getElementos().iterator() : null;
			}
		} else {
			if (palabra.length() < 1) {
				return null;
			} else {
				if (letra == caracter) {
					return hijoIzq != null ? hijoIzq.buscar(palabra.substring(1)) : null;
				} else {
					boolean estaEnlosHermanos = false;
					NodoTrie<T> actual = hermanoDer;
					NodoTrie<T> donde = null;
					while (actual != null && estaEnlosHermanos == false) {
						if (actual.getCaracter() == letra) {
							donde = actual;
							estaEnlosHermanos = true;
						}
						actual = actual.getHermanoDer();
					}
					return estaEnlosHermanos == true ? donde.buscar(palabra) : null;
				}
			}
		}
	}

	/**
	 * 
	 * @param prefijo
	 * @param respuesta
	 */
	public void busquedaPrefijos(String prefijo, ListaYPila<T> respuesta) {
		char letra = prefijo.charAt(0);
		if (prefijo.length() == 1) {
			if (letra == caracter) {
				recorridoAProfundidad(respuesta);
			} else {
				boolean estaEnlosHermanos = false;
				NodoTrie<T> actual = hermanoDer;
				NodoTrie<T> donde = null;
				while (actual != null && estaEnlosHermanos == false) {
					if (actual.getCaracter() == letra) {
						donde = actual;
						estaEnlosHermanos = true;
					}
					actual = actual.getHermanoDer();
				}
				if (estaEnlosHermanos) {
					donde.recorridoAProfundidad(respuesta);
				}
			}
		} else {
			if (prefijo.length() < 1) {
				// No hacer nada
			} else {
				if (letra == caracter) {
					if (hijoIzq != null) {
						hijoIzq.busquedaPrefijos(prefijo.substring(1), respuesta);
					}
				} else {
					boolean estaEnlosHermanos = false;
					NodoTrie<T> actual = hermanoDer;
					NodoTrie<T> donde = null;
					while (actual != null && estaEnlosHermanos == false) {
						if (actual.getCaracter() == letra) {
							donde = actual;
							estaEnlosHermanos = true;
						}
						actual = actual.getHermanoDer();
					}
					if (estaEnlosHermanos) {
						donde.busquedaPrefijos(prefijo, respuesta);
					}
				}
			}
		}
	}

	/**
	 * 
	 * @param respuesta
	 */
	public void recorridoAProfundidad(ListaYPila<T> respuesta) {
		if (!elementos.esVacia()) {
			Iterator<T> iter = elementos.iterator();
			while (iter.hasNext()) {
				T actual = (T) iter.next();
				try {
					System.out.println("trie>>Encontro: " + actual.toString());
					respuesta.agregar(actual);
				} catch (ElementoRepetidoException e) {
					System.out.println("Este elemento tambien estaba antes y ps ya se agrego");
				}
			}
		}
		if (hijoIzq != null) {
			hijoIzq.recorridoAProfundidad(respuesta);
			NodoTrie<T> actual = hijoIzq.getHermanoDer();
			while (actual != null) {
				actual.recorridoAProfundidad(respuesta);
				actual = actual.getHermanoDer();
			}
		}
	}

	/**
	 * 
	 * @param palabra
	 * @return
	 */
	public Iterator<T> eliminar(String palabra) {
		char letra = palabra.charAt(0);
		if (palabra.length() == 1) {
			if (letra == caracter) {
				Iterator<T> aux = elementos.iterator();
				elementos = new ListaYPilaToString<T>();
				return aux;
			} else {
				boolean estaEnlosHermanos = false;
				NodoTrie<T> actual = hermanoDer;
				NodoTrie<T> donde = null;
				while (actual != null && estaEnlosHermanos == false) {
					if (actual.getCaracter() == letra) {
						donde = actual;
						estaEnlosHermanos = true;
					}
					actual = actual.getHermanoDer();
				}
				if (estaEnlosHermanos) {
					Iterator<T> aux = donde.getElementos().iterator();
					donde.setElementos(new ListaYPilaToString<T>());
					return aux;
				} else {
					return null;
				}
			}
		} else {
			if (palabra.length() < 1) {
				return null;
			} else {
				if (letra == caracter) {
					return hijoIzq != null ? hijoIzq.eliminar(palabra.substring(1)) : null;
				} else {
					boolean estaEnlosHermanos = false;
					NodoTrie<T> actual = hermanoDer;
					NodoTrie<T> donde = null;
					while (actual != null && estaEnlosHermanos == false) {
						if (actual.getCaracter() == letra) {
							donde = actual;
							estaEnlosHermanos = true;
						}
						actual = actual.getHermanoDer();
					}
					return estaEnlosHermanos == true ? donde.eliminar(palabra) : null;
				}
			}
		}
	}

	/**
	 * 
	 * @param respuesta
	 */
	public void todasLasPalabras(ListaYPila<T> respuesta) {
		if (!elementos.esVacia()) {
			Iterator<T> iter = elementos.iterator();
			while (iter.hasNext()) {
				T actual = (T) iter.next();
				try {
					respuesta.agregar(actual);
				} catch (ElementoRepetidoException e) {
					System.out.println("Este elemento tambien estaba antes y ps ya se agrego");
				}
			}
		}
		if (hijoIzq != null) {
			hijoIzq.recorridoAProfundidad(respuesta);
		}
		NodoTrie<T> actual = hermanoDer;
		while (actual != null) {
			actual.recorridoAProfundidad(respuesta);
			actual = actual.getHermanoDer();
		}
	}

	/**
	 * 
	 * @param palabras
	 */
	public void darPalabraMasLarga(ListaYPila<String> palabras) {
		String palabra;
		if (hijoIzq != null) {
			palabra = "" + caracter;
			hijoIzq.armarPalabra(palabra, palabras);
		}
		if (hermanoDer != null) {
			palabra = "";
			hermanoDer.armarPalabra(palabra, palabras);
		}
	}

	/**
	 * 
	 * @param palabra
	 * @param palabras
	 */
	private void armarPalabra(String palabra, ListaYPila<String> palabras) {
		String palabraaa = palabra;
		palabra += caracter;
		if (hijoIzq != null) {
			hijoIzq.armarPalabra(palabra, palabras);
		} else {
			try {
				palabras.agregar(palabra);
			} catch (ElementoRepetidoException e) {
			}
		}
		if (hermanoDer != null) {
			hermanoDer.armarPalabra(palabraaa, palabras);
		}
	}
}