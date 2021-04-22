
package binarySearch;

import java.io.*;
import java.util.*;

// BOJ12015_가장_긴_증가하는_부분_수열2
// LIS: Longest Increasing Subsequence
// Binary Search: O(N log N)

// 기존의 dp로 풀어내는 방식의 시간 복잡도 O(N^2)
// data[i]로 만들수 있는 LIS의 길이는 0 ~ i-1의 인덱스에서 data[i]보다는 숫자는 작으면서 LIS가 가장 큰 숫자에 +1이다
// 나보다 작으면서 가장 큰 LIS를 가진 수를 찾을때 이분탐색을 이용하여 처리시간을 줄인다.
// arr 배열에서 arr[i]는 arr[i]의 수로 만들수 있는 LIS가 i 이다

public class BOJ12015_가장_긴_증가하는_부분_수열2
{
	public static void main(String[ ] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		int N = Integer.parseInt(st.nextToken( ));
		int arr[] = new int[ N ];
		st = new StringTokenizer(br.readLine( ));

		// arr[idx] : arr[idx]의 숫자로 만들수 있는 LIS의 길이가 idx
		int idx = 0;
		for (int i = 0; i < N; i++)
		{
			int here = Integer.parseInt(st.nextToken( ));
			if (idx == 0)
			{
				arr[ idx++ ] = here;
			}
			else
			{
				if (arr[ idx - 1 ] >= here)
				{
					int temp = lowerBound(arr, here, idx);
					arr[ temp ] = here;
				}
				else
				{
					arr[ idx++ ] = here;
				}
			}
		}
		System.out.println(idx);
	}

	static int lowerBound(int[ ] arr, int target, int len)
	{
		int begin = 0;
		int end = len - 1;
		while (begin < end)
		{
			int mid = (begin + end) >> 1;
			if (arr[ mid ] >= target) end = mid;
			else begin = mid + 1;
		}
		return end;
	}

	static void show(int[ ] temp)
	{
		StringBuilder sb = new StringBuilder( );
		int N = temp.length;
		for (int i = 0; i < N; i++)
		{
			sb.append(temp[ i ] + " ");
		}
		System.out.println(sb);
	}

}
