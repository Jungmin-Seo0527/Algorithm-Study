
package EuclideanAlgorithm;

import java.io.*;
import java.util.*;

// BOJ3955_캔디_분배
public class BOJ3955_캔디_분배
{
	static class Tuple
	{
		long g, x, y;

		public Tuple(long g, long x, long y)
		{
			this.g = g;
			this.x = x;
			this.y = y;
		}
	}

	static int t;
	static long K, C;
	static final long f = 1000000000;

	static StringBuilder sb = new StringBuilder( );

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );

		System.out.println(sb);

	}

	static void solve( )
	{
		Tuple t = extended_euclidean(K, C);
		if (t.g != 1)
		{
			sb.append("IMPOSSIBLE\n");
			return;
		}

		// KX+1=CY -> KX+CY=1 (X<0, Y>0)
		// t.x는 음수, t.y는 양수가 되어야 한다.
		while (t.y <= 0 || t.x >= 0)
		{
			t.y += K;
			t.x -= C;
		}
		
		// 이 지즘에서 t.x 는 음수, t.y는 양수로 만든 상태
		if (t.y > f ) // Y(t.y)가 10^9보다 큰 경우 불가능
		{
			sb.append("IMPOSSIBLE\n");
		}
		else
		{
			sb.append(t.y + "\n");
		}
	}

	// 유클리드 호제법을 이용한 최대 공약수 구하기
	static int getGcd(int a, int b)
	{
		if (b == 0) return a;
		else return getGcd(b, a % b);
	}

	// 확장 유클리드 호제법
	static Tuple extended_euclidean(long a, long b)
	{
		if (b == 0) return new Tuple(a, 1, 0);
		Tuple temp = extended_euclidean(b, a % b);
		long g = temp.g;
		long x = temp.x;
		long y = temp.y;
		//System.out.println(g + " " + x + " " + y + " " + a + " " + b);
		return new Tuple(g, y, x - (a / b) * y);
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));

		t = Integer.parseInt(st.nextToken( ));
		for (int i = 0; i < t; i++)
		{
			st = new StringTokenizer(br.readLine( ));
			K = Long.parseLong(st.nextToken( ));
			C = Long.parseLong(st.nextToken( ));

			solve( );
		}
	}
}
