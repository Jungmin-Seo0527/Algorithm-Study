
package EuclideanAlgorithm;

import java.io.*;
import java.util.*;

// BOJ14565_역원구하기
public class BOJ14565_역원구하기
{
	static class Tuple
	{
		long g, x, y;

		public Tuple(long g, long x, long y)
		{
			this.g = g;
			this.x = x;
			this.y = y;
		}
	}

	static long A, N;

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		solve( );
	}

	static void solve( )
	{
		long a = N - A;
		while (a < 0)
		{
			a += N;
		}

		long c = 0;
		Tuple t = extended_euclidean(N, A);
		if (t.g != 1)
		{
			c = -1;
		}
		else
		{
			c = (t.y + N) % N;
		}
		System.out.println(a + " " + c);
	}

	static int getGcd(int a, int b)
	{
		if (b == 0) return a;
		else return getGcd(b, a % b);
	}

	static Tuple extended_euclidean(long a, long b)
	{
		if (b == 0) return new Tuple(a, 1, 0);
		Tuple temp = extended_euclidean(b, a % b);
		long g = temp.g;
		long x = temp.x;
		long y = temp.y;
		//System.out.println(g + " " + x + " " + y + " " + a + " " + b);
		return new Tuple(g, y, x - (a / b) * y);
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		N = Long.parseLong(st.nextToken( ));
		A = Long.parseLong(st.nextToken( ));
	}
}
