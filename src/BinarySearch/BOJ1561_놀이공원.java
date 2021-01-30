
package BinarySearch;

import java.io.*;
import java.util.*;

// BOJ1561_놀이공원
// Bineary Search 
// 입국심사 문제와 비슷한 문제
// 놀이기구들이 소요하는 시간이 주어지고 주어진 인원수의 사람들이 모두 놀이기구를 탈때
// 마지막 인원이 타는 놀이기구의 기구번호를 찾는 문제

// 입국심사 문제와 같이 우선은 모든 인원이 놀이기구를 타는 최소 시간을 이분탐색을 이용하여 구한다
// 최소시간 바로 이전 시간에 놀이기구를 탄 인원수를 구하고
// 최소시간에 탈수 있는 기구들을 하나씩 탐색해서 인원수를 하나씩 늘려나간다.
// 늘려나간 인원수가 N이 되는 시점에 타는 놀이기구가 마지막 인원이 타는 놀이기구
public class BOJ1561_놀이공원
{
	static int N, M, arr[], max;

	public static void main(String[ ] args) throws IOException
	{
		inputAndSettingData( );
		solve( );
	}

	static void solve( )
	{
		// 입장하는 인원수가 놀이기구 수보다 작거나 같은 경우
		if (N <= M)
		{
			System.out.println(N);
			return;
		}

		// 모든 인원이 놀이기구를 탈수있는 가작 최소 시간 구하기
		long start = 0;
		long end = (long) max * N;
		while (start < end)
		{
			long mid = (start + end) >>> 1;
			long sum = 0;
			for (int i = 0; i < M; i++)
			{
				sum += (long) mid / arr[ i ] + 1;
			}
			if (sum >= N) end = mid;
			else start = mid + 1;
		}

		// 최소시간 바로 이전에 놀이기구를 탄 인원 구하기
		long sum = 0;
		for (int i = 0; i < M; i++)
		{
			sum += (long) (end - 1) / arr[ i ] + 1;
		}

		// 최소시간에 탈수 있는 놀이기구들을 찾으면서 인원수를 하나씩 더한다.
		// 그 인원수가 N일때 타는 놀이기구가 마지막 인원이 탈 놀이기구
		for (int i = 0; i < M; i++)
		{
			if (end % arr[ i ] == 0) sum++;
			if (sum == N)
			{
				System.out.println(i + 1);
				return;
			}
		}
	}

	static void inputAndSettingData( ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine( ));
		N = Integer.parseInt(st.nextToken( ));
		M = Integer.parseInt(st.nextToken( ));
		arr = new int[ M ];
		st = new StringTokenizer(br.readLine( ));
		for (int i = 0; i < M; i++)
		{
			arr[ i ] = Integer.parseInt(st.nextToken( ));
			if (arr[ i ] > max) max = arr[ i ];
		}
	}
}
