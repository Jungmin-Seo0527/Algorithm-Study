
package Samgsung;

import java.io.*;
import java.util.*;

// BOJ20061_모노미노도미노2
// 빨간색 영역에서 각각 파란, 초록 영역으로 블록을 이동시키는 방식을
// 파란색을 회전시킨 상태로 생각
// 초록색은 그대로 블록을 내리면 되고
// 파란색으로 블록 이동은 기존의 빨간색 블록의 행과 열을 바꾸어서(90도 회전) 그대로 내려준다
// 즉 블록을 내리는 함수만으로 초록색과 파란색 영역에서의 블록이동을 구현할 수 있다.
public class BOJ20061_모노미노도미노2
{
	static class Block
	{
		int r1, c1, r2, c2;
		int v;

		public Block(int r1, int c1, int r2, int c2)
		{
			this.r1 = r1;
			this.r2 = r2;
			this.c1 = c1;
			this.c2 = c2;
		}
	}

	static int N, green[][], blue[][], ans;
	static Block blocks[];

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		solve( );
	}

	static void solve( )
	{
		for (int i = 0; i < N; i++)
		{
			Block cur = blocks[ i ];
			//System.out.println(cur.r1+" "+cur.c1+" "+cur.r2+" "+cur.c2+" "+cur.v);
			moveBlock(cur, green);
			//show(green);

			Block temp = new Block(cur.c1, cur.r1, cur.c2, cur.r2); // blue를 위해 블록을 회전시킴

			// 빨간색에 있던 블록을 회전시켰으니 블록의 버전도 바뀜
			temp.v = cur.v;
			if (temp.v == 2) temp.v = 3;
			else if (temp.v == 3) temp.v = 2;
			//System.out.println(temp.r1+" "+temp.c1+" "+temp.r2+" "+temp.c2+" "+temp.v);
			moveBlock(temp, blue);
			//show(blue);
		}
		//show(green);
		//show(blue);
		System.out.println(ans);
		System.out.println(count( ));
	}

	static void moveBlock(Block block, int map[][])
	{
		// 처음 스타트 좌표 설정(영역 가장 위에서 시작하는것으로 하면 됨)
		Block cur = new Block(block.r1, block.c1, block.r2, block.c2);
		cur.r1 = 0;
		cur.r2 = 0;
		if (block.v == 3) cur.r2++;
		//System.out.println(cur.r1+" "+cur.c1+" "+cur.r2+" "+cur.c2);

		// 빨간색에 있던 블록을 아래로 이동
		while (true)
		{
			if (check(cur.r1, cur.c1) == false || check(cur.r2, cur.c2) == false)
			{
				map[ cur.r1 - 1 ][ cur.c1 ] = 1;
				map[ cur.r2 - 1 ][ cur.c2 ] = 1;
				break;
			}
			if (map[ cur.r1 ][ cur.c1 ] != 0 || map[ cur.r2 ][ cur.c2 ] != 0) // 그래프에 이미 블록이 존재하거나
			{
				map[ cur.r1 - 1 ][ cur.c1 ] = 1;
				map[ cur.r2 - 1 ][ cur.c2 ] = 1;
				break;
			}
			else
			{
				cur.r1++;
				cur.r2++;
			}
		}

		// 꽉찬 행 제거
		for (int i = 5; i >= 0; i--)
		{
			if (checkCol(i, map) == true) // 꽉찬 행인가?
			{
				ans++; // 꽉찬 행을 제거할때마다 점수 증가
				downBlocks(i, map);
				i++; // 해당 행을 제거후 위에서 블록이 내려왔는데 그 행이 다시 꽉찬 행인 경우가 있음을 주의!!!
			}
		}

		// 특별 공간 처리
		int cnt = 0;
		for (int i = 0; i <= 1; i++)
		{
			for (int j = 0; j <= 3; j++)
			{
				if (map[ i ][ j ] == 1)
				{
					cnt++;
					break;
				}
			}
		}

		// 특별 공간에 있는 만큼 맨 아래 행(5행)의 블록들 제거
		for (int i = 0; i < cnt; i++)
		{
			downBlocks(5, map);
		}
	}

	static boolean check(int r, int c)
	{
		if (r < 0 || r > 5 || c < 0 || c > 3) return false;

		return true;
	}

	// 꽉찬 행인가?
	static boolean checkCol(int r, int map[][])
	{
		for (int i = 0; i <= 3; i++)
		{
			if (map[ r ][ i ] == 0) return false;
		}
		return true;
	}

	// 해당 열의 블록 제거와 위에 있는 블록들 한칸씩 내림
	static void downBlocks(int row, int map[][])
	{
		for (int i = row; i > 0; i--)
		{
			for (int j = 0; j < 4; j++)
			{
				map[ i ][ j ] = map[ i - 1 ][ j ];
				map[ i - 1 ][ j ] = 0;
			}
		}
	}

	static int count( )
	{
		int ret = 0;
		for (int i = 0; i <= 5; i++)
		{
			for (int j = 0; j <= 3; j++)
			{
				if (green[ i ][ j ] == 1) ret++;
				if (blue[ i ][ j ] == 1) ret++;
			}
		}
		return ret;
	}

	static void show(int map[][])
	{
		StringBuilder sb = new StringBuilder( );
		for (int i = 0; i <= 5; i++)
		{
			for (int j = 0; j <= 3; j++)
			{
				sb.append(map[ i ][ j ] + "  ");
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
		blocks = new Block[ N ];
		green = new int[ 6 ][ 4 ];
		blue = new int[ 6 ][ 4 ];
		for (int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine( ));
			int b_v = Integer.parseInt(st.nextToken( ));
			int r = Integer.parseInt(st.nextToken( ));
			int c = Integer.parseInt(st.nextToken( ));
			switch (b_v)
			{
			case 1 :
				blocks[ i ] = new Block(r, c, r, c);
				break;
			case 2 :
				blocks[ i ] = new Block(r, c, r, c + 1);
				break;
			case 3 :
				blocks[ i ] = new Block(r, c, r + 1, c);
				break;

			default :
				break;
			}
			blocks[ i ].v = b_v;

		}
	}
}
