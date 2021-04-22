package samsung;

import java.io.*;
import java.util.*;

// BOJ19236_청소년_상어
// 배열 복사는 노가다 하자
public class BOJ19236_청소년_상어_1
{
	static class FishInfo
	{
		int row, col, vector;
		boolean eaten = false;

		FishInfo(int r, int c, int v)
		{
			this.row = r;
			this.col = c;
			this.vector = v;
		}
	}

	static final int SZ = 4;
	static int[ ][ ] graph = new int[ SZ ][ SZ ];
	static FishInfo[ ] fish = new FishInfo[ SZ * SZ + 1 ];

	static int[ ] v_r =
	{ 0, -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[ ] v_c =
	{ 0, 0, -1, -1, -1, 0, 1, 1, 1 };

	static int ans, eat;

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
				int fish_num = Integer.parseInt(st.nextToken( ));
				int fish_vec = Integer.parseInt(st.nextToken( ));
				graph[ i ][ j ] = fish_num;
				fish[ fish_num ] = new FishInfo(i, j, fish_vec);

			}
		}
		eat = graph[ 0 ][ 0 ];

		fish[ 0 ] = new FishInfo(0, 0, fish[ graph[ 0 ][ 0 ] ].vector);
	}

	// 불가능한 벡터는 반시계 방향으로 45도 회전을 하는데
	// 바뀐 벡터값을 유지해서 가지고 가야함
	static void moveFish( )
	{
		for (int i = 1; i <= 16; i++)
		{
			if (fish[ i ].eaten) continue;
			int f_r = fish[ i ].row;
			int f_c = fish[ i ].col;
			int f_v = fish[ i ].vector;
			int temp_v = f_v;

			while (true)
			{
				if (checkMoveFish(f_r, f_c, f_v))
				{
					swap(f_r, f_c, f_v);
					break;
				}
				else
				{
					f_v++;
					if (f_v == 9) f_v = 1;
				}
				if (f_v == temp_v) break;
			}
		}
	}

	static boolean checkMoveFish(int _r, int _c, int _v)
	{
		int r = _r + v_r[ _v ];
		int c = _c + v_c[ _v ];
		if (r < 0 || r >= SZ) return false;
		if (c < 0 || c >= SZ) return false;
		if (fish[ graph[ _r ][ _c ] ].eaten) return false;
		if (r == fish[ 0 ].row && c == fish[ 0 ].col) return false; // 상어 존재
		if (_r == fish[ 0 ].row && _c == fish[ 0 ].col) return false;
		return true;

	}

	static void swap(int _r, int _c, int _v)
	{
		int r = _r + v_r[ _v ];
		int c = _c + v_c[ _v ];
		int temp_fish_num = graph[ r ][ c ];
		graph[ r ][ c ] = graph[ _r ][ _c ];
		graph[ _r ][ _c ] = temp_fish_num;

		int n1 = graph[ r ][ c ];
		int n2 = graph[ _r ][ _c ];
		int temp_row = fish[ n1 ].row;
		int temp_col = fish[ n1 ].col;

		fish[ n1 ].row = fish[ n2 ].row;
		fish[ n1 ].col = fish[ n2 ].col;
		fish[ n1 ].vector = _v;

		fish[ n2 ].row = temp_row;
		fish[ n2 ].col = temp_col;
	}

	// backtracking
	// 이전 상태를 저장후 재귀 함수 이후 다시 graph와 fish를 reset
	static void doDFS(int e)
	{
		eatFish( );
		if (ans < e) ans = e;
		moveFish( );
		//		System.out.println(fish[ 0 ].row + " " + fish[ 0 ].col + " " + e+" "+fish[0].vector);
		//		show( );
		//		show2( );
		//		System.out.println("-----------------");

		int[ ][ ] clone_graph = new int[ SZ ][ SZ ];
		FishInfo[ ] clone_fish = new FishInfo[ SZ * SZ + 1 ];
		for (int i = 0; i < 17; i++)
		{
			clone_fish[ i ] = new FishInfo(0, 0, 0);
		}
		copyState(clone_graph, clone_fish);

		int n = 1;
		while (true)
		{
			if (moveShark(n))
			{
				doDFS(e + graph[ fish[ 0 ].row ][ fish[ 0 ].col ]);
				resetState(clone_graph, clone_fish);
				//				show( );
				//				show2( );
			}
			if (++n == 4) break;
		}
	}

	// n번만큼 상어를 움직인다.
	static boolean moveShark(int n)
	{
		int r = fish[ 0 ].row;
		int c = fish[ 0 ].col;
		int v = fish[ 0 ].vector;
		return checkMoveShark(r, c, v, n);
	}

	static boolean checkMoveShark(int _r, int _c, int _v, int _n)
	{
		int r = _r + v_r[ _v ] * _n;
		int c = _c + v_c[ _v ] * _n;
		if (r < 0 || r >= SZ) return false;
		if (c < 0 || c >= SZ) return false;
		if (fish[ graph[ r ][ c ] ].eaten) return false;
		if (r == _r && c == _c) return false;

		fish[ 0 ].row = r;
		fish[ 0 ].col = c;
		fish[ 0 ].vector = fish[ graph[ r ][ c ] ].vector;

		return true;
	}

	static void eatFish( )
	{
		fish[ graph[ fish[ 0 ].row ][ fish[ 0 ].col ] ].eaten = true;
	}

	static void copyState(int copy_graph[][], FishInfo copy_fish[])
	{
		for (int i = 0; i < SZ; i++)
		{
			for (int j = 0; j < SZ; j++)
			{
				copy_graph[ i ][ j ] = graph[ i ][ j ];
			}
		}
		for (int i = 0; i < 17; i++)
		{
			copy_fish[ i ].row = fish[ i ].row;
			copy_fish[ i ].col = fish[ i ].col;
			copy_fish[ i ].vector = fish[ i ].vector;
			copy_fish[ i ].eaten = fish[ i ].eaten;
		}
	}

	static void resetState(int[ ][ ] copy_graph, FishInfo[ ] copy_fish)
	{
		for (int i = 0; i < SZ; i++)
		{
			for (int j = 0; j < SZ; j++)
			{
				graph[ i ][ j ] = copy_graph[ i ][ j ];
				fish[ i * SZ + j + 1 ].row = copy_fish[ i * SZ + j + 1 ].row;
				fish[ i * SZ + j + 1 ].col = copy_fish[ i * SZ + j + 1 ].col;
				fish[ i * SZ + j + 1 ].vector = copy_fish[ i * SZ + j + 1 ].vector;
				fish[ i * SZ + j + 1 ].eaten = copy_fish[ i * SZ + j + 1 ].eaten;
			}
		}
		fish[ 0 ].row = copy_fish[ 0 ].row;
		fish[ 0 ].col = copy_fish[ 0 ].col;
		fish[ 0 ].vector = copy_fish[ 0 ].vector;
		fish[ 0 ].eaten = copy_fish[ 0 ].eaten;

	}

	static void show( )
	{
		StringBuilder sb = new StringBuilder( );
		for (int i = 0; i < SZ; i++)
		{
			for (int j = 0; j < SZ; j++)
			{
				sb.append(graph[ i ][ j ] + "    ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	static void show2( )
	{
		StringBuilder sb = new StringBuilder( );
		for (int i = 0; i <= 16; i++)
		{
			sb.append(fish[ i ].vector + " ");
		}
		System.out.println(sb);
	}
}
