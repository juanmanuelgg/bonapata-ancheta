package ancheta.testArrayChimbo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

import ancheta.arrayChimbo.ArrayChimbo;
import ancheta.comun.ClasePrueba;

public class TestArrayChimbo {

	private ArrayChimbo<ClasePrueba> arrayChimbo = new ArrayChimbo<ClasePrueba>();

	public void setupEscenario1_1() {
		ClasePrueba lol6 = new ClasePrueba(91, "Francisco");// 7 segun id
		ClasePrueba lol7 = new ClasePrueba(38, "Gloria");// 3 segun id
		ClasePrueba lol8 = new ClasePrueba(68, "Hector");// 5 segun id
		ClasePrueba lol9 = new ClasePrueba(77, "Ines");// 6 segun id
		ClasePrueba lol10 = new ClasePrueba(13, "Juan");// 2 segun id
		ClasePrueba lol11 = new ClasePrueba(53, "Kevin");// 4 segun id
		ClasePrueba lol12 = new ClasePrueba(8, "laura");// 1 segun id

		arrayChimbo.agregar(lol6);
		arrayChimbo.agregar(lol7);
		arrayChimbo.agregar(lol8);
		arrayChimbo.agregar(lol9);
		arrayChimbo.agregar(lol10);
		arrayChimbo.agregar(lol11);
		arrayChimbo.agregar(lol12);
	}

	@Test
	public void testPut() {
		setupEscenario1_1();
		for (int i = 0; i < arrayChimbo.getSize(); i++) {
			ClasePrueba actual = arrayChimbo.get(i);
			if (i == 0) {
				assertEquals("Francisco", actual.getText());
			} else if (i == 1) {
				assertEquals("Gloria", actual.getText());
			} else if (i == 2) {
				assertEquals("Hector", actual.getText());
			} else if (i == 3) {
				assertEquals("Ines", actual.getText());
			} else if (i == 4) {
				assertEquals("Juan", actual.getText());
			} else if (i == 5) {
				assertEquals("Kevin", actual.getText());
			} else if (i == 6) {
				assertEquals("laura", actual.getText());
			}
		}
	}

	@Test
	public void testCantidadElementos() {
		setupEscenario1_1();
		assertEquals(7, arrayChimbo.getSize());
	}

	@Test
	public void testGet() {
		assertNull(arrayChimbo.get(0));
		setupEscenario1_1();
		assertEquals("laura", arrayChimbo.get(6).getText());

	}

	@Test
	public void testGetRandom() {
		assertNull(arrayChimbo.getRandom());
		setupEscenario1_1();
		ClasePrueba temp = new ClasePrueba(4, "rancho");
		arrayChimbo.agregar(temp);
		String text;
		int cont = 0;
		for (int i = 0; i < 50; i++) {
			text = arrayChimbo.getRandom().getText();
			if (temp.getText().equals(text)) {
				cont++;
			}
		}
		assertTrue(cont < 50);

	}

	@Test
	public void testRemove() {
		ClasePrueba lol8 = new ClasePrueba(68, "Hector");
		arrayChimbo.agregar(lol8);
		assertEquals(1, arrayChimbo.getSize());
		arrayChimbo.remove(0);
		assertEquals(0, arrayChimbo.getSize());
		assertNull(arrayChimbo.remove(0));
		assertEquals(0, arrayChimbo.getSize());
		setupEscenario1_1();
		assertEquals(7, arrayChimbo.getSize());
		for (int i = 0; i < arrayChimbo.getSize(); i++) {
			ClasePrueba este = arrayChimbo.get(i);
			if (este.getText().equals("Hector") || este.getText().equals("Juan")) {
				arrayChimbo.remove(i);
			}
		}
		assertEquals(5, arrayChimbo.getSize());
		for (int i = 0; i < arrayChimbo.getSize(); i++) {
			ClasePrueba actual = arrayChimbo.get(i);
			if (i == 0) {
				assertEquals("Francisco", actual.getText());
			} else if (i == 1) {
				assertEquals("Gloria", actual.getText());
			} else if (i == 2) {
				assertEquals("Ines", actual.getText());
			} else if (i == 3) {
				assertEquals("Kevin", actual.getText());
			} else if (i == 4) {
				assertEquals("laura", actual.getText());
			}
		}
	}

	@Test
	public void testIterator() {
		setupEscenario1_1();

		int i = 0;
		Iterator<ClasePrueba> titerador = arrayChimbo.iterator();
		while (titerador.hasNext()) {
			ClasePrueba actual = (ClasePrueba) titerador.next();
			if (i == 0) {
				assertEquals("Francisco", actual.getText());
			} else if (i == 1) {
				assertEquals("Gloria", actual.getText());
			} else if (i == 2) {
				assertEquals("Hector", actual.getText());
			} else if (i == 3) {
				assertEquals("Ines", actual.getText());
			} else if (i == 4) {
				assertEquals("Juan", actual.getText());
			} else if (i == 5) {
				assertEquals("Kevin", actual.getText());
			} else if (i == 6) {
				assertEquals("laura", actual.getText());
			}
			i++;
		}
	}

	@Test
	public void testSort() {
		setupEscenario1_1();
		Object[] copiaOrdenada = arrayChimbo.sort();
		for (int i = 0; i < copiaOrdenada.length; i++) {
			ClasePrueba actual = (ClasePrueba) copiaOrdenada[i];
			if (i == 0) {
				assertEquals("laura", actual.getText());
			} else if (i == 1) {
				assertEquals("Juan", actual.getText());
			} else if (i == 2) {
				assertEquals("Gloria", actual.getText());
			} else if (i == 3) {
				assertEquals("Kevin", actual.getText());
			} else if (i == 4) {
				assertEquals("Hector", actual.getText());
			} else if (i == 5) {
				assertEquals("Ines", actual.getText());
			} else if (i == 6) {
				assertEquals("Francisco", actual.getText());
			}
		}
	}

}
