
package DynamicProgramming;

import java.io.*;
import java.util.*;

// BOJ2579_계단_오르기
public class BOJ2579_계단_오르기
{
	static int N;
	static int[ ] dp, stair;

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
		dp = new int[ N + 1 ];
		stair = new int[ N + 1 ];

		for (int i = 1; i <= N; i++)
		{
			st = new StringTokenizer(br.readLine( ));
			dp[ i ] = Integer.parseInt(st.nextToken( ));
			stair[ i ] = dp[ i ];
		}
	}

	static void solve( )
	{
		if (N >= 2) dp[ 2 ] += dp[ 1 ];
		for (int i = 3; i <= N; i++)
		{
			dp[ i ] = Integer.max(dp[ i - 3 ] + stair[ i - 1 ], dp[ i - 2 ]) + stair[ i ];
		}
		System.out.println(dp[ N ]);
	}
}
