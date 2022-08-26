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

import java.util.List;

public class Base_3D {
	private double movX, movY, movz, tetha_xy, tetha_xz, tetha_yz;

	public Base_3D(double movXP, double movYP, double movZP, double tetha_xyP, double tetha_xzP, double tetha_yzP) {
		movX = movXP;
		movY = movYP;
		movz = movZP;
		tetha_xy = Math.toRadians(tetha_xyP) % 360;
		tetha_xz = Math.toRadians(tetha_xzP) % 360;
		tetha_yz = Math.toRadians(tetha_yzP) % 360;
	}

	public Base_3D() {
		movX = movY = movz = tetha_xy = tetha_xz = tetha_yz = 0;
	}

	public void TransformarPunto(double[] po) {
		double x = po[0], y = po[1], z = po[2];

	}

	public void TransformarPuntos(List<double[]> puntosOriginales) {
	}

	public void cambiarParametros(double movXP, double movYP, double movZP, double tetha_xyP, double tetha_xzP,
			double tetha_yzP) {
		movX = movXP;
		movY = movYP;
		movz = movZP;
		tetha_xy = Math.toRadians(tetha_xyP) % 360;
		tetha_xz = Math.toRadians(tetha_xzP) % 360;
		tetha_yz = Math.toRadians(tetha_yzP) % 360;
	}
}