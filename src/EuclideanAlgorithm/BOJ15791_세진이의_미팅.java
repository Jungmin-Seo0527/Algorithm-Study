
package EuclideanAlgorithm;

import java.io.*;
import java.util.*;

// BOJ15791_세진이의_미팅
// 페르마의 소정리
public class BOJ15791_세진이의_미팅
{
	static final long P = 1000000007;

	static long fac[];
	static int N, M;

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		solve( );
	}

	static void solve( )
	{
		long A = fac[ N ];
		long B = fac[ M ] * fac[ N - M ] % P;

		long ret = A * pow(B, P - 2) % P;
		System.out.println(ret);
	}

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
		M = Integer.parseInt(st.nextToken( ));
		fac = new long[ N + 1 ];
		fac[ 0 ] = 1;
		for (int i = 1; i <= N; i++)
		{
			fac[ i ] = fac[ i - 1 ] * i % P;
		}
	}
}
