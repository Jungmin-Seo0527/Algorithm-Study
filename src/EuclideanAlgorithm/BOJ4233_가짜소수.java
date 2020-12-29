
package EuclideanAlgorithm;

import java.io.*;
import java.util.*;

// BOJ4233_가짜소수
public class BOJ4233_가짜소수
{
	static String yes = "yes";
	static String no = "no";

	static int a, p;

	static StringBuilder ret = new StringBuilder( );

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		System.out.println(ret);
	}

	static void solve( )
	{
		long temp = pow(a, p);
		if (temp == a)
		{
			if (isPrice(p) == false)
			{
				ret.append(yes + "\n");
				return;
			}
		}
		ret.append(no + "\n");
	}

	// 분할 정복을 이용한 제곱
	static long pow(long n, long a)
	{
		long ret = 1;
		while (a > 0)
		{
			if (a % 2 == 1)
			{
				ret *= n;
				ret %= p;
			}
			n *= n;
			n %= p;
			a /= 2;
		}
		return ret;
	}

	static boolean isPrice(long n)
	{
		for (int i = 2; i * i <= n; i++)
		{
			if (n % i == 0) return false;
		}
		return true;
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		while (true)
		{
			st = new StringTokenizer(br.readLine( ));
			p = Integer.parseInt(st.nextToken( ));
			a = Integer.parseInt(st.nextToken( ));
			if (a == 0 && p == 0)
			{
				break;
			}
			solve( );
		}

	}
}
