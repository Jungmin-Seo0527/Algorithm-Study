
package Codeforces_Round690_Div3;

import java.io.*;
import java.util.*;

// 900
// DFS
// 숫자의 각 자리의 숫자들의 합이 입력값이 되는 최소값 구하기
// 입력 15 -> 출력 69 (6 + 9 = 15)
// 최소값을 구해야 하니 큰자리 숫자가 작은 자리숫자보다 큰 형태
// 즉 오름차순 형태의 숫자만이 가능하다( 69와 96 모두 각 자리의 합이 15이지만 최소값인 69가 정답)
public class C_Unique_Number
{
	static int T, N, arr[];
	static int ans = -1;
	static boolean visited[];
	static StringBuilder sb = new StringBuilder( );

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
	}

	static void doDFS(int n, int cur)
	{
		int temp = 0;
		for (int i = 0; i < 10; i++)
		{
			if (visited[ i ]) temp += i;
		}
		if (temp == N)
		{
			if (ans == -1) ans = n;
			else
			{
				if (ans > n) ans = n;
			}
			return;
		}
		if (temp > N) return;
		if (ans != -1 && ans <= n) return;
		for (int i = cur + 1; i < 10; i++)
		{
			if (visited[ i ] == false)
			{
				visited[ i ] = true;
				doDFS(n * 10 + i, i);
				visited[ i ] = false;
			}
		}
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		T = Integer.parseInt(st.nextToken( ));
		for (int t = 0; t < T; t++)
		{
			st = new StringTokenizer(br.readLine( ));
			N = Integer.parseInt(st.nextToken( ));
			visited = new boolean[ 10 ];
			ans = -1;
			for (int i = 1; i < 10; i++)
			{
				visited[ i ] = true;
				doDFS(i, i);
				visited[ i ] = false;
			}
			sb.append(ans + "\n");
		}
		System.out.print(sb);
	}
}
