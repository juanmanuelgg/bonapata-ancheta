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
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 
 *
 */
public class CC {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File("./data/grafos/Grafo.in")));

		int V, E, from, to;

		String aux[] = br.readLine().split(" ");
		V = Integer.parseInt(aux[0]);
		E = Integer.parseInt(aux[1]);

		// Paso 1: Representar el grafo como una lista de adjacenica y como lista de
		// arcos
		List<int[]> arcos = new ArrayList<int[]>();

		@SuppressWarnings("unchecked")
		List<Integer>[] adj = new ArrayList[V];
		for (int i = 0; i < V; i++)
			adj[i] = new ArrayList<Integer>();

		while (E-- != 0) {
			aux = br.readLine().split(" ");
			from = Integer.parseInt(aux[0]);
			to = Integer.parseInt(aux[1]);

			// Lista de arcos
			int[] arco = { from, to };
			arcos.add(arco);

			// Lista de adjacencia
			adj[from].add(to);
			adj[to].add(from);
		}
		br.close();

		cc_Bfs(adj, V);
		cc_Dfs(adj, V);
		cc_Uf(arcos, V);
	}

	public static void cc_Dfs(List<Integer>[] adj, int V) {
		int[] marcados = new int[V];
		Arrays.fill(marcados, -1);
		for (int i = 0; i < V; i++)
			if (marcados[i] == -1)
				dfs(i, adj, marcados, i);

		Map<Integer, List<Integer>> map = new LinkedHashMap<Integer, List<Integer>>();
		for (int i = 0; i < V; i++) {
			List<Integer> pertenecen = map.get(marcados[i]);
			if (pertenecen == null)
				pertenecen = new ArrayList<Integer>();
			pertenecen.add(i);
			map.put(marcados[i], pertenecen);
		}

		System.out.println("DFS:");
		System.out.println(map);
		System.out.println("----------------------------------------------");
	}

	private static void dfs(int source, List<Integer>[] adj, int[] marcados, int v) {
		marcados[v] = source;
		for (int w : adj[v])
			if (marcados[w] != source)
				dfs(source, adj, marcados, w);
	}

	public static void cc_Bfs(List<Integer>[] adj, int V) {
		int[] marcados = new int[V];
		Arrays.fill(marcados, -1);
		for (int i = 0; i < V; i++)
			if (marcados[i] == -1)
				bfs(i, adj, marcados);

		Map<Integer, List<Integer>> map = new LinkedHashMap<Integer, List<Integer>>();
		for (int i = 0; i < V; i++) {
			List<Integer> pertenecen = map.get(marcados[i]);
			if (pertenecen == null)
				pertenecen = new ArrayList<Integer>();
			pertenecen.add(i);
			map.put(marcados[i], pertenecen);
		}

		System.out.println("BFS:");
		System.out.println(map);
		System.out.println("----------------------------------------------");
	}

	private static void bfs(int source, List<Integer>[] adj, int[] marcados) {
		Queue<Integer> q = new LinkedList<Integer>();
		marcados[source] = source;
		q.add(source);

		while (!q.isEmpty()) {
			int v = q.poll();
			for (int w : adj[v]) {
				if (marcados[w] != source) {
					marcados[w] = source;
					q.add(w);
				}
			}
		}
	}

	public static void cc_Uf(List<int[]> arcos, int V) {
		UnionFind uf = new UnionFind(V);
		for (int[] arco : arcos)
			uf.union(arco[0], arco[1]);
		int[] roots = uf.darRoots();

		Map<Integer, List<Integer>> map = new LinkedHashMap<Integer, List<Integer>>(uf.getCount());
		for (int i = 0; i < V; i++) {
			List<Integer> pertenecen = map.get(roots[i]);
			if (pertenecen == null)
				pertenecen = new ArrayList<Integer>();
			pertenecen.add(i);
			map.put(roots[i], pertenecen);
		}

		System.out.println("Union-Find:");
		System.out.println(map);
		System.out.println("----------------------------------------------");
	}

	public static class UnionFind {
		private int[] idPadres;
		private int cantConjuntos;

		public UnionFind(int N) {
			cantConjuntos = N;
			idPadres = new int[N];
			for (int i = 0; i < N; i++)
				idPadres[i] = i;
		}

		public int getCount() {
			return cantConjuntos;
		}

		public void union(int p, int q) {
			int i = root(p);
			int j = root(q);
			if (i != j) {
				idPadres[i] = j;
				cantConjuntos--;
			}
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

		public int[] darRoots() {
			int[] rta = new int[idPadres.length];
			for (int i = 0; i < rta.length; i++)
				rta[i] = root(i);
			return rta;
		}
	}
}