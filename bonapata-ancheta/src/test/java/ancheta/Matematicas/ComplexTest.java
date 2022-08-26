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

package ancheta.Matematicas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ComplexTest {
	private static final double DELTA = 1e-15;

	private static double[] a = { 5.0, 6.0 };
	private static double[] b = { -3.0, 4.0 };

	@Test
	public void testRepresentacion() {
		assertEquals("5.0 + 6.0i", Complex.toString(a));
		assertEquals("-3.0 + 4.0i", Complex.toString(b));
		assertEquals(5.0, Complex.re(a), DELTA);
		assertEquals(6.0, Complex.im(a), DELTA);
	}

	@Test
	public void testAritmetica() {
		assertEquals("2.0 + 10.0i", Complex.toString(Complex.plus(b, a)));
		assertEquals("8.0 + 2.0i", Complex.toString(Complex.minus(a, b)));
		assertEquals("-39.0 + 2.0i", Complex.toString(Complex.mult(a, b)));
		assertEquals("-39.0 + 2.0i", Complex.toString(Complex.mult(b, a)));
		assertEquals("0.36 - 1.52i", Complex.toString(Complex.divides(a, b)));
		assertEquals("5.0 + 6.0i", Complex.toString(Complex.mult(Complex.divides(a, b), b)));
	}

	@Test
	public void testPropiedades() {
		assertEquals("5.0 - 6.0i", Complex.toString(Complex.conjugate(a)));
		assertEquals(7.810249675906654, Complex.abs(a), DELTA);
		assertEquals("-6.685231390246571E-6 + 1.0000103108981198i", Complex.toString(Complex.tan(a)));
	}
}
