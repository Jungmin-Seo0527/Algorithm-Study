
package EuclideanAlgorithm;

import java.io.*;
import java.util.*;

// BOJ1850_최대_공약수
public class BOJ1850_최대_공약수
{
	public static void main(String[ ] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		long n1 = Long.parseLong(st.nextToken( ));
		long n2 = Long.parseLong(st.nextToken( ));

		long bigger = Math.max(n1, n2);
		long smaller = Math.min(n1, n2);

		long ans = gcd(bigger, smaller);
		StringBuilder sb = new StringBuilder( );
		for (int i = 0; i < ans; i++)
		{
			sb.append("1");
		}
		System.out.println(sb);
	}
	
	// 유클리드 호제법을 이용한 최대 공약수 구하는 알고리즘
	// 큰수에서 작은수를 나눈다.
	// 그수가 0이 아니라면 기존의 큰수는 나누는 수, 작은수는 나머지
	// 나머지 값이 0이될때까지 반복
	
	// (1071, 1029) = (1029, 42) = (42, 21) = (21, 0) = 21(1071과 1029의 최대 공약수)
	// a를 b로 나눈 나머지 r이 있으면 a와 b의 최대공약수는 b와 r의 최대 공약수와 같다
	public static long gcd(long p, long q)
	{
		if (q == 0) return p;
		else return gcd(q, p % q);
	}
}