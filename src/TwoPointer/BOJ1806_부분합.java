
package TwoPointer;

import java.io.*;
import java.util.*;

// BOJ1806_부분합
public class BOJ1806_부분합
{
	static int N, S;
	static int[ ] graph;

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
		S = Integer.parseInt(st.nextToken( ));
		graph = new int[ N ];
		st = new StringTokenizer(br.readLine( ));
		for (int i = 0; i < N; i++)
		{
			graph[ i ] = Integer.parseInt(st.nextToken( ));
		}
	}

	static void solve( )
	{
		int start = 0, end = 0, sum = graph[ 0 ];
		int min_len = Integer.MAX_VALUE;

		while (start <= end && end < N && start < N)
		{
			if (sum < S)
			{
				if (end + 1 < N)
				{
					sum += graph[ end + 1 ];
					end++;
				}
				else break;
			}
			else if (sum >= S)
			{
				min_len = Integer.min(min_len, end - start + 1);
				sum -= graph[ start++ ];
			}
		}
		if (min_len == Integer.MAX_VALUE) min_len = 0;
		System.out.println(min_len);
	}
}
