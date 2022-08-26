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

package ancheta.Grafos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Lazy Prim Complejidad Temporal: O(E*Log(E)) Complejidad Espacial: O(E)
 */
public class Prim_Lazy {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File("./data/grafos/MST.in")));

		int V, E, from, to;
		double peso;

		String aux[] = br.readLine().split(" ");
		V = Integer.parseInt(aux[0]);
		E = Integer.parseInt(aux[1]);

		// Paso 1: Representar el grafo como una lista de adjacenica de Arcos
		@SuppressWarnings("unchecked")
		List<Arco>[] adj = new ArrayList[V];
		for (int i = 0; i < V; i++)
			adj[i] = new ArrayList<Arco>();
		while (E-- != 0) {
			aux = br.readLine().split(" ");
			from = Integer.parseInt(aux[0]);
			to = Integer.parseInt(aux[1]);
			peso = Double.parseDouble(aux[2]);

			adj[from].add(new Arco(from, to, peso));
			adj[to].add(new Arco(to, from, peso));
		}
		br.close();

		// Paso 3: prim
		List<Arco> mst = prim(adj, V);

		System.out.println(mst);

	}

	private static List<Arco> prim(List<Arco>[] adj, int V) {
		List<Arco> mst = new ArrayList<Arco>();
		boolean[] marcado = new boolean[V];
		PriorityQueue<Arco> pq = new PriorityQueue<Arco>();

		visit(adj, marcado, pq, 0);
		while (!pq.isEmpty()) {
			Arco e = pq.poll();
			if (!marcado[e.from] || !marcado[e.to]) {
				mst.add(e);
				if (!marcado[e.from])
					visit(adj, marcado, pq, e.from);
				if (!marcado[e.to])
					visit(adj, marcado, pq, e.to);
			}
		}
		return mst;
	}

	private static void visit(List<Arco>[] adj, boolean[] marcado, PriorityQueue<Arco> pq, int v) {
		marcado[v] = true;
		for (Arco e : adj[v])
			if (!marcado[e.to])
				pq.add(e);
	}

	private static class Arco implements Comparable<Arco> {
		int from, to;
		double peso;

		public Arco(int fromP, int toP, double pesoP) {
			from = fromP;
			to = toP;
			peso = pesoP;
		}

		@Override
		public int compareTo(Arco arg0) {
			return Double.compare(peso, arg0.peso);
		}

		public String toString() {
			return String.format("%d-%d (%.2f)", from, to, peso);
		}
	}
}