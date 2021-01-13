
package BinarySearch;

import java.io.*;
import java.util.*;

//BOJ2805_나무_자르기_2
public class BOJ2805_나무_자르기_2
{
	static int N, M, arr[];

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		System.out.println(solve( ));
	}

	// binary search
	static long solve( )
	{
		// 절단기의 길이를 0부터 1e9까지 이분탐색
		long start = 0;
		long end = (long) 1e9;

		while (start <= end)
		{
			long mid = start + end >> 1; // 중단
			long sum = 0; // 집으로 가져갈 나무
			for (int i = 0; i < N; i++)
			{
				// 나무의 길이가 절단기 길이의 나무보다 큰 경우에만 잘리는 나무의 길이만큼 더해준다
				sum += arr[ i ] < mid ? 0 : arr[ i ] - mid;
				if (sum >= M) break; // 이미 필요한 나무의 길이보다 커지면 break;
			}

			// 이분탐색을 위한 start, end, mid 갱신
			if (sum < M) end = mid - 1; // 절단기의 길이가 현재 설정한 mid보다는 작아야 한다.
			else start = mid + 1; // 절단기의 길이는 mid보다는 큰 값이어야 한다.
		}

		return end;
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		N = Integer.parseInt(st.nextToken( ));
		M = Integer.parseInt(st.nextToken( ));
		arr = new int[ N ];
		st = new StringTokenizer(br.readLine( ));
		for (int i = 0; i < N; i++)
		{
			arr[ i ] = Integer.parseInt(st.nextToken( ));
		}
		//Arrays.sort(arr);
	}
}
