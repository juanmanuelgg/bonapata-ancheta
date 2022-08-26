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

package ancheta.testArbolTrie;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

import ancheta.arbolTrie.ArbolTrie;
import ancheta.comun.ClasePrueba;
import ancheta.exceptions.ElementoRepetidoException;
import ancheta.exceptions.palabraInvalidaException;

public class TestArbolTrie {

	private ArbolTrie<ClasePrueba> arbol = new ArbolTrie<ClasePrueba>();

	public void setUpEscenario1() {
		// escenario planeado sin elementos repetidos o invalidos
		ClasePrueba aInsertar1 = new ClasePrueba(1, "XXX::arroz");
		ClasePrueba aInsertar2 = new ClasePrueba(2, "XXX::arreglo");
		ClasePrueba aInsertar3 = new ClasePrueba(3, "XXX::furia");
		ClasePrueba aInsertar4 = new ClasePrueba(4, "XXX::fuego");
		ClasePrueba aInsertar5 = new ClasePrueba(5, "XXX::estadiaticas de servicio");
		ClasePrueba aInsertar6 = new ClasePrueba(6, "XXX::lolol");
		ClasePrueba aInsertar7 = new ClasePrueba(7, "XXX::armagedon");
		try {
			arbol.agregar("arroz", aInsertar1);
			arbol.agregar("arreglo", aInsertar2);
			arbol.agregar("furia", aInsertar3);
			arbol.agregar("fuego", aInsertar4);
			arbol.agregar("estadiaticas de servicio", aInsertar5);
			arbol.agregar("lolol", aInsertar6);
			arbol.agregar("armagedon", aInsertar7);
		} catch (Exception e) {
			fail("Esto no deveria pasar pues no he metido palabras invalidas o repetidas");
		}
	}

	public void setUpEscenario2() {
		// escenario planeado sin elementos repetidos o invalidos
		ClasePrueba aInsertar1 = new ClasePrueba(11, "X_X::arroz");
		ClasePrueba aInsertar3 = new ClasePrueba(33, "X_X::furia");
		ClasePrueba aInsertar5 = new ClasePrueba(55, "X_X::estadiaticas de servicio");
		ClasePrueba aInsertar7 = new ClasePrueba(77, "X_X::armagedon");
		System.out.println("ARBOL N 1");
		System.out.println("____________________________");
		try {
			arbol.agregar("arroz", aInsertar1);
			arbol.agregar("furia", aInsertar3);
			arbol.agregar("estadiaticas de servicio", aInsertar5);
			arbol.agregar("armagedon", aInsertar7);
		} catch (Exception e) {
			fail("Esto no deveria pasar pues no he metido palabras invalidas o repetidas");
		}
		System.out.println("____________________________");
	}

	public void setUpEscenario1_1() {
		// escenario planeado sin elementos repetidos o invalidos
		ClasePrueba aInsertar1 = new ClasePrueba(1, "XXX::arroz");
		ClasePrueba aInsertar2 = new ClasePrueba(2, "XXX::arreglo on");
		ClasePrueba aInsertar3 = new ClasePrueba(3, "XXX::furia");
		ClasePrueba aInsertar4 = new ClasePrueba(4, "XXX::fuego");
		ClasePrueba aInsertar6 = new ClasePrueba(6, "XXX::lolol");
		ClasePrueba aInsertar7 = new ClasePrueba(7, "XXX::armagedon");
		try {
			arbol.agregar("arroz", aInsertar1);
			arbol.agregar("arreglo on", aInsertar2);
			arbol.agregar("furia", aInsertar3);
			arbol.agregar("fuego", aInsertar4);
			arbol.agregar("lolol", aInsertar6);
			arbol.agregar("armagedon", aInsertar7);
		} catch (Exception e) {
			fail("Esto no deveria pasar pues no he metido palabras invalidas o repetidas");
		}
	}

	@Test
	public void testAgregar() {
		setUpEscenario1();
		// ------------------------------[[PALABRA
		// CORTA]]------------------------------------
		// se evalua el estado del escenario, esperamos 7 elementos
		assertEquals(7, arbol.getNumeroElemtos());
		ClasePrueba palabraCorta = new ClasePrueba(6, "XXX::lol");
		try {
			arbol.agregar("lol", palabraCorta);
		} catch (palabraInvalidaException e) {
			System.out.println(">>BIEN, lol es muy chiquito no agrega\n");
		} catch (ElementoRepetidoException e) {
			fail("Esto no deveria pasar pues no he metido palabras repetidas");
		}
		// se evalua nuevamente para asegurar que no incluyo la palabra invalida
		assertEquals(7, arbol.getNumeroElemtos());
		// --------------------------[[ELEMENTO REPETIDO, MISMA
		// POSICION]-----------------------
		// el elemento repetido es igual a el 5to elemento que se agrega en el escenario
		ClasePrueba palabraRepetidaInvalida = new ClasePrueba(5, "XXX::estadiaticas de servicio");
		try {
			arbol.agregar("estadiaticas de servicio", palabraRepetidaInvalida);
		} catch (palabraInvalidaException e) {
			fail("Esto no deveria pasar pues no he metido palabras cortas");
		} catch (ElementoRepetidoException e) {
			System.out.println(">>BIEN, estadiaticas de servicio y su elemento estan repetidos, no agrega\n");
		}
		// se evalua nuevamente para asegurar que no incluyo la palabra invalida
		assertEquals(7, arbol.getNumeroElemtos());
		assertEquals(7, arbol.contarPalabras());
		// --------------------------[[ELEMENTO REPETIDO, MISMA
		// POSICION]-----------------------
		// el elemento repetido es igual a el 5to elemento que se agrega en el escenario
		ClasePrueba palabraRepetidaValida = new ClasePrueba(5, "XXX::estadiaticas de servicio");
		try {
			arbol.agregar("estadiaticas de meter palabra", palabraRepetidaValida);
		} catch (palabraInvalidaException e) {
			fail("Esto no deveria pasar pues no he metido palabras cortas");
		} catch (ElementoRepetidoException e) {
			fail("Esto no deveria pasar pues no he metido palabras repetidas");
		}
		// se evalua nuevamente para asegurar que no incluyo la palabra invalida
		assertEquals(8, arbol.getNumeroElemtos());
		assertEquals(7, arbol.contarPalabras());
		palabraRepetidaValida = new ClasePrueba(5, "diferente");
		try {
			arbol.agregar("estadiaticas de meter palabra", palabraRepetidaValida);
		} catch (palabraInvalidaException e) {
			fail("Esto no deveria pasar pues no he metido palabras cortas");
		} catch (ElementoRepetidoException e) {
			fail("Esto no deveria pasar pues no he metido palabras repetidas");
		}
	}

	@Test
	public void testBuscar() {
		// se carga el escenario sin repetidas
		setUpEscenario1();
		// se agregar repetidas para solo los casos impares(mirar escenario)
		setUpEscenario2();
		// ------------------------------[se esperan 2]-------------------------------
		Iterator<ClasePrueba> iter = arbol.buscar("arroz");
		int i = 0;
		while (iter.hasNext()) {
			ClasePrueba actual = (ClasePrueba) iter.next();
			if (i == 0) {
				assertEquals("XXX::arroz", actual.getText());
			} else if (i == 1) {
				assertEquals("X_X::arroz", actual.getText());
			} else {
				fail("no se  espera un mumero diferente de elementos");
			}
			i++;
		}
		assertEquals(2, i);
		// ------------------------------[se espera 1]-------------------------------
		i = 0;
		iter = arbol.buscar("arreglo");
		while (iter.hasNext()) {
			ClasePrueba actual = (ClasePrueba) iter.next();
			if (i == 0) {
				assertEquals("XXX::arreglo", actual.getText());
			} else {
				fail("no se  espera un mumero diferente de elementos");
			}
			i++;
		}
		assertEquals(1, i);
		// ------------------------------[se esperan 2]-------------------------------
		i = 0;
		iter = arbol.buscar("furia");
		while (iter.hasNext()) {
			ClasePrueba actual = (ClasePrueba) iter.next();
			if (i == 0) {
				assertEquals("XXX::furia", actual.getText());
			} else if (i == 1) {
				assertEquals("X_X::furia", actual.getText());
			} else {
				fail("no se  espera un mumero diferente de elementos");
			}
			i++;
		}
		assertEquals(2, i);
		// ------------------------------[se espera 1]-------------------------------
		i = 0;
		iter = arbol.buscar("fuego");
		while (iter.hasNext()) {
			ClasePrueba actual = (ClasePrueba) iter.next();
			if (i == 0) {
				assertEquals("XXX::fuego", actual.getText());
			} else {
				fail("no se  espera un mumero diferente de elementos");
			}
			i++;
		}
		assertEquals(1, i);
		// ------------------------------[se esperan 2]-------------------------------
		i = 0;
		iter = arbol.buscar("estadiaticas de servicio");
		while (iter.hasNext()) {
			ClasePrueba actual = (ClasePrueba) iter.next();
			if (i == 0) {
				assertEquals("XXX::estadiaticas de servicio", actual.getText());
			} else if (i == 1) {
				assertEquals("X_X::estadiaticas de servicio", actual.getText());
			} else {
				fail("no se  espera un mumero diferente de elementos");
			}
			i++;
		}
		assertEquals(2, i);
		// ------------------------------[se espera 1]-------------------------------
		i = 0;
		iter = arbol.buscar("lolol");
		while (iter.hasNext()) {
			ClasePrueba actual = (ClasePrueba) iter.next();
			if (i == 0) {
				assertEquals("XXX::lolol", actual.getText());
			} else {
				fail("no se  espera un mumero diferente de elementos");
			}
			i++;
		}
		assertEquals(1, i);
		// ------------------------------[se esperan 2]-------------------------------
		i = 0;
		iter = arbol.buscar("armagedon");
		while (iter.hasNext()) {
			ClasePrueba actual = (ClasePrueba) iter.next();
			if (i == 0) {
				assertEquals("XXX::armagedon", actual.getText());
			} else if (i == 1) {
				assertEquals("X_X::armagedon", actual.getText());
			} else {
				fail("no se  espera un mumero diferente de elementos");
			}
			i++;
		}
		assertEquals(2, i);
		// se espera que el arbol tenga 11 elementos
		assertEquals(11, arbol.getNumeroElemtos());
	}

	@Test
	public void testBusquedaPorPefijo() {
		// los prefijos en comun de laspalbras son arr,fu, ar
		setUpEscenario1();
		setUpEscenario2();
		// primer prefijo comun arr
		String prefijo = "arr";
		Iterator<ClasePrueba> iter = arbol.buscarPorPrefijo(prefijo);
		int contadorEncontradas = 0;
		while (iter.hasNext()) {
			ClasePrueba este = (ClasePrueba) iter.next();
			if (contadorEncontradas == 0) {
				assertEquals("XXX::arroz", este.getText());
			} else if (contadorEncontradas == 1) {
				assertEquals("X_X::arroz", este.getText());
			} else if (contadorEncontradas == 2) {
				assertEquals("XXX::arreglo", este.getText());
			} else {
				fail("no se encontro la cantidad esperada de elementos");
			}
			contadorEncontradas++;
		}
		assertEquals(3, contadorEncontradas);
		// segundo prefijo comun fu
		prefijo = "fu";
		iter = arbol.buscarPorPrefijo(prefijo);
		contadorEncontradas = 0;
		while (iter.hasNext()) {
			ClasePrueba este = (ClasePrueba) iter.next();
			if (contadorEncontradas == 0) {
				assertEquals("XXX::furia", este.getText());
			} else if (contadorEncontradas == 1) {
				assertEquals("X_X::furia", este.getText());
			} else if (contadorEncontradas == 2) {
				assertEquals("XXX::fuego", este.getText());
			} else {
				fail("no se encontro la cantidad esperada de elementos");
			}
			contadorEncontradas++;
		}
		assertEquals(3, contadorEncontradas);
		// tercer prefijo comun ar
		prefijo = "ar";
		iter = arbol.buscarPorPrefijo(prefijo);
		contadorEncontradas = 0;
		while (iter.hasNext()) {
			ClasePrueba este = (ClasePrueba) iter.next();
			if (contadorEncontradas == 0) {
				assertEquals("XXX::arroz", este.getText());
			} else if (contadorEncontradas == 1) {
				assertEquals("X_X::arroz", este.getText());
			} else if (contadorEncontradas == 2) {
				assertEquals("XXX::arreglo", este.getText());
			} else if (contadorEncontradas == 3) {
				assertEquals("XXX::armagedon", este.getText());
			} else if (contadorEncontradas == 4) {
				assertEquals("X_X::armagedon", este.getText());
			} else {
				fail("no se encontro la cantidad esperada de elementos");
			}
			contadorEncontradas++;
		}
		assertEquals(5, contadorEncontradas);
		// --------------------------ahora un elemento que esta bajo 2 nombres no deve
		// aparecer 2 veces
		ClasePrueba aInsertar6 = new ClasePrueba(6, "XXX::lolol");
		try {
			arbol.agregar("loliputa", aInsertar6);
		} catch (Exception e) {
			fail("no de veria pasar");
		}
		prefijo = "lol";
		iter = arbol.buscarPorPrefijo(prefijo);
		contadorEncontradas = 0;
		while (iter.hasNext()) {
			ClasePrueba este = (ClasePrueba) iter.next();
			if (contadorEncontradas == 0) {
				assertEquals("XXX::lolol", este.getText());
			} else {
				fail("no se encontro la cantidad esperada de elementos");
			}
			contadorEncontradas++;
		}
		assertEquals(1, contadorEncontradas);
	}

	@Test
	public void testEliminar() {
		setUpEscenario1();
		assertEquals(7, arbol.getNumeroElemtos());
		arbol.eliminar("arroz");
		assertEquals(6, arbol.getNumeroElemtos());
		assertEquals(6, arbol.contarPalabras());
	}

	@Test
	public void testPalabraMasLarga() {
		// escenario planeado para que lapalabra maslarga este un poco mas anidada en el
		// arbol que en el otro escenario
		setUpEscenario1_1();
		assertEquals("arreglo on", arbol.darPalabraMasLarga());
		Iterator<ClasePrueba> iter = arbol.ultimosN(40);
		int i = 0;
		while (iter.hasNext()) {
			ClasePrueba este = (ClasePrueba) iter.next();
			if (i == 0) {
				assertEquals("XXX::armagedon", este.getText());
			} else if (i == 1) {
				assertEquals("XXX::lolol", este.getText());
			} else if (i == 2) {
				assertEquals("XXX::fuego", este.getText());
			} else {
				// fail("no se esperan mas elementos");
			}
			i++;
		}
		System.out.println(i);
	}
}
