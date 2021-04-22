
package samsung;

import java.io.*;
import java.util.*;

// BOJ20056_마법사_상어와_파이어볼
public class BOJ20056_마법사_상어와_파이어볼
{
	static class Ball
	{
		int massive;
		int speed;
		int dir;

		public Ball(int massive, int speed, int dir)
		{
			this.massive = massive;
			this.speed = speed;
			this.dir = dir;
		}
	}

	static int N, M, K;

	static int[ ] v_r =
	{ -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[ ] v_c =
	{ 0, 1, 1, 1, 0, -1, -1, -1 };

	static LinkedList< Ball > graph[][];

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		for (int i = 0; i < K; i++)
		{
			moveBalls( );
		}
		System.out.println(countMassive( ));
	}

	static void moveBalls( )
	{
		LinkedList< Ball > temp[][] = new LinkedList[ N ][ N ];
		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < N; j++)
			{
				temp[ i ][ j ] = new LinkedList<>( );
			}
		}

		// ball 이동
		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < N; j++)
			{
				if (graph[ i ][ j ].size( ) > 0)
				{
					for (Ball ball : graph[ i ][ j ])
					{
						int next_row = i + v_r[ ball.dir ] * ball.speed % N;
						int next_col = j + v_c[ ball.dir ] * ball.speed % N;
						
						
						// 양 끝은 서로 이어져 있음
						if (next_row >= N) next_row -= N;
						else if (next_row < 0) next_row += N;
						if (next_col >= N) next_col -= N;
						else if (next_col < 0) next_col += N;

						temp[ next_row ][ next_col ]
						                .add(new Ball(ball.massive, ball.speed, ball.dir));
					}
				}
			}
		}
		graph = temp;
		removeOverlap( );
	}

	// 중복 처리
	static void removeOverlap( )
	{
		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < N; j++)
			{
				if (graph[ i ][ j ].size( ) > 1)
				{
					int massive_sum = 0;
					int speed_sum = 0;
					boolean even = true, odd = true;

					for (Ball cur_ball : graph[ i ][ j ])
					{
						massive_sum += cur_ball.massive;
						speed_sum += cur_ball.speed;

						if (cur_ball.dir % 2 == 0) odd = false;
						else even = false;
					}

					massive_sum /= 5;
					speed_sum /= graph[ i ][ j ].size( );
					graph[ i ][ j ].clear( );

					if (massive_sum > 0)
					{
						for (int a = 0; a < 4; a++)
						{
							if (odd || even)
							{
								graph[ i ][ j ].add(new Ball(massive_sum, speed_sum,
								                2 * a));
							}
							else
							{
								graph[ i ][ j ].add(new Ball(massive_sum, speed_sum,
								                1 + 2 * a));
							}
						}
					}
				}
			}
		}
	}

	static long countMassive( )
	{
		long ret = 0;
		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < N; j++)
			{
				for (Ball ball : graph[ i ][ j ])
				{
					ret += ball.massive;
				}
			}
		}
		return ret;
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		N = Integer.parseInt(st.nextToken( ));
		M = Integer.parseInt(st.nextToken( ));
		K = Integer.parseInt(st.nextToken( ));
		graph = new LinkedList[ N ][ N ];
		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < N; j++)
			{
				graph[ i ][ j ] = new LinkedList<>( );
			}
		}
		for (int i = 0; i < M; i++)
		{
			st = new StringTokenizer(br.readLine( ));
			int r = Integer.parseInt(st.nextToken( ));
			int c = Integer.parseInt(st.nextToken( ));
			int m = Integer.parseInt(st.nextToken( ));
			int s = Integer.parseInt(st.nextToken( ));
			int d = Integer.parseInt(st.nextToken( ));
			graph[ r - 1 ][ c - 1 ].add(new Ball(m, s, d));
		}
	}
}
