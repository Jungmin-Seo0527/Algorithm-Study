
package bruteForce;

import java.io.*;
import java.util.*;

// BOJ17071_숨바꼭질5
// 수빈이의 이동중 +1, -1은 계속 왕복하는 경우가 존재
// 이전에 홀수번 타임에 수빈이가 건너갔던 지점을 동생이 같은 홀수 타임에 도착하면 만나는 것임(짝수도 동일)
// x2인 경우도 x2이기 때문에 가능

public class BOJ17071_숨바꼭질5
{
	static class State
	{
		int subin, sis, cnt;

		public State(int subin, int sis, int cnt)
		{
			this.subin = subin;
			this.sis = sis;
			this.cnt = cnt;
		}
	}

	static int N, K;
	static final int end = 500000;
	static boolean visited[][] = new boolean[ 500001 ][ 2 ];

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		doBFS( );
	}

	static void doBFS( )
	{
		Queue< State > que = new LinkedList<>( );
		State start = new State(N, K, 0);
		que.add(start);
		visited[ start.subin ][ start.cnt % 2 ] = true;
		while (que.isEmpty( ) == false)
		{
			State cur = que.poll( );
			//System.out.println(cur.cnt + " " + cur.subin + " " + cur.sis);
			if (cur.subin == cur.sis)
			{
				System.out.println(cur.cnt);
				return;
			}

			int next_sis = cur.sis + cur.cnt + 1;
			if (next_sis > end)
			{
				System.out.println("-1");
				return;
			}

			for (int i = -1; i <= 1; i++)
			{
				int next_subin = cur.subin + i;
				if (i == 0) next_subin = cur.subin * 2;

				if (next_subin < 0) continue;
				if (next_subin > end) continue;

				// 수빈이와 동생이 현재 같은 지점에 있거나
				// 동생이 같은 홀수 혹은 짝수 타임에 존재하는 지점이
				// 같은 홀수 혹은 짝수 타임에 수빈이가 지나왔던 지점(수빈이는 이곳을 계속 왕복운동 가능)
				if (next_sis == next_subin || visited[ next_sis ][ (cur.cnt + 1) % 2 ])
				{
					System.out.println(cur.cnt + 1);
					return;
				}
				if (visited[ next_subin ][ (cur.cnt + 1) % 2 ]) continue;
				visited[ next_subin ][ (cur.cnt + 1) % 2 ] = true;

				que.add(new State(next_subin, next_sis, cur.cnt + 1));
			}
		}
		System.out.println("-1");
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		N = Integer.parseInt(st.nextToken( ));
		K = Integer.parseInt(st.nextToken( ));
	}
}
