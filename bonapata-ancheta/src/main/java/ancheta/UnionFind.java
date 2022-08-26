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

package ancheta;

public class UnionFind {
	private int[] idPadres;
	private int[] cantHijos;
	private int cantConjuntos;

	public UnionFind(int N) {
		cantConjuntos = N;

		idPadres = new int[N];
		cantHijos = new int[N];
		for (int i = 0; i < N; i++) {
			idPadres[i] = i;
			cantHijos[i] = 1;
		}
	}

	public int getCount() {
		return cantConjuntos;
	}

	// Retorna la cantida de elementos que resultaron unidos.
	public int union(int p, int q) {
		int i = root(p);
		int j = root(q);
		if (i != j) {
			idPadres[i] = j;
			cantHijos[j] += cantHijos[i];
			cantConjuntos--;
		}
		return cantHijos[j];
	}

	public boolean conected(int p, int q) {
		return root(p) == root(q);
	}

	private int root(int i) {
		int root = i;
		while (root != idPadres[root])
			root = idPadres[root];
		while (i != root) {
			int newp = idPadres[i];
			idPadres[i] = root;
			i = newp;
		}
		return root;
	}

	// Para cuando se buscan componenetes conexos
	public int[] darRoots() {
		int[] rta = new int[idPadres.length];
		for (int i = 0; i < rta.length; i++)
			rta[i] = root(i);
		return rta;
	}
}
