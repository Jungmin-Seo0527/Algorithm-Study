
package binarySearch;

import java.io.*;
import java.util.*;

// BOJ1208_부분수열의_합2_2
// 문제 조건에서 부분수열의 최대합은 4000000임을 이용하여 부분수열의 합들을 일차원 배열에 저장
// 부분합을 인덱스로 하여 갯수를 저장하는 배열로 부분합들을 저장
// 음수부분 처리를 위해 +4000000를 항상 해준다
// 두 인덱스의 합이 S가 되는 경우 해당 값들을 곱(왼쪽 오른쪽 부분배열 모두 필요한 경우)
// 오직 왼쪽 혹은 오른쪽만의 부분배열로 S를 만드는 방법인 각각의 S+SZ 인덱스의 값들을 따로 더해준다
// 이분탐색 처리시간: 1141ms
// 아래와 같은 방법의 처리시간: 208ms

public class BOJ1208_부분수열의_합2_2
{
	static int N, S, arr[];
	static int SZ = 4000000;
	static long ans, arr1[], arr2[];

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		solve( );
	}

	static void solve( )
	{
		for (int i = 0; i < N / 2; i++)
		{
			doDFS(i, N / 2, arr[ i ], 1);
		}
		for (int i = N / 2; i < N; i++)
		{
			doDFS(i, N, arr[ i ], 2);
		}

		ans += arr1[ S + SZ ];
		ans += arr2[ S + SZ ];
		for (int i = 0; i <= 2 * SZ; i++)
		{
			if (2 * SZ - i + S > 2 * SZ || 2 * SZ - i + S < 0) continue;
			ans += arr1[ i ] * arr2[ 2 * SZ - i + S ];
		}
		System.out.println(ans);
	}

	static void doDFS(int idx, int end, int sum, int mode)
	{
		if (idx == end) return;
		if (mode == 1) arr1[ sum + SZ ]++;
		else arr2[ sum + SZ ]++;
		for (int i = idx + 1; i < end; i++)
		{
			doDFS(i, end, sum + arr[ i ], mode);
		}
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		N = Integer.parseInt(st.nextToken( ));
		S = Integer.parseInt(st.nextToken( ));
		arr = new int[ N ];
		st = new StringTokenizer(br.readLine( ));
		for (int i = 0; i < N; i++)
		{
			arr[ i ] = Integer.parseInt(st.nextToken( ));
		}
		arr1 = new long[ 2 * SZ + 1 ];
		arr2 = new long[ 2 * SZ + 1 ];
	}
}
