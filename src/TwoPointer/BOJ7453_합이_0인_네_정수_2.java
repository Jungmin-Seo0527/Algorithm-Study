
package TwoPointer;

import java.io.*;
import java.util.*;

// BOJ7453_합이_0인_네_정수
// 기존에 이분탐색으로 풀었던 문제를 두포인터로 풀어냄
// 이분탐색 처리시간: 11668ms
// 두포인터 처리시간: 2904ms

// 4개의 배열의 2개씩 짝지어 두개의 합들을 모두 구한후에 오름차순으로 정렬
// 두배열중 하나는 인덱스 0, 하나는 인덱스 마지막으로 포인터를 둔다.
// 오름차순으로 정렬한점을 고려하려 두 포인터를 조정하여 0인 지점들을 모두 count
public class BOJ7453_합이_0인_네_정수_2
{
	static int arr[][], n;

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		solve( );
	}

	static void solve( )
	{
		int sum1[] = new int[ n * n ];
		int sum2[] = new int[ n * n ];
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
			{
				sum1[ i * n + j ] = arr[ 0 ][ i ] + arr[ 1 ][ j ];
				sum2[ i * n + j ] = arr[ 2 ][ i ] + arr[ 3 ][ j ];
			}
		}
		Arrays.sort(sum1);
		Arrays.sort(sum2);

		int sum2_idx = n * n - 1;
		long cnt = 0;
		long ans = 0;

		for (int i = 0; i < n * n; i++)
		{
			if (i > 0)
			{
				if (sum1[ i ] == sum1[ i - 1 ])
				{
					ans += cnt;
					continue;
				}
				else cnt = 0;
			}
			for (; sum2_idx >= 0; sum2_idx--)
			{
				if (sum1[ i ] + sum2[ sum2_idx ] > 0) continue;
				else if (sum1[ i ] + sum2[ sum2_idx ] < 0) break;
				else cnt++;
			}
			ans += cnt;
		}

		System.out.println(ans);
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		n = Integer.parseInt(st.nextToken( ));
		arr = new int[ 4 ][ n ];
		for (int i = 0; i < n; i++)
		{
			st = new StringTokenizer(br.readLine( ));
			for (int j = 0; j < 4; j++)
			{
				arr[ j ][ i ] = Integer.parseInt(st.nextToken( ));
			}
		}
		for (int i = 0; i < 4; i++)
		{
			Arrays.sort(arr[ i ]);
		}
	}
}
