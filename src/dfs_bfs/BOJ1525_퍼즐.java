
package dfs_bfs;

import java.io.*;
import java.util.*;

// BOJ1525_퍼즐
public class BOJ1525_퍼즐
{
	static int graph[][];
	static int v_r[] =
	{ 1, -1, 0, 0 };
	static int v_c[] =
	{ 0, 0, 1, -1 };
	static BitSet visited = new BitSet( );

	static Queue< Integer > que = new LinkedList<>( );
	static Map< Integer, Integer > cnt = new HashMap<>( );

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		solve( );
	}

	static void solve( )
	{
		while (que.isEmpty( ) == false)
		{
			int cur = que.poll( );
			int cur2 = cur;
			if (cur == 123456780) // 정리된 상태의 정수
			{
				System.out.println(cnt.get(cur));
				return;
			}

			// 9자리 정수로 저장한 그래프의 상태를 다시 2차원 그래프로 복귀
			int row = 0, col = 0; // 0이 있는 좌표
			for (int i = 2; i >= 0; i--)
			{
				for (int j = 2; j >= 0; j--)
				{
					graph[ i ][ j ] = cur % 10;
					cur /= 10;
					if (graph[ i ][ j ] == 0)
					{
						row = i;
						col = j;
					}
				}
			}

			// 4방향에 대한 조사
			for (int i = 0; i < 4; i++)
			{
				int next_row = row + v_r[ i ];
				int next_col = col + v_c[ i ];
				if (check(next_row, next_col)) // check는 오직 범위만 조사
				{
					// swap
					graph[ row ][ col ] = graph[ next_row ][ next_col ];
					graph[ next_row ][ next_col ] = 0;

					// swap된 그래프를 다시 정수로 변환
					int temp = 0;
					for (int j = 0; j < 3; j++)
					{
						for (int k = 0; k < 3; k++)
						{
							temp = temp * 10 + graph[ j ][ k ];
							//System.out.println(temp);
						}
					}

					// visited 조사 
					if (cnt.get(temp) == null)
					{

						int temp_cnt = cnt.get(cur2) + 1;
						cnt.put(temp, temp_cnt);
						que.add(temp);
					}

					// swap된 그래프 원상복구
					graph[ next_row ][ next_col ] = graph[ row ][ col ];
					graph[ row ][ col ] = 0;
				}
			}
		}
		System.out.println(-1);
	}

	static boolean check(int row, int col)
	{
		if (row < 0 || row >= 3 || col < 0 || col >= 3) return false;

		return true;
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		graph = new int[ 3 ][ 3 ];

		int temp = 0;
		for (int i = 0; i < 3; i++)
		{
			st = new StringTokenizer(br.readLine( ));
			for (int j = 0; j < 3; j++)
			{
				graph[ i ][ j ] = Integer.parseInt(st.nextToken( ));
				temp = temp * 10 + graph[ i ][ j ];
			}
		}
		que.add(temp);
		cnt.put(temp, 0);
	}
}
