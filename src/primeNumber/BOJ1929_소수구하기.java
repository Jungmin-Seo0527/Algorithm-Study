
package primeNumber;

import java.io.*;
import java.util.*;

// BOJ1929_소수구하기
public class BOJ1929_소수구하기
{
	static boolean prime[];

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
	}

	static void solve(int n1, int n2)
	{
		StringBuilder sb = new StringBuilder( );
		prime[ 0 ] = true;
		prime[ 1 ] = true;
		for (int i = 2; i * i <= n2; i++)
		{
			if (prime[ i ] == false)
			{
				for (int j = i * i; j <= n2; j += i)
				{
					prime[ j ] = true;
				}
			}
		}
		for (int i = n1; i <= n2; i++)
		{
			if (prime[ i ] == false) sb.append(i + " \n");
		}
		System.out.println(sb);
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		int M = Integer.parseInt(st.nextToken( ));
		int N = Integer.parseInt(st.nextToken( ));
		prime = new boolean[ N + 1 ];
		solve(M, N);
	}
}
