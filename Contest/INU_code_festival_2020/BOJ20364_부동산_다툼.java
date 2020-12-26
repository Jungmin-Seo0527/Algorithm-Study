
package INU_code_festival_2020;

import java.io.*;
import java.util.*;

// BOJ20364_부동산_다툼
public class BOJ20364_부동산_다툼
{
	static int N, Q;
	static boolean graph[];

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		N = Integer.parseInt(st.nextToken( ));
		Q = Integer.parseInt(st.nextToken( ));
		graph = new boolean[ N + 1 ];

		StringBuilder sb = new StringBuilder( );
		for (int i = 0; i < Q; i++)
		{
			st = new StringTokenizer(br.readLine( ));
			int cur = Integer.parseInt(st.nextToken( ));
			int flag = cur;
			int temp = 0;

			// 처음 가려고 하는 노드부터 시작하여 부모노드로 올라간다.
			// 마지막까지 이미 오리가 있는 곳이 있는지 조사하여 있으면 temp=현재 노드
			// temp가 마지막까지 0이면 맨 위의 노드까지 올라갈때가지 만나는 오리 없음
			// 0이 아닌 노드 번호가 있으면 가장 처음 만나는 노드 번호
			while (true)
			{
				if (graph[ cur ])
				{
					temp = cur;
				}
				cur = cur / 2;
				if (cur == 0) break;
			}
			sb.append(temp + "\n");
			if (temp == 0) graph[ flag ] = true;
		}
		System.out.println(sb);

	}
}
