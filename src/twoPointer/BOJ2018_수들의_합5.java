
package twoPointer;

import java.io.*;
import java.util.*;

// BOJ2018_수들의_합5
public class BOJ2018_수들의_합5
{
	static int N, ans;
	static int[ ] graph;

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		solve( );
		System.out.println(ans);
	}

	static void solve( )
	{
		int start = 0;
		int end = 0;
		int sum = 0;

		while (start <= end)
		{

			if (sum < N && end < N)
			{
				sum += graph[ ++end ];
			}
			else if (sum > N)
			{
				sum -= graph[ start++ ];
			}
			else
			{
				ans++;
				if (end < N) sum += graph[ ++end ];
				sum -= graph[ start++ ];
			}
		}
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		N = Integer.parseInt(st.nextToken( ));
		graph = new int[ N + 1 ];
		for (int i = 0; i <= N; i++)
		{
			graph[ i ] = i;
		}
	}
}
