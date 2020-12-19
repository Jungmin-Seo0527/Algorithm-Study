
package DynamicProgramming;

import java.io.*;
import java.util.*;

// BOJ2156_������_�ý�
public class BOJ2156_������_�ý�
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
			dp[ i ] = Integer.max(dp[ i - 1 ], dp[ i ]); // �߿� -> ���� ���� dp�� �ִ밪�� �ƴҼ� �ִ�.
			                                             // �������� ���ؼ� ���� �� ��ġ���� �ü� �ִ� �ִ밪���� ����
			ans = Integer.max(ans, dp[ i ]);
		}
		System.out.println(ans);
	}
}
