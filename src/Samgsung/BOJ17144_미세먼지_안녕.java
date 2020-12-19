
package Samgsung;

import java.io.*;
import java.util.*;

// BOJ17144_미세먼지_안녕
public class BOJ17144_미세먼지_안녕
{

	static class Point
	{
		int row, col;

		public Point(int _r, int _c)
		{
			this.row = _r;
			this.col = _c;
		}
	}

	static int R, C, T;

	static int[ ] v_r =
	{ 1, -1, 0, 0 };
	static int[ ] v_c =
	{ 0, 0, 1, -1 };

	static int[ ][ ] graph;
	static Point cleaner_top, cleaner_bottom;

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		for (int i = 0; i < T; i++)
		{
			moveDust( );
			cleanDust( );
		}
		show( );
	}

	static void moveDust( )
	{
		int[ ][ ] copy = new int[ R ][ C ];
		for (int i = 0; i < R; i++)
		{
			for (int j = 0; j < C; j++)
			{
				if (graph[ i ][ j ] > 0)
				{
					int check_cnt = 0;
					for (int v = 0; v < 4; v++)
					{
						if (check(i + v_r[ v ], j + v_c[ v ]))
						{
							copy[ i + v_r[ v ] ][ j + v_c[ v ] ] += graph[ i ][ j ] / 5;
							check_cnt++;
						}
					}
					int temp = graph[ i ][ j ] / 5;
					graph[ i ][ j ] = graph[ i ][ j ] - temp * check_cnt;
				}
			}
		}

		for (int i = 0; i < R; i++)
		{
			for (int j = 0; j < C; j++)
			{
				graph[ i ][ j ] += copy[ i ][ j ];
			}
		}
	}

	static boolean check(int r, int c)
	{
		if (r < 0 || r >= R || c < 0 || c >= C) return false;
		if (graph[ r ][ c ] == -1) return false;

		return true;
	}

	static void cleanDust( )
	{
		graph[ cleaner_top.row - 1 ][ cleaner_top.col ] = 0;
		graph[ cleaner_bottom.row + 1 ][ cleaner_bottom.col ] = 0;

		int t_row = cleaner_top.row - 2;
		int t_col = 0;
		int b_row = cleaner_bottom.row + 2;
		int b_col = 0;

		// 좌측
		for (int r = cleaner_top.row - 2; r >= 0; r--)
		{
			graph[ r + 1 ][ 0 ] = graph[ r ][ 0 ];
		}
		for (int r = cleaner_bottom.row + 1; r < R - 1; r++)
		{
			graph[ r ][ 0 ] = graph[ r + 1 ][ 0 ];
		}

		for (int c = 0; c < C - 1; c++)
		{
			graph[ 0 ][ c ] = graph[ 0 ][ c + 1 ];
			graph[ R - 1 ][ c ] = graph[ R - 1 ][ c + 1 ];
		}

		// 우측
		for (int r = 0; r < cleaner_top.row; r++)
		{
			graph[ r ][ C - 1 ] = graph[ r + 1 ][ C - 1 ];
		}
		for (int r = R - 1; r > cleaner_bottom.row; r--)
		{
			graph[ r ][ C - 1 ] = graph[ r - 1 ][ C - 1 ];
		}

		for (int c = C - 1; c > cleaner_top.col + 1; c--)
		{
			graph[ cleaner_top.row ][ c ] = graph[ cleaner_top.row ][ c - 1 ];
			graph[ cleaner_bottom.row ][ c ] = graph[ cleaner_bottom.row ][ c - 1 ];
		}
		graph[ cleaner_top.row ][ 1 ] = 0;
		graph[ cleaner_bottom.row ][ 1 ] = 0;

	}

	static void show( )
	{
		int cnt = 0;
		//StringBuilder sb = new StringBuilder( );
		for (int i = 0; i < R; i++)
		{
			for (int j = 0; j < C; j++)
			{
				//sb.append(graph[ i ][ j ] + "   ");
				if (graph[ i ][ j ] > 0)
				{
					cnt += graph[ i ][ j ];
				}
			}
			//sb.append("\n");
		}
		//System.out.println(sb);
		System.out.println(cnt);
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		R = Integer.parseInt(st.nextToken( ));
		C = Integer.parseInt(st.nextToken( ));
		T = Integer.parseInt(st.nextToken( ));
		graph = new int[ R ][ C ];
		for (int i = 0; i < R; i++)
		{
			st = new StringTokenizer(br.readLine( ));
			for (int j = 0; j < C; j++)
			{
				graph[ i ][ j ] = Integer.parseInt(st.nextToken( ));
				if (graph[ i ][ j ] == -1)
				{
					if (cleaner_top == null)
					{
						cleaner_top = new Point(i, j);
					}
					else
					{
						cleaner_bottom = new Point(i, j);
					}
				}
			}
		}
	}
}
