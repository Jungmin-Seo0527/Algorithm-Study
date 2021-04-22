
package binarySearch;

import java.io.*;
import java.util.*;

// BOJ2143_두_배열의_합
// A, B 배열의 부분배열을 모두 구한후에 하나의 부분배열들중 T가 되기위한 다른 하나의 부분배열을 구한다.
// A, B 배열의 값을 입력받을때 누적합으로 받는다.
// B배열은 누적합을 이용하여 각 부배열의 합들을 모두 나열한 새로운 배열로 만든후 오름차순으로 정렬한다.
// A의 배열의 모든 부 배열을 구하는 동시에 A의 부배열과 B배열의 부 배열의 합이 T가 되는 B의 부 배열을 이분탐색
// 이분탐색에서 lower_bound와 upper_bound를 이용한다.
// lower_bound는 target이 arr에 들어갈수 있는 가장 작은 인덱스가 된다. (target 이상의 값이 있는 가장 작은 인덱스)
// upper_bound는 target이 arr에 들어갈 수 있는 가장 큰 인덱스가 된다.(tatget 초과의 값이 있는 가장 작은 인덱스)
// upper_bound 의 return 값 - lower_bound 의 return 값을 ans에 더해준다.
// 존재하지 않는 경우에는 lower_bound의 return == upper_bound의 return 이므로 0이 된다.

public class BOJ2143_두_배열의_합
{
	static int T, n, m, arrA[], arrB[], arrB2[];
	static long ans;

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		solve( );
	}

	static void solve( )
	{
		arrB2 = new int[ m * (m + 1) / 2 ];
		int idx = 0;
		for (int i = 0; i < m; i++)
		{
			for (int j = 0; j < i; j++)
			{
				arrB2[ idx++ ] = arrB[ i ] - arrB[ j ];
			}
			arrB2[ idx++ ] = arrB[ i ];
		}
		Arrays.sort(arrB2);
		
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j <= i; j++)
			{
				int temp = arrA[ i ] - arrA[ j ];
				if (i == j) temp = arrA[ i ];
				int high = upper_bound(arrB2, T - temp);
				int low = lower_bound(arrB2, T - temp);
				ans += (high - low);
			}
		}
		System.out.println(ans);
	}

	static int lower_bound(int arr[], int target)
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

	static int upper_bound(int arr[], int target)
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
		T = Integer.parseInt(st.nextToken( ));

		st = new StringTokenizer(br.readLine( ));
		n = Integer.parseInt(st.nextToken( ));

		arrA = new int[ n ];
		st = new StringTokenizer(br.readLine( ));
		arrA[ 0 ] = Integer.parseInt(st.nextToken( ));
		for (int i = 1; i < n; i++)
		{
			int input = Integer.parseInt(st.nextToken( ));
			arrA[ i ] = arrA[ i - 1 ] + input;
		}

		st = new StringTokenizer(br.readLine( ));
		m = Integer.parseInt(st.nextToken( ));

		arrB = new int[ m ];
		st = new StringTokenizer(br.readLine( ));
		arrB[ 0 ] = Integer.parseInt(st.nextToken( ));
		for (int i = 1; i < m; i++)
		{
			int input = Integer.parseInt(st.nextToken( ));
			arrB[ i ] = arrB[ i - 1 ] + input;
		}
	}
}
