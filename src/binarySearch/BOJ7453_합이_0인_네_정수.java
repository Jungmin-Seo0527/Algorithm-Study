
package binarySearch;

import java.io.*;
import java.util.*;

// BOJ7453_합이_0인_네_정수
// 총 4개의 배열에서 각각 하나의 숫자들을 골라 총 4개의 합이 0이되는 경우의 수 구하기
// 두개의 배열에서 각각의 합들을 모두 구한 배열을 오름차순으로 만든다
// 나머지 두개의 배열의 합을 구해가면서 그 합과 오름차순 정렬한 배열에서 더하면 0이 되는 값을 구한다.
// lower_bound, upper_bound를 이용한 이분탐색으로 시간을 줄인다.

public class BOJ7453_합이_0인_네_정수
{
	static long n, arr1[], arr2[], arr3[], arr4[], ans;

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		solve( );
	}

	static void solve( )
	{
		long arr[] = new long[ (int) (n * n) ];
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
			{
				arr[ i * (int) n + j ] = arr1[ i ] + arr2[ j ];
			}
		}
		Arrays.sort(arr);
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
			{
				long sum = arr3[ i ] + arr4[ j ];
				int high = upper_bound(arr, -sum);
				int low = lower_bound(arr, -sum);
				ans += (high - low);
			}
		}
		System.out.println(ans);
	}

	static int lower_bound(long arr[], long target)
	{
		int start = 0;
		int end = arr.length;
		while (start < end)
		{
			int mid = (start + end) >>> 1;
			if (arr[ mid ] >= target) end = mid;
			else start = mid + 1;
		}
		return end;
	}

	static int upper_bound(long arr[], long target)
	{
		int start = 0;
		int end = arr.length;
		while (start < end)
		{
			int mid = (start + end) >>> 1;
			if (arr[ mid ] > target) end = mid;
			else start = mid + 1;
		}
		return end;
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		n = Long.parseLong(st.nextToken( ));
		arr1 = new long[ (int) n ];
		arr2 = new long[ (int) n ];
		arr3 = new long[ (int) n ];
		arr4 = new long[ (int) n ];
		for (int i = 0; i < n; i++)
		{
			st = new StringTokenizer(br.readLine( ));
			arr1[ i ] = Long.parseLong(st.nextToken( ));
			arr2[ i ] = Long.parseLong(st.nextToken( ));
			arr3[ i ] = Long.parseLong(st.nextToken( ));
			arr4[ i ] = Long.parseLong(st.nextToken( ));
		}

	}
}
