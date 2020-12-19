
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
	}

	static void moveDust( )
	{
		int[ ][ ] copy = new int[ R ][ C ];
		for (int i = 0; i < R; i++)
		{
			for (int j = 0; j < C; j++)
			{
				if (graph[ i ][ j ] != 0)
				{
					int check_cnt = 0;
					for (int v = 0; v < 4; v++)
					{
						if (check(i + v_r[ v ], j + v_c[ v ]))
						{
							copy[ i + v_r[ v ] ][ j + v_c[ v ] ] = graph[ i ][ j ] / 5;
						}
					}
					graph[ i ][ j ] = graph[ i ][ j ] - graph[ i ][ j ] / 5 * check_cnt;
				}
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
