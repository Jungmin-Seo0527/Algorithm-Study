
package Samgsung;

import java.io.*;
import java.util.*;

// BOJ17141_연구소2
public class BOJ17141_연구소2
{
	static class Point
	{
		int row, col;
		int cnt = 0;

		public Point(int row, int col)
		{
			this.row = row;
			this.col = col;
		}
	}

	static int N, M, graph[][], ans = -1;
	static int v_r[] =
	{ 0, 1, 0, -1 };
	static int v_c[] =
	{ 1, 0, -1, 0 };

	static Queue< Point > que = new LinkedList<>( );
	static LinkedList< Point > virusList = new LinkedList<>( );

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		int arr[] = new int[ M ];
		doDFS(0, arr, 0);
		System.out.println(ans);
	}

	// 총 바이러스중 M개 골라서 그 바이러스들만 존재한다는 가정하에 BFS 수행
	static void doDFS(int vidx, int arr[], int cnt)
	{
		if (cnt == M)
		{
			int t = doBFS(arr);
			if (t == -1) return;
			else
			{
				if (ans == -1)
				{
					ans = t;
				}
				else if (ans > t) ans = t;
			}
			return;
		}
		for (int i = vidx; i < virusList.size( ); i++)
		{
			arr[ cnt ] = i;
			doDFS(i + 1, arr, cnt + 1);
		}
	}

	static int doBFS(int arr[])
	{
		Queue< Point > que = new LinkedList<>( );
		boolean visited[][] = new boolean[ N ][ N ];
		for (int i = 0; i < M; i++)
		{
			Point cur = new Point(virusList.get(arr[ i ]).row, virusList.get(arr[ i ]).col);
			que.add(cur);
		}
		int ret = 0;
		while (que.isEmpty( ) == false)
		{
			Point cur = que.poll( );
			if (visited[ cur.row ][ cur.col ]) continue;
			visited[ cur.row ][ cur.col ] = true;

			// 현재 위치에 활성화 혹은 비활성화 바이러스가 없는 곳으로만 경로 거리를 추적한다.
			// 이 경우는 만약 제일 마지막에 가야할 곳에 이미 바이러스가 있는 경우에
			// 그곳으로 갈 필요 없이 바이러스가 전부 퍼져있는 상태에 대한 조건이다

			ret = cur.cnt;

			for (int i = 0; i < 4; i++)
			{
				int next_row = cur.row + v_r[ i ];
				int next_col = cur.col + v_c[ i ];
				if (check(next_row, next_col, visited))
				{
					Point next = new Point(next_row, next_col);
					next.cnt = cur.cnt + 1;
					que.add(next);
				}
			}
		}

		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < N; j++)
			{
				if (graph[ i ][ j ] == 0 && visited[ i ][ j ] == false) return -1;
			}
		}
		return ret;
	}

	static boolean check(int r, int c, boolean visited[][])
	{
		if (r < 0 || r >= N || c < 0 || c >= N) return false;
		if (graph[ r ][ c ] == 1) return false;
		if (visited[ r ][ c ] == true) return false;

		return true;
	}

	static void show(int arr[][])
	{
		StringBuilder sb = new StringBuilder( );
		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < N; j++)
			{
				sb.append(arr[ i ][ j ] + "  ");
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
		graph = new int[ N ][ N ];

		for (int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine( ));
			for (int j = 0; j < N; j++)
			{
				int input = Integer.parseInt(st.nextToken( ));
				graph[ i ][ j ] = input;
				if (input == 2)
				{
					virusList.add(new Point(i, j));
				}
			}
		}
	}
}
