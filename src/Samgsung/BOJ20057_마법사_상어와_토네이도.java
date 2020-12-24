
package Samgsung;

import java.io.*;
import java.util.*;

// BOJ20057_마법사_상어와_토네이도
public class BOJ20057_마법사_상어와_토네이도
{
	static int N, ans;

	static int v_r[] =
	{ 0, 1, 0, -1 };
	static int v_c[] =
	{ -1, 0, 1, 0 };
	static int graph[][];

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		solve( );
		System.out.println(ans);
	}

	static void solve( )
	{
		int dir = 0, go = 1, cnt = 0;
		int row = (N - 1) / 2, col = (N - 1) / 2;
		while (true)
		{
			if (row == 0 && col == 0) break;
			for (int i = 0; i < go; i++)
			{
				row = row + v_r[ dir ];
				col = col + v_c[ dir ];
				//System.out.println(row + " " + col);

				// 먼지 이동
				if (graph[ row ][ col ] > 0)
				{
					int next_row = 0;
					int next_col = 0;
					int total_moved_send = 0;
					int send = 0;

					// 5%
					next_row = row + v_r[ dir ] * 2;
					next_col = col + v_c[ dir ] * 2;
					send = graph[ row ][ col ] * 5 / 100;
					total_moved_send += send;
					moveSend(next_row, next_col, send);

					// 10%
					for (int j = 1; j <= 3; j += 2)
					{
						next_row = row + v_r[ dir ] + v_r[ (dir + j) % 4 ];
						next_col = col + v_c[ dir ] + v_c[ (dir + j) % 4 ];
						send = graph[ row ][ col ] / 10;
						total_moved_send += send;
						moveSend(next_row, next_col, send);
					}

					// 7%
					for (int j = 1; j <= 3; j += 2)
					{
						next_row = row + v_r[ (dir + j) % 4 ];
						next_col = col + v_c[ (dir + j) % 4 ];
						send = graph[ row ][ col ] * 7 / 100;
						total_moved_send += send;
						moveSend(next_row, next_col, send);
					}

					// 2% 
					for (int j = 1; j <= 3; j += 2)
					{
						next_row = row + v_r[ (dir + j) % 4 ] * 2;
						next_col = col + v_c[ (dir + j) % 4 ] * 2;
						send = graph[ row ][ col ] * 2 / 100;
						total_moved_send += send;
						moveSend(next_row, next_col, send);
					}

					// 1%
					for (int j = 1; j <= 3; j += 2)
					{
						next_row = row + v_r[ (dir + j) % 4 ] + v_r[ (dir + 2) % 4 ];
						next_col = col + v_c[ (dir + j) % 4 ] + v_c[ (dir + 2) % 4 ];
						send = graph[ row ][ col ] / 100;
						total_moved_send += send;
						moveSend(next_row, next_col, send);
					}

					// a
					next_row = row + v_r[ dir ];
					next_col = col + v_c[ dir ];
					send = graph[ row ][ col ] - total_moved_send;
					moveSend(next_row, next_col, send);
				}
				graph[ row ][ col ] = 0;
				if (row == 0 && col == 0) break;
			}

			// 나선형 이동을 위한 방향변경과 이동 거리(2번 방향을 바꿀때마다 이동거리 +1)
			dir = (dir + 1) % 4;
			cnt++;
			if (cnt == 2)
			{
				go++;
				cnt = 0;
			}

		}
	}

	static void moveSend(int r, int c, int s)
	{
		if (r < 0 || r >= N || c < 0 || c >= N)
		{
			ans += s;
		}
		else
		{
			graph[ r ][ c ] += s;
		}
	}

	static void show( )
	{
		StringBuilder sb = new StringBuilder( );
		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < N; j++)
			{
				sb.append(graph[ i ][ j ] + "     ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		N = Integer.parseInt(st.nextToken( ));
		graph = new int[ N ][ N ];

		for (int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine( ));
			for (int j = 0; j < N; j++)
			{
				graph[ i ][ j ] = Integer.parseInt(st.nextToken( ));
			}
		}

	}
}
