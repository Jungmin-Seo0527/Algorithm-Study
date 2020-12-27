
package EuclideanAlgorithm;

import java.io.*;
import java.util.*;

// BOJ9613_GCD합
public class BOJ9613_GCD합
{
	static int T;

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		StringBuilder sb = new StringBuilder( );
		T = Integer.parseInt(st.nextToken( ));
		for (int i = 0; i < T; i++)
		{
			st = new StringTokenizer(br.readLine( ));
			int n = Integer.parseInt(st.nextToken( ));
			int list[] = new int[ n ];
			for (int j = 0; j < n; j++)
			{
				list[ j ] = Integer.parseInt(st.nextToken( ));
			}
			long ans = 0;
			for (int j = 0; j < n - 1; j++)
			{
				for (int k = j + 1; k < n; k++)
				{
					ans += gcd(list[ j ], list[ k ]);
				}
			}
			sb.append(ans + "\n");
		}
		System.out.println(sb);
	}

	static int gcd(int p, int q)
	{
		if (q == 0) return p;
		else return gcd(q, p % q);
	}
}
