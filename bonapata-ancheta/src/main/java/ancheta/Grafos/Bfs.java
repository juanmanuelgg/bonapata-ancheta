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
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * 
 *
 */
public class Bfs {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File("./data/grafos/Grafo.in")));

		int V, E, from, to;

		String aux[] = br.readLine().split(" ");
		V = Integer.parseInt(aux[0]);
		E = Integer.parseInt(aux[1]);

		// Paso 1: Representar el grafo como una lista de adjacenica
		@SuppressWarnings("unchecked")
		List<Integer>[] adj = new ArrayList[V];
		for (int i = 0; i < V; i++)
			adj[i] = new ArrayList<Integer>();
		while (E-- != 0) {
			aux = br.readLine().split(" ");
			from = Integer.parseInt(aux[0]);
			to = Integer.parseInt(aux[1]);
			adj[from].add(to);
			// adj[to].add(from); sin direccion
		}
		br.close();

		// Paso 2: Prepara las estructura de datos donde guardo la RTA.
		int[] marcados = new int[V];
		Arrays.fill(marcados, -1);
		int[] edgeTo = new int[V];
		int[] distTo = new int[V];
		Arrays.fill(distTo, Integer.MAX_VALUE);

		// Paso 3: Correr Bfs en todos los nodos
		for (int i = 0; i < V; i++)
			if (marcados[i] == -1)
				bfs(i, adj, marcados, edgeTo, distTo);

		for (int i = 0; i < V; i++) {
			System.out.printf("Nodo: %d, componente(from):%d, distTo:%d, camino: ", i, marcados[i], distTo[i]);

			Stack<Integer> camino = pathTo(i, marcados, edgeTo, distTo);
			StringBuilder sb = new StringBuilder();
			while (!camino.isEmpty())
				sb.append(camino.pop() + " ");
			System.out.println(sb.toString());
		}
	}

	private static void bfs(int source, List<Integer>[] adj, int[] marcados, int[] edgeTo, int[] distTo) {
		Queue<Integer> q = new LinkedList<Integer>();
		marcados[source] = source;
		distTo[source] = 0;
		q.add(source);

		while (!q.isEmpty()) {
			int v = q.poll();
			for (int w : adj[v]) {
				if (marcados[w] != source) {
					edgeTo[w] = v;
					distTo[w] = distTo[v] + 1;
					marcados[w] = source;
					q.add(w);
				}
			}
		}
	}

	private static Stack<Integer> pathTo(int v, int[] marcados, int[] edgeTo, int[] distTo) {
		if (marcados[v] == -1)
			return null;

		Stack<Integer> path = new Stack<Integer>();

		int x;
		for (x = v; distTo[x] != 0; x = edgeTo[x])
			path.push(x);
		path.push(x);

		return path;
	}
}