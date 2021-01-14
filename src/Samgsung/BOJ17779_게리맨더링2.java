
package Samgsung;

import java.io.*;
import java.util.*;

//BOJ17779_게리맨더링2
public class BOJ17779_게리맨더링2
{
	static int v_r[] =
	{ 1, -1, 0, 0 };
	static int v_c[] =
	{ 0, 0, 1, -1 };

	static int N;
	static int graph[][];

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		System.out.println(solve( ));
	}

	static int solve( )
	{
		int ans = Integer.MAX_VALUE;
		for (int x = 1; x < N; x++)
		{
			for (int y = 1; y < N; y++)
			{
				for (int d = 1; d <= N - x; d++)
				{
					for (int d1 = 1; d1 < d; d1++)
					{
						int d2 = d - d1;
						if (1 <= y - d1 && y - d1 < y && y < y + d2 && y + d2 <= N)
						{
							int map[][] = border(x, y, d1, d2);
							ans = Math.min(ans, count(map));
						}
					}
				}
			}
		}
		return ans;
	}

	static int[ ][ ] border(int r, int c, int d1, int d2)
	{
		// 5구역 경계선 세우기
		int[ ][ ] ret = new int[ N + 1 ][ N + 1 ];
		for (int i = 0; i <= d1; i++)
		{
			if (checkBorder(r + i, c - i)) ret[ r + i ][ c - i ] = 5;
		}
		for (int i = 0; i <= d2; i++)
		{
			if (checkBorder(r + i, c + i)) ret[ r + i ][ c + i ] = 5;
		}
		for (int i = 0; i <= d2; i++)
		{
			if (checkBorder(r + d1 + i, c - d1 + i)) ret[ r + d1 + i ][ c - d1 + i ] = 5;
		}
		for (int i = 0; i <= d1; i++)
		{
			if (checkBorder(r + d2 + i, c + d2 - i)) ret[ r + d2 + i ][ c + d2 - i ] = 5;
		}

		// 5구역 경계값 안에 있는 구역들을 5로 채우기
		section5(ret, r, c, d1, d2);

		// 나머지 1, 2, 3, 4 구역들을 조건에 맞게 채우기
		for (int i = 1; i <= N; i++)
		{
			for (int j = 1; j <= N; j++)
			{
				if (ret[ i ][ j ] == 5) continue;
				if (i < r + d1 && j <= c)
				{
					ret[ i ][ j ] = 1;
					continue;
				}
				else if (i <= r + d2 && j > c)
				{
					ret[ i ][ j ] = 2;
					continue;
				}
				else if (i >= r + d1 && j < c - d1 + d2)
				{
					ret[ i ][ j ] = 3;
					continue;
				}
				else if (i > r + d2 && j >= c - d1 + d2)
				{
					ret[ i ][ j ] = 4;
					continue;
				}
				else ret[ i ][ j ] = 5;
			}
		}

		return ret;
	}

	static void section5(int[ ][ ] map, int r, int c, int d1, int d2)
	{
		for (int i = r + 1; i < r + d1 + d2; i++)
		{
			if (i > N) continue;
			int five = 0;
			for (int j = 1; j <= N; j++)
			{
				if (five == 2) break;
				if (five == 0)
				{
					if (map[ i ][ j ] == 5)
					{
						five++;
						continue;
					}
				}
				else if (five == 1)
				{
					if (map[ i ][ j ] == 0) map[ i ][ j ] = 5;
					else if (map[ i ][ j ] == 5) five++;
				}

			}
		}
	}

	static int count(int[ ][ ] map)
	{
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		int list[] = new int[ 6 ];
		for (int i = 1; i <= N; i++)
		{
			for (int j = 1; j <= N; j++)
			{
				list[ map[ i ][ j ] ] += graph[ i ][ j ];
			}
		}
		for (int i = 1; i <= 5; i++)
		{
			max = Math.max(max, list[ i ]);
			min = Math.min(min, list[ i ]);
		}
		return max - min;
	}

	static boolean checkBorder(int r, int c)
	{
		if (r <= 0 || r > N || c <= 0 || c > N) return false;

		return true;
	}

	static void show(int[ ][ ] temp)
	{
		StringBuilder sb = new StringBuilder( );
		for (int i = 1; i <= N; i++)
		{
			for (int j = 1; j <= N; j++)
			{
				sb.append(temp[ i ][ j ] + " ");
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
		graph = new int[ N + 1 ][ N + 1 ];

		for (int i = 1; i <= N; i++)
		{
			st = new StringTokenizer(br.readLine( ));
			for (int j = 1; j <= N; j++)
			{
				graph[ i ][ j ] = Integer.parseInt(st.nextToken( ));

			}
		}
	}
}
