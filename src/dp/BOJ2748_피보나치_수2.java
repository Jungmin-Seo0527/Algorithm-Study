
package dp;

import java.io.*;
import java.util.*;

// BOJ2748_피보나치_수2
public class BOJ2748_피보나치_수2
{
	static int N;
	static long[ ] dp;

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
		dp = new long[ N + 1 ];
		dp[ 0 ] = 0;
		dp[ 1 ] = 1;
	}

	static void solve( )
	{
		for (int i = 2; i <= N; i++)
		{
			dp[ i ] = dp[ i - 1 ] + dp[ i - 2 ];
		}
		System.out.println(dp[ N ]);
	}
}
