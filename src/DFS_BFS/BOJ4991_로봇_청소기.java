package DFS_BFS;


import java.io.*;
import java.util.*;

// BOJ4991_로봇_청소기
// 지났던 지점을 다시 지날수 있음
// 각 지점간의 최단 거리들을 mst 테이블에 저장 (bfs)
// mst테이블을 이용해 모든 지점들을 지날때의 최단 거리 구하기 (dfs)
public class BOJ4991_로봇_청소기
{
	static class Point
	{
		int row, col;

		public Point(int row, int col)
		{
			this.row = row;
			this.col = col;
		}
	}

	static char graph[][];
	static int dirty_num[][];
	static int rowSZ, colSZ, d, min = Integer.MAX_VALUE;

	static int v_r[] =
	{ 1, -1, 0, 0 };
	static int v_c[] =
	{ 0, 0, 1, -1 };

	static int mst_table[][];
	static boolean mst_visited[];
	static LinkedList< Point > dirtyList = new LinkedList<>( );

	static StringBuilder sb = new StringBuilder( );

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		System.out.println(sb);
	}

	// 각 지점간의 최단 거리를 구하여 mst table 생성
	static void initMST_table( )
	{
		for (int i = 0; i <= d; i++)
		{
			Point cur = dirtyList.get(i);
			doBFS(cur, i);
		}
	}

	// 최단거리 구하기
	static void doBFS(Point start, int n)
	{
		int visited[][] = new int[ rowSZ ][ colSZ ];
		Queue< Point > que = new LinkedList<>( );
		que.add(start);

		visited[ start.row ][ start.col ] = 1;
		graph[ start.row ][ start.col ] = 'x';
		int dirty_cnt = 0;

		while (que.isEmpty( ) == false)
		{
			Point cur = que.poll( );

			if (graph[ cur.row ][ cur.col ] == '*')
			{
				int dn = dirty_num[ cur.row ][ cur.col ];
				int temp = visited[ cur.row ][ cur.col ] - 1;
				//System.out.println(cur.row+" "+cur.col+" "+temp+"ddd");
				mst_table[ n ][ dn ] = visited[ cur.row ][ cur.col ] - 1;
				dirty_cnt++;
			}
			if (dirty_cnt == d + 1)
			{
				return;
			}
			for (int i = 0; i < 4; i++)
			{
				int next_row = cur.row + v_r[ i ];
				int next_col = cur.col + v_c[ i ];
				if (check(next_row, next_col))
				{
					if (next_row == start.row && next_col == start.col) continue;
					if (visited[ next_row ][ next_col ] == 0)
					{
						visited[ next_row ][ next_col ] = visited[ cur.row ][ cur.col ] + 1;
						que.add(new Point(next_row, next_col));
					}
					else if (visited[ next_row ][ next_col ] > visited[ cur.row ][ cur.col ] + 1)
					{
						visited[ next_row ][ next_col ] = visited[ cur.row ][ cur.col ] + 1;
						que.add(new Point(next_row, next_col));
					}
				}
			}
		}
		graph[ start.row ][ start.col ] = '*';
	}

	static boolean check(int r, int c)
	{
		if (r < 0 || r >= rowSZ || c < 0 || c >= colSZ) return false;
		if (graph[ r ][ c ] == 'x') return false;
		return true;
	}

	static void show( )
	{
		StringBuilder ss = new StringBuilder( );
		for (int i = 0; i <= d; i++)
		{
			for (int j = 0; j <= d; j++)
			{
				ss.append(mst_table[ i ][ j ] + " ");
			}
			ss.append("\n");
		}
		System.out.println(ss);
	}

	// mst 테이블을 보면서 모든 지점들을 지날때의 최단거리를 dfs를 이용해서 구하기
	// 단 mst테이블에서 0이 있다는 것은 지점간에 이동이 불가능하다는 의미이므로 -1 출력
	static void doDFS(int dirty_cnt, int total_dist, int cur)
	{
		//System.out.println(min);
		if (min == -1)
		{
			return;
		}
		if (dirty_cnt == d)
		{
			min = Math.min(min, total_dist);
			return;
		}
		for (int i = 1; i <= d; i++)
		{
			if (i == cur) continue;
			if (mst_table[ cur ][ i ] == 0)
			{
				min = -1;
				return;
			}
			if (mst_visited[ i ] == false)
			{
				mst_visited[ i ] = true;
				doDFS(dirty_cnt + 1, total_dist + mst_table[ cur ][ i ], i);
				mst_visited[ i ] = false;
			}

		}
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		while (true)
		{
			st = new StringTokenizer(br.readLine( ));
			colSZ = Integer.parseInt(st.nextToken( ));
			rowSZ = Integer.parseInt(st.nextToken( ));
			d = 0;
			if (rowSZ == 0 && colSZ == 0)
			{
				return;
			}

			graph = new char[ rowSZ ][ colSZ ];
			dirty_num = new int[ rowSZ ][ colSZ ];
			int start_row = 0;
			int start_col = 0;

			for (int i = 0; i < rowSZ; i++)
			{
				String temp = br.readLine( );
				for (int j = 0; j < colSZ; j++)
				{
					graph[ i ][ j ] = temp.charAt(j);
					if (graph[ i ][ j ] == '*')
					{
						dirtyList.addLast(new Point(i, j));
						d++;
						dirty_num[ i ][ j ] = d;
					}
					if (graph[ i ][ j ] == 'o')
					{
						dirtyList.addFirst(new Point(i, j));
						graph[ i ][ j ] = '*';
					}
				}
			}

			mst_table = new int[ d + 1 ][ d + 1 ];
			initMST_table( );
			//show( );
			mst_visited = new boolean[ d + 1 ];
			mst_visited[ 0 ] = true;
			for (int i = 1; i <= d; i++)
			{
				if (mst_table[ 0 ][ i ] == 0)
				{
					min = -1;
					break;
				}
				mst_visited[ i ] = true;
				doDFS(1, mst_table[ 0 ][ i ], i);
				mst_visited[ i ] = false;
			}
			sb.append(min + "\n");
			min = Integer.MAX_VALUE;
			dirtyList.clear( );
		}
	}
}
