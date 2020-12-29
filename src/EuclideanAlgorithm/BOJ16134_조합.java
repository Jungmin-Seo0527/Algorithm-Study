
package EuclideanAlgorithm;

import java.io.*;
import java.util.*;

// BOJ16134_조합
// 페르마의 소정리 이용
// BOJ11401_이항계수3 문제와 완전히 동일한 문제 (백프로 동일한 코드)
public class BOJ16134_조합
{
	static int N, R;
	static long fac[];
	static final long P = 1000000007;

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		solve( );
	}

	static void solve( )
	{
		long A = fac[ N ];
		long B = fac[ R ] * fac[ N - R ] % P;

		long ret = A * pow(B, P - 2) % P;
		System.out.println(ret);
	}

	// 분할 정복을 이용한 제곱
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
		R = Integer.parseInt(st.nextToken( ));
		fac = new long[ N + 1 ];
		fac[ 0 ] = 1;
		for (int i = 1; i <= N; i++)
		{
			fac[ i ] = fac[ i - 1 ] * i % P;
		}
	}
}
