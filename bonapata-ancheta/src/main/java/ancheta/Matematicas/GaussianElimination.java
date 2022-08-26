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

import java.util.Arrays;

public class GaussianElimination {
	private static final double EPSILON = 1e-10;

	// Gaussian elimination with partial pivoting
	public static double[] lsolve(double[][] A, double[] b) {
		int N = b.length;

		for (int p = 0; p < N; p++) {
			// find pivot row and swap
			int max = p;
			for (int i = p + 1; i < N; i++) {
				if (Math.abs(A[i][p]) > Math.abs(A[max][p]))
					max = i;
			}
			double[] temp = A[p];
			A[p] = A[max];
			A[max] = temp;
			double t = b[p];
			b[p] = b[max];
			b[max] = t;

			// Matrix is singular or nearly singular
			if (Math.abs(A[p][p]) <= EPSILON)
				return null;

			// pivot within A and b
			for (int i = p + 1; i < N; i++) {
				double alpha = A[i][p] / A[p][p];
				b[i] -= alpha * b[p];
				for (int j = p; j < N; j++)
					A[i][j] -= alpha * A[p][j];
			}
		}

		// back substitution
		double[] x = new double[N];
		for (int i = N - 1; i >= 0; i--) {
			double sum = 0.0;
			for (int j = i + 1; j < N; j++)
				sum += A[i][j] * x[j];

			x[i] = (b[i] - sum) / A[i][i];
		}
		return x;
	}

	// sample client
	public static void main(String[] args) {
		int N = 3;
		double[][] A = { { 0, 1, 1 }, { 2, 4, -2 }, { 0, 3, 15 } };
		double[] b = { 4, 2, 36 };
		double[] x = lsolve(A, b);

		// print results
		System.out.println(Arrays.toString(x));
	}

}
