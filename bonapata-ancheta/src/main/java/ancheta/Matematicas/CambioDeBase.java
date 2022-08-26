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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CambioDeBase {
	// Integer.parseInt("31",8) == 25
	public static long baseN_a_Base10(char[] numero, int base) {
		long rta = 0;
		for (int i = 0; i < numero.length; i++)
			rta += (numero[i] - '0') * Math.pow(base, numero.length - 1 - i);
		return rta;
	}

	// Integer.toString(25,8) == "31"
	public static String base10_a_BaseN(long numero, int base) {
		List<Long> aux = new ArrayList<Long>();
		if (numero == 0)
			aux.add((long) 0);
		while (numero > 0) {
			long residuo = numero % base;
			aux.add(residuo);
			numero /= base;
		}
		StringBuilder rta = new StringBuilder();
		for (int i = aux.size() - 1; i >= 0; i--)
			rta.append(aux.get(i));
		return rta.toString();
	}

	// 31 en base 8 es 25 en base 10.
	// transformacionGeneral("31".toCharArray(), "01234567".toCharArray(),
	// "0123456789".toCharArray()) == "25"
	public static String transformacionGeneral(char[] numero, char[] lOrigen, char[] lDestino) {
		Map<Character, Integer> mOrigen = new HashMap<Character, Integer>();
		int baseOrigen = lOrigen.length;
		for (int i = 0; i < baseOrigen; i++)
			mOrigen.put(lOrigen[i], i);

		long numBase10 = 0;
		for (int i = 0; i < numero.length; i++)
			numBase10 += mOrigen.get(numero[i]) * Math.pow(baseOrigen, numero.length - 1 - i);

		Map<Long, Character> mDestino = new HashMap<Long, Character>();
		int baseDestino = lDestino.length;
		for (long i = 0; i < baseDestino; i++)
			mDestino.put(i, lDestino[(int) i]);

		List<Long> aux = new ArrayList<Long>();
		if (numBase10 == 0)
			aux.add((long) 0);
		while (numBase10 > 0) {
			long residuo = numBase10 % baseDestino;
			aux.add(residuo);
			numBase10 /= baseDestino;
		}

		StringBuilder sb = new StringBuilder();
		for (int i = aux.size() - 1; i >= 0; i--)
			sb.append(mDestino.get(aux.get(i)));
		return sb.toString();
	}

	public static String binario_a_Gray(char[] binario) {
		StringBuilder gray = new StringBuilder();
		for (int i = 0; i < binario.length; i++) {
			if (i == 0)
				gray.append(binario[i]);
			else {
				if (binario[i - 1] != binario[i])
					gray.append('1');
				else
					gray.append('0');
			}
		}
		return gray.toString();
	}

	public static String gray_a_Binario(char[] gray) {
		StringBuilder binario = new StringBuilder();
		for (int i = 0; i < gray.length; i++) {
			if (i == 0)
				binario.append(gray[i]);
			else {
				if (gray[i] == '0')
					binario.append(binario.charAt(i - 1));
				else
					binario.append(binario.charAt(i - 1) == '0' ? '1' : '0');
			}
		}
		return binario.toString();
	}
}