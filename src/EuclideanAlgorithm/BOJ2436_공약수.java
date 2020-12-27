
package EuclideanAlgorithm;

import java.io.*;
import java.util.*;

// BOJ2436_공약수
public class BOJ2436_공약수
{
	static int gcd, lcm;

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		solve( );
	}

	// 최대공약수가 6, 최소 공배수가 180이라고 가정하면
	// 정답이 되는 x1, x2 -> 6a, 6b (최대공약수 6)
	// a와 b는 서로소이며 x1*x2=최대공약수*최소공배수이기 때문에
	// a*b는 180/6 = 30
	// 서로소이며 곱이 최소공배수/최대 공약수인 두수중 합이 가장 큰 쌍을 구하여야 한다.
	static void solve( )
	{
		int n = lcm / gcd;
		int ans = 1;
		for (int i = 1; i * i < n; i++) // 반복문 범위를 줄여준다(끝가지 가면 순서만 다른 같은 쌍이 존재한다)
		{
			if (n % i == 0) // 곱하였을때 n이며
			{
				if (getGcd(n / i, i) == 1) // 서로소인 a, b
				{
					ans = i;
				}
			}
		}
		// ans는 a인 상태이므로 실제 값인 6a를 위해 최소 공배수를 곱하여 준다.(짝꿍도 마찬가지)
		int ans2 = n / ans * gcd;
		ans *= gcd;
		System.out.println(ans + " " + ans2);
	}

	static int getGcd(int p, int q)
	{
		if (q == 0) return p;
		else return getGcd(q, p % q);
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		gcd = Integer.parseInt(st.nextToken( ));
		lcm = Integer.parseInt(st.nextToken( ));
	}
}
