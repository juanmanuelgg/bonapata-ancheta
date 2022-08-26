package ancheta.Matematicas;

import java.util.ArrayList;
import java.util.List;

public class Permutaciones
{
	private static final int TAM=30;//Maximo posible para un long
	private static long[] fact=new long[TAM+1];
	
	/* int i=1;
		char[] x="0123456789".toCharArray();
		do
		{
			if(i==1000000) {System.out.println(x);break;}
			i++;
		}
		while(nextPermutation(x));
	 */
	// rta == 2783915460
	public static boolean nextPermutation(char[] array)
	{
		int i=array.length-1;
		while(i>0 && array[i-1]>=array[i]) i--;
		
		if(i==0) return false;
		
		int j=array.length-1;
		while(array[j]<=array[i-1]) j--;
		
		char temp=array[i-1];
		array[i-1]=array[j];
		array[j]=temp;
		
		j=array.length-1;
		while(i<j)
		{
			temp=array[i];
			array[i]=array[j];
			array[j]=temp;
			i++;j--;
		}
		return true;
	}

	//nPermutation(1000000,"0123456789".toCharArray()) == "2783915460"
	public static String nPermutation(int n, char[] charArray)
	{
		if(fact[0]==0) precalcularFactoriales();
		List<Character> caracteres=new ArrayList<Character>();
		for(char c : charArray) caracteres.add(c);
		
		StringBuilder rta=new StringBuilder();
		int T=charArray.length;
		n--;
		for(int i=0; i<T; i++)
		{
			long aux1= n/fact[T-i-1];
			rta.append(caracteres.get((int)aux1));
			caracteres.remove((int)aux1);
			n%=fact[T-i-1];
		}
		return rta.toString();
	}

	private static void precalcularFactoriales()
	{
		fact[0]=1;
		for(int i=1; i<=TAM; i++) fact[i]=fact[i-1]*i;
	}
}