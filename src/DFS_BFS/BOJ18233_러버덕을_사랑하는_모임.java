
package DFS_BFS;

import java.io.*;
import java.util.*;

// BOJ18233_러버덕을_사랑하는_모임
public class BOJ18233_러버덕을_사랑하는_모임
{
	static class Info
	{
		int min, max;

		Info(int _m1, int _m2)
		{
			this.min = _m1;
			this.max = _m2;
		}
	}

	static int N, P, E, ans = -1;
	static Info[ ] person;
	static boolean visited[];

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		solve(0, 0, 0, 0);
		if (ans == -1) System.out.println(ans);
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		N = Integer.parseInt(st.nextToken( ));
		P = Integer.parseInt(st.nextToken( ));
		E = Integer.parseInt(st.nextToken( ));
		person = new Info[ N + 1 ];
		visited = new boolean[ N + 1 ];

		for (int i = 1; i <= N; i++)
		{
			st = new StringTokenizer(br.readLine( ));
			int min = Integer.parseInt(st.nextToken( ));
			int max = Integer.parseInt(st.nextToken( ));
			person[ i ] = new Info(min, max);
		}
	}

	// DFS
	static void solve(int min, int max, int n, int p)
	{
		if (ans == 1) return;
		if (n == P)
		{
			if (min <= E && max >= E)
			{
				// 해결
				ans = 1;
				int[ ] ret = new int[ N + 1 ];
				for (int i = 1; i <= N; i++)
				{
					if (visited[ i ])
					{
						ret[ i ] = person[ i ].min;
						E -= ret[ i ];
					}
				}

				// 배분
				while (true)
				{
					if (E == 0) break;
					for (int i = 1; i <= N; i++)
					{
						if (visited[ i ])
						{
							if (ret[ i ] + 1 <= person[ i ].max)
							{
								ret[ i ]++;
								E--;
								if (E == 0) break;
							}
						}
					}
				}

				// 출력
				for (int i = 1; i <= N; i++)
				{
					System.out.print(ret[ i ] + " ");
				}

				return;
			}
		}

		for (int i = p + 1; i <= N; i++)
		{
			if (visited[ i ] == false)
			{
				visited[ i ] = true;
				solve(min + person[ i ].min, max + person[ i ].max, n + 1, i);
				visited[ i ] = false;
			}
		}
	}
}
