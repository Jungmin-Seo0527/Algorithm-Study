
package samsung;

import java.io.*;
import java.util.*;

// BOJ12100_2048_easy
// 숫자 이동이 어려운 문제
public class BOJ12100_2048_easy
{
	static class Point
	{
		int num;
		boolean plus = false; // 합치기 가능 여부 (false -> 합친적 없음(합치기 가능))

		public Point(int num)
		{
			this.num = num;
		}
	}

	static int N, ans = Integer.MIN_VALUE;

	// 오 아 왼 위
	static int v_r[] =
	{ 0, 1, 0, -1 };
	static int v_c[] =
	{ 1, 0, -1, 0 };

	public static void main(String[ ] args) throws IOException
	{
		Point graph[][] = inputAndSettingData( );
		doDFS(graph, 0);
		System.out.println(ans);
	}

	static void doDFS(Point map[][], int cnt)
	{
		if (cnt == 5)
		{
			ans = Math.max(ans, maxNum(map));
			return;
		}
		for (int i = 0; i < 4; i++)
		{
			Point temp[][] = moveNum(map, i);
			doDFS(temp, cnt + 1);
		}
	}

	static int maxNum(Point map[][])
	{
		int ret = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < N; j++)
			{
				if (map[ i ][ j ].num > ret)
				{
					ret = map[ i ][ j ].num;
				}
			}
		}
		return ret;
	}

	// 움직이려는 방향에 따라 시작지점을 다르게 하고
	// 움직이려는 방향과 시작점이 같은 선상에 있는 숫자들을 모두 이동후
	// 시작점을 움직이려는 방향의 90도 시계방향으로 이동된 지점으로 옮겨준다.
	static Point[ ][ ] moveNum(Point graph[][], int dir)
	{
		// 반환할 map 할당
		Point map[][] = new Point[ N ][ N ];
		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < N; j++)
			{
				map[ i ][ j ] = new Point(graph[ i ][ j ].num);
			}
		}

		// 움직이는 방향에 따라 시작점을 지정
		int start_row = 0, start_col = 0;
		switch (dir)
		{
		case 0 : // 오른쪽으로 이동 시작점: 우상단
			start_row = 0;
			start_col = N - 1;

			break;
		case 1 : // 아래쪽으로 이동 시작점: 우하단
			start_row = N - 1;
			start_col = N - 1;
			break;
		case 2 : // 왼쪽으로 이동 시작점: 좌하단
			start_row = N - 1;
			start_col = 0;
			break;
		case 3 : // 위쪽으로 이동 시작점: 좌상단
			start_row = 0;
			start_col = 0;
			break;

		default :
			break;
		}

		for (int i = 0; i < N; i++)
		{
			// 움직임을 시작하는 지점
			int cur_row = start_row + v_r[ (dir + 1) % 4 ] * i;
			int cur_col = start_col + v_c[ (dir + 1) % 4 ] * i;
			for (int j = 0; j < N; j++)
			{
				// 움직이려는 방향과 같은 방향의 숫자들 이동(다음 숫자는 이동하려는 방향 반대방향으로 다음 숫자)
				int move_row = cur_row + v_r[ (dir + 2) % 4 ] * j;
				int move_col = cur_col + v_c[ (dir + 2) % 4 ] * j;
				int num = map[ move_row ][ move_col ].num;
				if (num == 0) continue;
				map[ move_row ][ move_col ].num = 0;
				while (true) // 숫자 이동
				{
					int next_row = move_row + v_r[ dir ];
					int next_col = move_col + v_c[ dir ];
					if (check(next_row, next_col) == false) // 범위 초과
					{
						map[ move_row ][ move_col ].num = num;
						break;
					}

					if (map[ next_row ][ next_col ].num == num) // 같은 숫자를 만남
					{
						if (map[ next_row ][ next_col ].plus == false) // 합치기 가능
						{
							map[ next_row ][ next_col ].num *= 2;
							map[ next_row ][ next_col ].plus = true;
							break;
						}
						else // 합치기 불가능
						{
							map[ move_row ][ move_col ].num = num;
							break;
						}
					}
					else if (map[ next_row ][ next_col ].num > 0) // 0이 아닌 다른 숫자와 만남
					{
						map[ move_row ][ move_col ].num = num;
						break;
					}
					move_row = next_row;
					move_col = next_col;
					//show(map);
				}
			}
		}
		return map;
	}

	static boolean check(int r, int c)
	{
		if (r < 0 || r >= N || c < 0 || c >= N) return false;
		return true;
	}

	static void show(Point graph[][])
	{
		StringBuilder sb = new StringBuilder( );
		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < N; j++)
			{
				sb.append(graph[ i ][ j ].num + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	static Point[ ][ ] inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		N = Integer.parseInt(st.nextToken( ));
		Point[ ][ ] graph = new Point[ N ][ N ];
		for (int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine( ));
			for (int j = 0; j < N; j++)
			{
				graph[ i ][ j ] = new Point(Integer.parseInt(st.nextToken( )));
			}
		}
		return graph;
	}
}
