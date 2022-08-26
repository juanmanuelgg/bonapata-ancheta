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

import java.util.ArrayList;
import java.util.List;

public class Permutaciones {
	private static final int TAM = 30;// Maximo posible para un long
	private static long[] fact = new long[TAM + 1];

	/*
	 * int i=1; char[] x="0123456789".toCharArray(); do { if(i==1000000)
	 * {System.out.println(x);break;} i++; } while(nextPermutation(x));
	 */
	// rta == 2783915460
	public static boolean nextPermutation(char[] array) {
		int i = array.length - 1;
		while (i > 0 && array[i - 1] >= array[i])
			i--;

		if (i == 0)
			return false;

		int j = array.length - 1;
		while (array[j] <= array[i - 1])
			j--;

		char temp = array[i - 1];
		array[i - 1] = array[j];
		array[j] = temp;

		j = array.length - 1;
		while (i < j) {
			temp = array[i];
			array[i] = array[j];
			array[j] = temp;
			i++;
			j--;
		}
		return true;
	}

	// nPermutation(1000000,"0123456789".toCharArray()) == "2783915460"
	public static String nPermutation(int n, char[] charArray) {
		if (fact[0] == 0)
			precalcularFactoriales();
		List<Character> caracteres = new ArrayList<Character>();
		for (char c : charArray)
			caracteres.add(c);

		StringBuilder rta = new StringBuilder();
		int T = charArray.length;
		n--;
		for (int i = 0; i < T; i++) {
			long aux1 = n / fact[T - i - 1];
			rta.append(caracteres.get((int) aux1));
			caracteres.remove((int) aux1);
			n %= fact[T - i - 1];
		}
		return rta.toString();
	}

	private static void precalcularFactoriales() {
		fact[0] = 1;
		for (int i = 1; i <= TAM; i++)
			fact[i] = fact[i - 1] * i;
	}
}