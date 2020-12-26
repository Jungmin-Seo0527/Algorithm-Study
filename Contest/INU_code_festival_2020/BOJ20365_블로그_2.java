
package INU_code_festival_2020;

import java.io.*;
import java.util.*;

// BOJ20365_블로그_2
public class BOJ20365_블로그_2
{
	static int N, b_cnt, r_cnt;
	static char graph[];
	static char c1, c2;

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		N = Integer.parseInt(st.nextToken( ));
		graph = new char[ N ];

		String temp = br.readLine( );

		// 첫번째 색은 그냥 카운트
		graph[ 0 ] = temp.charAt(0);
		if (graph[ 0 ] == 'B') b_cnt++;
		else r_cnt++;

		// 이전색과 다른 색인경우(작업+1) -> 해당 색 카운트 +1
		for (int i = 1; i < N; i++)
		{
			graph[ i ] = temp.charAt(i);
			if (graph[ i ] == 'B' && graph[ i - 1 ] == 'R') b_cnt++;
			else if (graph[ i ] == 'R' && graph[ i - 1 ] == 'B') r_cnt++;
		}

		// 작업량이 많은 색은 처음에 전체 칠해주고
		// 작업량이 적은 색을 곳곳에 색칠해 준다.
		int ret = Math.min(b_cnt, r_cnt) + 1;
		System.out.println(ret);

	}
}
