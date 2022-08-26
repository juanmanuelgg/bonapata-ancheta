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

package ancheta.testListas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

import ancheta.comun.ClasePrueba;
import ancheta.listasCYP.IPilaEncadenada;
import ancheta.listasCYP.ListaYPila;

public class PilaTest {

	private IPilaEncadenada<ClasePrueba> pila = new ListaYPila<ClasePrueba>();

	public void setUpEscenario1_1() {
		ClasePrueba aInsertar1 = new ClasePrueba(1, "num1");
		pila.agregarEnPila(aInsertar1);
	}

	public void setUpEscenario1_2() {

		ClasePrueba aInsertar2 = new ClasePrueba(2, "num2");
		ClasePrueba aInsertar3 = new ClasePrueba(3, "num3");
		ClasePrueba aInsertar4 = new ClasePrueba(4, "num4");
		ClasePrueba aInsertar5 = new ClasePrueba(5, "num5");
		ClasePrueba aInsertar6 = new ClasePrueba(6, "num6");
		ClasePrueba aInsertar7 = new ClasePrueba(7, "num7");

		pila.agregarEnPila(aInsertar2);
		pila.agregarEnPila(aInsertar3);
		pila.agregarEnPila(aInsertar4);
		pila.agregarEnPila(aInsertar5);
		pila.agregarEnPila(aInsertar6);
		// se manda un elemento repetido
		pila.agregarEnPila(aInsertar5);
		pila.agregarEnPila(aInsertar7);
	}

	public void setUpEscenario2() {
		ClasePrueba aInsertar1 = new ClasePrueba(1, "num1");
		ClasePrueba aInsertar2 = new ClasePrueba(2, "num2");
		ClasePrueba aInsertar3 = new ClasePrueba(3, "num3");
		ClasePrueba aInsertar4 = new ClasePrueba(4, "num4");
		pila.agregarEnPila(aInsertar1);
		pila.agregarEnPila(aInsertar2);
		pila.agregarEnPila(aInsertar3);
		// se manda un elemento repetido
		pila.agregarEnPila(aInsertar2);
		pila.agregarEnPila(aInsertar4);
	}

	/**
	 * Prueva el comportamiento de la pila esperando que se comporte como una
	 * pila(Estructura de datos)
	 */
	@Test
	public void testPila() {
		// se evalua el comportamiento de la pila sin elementos
		assertEquals(0, pila.darCantidadDeElementos());
		assertNull(pila.tomarElemento());
		// se evalua el comportamiento con solo un elemento
		setUpEscenario1_1();
		assertEquals(1, pila.darCantidadDeElementos());
		assertEquals("num1", pila.tomarElemento().getText());
		assertEquals(0, pila.darCantidadDeElementos());
		// se evalua para un conjunto demayor tamaño
		setUpEscenario1_1();
		assertEquals(1, pila.darCantidadDeElementos());
		setUpEscenario1_2();
		assertEquals(8, pila.darCantidadDeElementos());
		assertEquals("num1", pila.tomarElemento().getText());
		assertEquals(7, pila.darCantidadDeElementos());
		assertEquals("num2", pila.tomarElemento().getText());
		assertEquals(6, pila.darCantidadDeElementos());
		assertEquals("num3", pila.tomarElemento().getText());
		assertEquals(5, pila.darCantidadDeElementos());
		assertEquals("num4", pila.tomarElemento().getText());
		assertEquals(4, pila.darCantidadDeElementos());
		assertEquals("num5", pila.tomarElemento().getText());
		assertEquals(3, pila.darCantidadDeElementos());
		assertEquals("num6", pila.tomarElemento().getText());
		assertEquals(2, pila.darCantidadDeElementos());
		assertEquals("num5", pila.tomarElemento().getText());
		assertEquals(1, pila.darCantidadDeElementos());
		assertEquals("num7", pila.tomarElemento().getText());
		assertEquals(0, pila.darCantidadDeElementos());
		assertNull(pila.tomarElemento());
		assertEquals(0, pila.darCantidadDeElementos());
		assertNull(pila.tomarElemento());
	}

	@Test
	public void testIterador() {
		// se evalua el comportamiento de la pila sin elementos
		assertEquals(0, pila.darCantidadDeElementos());
		assertNull(pila.tomarElemento());
		// se carga para menos casos
		setUpEscenario2();
		assertEquals(5, pila.darCantidadDeElementos());
		Iterator<ClasePrueba> iter = pila.iterator();
		int i = 0;
		while (iter.hasNext()) {
			ClasePrueba este = (ClasePrueba) iter.next();
			if (i == 0) {
				assertEquals("num1", este.getText());
			} else if (i == 1) {
				assertEquals("num2", este.getText());
			} else if (i == 2) {
				assertEquals("num3", este.getText());
			} else if (i == 3) {
				assertEquals("num2", este.getText());
			} else if (i == 4) {
				assertEquals("num4", este.getText());
			}
			i++;
		}
		assertEquals(5, pila.darCantidadDeElementos());
	}

}
