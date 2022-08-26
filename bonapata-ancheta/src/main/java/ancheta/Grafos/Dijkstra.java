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
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * Dijkstra con PriorityQueue Complejidad Temporal: O((E+V)*Log(V)) Complejidad
 * Espacial: O(V)
 */
public class Dijkstra {
	private static final int SRC = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File("./data/grafos/SP.in")));

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
		}
		br.close();

		// Paso 2: Prepara las estructura de datos donde guardo la RTA.
		Arco[] edgeTo = new Arco[V];
		double[] distTo = new double[V];
		for (int i = 0; i < V; i++)
			distTo[i] = Double.POSITIVE_INFINITY;

		// Paso 3: Dijkstra
		dijkstra(adj, edgeTo, distTo, SRC);

		System.out.println(Arrays.toString(edgeTo));
		for (int i = 0; i < V; i++) {
			System.out.printf("%d to %d (%.2f) :: ", SRC, i, distTo[i]);

			Stack<Arco> camino = pathTo(i, distTo, edgeTo);
			StringBuilder sb = new StringBuilder();
			while (!camino.isEmpty())
				sb.append(camino.pop() + " ");
			System.out.println(sb.toString());
		}
	}

	public static void dijkstra(List<Arco>[] adj, Arco[] edgeTo, double[] distTo, int src) {
		PriorityQueue<NodoDijkstra> pq = new PriorityQueue<NodoDijkstra>();

		distTo[src] = 0.0;
		pq.add(new NodoDijkstra(src, 0.0));
		for (int v, w; !pq.isEmpty();) {
			NodoDijkstra aRelajar = pq.poll();
			v = aRelajar.id;
			for (Arco edge : adj[v]) // Relajar el nodo
			{
				w = edge.to;
				if (distTo[w] > distTo[v] + edge.peso) {
					distTo[w] = distTo[v] + edge.peso;
					edgeTo[w] = edge;
					pq.remove(aRelajar);
					pq.add(new NodoDijkstra(w, distTo[w]));
				}
			}
		}
	}

	public static Stack<Arco> pathTo(int v, double[] distTo, Arco[] edgeTo) {
		if (distTo[v] == Double.POSITIVE_INFINITY)
			return null;

		Stack<Arco> path = new Stack<Arco>();
		for (Arco e = edgeTo[v]; e != null; e = edgeTo[e.from])
			path.push(e);
		return path;
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
			return String.format("[%d->%d (%.2f)]", from, to, peso);
		}
	}

	private static class NodoDijkstra implements Comparable<NodoDijkstra> {
		int id;
		double dist;

		public NodoDijkstra(int wP, double distP) {
			id = wP;
			dist = distP;
		}

		@Override
		public int compareTo(NodoDijkstra arg0) {
			return Double.compare(dist, arg0.dist);
		}

		public String toString() {
			return String.format("%d (%.2f)", id, dist);
		}
	}
}