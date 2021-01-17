
package BinarySearch;

import java.io.*;
import java.util.*;

// BOJ3079_입국심사
public class BOJ3079_입국심사
{
	static int N, M, max;
	static int times[];

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		System.out.println(solve( ));
	}

	// times를 오름차순 정렬후
	// start=0, end=가장 긴 time으로만 M명의 사람들을 처리할때의 시간 즉 주어진 조건에서의 최대 소요 시간이다
	// mid에서의 시간에서 각 심사대에서 처리할수 있는 사람들의 수들을 모두 구해준다.
	// 그 사람수를 기준으로 start, end 지점을 정해준다.
	static long solve( )
	{
		long start = 0;
		long end = (long) max * M;

		while (start < end)
		{
			long mid = (start + end) >> 1;
			long sum = 0;
			for (int i = 0; i < N; i++)
			{
				sum += mid / times[ i ];
			}
			if (sum >= M) end = mid;
			else start = mid + 1;
		}
		return end;
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		N = Integer.parseInt(st.nextToken( ));
		M = Integer.parseInt(st.nextToken( ));
		times = new int[ N ];
		for (int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine( ));
			times[ i ] = Integer.parseInt(st.nextToken( ));
			if (max < times[ i ]) max = times[ i ];
		}
	}
}
