
package dp;

import java.io.*;
import java.util.*;

// BOJ1463_1로만들기
public class BOJ1463_1로만들기
{
	static int N;
	static int[ ] dp;

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
	}

	static void solve( )
	{
		for (int i = N; i >= 1; i--)
		{
			// 3가지 경우
			for (int j = 1; j <= 3; j++)
			{
				// -1
				if (j == 1)
				{
					if (i - 1 > 0) // 단 확인하려는 곳이 1이상
					{
						if ((dp[ i - 1 ] > dp[ i ] + 1) || dp[ i - 1 ] == 0)
						{
							dp[ i - 1 ] = dp[ i ] + 1;
						}
					}
				}
				else // 2또는 3으로 나눌때 나머지가 0이면 그수로 나눔
				{
					if (i % j == 0 && i / j > 0)
					{
						int temp = i / j;
						if (dp[ temp ] > dp[ i ] + 1 || dp[ temp ] == 0)
						{
							dp[ temp ] = dp[ i ] + 1;
						}
					}
				}
			}
		}
		System.out.println(dp[ 1 ]);
	}
}
