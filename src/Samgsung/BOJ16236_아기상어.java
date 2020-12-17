package Samgsung;

import java.io.*;
import java.util.*;

// BOJ16236_아기상어
public class BOJ16236_아기상어
{
	private static class Point implements Comparable< Point >
	{
		int row, col, dist;

		Point(int _r, int _c)
		{
			this.row = _r;
			this.col = _c;
		}

		@Override
		// priority queue 이용
		// 우선순위 조건 : 1. 거리 2. row  3. col
		public int compareTo(Point o)
		{
			// TODO Auto-generated method stub
			if (this.dist < o.dist)
			{
				return -1;
			}
			else if (this.dist == o.dist)
			{
				if (this.row < o.row)
				{
					return -1;
				}
				else if (this.row == o.row)
				{
					if (this.col < o.col)
					{
						return -1;
					}
					else if (this.col == o.col)
					{
						return 0;
					}
				}
			}
			return 1;
		}
	}

	static int N, cur_size, num, ans;
	static int[ ][ ] graph;

	static Point start;

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		while (true)
		{
			if (doBFS( ) == false)
			{
				break;
			}
		}
		System.out.println(ans);
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
				if (graph[ i ][ j ] == 9)
				{
					start = new Point(i, j);
					graph[ i ][ j ] = 0;
				}
			}
		}
		cur_size = 2;
	}

	// 먹이 찾기
	static boolean doBFS( )
	{
		PriorityQueue< Point > pq = new PriorityQueue<>( );
		boolean[ ][ ] visited = new boolean[ N ][ N ];
		visited[ start.row ][ start.col ] = true;
		pq.offer(start);
		int[ ] v_r =
		{ 0, 0, 1, -1 };
		int[ ] v_c =
		{ -1, 1, 0, 0 };

		while (pq.isEmpty( ) == false)
		{
			Point cur = pq.poll( );

			// 먹을수 있는 먹이 존재
			if (graph[ cur.row ][ cur.col ] < cur_size && graph[ cur.row ][ cur.col ] != 0)
			{
				num++;
				if (num == cur_size)
				{
					cur_size++;
					num = 0;
				}
				graph[ cur.row ][ cur.col ] = 0;
				start = cur;
				ans += cur.dist;
				start.dist = 0;
				//System.out.println(cur.dist + " " + start.dist);
				return true;
			}

			for (int i = 0; i < 4; i++)
			{
				Point next = new Point(cur.row + v_r[ i ], cur.col + v_c[ i ]);
				if (check(next, visited))
				{
					// 갈수 없는 곳(나보다 먹이가 더 큰곳)을 고려 해야하기 때문에 포인트마다 시작점으로부터의
					// 거리를 누적시키면서 다음 포인트로 이동할때 마다 +1을 해준다.
					// start지점에서부터 그냥 row칸, col칸 더해서 거리를 구하면 갈수 없는 지점 고려 안됨(틀린 이유였음)
					next.dist = cur.dist + 1;
					pq.offer(next);
				}
			}
		}

		return false;
	}

	static boolean check(Point _p, boolean[ ][ ] v)
	{
		if (_p.row < 0 || _p.row >= N) return false;
		if (_p.col < 0 || _p.col >= N) return false;
		if (graph[ _p.row ][ _p.col ] > cur_size) return false;
		if (v[ _p.row ][ _p.col ] == true) return false;
		v[ _p.row ][ _p.col ] = true;
		return true;
	}

	static void showGraph( )
	{
		StringBuilder sb = new StringBuilder( );
		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < N; j++)
			{
				sb.append(graph[ i ][ j ] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
