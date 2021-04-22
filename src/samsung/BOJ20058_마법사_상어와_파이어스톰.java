package samsung;

import java.io.*;
import java.util.*;

// BOJ20058_마법사_상어와_파이어스톰
public class BOJ20058_마법사_상어와_파이어스톰
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

	static int N, Q, SZ, graph[][], list[];
	static int v_r[] =
	{ 1, -1, 0, 0 };
	static int v_c[] =
	{ 0, 0, 1, -1 };

	static int max, cnt = 0, ret_max, ret_cnt;

	static boolean visited[][];

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		solve( );
		System.out.println(countIce( ));
		for (int i = 0; i < SZ; i++)
		{
			for (int j = 0; j < SZ; j++)
			{
				if (graph[ i ][ j ] > 0 && visited[ i ][ j ] == false)
				{
					doDFS(i, j);
					if (cnt > ret_cnt)
					{
						ret_cnt = cnt;
					}
				}
				cnt = 0;
			}
		}
		System.out.println(ret_cnt);
	}

	static void solve( )
	{
		for (int q = 0; q < Q; q++)
		{
			int l = (int) Math.pow(2, list[ q ]);
			for (int i = 0; i < SZ; i += l)
			{
				for (int j = 0; j < SZ; j += l)
				{
					rotate(i, j, l);
				}
			}
			removeIce( );
		}
	}
	
	// 얼음 회전
	static void rotate(int r, int c, int len)
	{
		int ret[][] = new int[ len ][ len ];

		for (int i = 0; i < len; i++)
		{
			for (int j = 0; j < len; j++)
			{
				int row = len - 1 - j + r;
				int col = i + c;
				//System.out.println(i+" "+j+"  "+row+" "+col);
				ret[ i ][ j ] = graph[ row ][ col ];
			}
		}
		
		for (int i = 0; i < len; i++)
		{
			for (int j = 0; j < len; j++)
			{
				graph[ r + i ][ c + j ] = ret[ i ][ j ];
			}
		}
	}

	static void removeIce( )
	{
		Queue< Point > que = new LinkedList<>( );

		for (int i = 0; i < SZ; i++)
		{
			for (int j = 0; j < SZ; j++)
			{
				if (graph[ i ][ j ] == 0) continue;
				int cnt = 0;
				for (int d = 0; d < 4; d++)
				{
					int row = i + v_r[ d ];
					int col = j + v_c[ d ];
					if (check(row, col))
					{
						cnt++;
					}
				}
				if (cnt < 3)
				{
					que.add(new Point(i, j));
				}
			}
		}

		while (que.isEmpty( ) == false)
		{
			Point cur = que.poll( );
			int row = cur.row;
			int col = cur.col;
			graph[ row ][ col ]--;
			if (graph[ row ][ col ] == -1) graph[ row ][ col ] = 0;
		}
	}

	static boolean check(int r, int c)
	{
		if (r < 0 || r >= SZ || c < 0 || c >= SZ) return false;
		if (graph[ r ][ c ] == 0) return false;

		return true;
	}

	static int countIce( )
	{
		int ret = 0;
		for (int i = 0; i < SZ; i++)
		{
			for (int j = 0; j < SZ; j++)
			{
				ret += graph[ i ][ j ];
			}
		}
		return ret;
	}

	static void doDFS(int r, int c)
	{
		visited[ r ][ c ] = true;
		cnt++;

		for (int i = 0; i < 4; i++)
		{
			int row = r + v_r[ i ];
			int col = c + v_c[ i ];
			if (check(row, col) && visited[ row ][ col ] == false)
			{
				visited[ row ][ col ] = true;
				doDFS(row, col);
			}
		}
	}

	static void show( )
	{
		for (int i = 0; i < SZ; i++)
		{
			for (int j = 0; j < SZ; j++)
			{
				System.out.printf("%3d ", graph[ i ][ j ]);
			}
			System.out.println( );
		}
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		N = Integer.parseInt(st.nextToken( ));
		Q = Integer.parseInt(st.nextToken( ));
		SZ = (int) Math.pow(2, N);
		graph = new int[ SZ ][ SZ ];
		list = new int[ Q ];
		visited = new boolean[ SZ ][ SZ ];

		for (int i = 0; i < SZ; i++)
		{
			st = new StringTokenizer(br.readLine( ));
			for (int j = 0; j < SZ; j++)
			{
				graph[ i ][ j ] = Integer.parseInt(st.nextToken( ));
			}
		}

		st = new StringTokenizer(br.readLine( ));
		for (int i = 0; i < Q; i++)
		{
			list[ i ] = Integer.parseInt(st.nextToken( ));
		}
	}
}
