package DynamicProgramming;

import java.io.*;
import java.util.*;

// BOJ18232_텔리포트_정거장
// dp + linkedList[] 
public class BOJ18232_텔리포트_정거장
{
	static int N, M, S, E;
	static int[ ] dp;

	static LinkedList< Integer >[ ] tel;

	static Queue< Integer > que = new LinkedList<>( );

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
		M = Integer.parseInt(st.nextToken( ));
		dp = new int[ N + 1 ];
		tel = new LinkedList[ N + 1 ];

		for (int i = 0; i < N + 1; i++)
		{
			tel[ i ] = new LinkedList< Integer >( );
		}

		st = new StringTokenizer(br.readLine( ));
		S = Integer.parseInt(st.nextToken( ));
		E = Integer.parseInt(st.nextToken( ));

		for (int i = 0; i < M; i++)
		{
			st = new StringTokenizer(br.readLine( ));
			int t_1 = Integer.parseInt(st.nextToken( ));
			int t_2 = Integer.parseInt(st.nextToken( ));
			tel[ t_1 ].add(t_2);
			tel[ t_2 ].add(t_1);
		}
		que.add(S);
	}

	static void solve( )
	{
		while (que.isEmpty( ) == false)
		{
			int cur = que.poll( );
			if (cur == E)
			{
				System.out.println(dp[ E ]);
				break;
			}

			// +1, -1;
			for (int i = -1; i <= 1; i++)
			{
				int next = cur + i;
				if (i == 0) continue;

				if (next == S) continue;
				if (next == cur) continue;
				if (next > N) continue;
				if (next <= 0) continue;

				if (dp[ next ] == 0 || dp[ next ] > dp[ cur ] + 1)
				{
					dp[ next ] = dp[ cur ] + 1;
					que.add(next);
				}
			}

			// tel
			for (int i = 0; i < tel[ cur ].size( ); i++)
			{
				int next = tel[ cur ].get(i);
				if (next == S) continue;
				if (next == cur) continue;
				if (next > N) continue;
				if (next <= 0) continue;

				if (dp[ next ] == 0 || dp[ next ] > dp[ cur ] + 1)
				{
					dp[ next ] = dp[ cur ] + 1;
					que.add(next);
				}
			}

		}
	}
}
