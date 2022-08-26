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

package ancheta.testListaDoblementeEncadenada;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import ancheta.comun.ClasePrueba;
import ancheta.exceptions.ElementoRepetidoException;
import ancheta.listaDoble.Lista2bleEncadenada;

public class TestListaDoble {

	private Lista2bleEncadenada<ClasePrueba> listaD = new Lista2bleEncadenada<ClasePrueba>();

	public void setUpEscenario1() {
		ClasePrueba obj1 = new ClasePrueba(1, "num1");
		ClasePrueba obj2 = new ClasePrueba(1, "num2");
		ClasePrueba obj2_2 = new ClasePrueba(2, "num2");
		ClasePrueba obj3 = new ClasePrueba(3, "num3");
		meterPaLaLista(obj1);
		meterPaLaLista(obj2);
		meterPaLaLista(obj2_2);
		meterPaLaLista(obj3);
	}

	private void meterPaLaLista(Object elemnt) {
		try {
			listaD.agregar((ClasePrueba) elemnt);
		} catch (ElementoRepetidoException e) {
		}
	}

	@Test
	public void testAgregarYBuscar() {
		setUpEscenario1();
		assertEquals(3, listaD.darCantidadDeElementos());
		ClasePrueba mascara = new ClasePrueba(2, null);
		assertEquals("num2", listaD.buscar(mascara).toString());
		assertEquals(3, listaD.darCantidadDeElementos());
	}
}
