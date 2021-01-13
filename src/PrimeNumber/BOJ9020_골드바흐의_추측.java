
package PrimeNumber;

import java.io.*;
import java.util.*;

// BOJ9020_골드바흐의_추측
public class BOJ9020_골드바흐의_추측
{
	static final int SZ = 10001;

	static boolean prime[] = new boolean[ SZ + 1 ];

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
	}

	// 시작을 중점으로
	static void solve(int n)
	{
		for (int i = n / 2;; i--)
		{
			if (prime[ i ] == false && prime[ n - i ] == false)
			{
				System.out.println(i + " " + (n - i));
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
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		int t = Integer.parseInt(st.nextToken( ));
		for (int i = 0; i < t; i++)
		{
			st = new StringTokenizer(br.readLine( ));
			int n = Integer.parseInt(st.nextToken( ));
			solve(n);
		}
	}
}
