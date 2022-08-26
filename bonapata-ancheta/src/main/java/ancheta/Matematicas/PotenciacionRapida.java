package ancheta.Matematicas;

import java.math.BigInteger;

public class PotenciacionRapida
{
	public static void main(String[] args)
	{
		for(int i=1; i<=12; i++)
		{
			BigInteger[][] R=fib(i);
			//System.out.println(Arrays.toString(R[0]));
			//System.out.println(Arrays.toString(R[1]));
			System.out.printf("%2d: %d\n",i,R[1][1]);
		}
	}

	//|1 1|
	//|1 0| Esta es la matriz que representa la funcion de fibonacci
	public static BigInteger[][] fib(int n)
	{
		BigInteger r00=new BigInteger("1");
		BigInteger r01=new BigInteger("1");
		BigInteger r10=new BigInteger("1");
		BigInteger r11=new BigInteger("0");

		BigInteger[][] A={{r00,r01},{r10,r11}};
		BigInteger[][] R=MatrixPow(A, n+1);//n+1, para fibonacci
		return R;
	}

	//Este es el algoritmo de potenciacion rapida de matrices, la matriz debe ser 2x2
	private static BigInteger[][] MatrixPow(BigInteger[][] A, int n)
	{
		if(n==0)
		{
			BigInteger[][] I={{BigInteger.ONE,BigInteger.ZERO},{BigInteger.ZERO,BigInteger.ONE}};//Identidad
			return I;
		}
		else if(n==1) return A;
		else if(n%2==0)
		{
			BigInteger[][] D=MatrixPow(A, n/2);
			return matrixMult(D,D);
		}
		else
		{
			BigInteger[][] D=MatrixPow(A, n/2);
			return matrixMult(matrixMult(D,D),A);			
		}
	}

	private static BigInteger[][] matrixMult(BigInteger[][] A, BigInteger[][] B)
	{
		BigInteger r00=A[0][0].multiply(B[0][0]).add(A[0][1].multiply(B[1][0]));
		BigInteger r01=A[0][0].multiply(B[0][1]).add(A[0][1].multiply(B[1][1]));
		BigInteger r10=A[1][0].multiply(B[0][0]).add(A[1][1].multiply(B[1][0]));
		BigInteger r11=A[1][0].multiply(B[0][1]).add(A[1][1].multiply(B[1][1]));

		BigInteger[][] R={{r00,r01},{r10,r11}};
		return R;
	}
}