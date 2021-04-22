
package samsung;

import java.io.*;
import java.util.*;

// BOJ12100_2048_easy
// 그래프 자체를 회전시키면서 숫자 이동
public class BOJ12100_2048_easy_2
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
			Point temp[][] = moveUP(map);
			doDFS(temp, cnt + 1);
			map = rotate(map);
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

	static Point[ ][ ] rotate(Point graph[][])
	{
		Point ret[][] = new Point[ N ][ N ];
		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < N; j++)
			{
				int temp = graph[ N - j - 1 ][ i ].num;
				ret[ i ][ j ] = new Point(temp);
			}
		}
		return ret;
	}

	static Point[ ][ ] moveUP(Point graph[][])
	{
		Point ret[][] = new Point[ N ][ N ];
		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < N; j++)
			{
				ret[ i ][ j ] = new Point(graph[ i ][ j ].num);
			}
		}

		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < N; j++)
			{
				if (ret[ i ][ j ].num == 0) continue;
				int cur_row = i;
				int cur_col = j;
				int num = ret[ i ][ j ].num;
				ret[ i ][ j ].num = 0;
				while (true)
				{
					int next_row = cur_row - 1;
					int next_col = j;
					if (check(next_row, next_col) == false)
					{
						ret[ cur_row ][ cur_col ].num = num;
						break;
					}
					if (ret[ next_row ][ next_col ].num == num)
					{
						if (ret[ next_row ][ next_col ].plus == false)
						{
							ret[ next_row ][ next_col ].num *= 2;
							ret[ next_row ][ next_col ].plus = true;
							break;
						}
						else
						{
							ret[ cur_row ][ cur_col ].num = num;
							break;
						}
					}
					else if (ret[ next_row ][ next_col ].num > 0)
					{
						ret[ cur_row ][ cur_col ].num = num;
						break;
					}
					cur_row = next_row;
					cur_col = next_col;
				}
			}
		}

		return ret;
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
