
package greedy;

import java.io.*;
import java.util.*;

// BOJ18234_당근_훔쳐_먹기
public class BOJ18234_당근_훔쳐_먹기
{
	static class Carrot implements Comparable< Carrot >
	{
		long weight;
		long power;
		long cur = 0;

		public Carrot(int _weight, int _power)
		{
			this.weight = _weight;
			this.power = _power;
		}

		@Override
		public int compareTo(Carrot o)
		{
			if (this.power < o.power)
			{
				return 1;
			}
			else if (this.power == o.power)
			{
				return 0;
			}
			else
			{
				return -1;
			}

		}
	}

	static int N, T;
	static long ans;

	static Carrot[ ] carrots;

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		solve( );
		System.out.println(ans);
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		N = Integer.parseInt(st.nextToken( ));
		T = Integer.parseInt(st.nextToken( ));
		carrots = new Carrot[ N ];

		for (int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine( ));
			carrots[ i ] = new Carrot(Integer.parseInt(st.nextToken( )), Integer.parseInt(st.nextToken( )));
			carrots[ i ].cur = carrots[ i ].weight + carrots[ i ].power * (T - 1);
		}
		Arrays.sort(carrots);
	}

	// power가 큰 순서대로 당근 정렬
	// 당근들이 최대로 커질때까지 당근을 먹지 않는다.
	// 최대로 커지면 가장 큰것을 먹고 오늘 먹은 것을 뺀 이전날 가장 큰것을 먹는다
	// 일수가 아닌 당근 갯수만큼 1바퀴만 돌면 된다.
	static void solve( )
	{

		for (int i = 0; i < N; i++)
		{
			ans += (carrots[ i ].cur - carrots[ i ].power * i);
		}
	}
}
