
package DynamicProgramming;

import java.io.*;
import java.util.*;

// BOJ1003_피보나치_함수
public class BOJ1003_피보나치_함수
{
	static int T, N;
	static int[ ][ ] dp;

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		T = Integer.parseInt(st.nextToken( ));
		for (int i = 0; i < T; i++)
		{
			st = new StringTokenizer(br.readLine( ));
			N = Integer.parseInt(st.nextToken( ));
			solve(N);
		}
	}

	static void solve(int n)
	{
		dp = new int[ 2 ][ n + 2 ];
		dp[ 0 ][ 0 ] = 1;
		dp[ 1 ][ 1 ] = 1;
		for (int i = 2; i <= n; i++)
		{
			dp[ 0 ][ i ] = dp[ 0 ][ i - 1 ] + dp[ 0 ][ i - 2 ];
			dp[ 1 ][ i ] = dp[ 1 ][ i - 1 ] + dp[ 1 ][ i - 2 ];
		}
		System.out.println(dp[ 0 ][ n ] + " " + dp[ 1 ][ n ]);
	}
}
