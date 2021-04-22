
package dp;

import java.io.*;
import java.util.*;

// BOJ1149_RGB거리
public class BOJ1149_RGB거리
{
	static int N;
	static int[ ][ ] dp;

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
		dp = new int[ N ][ 3 ];
		for (int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine( ));
			for (int j = 0; j < 3; j++)
			{
				dp[ i ][ j ] = Integer.parseInt(st.nextToken( ));
			}
		}
	}

	static void solve( )
	{
		for (int i = 1; i < N; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				dp[ i ][ j ] += Integer.min(dp[ i - 1 ][ (j + 1) % 3 ], dp[ i - 1 ][ (j + 2) % 3 ]);
			}
		}
		int ans = Integer.min(dp[ N - 1 ][ 0 ], dp[ N - 1 ][ 1 ]);
		ans = Integer.min(ans, dp[ N - 1 ][ 2 ]);
		System.out.println(ans);

	}
}
