package samsung;

import java.io.*;
import java.util.*;

// BOJ19236_청소년_상어_2
public class BOJ19236_청소년_상어_2
{
	static class FishInfo
	{
		int row, col, vector;
		boolean eaten = false;

		public FishInfo(int _r, int _c, int _v)
		{
			this.row = _r;
			this.col = _c;
			this.vector = _v;
		}
	}

	static final int SZ = 4;
	static int[ ][ ] graph = new int[ SZ ][ SZ ];
	static FishInfo[ ] fish = new FishInfo[ SZ * SZ + 1 ];

	static int[ ] v_r =
	{ 0, -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[ ] v_c =
	{ 0, 0, -1, -1, -1, 0, 1, 1, 1 };

	static int ans;

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		doDFS(graph[ fish[ 0 ].row ][ fish[ 0 ].col ]);
		System.out.println(ans);
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for (int i = 0; i < SZ; i++)
		{
			st = new StringTokenizer(br.readLine( ));
			for (int j = 0; j < SZ; j++)
			{
				graph[ i ][ j ] = Integer.parseInt(st.nextToken( ));
				fish[ graph[ i ][ j ] ] = new FishInfo(i, j, Integer.parseInt(st.nextToken( )));
			}
		}

		// 처음 상어 위치 
		fish[ 0 ] = new FishInfo(0, 0, fish[ graph[ 0 ][ 0 ] ].vector);
	}

	static void eatFish( )
	{
		int cur_shark_row = fish[ 0 ].row;
		int cur_shark_col = fish[ 0 ].col;
		int eatenFish = graph[ cur_shark_row ][ cur_shark_col ];
		fish[ eatenFish ].eaten = true;
	}

	static void moveFish( )
	{
		for (int i = 1; i <= 16; i++)
		{
			int fish_row = fish[ i ].row;
			int fish_col = fish[ i ].col;
			int fish_vector = fish[ i ].vector;
			int temp_vector = fish_vector;
			while (true)
			{
				if (checkMoveFish(fish_row, fish_col, fish_vector))
				{
					// swap
					swapFish(fish_row, fish_col, fish_vector);
					break;
				}

				fish_vector++;
				if (fish_vector == 9) fish_vector = 1;
				if (fish_vector == temp_vector) break;

			}
		}

	}

	static boolean checkMoveFish(int _r, int _c, int _v)
	{
		int r = _r + v_r[ _v ];
		int c = _c + v_c[ _v ];
		if (r < 0 || r >= SZ || c < 0 || c >= SZ) return false; // 범위 탈락 
		if (_r == fish[ 0 ].row && _c == fish[ 0 ].col) return false; // 내 자신이 상어
		if (r == fish[ 0 ].row && c == fish[ 0 ].col) return false; // 가려고 하는 곳에 상어
		if (fish[ graph[ _r ][ _c ] ].eaten) return false; // 이미 나는 먹힘

		return true;
	}

	// fish -> row, col
	// fish -> vector 갱신
	// graph
	static void swapFish(int _r, int _c, int _v)
	{
		int r = _r + v_r[ _v ];
		int c = _c + v_c[ _v ];
		int temp_fish_num = graph[ r ][ c ];

		graph[ r ][ c ] = graph[ _r ][ _c ];
		graph[ _r ][ _c ] = temp_fish_num;

		int fish_num1 = graph[ _r ][ _c ];
		int fish_num2 = graph[ r ][ c ];

		int temp_row = fish[ fish_num2 ].row;
		int temp_col = fish[ fish_num2 ].col;

		fish[ fish_num2 ].row = fish[ fish_num1 ].row;
		fish[ fish_num2 ].col = fish[ fish_num1 ].col;
		fish[ fish_num2 ].vector = _v;

		fish[ fish_num1 ].row = temp_row;
		fish[ fish_num1 ].col = temp_col;

	}

	static void moveShark(int r, int c)
	{
		fish[ 0 ].row = r;
		fish[ 0 ].col = c;
		fish[ 0 ].vector = fish[ graph[ r ][ c ] ].vector;
	}

	static void doDFS(int e)
	{
		if (ans < e) ans = e;
		eatFish( );
		moveFish( );
		int[ ][ ] copy_graph = new int[ SZ ][ SZ ];
		FishInfo[ ] copy_fish = new FishInfo[ SZ * SZ + 1 ];
		for (int i = 0; i < SZ * SZ + 1; i++)
		{
			copy_fish[ i ] = new FishInfo(0, 0, 0);
		}
		copyState(copy_graph, copy_fish);
		int n = 1;
		while (true)
		{
			if (checkMoveShart(n))
			{
				int row = fish[ 0 ].row + v_r[ fish[ 0 ].vector ] * n;
				int col = fish[ 0 ].col + v_c[ fish[ 0 ].vector ] * n;
				moveShark(row, col);
				doDFS(e + graph[ fish[ 0 ].row ][ fish[ 0 ].col ]);
				resetState(copy_graph, copy_fish);
			}
			if (++n == 4) break;
		}
	}

	static boolean checkMoveShart(int n)
	{
		int r = fish[ 0 ].row + v_r[ fish[ 0 ].vector ] * n;
		int c = fish[ 0 ].col + v_c[ fish[ 0 ].vector ] * n;
		if (r < 0 || r >= SZ || c < 0 || c >= SZ) return false;
		if (fish[ graph[ r ][ c ] ].eaten) return false;

		return true;
	}

	static void copyState(int[ ][ ] c_graph, FishInfo[ ] c_fish)
	{
		for (int i = 0; i < SZ; i++)
		{
			for (int j = 0; j < SZ; j++)
			{
				c_graph[ i ][ j ] = graph[ i ][ j ];
				c_fish[ i * SZ + j + 1 ].row = fish[ i * SZ + j + 1 ].row;
				c_fish[ i * SZ + j + 1 ].col = fish[ i * SZ + j + 1 ].col;
				c_fish[ i * SZ + j + 1 ].vector = fish[ i * SZ + j + 1 ].vector;
				c_fish[ i * SZ + j + 1 ].eaten = fish[ i * SZ + j + 1 ].eaten;
			}
		}
		c_fish[ 0 ].row = fish[ 0 ].row;
		c_fish[ 0 ].col = fish[ 0 ].col;
		c_fish[ 0 ].vector = fish[ 0 ].vector;
		c_fish[ 0 ].eaten = fish[ 0 ].eaten;
	}

	static void resetState(int[ ][ ] c_graph, FishInfo[ ] c_fish)
	{
		for (int i = 0; i < SZ; i++)
		{
			for (int j = 0; j < SZ; j++)
			{
				graph[ i ][ j ] = c_graph[ i ][ j ];
				fish[ i * SZ + j + 1 ].row = c_fish[ i * SZ + j + 1 ].row;
				fish[ i * SZ + j + 1 ].col = c_fish[ i * SZ + j + 1 ].col;
				fish[ i * SZ + j + 1 ].vector = c_fish[ i * SZ + j + 1 ].vector;
				fish[ i * SZ + j + 1 ].eaten = c_fish[ i * SZ + j + 1 ].eaten;
			}
		}
		fish[ 0 ].row = c_fish[ 0 ].row;
		fish[ 0 ].col = c_fish[ 0 ].col;
		fish[ 0 ].vector = c_fish[ 0 ].vector;
		fish[ 0 ].eaten = c_fish[ 0 ].eaten;
	}

	static void show( )
	{
		StringBuilder sb = new StringBuilder( );
		for (int i = 0; i < SZ; i++)
		{
			for (int j = 0; j < SZ; j++)
			{
				sb.append(graph[ i ][ j ] + "   ");
			}
			sb.append("\n");
		}
		for (int i = 0; i <= 16; i++)
		{
			sb.append(i + ": " + fish[ i ].vector + "\n");
		}
		System.out.println(sb);
	}
}