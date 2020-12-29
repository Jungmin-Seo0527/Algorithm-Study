
package EuclideanAlgorithm;

import java.io.*;
import java.util.*;

// BOJ11401_이항계수3
public class BOJ11401_이항계수3
{
	static int N, K;
	static long fac[];
	static final long P = 1000000007;

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		solve( );
	}

	// 페르마의 소정리
	// p가 소수이고 a가 정수일때
	// a^p % p = a%p
	// a^(p-1)%p = 1
	// a^(p-1)%p = a^(-1)
	static void solve( )
	{
		long A = fac[ N ];
		long B = fac[ K ] * fac[ N - K ] % P;
		B = pow(B, P - 2);

		long ret = A * B % P;
		System.out.println(ret);
	}

	// 분할정복으로 제곱 구하기
	// 2^13 = 2^6 * 2^6 * 2
	// 2^6 = 2*3 * 2^3
	// 2^3 = 2^1 * 2^1 * 2
	static long pow(long n, long a)
	{
		long ret = 1;
		while (a > 0)
		{
			if (a % 2 == 1)
			{
				ret *= n;
				ret %= P;
			}
			n *= n;
			n %= P;
			a /= 2;
		}
		return ret;
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		N = Integer.parseInt(st.nextToken( ));
		K = Integer.parseInt(st.nextToken( ));

		fac = new long[ N + 1 ];

		// 0! = 1
		fac[ 0 ] = 1;
		for (int i = 1; i <= N; i++)
		{
			fac[ i ] = fac[ i - 1 ] * i % P;
		}

	}
}
