
package BruteForce;

import java.io.*;
import java.util.*;

// BOJ14391_종이조각
public class BOJ14391_종이조각
{
	static int N, M, ans;
	static int v_r[] =
	{ 0, 1 };
	static int v_c[] =
	{ 1, 0 };
	static int graph[][];

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		doDFS(0, 0, 0);
		System.out.println(ans);
	}

	// visited 는 비트마스킹
	// 현재 row, col은 cur = cur_row*M+cur_col
	static void doDFS(int cur, int sum, int visited)
	{
		if (cur >= N * M)
		{
			ans = ans > sum ? ans : sum;
			return;
		}
		for (int d = 0; d < 2; d++) // 방향 (오른쪽, 아래)
		{
			int cur_row = cur / M;
			int cur_col = cur % M;
			int cur_sum = 0;
			int cur_visited = visited;
			for (int i = 0; i <= 4; i++) // 누적 칸(0칸 ~ 4칸)
			{
				int sum_row = cur_row + v_r[ d ] * i;
				int sum_col = cur_col + v_c[ d ] * i;
				if (check(sum_row, sum_col))
				{
					int sum_point = sum_row * M + sum_col;
					if ((cur_visited & (1 << sum_point)) != 0) break;
					cur_visited = cur_visited | (1 << sum_point);
					cur_sum = cur_sum * 10 + graph[ sum_row ][ sum_col ];

					// 다음 시작 지점 찾기
					// (마지막으로 숫자를 합한 지점 다음 지점이 아닌 cur 지점 다음 지점부터 탐색)
					int next = cur;
					while (true)
					{
						next++;
						if ((cur_visited & (1 << next)) == 0) break;
					}
					doDFS(next, sum + cur_sum, cur_visited);
				}
			}
		}
	}

	static boolean check(int r, int c)
	{
		if (r < 0 || r >= N || c < 0 || c >= M) return false;

		return true;
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		N = Integer.parseInt(st.nextToken( ));
		M = Integer.parseInt(st.nextToken( ));
		graph = new int[ N ][ M ];

		for (int i = 0; i < N; i++)
		{
			String temp = br.readLine( );
			for (int j = 0; j < M; j++)
			{
				graph[ i ][ j ] = temp.charAt(j) - '0';
			}
		}
	}
}
