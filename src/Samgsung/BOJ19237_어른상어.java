
package Samgsung;

import java.io.*;
import java.util.*;

// BOJ19237_어른상어
public class BOJ19237_어른상어
{
	static class Shark
	{
		public int num = 0, cur_state = 0, row = 0, col = 0;
		public int[ ][ ] priority = new int[ 5 ][ 5 ];

		public Shark(int num, int row, int col)
		{
			this.num = num;
			this.row = row;
			this.col = col;
		}
	}

	static class Point
	{
		public int shark_num = 0;
		public int smell = 0;

		public Point(int shark_num, int smell)
		{
			this.shark_num = shark_num;
			this.smell = smell;
		}
	}

	static Shark[ ] shark_list;
	static Point[ ][ ] graph;

	static int N, M, K;
	static int[ ] v_r =
	{ 0, -1, 1, 0, 0 };
	static int[ ] v_c =
	{ 0, 0, 0, -1, 1 };

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		int ans = -1;

		for (int i = 0; i < 1000; i++)
		{
			moveShark( );
			moveSmell( );
			if (checkSharks( ))
			{
				ans = i + 1;
				break;
			}

		}
		System.out.println(ans);
	}

	static void moveShark( )
	{
		// 상어 이동(이동만 냄새이동은 x)
		for (int shark = 1; shark <= M; shark++)
		{
			Shark cur_shark = shark_list[ shark ];
			if (cur_shark.row == 0 && cur_shark.col == 0) continue;

			// 냄새가 있는곳으로 이동
			boolean change = false;
			for (int i = 1; i <= 4; i++)
			{
				int next_row = cur_shark.row + v_r[ cur_shark.priority[ cur_shark.cur_state ][ i ] ];
				int next_col = cur_shark.col + v_c[ cur_shark.priority[ cur_shark.cur_state ][ i ] ];
				if (checkMove(cur_shark, next_row, next_col))
				{
					//					cur_shark.prev_row = cur_shark.row;
					//					cur_shark.prev_col = cur_shark.col;
					//					cur_shark.prev_state = cur_shark.cur_state;

					cur_shark.row = next_row;
					cur_shark.col = next_col;
					cur_shark.cur_state = cur_shark.priority[ cur_shark.cur_state ][ i ];

					change = true;
					break;
				}
			}

			// 냄새가 없는곳으로 이동이 불가
			// 나의 냄새가 있는 곳으로 다시 이동
			if (change == false)
			{
				for (int i = 1; i <= 4; i++)
				{
					int next_row = cur_shark.row
					                + v_r[ cur_shark.priority[ cur_shark.cur_state ][ i ] ];
					int next_col = cur_shark.col
					                + v_c[ cur_shark.priority[ cur_shark.cur_state ][ i ] ];

					if (checkMove2(cur_shark, next_row, next_col))
					{
						cur_shark.row = next_row;
						cur_shark.col = next_col;
						cur_shark.cur_state = cur_shark.priority[ cur_shark.cur_state ][ i ];
						break;
					}
				}
			}

		}
	}

	// 각 냄새들의 최신화
	// 상어의 현재 위치에 최대 냄새
	static void moveSmell( )
	{
		for (int i = 1; i <= N; i++)
		{
			for (int j = 1; j <= N; j++)
			{
				if (graph[ i ][ j ].smell > 0)
				{
					graph[ i ][ j ].smell--;
					if (graph[ i ][ j ].smell == 0)
					{
						graph[ i ][ j ].shark_num = 0;
					}
				}
			}
		}

		// 상어 위치에 대한 냄새 최신화
		for (int i = 1; i <= M; i++)
		{
			int row = shark_list[ i ].row;
			int col = shark_list[ i ].col;

			// 상어 잡아먹기에 대한 조건(현재 위치에서 가장 적은 숫자의 상어외의 상어는 사망)
			if (graph[ row ][ col ].shark_num != 0 && graph[ row ][ col ].shark_num < shark_list[ i ].num)
			{
				shark_list[ i ].row = 0;
				shark_list[ i ].col = 0;
				shark_list[ i ].num = 0;
				shark_list[ i ].cur_state = 0;
				continue;
			}

			graph[ row ][ col ].shark_num = i;
			graph[ row ][ col ].smell = K;

		}
	}

	// 최종 확인(격자에 상어 1만 있는가)
	static boolean checkSharks( )
	{
		for (int i = 2; i <= M; i++)
		{
			if (shark_list[ i ].num != 0) return false;
		}
		return true;
	}

	// 첫번째 상어 움직임 조건(냄새가 없는 곳으로 이동 가능) 여부 조사
	static boolean checkMove(Shark cur, int next_row, int next_col)
	{
		if (next_row <= 0 || next_row > N || next_col <= 0 || next_col > N) return false;
		if (graph[ next_row ][ next_col ].smell > 0) return false;

		return true;
	}

	// 두번째 상어 움직임 조건(자신의 냄새가 있는 곳으로 이동 가능) 여부 조사
	static boolean checkMove2(Shark cur, int next_row, int next_col)
	{
		if (next_row <= 0 || next_row > N || next_col <= 0 || next_col > N) return false;
		if (graph[ next_row ][ next_col ].smell > 0)
		{
			if (graph[ next_row ][ next_col ].shark_num == cur.num)
			{
				return true;
			}
		}

		return false;
	}

	// 행과 열을 1부터 시작해서 각 자료들이 들어갈 container들을 초기화 할때
	// 0을 초기화 하지 않고 0을 쓰는 경우를 주의!!!!
	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		N = Integer.parseInt(st.nextToken( ));
		M = Integer.parseInt(st.nextToken( ));
		K = Integer.parseInt(st.nextToken( ));
		graph = new Point[ N + 1 ][ N + 1 ];
		shark_list = new Shark[ M + 1 ];

		// 1차원 배열에서 상어가 나타나는 지점에서 새로 할당을 하는데
		// 배열들은 배열의 순서대로 할당하지 않으면 런타임 에러가 나는 듯 하다
		for (int i = 0; i <= M; i++)
		{
			shark_list[ i ] = new Shark(0, 0, 0);
		}

		// 하지만 이차원배열에서는 각각 0행과 0열의 지점을 초기화를 안하고 진행했는데 에러가 없다
		// 공부 요망
		graph[ 0 ][ 0 ] = new Point(0, 0);

		// 현재 격자의 상태 (상어의 유무)
		// 상어가 있으면 상어 리스트에 추가
		for (int i = 1; i <= N; i++)
		{
			st = new StringTokenizer(br.readLine( ));
			for (int j = 1; j <= N; j++)
			{
				int shark = Integer.parseInt(st.nextToken( ));
				graph[ i ][ j ] = new Point(shark, 0);
				if (shark != 0)
				{
					graph[ i ][ j ].smell = K;
					shark_list[ shark ].num = shark;
					shark_list[ shark ].row = i;
					shark_list[ shark ].col = j;
				}

			}
		}

		// 처음 주어지는 상어의 방향
		st = new StringTokenizer(br.readLine( ));
		for (int i = 1; i <= M; i++)
		{
			shark_list[ i ].cur_state = Integer.parseInt(st.nextToken( ));
		}

		// 각 상어들의 각 방향에 대한 우선순위 방향 저장
		for (int i = 1; i <= M; i++)
		{
			for (int j = 1; j <= 4; j++)
			{
				st = new StringTokenizer(br.readLine( ));

				for (int k = 1; k <= 4; k++)
				{
					shark_list[ i ].priority[ j ][ k ] = Integer.parseInt(st.nextToken( ));
				}
			}
		}
	}

	static void show( )
	{
		StringBuilder sb = new StringBuilder( );
		for (int i = 1; i <= N; i++)
		{
			for (int j = 1; j <= N; j++)
			{
				sb.append(graph[ i ][ j ].shark_num + " ");
			}
			sb.append("\n");
		}

		for (int i = 1; i <= M; i++)
		{
			sb.append("shark" + i + " ");
			sb.append(shark_list[ i ].cur_state + " row: " + shark_list[ i ].row + " col: "
			                + shark_list[ i ].col + "\n");
		}

		System.out.println(sb);
	}
}
