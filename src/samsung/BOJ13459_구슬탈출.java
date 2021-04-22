
package samsung;

import java.io.*;
import java.util.*;

// BOJ13459_구슬탈출
public class BOJ13459_구슬탈출
{
	static class Point
	{
		int row, col;
		int cnt = 0;

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

		public State(Point red, Point blue)
		{
			this.red = red;
			this.blue = blue;
		}
	}

	static int N, M;
	static char graph[][];

	static int v_r[] =
	{ 0, 1, 0, -1 };
	static int v_c[] =
	{ 1, 0, -1, 0 };

	static Point red, blue, hole;

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		solve( );
	}

	static void solve( )
	{
		Queue< State > que = new LinkedList<>( );
		State start = new State(new Point(red.row, red.col), new Point(blue.row, blue.col));
		boolean visited[][] = new boolean[ N * M ][ N * M ];
		que.add(start);

		while (que.isEmpty( ) == false)
		{
			State cur = que.poll( );
			if (cur.cnt == 10) continue;
			if (visited[ cur.red.row * M + cur.red.col ][ cur.blue.row * M + cur.blue.col ]) continue;
			visited[ cur.red.row * M + cur.red.col ][ cur.blue.row * M + cur.blue.col ] = true;
			for (int i = 0; i < 4; i++)
			{
				Point next_red = moveBall(cur.red, i);
				Point next_blue = moveBall(cur.blue, i);
				if (graph[ next_blue.row ][ next_blue.col ] == 'O')
				{
					continue;
				}
				if (graph[ next_red.row ][ next_red.col ] == 'O')
				{
					System.out.println("1");
					return;
				}

				//  빨간공 파란공 충돌 처리 -> 각 공이 움직인 거리가 먼 공을 뒤로 보냄(거리가 가까운 공이 먼저 도착)
				if (next_red.row == next_blue.row && next_red.col == next_blue.col)
				{
					if (next_red.cnt > next_blue.cnt)
					{
						next_red.row += v_r[ (i + 2) % 4 ];
						next_red.col += v_c[ (i + 2) % 4 ];
					}
					else
					{
						next_blue.row += v_r[ (i + 2) % 4 ];
						next_blue.col += v_c[ (i + 2) % 4 ];
					}
				}

				State temp = new State(next_red, next_blue);
				temp.cnt = cur.cnt + 1;
				que.add(temp);
			}
		}
		System.out.println("0");
	}

	static Point moveBall(Point ball, int dir)
	{
		Point ret = new Point(ball.row, ball.col);
		while (true)
		{
			if (graph[ ret.row + v_r[ dir ] ][ ret.col + v_c[ dir ] ] == '#')
			{
				break;
			}
			ret.row += v_r[ dir ];
			ret.col += v_c[ dir ];
			ret.cnt++;
			if (graph[ ret.row ][ ret.col ] == 'O')
			{
				break;
			}
		}
		return ret;
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		N = Integer.parseInt(st.nextToken( ));
		M = Integer.parseInt(st.nextToken( ));
		graph = new char[ N ][ M ];

		for (int i = 0; i < N; i++)
		{
			String temp = br.readLine( );
			for (int j = 0; j < M; j++)
			{
				graph[ i ][ j ] = temp.charAt(j);
				if (graph[ i ][ j ] == 'R') red = new Point(i, j);
				else if (graph[ i ][ j ] == 'B') blue = new Point(i, j);
				else if (graph[ i ][ j ] == 'O') hole = new Point(i, j);
			}
		}
	}
}
