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

public class Base_2D {
	private double movX, movY, tetha;

	public Base_2D(double movXP, double movYP, double tethaP) {
		movX = movXP;
		movY = movYP;
		tetha = Math.toRadians(tethaP % 360);
	}

	public Base_2D() {
		movX = movY = tetha = 0;
	}

	public void TransformarPunto(double[] po) {
		double x = po[0], y = po[1];
		po[0] = x * Math.cos(tetha) + y * Math.sin(tetha) + movX;
		po[1] = -x * Math.sin(tetha) + y * Math.cos(tetha) + movY;
	}

	public double[] TransformarPuntos(double[][] listaPuntos) {
		double[] centros = new double[2];
		double x, y;
		for (double[] ds : listaPuntos) {
			x = ds[0];
			y = ds[1];
			centros[0] += x;
			centros[1] += y;
			ds[0] = x * Math.cos(tetha) + y * Math.sin(tetha) + movX;
			ds[1] = -x * Math.sin(tetha) + y * Math.cos(tetha) + movY;
		}
		centros[0] /= listaPuntos.length;
		centros[1] /= listaPuntos.length;
		return centros;
	}

	public void cambiarParametros(double movXP, double movYP, double tethaP) {
		movX = movXP;
		movY = movYP;
		tetha = Math.toRadians(tethaP % 360);
	}
}