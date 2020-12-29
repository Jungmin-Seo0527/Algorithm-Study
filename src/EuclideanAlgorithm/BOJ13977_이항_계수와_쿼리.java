
package EuclideanAlgorithm;

import java.io.*;
import java.util.*;

// BOJ13977_이항_계수와_쿼리
public class BOJ13977_이항_계수와_쿼리
{
	static final long SZ = 4000000;
	static final long P = 1000000007;
	static long fac[];

	static StringBuilder sb = new StringBuilder( );

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		System.out.println(sb);
	}

	static void solve(int n, int k)
	{
		long A = fac[ n ];
		long B = fac[ k ] * fac[ n - k ] % P;

		long ret = A * pow(B, P - 2) % P;
		sb.append(ret + "\n");
	}

	// 분할정복
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
		int M = 0;

		fac = new long[ (int) (SZ + 1) ];
		fac[ 0 ] = 1;
		for (int i = 1; i <= SZ; i++)
		{
			fac[ i ] = fac[ i - 1 ] * i % P;

		}

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));

		M = Integer.parseInt(st.nextToken( ));
		for (int i = 0; i < M; i++)
		{
			st = new StringTokenizer(br.readLine( ));
			int N = Integer.parseInt(st.nextToken( ));
			int K = Integer.parseInt(st.nextToken( ));
			solve(N, K);
		}

	}
}
