
package DFS_BFS;

import java.io.*;
import java.util.*;

// BOJ1799_비숍
public class BOJ1799_비숍
{
	static int N, max, ans;
	static boolean diag1[], diag2[], graph[][];

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		doDFS(0, 0, 0);
		ans = max;
		max = 0;
		doDFS(0, 1, 0);
		ans += max;
		System.out.println(ans);
	}

	// 체스판의 특징과 대각성이란 특성을 이용한 풀이
	// 한 지점에서 바로 다음칸은 현 지점에 아무런 영향을 주지 못한다 (대각선만 영향을 받는다)
	// 따라서 만약 4x4 사이즈의 체스판이 있다면 서로 영향이 있는 지점은
	// (0, 0) (0, 2), (1, 1), (1, 3), (2, 0), (2, 2), (3, 1), (3, 3) 이 된다
	// 즉 DFS를 돌릴때 다음 지점으로 넘어갈때 row가 바뀔때 주의해야 한다. (한번은 col이 홀수 한번은 짝수 번갈아서)
	// 시간복잡도 O(2^(N/2 * N/2)) 로 줄일 수 있다. 

	// 이분매칭이란 방법도 있다고 한다. 
	static void doDFS(int row, int col, int cnt)
	{
		if (col >= N)
		{
			row++;
			if (col % 2 == 0) col = 1;
			else col = 0;
		}
		if (row >= N)
		{
			max = max > cnt ? max : cnt;
			return;
		}
		if (graph[ row ][ col ] && diag1[ row + col ] == false && diag2[ row - col + N - 1 ] == false)
		{
			diag1[ row + col ] = diag2[ row - col + N - 1 ] = true;
			doDFS(row, col + 2, cnt + 1);
			diag1[ row + col ] = diag2[ row - col + N - 1 ] = false;
		}
		doDFS(row, col + 2, cnt);
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		N = Integer.parseInt(st.nextToken( ));
		diag1 = new boolean[ 2 * N - 1 ];
		diag2 = new boolean[ 2 * N - 1 ];
		graph = new boolean[ N ][ N ];

		for (int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine( ));
			for (int j = 0; j < N; j++)
			{
				int input = Integer.parseInt(st.nextToken( ));
				if (input == 1) graph[ i ][ j ] = true;
			}
		}
	}
}
