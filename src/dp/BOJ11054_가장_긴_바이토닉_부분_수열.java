
package dp;

import java.io.*;
import java.util.*;

// BOJ11054_가장_긴_바이토닉_부분_수열
public class BOJ11054_가장_긴_바이토닉_부분_수열
{
	static int N;
	static int[ ] seq;
	static int[ ][ ] dp;

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		solve( );
	}

	static void solve( )
	{
		for (int i = 1; i <= N; i++)
		{
			int cur = seq[ i ];
			int max_dp = 0;

			int cur2 = seq[ N - i + 1 ];
			int max_dp2 = 0;

			int temp = N - i + 1;
			for (int j = i; j >= 0; j--)
			{
				if (seq[ j ] < cur) // 인덱스 1부터 가장 긴 증가하는 부분 수열
				{
					max_dp = Math.max(dp[ 0 ][ j ], max_dp);
				}

				if (seq[ N - i + 1 + j ] < cur2) // 인덱스 N부터 가장 긴 증가하는 부분 수열 (방항은 0으로)
				{
					max_dp2 = Math.max(dp[ 1 ][ N - i + 1 + j ], max_dp2);
				}

			}
			dp[ 0 ][ i ] = max_dp + 1;
			dp[ 1 ][ N - i + 1 ] = max_dp2 + 1;
		}

		int ans = 0;
		for (int i = 1; i <= N; i++)
		{
			ans = Math.max(dp[ 0 ][ i ] + dp[ 1 ][ i ], ans);
		}
		System.out.println(ans - 1);
	}

	static void show( )
	{
		StringBuilder sb = new StringBuilder( );
		for (int i = 1; i <= N; i++)
		{
			sb.append(dp[ 0 ][ i ] + "       ");
		}
		sb.append("\n");
		for (int i = 1; i <= N; i++)
		{
			sb.append(dp[ 1 ][ i ] + "       ");
		}
		System.out.println(sb);
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		N = Integer.parseInt(st.nextToken( ));
		seq = new int[ N + 2 ];
		dp = new int[ 2 ][ N + 2 ];

		st = new StringTokenizer(br.readLine( ));
		for (int i = 1; i <= N; i++)
		{
			seq[ i ] = Integer.parseInt(st.nextToken( ));
		}
	}
}
