package DynamicProgramming;

import java.io.*;
import java.util.*;

// BOJ1932_정수_삼각형
public class BOJ1932_정수_삼각형
{
	static int n;
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
		n = Integer.parseInt(st.nextToken( ));
		dp = new int[ n ][ n ];

		for (int i = 0; i < n; i++)
		{
			st = new StringTokenizer(br.readLine( ));
			for (int j = 0; j <= i; j++)
			{
				dp[ i ][ j ] = Integer.parseInt(st.nextToken( ));
			}
		}
	}
	
	// 피라미드 가장 밑층부터 인접한 두 수중 큰수를 바로 윗층의 수와 더함
	static void solve( )
	{
		int start_row = n - 1;
		while (true)
		{
			if (start_row == 0) break;
			for (int i = 0; i < start_row; i++)
			{
				dp[ start_row - 1 ][ i ] += Integer.max(dp[ start_row ][ i ], dp[ start_row ][ i + 1 ]);
			}
			start_row--;
		}
		System.out.println(dp[ 0 ][ 0 ]);
	}
}
