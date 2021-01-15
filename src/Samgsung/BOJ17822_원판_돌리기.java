
package Samgsung;

import java.io.*;
import java.util.*;

// BOJ17822_원판_돌리기
public class BOJ17822_원판_돌리기
{
	static int N, M, T, circle[][];

	public static void main(String[ ] args) throws IOException
	{
		//inputAndSettingData( );
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		N = Integer.parseInt(st.nextToken( ));
		M = Integer.parseInt(st.nextToken( ));
		T = Integer.parseInt(st.nextToken( ));
		circle = new int[ N ][ M ];
		for (int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine( ));
			for (int j = 0; j < M; j++)
			{
				circle[ i ][ j ] = Integer.parseInt(st.nextToken( ));
			}
		}
		for (int i = 0; i < T; i++)
		{
			st = new StringTokenizer(br.readLine( ));
			int x = Integer.parseInt(st.nextToken( ));
			int d = Integer.parseInt(st.nextToken( ));
			int k = Integer.parseInt(st.nextToken( ));
			rotateCircle(x, d, k);
			//show(circle);
			remove( );
			//show(circle);
		}
		System.out.println(count( ));
	}

	// 원판돌리기 -> 원판 하나를 일차원 배열로 구현
	// 양 끝점이 서로 이웃되므로 col 에 이동 거리 + M을 한번 더 다한후에 M으로 나눈 나머지
	static void rotateCircle(int x, int d, int k)
	{
		int temp[][] = new int[ N ][ M ];
		for (int i = 0; i < N; i++)
		{
			temp[ i ] = circle[ i ].clone( );
		}
		if (d == 0) d = 1;
		else d = -1;

		for (int i = 0; i < N; i++)
		{
			if ((i + 1) % x != 0) continue;
			for (int j = 0; j < M; j++)
			{
				circle[ i ][ (j + d * k + M) % M ] = temp[ i ][ j ]; // col이 음수일때를 고려해서 M을 한번 더해준다
			}
		}
	}

	static void remove( )
	{
		Queue< Integer > que = new LinkedList( );
		LinkedList< Integer > list = new LinkedList( );
		int v_r[] =
		{ 1, -1, 0, 0 };
		int v_c[] =
		{ 0, 0, 1, -1 };
		boolean changed = false;

		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < M; j++)
			{
				// 인접하면서 서로 같은 수 탐색 BFS
				int num = circle[ i ][ j ];
				if (num == 0) continue;
				que.clear( );
				list.clear( );
				boolean visited[][] = new boolean[ N ][ M ];
				visited[ i ][ j ] = true;
				que.add(i * M + j);
				list.add(i * M + j);
				while (que.isEmpty( ) == false)
				{
					int cur = que.poll( );
					int cur_row = cur / M;
					int cur_col = cur % M;
					for (int d = 0; d < 4; d++)
					{
						int next_row = cur_row + v_r[ d ];
						int next_col = (cur_col + v_c[ d ] + M) % M;
						if (check(next_row, next_col))
						{
							if (visited[ next_row ][ next_col ]) continue;
							if (circle[ next_row ][ next_col ] == num)
							{
								list.add(next_row * M + next_col);
								que.add(next_row * M + next_col);
								visited[ next_row ][ next_col ] = true;
							}
						}
					}
				}
				if (list.size( ) == 1) continue;
				for (int p : list)
				{
					changed = true;
					int row = p / M;
					int col = p % M;
					circle[ row ][ col ] = 0;
				}
			}
		}

		// 변화가 없는 경우 (서로 인접한 수가 없음)
		if (changed == false)
		{
			double sum = 0;
			double cnt = 0.0;
			for (int i = 0; i < N; i++)
			{
				for (int j = 0; j < M; j++)
				{
					if (circle[ i ][ j ] != 0)
					{
						sum += circle[ i ][ j ];
						cnt++;
					}
				}
			}
			sum = sum / cnt;
			for (int i = 0; i < N; i++)
			{
				for (int j = 0; j < M; j++)
				{
					if (circle[ i ][ j ] != 0)
					{
						if (circle[ i ][ j ] < sum)
						{
							circle[ i ][ j ]++;
						}
						else if (circle[ i ][ j ] > sum)
						{
							circle[ i ][ j ]--;
						}
					}
				}
			}
		}
	}

	static int count( )
	{
		int ret = 0;
		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < M; j++)
			{
				ret += circle[ i ][ j ];
			}
		}
		return ret;
	}

	static boolean check(int r, int c)
	{
		if (r < 0 || r >= N || c < 0 || c >= M) return false;

		return true;
	}

	static void show(int ret[][])
	{
		StringBuilder sb = new StringBuilder( );
		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < M; j++)
			{
				sb.append(ret[ i ][ j ] + " ");
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
		M = Integer.parseInt(st.nextToken( ));
		T = Integer.parseInt(st.nextToken( ));
		circle = new int[ N ][ M ];
		for (int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine( ));
			for (int j = 0; j < M; j++)
			{
				circle[ i ][ j ] = Integer.parseInt(st.nextToken( ));
			}
		}
	}
}
