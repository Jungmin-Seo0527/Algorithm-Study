
package DynamicProgramming;

import java.io.*;
import java.util.*;

// BOJ2156_포도주_시식
public class BOJ2156_포도주_시식
{
	static int n;
	static int[ ] wine, dp;

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
		wine = new int[ n + 1 ];
		dp = new int[ n + 1 ];
		for (int i = 1; i <= n; i++)
		{
			st = new StringTokenizer(br.readLine( ));
			wine[ i ] = Integer.parseInt(st.nextToken( ));
		}
		dp[ 1 ] = wine[ 1 ];
		if (n > 1) dp[ 2 ] = wine[ 1 ] + wine[ 2 ];
	}

	static void solve( )
	{
		int ans = dp[ 1 ];
		if (n > 1) ans = Integer.max(dp[ 1 ], dp[ 2 ]);

		for (int i = 3; i <= n; i++)
		{
			dp[ i ] = Integer.max(dp[ i - 2 ], dp[ i - 3 ] + wine[ i - 1 ]) + wine[ i ];
			dp[ i ] = Integer.max(dp[ i - 1 ], dp[ i ]); // 중요 -> 현재 나의 dp가 최대값이 아닐수 있다.
			                                             // 이전값과 비교해서 현재 내 위치까지 올수 있는 최대값으로 갱신
			ans = Integer.max(ans, dp[ i ]);
		}
		System.out.println(ans);
	}
}
