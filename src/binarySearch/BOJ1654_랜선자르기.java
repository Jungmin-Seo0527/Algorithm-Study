
package binarySearch;

import java.io.*;
import java.util.*;

// BOJ1654_랜선자르기
public class BOJ1654_랜선자르기
{
	static int K, N;
	static int arr[];

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		System.out.println(solve( ));
	}

	static long solve( )
	{
		long start = 0;
		long end = Integer.MAX_VALUE;

		while (start <= end)
		{
			long sum = 0;
			long mid = start + end >> 1;
			for (int i = 0; i < K; i++)
			{
				sum += arr[ i ] / mid;
				if (sum >= N) break;
			}
			if (sum < N) end = mid - 1; // 랜선 갯수가 부족 -> 랜선의 길이 감소
			else start = mid + 1;
		}

		return end;
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		K = Integer.parseInt(st.nextToken( ));
		N = Integer.parseInt(st.nextToken( ));
		arr = new int[ K ];
		for (int i = 0; i < K; i++)
		{
			st = new StringTokenizer(br.readLine( ));
			arr[ i ] = Integer.parseInt(st.nextToken( ));
		}
	}
}
