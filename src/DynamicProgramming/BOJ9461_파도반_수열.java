
package DynamicProgramming;

import java.io.*;
import java.util.*;

// BOJ9461_파도반_수열
public class BOJ9461_파도반_수열
{
	static int T, N;
	static long[ ] dp;

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
			solve( );
		}
	}

	static void solve( )
	{
		dp = new long[ N + 5 ];
		dp[ 0 ] = 0;
		dp[ 1 ] = 1;
		dp[ 2 ] = 1;
		dp[ 3 ] = 1;
		dp[ 4 ] = 2;
		for (int i = 5; i <= N; i++)
		{
			dp[ i ] = dp[ i - 1 ] + dp[ i - 5 ];
		}
		System.out.println(dp[ N ]);
	}
}
