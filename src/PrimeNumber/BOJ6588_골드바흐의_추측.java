
package PrimeNumber;

import java.io.*;
import java.util.*;

// BOJ6588_골드바흐의_추측
public class BOJ6588_골드바흐의_추측
{
	static final int SZ = 1000001;

	static boolean prime[] = new boolean[ SZ + 1 ];
	static StringBuilder sb = new StringBuilder( );

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
	}

	static void solve(int n)
	{
		for (int i = 0;; i++)
		{
			if (i > n)
			{
				sb.append("Goldbach's conjecture is wrong.\n");
				break;
			}
			if (prime[ i ] == false && prime[ n - i ] == false && i % 2 == 1 && (n - i) % 2 == 1)
			{
				sb.append(n + " = " + i + " + " + (n - i) + "\n");
				break;
			}
		}
	}

	static void inputAndSettingData( ) throws IOException
	{
		prime[ 0 ] = true;
		prime[ 1 ] = true;
		for (int i = 2; i * i <= SZ; i++)
		{
			if (prime[ i ] == false)
			{
				for (int j = i * i; j <= SZ; j += i)
				{
					prime[ j ] = true;
				}
			}
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for (;;)
		{
			st = new StringTokenizer(br.readLine( ));
			int n = Integer.parseInt(st.nextToken( ));
			if (n == 0) break;
			solve(n);
		}
		System.out.println(sb);
	}
}
