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

package ancheta.testArbolAVL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

import ancheta.arbolAVL.ArbolAVL;
import ancheta.arrayChimbo.ArrayChimbo;
import ancheta.comun.ClasePrueba;
import ancheta.listasCYP.IPilaEncadenada;

public class TestArbolAVL {

	private ArbolAVL<ClasePrueba> arbol = new ArbolAVL<ClasePrueba>();

	public void setUpEscenario1() {
		// escenario planeado para ver rotaciones simples
		ClasePrueba aInsertar1 = new ClasePrueba(1, "num05");
		ClasePrueba aInsertar2 = new ClasePrueba(2, "num10");
		ClasePrueba aInsertar3 = new ClasePrueba(3, "num15");
		ClasePrueba aInsertar4 = new ClasePrueba(4, "num20");
		ClasePrueba aInsertar5 = new ClasePrueba(5, "num25");
		ClasePrueba aInsertar6 = new ClasePrueba(6, "num30");
		ClasePrueba aInsertar7 = new ClasePrueba(7, "num35");
		ClasePrueba aInsertar8 = new ClasePrueba(8, "num40");
		System.out.println("ARBOL N 1");
		System.out.println("____________________________________________________");
		arbol.agregar(aInsertar1, aInsertar1.getText());
		arbol.agregar(aInsertar2, aInsertar2.getText());
		arbol.agregar(aInsertar3, aInsertar3.getText());
		// balanceo (num15)
		arbol.agregar(aInsertar4, aInsertar4.getText());
		arbol.agregar(aInsertar5, aInsertar5.getText());
		// balanceo (num25)
		arbol.agregar(aInsertar6, aInsertar6.getText());
		// balanceo (num30)
		arbol.agregar(aInsertar7, aInsertar7.getText());
		// balanceo (num35)
		arbol.agregar(aInsertar8, aInsertar8.getText());
		System.out.println("____________________________");
	}

	public void setUpEscenario2() {
		// escenario planeado para ver rotaciones simples
		ClasePrueba aInsertar1 = new ClasePrueba(1, "num40");
		ClasePrueba aInsertar2 = new ClasePrueba(2, "num35");
		ClasePrueba aInsertar3 = new ClasePrueba(3, "num30");
		ClasePrueba aInsertar4 = new ClasePrueba(4, "num25");
		ClasePrueba aInsertar5 = new ClasePrueba(5, "num20");
		ClasePrueba aInsertar6 = new ClasePrueba(6, "num15");
		ClasePrueba aInsertar7 = new ClasePrueba(7, "num10");
		ClasePrueba aInsertar8 = new ClasePrueba(8, "num05");
		System.out.println("ARBOL N 2");
		System.out.println("____________________________");
		arbol.agregar(aInsertar1, aInsertar1.getText());
		arbol.agregar(aInsertar2, aInsertar2.getText());
		arbol.agregar(aInsertar3, aInsertar3.getText());
		// balanceo (num30)
		arbol.agregar(aInsertar4, aInsertar4.getText());
		arbol.agregar(aInsertar5, aInsertar5.getText());
		// balanceo (num20)
		arbol.agregar(aInsertar6, aInsertar6.getText());
		// balanceo (num15)
		arbol.agregar(aInsertar7, aInsertar7.getText());
		// balanceo (num10)
		arbol.agregar(aInsertar8, aInsertar8.getText());
		System.out.println("____________________________________________________");
	}

	public void setUpEscenario3() {
		// escenario planeado para ver rotaciones simples
		ClasePrueba aInsertar1 = new ClasePrueba(1, "num10");
		ClasePrueba aInsertar2 = new ClasePrueba(2, "num05");
		ClasePrueba aInsertar3 = new ClasePrueba(3, "num15");
		ClasePrueba aInsertar4 = new ClasePrueba(4, "num02");
		ClasePrueba aInsertar5 = new ClasePrueba(5, "num06");
		ClasePrueba aInsertar6 = new ClasePrueba(6, "num04");
		ClasePrueba aInsertar7 = new ClasePrueba(7, "num01");
		System.out.println("ARBOL N 3");
		System.out.println("____________________________");
		arbol.agregar(aInsertar1, aInsertar1.getText());
		arbol.agregar(aInsertar2, aInsertar2.getText());
		arbol.agregar(aInsertar3, aInsertar3.getText());
		arbol.agregar(aInsertar4, aInsertar4.getText());
		arbol.agregar(aInsertar5, aInsertar5.getText());
		arbol.agregar(aInsertar6, aInsertar6.getText());
		// balanceo (num04)
		arbol.agregar(aInsertar7, aInsertar7.getText());
		System.out.println("____________________________________________________");
	}

	public void setUpEscenario4() {
		// escenario planeado para ver rotaciones dobles
		ClasePrueba aInsertar1 = new ClasePrueba(1, "num10");
		ClasePrueba aInsertar2 = new ClasePrueba(2, "num25");
		ClasePrueba aInsertar3 = new ClasePrueba(3, "num90");
		ClasePrueba aInsertar4 = new ClasePrueba(4, "num50");
		ClasePrueba aInsertar5 = new ClasePrueba(5, "num60");
		ClasePrueba aInsertar6 = new ClasePrueba(6, "num65");
		ClasePrueba aInsertar7 = new ClasePrueba(7, "num70");
		ClasePrueba aInsertarRep = new ClasePrueba(8, "num75");
		System.out.println("ARBOL N 4");
		System.out.println("____________________________________________________");
		arbol.agregar(aInsertar1, aInsertar1.getText());
		arbol.agregar(aInsertar2, aInsertar2.getText());
		arbol.agregar(aInsertar3, aInsertar3.getText());
		// balanceo (num90)
		arbol.agregar(aInsertar4, aInsertar4.getText());
		arbol.agregar(aInsertar5, aInsertar5.getText());
		// balanceo Doble(~num 60~)
		arbol.agregar(aInsertar6, aInsertar6.getText());
		// balanceo (num65)
		arbol.agregar(aInsertar7, aInsertar7.getText());
		// balanceo Doble(~num70~)
		arbol.agregar(aInsertarRep, aInsertarRep.getText());
		System.out.println("____________________________________________________");
	}

	public void setUpEscenario5() {
		// escenario planeado para ver rotaciones de ambos tipos; y un elemento repetido
		ClasePrueba aInsertar1 = new ClasePrueba(1, "num2");
		ClasePrueba aInsertar2 = new ClasePrueba(2, "num4");
		ClasePrueba aInsertar3 = new ClasePrueba(3, "num6");
		ClasePrueba aInsertar4 = new ClasePrueba(4, "num7");
		ClasePrueba aInsertar5 = new ClasePrueba(5, "num8");
		ClasePrueba aInsertar6 = new ClasePrueba(6, "num5");
		ClasePrueba aInsertar7 = new ClasePrueba(7, "num1");
		ClasePrueba aInsertar8 = new ClasePrueba(8, "num5");
		System.out.println("ARBOL N 5");
		System.out.println("____________________________________________________");
		arbol.agregar(aInsertar1, aInsertar1.getText());
		arbol.agregar(aInsertar2, aInsertar2.getText());
		arbol.agregar(aInsertar3, aInsertar3.getText());
		// balancea (num6)
		arbol.agregar(aInsertar4, aInsertar4.getText());
		arbol.agregar(aInsertar5, aInsertar5.getText());
		// balancea (num8)
		arbol.agregar(aInsertar6, aInsertar6.getText());
		// balanceo Doble!! (num5)
		arbol.agregar(aInsertar7, aInsertar7.getText());
		arbol.agregar(aInsertar8, aInsertar8.getText());
		System.out.println("____________________________________________________");
	}

	@Test
	public void testInsercion1Rotacion() {
		// se carga el primero de 2 escenarios(esta es de rotacion sencilla hacia
		// izquierda)
		setUpEscenario1();
		// se espera cierta altura , peso, y cantidad de balanceos(sencillos y dobles)
		assertEquals(4, arbol.getAltura());
		assertEquals(8, arbol.getPeso());
		assertEquals(4, arbol.getBalanceosSencillos());
		assertEquals(0, arbol.getBalanceosDobles());
		// se evalua que este ordenado haciendo un recorro inOrden
		Iterator<ClasePrueba> iterador = arbol.iterator();
		int i = 0;
		while (iterador.hasNext()) {
			ClasePrueba este = (ClasePrueba) iterador.next();
			if (i == 0) {
				assertEquals("num05", este.getText());
			} else if (i == 1) {
				assertEquals("num10", este.getText());
			} else if (i == 2) {
				assertEquals("num15", este.getText());
			} else if (i == 3) {
				assertEquals("num20", este.getText());
			} else if (i == 4) {
				assertEquals("num25", este.getText());
			} else if (i == 5) {
				assertEquals("num30", este.getText());
			} else if (i == 6) {
				assertEquals("num35", este.getText());
			} else if (i == 7) {
				assertEquals("num40", este.getText());
			}
			i++;
		}
		// se evalua cada uno de los niveles para confirmar que es un arbol AVL
		// nivel 1, la raiz
		IPilaEncadenada<String> enNivel = arbol.darNivel(1);
		Iterator<String> iterNiveles = enNivel.iterator();
		int index = 0;
		while (iterNiveles.hasNext()) {
			String este = (String) iterNiveles.next();
			if (index == 0) {
				assertEquals("num20", este);
			} else if (index > 0) {
				fail("no deberian haber mas elementos  en este nivel");
			}
			index++;
		}
		// nivel 2
		enNivel = arbol.darNivel(2);
		iterNiveles = enNivel.iterator();
		index = 0;
		while (iterNiveles.hasNext()) {
			String este = (String) iterNiveles.next();
			if (index == 0) {
				assertEquals("num10", este);
			} else if (index == 1) {
				assertEquals("num30", este);
			} else if (index > 1) {
				fail("no deberian haber mas elementos  en este nivel");
			}
			index++;
		}
		// nivel 3
		enNivel = arbol.darNivel(3);
		iterNiveles = enNivel.iterator();
		index = 0;
		while (iterNiveles.hasNext()) {
			String este = (String) iterNiveles.next();
			if (index == 0) {
				assertEquals("num05", este);
			} else if (index == 1) {
				assertEquals("num15", este);
			} else if (index == 2) {
				assertEquals("num25", este);
			} else if (index == 3) {
				assertEquals("num35", este);
			} else if (index > 3) {
				fail("no deberian haber mas elementos  en este nivel");
			}
			index++;
		}
		// nivel 4
		enNivel = arbol.darNivel(4);
		iterNiveles = enNivel.iterator();
		index = 0;
		while (iterNiveles.hasNext()) {
			String este = (String) iterNiveles.next();
			if (index == 0) {
				assertEquals("num40", este);
			} else if (index > 0) {
				fail("no deberian haber mas elementos  en este nivel");
			}
			index++;
		}
		// se vacia el arbol para hacer la segunda prueva
		arbol.vaciar();
		assertEquals(0, arbol.getAltura());
		assertEquals(0, arbol.getPeso());
		assertEquals(0, arbol.getBalanceosSencillos());
		assertEquals(0, arbol.getBalanceosDobles());
		// se carga el segundo escenario(de rotaciones sensillas hacia la derecha)
		setUpEscenario2();
		// se espera cierta altura , peso, y cantidad de balanceos(sencillos y dobles)
		assertEquals(8, arbol.getPeso());
		assertEquals(4, arbol.getBalanceosSencillos());
		assertEquals(0, arbol.getBalanceosDobles());
		// se evalua que este ordenado haciendo un recorro inOrden
		iterador = arbol.iterator();
		i = 0;
		while (iterador.hasNext()) {
			ClasePrueba este = (ClasePrueba) iterador.next();
			if (i == 0) {
				assertEquals("num05", este.getText());
			} else if (i == 1) {
				assertEquals("num10", este.getText());
			} else if (i == 2) {
				assertEquals("num15", este.getText());
			} else if (i == 3) {
				assertEquals("num20", este.getText());
			} else if (i == 4) {
				assertEquals("num25", este.getText());
			} else if (i == 5) {
				assertEquals("num30", este.getText());
			} else if (i == 6) {
				assertEquals("num35", este.getText());
			} else if (i == 7) {
				assertEquals("num40", este.getText());
			}
			i++;
		}
		// se evalua cada uno de los niveles para confirmar que es un arbol AVL
		// nivel 1, la raiz
		assertEquals(4, arbol.getAltura());
		enNivel = arbol.darNivel(1);
		iterNiveles = enNivel.iterator();
		index = 0;
		while (iterNiveles.hasNext()) {
			String este = (String) iterNiveles.next();
			if (index == 0) {
				assertEquals("num25", este);
			} else if (index > 0) {
				fail("no deberian haber mas elementos  en este nivel");
			}
			index++;
		}
		// nivel 2
		enNivel = arbol.darNivel(2);
		iterNiveles = enNivel.iterator();
		index = 0;
		while (iterNiveles.hasNext()) {
			String este = (String) iterNiveles.next();
			if (index == 0) {
				assertEquals("num15", este);
			} else if (index == 1) {
				assertEquals("num35", este);
			} else if (index > 1) {
				fail("no deberian haber mas elementos  en este nivel");
			}
			index++;
		}
		// nivel 3
		enNivel = arbol.darNivel(3);
		iterNiveles = enNivel.iterator();
		index = 0;
		while (iterNiveles.hasNext()) {
			String este = (String) iterNiveles.next();
			if (index == 0) {
				assertEquals("num10", este);
			} else if (index == 1) {
				assertEquals("num20", este);
			} else if (index == 2) {
				assertEquals("num30", este);
			} else if (index == 3) {
				assertEquals("num40", este);
			} else if (index > 3) {
				fail("no deberian haber mas elementos  en este nivel");
			}
			index++;
		}
		// nivel 4
		enNivel = arbol.darNivel(4);
		iterNiveles = enNivel.iterator();
		index = 0;
		while (iterNiveles.hasNext()) {
			String este = (String) iterNiveles.next();
			if (index == 0) {
				assertEquals("num05", este);
			} else if (index > 0) {
				fail("no deberian haber mas elementos  en este nivel");
			}
			index++;
		}
	}

	@Test
	public void testInsercion1RotacionDeEsteticaRara() {
		// test de rotacion sencillas
		// se espera cierta altura , peso, y cantidad de balanceos(sencillos y dobles)
		setUpEscenario3();
		assertEquals(3, arbol.getAltura());
		assertEquals(7, arbol.getPeso());
		assertEquals(1, arbol.getBalanceosSencillos());
		assertEquals(0, arbol.getBalanceosDobles());
		// se evalua que este ordenado haciendo un recorro inOrden
		Iterator<ClasePrueba> iter = arbol.iterator();
		int i = 0;
		while (iter.hasNext()) {
			ClasePrueba este = (ClasePrueba) iter.next();
			if (i == 0) {
				assertEquals("num01", este.getText());
			} else if (i == 1) {
				assertEquals("num02", este.getText());
			} else if (i == 2) {
				assertEquals("num04", este.getText());
			} else if (i == 3) {
				assertEquals("num05", este.getText());
			} else if (i == 4) {
				assertEquals("num06", este.getText());
			} else if (i == 5) {
				assertEquals("num10", este.getText());
			} else if (i == 7) {
				assertEquals("num15", este.getText());
			}
			i++;
		}
		// se evalua cada uno de los niveles para confirmar que es un arbol AVL
		// nivel 1, la raiz
		IPilaEncadenada<String> enNivel = arbol.darNivel(1);
		Iterator<String> iterNiveles = enNivel.iterator();
		int index = 0;
		while (iterNiveles.hasNext()) {
			String este = (String) iterNiveles.next();
			if (index == 0) {
				assertEquals("num05", este);
			} else if (index > 0) {
				fail("no deberian haber mas elementos  en este nivel");
			}
			index++;
		}
		// nivel 2
		enNivel = arbol.darNivel(2);
		iterNiveles = enNivel.iterator();
		index = 0;
		while (iterNiveles.hasNext()) {
			String este = (String) iterNiveles.next();
			if (index == 0) {
				assertEquals("num02", este);
			} else if (index == 1) {
				assertEquals("num10", este);
			} else if (index > 1) {
				fail("no deberian haber mas elementos  en este nivel");
			}
			index++;
		}
		// nivel 3
		enNivel = arbol.darNivel(3);
		iterNiveles = enNivel.iterator();
		index = 0;
		while (iterNiveles.hasNext()) {
			String este = (String) iterNiveles.next();
			if (index == 0) {
				assertEquals("num01", este);
			} else if (index == 1) {
				assertEquals("num04", este);
			} else if (index == 2) {
				assertEquals("num06", este);
			} else if (index == 3) {
				assertEquals("num15", este);
			} else if (index > 3) {
				fail("no deberian haber mas elementos  en este nivel");
			}
			index++;
		}
	}

	@Test
	public void testInsercionDobleRotacion() {
		// test de rotacion doble y sencillas
		// se espera cierta altura , peso, y cantidad de balanceos(sencillos y dobles)
		setUpEscenario4();
		assertEquals(4, arbol.getAltura());
		assertEquals(8, arbol.getPeso());
		assertEquals(2, arbol.getBalanceosSencillos());
		assertEquals(2, arbol.getBalanceosDobles());
		// se evalua que este ordenado haciendo un recorro inOrden
		Iterator<ClasePrueba> iter = arbol.iterator();
		int i = 0;
		while (iter.hasNext()) {
			ClasePrueba este = (ClasePrueba) iter.next();
			if (i == 0) {
				assertEquals("num10", este.getText());
			} else if (i == 1) {
				assertEquals("num25", este.getText());
			} else if (i == 2) {
				assertEquals("num50", este.getText());
			} else if (i == 3) {
				assertEquals("num60", este.getText());
			} else if (i == 4) {
				assertEquals("num65", este.getText());
			} else if (i == 5) {
				assertEquals("num70", este.getText());
			} else if (i == 6) {
				assertEquals("num75", este.getText());
			} else if (i == 7) {
				assertEquals("num90", este.getText());
			}
			i++;
		}
		// se evalua cada uno de los niveles para confirmar que es un arbol AVL
		// nivel 1, la raiz
		IPilaEncadenada<String> enNivel = arbol.darNivel(1);
		Iterator<String> iterNiveles = enNivel.iterator();
		int index = 0;
		while (iterNiveles.hasNext()) {
			String este = (String) iterNiveles.next();
			if (index == 0) {
				assertEquals("num60", este);
			} else if (index > 0) {
				fail("no deberian haber mas elementos  en este nivel");
			}
			index++;
		}
		// nivel 2
		enNivel = arbol.darNivel(2);
		iterNiveles = enNivel.iterator();
		index = 0;
		while (iterNiveles.hasNext()) {
			String este = (String) iterNiveles.next();
			if (index == 0) {
				assertEquals("num25", este);
			} else if (index == 1) {
				assertEquals("num70", este);
			} else if (index > 1) {
				fail("no deberian haber mas elementos  en este nivel");
			}
			index++;
		}
		// nivel 3
		enNivel = arbol.darNivel(3);
		iterNiveles = enNivel.iterator();
		index = 0;
		while (iterNiveles.hasNext()) {
			String este = (String) iterNiveles.next();
			if (index == 0) {
				assertEquals("num10", este);
			} else if (index == 1) {
				assertEquals("num50", este);
			} else if (index == 2) {
				assertEquals("num65", este);
			} else if (index == 3) {
				assertEquals("num90", este);
			} else if (index > 3) {
				fail("no deberian haber mas elementos  en este nivel");
			}
			index++;
		}
		// nivel 4
		enNivel = arbol.darNivel(4);
		iterNiveles = enNivel.iterator();
		index = 0;
		while (iterNiveles.hasNext()) {
			String este = (String) iterNiveles.next();
			if (index == 0) {
				assertEquals("num75", este);
			} else if (index > 0) {
				fail("no deberian haber mas elementos  en este nivel");
			}
			index++;
		}
	}

	@Test
	public void testInsercionHijosEnLCasoEspecial() {
		// este test es de rotacion sencilla y doble, con un elemento repetido y una
		// rotacion doble extrania
		setUpEscenario5();
		// se espera cierta altura , peso, y cantidad de balanceos(sencillos y dobles)
		assertEquals(4, arbol.getAltura());
		assertEquals(8, arbol.getPeso());
		assertEquals(2, arbol.getBalanceosSencillos());
		assertEquals(1, arbol.getBalanceosDobles());
		// se evalua que este ordenado haciendo un recorro inOrden
		Iterator<ClasePrueba> iterador = arbol.iterator();
		int i = 0;
		while (iterador.hasNext()) {
			ClasePrueba este = (ClasePrueba) iterador.next();
			if (i == 0) {
				assertEquals("num1", este.getText());
			} else if (i == 1) {
				assertEquals("num2", este.getText());
			} else if (i == 2) {
				assertEquals("num4", este.getText());
			} else if (i == 3) {
				assertEquals("num5", este.getText());
			} else if (i == 4) {
				assertEquals("num5", este.getText());
			} else if (i == 5) {
				assertEquals("num6", este.getText());
			} else if (i == 6) {
				assertEquals("num7", este.getText());
			} else if (i == 7) {
				assertEquals("num8", este.getText());
			} else if (i > 7) {
				fail("no deberian haber mas elementos en el iterador");
			}
			i++;
		}
		// se evalua cada uno de los niveles para confirmar que es un arbol AVL
		// nivel 1, la raiz
		IPilaEncadenada<String> enNivel = arbol.darNivel(1);
		Iterator<String> iterNiveles = enNivel.iterator();
		int index = 0;
		while (iterNiveles.hasNext()) {
			String este = (String) iterNiveles.next();
			if (index == 0) {
				assertEquals("num6", este);
			} else if (index > 0) {
				fail("no deberian haber mas elementos  en este nivel");
			}
			index++;
		}
		// nivel 2
		enNivel = arbol.darNivel(2);
		iterNiveles = enNivel.iterator();
		index = 0;
		while (iterNiveles.hasNext()) {
			String este = (String) iterNiveles.next();
			if (index == 0) {
				assertEquals("num4", este);
			} else if (index == 1) {
				assertEquals("num7", este);
			} else if (index > 1) {
				fail("no deberian haber mas elementos  en este nivel");
			}
			index++;
		}
		// nivel 3
		enNivel = arbol.darNivel(3);
		iterNiveles = enNivel.iterator();
		index = 0;
		while (iterNiveles.hasNext()) {
			String este = (String) iterNiveles.next();
			if (index == 0) {
				assertEquals("num2", este);
			} else if (index == 1) {
				assertEquals("num5", este);
			} else if (index == 2) {
				assertEquals("num8", este);
			} else if (index > 2) {
				fail("no deberian haber mas elementos  en este nivel");
			}
			index++;
		}
		// nivel 4
		enNivel = arbol.darNivel(4);
		iterNiveles = enNivel.iterator();
		index = 0;
		while (iterNiveles.hasNext()) {
			String este = (String) iterNiveles.next();
			if (index == 0) {
				assertEquals("num1", este);
			} else if (index > 0) {
				fail("no deberian haber mas elementos  en este nivel");
			}
			index++;
		}
	}

	@Test
	public void testGet() {
		// cargamos este escenario por que es el unico que lleva un elemento repetido
		setUpEscenario5();
		// el elemento repetido es cualquiera que se indexe con la llave num 5==> 6 & 8
		ArrayChimbo<ClasePrueba> rtaBusqueda = arbol.getGroup("num5");
		for (int i = 0; i < rtaBusqueda.getSize(); i++) {
			ClasePrueba este = (ClasePrueba) rtaBusqueda.get(i);
			if (i == 0) {
				assertEquals("num5", este.getText());
				assertEquals(6, este.getId());
			} else if (i == 1) {
				assertEquals("num5", este.getText());
				assertEquals(8, este.getId());
			} else if (i > 1) {
				fail("no se esperan mas elementos");
			}
		}
		// otra busqueda con solo un elemento
		assertEquals(2, rtaBusqueda.getSize());
		rtaBusqueda = arbol.getGroup("num6");
		for (int i = 0; i < rtaBusqueda.getSize(); i++) {
			ClasePrueba este = (ClasePrueba) rtaBusqueda.get(i);
			if (i == 0) {
				assertEquals("num6", este.getText());
				assertEquals(3, este.getId());
			} else if (i > 0) {
				fail("no se esperan mas elementos");
			}
		}
		// una busqueda de un elemento que no esta en el arbol
		assertEquals(1, rtaBusqueda.getSize());
		rtaBusqueda = arbol.getGroup("no_existe");
		for (int i = 0; i < rtaBusqueda.getSize(); i++) {
			fail("no se esperan elementos");
		}
		assertEquals(0, rtaBusqueda.getSize());
	}
}
