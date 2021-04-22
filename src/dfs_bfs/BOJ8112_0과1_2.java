
package dfs_bfs;

import java.io.*;
import java.util.*;

// BOJ8112_0과1_2
public class BOJ8112_0과1_2
{
	static class State
	{
		int num, cnt;
		String ret;

		public State(int num, String ret)
		{
			this.num = num;
			this.ret = ret;
		}
	}

	static void doBFS(int n, int num, String ret, int cnt)
	{
		Queue< State > que = new LinkedList<>( );
		boolean visited[] = new boolean[ n ];
		State start = new State(num, ret);
		que.add(start);

		while (que.isEmpty( ) == false)
		{
			State cur = que.poll( );
			//System.out.println(cur.ret);
			int moc = cur.num / n;
			int na = cur.num % n;

			// 몫이 0이 아니면 나오는 나머지를 현재 num으로 갱신
			if (moc != 0)
			{
				cur.num = na;
			}
			if (na == 0) // 출력
			{
				System.out.println(cur.ret);
				return;
			}
			// 이미 이전에 나왔던 나머지였다면 다음 단계는 불필요
			// 이 과정이 없으면 num이 반복적으로 나오는 중복되는 과정이 생긴다.
			if (visited[ na ]) continue;
			visited[ na ] = true;
			for (int i = 0; i <= 1; i++)
			{
				State next = new State(cur.num * 10 + i, cur.ret + i + "");
				next.cnt = cur.cnt + 1;
				que.add(next);
			}
		}
		System.out.println("BRAK");
	}

	public static void main(String[ ] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		int T = Integer.parseInt(st.nextToken( ));
		for (int i = 0; i < T; i++)
		{
			st = new StringTokenizer(br.readLine( ));
			int N = Integer.parseInt(st.nextToken( ));
			doBFS(N, 1, "1", 1);
		}
	}
}
