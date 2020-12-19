
package DynamicProgramming;

import java.io.*;
import java.util.*;

// BOJ10844_쉬운_계단_수
public class BOJ10844_쉬운_계단_수
{
	static int N;
	static final long FN = 1000000000;
	static long[ ][ ] dp;

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		solve( );
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		N = Integer.parseInt(st.nextToken( ));
		dp = new long[ 10 ][ N + 1 ];
		for (int i = 1; i < 10; i++)
		{
			dp[ i ][ 1 ] = 1;
		}
		dp[ 0 ][ 1 ] = 0;
	}

	static void solve( )
	{
		for (int i = 2; i <= N; i++)
		{
			for (int j = 1; j < 9; j++)
			{
				dp[ j ][ i ] = (dp[ j - 1 ][ i - 1 ] + dp[ j + 1 ][ i - 1 ]) % FN;
			}
			dp[ 0 ][ i ] = dp[ 1 ][ i - 1 ];
			dp[ 9 ][ i ] = dp[ 8 ][ i - 1 ];
		}
		long ans = 0;
		for (int i = 0; i < 10; i++)
		{
			ans += dp[ i ][ N ];
		}
		System.out.println(ans % FN);
	}
}
