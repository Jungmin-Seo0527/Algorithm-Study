
package TwoPointer;

import java.io.*;
import java.util.*;

// BOJ2559_수열
public class BOJ2559_수열
{
	static int N, K;
	static int[ ] graph;

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		solve( );
	}

	static void solve( )
	{
		int start = 0;
		int end = start + K - 1;
		int sum = 0;

		for (int i = start; i <= end; i++)
		{
			sum += graph[ i ];
		}
		int max = sum;

		while (end < N - 1)
		{
			sum += graph[ ++end ];
			sum -= graph[ start++ ];
			max = Math.max(max, sum);
		}
		System.out.println(max);
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		N = Integer.parseInt(st.nextToken( ));
		K = Integer.parseInt(st.nextToken( ));
		graph = new int[ N ];

		st = new StringTokenizer(br.readLine( ));
		for (int i = 0; i < N; i++)
		{
			graph[ i ] = Integer.parseInt(st.nextToken( ));
		}
	}
}
