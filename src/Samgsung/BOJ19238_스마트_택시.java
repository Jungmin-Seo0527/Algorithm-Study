
package Samgsung;

import java.io.*;
import java.util.*;

// BOJ19238_스마트_택시
public class BOJ19238_스마트_택시
{
	static class Point implements Comparable< Point >
	{
		int row, col, cnt = 0;

		public Point(int row, int col)
		{
			this.row = row;
			this.col = col;
		}

		@Override
		public int compareTo(BOJ19238_스마트_택시.Point o)
		{
			// TODO Auto-generated method stub
			if (this.row > o.row)
			{
				return 1;
			}
			else if (this.row == o.row)
			{
				if (this.col > o.col)
				{
					return 1;
				}
			}
			return -1;
		}
	}

	static class Customer
	{
		Point start, dest;

		public Customer(Point start, Point dest)
		{
			this.start = start;
			this.dest = dest;
		}
	}

	static int N, M, C, graph[][];

	static int v_r[] =
	{ 1, -1, 0, 0 };
	static int v_c[] =
	{ 0, 0, 1, -1 };

	static Customer customers[];
	static Point texi;

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		solve( );
	}

	static void solve( )
	{
		for (int i = 0; i < M; i++)
		{
			Point cus = findCustomer( );
			if (texi.cnt < 0)
			{
				System.out.println("-1");
				return;
			}

			goDest(cus);
			if (texi.cnt < 0)
			{
				System.out.println("-1");
				return;
			}
		}
		System.out.println(texi.cnt);
	}

	static void goDest(Point customer)
	{
		boolean visited[][] = new boolean[ N + 1 ][ N + 1 ];
		int cus_num = customer.cnt;
		Point dest = new Point(customers[ cus_num ].dest.row, customers[ cus_num ].dest.col);
		Queue< Point > que = new LinkedList<>( );
		customer.cnt = 0;
		que.add(customer);
		visited[ customer.row ][ customer.col ] = true;

		while (que.isEmpty( ) == false)
		{
			Point cur = que.poll( );
			if (cur.cnt > texi.cnt)
			{
				continue;
			}
			if (cur.row == dest.row && cur.col == dest.col)
			{
				// 연료 확인
				texi.cnt -= cur.cnt;
				if (texi.cnt < 0) break;
				texi.cnt += cur.cnt * 2;
				texi.row = cur.row;
				texi.col = cur.col;
				return;
			}
			for (int i = 0; i < 4; i++)
			{
				int next_row = cur.row + v_r[ i ];
				int next_col = cur.col + v_c[ i ];
				if (check(next_row, next_col))
				{
					if (visited[ next_row ][ next_col ] == true) continue;
					visited[ next_row ][ next_col ] = true;
					Point next = new Point(next_row, next_col);
					next.cnt = cur.cnt + 1;
					que.add(next);
				}
			}
		}
		texi.cnt = -1;
	}

	// 같은 거리에 있을때 row값이 더적은 손님, row값도 같을때 col값이 더 적은 손님이 우선 -> pq
	static Point findCustomer( )
	{
		boolean visited[][] = new boolean[ N + 1 ][ N + 1 ];
		Queue< Point > que = new LinkedList<>( );
		PriorityQueue< Point > pq = new PriorityQueue<>( );
		Point start = new Point(texi.row, texi.col);
		que.add(start);
		visited[ start.row ][ start.col ] = true;
		int same_dest = -1;

		while (que.isEmpty( ) == false)
		{
			Point cur = que.poll( );
			if (cur.cnt > texi.cnt) continue;
			if (same_dest != -1 && cur.cnt > same_dest) continue;
			if (graph[ cur.row ][ cur.col ] > 0 && same_dest == -1) //  find customer first
			{
				same_dest = cur.cnt;
				pq.add(cur);
				continue;
			}
			if (graph[ cur.row ][ cur.col ] > 0 && same_dest != -1 && cur.cnt == same_dest) // find customer2
			{
				pq.add(cur);
				continue;
			}

			for (int i = 0; i < 4; i++)
			{
				int next_row = cur.row + v_r[ i ];
				int next_col = cur.col + v_c[ i ];
				if (check(next_row, next_col))
				{
					if (visited[ next_row ][ next_col ] == true) continue;
					visited[ next_row ][ next_col ] = true;
					Point next = new Point(next_row, next_col);
					next.cnt = cur.cnt + 1;
					que.add(next);
				}
			}
		}
		if (pq.isEmpty( )) // 손님에게 갈수 없음
		{
			texi.cnt = -1;
			return new Point(-1, -1);
		}
		Point ret = pq.poll( );
		ret.cnt = graph[ ret.row ][ ret.col ];
		graph[ ret.row ][ ret.col ] = 0; // 그래프 갱신(손님 현황)

		// 택시 위치와 연료 갱신
		texi.row = ret.row;
		texi.col = ret.col;
		texi.cnt -= same_dest;
		return ret;
	}

	static boolean check(int r, int c)
	{
		if (r < 1 || r > N || c < 1 || c > N) return false;
		if (graph[ r ][ c ] == -1) return false;
		return true;
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		N = Integer.parseInt(st.nextToken( ));
		M = Integer.parseInt(st.nextToken( ));
		C = Integer.parseInt(st.nextToken( ));
		graph = new int[ N + 1 ][ N + 1 ];
		customers = new Customer[ M + 1 ];
		customers[ 0 ] = new Customer(new Point(-1, -1), new Point(-1, -1));

		for (int i = 1; i <= N; i++)
		{
			st = new StringTokenizer(br.readLine( ));
			for (int j = 1; j <= N; j++)
			{
				int temp = Integer.parseInt(st.nextToken( ));
				if (temp == 1) temp = -1;
				graph[ i ][ j ] = temp;
			}
		}

		st = new StringTokenizer(br.readLine( ));
		int row = Integer.parseInt(st.nextToken( ));
		int col = Integer.parseInt(st.nextToken( ));
		texi = new Point(row, col);
		texi.cnt = C;

		for (int i = 1; i <= M; i++)
		{
			st = new StringTokenizer(br.readLine( ));
			int sr = Integer.parseInt(st.nextToken( ));
			int sc = Integer.parseInt(st.nextToken( ));
			int dr = Integer.parseInt(st.nextToken( ));
			int dc = Integer.parseInt(st.nextToken( ));
			customers[ i ] = new Customer(new Point(sr, sc), new Point(dr, dc));
			graph[ sr ][ sc ] = i;
		}
	}
}
