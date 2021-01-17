
package Samgsung;

import java.io.*;
import java.util.*;

// BOJ17143_낚시왕
public class BOJ17143_낚시왕
{
	static class Shark
	{
		int row, col, speed, dist, size;
		boolean die = false;

		public Shark(int row, int col, int speed, int dist, int size)
		{
			this.row = row;
			this.col = col;
			this.speed = speed;
			this.dist = dist;
			this.size = size;
		}
	}

	static int v_r[] =
	{ -1, 1, 0, 0 };
	static int v_c[] =
	{ 0, 0, 1, -1 };

	static int R, C, M, graph[][], ans;
	static Shark sharks[];

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		for (int i = 0; i < C; i++)
		{
			gotFish(i);
			moveShark( );
			//show( );
		}
		System.out.println(ans);
	}

	// 상어의 이동 좌표 처리
	static void moveShark( )
	{
		int copy[][] = new int[ R ][ C ];

		for (int s = 1; s <= M; s++)
		{
			Shark cur = sharks[ s ];
			if (cur.die) continue;
			int next_row = cur.row + v_r[ cur.dist ] * cur.speed;
			int next_col = cur.col + v_c[ cur.dist ] * cur.speed;

			// 좌표가 음수일땐 방향만 반대로 바꾸어 주고 좌표를 양수로 처리
			if (next_row < 0)
			{
				next_row *= -1;
				cur.dist = changeDist(cur.dist);
			}
			if (next_col < 0)
			{
				next_col *= -1;
				cur.dist = changeDist(cur.dist);
			}

			// 좌표가 0부터 시작한다고 설정해야 된다
			// 좌표를 (R-1)로 나눈 몫이 짝수이면
			// 실제 좌표는 좌표를 (R-1)로 나눈 나머지이며 방향은 그대로
			// 만약 나눈 몫이 홀수이면
			// R-1에서 나머지를 뺀 값이 실제 좌표가 되며 방향은 반대
			if (next_row >= R)
			{
				if (next_row / (R - 1) % 2 == 0)
				{
					next_row = next_row % (R - 1);
				}
				else
				{
					next_row = R - 1 - (next_row % (R - 1));
					cur.dist = changeDist(cur.dist);
				}
			}

			if (next_col >= C)
			{
				if (next_col / (C - 1) % 2 == 0)
				{
					next_col = next_col % (C - 1);
				}
				else
				{
					next_col = C - 1 - (next_col % (C - 1));
					cur.dist = changeDist(cur.dist);
				}
			}
			//			System.out.println((cur.row + 1) + " " + (cur.col + 1) + " " + (next_row + 1) + " "
			//			                + (next_col + 1)+" "+cur.dist);
			cur.row = next_row;
			cur.col = next_col;

			if (copy[ cur.row ][ cur.col ] == 0)
			{
				copy[ cur.row ][ cur.col ] = s;
			}
			else
			{
				if (sharks[ copy[ cur.row ][ cur.col ] ].size > cur.size)
				{
					cur.die = true;
				}
				else
				{
					sharks[ copy[ cur.row ][ cur.col ] ].die = true;
					copy[ cur.row ][ cur.col ] = s;
				}
			}
		}
		for (int i = 0; i < R; i++)
		{
			graph[ i ] = copy[ i ].clone( );
		}
	}

	static void gotFish(int c)
	{
		for (int i = 0; i < R; i++)
		{
			if (graph[ i ][ c ] != 0)
			{
				sharks[ graph[ i ][ c ] ].die = true;
				ans += sharks[ graph[ i ][ c ] ].size;
				graph[ i ][ c ] = 0;
				break;
			}
		}
	}

	static int changeDist(int dist)
	{
		if (dist == 0) return 1;
		if (dist == 1) return 0;
		if (dist == 2) return 3;
		if (dist == 3) return 2;
		return -1;
	}

	static void show( )
	{
		StringBuilder sb = new StringBuilder( );
		for (int i = 0; i < R; i++)
		{
			for (int j = 0; j < C; j++)
			{
				Shark cur = sharks[ graph[ i ][ j ] ];
				sb.append(cur.size + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		R = Integer.parseInt(st.nextToken( ));
		C = Integer.parseInt(st.nextToken( ));
		M = Integer.parseInt(st.nextToken( ));
		graph = new int[ R ][ C ];
		sharks = new Shark[ M + 1 ];
		sharks[ 0 ] = new Shark(-1, -1, -1, -1, 0);

		for (int i = 1; i <= M; i++)
		{
			st = new StringTokenizer(br.readLine( ));
			int r = Integer.parseInt(st.nextToken( )) - 1;
			int c = Integer.parseInt(st.nextToken( )) - 1;
			int s = Integer.parseInt(st.nextToken( ));
			int d = Integer.parseInt(st.nextToken( )) - 1;
			int z = Integer.parseInt(st.nextToken( ));
			sharks[ i ] = new Shark(r, c, s, d, z);
			graph[ r ][ c ] = i;
		}
	}
}
