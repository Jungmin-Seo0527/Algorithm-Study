
package DynamicProgramming;

import java.io.*;
import java.util.*;

// BOJ11053_가장_긴_증가하는_부분_수열
public class BOJ11053_가장_긴_증가하는_부분_수열
{
	static int N;
	static int[ ] dp, seq;

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		solve( );
	}

	// dp[i]= 현재 수열 i번째보다 먼저 나온 수중 현재 i번째 수보다 적은 값들중
	// dp값이 최대값 +1
	static void solve( )
	{
		int ans = 0;
		for (int i = 1; i <= N; i++)
		{
			int max = 0;
			int max_idx = 0;
			int cur = seq[ i ];
			for (int j = i; j >= 0; j--)
			{
				if (seq[ j ] < cur)
				{
					if (max < dp[ j ])
					{
						max = dp[ j ];
					}
				}
				dp[ i ] = max + 1;
				ans = Math.max(ans, dp[ i ]);
			}
		}
		System.out.println(ans);
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		N = Integer.parseInt(st.nextToken( ));
		dp = new int[ N + 1 ];
		seq = new int[ N + 1 ];
		st = new StringTokenizer(br.readLine( ));
		for (int i = 1; i <= N; i++)
		{
			seq[ i ] = Integer.parseInt(st.nextToken( ));
		}
	}
}