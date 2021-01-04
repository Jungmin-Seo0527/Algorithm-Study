
package DynamicProgramming;

import java.io.*;
import java.util.*;

// BOJ1562_계단_수
// 비트필드를 이용한 dynamic programming
// 현재까지 지나왔던 숫자들을 bit masking
// 기존의 다이나믹 프로그래밍의 계단수 문제가 현제 계단 번호와 지나온 계단 수의 dp라면
// bit masking 을 추가한 3차원 dp로 문제 해결

// 다음 채울 계단의 번호와 현재 계단의 bitmask를 or 연산하여 나오는 bit에 +=현재 계단에서의 비트마스킹에서의 dp
public class BOJ1562_계단_수
{
	static final int mod = 1000000000;
	static final int mask = (1 << 10) - 1;
	static int dp[][][];
	static int N;

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		solve( );
	}

	static void solve( )
	{
		// 처음 계단 하나만 지났을때의 bit mask를 채워준다.
		// 10, 100, 1000, 10000 ,,,
		for (int i = 1; i < 10; i++)
		{
			dp[ 1 ][ i ][ 1 << i ] = 1;
		}

		// 현재 계단의 bit에서 채울 dp는 현재 개단의 +-1에서의 현재 bit에서 다음 채울 계단을 or 연산한 bit mask
		for (int n = 1; n < N; n++) // 현재 지나온 계단 갯수
		{
			for (int next = 0; next < 10; next++) // 다음 계단 번호
			{
				for (int bit = 0; bit <= mask; bit++) // bit mask
				{
					if (next == 0) // 채워야할 계단 번호가 0이거나 9는 1과 8에서만 가져올 수 있다.
					{
						int next_bit = bit | (1 << (next));
						dp[ n + 1 ][ next ][ next_bit ] = (dp[ n + 1 ][ next ][ next_bit ]
						                + dp[ n ][ next + 1 ][ bit ]) % mod;
					}
					else if (next == 9)
					{
						int next_bit = bit | (1 << (next));
						dp[ n + 1 ][ next ][ next_bit ] = (dp[ n + 1 ][ next ][ next_bit ]
						                + dp[ n ][ next - 1 ][ bit ]) % mod;
					}
					else
					{
						int next_bit = bit | (1 << (next));
						dp[ n + 1 ][ next ][ next_bit ] = (dp[ n + 1 ][ next ][ next_bit ]
						                + dp[ n ][ next + 1 ][ bit ]) % mod;

						dp[ n + 1 ][ next ][ next_bit ] = (dp[ n + 1 ][ next ][ next_bit ]
						                + dp[ n ][ next - 1 ][ bit ]) % mod;
					}
				}
			}
		}
		int ret = 0;
		for (int cur = 0; cur < 10; cur++)
		{
			ret = (ret + dp[ N ][ cur ][ mask ]) % mod;
		}
		System.out.println(ret);
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		N = Integer.parseInt(st.nextToken( ));
		dp = new int[ N + 1 ][ 10 ][ mask + 1 ];
	}
}
