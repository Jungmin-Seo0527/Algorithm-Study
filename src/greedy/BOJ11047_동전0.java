
package greedy;

import java.io.*;
import java.util.*;

// BOJ11047_동전0
// Greedy
// 동전의 값이 큰것부터 조사
public class BOJ11047_동전0
{
	static int N, K, arr[];

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		solve( );
	}

	static void solve( )
	{
		int ans = 0;
		for (int i = N - 1; i >= 0; i--)
		{
			if (K / arr[ i ] > 0)
			{
				ans += K / arr[ i ];
				K = K % arr[ i ];
			}
			if (K == 0) break;
		}
		System.out.println(ans);
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		N = Integer.parseInt(st.nextToken( ));
		K = Integer.parseInt(st.nextToken( ));
		arr = new int[ N ];
		for (int i = 0; i < N; i++)
		{
			arr[ i ] = Integer.parseInt(br.readLine( ));
		}
	}
}
