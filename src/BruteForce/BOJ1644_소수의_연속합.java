
package BruteForce;

import java.io.*;
import java.util.*;

// BOJ1644_소수의_연속합
// 소수 판별, 두 포인터
public class BOJ1644_소수의_연속합
{
	static int N, primeCnt;
	static final int SZ = 4000000;

	static boolean isPrime[] = new boolean[ SZ + 1 ];
	static int prime[] = new int[ SZ + 1 ];

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		solve( );
	}

	// 소수 배열을 두포인터로 연속 합 구하기
	static void solve( )
	{
		int start = 0;
		int end = 0;
		int sum = 0;
		int cnt = 0;
		while (start <= end && end < primeCnt)
		{
			if (sum < N)
			{
				sum += prime[ end ];
				end++;
			}
			else if (sum > N)
			{
				sum -= prime[ start ];
				start++;
			}
			else
			{
				cnt++;
				sum -= prime[ start ];
				start++;
			}
		}
		System.out.println(cnt);
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		N = Integer.parseInt(st.nextToken( ));

		isPrime[ 0 ] = true;
		isPrime[ 1 ] = true;
		for (int i = 2; i * i <= SZ; i++)
		{
			if (isPrime[ i ] == false)
			{
				for (int j = i * i; j <= SZ; j += i)
				{
					isPrime[ j ] = true;
				}
			}
		}

		// 소수배열
		for (int i = 0; i <= SZ; i++)
		{
			if (isPrime[ i ] == false)
			{
				prime[ primeCnt++ ] = i;
			}
		}
	}
}
