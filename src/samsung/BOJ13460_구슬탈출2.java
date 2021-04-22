
package samsung;

import java.io.*;
import java.util.*;

// BOJ13460_구슬탈출2
public class BOJ13460_구슬탈출2
{
	static class Point
	{
		int row;
		int col;

		public Point(int row, int col)
		{
			this.row = row;
			this.col = col;
		}
	}

	static class State
	{
		Point red, blue;
		int cnt = 0;

		public State(Point r, Point b)
		{
			this.red = r;
			this.blue = b;
		}
	}

	static int N, M;
	static char graph[][];
	static Point hole;

	// 오 아 왼 위
	static int v_r[] =
	{ 0, 1, 0, -1 };
	static int v_c[] =
	{ 1, 0, -1, 0 };

	static Queue< State > que = new LinkedList<>( );

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		solve( );
	}

	static void solve( )
	{
		int ans = -1;
		while (que.isEmpty( ) == false)
		{
			State cur = que.poll( );
			if (cur.cnt == 10) continue;
			for (int i = 0; i < 4; i++)
			{
				State next = moveBall(cur, i);
				if (next.red.row == cur.red.row && next.red.col == cur.red.col
				                && next.blue.row == cur.blue.row && next.blue.col == cur.blue.col)
				        continue; // 빨간공 파란공 모두 움직임이 없음 -> 버림

				next.cnt = cur.cnt + 1;

				// 파란공 골인(빨간공이 골인해도 무조건 무효임)
				if (next.blue.row == hole.row && next.blue.col == hole.col)
				{
					continue;
				}

				// 빨간공 골인 (여기까지 왔으면 파란공은 골인 아님)
				if (next.red.row == hole.row && next.red.col == hole.col)
				{
					System.out.println(next.cnt);
					return;
				}
				que.add(next);
			}
		}
		System.out.println(ans);
	}

	static State moveBall(State cur, int dir)
	{
		Point first = null;
		Point second = null;
		boolean red = false;

		// 방향에 따라 어떤 공을 먼저 움직일지 결정
		switch (dir)
		{
		case 0 : // right
			if (cur.red.col > cur.blue.col)
			{
				first = new Point(cur.red.row, cur.red.col);
				second = new Point(cur.blue.row, cur.blue.col);
				red = true;
			}
			else
			{
				first = new Point(cur.blue.row, cur.blue.col);
				second = new Point(cur.red.row, cur.red.col);
			}
			break;

		case 1 : // down
			if (cur.red.row > cur.blue.row)
			{
				first = new Point(cur.red.row, cur.red.col);
				second = new Point(cur.blue.row, cur.blue.col);
				red = true;
			}
			else
			{
				first = new Point(cur.blue.row, cur.blue.col);
				second = new Point(cur.red.row, cur.red.col);
			}
			break;

		case 2 : // left
			if (cur.red.col < cur.blue.col)
			{
				first = new Point(cur.red.row, cur.red.col);
				second = new Point(cur.blue.row, cur.blue.col);
				red = true;
			}
			else
			{
				first = new Point(cur.blue.row, cur.blue.col);
				second = new Point(cur.red.row, cur.red.col);
			}
			break;

		case 3 : // up
			if (cur.red.row < cur.blue.row)
			{
				first = new Point(cur.red.row, cur.red.col);
				second = new Point(cur.blue.row, cur.blue.col);
				red = true;
			}
			else
			{
				first = new Point(cur.blue.row, cur.blue.col);
				second = new Point(cur.red.row, cur.red.col);
			}
			break;

		default :
			break;
		}

		boolean f = false; // true -> first ball 이동x
		boolean s = false; // true -> second ball 이동x
		while (f == false || s == false)
		{
			int next_row = first.row + v_r[ dir ];
			int next_col = first.col + v_c[ dir ];
			if (f == false) // 먼저 움직이는 공이 아직 움직일 수 있음
			{
				if (graph[ next_row ][ next_col ] == '#')
				{
					f = true;
				}
				else if (graph[ next_row ][ next_col ] == 'O')
				{
					first.row = next_row;
					first.col = next_col;
					f = true;
				}
				else
				{
					first.row = next_row;
					first.col = next_col;
				}
			}

			// 다음 움직이는 공
			if (s == false)
			{
				next_row = second.row + v_r[ dir ];
				next_col = second.col + v_c[ dir ];

				if (graph[ next_row ][ next_col ] == '#')
				{
					s = true;
				}
				else if (graph[ next_row ][ next_col ] == 'O')
				{
					second.row = next_row;
					second.col = next_col;

					s = true;
					if (f) break;
				}

				// 공끼리 박음
				else if (next_row == first.row && next_col == first.col)
				{
					s = true;
				}
				else
				{
					second.row = next_row;
					second.col = next_col;
				}
			}
		}
		if (red) //  first=red;
		{
			return new State(first, second);
		}
		else // first = blue
		{
			return new State(second, first);
		}

	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		N = Integer.parseInt(st.nextToken( ));
		M = Integer.parseInt(st.nextToken( ));
		graph = new char[ N ][ M ];
		Point red = null;
		Point blue = null;
		for (int i = 0; i < N; i++)
		{
			String input = br.readLine( );
			for (int j = 0; j < M; j++)
			{
				char temp = input.charAt(j);
				if (temp == 'R')
				{
					red = new Point(i, j);
				}
				else if (temp == 'B')
				{
					blue = new Point(i, j);
				}
				else if (temp == 'O')
				{
					graph[ i ][ j ] = temp;
					hole = new Point(i, j);
				}
				else
				{
					graph[ i ][ j ] = temp;
				}
			}
		}
		que.add(new State(red, blue));
	}
}
