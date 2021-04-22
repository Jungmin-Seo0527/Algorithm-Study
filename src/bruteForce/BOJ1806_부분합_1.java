
package bruteForce;

import java.io.*;
import java.util.*;

// BOJ1806_부분합
public class BOJ1806_부분합_1
{
	static int N, S, start, end;
	static int[ ] graph;

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		System.out.println(solve( ));
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
	static int solve( )
	{
		int ret = Integer.MAX_VALUE;
		int sum = graph[ 0 ];
		while (start <= end)
		{
			//System.out.println(start+" "+end+" "+sum);
			if (sum >= S)
			{
				if (ret > end - start + 1)
				{
					ret = end - start + 1;
				}
				sum -= graph[ start++ ];

			}
			else if (end == N - 1) break;
			else
			{
				sum += graph[ ++end ];
			}
		}
		if (ret == Integer.MAX_VALUE) ret = 0;
		return ret;
	}
}
